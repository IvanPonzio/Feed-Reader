package feed;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import namedEntity.heuristic.QuickHeuristic;
import namedEntity.heuristic.Heuristic;

import namedEntity.NamedEntity;
import namedEntity.*;
import namedEntity.themes.Themes;


/*Esta clase modela el contenido de un articulo (ie, un item en el caso del rss feed) */

public class Article {
	private String title;
	private String text;
	private Date publicationDate;
	private String link;
	
	private List<NamedEntity> namedEntityList = new ArrayList<NamedEntity>();
	
	

	public Article(String title, String text, Date publicationDate, String link) {
		super();
		this.title = title;
		this.text = text;
		this.publicationDate = publicationDate;
		this.link = link;
	}


	public List<NamedEntity> getNamedEntityList() {
        return namedEntityList;
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	@Override
	public String toString() {
		return "Article [title=" + title + ", text=" + text + ", publicationDate=" + publicationDate + ", link=" + link
				+ "]";
	}
	
	
	
	public NamedEntity getNamedEntity(String namedEntity){
		for (NamedEntity n: namedEntityList){
			if (n.getName().compareTo(namedEntity) == 0){				
				return n;
			}
		}
		return null;
	}
	
	public void computeNamedEntities(Heuristic h) {
		String text = this.getTitle() + " " + this.getText();
	
		String charsToRemove = ".,;:()'!?\n";
		for (char c : charsToRemove.toCharArray()) {
			text = text.replace(String.valueOf(c), "");
		}
	
		for (String s : text.split(" ")) {
			if (h.isEntity(s)) {
				String category = h.getCategory(s);
				if (category == null) {
					category = "Other";
				}
				NamedEntity ne = this.getNamedEntity(s);
				if (ne == null) {
					NamedEntity n;
					if (category.equals("Person")) {
						Person p = new Person(s);
						p.inqPersonFrequency();
						Themes theme = h.getTheme(s);
						if (theme != null) {
							p.setTheme(theme);
						}
						namedEntityList.add(p);
						n = p;
					} else if (category.equals("Organization")) {
						//NamedEntity o = new NamedEntity(s,category);
						Organization o = new Organization(s);
						o.inqOrganizationFrequency();
						Themes theme = h.getTheme(s);
						if (theme != null) {
							o.setTheme(theme);
						}
						namedEntityList.add(o);
						n = o;
					} else if (category.equals("Product")){
						Product p = new Product(s);
						p.inqProductFrequency();
						Themes theme = h.getTheme(s);
						if (theme != null) {
							p.setTheme(theme);
						}
						namedEntityList.add(p);
						n = p;
					} else if (category.equals("Event")){
						Event e = new Event(s);
						e.inqEventFrequency();
						Themes theme = h.getTheme(s);
						if (theme != null) {
							e.setTheme(theme);
						}
						namedEntityList.add(e);
						n = e;
					} else if (category.equals("Place")){
						Place p = new Place(s);
						p.inqPlaceFrequency();
						Themes theme = h.getTheme(s);
						if (theme != null) {
							p.setTheme(theme);
						}
						namedEntityList.add(p);
						n = p;
					} else if (category.equals("ImportantDate")){
						ImportantDate d = new ImportantDate(s);
						d.inqDateFrequency();
						Themes theme = h.getTheme(s);
						if (theme != null) {
							d.setTheme(theme);
						}
						namedEntityList.add(d);
						n = d;
					}
					else {
						n = new NamedEntity(s, category);
						namedEntityList.add(n);
					}
					n.incFrequency();
				} else {
					ne.incFrequency();
					if (ne instanceof Person) {
						((Person)ne).inqPersonFrequency();
					} else if (ne instanceof Organization) {
						((Organization)ne).inqOrganizationFrequency();
					} else if (ne instanceof Product) {
						((Product)ne).inqProductFrequency();
					} else if (ne instanceof Event) {
						((Event)ne).inqEventFrequency();
					} else if (ne instanceof Place) {
						((Place)ne).inqPlaceFrequency();
					} else if (ne instanceof ImportantDate) {
						((ImportantDate)ne).inqDateFrequency();
					}
				}
			}
		} 
	}
	

	
	public void prettyPrint() {
		System.out.println("\n");
		System.out.println("**********************************************************************************************");
		System.out.println("Title: " + this.getTitle());
		System.out.println("Publication Date: " + this.getPublicationDate());
		System.out.println("Link: " + this.getLink());
		System.out.println("Text: " + this.getText());
		System.out.println("\n");
	
	}
	
	// public static void main(String[] args) {
	// 	  Article a = new Article("This Historically Black University Created Its Own Tech Intern Pipeline",
	// 		  "A new program at Bowie State connects computing students directly with companies, bypassing an often harsh Silicon Valley vetting process",
	// 		  new Date(),
	// 		  "https://www.nytimes.com/2023/04/05/technology/bowie-hbcu-tech-intern-pipeline.html"
	// 		  );
		 
	// 	  a.prettyPrint();
	// }
	
	public static void main(String[] args) {
		// Crear una instancia de la heurística que desees utilizar
		Heuristic heuristic = new QuickHeuristic();
	
		// Crear un artículo de ejemplo
		String title = "Entidades Nombradas en Acción";
    String text = "Microsoft, Apple, Google, Musk, Biden, Trump, Messi, Federer, USA, Russia, Berlin, Tokyo, Olympics, WorldCup, iPhone, Tesla, " +
            "Netflix, Disney, SpaceX, NASA, Elvis, Madonna, Einstein, Picasso, London, Paris, Rome, Sydney, Pyramids, GreatWall, SuperBowl, " +
            "Oscars, Grammys, Samsung, Nike, Adidas, NewYear, Thanksgiving, Christmas, Halloween. " +
            "Microsoft es una compañía líder en tecnología. Apple es conocida por sus productos innovadores. " +
            "Google es el motor de búsqueda más utilizado en el mundo. Musk es un visionario empresario. " +
            "Biden es el actual presidente de los Estados Unidos. Trump es un controvertido ex presidente. " +
            "Messi es un futbolista argentino reconocido a nivel mundial. Federer es un tenista legendario. " +
            "USA es un país poderoso con una influencia global. Russia es una nación con una rica historia. " +
            "Berlin es una ciudad llena de historia y cultura. Tokyo es la capital de Japón. " +
            "Olympics es un evento deportivo de gran envergadura. WorldCup es el campeonato mundial de fútbol. " +
            "iPhone es un popular smartphone de Apple. Tesla es una marca de vehículos eléctricos. " +
            "Netflix ofrece entretenimiento en línea. Disney es conocida por sus películas y parques temáticos. " +
            "SpaceX es una empresa privada de exploración espacial. NASA es la agencia espacial de Estados Unidos. " +
            "Elvis fue un icónico cantante de rock and roll. Madonna es una reconocida artista pop. " +
            "Einstein fue un brillante científico. Picasso fue un influyente pintor. " +
            "London es la capital de Inglaterra. Paris es la ciudad del amor. Rome es la ciudad eterna. " +
            "Sydney es una vibrante ciudad australiana. Pyramids son antiguas estructuras egipcias. " +
            "GreatWall es una impresionante muralla en China. SuperBowl es el evento deportivo más importante de Estados Unidos. " +
            "Oscars es una ceremonia de premios cinematográficos. Grammys es un reconocimiento a la música. " +
            "Samsung es una empresa de tecnología. Nike es una marca de moda deportiva. " +
            "Adidas es una reconocida marca de ropa y calzado. NewYear es la celebración del año nuevo. " +
            "Thanksgiving es una festividad para dar gracias. Christmas es la Navidad. Halloween es una fiesta de disfraces.";

		Date publicationDate = new Date();
		String link = "https://example.com/article";
	
		Article article = new Article(title, text, publicationDate, link);
	
		// Calcular las entidades nombradas del artículo utilizando la heurística
		 article.computeNamedEntities(heuristic);
	
		// Imprimir el artículo y sus entidades nombradas
		article.prettyPrint();
		System.out.println("Named Entities:");
		for (NamedEntity namedEntity : article.getNamedEntityList()) {
			namedEntity.prettyPrint();
			
		}
	}
	
	
}



