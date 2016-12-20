package com.example.sviatoslav.newsapplication;

import com.google.gson.Gson;

import org.json.JSONException;

/**
 * Created by Sviatoslav on 12/19/2016.
 */

public class JSONHelper {
    private String source;

    JSONHelper(String source) {
        this.source = source;
    }

    protected NewsSuite parse() throws JSONException {
        NewsSuite ns = new Gson().fromJson(source, NewsSuite.class);
        return ns;
    }
}
