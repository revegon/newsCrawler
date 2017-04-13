/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newscrawler;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Xenon
 */
public class Crawler {
    
    private static final int MAX_PAGES_TO_SEARCH = 30;
    private Set<String> pagesVisited = new HashSet<String>();
    private List<String> pagesToVisit = new LinkedList<String>();
    
    public void search(List<String> pagesToVisit){
        
        
        while(!pagesToVisit.isEmpty()){
            
            String currentUrl = pagesToVisit.remove(0);
            CrawlConfig config = new CrawlConfig();
            
            System.out.println("Crawling with parent link: " + currentUrl);
            
            config.crawlForLinks(currentUrl);
            
            this.pagesToVisit.addAll(config.getLinks());
            
            
                
                while(!this.pagesToVisit.isEmpty()){
                
                try {
                    Thread.sleep(1000);
                    
                    String childLink = this.pagesToVisit.remove(0);
                    System.out.println("Crawling with child link: " + childLink);
                    
                    config.crawlForNews(childLink);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            
            }
            
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
