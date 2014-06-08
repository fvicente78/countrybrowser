package pt.fv.services.api.bean;

/**
 * Entidade abstracta de local
 *
 * @author fvicente
 */
public abstract class Place {
    /**
     * Nome da localidade
     */
    private String name;

    /**
     * Coordenadas geográficas
     */
    private String coordinates;

    /**
     * Contagem de população
     */
    private int population;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the coordinates
     */
    public String getCoordinates() {
        return coordinates;
    }

    /**
     * @param coordinates the coordinates to set
     */
    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * @return the population
     */
    public int getPopulation() {
        return population;
    }

    /**
     * @param population the population to set
     */
    public void setPopulation(int population) {
        this.population = population;
    }

}
