package pt.fv.services.api;

import pt.fv.services.api.bean.Country;
import pt.fv.services.api.bean.CountryNotFoundException;
import pt.fv.services.api.bean.EmptyWeatherDataException;
import pt.fv.services.api.bean.WeatherReport;

/**
 * Disponibiliza informação relativa a países.
 *
 * Interface principal para pesquisa e obtenção detalhada de informação associada a um país.
 *
 * @author fvicente
 */
public interface CountryService {

    /**
     * Obtém informação sobre um país.
     *
     * A informação a devolver inclui apenas dados sobre o país e respectiva capital.
     *
     * @param iso3166Code código ISO-3166 representativo do país a pesquisar
     * @return informação detalhada de país e respectiva capital.
     *
     * @throws CountryNotFoundException se não for possível encontrar o país requisitado
     */
    public Country findCountry(String iso3166Code) throws CountryNotFoundException;

    /**
     * Obtém boletim meteorológico para uma cidade.
     *
     * @param cityName nome da cidade a pesquisa o boletim meteorológico
     * @return informação meteorológica detalhada para a cidade
     *
     * @throws EmptyWeatherDataException se não for possível obter informação meteorológica para a cidade indicada
     */
    public WeatherReport getWeatherReport(String cityName) throws EmptyWeatherDataException;

}
