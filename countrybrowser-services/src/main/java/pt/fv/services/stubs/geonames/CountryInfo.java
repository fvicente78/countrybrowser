package pt.fv.services.stubs.geonames;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Stub de país na resposta ao serviço http://api.geonames.org/countryInfoJSON
 *
 * @author fvicente
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryInfo {

    private String countryCode;
    private String countryName;
    private int isoNumeric;
    private String isoAlpha3;
    private String fipsCode;
    private String continent;
    private String continentName;
    private String capital;
    private BigDecimal areaInSqKm;
    private int population;
    private String currencyCode;
    private String languages;
    private String geonameId;
    private String west;
    private String north;
    private String east;
    private String south;

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
     * @return the countryName
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * @param countryName the countryName to set
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * @return the isoNumeric
     */
    public int getIsoNumeric() {
        return isoNumeric;
    }

    /**
     * @param isoNumeric the isoNumeric to set
     */
    public void setIsoNumeric(int isoNumeric) {
        this.isoNumeric = isoNumeric;
    }

    /**
     * @return the isoAlpha3
     */
    public String getIsoAlpha3() {
        return isoAlpha3;
    }

    /**
     * @param isoAlpha3 the isoAlpha3 to set
     */
    public void setIsoAlpha3(String isoAlpha3) {
        this.isoAlpha3 = isoAlpha3;
    }

    /**
     * @return the fipsCode
     */
    public String getFipsCode() {
        return fipsCode;
    }

    /**
     * @param fipsCode the fipsCode to set
     */
    public void setFipsCode(String fipsCode) {
        this.fipsCode = fipsCode;
    }

    /**
     * @return the continent
     */
    public String getContinent() {
        return continent;
    }

    /**
     * @param continent the continent to set
     */
    public void setContinent(String continent) {
        this.continent = continent;
    }

    /**
     * @return the continentName
     */
    public String getContinentName() {
        return continentName;
    }

    /**
     * @param continentName the continentName to set
     */
    public void setContinentName(String continentName) {
        this.continentName = continentName;
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
     * @return the areaInSqKm
     */
    public BigDecimal getAreaInSqKm() {
        return areaInSqKm;
    }

    /**
     * @param areaInSqKm the areaInSqKm to set
     */
    public void setAreaInSqKm(BigDecimal areaInSqKm) {
        this.areaInSqKm = areaInSqKm;
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
     * @return the currencyCode
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * @param currencyCode the currencyCode to set
     */
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    /**
     * @return the languages
     */
    public String getLanguages() {
        return languages;
    }

    /**
     * @param languages the languages to set
     */
    public void setLanguages(String languages) {
        this.languages = languages;
    }

    /**
     * @return the geonameId
     */
    public String getGeonameId() {
        return geonameId;
    }

    /**
     * @param geonameId the geonameId to set
     */
    public void setGeonameId(String geonameId) {
        this.geonameId = geonameId;
    }

    /**
     * @return the west
     */
    public String getWest() {
        return west;
    }

    /**
     * @param west the west to set
     */
    public void setWest(String west) {
        this.west = west;
    }

    /**
     * @return the north
     */
    public String getNorth() {
        return north;
    }

    /**
     * @param north the north to set
     */
    public void setNorth(String north) {
        this.north = north;
    }

    /**
     * @return the east
     */
    public String getEast() {
        return east;
    }

    /**
     * @param east the east to set
     */
    public void setEast(String east) {
        this.east = east;
    }

    /**
     * @return the south
     */
    public String getSouth() {
        return south;
    }

    /**
     * @param south the south to set
     */
    public void setSouth(String south) {
        this.south = south;
    }

    /**
     * {@inheritDoc}
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("GeoNameItem [countryCode=");
        builder.append(countryCode);
        builder.append(", countryName=");
        builder.append(countryName);
        builder.append(", isoNumeric=");
        builder.append(isoNumeric);
        builder.append(", isoAlpha3=");
        builder.append(isoAlpha3);
        builder.append(", fipsCode=");
        builder.append(fipsCode);
        builder.append(", continent=");
        builder.append(continent);
        builder.append(", continentName=");
        builder.append(continentName);
        builder.append(", capital=");
        builder.append(capital);
        builder.append(", areaInSqKm=");
        builder.append(areaInSqKm);
        builder.append(", population=");
        builder.append(population);
        builder.append(", currencyCode=");
        builder.append(currencyCode);
        builder.append(", languages=");
        builder.append(languages);
        builder.append(", geonameId=");
        builder.append(geonameId);
        builder.append(", west=");
        builder.append(west);
        builder.append(", north=");
        builder.append(north);
        builder.append(", east=");
        builder.append(east);
        builder.append(", south=");
        builder.append(south);
        builder.append("]");
        return builder.toString();
    }

}
