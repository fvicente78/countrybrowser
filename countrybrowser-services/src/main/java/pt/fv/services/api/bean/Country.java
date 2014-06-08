package pt.fv.services.api.bean;

import java.io.Serializable;
import java.util.Currency;

/**
 * Representa entidade País.
 *
 * @author fvicente
 */
public class Country extends Place implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Código ISO-3166
     */
    private String iso3166Code;

    /**
     * Língua Oficial
     */
    private String language;

    /**
     * Moeda Oficial
     */
    private Currency currency;

    /**
     * Capital do país
     */
    private City capital;

    /**
     * @return the iso3166Code
     */
    public String getIso3166Code() {
        return iso3166Code;
    }

    /**
     * @param iso3166Code the iso3166Code to set
     */
    public void setIso3166Code(String iso3166Code) {
        this.iso3166Code = iso3166Code;
    }

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
     * @return the currency
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     * @param currency the currency to set
     */
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    /**
     * @return the capital
     */
    public City getCapital() {
        return capital;
    }

    /**
     * @param capital the capital to set
     */
    public void setCapital(City capital) {
        this.capital = capital;
    }

}
