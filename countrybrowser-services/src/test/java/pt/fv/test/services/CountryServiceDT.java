package pt.fv.test.services;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.util.Assert;

import pt.fv.services.api.CountryService;
import pt.fv.services.api.bean.City;
import pt.fv.services.api.bean.Country;
import pt.fv.services.api.bean.CountryNotFoundException;
import pt.fv.services.api.bean.EmptyWeatherDataException;
import pt.fv.services.api.bean.WeatherReport;

/**
 * Testes unitários sobre o serviço {@link pt.fv.services.api.CountryService}
 *
 * @author fvicente
 */
@ContextConfiguration({"countrybrowser-test-context.xml"})
public class CountryServiceDT extends AbstractJUnit4SpringContextTests {

    @Autowired
    private CountryService countryService;

    /**
     * Testa a correcta obtenção dos dados relativos a Portugal
     *
     * Particularmente valida informação sobre:
     *   - dados genéricos do país
     *   - dados genéricos da capital do país
     *   - existência de boletim meteorológico para a capital
     */
    @Test
    public void testFindPortugal() throws CountryNotFoundException {
        String code = "PT";
        Country country;

        country = countryService.findCountry(code);
        assertValidCountry(code, country);
        assertValidCapital(country.getCapital());
    }

    /**
     * Valida a ocorrência de excepção CountryNotFoundException na tentativa de obter país inexistente.
     */
    @Test
    public void testFindCountryFailOnInvalidCountry() {
        String code = "xx";
        Exception errorOccured = null;

        try {
            countryService.findCountry(code);
        } catch (CountryNotFoundException e) {
            errorOccured = e;
        }

        Assert.notNull(errorOccured, "CountryNotFoundException not sent when expected");
        Assert.isAssignable(CountryNotFoundException.class, errorOccured.getClass(), "Exception [" + errorOccured.getClass().getName()
            + "] occured when expecting [" + CountryNotFoundException.class.getName() + "]");
    }

    /**
     * Testa o suporte case-insensitive na pesquisa de países.
     *
     * Particularmente valida:
     *   - informação de país requisitado com código em lower-case é a mesma de que executar pedido com código em upper-case
     */
    @Test
    public void testFindCountryCaseInsensitive() throws CountryNotFoundException {
        String lowerCode = "pt";
        String upperCode = lowerCode.toUpperCase();
        Country lowerCountry, upperCountry;

        //pesquisa país em letra minúscula
        lowerCountry = countryService.findCountry(lowerCode);
        assertValidCountry(lowerCode, lowerCountry);
        assertValidCapital(lowerCountry.getCapital());

        //pesquisa país em letra maiúscula
        upperCountry = countryService.findCountry(lowerCode);
        assertValidCountry(upperCode, upperCountry);
        assertValidCapital(upperCountry.getCapital());

        //valida se país é o mesmo
        Assert.isTrue(lowerCountry.getIso3166Code().equals(upperCountry.getIso3166Code()), "country.iso3166Code is different");
        Assert.isTrue(lowerCountry.getName().equals(upperCountry.getName()), "country.name is different");
        Assert.isTrue(
            lowerCountry.getCurrency().getCurrencyCode().equals(upperCountry.getCurrency().getCurrencyCode()),
            "country.currency is different");
        Assert.isTrue(lowerCountry.getLanguage().equals(upperCountry.getLanguage()), "country.language is different");
        Assert.isTrue(lowerCountry.getPopulation() == upperCountry.getPopulation(), "country.population is different");
    }

    /**
     * Testa a correcta obtenção do boletim meteorológico para Lisboa
     *
     * @throws EmptyWeatherDataException se não houver informação meteorológica
     */
    @Test
    public void testGetWeatherForLisboa() throws EmptyWeatherDataException {
        String city = "Lisboa";
        WeatherReport report;

        report = countryService.getWeatherReport(city);
        assertWeatherReport(report);
    }

    /**
     * Testa a correcta obtenção do boletim meteorológico para Lisboa
     *
     * @throws EmptyWeatherDataException se não houver informação meteorológica
     */
    @Test
    public void testGetWeatherFailForInvalidCity() {
        String city = "xpto";
        Exception errorOccured = null;

        try {
            countryService.getWeatherReport(city);
        } catch (EmptyWeatherDataException e) {
            errorOccured = e;
        }

        Assert.notNull(errorOccured, "EmptyWeatherDataException not sent when expected");
        Assert.isAssignable(EmptyWeatherDataException.class, errorOccured.getClass(), "Exception [" + errorOccured.getClass().getName()
            + "] occured when expecting [" + CountryNotFoundException.class.getName() + "]");
    }

    private void assertValidCountry(String code, Country country) {
        Assert.notNull(country, "country is null");
        Assert.notNull(code, "validation isoCode is null");
        Assert.isTrue(code.toUpperCase().equals(country.getIso3166Code()), "country.iso3166Code is different");
        Assert.notNull(country.getCapital(), "country.capital is null");
        Assert.notNull(country.getCurrency(), "country.currency is null");
        Assert.notNull(country.getLanguage(), "country.language is null");
        Assert.notNull(country.getName(), "country.name is null");
        Assert.isTrue(country.getPopulation() > 0, "country.population is invalid");
    }

    private void assertValidCapital(City capital) {
        assertValidCity(capital);
        Assert.isTrue(capital.isCapital(), "city [" + capital.getName() + "] is not a capital");
    }

    private void assertValidCity(City city) {
        Assert.notNull(city, "city is null");
        Assert.notNull(city.getCoordinates(), "city.coordinates is null");
        Assert.notNull(city.getIcaoCode(), "city.icaoCode is null");
        Assert.notNull(city.getName(), "city.name is null");
    }

    private void assertWeatherReport(WeatherReport weather) {
        Assert.notNull(weather, "weather is null");
        Assert.notNull(weather.getLocation(), "weather.location is null");
        Assert.notNull(weather.getSkyConditions(), "weather.skyConditions is null");
        Assert.notNull(weather.getTemperature(), "weather.termperature is null");
        Assert.notNull(weather.getTimestamp(), "weather.timestamp is null");
        Assert.notNull(weather.getWind(), "weather.wind is null");
    }
}
