/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Xenon
 */
public class DBConnector {
    
     private Connection conn;
    private Statement stm;
    
    
    public void dbConnection()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/persons",  "root" , "");
            stm = conn.createStatement();
            System.out.println("Success!");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public void insert(News n) 
    {
        try {
            String query = "insert into allnews(title, news, date, url, website) values(\'"+n.getTitle()+"\',\'"+n.getNews()+"\',\'"+n.getDate()+"\',\'"+n.getUrl()+"\',\'"+n.getWebsite()+"\');";
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println("Error in inserting");
        }
    }
    
    public void insert(ArrayList<News> list) 
    {
       for(News n : list) insert(n);
    }
    
    public ArrayList<News> getData()
    {
        ArrayList<News> list = new ArrayList<>();
        
         try {
             String query = "select * from allnews;";
             ResultSet rs = stm.executeQuery(query);
             while(rs.next())
             {
                 int id = rs.getInt("id");
                 String title = rs.getString("title");
                 String news = rs.getString("news");
                 String date = rs.getString("date");
                 String url = rs.getString("url");
                 String website = rs.getString("website");
                 
                 News n = new News(id, title, news, date, url, website);
                 
             }
         } catch (SQLException ex) {
             System.out.println("Error in retriving data");
         }
         finally{
             return  list;
         }
         
    }
    
    public News getNews(int id)
    {
        News n = null;
         try {
             String query = "select * from allnews where id = "+id+";";
             ResultSet rs = stm.executeQuery(query);
             rs.first();
                 String title = rs.getString("title");
                 String news = rs.getString("news");
                 String date = rs.getString("date");
                 String url = rs.getString("url");
                 String website = rs.getString("website");
                 
                  n = new News(id, title, news, date, url, website);
         } catch (SQLException ex) {
             Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
         }
         finally{
             return n;
         }
    }
}
