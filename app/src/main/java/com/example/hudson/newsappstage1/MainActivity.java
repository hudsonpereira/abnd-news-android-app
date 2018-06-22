package com.example.hudson.newsappstage1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.hudson.newsappstage1.adapter.ArticlesAdapter;
import com.example.hudson.newsappstage1.loader.ArticleLoader;
import com.example.hudson.newsappstage1.pojo.Article;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Article>>,ArticlesAdapter.OnArticleAdapterInteraction {

    private static final String GUARDIAN_API_URL = "http://content.guardianapis.com/search?api-key=8318e902-5a3e-4d1a-9ab7-abf247d3056b";

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater()
                .inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @NonNull
    @Override
    public Loader<List<Article>> onCreateLoader(int i, @Nullable Bundle bundle) {

        Uri.Builder builder = Uri.parse(GUARDIAN_API_URL).buildUpon();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        String section = sharedPreferences.getString(getString(R.string.section_preference_key), getString(R.string.settings_sections_default_value));
        String query = sharedPreferences.getString(getString(R.string.query_preference_key), "");

        builder.appendQueryParameter("section", section);
        builder.appendQueryParameter("q", query);
        builder.appendQueryParameter("show-tags", "contributor");

        return new ArticleLoader(this, builder.toString());
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
