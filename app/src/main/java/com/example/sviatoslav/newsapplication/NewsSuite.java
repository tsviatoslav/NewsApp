package com.example.sviatoslav.newsapplication;

import java.util.List;

/**
 * Created by Sviatoslav on 12/19/2016.
 */

public class NewsSuite {
    private String status;
    private String source;
    private String sortBy;
    List<Article> articles;

    protected Article getArticle(int i) {
        return articles.get(i);
    }

    public String getSource() {
        return source;
    }
}
