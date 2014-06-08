package pt.fv.services.stubs.geonames;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Stub de boletim meteorológico na resposta ao serviço http://api.geonames.org/weatherJSON
 *
 * @author fvicente
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherObservation {

    private String clouds;
    @JsonProperty(value = "ICAO")
    private String icao;
    private BigDecimal lng;
    private int temperature;
    private int windSpeed;
    private String stationName;
    private String datetime;
    private BigDecimal lat;

    /**
     * @return the clouds
     */
    public String getClouds() {
        return clouds;
    }

    /**
     * @param clouds the clouds to set
     */
    public void setClouds(String clouds) {
        this.clouds = clouds;
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
     * @return the lng
     */
    public BigDecimal getLng() {
        return lng;
    }

    /**
     * @param lng the lng to set
     */
    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    /**
     * @return the temperature
     */
    public int getTemperature() {
        return temperature;
    }

    /**
     * @param temperature the temperature to set
     */
    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    /**
     * @return the windSpeed
     */
    public int getWindSpeed() {
        return windSpeed;
    }

    /**
     * @param windSpeed the windSpeed to set
     */
    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    /**
     * @return the stationName
     */
    public String getStationName() {
        return stationName;
    }

    /**
     * @param stationName the stationName to set
     */
    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    /**
     * @return the datetime
     */
    public String getDatetime() {
        return datetime;
    }

    /**
     * @param datetime the datetime to set
     */
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    /**
     * @return the lat
     */
    public BigDecimal getLat() {
        return lat;
    }

    /**
     * @param lat the lat to set
     */
    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    /**
     * {@inheritDoc}
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("WeatherObservation [clouds=");
        builder.append(clouds);
        builder.append(", icao=");
        builder.append(icao);
        builder.append(", lng=");
        builder.append(lng);
        builder.append(", temperature=");
        builder.append(temperature);
        builder.append(", windSpeed=");
        builder.append(windSpeed);
        builder.append(", stationName=");
        builder.append(stationName);
        builder.append(", datetime=");
        builder.append(datetime);
        builder.append(", lat=");
        builder.append(lat);
        builder.append("]");
        return builder.toString();
    }

}
