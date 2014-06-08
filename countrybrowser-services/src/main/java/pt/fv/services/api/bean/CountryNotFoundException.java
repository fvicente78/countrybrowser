package pt.fv.services.api.bean;

/**
 * Excepção a utilizar quando ocorre erro a obter informação de um país
 *
 * @author fvicente
 */
public class CountryNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public CountryNotFoundException(String iso3166Code) {
        super("Country [" + iso3166Code + "] couldn't be found.");
    }

}
