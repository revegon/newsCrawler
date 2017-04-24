/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Xenon
 */
public class DBConnector {
    
     private Connection conn;
    private Statement stm;

    public DBConnector() {
         try {
             dbConnection();
             DatabaseMetaData meta = this.conn.getMetaData();
             ResultSet res = meta.getTables(null, null, "allnews",
                     new String[] {"TABLE"});
             if(!res.next()) {
                 createTable();
                 System.out.println("table has been created");
             }
         } catch (SQLException ex) {
             Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    
    public final void dbConnection()
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
    
    public void createTable() throws SQLException
    {
        String query = "create table allnews(id int primary key AUTO_INCREMENT, title text, news text, date text, url text, website text, crawlDate datetime);";
        stm.execute(query);
    }
    
    public void insert(News n) 
    {       String news = n.getNews().replace("\'", "\\\'");
            news = news.replace("\"", "\\\"");
            
            String title = n.getTitle().replace("\'", "\\\'");
            title = title.replace("\"", "\\\"");
        try {
            String query = "insert into allnews (title, news, date, url, website, crawlDate) values(\'"+title+"\',\'"+news+"\',\'"+n.getDate()+"\',\'"+n.getUrl()+"\',\'"+n.getWebsite()+"\',\'"+n.getCrawlDate()+"\');";
//           System.out.println(query);
    
            stm.execute(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
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
                 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
                 String cd = df.format(new Date());
                 News n = new News(id, title, news, date, url, website, cd);
                 list.add(n);
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
                 String cd = rs.getString("crawlDate");
                  n = new News(id, title, news, date, url, website, cd);
         } catch (SQLException ex) {
             Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
         }
         finally{
             return n;
         }
    }
}
