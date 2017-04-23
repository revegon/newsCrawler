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
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         
        
        List<String> pagesToVisit = new LinkedList<String>();
        
        //LATimes
        pagesToVisit.add("http://www.latimes.com/search/dispatcher.front?target=all&spell=on&Query=bangladesh&date=04/16/2017-04/23/2017#trb_search");         
        LATimes lat = new LATimes();
        lat.search(pagesToVisit);
        pagesToVisit.clear();
        
        //Times of India
        pagesToVisit.add("http://timesofindia.indiatimes.com/topic/Bangladesh/news");
        pagesToVisit.add("http://timesofindia.indiatimes.com/topic/Bangladesh/news/2");
        TimesOfIndia toi = new TimesOfIndia();
        toi.search(pagesToVisit);
        pagesToVisit.clear();
        
        //
    }
    
}
