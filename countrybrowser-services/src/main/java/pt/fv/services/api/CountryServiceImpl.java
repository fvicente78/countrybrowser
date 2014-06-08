package pt.fv.services.api;

import java.math.BigDecimal;
import java.text.MessageFormat;

import net.webservicex.GetWeather;
import net.webservicex.GetWeatherResponse;
import net.webservicex.ObjectFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestOperations;
import org.springframework.ws.client.core.WebServiceTemplate;

import pt.fv.services.api.bean.City;
import pt.fv.services.api.bean.Country;
import pt.fv.services.api.bean.CountryNotFoundException;
import pt.fv.services.api.bean.EmptyWeatherDataException;
import pt.fv.services.api.bean.WeatherReport;
import pt.fv.services.stubs.geonames.CountryInfo;
import pt.fv.services.stubs.geonames.GeoNames;
import pt.fv.services.stubs.geonames.WeatherObservation;

/**
 * {@inheritDoc}
 * @see pt.fv.services.api.CountryService
 *
 * @author fvicente
 */
@Service
public class CountryServiceImpl implements CountryService {

    /**
     * Log Handler
     */
    private final Log log = LogFactory.getLog(getClass());

    /**
     * Handler para comunicação REST
     */
    private RestOperations restOperationHandler;

    /**
     * Handler para comunicação com http://www.webservicex.net/globalweather.asmx
     */
    private WebServiceTemplate weatherService;

    /**
     * Factory de objectos para o webserviceX;
     */
    private ObjectFactory webservicexFactory;

    /**
     * Serviço de conversão de Beans
     * Autowired por ser parte de infraestrutura de suporte e não negócio core
     */
    @Autowired
    private ConversionService conversionService;

    /**
     * Query REST a utilizar na pesquisa de informação de país
     * Deverá contemplar um placeholder para passar o código de país como argumento
     */
    private static String COUNTRY_BY_CODE_QUERY = "http://api.geonames.org/countryInfoJSON?lang=en&country={0}&username=fvicente&style=full";

    /**
     * Query REST a utilizar na pesquisa de boletim meteorológico por coordenada
     * Deverá contemplar quatro placeholders para passar o código de país como argumento
     */
    private static String WEATHER_BY_COORDINATES_QUERY = "http://api.geonames.org/weatherJSON?north={0}&south={1}&east={2}&west={3}&username=fvicente&style=full";

    /**
     * @param restOperationHandler the restOperationHandler to set
     */
    @Required
    public void setRestOperationHandler(RestOperations restOperationHandler) {
        this.restOperationHandler = restOperationHandler;
    }

    /**
     * @param weatherService the weatherService to set
     */
    @Required
    public void setWeatherService(WebServiceTemplate weatherService) {
        this.weatherService = weatherService;
    }

    /**
     * @param webservicexFactory the webservicexFactory to set
     */
    @Required
    public void setWebservicexFactory(ObjectFactory webservicexFactory) {
        this.webservicexFactory = webservicexFactory;
    }

    /**
     * {@inheritDoc}
     * @see pt.fv.services.api.CountryService#findCountry(java.lang.String)
     */
    public Country findCountry(String iso3166Code) throws CountryNotFoundException {
        Country country;
        WeatherReport weatherReport;
        CountryInfo countryInfo;
        WeatherObservation weatherObservation;

        Assert.notNull(iso3166Code);
        Assert.isTrue(iso3166Code.trim().length() == 2, "Invalid country code size");

        //formata código para upper-case e pequisa país em subsistema externo
        countryInfo = findCountryInGeonames(iso3166Code.trim().toUpperCase());
        country = conversionService.convert(countryInfo, Country.class);

        //pesquisa boletim meteorológico e código ICAO em subsistema externo
        try {
            City capital = country.getCapital();

            weatherObservation = getWeatherInGeonames(countryInfo);
            weatherReport = conversionService.convert(weatherObservation, WeatherReport.class);

            //preenche informação adicional para a capital
            capital.setCurrentWeatherReport(weatherReport);
            capital.setIcaoCode(weatherObservation.getIcao());
            capital.setCoordinates(buildCoordinates(weatherObservation.getLat(), weatherObservation.getLng()));

        } catch (EmptyWeatherDataException e) {
            //apenas avisa e continua, pois os dados aqui obtidos não são impeditivos de uma resposta menos completa
            log.warn(e.getMessage(), e);
        }

        return country;
    }

