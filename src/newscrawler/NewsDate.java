/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newscrawler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Xenon
 */
public class NewsDate {
    
    private String from;
    private String to;

    public NewsDate() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("DD/MM/YYYY");
        System.out.println("");
    }

     

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
    
    
    
}
