package namedEntity;
import namedEntity.themes.Themes;



public class Product extends NamedEntity {
    private String commercial;
    private String producer;
    private Themes theme;
    private int productFrequency;

    public Product(String name) {
        super(name, "Product");    }

    public String getCommercial() {
        return commercial;
    }

    public void setCommercial(String commercial) {
        this.commercial = commercial;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }
    
    public Themes getThemeProduct() {
        return theme;
    }

    public void setThemeProduct(Themes theme) {
        this.theme = theme;
    }

    public int inqProductFrequency() {
        return this.productFrequency++;
    }

    public int getProductFrequency() {
        return productFrequency;
    }
}



