package com.example.hudson.newsappstage1.pojo;

import java.util.Date;

public class Article {
    String title;

    String section;

    String publicationDate;

    String link;

    public Article(String title, String section, String publicationDate, String link) {
        this.title = title;
        this.section = section;
        this.publicationDate = publicationDate;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
