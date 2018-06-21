package com.example.hudson.newsappstage1;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.hudson.newsappstage1.adapter.ArticlesAdapter;
import com.example.hudson.newsappstage1.loader.ArticleLoader;
import com.example.hudson.newsappstage1.pojo.Article;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Article>> {

    ArticlesAdapter adapter = new ArticlesAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);

        getSupportLoaderManager().initLoader(1, null, this).forceLoad();
    }

    @NonNull
    @Override
    public Loader<List<Article>> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new ArticleLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Article>> loader, List<Article> articles) {
        adapter.setArticles(articles);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Article>> loader) {
        adapter = new ArticlesAdapter();
    }
}
