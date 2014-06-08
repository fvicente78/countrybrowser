package pt.fv.services.api.bean;

import java.io.Serializable;

/**
 * Representa entidade Cidade dentro de um País.
 *
 * @author fvicente
 */
public class City extends Place implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Indicador de cidade capital de País
     */
    private boolean isCapital = false;

    /**
     * Código ICAO associado
     */
    private String icaoCode;

    /**
     * Informação meteorológica para o dia actual
     */
    private WeatherReport currentWeatherReport;

    /**
     * País da cidade
     */
    private Country country;

    /**
     * @return the isCapital
     */
    public boolean isCapital() {
        return isCapital;
    }

    /**
     * @param isCapital the isCapital to set
     */
    public void setCapital(boolean isCapital) {
        this.isCapital = isCapital;
    }

    /**
     * @return the icaoCode
     */
    public String getIcaoCode() {
        return icaoCode;
    }

    /**
     * @param icaoCode the icaoCode to set
     */
    public void setIcaoCode(String icaoCode) {
        this.icaoCode = icaoCode;
    }

    /**
     * @return the currentWeatherReport
     */
    public WeatherReport getCurrentWeatherReport() {
        return currentWeatherReport;
    }

    /**
     * @param currentWeatherReport the currentWeatherReport to set
     */
    public void setCurrentWeatherReport(WeatherReport currentWeatherReport) {
        this.currentWeatherReport = currentWeatherReport;
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

}
