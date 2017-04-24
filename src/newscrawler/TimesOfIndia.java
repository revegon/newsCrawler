/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newscrawler;

import database.DBConnector;
import database.News;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Xenon
 */
public class TimesOfIndia {
    
    private static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
    
    private List<String> links = new LinkedList<String>();
    private Document htmlDocument;
    
    private static final int MAX_PAGES_TO_SEARCH = 30;
    private Set<String> pagesVisited = new HashSet<String>();
    private List<String> pagesToVisit = new LinkedList<String>();
    
    public void search(List<String> pagesToVisit){
        
        
        while(!pagesToVisit.isEmpty()){
            
            String currentUrl = pagesToVisit.remove(0);
//            CrawlConfig config = new CrawlConfig();
            
            
            
            System.out.println("Crawling with parent link: " + currentUrl);
            
            crawlForLinks(currentUrl);
            
            this.pagesToVisit.addAll(getLinks());
            
            
                
                while(!this.pagesToVisit.isEmpty()){
                
                try {
                    Thread.sleep(1000);
                    
                    String childLink = this.pagesToVisit.remove(0);
                    System.out.println("Crawling with child link: " + childLink);
                    
                    crawlForNews(childLink);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            
            }
            
        }

    }
    
    public void crawlForLinks(String url){
        
        try{
            
            Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
            Document htmlDocument = connection.timeout(10*1000).get();
            
            this.htmlDocument = htmlDocument;
            
            if(connection.response().statusCode() == 200){
                System.out.println("Received web page: " + url);
            }
            
            if(!connection.response().contentType().contains("text/html")){
                
                System.out.println("Retireved something other than html");
                return;
            }
            
//            System.out.println(htmlDocument.html());
            
            Elements linksOnPage = htmlDocument.select("div.tab_content").select("ul").select("li.article");
            
            System.out.println("Found (" + linksOnPage.size() + ") links");
            
            
            for(Element link: linksOnPage){
                Element atag = link.select("a").first();
                this.links.add(atag.absUrl("href"));
            }
            
        }catch(IOException e){
           e.printStackTrace();
        }              
    }
    
    
    
    public List<String> getLinks(){
        
        return this.links;
    }

    void crawlForNews(String childLink)  {
        
        
            
         try {
             Connection connection = Jsoup.connect(childLink).userAgent(USER_AGENT);
             Document htmlDocument = connection.timeout(10*1000).get();
             
             if(connection.response().statusCode() == 200){
                 System.out.println("Received web page: " + childLink);
             }
             
             if(!connection.response().contentType().contains("text/html")){
                 
                 System.out.println("Retireved something other than html");
                 return;
             }
             
//             System.out.println;
            // get news
             String news = htmlDocument.select("div.Normal").text();
//             System.out.println(news);
             
             //get title
             String title = htmlDocument.select("div.main-content").select("h1").text();
//             System.out.println(title);
             
//             //get date
             
             String date = htmlDocument.select("div.main-content").select("span.time_cptn").text();
//             System.out.println(date);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
                 String cd = df.format(new Date());
             DBConnector con = new DBConnector();
             con.insert(new News(title, news, date, childLink, "Times of India", cd));
             
         } catch (IOException ex) {
             ex.printStackTrace();
         }
            
           
                
            }
    
}
