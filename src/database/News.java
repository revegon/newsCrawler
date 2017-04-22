/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 *
 * @author Xenon
 */
public class News {
    
    private int id;
    private String title;
    private String news;
    private String date;
    private String url;
    private String website;

    public News(int id, String title, String news, String date, String url, String webSite) {
        this.id = id;
        this.title = title;
        this.news = news;
        this.date = date;
        this.url = url;
        this.website = webSite;
    }

    public News(String title, String news, String date, String url, String webSite) {
        this.title = title;
        this.news = news;
        this.date = date;
        this.url = url;
        this.id = -1;
        this.website = webSite;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    
}
