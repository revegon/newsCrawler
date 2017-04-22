/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newscrawler;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Xenon
 */
public class LATimes {
    
    private static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
    
    private List<String> links = new LinkedList<String>();
    private Document htmlDocument;
    
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
            
            Elements linksOnPage = htmlDocument.select("div.white a");
            
            System.out.println("Found (" + linksOnPage.size() + ") links");
            
            
            for(Element link: linksOnPage){
                
                this.links.add(link.absUrl("href"));
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
             
            
             String news = htmlDocument.select("p#content").text();
             
             String title = htmlDocument.select("h2.title").text();
             String date = htmlDocument.select("p.authorInfo").text();
             
         } catch (IOException ex) {
             ex.printStackTrace();
         }
            
           
                
            }
    
}
