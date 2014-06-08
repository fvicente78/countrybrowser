package pt.fv.services.converters;

import org.springframework.core.convert.converter.Converter;

import pt.fv.services.api.bean.WeatherReport;
import pt.fv.services.stubs.geonames.WeatherObservation;

/**
 * Conversor de entidades WeatherObservation do serviço REST externo para entidades WeatherReport da api local
 *
 * @author fvicente
 */
public class WeatherObservation2WeatherReportConverter implements Converter<WeatherObservation, WeatherReport> {

    /**
     * {@inheritDoc}
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    public WeatherReport convert(WeatherObservation src) {
        WeatherReport target = null;

        if (src != null) {
            target = new WeatherReport();

            target.setLocation(src.getStationName());
            target.setSkyConditions(src.getClouds());
            target.setTemperature(String.valueOf(src.getTemperature()));
            target.setTimestamp(src.getDatetime());
            target.setWind(String.valueOf(src.getWindSpeed()));
        }

        return target;
    }

}
