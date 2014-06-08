package pt.fv.services.api;

/**
 * Disponibiliza informação de imagens
 *
 * @author fvicente
 */
public interface ImagingService {

    /**
     * Pesquisa URLs de imagens com base tags fornecidas
     *
     * @param tags critérios a pesquisas. Pelo menos um critério é obrigatório
     * @return Lista de URLs de imagens encontradas
     */
    public String[] searchImageUrls(String[] tags);

}
