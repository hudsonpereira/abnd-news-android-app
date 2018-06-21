package com.example.hudson.newsappstage1.pojo;

public class Article {
    private String title;

    private String section;

    private String publicationDate;

    private String link;

    private String author;

    public Article(String title, String section, String publicationDate, String link, String author) {
        this.title = title;
        this.section = section;
        this.publicationDate = publicationDate;
        this.link = link;
        this.author = author;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
