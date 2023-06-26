package namedEntity;
import namedEntity.themes.Themes;


public class Place extends NamedEntity{
    private String ciudad;
    private String pais;
    private String direccion;
    private String language;
    private int population;
    private Themes theme;
    private int placeFrequency;

    public Place (String name) {
        super(name, "place");
        this.placeFrequency = 1;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
    public String getLanguage() {
        return language;
    }
    public void setPopulation(int population) {
        this.population = population;
    }

    public int getPopulation() {
        return population;
    }

    public void setThemePlace(Themes theme) {
        this.theme = theme;
    }

    public Themes getThemePlace() {
        return theme;
    }
    
    public int inqPlaceFrequency() {
        return this.placeFrequency++;
    }

    public int getPlaceFrequency() {
        return placeFrequency;
    }

    
}
