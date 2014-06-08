package pt.fv.services.api.bean;

/**
 * Excepção a utilizar quando não existe informação meteorológica para uma local
 *
 * @author fvicente
 */
public class EmptyWeatherDataException extends Exception {
    private static final long serialVersionUID = 1L;

    public EmptyWeatherDataException(String placeName) {
        super("No weather available for place [" + placeName + "].");
    }
}
