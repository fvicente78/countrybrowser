package pt.fv.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pt.fv.services.api.CountryService;
import pt.fv.services.api.bean.Country;
import pt.fv.services.api.bean.CountryNotFoundException;
import pt.fv.webservice.bean.CountryInfo;

/**
 * Implementação do handler que responde ao webservice REST
 *
 * @author fvicentept
 */
@Controller
public class CountryController {

    /**
     * Serviço da camada de negócio que fornece informações de país
     */
    @Autowired
    private CountryService countryService;

    /**
     * Serviço de conversão de Beans
     * Autowired por ser parte de infraestrutura de suporte e não negócio core
     */
    @Autowired
    private ConversionService conversionService;

    @RequestMapping("/countryInfo")
    public @ResponseBody
    CountryInfo getCountryInfo(@RequestParam(value = "code", required = true) String countryCode) throws CountryNotFoundException {
        Country country;
        CountryInfo countryInfo;

        //pesquisa país
        country = countryService.findCountry(countryCode);
        countryInfo = conversionService.convert(country, CountryInfo.class);

        return countryInfo;
    }

}
