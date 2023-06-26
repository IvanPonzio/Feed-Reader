import java.util.List;
import java.util.ArrayList;
import feed.Feed;
import httpRequest.httpRequester;
import parser.*;
import subscription.*;
import feed.*;
import namedEntity.heuristic.QuickHeuristic;
import namedEntity.heuristic.Heuristic;
import java.util.Date;
import namedEntity.NamedEntity;

public class FeedReaderMain {

	private static void printHelp(){
		System.out.println("Please, call this program in correct way: FeedReader [-ne]");
	}
	
	public static void main(String[] args) {
		System.out.println("************* FeedReader version 1.0 *************");
		if (args.length == 0) {


			// // Para leer el archvio subscrition por defecto
			List<String> listUrlsRss = new ArrayList<>();
			SubscriptionParser subscriptionParser = new SubscriptionParser(null);
			subscriptionParser.parse();
			Subscription subscription = subscriptionParser.getSubscriptions();

			// Recorrer la lista de suscripciones para armar los feeds
			for (int i = 0; i < subscription.getSubscriptionsList().size(); i++) {
				// Chequeamos el tipo de url type de la suscripcion
				if(subscription.getSingleSubscription(i).getUrlType().equals("rss")) {
					// Recorremos los tipo de parametros de la url
					for(int j = 0; j < subscription.getSingleSubscription(i).getUlrParamsSize(); j++){
						// Agregamos estos url a la lista de urls
						listUrlsRss.add(subscription.getSingleSubscription(i).getFeedToRequest(j));
					}
				}
			}

			// Obtenida la lista de urls, recorremos la lista para obtener los feeds
			for (int i = 0; i < listUrlsRss.size(); i++) {
				System.out.println("\n" + listUrlsRss.get(i) + "\n");
				// Llamar al httpRequester para obtener el feed del servidor
				httpRequester httpRequester = new httpRequester();
				String feedXml = httpRequester.getFeedRss(listUrlsRss.get(i));
				// Llamar al Parser especifico para extrar los datos necesarios por la aplicacion
				RssParser rssParser = new RssParser(listUrlsRss);
				rssParser.setFeedContent(feedXml);
				rssParser.parse();
				// Llamar al constructor de Feed
				Feed feed = new Feed("NYT\n");
				feed.setArticleList(rssParser.getArticleList());
				// LLamar al prettyPrint del Feed para ver los articulos del feed en forma legible y amigable para el usuario
				feed.prettyPrint();
			}

		} else if (args.length == 1 && args[0].equals("-ne")){
			/*
			Leer el archivo de suscription por defecto;
			Llamar al httpRequester para obtenr el feed del servidor
			Llamar al Parser especifico para extrar los datos necesarios por la aplicacion 
			Llamar al constructor de Feed
			Llamar a la heuristica para que compute las entidades nombradas de cada articulos del feed
			LLamar al prettyPrint de la tabla de entidades nombradas del feed.
			 */
			List<String> listUrlsRss = new ArrayList<>();
			SubscriptionParser subscriptionParser = new SubscriptionParser(null);
			subscriptionParser.parse();
			Subscription subscription = subscriptionParser.getSubscriptions();

			for (int i = 0; i < subscription.getSubscriptionsList().size(); i++) {
				if (subscription.getSingleSubscription(i).getUrlType().equals("rss")) {
					for (int j = 0; j < subscription.getSingleSubscription(i).getUlrParamsSize(); j++) {
						listUrlsRss.add(subscription.getSingleSubscription(i).getFeedToRequest(j));
					}
				}
			}

		for (int i = 0; i < listUrlsRss.size(); i++) {
			System.out.println("\n" + listUrlsRss.get(i) + "\n");
			httpRequester httpRequester = new httpRequester();
			String feedXml = httpRequester.getFeedRss(listUrlsRss.get(i));
			RssParser rssParser = new RssParser(listUrlsRss);
			rssParser.setFeedContent(feedXml);
			rssParser.parse();
			List<String> titles = rssParser.getArticleTitles();
			List<String> descriptions = rssParser.getArticleDescriptions();
			List<String> links = rssParser.getArticleLinks();

			for (int j = 0; j < titles.size(); j++) {
				String title = titles.get(j);
				String description = descriptions.get(j);
				String link = links.get(j);
				Date publicationDate = new Date();
				Article article = new Article(title, description, publicationDate, link);
				Heuristic heuristic = new QuickHeuristic();
				article.computeNamedEntities(heuristic);
				article.prettyPrint();
				System.out.println("Named Entities:");
				for (NamedEntity namedEntity : article.getNamedEntityList()) {
					namedEntity.prettyPrint();
				}
			}
		}

			
		} else {
			printHelp();
		}
	}

}
