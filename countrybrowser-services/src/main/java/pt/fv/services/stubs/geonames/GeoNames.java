package pt.fv.services.stubs.geonames;

import java.util.Collection;
import java.util.Collections;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Stub para wrapper de resposta JSON do serviço GeoNames
 *
 * @author fvicente
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoNames {
    /**
     * Resultados de informação de países para invocações ao serviço http://api.geonames.org/countryInfoJSON
     */
    private Collection<CountryInfo> geonames;

    /**
     * Resultados de informação de boletins meteorológicos para invocações ao serviço http://api.geonames.org/weatherJSON
     */
    private Collection<WeatherObservation> weatherObservations;

    /**
     * @return the geonames
     */
    public Collection<CountryInfo> getGeonames() {
        return this.geonames;
    }

    /**
     * @param geonames the geonames to set
     */
    public void setGeonames(Collection<CountryInfo> geonames) {
        this.geonames = Collections.unmodifiableCollection(geonames);
    }

    /**
     * @return the weatherObservations
     */
    public Collection<WeatherObservation> getWeatherObservations() {
        return weatherObservations;
    }

    /**
     * @param weatherObservations the weatherObservations to set
     */
    public void setWeatherObservations(Collection<WeatherObservation> weatherObservations) {
        this.weatherObservations = Collections.unmodifiableCollection(weatherObservations);
    }

    /**
     * {@inheritDoc}
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("GeoNames [geonames=");
        builder.append(geonames);
        builder.append(", weatherObservations=");
        builder.append(weatherObservations);
        builder.append("]");
        return builder.toString();
    }

}
