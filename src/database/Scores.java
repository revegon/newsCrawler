/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Date;

/**
 *
 * @author Dhruba
 */
public class Scores {
     private int id;
    private double score;
    private Date date;
    private int newsid;

     public Scores(int id, double score, int newsid, Date date) {
        this.id = id;
        this.score = score;
        this.newsid = newsid;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNewsid() {
        return newsid;
    }

    public void setNewsid(int newsid) {
        this.newsid = newsid;
    }

    public Scores(int id, double score, Date date, int newsid) {
        this.id = id;
        this.score = score;
        this.date = date;
        this.newsid = newsid;
    }

    public Scores(double score, Date date, int newsid) {
        this.score = score;
        this.date = date;
        this.newsid = newsid;
    }
}
