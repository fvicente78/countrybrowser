package pt.fv.frontend.mbean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pt.fv.services.api.CountryService;
import pt.fv.services.api.ImagingService;
import pt.fv.services.api.bean.Country;
import pt.fv.services.api.bean.CountryNotFoundException;
import pt.fv.services.api.bean.EmptyWeatherDataException;
import pt.fv.services.api.bean.WeatherReport;

/**
 * Controller JSF responsável por atender pedidos relativos a países
 *
 * @author fvicente
 */
@ManagedBean(name = "countryMB")
@RequestScoped
public class CountryManagedBean implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Log Handler
     */
    private final transient Log log = LogFactory.getLog(getClass());

    /**
     * Serviço da camada de negócio que fornece informações de país
     */
    @ManagedProperty(value = "#{countryService}")
    private transient CountryService countryService;

    /**
     * Serviço da camada de negócio que fornece informações de imagens
     */
    @ManagedProperty(value = "#{imagingService}")
    private transient ImagingService imagingService;

    /**
     * Código ISO-3166 de país
     * Input de página
     */
    private String countryCode;

    /**
     * Indicador de utilização de informação de país de fonte de dados alternativa
     * (Para testar integração com Webservices SOAP)
     * Input de página
     */
    private boolean alternativeDataSource;

    /**
     * País devolvido pela pesquisa
     * Output de página
     */
    private Country country;

    /**
     * Boletim meteorológico do País
     * Output de página
     */
    private WeatherReport weatherReport;

    /**
     * URLs de imagens associadas ao país/capital pesquisada
     * Output de página
     */
    private Collection<String> imageURLs;

    /**
     * @param countryService the countryService to set
     */
    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

    /**
     * @param imagingService the imagingService to set
     */
    public void setImagingService(ImagingService imagingService) {
        this.imagingService = imagingService;
    }

    /**
     * @return the countryCode
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * @param countryCode the countryCode to set
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * @return the alternativeDataSource
     */
    public boolean isAlternativeDataSource() {
        return alternativeDataSource;
    }

    /**
     * @param alternativeDataSource the alternativeDataSource to set
     */
    public void setAlternativeDataSource(boolean alternativeDataSource) {
        this.alternativeDataSource = alternativeDataSource;
    }

    /**
     * @return the country
     */
    public Country getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(Country country) {
        this.country = country;
    }

    /**
     * @return the weatherReport
     */
    public WeatherReport getWeatherReport() {
        return weatherReport;
    }

    /**
     * @param weatherReport the weatherReport to set
     */
    public void setWeatherReport(WeatherReport weatherReport) {
        this.weatherReport = weatherReport;
    }

    /**
     * @return the imageURLs
     */
    public Collection<String> getImageURLs() {
        return imageURLs;
    }

    /**
     * @param imageURLs the imageURLs to set
     */
    public void setImageURLs(Collection<String> imageURLs) {
        this.imageURLs = imageURLs;
    }

    /**
     * Pesquisa informação de país consoante o código fornecido
     *
     * @return resultado da acção de pesquisa
     */
    public String findCountry() {
        Country country;

        //obtém informação de país
        try {
            country = countryService.findCountry(getCountryCode());
            setCountry(country);

            //preenche imagens relacionadas com resultado da pesquisa
            buildImageUrls(country);

        } catch (CountryNotFoundException e) {
            return handleFindCountryError(e, "Country not found");
        } catch (Exception e) {
            return handleFindCountryError(e, "Search error, please check criteria and try again");
        }

        //obtém boletim meteorológico da capital
        if (isAlternativeDataSource()) {
            try {
                setWeatherReport(countryService.getWeatherReport(country.getCapital().getName()));
            } catch (EmptyWeatherDataException e) {
                return handleFindCountryError(e, "No weather report found for " + country.getCapital().getName());
            }

        } else {
            setWeatherReport(country.getCapital().getCurrentWeatherReport());
        }

        return null; //fica na mesma página
    }

    /*
     * obtém imagens relacionadas com país/capital
     */
    private void buildImageUrls(Country country) {
        if (country != null) {
            String[] tags;
            String[] urls;

            if (country.getCapital() != null) {
                tags = new String[2];
                tags[1] = country.getCapital().getName();
            } else {
                tags = new String[1];
            }

            tags[0] = country.getName();
            urls = imagingService.searchImageUrls(tags);
            if (urls != null) {
                setImageURLs(Arrays.asList(urls));
            }
        }
    }

    private String handleFindCountryError(Exception e, String message) {
        log.error(e.getMessage(), e);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
        return null; //stays on same view
    }

}
