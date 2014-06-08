package pt.fv.webservice.bean;

/**
 * Representa resposta com informação de país para o webservice.
 *
 * @author fvicente
 */
public class CountryInfo {

    private String language;
    private int population;
    private String currency;
    private String capital;
    private String icao;
    private String coordinates;
    private String simpleWeather;

    /**
     * @return the language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language the language to set
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * @return the population
     */
    public int getPopulation() {
        return population;
    }

    /**
     * @param population the population to set
     */
    public void setPopulation(int population) {
        this.population = population;
    }

    /**
     * @return the currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @param currency the currency to set
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * @return the capital
     */
    public String getCapital() {
        return capital;
    }

    /**
     * @param capital the capital to set
     */
    public void setCapital(String capital) {
        this.capital = capital;
    }

    /**
     * @return the icao
     */
    public String getIcao() {
        return icao;
    }

    /**
     * @param icao the icao to set
     */
    public void setIcao(String icao) {
        this.icao = icao;
    }

    /**
     * @return the coordinates
     */
    public String getCoordinates() {
        return coordinates;
    }

    /**
     * @param coordinates the coordinates to set
     */
    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * @return the simpleWeather
     */
    public String getSimpleWeather() {
        return simpleWeather;
    }

    /**
     * @param simpleWeather the simpleWeather to set
     */
    public void setSimpleWeather(String simpleWeather) {
        this.simpleWeather = simpleWeather;
    }

}
