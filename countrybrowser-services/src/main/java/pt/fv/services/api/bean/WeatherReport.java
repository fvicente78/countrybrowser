package pt.fv.services.api.bean;

import java.io.Serializable;

/**
 * Informação meteorológica para uma coordenada geográfica
 *
 * @author fvicente
 */
public class WeatherReport implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Localidade (país/cidade)
     */
    private String location;

    /**
     * Instante de obtenção da informação
     */
    private String timestamp;

    /**
     * Velocidade e direcção do vento
     */
    private String wind;

    /**
     * Aspecto do céu
     */
    private String skyConditions;

    /**
     * Temperatura
     */
    private String temperature;

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return the wind
     */
    public String getWind() {
        return wind;
    }

    /**
     * @param wind the wind to set
     */
    public void setWind(String wind) {
        this.wind = wind;
    }

    /**
     * @return the skyConditions
     */
    public String getSkyConditions() {
        return skyConditions;
    }

    /**
     * @param skyConditions the skyConditions to set
     */
    public void setSkyConditions(String skyConditions) {
        this.skyConditions = skyConditions;
    }

    /**
     * @return the temperature
     */
    public String getTemperature() {
        return temperature;
    }

    /**
     * @param temperature the temperature to set
     */
    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    /**
     * {@inheritDoc}
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("WeatherReport [location=");
        builder.append(location);
        builder.append(", timestamp=");
        builder.append(timestamp);
        builder.append(", wind=");
        builder.append(wind);
        builder.append(", skyConditions=");
        builder.append(skyConditions);
        builder.append(", temperature=");
        builder.append(temperature);
        builder.append("]");
        return builder.toString();
    }

}
