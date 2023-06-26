package namedEntity;
import namedEntity.themes.Themes;


public class Event extends NamedEntity{
    private String date;
    private Themes theme;
    private int eventFrequency;

    
    public Event(String name) {
        super(name, "Event");
        this.eventFrequency = 1;
    }

    public String getEventDate() {
        return date;
    }

    public void setEventDate(String date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Themes getThemeevent() {
        return theme;
    }

    public void setThemeevent(Themes theme) {
        this.theme = theme;
    }

    public int inqEventFrequency() {
        return this.eventFrequency++;
    }

    public int getEventFrequency() {
        return eventFrequency;
    }
}
