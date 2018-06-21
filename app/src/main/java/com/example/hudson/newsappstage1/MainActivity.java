package com.example.hudson.newsappstage1;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.hudson.newsappstage1.adapter.ArticlesAdapter;
import com.example.hudson.newsappstage1.loader.ArticleLoader;
import com.example.hudson.newsappstage1.pojo.Article;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Article>>,ArticlesAdapter.OnArticleAdapterInteraction {

    ArticlesAdapter adapter = new ArticlesAdapter(this);

    TextView noDataAvailableTextView;

    TextView noInternetConnectionTextView;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noDataAvailableTextView = findViewById(R.id.no_data);
        noInternetConnectionTextView = findViewById(R.id.no_internet);
        progressBar = findViewById(R.id.progress_bar);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            getSupportLoaderManager().initLoader(1, null, this).forceLoad();
        } else {
            progressBar.setVisibility(View.GONE);
            noInternetConnectionTextView.setVisibility(View.VISIBLE);
        }
    }

    @NonNull
    @Override
    public Loader<List<Article>> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new ArticleLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Article>> loader, List<Article> articles) {
        if (articles != null && articles.size() > 0) {
            adapter.setArticles(articles);
        } else {
            noDataAvailableTextView.setVisibility(View.VISIBLE);
        }

        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Article>> loader) {
        adapter = new ArticlesAdapter(this);
    }

    @Override
    public void onArticleClicked(Article article) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(article.getLink()));
        startActivity(intent);
    }
}
