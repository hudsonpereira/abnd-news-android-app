package com.example.hudson.newsappstage1.pojo;

import java.util.Date;

public class Article {
    String title;

    String section;

    Date publicationDate;

    public Article(String title, String section, Date publicationDate) {
        this.title = title;
        this.section = section;
        this.publicationDate = publicationDate;
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

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }
}
