package pt.fv.test.services;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.util.Assert;

import pt.fv.services.api.ImagingService;
import pt.fv.services.api.bean.CountryNotFoundException;

/**
 * Testes unitários sobre o serviço {@link pt.fv.services.api.ImagingService}
 *
 * @author fvicente
 */
@ContextConfiguration({"countrybrowser-test-context.xml"})
public class ImageServiceDT extends AbstractJUnit4SpringContextTests {

    @Autowired
    private ImagingService imagingService;

    /**
     * Testa a correcta obtenção de URLs com tags Portugal e Lisbon
     */
    @Test
    public void testSearchImageUrlsPortugal() throws CountryNotFoundException {
        String[] tags = new String[] {"Portugal", "Lisbon"};
        String[] imageURLs;

        imageURLs = imagingService.searchImageUrls(tags);
        Assert.notEmpty(imageURLs);

        for (int i = 0; i < imageURLs.length; i++) {
            Assert.notNull(imageURLs[i]);
        }
    }

    /**
     * Testa erro em caso de utilização com tags vazias
     */
    @Test
    public void testSearchImageUrlsEmptyTags() throws CountryNotFoundException {
        String[] tags = new String[0];
        Exception errorOccured = null;

        try {
            imagingService.searchImageUrls(tags);
        } catch (IllegalArgumentException e) {
            errorOccured = e;
        }

        Assert.notNull(errorOccured, "IllegalArgumentException not sent when expected");
        Assert.isAssignable(IllegalArgumentException.class, errorOccured.getClass(), "Exception [" + errorOccured.getClass().getName()
            + "] occured when expecting [" + IllegalArgumentException.class.getName() + "]");
    }
}
