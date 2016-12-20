package com.example.sviatoslav.newsapplication;

import java.net.URL;

/**
 * Created by Sviatoslav on 12/19/2016.
 */

public class Article {
    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public URL getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    private String author;
    private String title;
    private String description;
    private URL url;
    private String urlToImage;
    private String publishedAt;

}
