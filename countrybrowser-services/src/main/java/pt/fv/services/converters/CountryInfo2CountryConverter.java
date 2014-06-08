package pt.fv.services.converters;

import java.util.Currency;

import org.springframework.core.convert.converter.Converter;

import pt.fv.services.api.bean.City;
import pt.fv.services.api.bean.Country;
import pt.fv.services.stubs.geonames.CountryInfo;

/**
 * Conversor de entidades CountryInfo do serviço REST externo para entidades Country da api local
 *
 * @author fvicente
 */
public class CountryInfo2CountryConverter implements Converter<CountryInfo, Country> {

    /**
     * {@inheritDoc}
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    public Country convert(CountryInfo src) {
        Country target = null;

        if (src != null) {
            target = new Country();

            target.setCapital(buildCapital(src.getCapital()));
            target.setCoordinates(buildCoordinates(src.getNorth(), src.getEast(), src.getSouth(), src.getWest()));
            target.setCurrency(buildCurrency(src.getCurrencyCode()));
            target.setIso3166Code(src.getCountryCode());
            target.setLanguage(src.getLanguages());
            target.setName(src.getCountryName());
            target.setPopulation(src.getPopulation());
        }
        return target;
    }

    /*
     * Cria String descritiva das coordenadas fornecidas
     */
    private String buildCoordinates(String north, String east, String south, String west) {
        String coord = null;
        if (north != null || east != null || south != null || west != null) {
            StringBuffer buf = new StringBuffer();
            appendIfNotNull(buf, north, "N=");
            appendIfNotNull(buf, east, "E=");
            appendIfNotNull(buf, south, "S=");
            appendIfNotNull(buf, west, "W=");

            coord = buf.toString();
            if (coord.length() == 0) {
                coord = null;
            }
        }

        return coord;
    }

    /*
     * Adiciona value a buf com sufixo key, se value tiver conteúdo
     */
    private void appendIfNotNull(StringBuffer buf, String value, String key) {
        if (value != null) {
            if (key != null) {
                buf.append(key);
            }
            buf.append(value).append(";");
        }
    }

    /*
     * Cria instância de objecto Currency com base num código de moeda
     */
    private Currency buildCurrency(String currencyCode) {
        Currency currency = null;
        if (currencyCode != null) {
            currency = Currency.getInstance(currencyCode);
        }

        return currency;
    }

    /*
     * Criar instância simples de Capital apenas com o nome
     */
    private City buildCapital(String capitalCity) {
        City capital = null;
        if (capitalCity != null) {
            capital = new City();
            capital.setName(capitalCity);
            capital.setCapital(true);
        }

        return capital;
    }

}
