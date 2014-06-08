package pt.fv.webservice.converters;

import org.springframework.core.convert.converter.Converter;

import pt.fv.services.api.bean.City;
import pt.fv.services.api.bean.Country;
import pt.fv.webservice.bean.CountryInfo;

/**
 * Conversor de entidades Country do serviço de negócio interno para entidades CountryInfo de suporte ao webservice REST local
 *
 * @author fvicente
 */
public class Country2CountryInfoConverter implements Converter<Country, CountryInfo> {

    /**
     * {@inheritDoc}
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    @Override
    public CountryInfo convert(Country src) {
        CountryInfo target = null;

        if (src != null) {
            target = new CountryInfo();
            target.setCoordinates(src.getCoordinates());
            target.setCurrency(src.getCurrency() != null ? src.getCurrency().getCurrencyCode() : null);
            target.setLanguage(src.getLanguage());
            target.setPopulation(src.getPopulation());

            if (src.getCapital() != null) {
                City capital = src.getCapital();

                target.setCapital(capital.getName());
                target.setIcao(capital.getIcaoCode());
                target.setSimpleWeather(capital.getCurrentWeatherReport() != null ? capital.getCurrentWeatherReport().getSkyConditions()
                    : null);
            }
        }

        return target;
    }

}
