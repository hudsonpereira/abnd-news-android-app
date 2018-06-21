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
    private final String url;

    public ArticleLoader(@NonNull Context context, String url) {
        super(context);
        this.url = url;
    }

    @Nullable
    @Override
    public List<Article> loadInBackground() {
        try {
            return ArticleDownloader.parseJSON(ArticleDownloader.downloadArticles(url));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
