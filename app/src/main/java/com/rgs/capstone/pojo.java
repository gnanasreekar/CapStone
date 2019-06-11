package com.rgs.capstone;

public class pojo {
    public String author;
    public String content;
    public String image;
    public String title;
    public String url;
    public String date;
    public String description;

    public pojo(String author, String content, String image, String title, String url, String date, String description) {
        this.author = author;
        this.content = content;
        this.image = image;
        this.title = title;
        this.url = url;
        this.date = date;
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }
}


