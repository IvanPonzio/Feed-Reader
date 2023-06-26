package parser;
import org.xml.sax.InputSource;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.IOException;
import org.xml.sax.SAXException;
import java.util.ArrayList;
import java.util.List;
import httpRequest.httpRequester;
import java.io.StringReader;




/*
 * Esta clase implementa el parser de feed de tipo reddit (json)
 * pero no es necesario su implemntacion 
 * */
public class RedditParser extends GeneralParser {
    // método heredado de GeneralParser
    public RedditParser(List<String> urls) {
        super(urls);
        this.urls = new ArrayList<String>(); // Agregar esta línea para inicializar la lista de URLs
    }

    public void parse() {
        String feedUrl = "https://www.reddit.com/r/science/.rss";
        httpRequester requester = new httpRequester();
        String result = requester.getFeedReedit(feedUrl);
    
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    
        Document doc = null;
        try {
            InputSource inputSource = new InputSource(new StringReader(result));
            doc = dBuilder.parse(inputSource);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
            return;
        }
    
        doc.getDocumentElement().normalize();
    
        NodeList titleList = doc.getElementsByTagName("title");
        NodeList linkList = doc.getElementsByTagName("link");
        NodeList descriptionList = doc.getElementsByTagName("description");
        NodeList pubDateList = doc.getElementsByTagName("published");
    
        // crear StringBuilder
        StringBuilder sb = new StringBuilder();
    
        // Resto del código
        for (int temp = 0; temp < titleList.getLength(); temp++) {
            Node nNode = titleList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                sb.append("Title : " + eElement.getTextContent() + "\n");
            }
        }
        for (int temp = 0; temp < linkList.getLength(); temp++) {
            Node nNode = linkList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                sb.append("Link : " + eElement.getTextContent() + "\n");
            }
        }
        for (int temp = 0; temp < descriptionList.getLength(); temp++) {
            Node nNode = descriptionList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                sb.append("Description : " + eElement.getTextContent() + "\n");
            }
        }
        for (int temp = 0; temp < pubDateList.getLength(); temp++) {
            Node nNode = pubDateList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                sb.append("Published : " + eElement.getTextContent() + "\n");
            }
        }
    



        // List<Article> articles = new ArrayList<Article>();
        // for (int i = 0; i < titleList.getLength(); i++) {
        //     String title = null, link = null, description = null, published = null;
        //     if (titleList.item(i) != null) {
        //         title = titleList.item(i).getTextContent();
        //     }
        //     if (linkList.item(i) != null) {
        //         link = linkList.item(i).getTextContent();
        //     }
        //     if (descriptionList.item(i) != null) {
        //         description = descriptionList.item(i).getTextContent();
        //     }
        //     if (pubDateList.item(i) != null) {
        //         published = pubDateList.item(i).getTextContent();
        //     }

        //     if (title != null && link != null && published != null) {
        //         Article article = new Article(title, link, new Date(published), description);
        //         articles.add(article);
        //     }

        // }


        System.out.println(sb.toString());
    }
    


    public static void main(String[] args) {
        List<String> urls = new ArrayList<String>();
        urls.add("https://www.reddit.com/r/science/.rss");
        RedditParser parser = new RedditParser(urls);
        parser.parse();
    }
}