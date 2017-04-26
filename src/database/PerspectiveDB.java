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
import newscrawler.HttpURLConnectionExample;

/**
 *
 * @author Dhruba
 */
public class PerspectiveDB {
      private Connection conn;
    private Statement stm;

    public PerspectiveDB() {
         try {
             dbConnection();
             DatabaseMetaData meta = this.conn.getMetaData();
             ResultSet res = meta.getTables(null, null, "perspective",
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
        String query = "create table perspective(id int primary key AUTO_INCREMENT, score double, newsid int references allnews(id) , date datetime);";
        stm.execute(query);
    }
    
    public void insert() {
    
//        double score = n.getScore();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = df.format(new Date());
            double perspective = HttpURLConnectionExample.TOIs;
            int id = HttpURLConnectionExample.id;
//            int news
        try{
            String query = "insert into perspective (score, newsid, date) values("+perspective+","+id+",\'"+date+"\');";
           System.out.println(query);
    
            stm.execute(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
   
    public ArrayList<Scores> getData()
    {
        ArrayList<Scores> list = new ArrayList<>();
        
         try {
             String query = "select * from perspective;";
             ResultSet rs = stm.executeQuery(query);
             while(rs.next())
             {
                 int id = rs.getInt("id");
                 double score = rs.getDouble("score");
                 int newsid = rs.getInt("newsid");
                 java.sql.Date date = rs.getDate("date");
                 
                  
                 Scores n = new Scores(id, score, newsid, date);
                 list.add(n);
             }
            
         } catch (SQLException ex) {
             System.out.println("Error in retriving data");
         }
         finally{
            
           
         }
       
            return  list;
    }
    
   
}
