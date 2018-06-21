package com.example.hudson.newsappstage1.loader;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import com.example.hudson.newsappstage1.pojo.Article;
import com.example.hudson.newsappstage1.util.ArticleDownloader;

import org.json.JSONException;

import java.util.List;

public class ArticleLoader extends AsyncTaskLoader<List<Article>> {

    private static final String GUARDIAN_API_URL = "http://content.guardianapis.com/search?q=debates&api-key=8318e902-5a3e-4d1a-9ab7-abf247d3056b";

    public ArticleLoader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public List<Article> loadInBackground() {
        try {
            return ArticleDownloader.parseJSON(ArticleDownloader.downloadArticles(GUARDIAN_API_URL));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
