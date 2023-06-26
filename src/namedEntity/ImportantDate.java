package namedEntity;
import namedEntity.themes.Themes;


public class ImportantDate extends NamedEntity{
    private String real_date;
    private String date;
    private Themes theme;
    private int dateFrequency;


    public ImportantDate(String name) {
        super(name, "date");
    }

    public String getRealDate() {
        return real_date;
    }

    public void setDateId(String date_id) {
        this.real_date = date_id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public Themes getThemeImportantDate() {
        return theme;
    }

    public void setThemeImportanteDate(Themes theme) {
        this.theme = theme;
    }

    public int inqDateFrequency() {
        return this.dateFrequency++;
    }

    public int getDateFrequency() {
        return dateFrequency;
    }
}