    /**
     * {@inheritDoc}
     * @see pt.fv.services.api.CountryService#getWeatherReport(java.lang.String)
     */
    public WeatherReport getWeatherReport(String cityName) throws EmptyWeatherDataException {
        GetWeather weatherRequest;
        GetWeatherResponse weatherResponse;
        WeatherReport report;

        weatherRequest = webservicexFactory.createGetWeather();
        weatherRequest.setCityName(cityName);
        weatherRequest.setCountryName(""); //necessário pela API remota

        //invocação remota
        weatherResponse = (GetWeatherResponse) weatherService.marshalSendAndReceive(weatherRequest);
        if (weatherResponse == null || weatherResponse.getGetWeatherResult() == null) {
            throw new EmptyWeatherDataException(cityName);
        }
        log.info("weatherResponse=[" + weatherResponse.getGetWeatherResult() + "]");

        //conversão para objecto da API local
        report = conversionService.convert(weatherResponse.getGetWeatherResult(), WeatherReport.class);
        log.info("report=[" + report + "]");
        if (report == null) {
            throw new EmptyWeatherDataException(cityName);
        }

        return report;
    }

    /*
     * Obtém informação de um país através do serviço externo geonames
     */
    private CountryInfo findCountryInGeonames(String formattedCode) throws CountryNotFoundException {
        //constrói URL de pesquisa
        String queryUrl = MessageFormat.format(COUNTRY_BY_CODE_QUERY, formattedCode);

        //apenas um resultado é esperado
        GeoNames res = restOperationHandler.getForObject(queryUrl, GeoNames.class);
        log.info("res=[" + res + "]");
        if (res == null || res.getGeonames() == null || res.getGeonames().isEmpty()) {
            throw new CountryNotFoundException(formattedCode);
        }
        Assert.isTrue(res.getGeonames().size() == 1, "More than one country found when only one was expected. Count=["
            + res.getGeonames().size() + "]");

        return res.getGeonames().iterator().next();
    }

    /*
     * Obtém boletim meterológico através do serviço externo geonames
     */
    private WeatherObservation getWeatherInGeonames(CountryInfo countryInfo) throws EmptyWeatherDataException {
        //constrói URL de pesquisa
        String queryUrl = MessageFormat.format(
            WEATHER_BY_COORDINATES_QUERY,
            countryInfo.getNorth(),
            countryInfo.getSouth(),
            countryInfo.getEast(),
            countryInfo.getWest());

        //apenas um resultado é esperado
        GeoNames res = restOperationHandler.getForObject(queryUrl, GeoNames.class);
        log.info("res=[" + res + "]");
        if (res == null || res.getWeatherObservations() == null || res.getWeatherObservations().isEmpty()) {
            throw new EmptyWeatherDataException(countryInfo.getCapital());
        }

        return res.getWeatherObservations().iterator().next();
    }

    /*
     * Constrói descritivo de coordenadas
     */
    private String buildCoordinates(BigDecimal lat, BigDecimal lng) {
        String coord = null;
        if (lat != null || lng != null) {
            StringBuffer buf = new StringBuffer();
            appendIfNotNull(buf, lat, "Lat=");
            appendIfNotNull(buf, lng, "Lng=");

            coord = buf.toString();
            if (coord.length() == 0) {
                coord = null;
            }
        }

        return coord;
    }

    /*
     * Adiciona value a buf com sufixo key, se value tiver conteúdo
     */
    private void appendIfNotNull(StringBuffer buf, BigDecimal value, String key) {
        if (value != null) {
            if (key != null) {
                buf.append(key);
            }
            buf.append(value).append(";");
        }
    }
}
