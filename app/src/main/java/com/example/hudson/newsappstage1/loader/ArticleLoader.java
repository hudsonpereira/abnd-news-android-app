package com.example.hudson.newsappstage1.loader;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import com.example.hudson.newsappstage1.pojo.Article;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArticleLoader extends AsyncTaskLoader<List<Article>> {


    public ArticleLoader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public List<Article> loadInBackground() {
        ArrayList<Article> articles = new ArrayList<>();

        articles.add(new Article("Project Fantasy? German exam question debates Brexit reality", "World news", new Date()));
        articles.add(new Article("The Guardian view on the Brexit bill debates: crash bang wallop | Editorial", "Opinion", new Date()));
        articles.add(new Article("Universities are part of the solution to dysfunctional Brexit debates", "Science", new Date()));
        articles.add(new Article("Putting the antisemitism debate in perspective | Letters", "News", new Date()));

        return articles;
    }
}
