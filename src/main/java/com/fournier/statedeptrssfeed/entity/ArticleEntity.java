package com.fournier.statedeptrssfeed.entity;

public class ArticleEntity {

    private String link;
    private String title;
    private String author;


    public ArticleEntity(String link, String title, String author) {
        this.link = link;
        this.title = title;
        this.author = author;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    @Override
    public String toString() {
        return "ArticleEntity{" +
                "link='" + link + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
