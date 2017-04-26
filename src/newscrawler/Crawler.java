/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newscrawler;

import database.DBConnector;
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
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         
        
       
        crawl();
        
        //
//        pagesToVisit.add("http://www.chicagotribune.com/search/dispatcher.front?page=1&target=stories&date=03%2F25%2F2017-04%2F24%2F2017&spell=on&Query=bangladesh");
//        Coll c = new Coll();
//        c.search(pagesToVisit);
        

//        DBConnector db = new DBConnector();
        
        
    }

    public static void crawl() {
         List<String> pagesToVisit = new LinkedList<String>();
        
//        //LATimes
        pagesToVisit.add("http://www.latimes.com/search/dispatcher.front?target=all&spell=on&Query=bangladesh&date=03/24/2017-04/23/2017#trb_search");  //month
//       pagesToVisit.add("http://www.latimes.com/search/dispatcher.front?target=all&spell=on&Query=bangladesh&date=04/16/2017-04/23/2017#trb_search");         //week
        LATimes lat = new LATimes();
        lat.search(pagesToVisit);
        pagesToVisit.clear();
//        
//        //Times of India
        pagesToVisit.add("http://timesofindia.indiatimes.com/topic/Bangladesh/news");
        for(int i =2; i<11; i++) pagesToVisit.add("http://timesofindia.indiatimes.com/topic/Bangladesh/news/"+i);
        TimesOfIndia toi = new TimesOfIndia();
        toi.search(pagesToVisit);
        pagesToVisit.clear();
        
        //China Daily
        pagesToVisit.add("http://searchen.chinadaily.com.cn/search?sortBy=-publishtime&view=allsitesppublished&classify=en&navigation=&drillDown=&drillUp=&offset=1&query=bangladesh");
        pagesToVisit.add("http://searchen.chinadaily.com.cn/search?sortBy=-publishtime&view=allsitesppublished&classify=en&navigation=&drillDown=&drillUp=&offset=2&query=bangladesh");
        ChinaDaily cd = new ChinaDaily();
        cd.search(pagesToVisit);
        pagesToVisit.clear();
        
        

        //chicago tribunre
        pagesToVisit.add("http://www.chicagotribune.com/search/dispatcher.front?page=1&target=stories&date=03%2F25%2F2017-04%2F24%2F2017&spell=on&Query=bangladesh");
        pagesToVisit.add("http://www.chicagotribune.com/search/dispatcher.front?page=2&target=stories&date=03%2F25%2F2017-04%2F24%2F2017&spell=on&Query=bangladesh");
        ChicagoTribune ct = new ChicagoTribune();
        ct.search(pagesToVisit);
        pagesToVisit.clear();
        
//        DBConnector db = new DBConnector();
//        db.removeDuplicate();
    }
    
}
