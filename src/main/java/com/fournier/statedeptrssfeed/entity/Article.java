package com.fournier.statedeptrssfeed.entity;

import java.util.Date;
import java.util.List;


//todo: look into lombok so I don't have to have such long model classes.
public class Article {

    private String link;
    private String title;
    private Date date;
    private String description;


    public Article(){

    }


    public Article(String link, String title, Date date, String description) {
        this.link = link;
        this.title = title;
        this.date = date;
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Article{" +
                "link='" + link + '\'' +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                '}';
    }
}