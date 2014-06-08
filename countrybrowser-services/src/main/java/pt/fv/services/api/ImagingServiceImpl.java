package pt.fv.services.api;

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photos.SearchParameters;

/**
 * {@inheritDoc}
 * @see pt.fv.services.api.ImagingService
 *
 * @author fvicentept
 */
@Service
public class ImagingServiceImpl implements ImagingService {

    private static final int PAGE_SIZE = 10;
    private static final int FIRST_PAGE = 0;

    /**
     * Log Handler
     */
    private final Log log = LogFactory.getLog(getClass());

    /**
     * Handler para a API do Flickr
     */
    private Flickr flickrApi;

    /**
     * Token de autenticação para a API Flickr
     */
    private Auth flickrAuth;

    /**
     * @param flickrApi the flickrApi to set
     */
    @Required
    public void setFlickrApi(Flickr flickrApi) {
        this.flickrApi = flickrApi;
    }

    /**
     * @param flickrAuth the flickrAuth to set
     */
    @Required
    public void setFlickrAuth(Auth flickrAuth) {
        this.flickrAuth = flickrAuth;
    }

    /**
     * Inicialização de serviço
     */
    public void initialize() {
        RequestContext.getRequestContext().setAuth(flickrAuth);
    }

    /**
     * {@inheritDoc}
     * @see pt.fv.services.api.ImagingService#searchImageUrls(java.lang.String[])
     */
    public String[] searchImageUrls(String[] tags) {
        SearchParameters params;
        PhotoList<Photo> photos;
        String[] photoURLs = null;
        Assert.notEmpty(tags, "Empty tag list");

        params = new SearchParameters();
        params.setTags(tags);
        params.setTagMode("all"); //todas as tags são necessárias para a imagem ser considerada válida
        params.setSort(SearchParameters.INTERESTINGNESS_DESC); //as fotos mais interessantes primeiro
        params.setSafeSearch(Flickr.SAFETYLEVEL_SAFE); //apenas conteúdo seguro

        try {
            photos = flickrApi.getPhotosInterface().search(params, PAGE_SIZE, FIRST_PAGE);
            if (photos != null) {
                photoURLs = new String[photos.size()];
                for (int i = 0; i < photoURLs.length; i++) {
                    photoURLs[i] = photos.get(i).getMediumUrl();
                }
            }
        } catch (FlickrException e) {
            log.warn(e.getMessage(), e); //warn only
        }

        log.info("photoURLs=" + Arrays.asList(photoURLs));
        return photoURLs;
    }

}
