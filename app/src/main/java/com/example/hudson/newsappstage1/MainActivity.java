package com.example.hudson.newsappstage1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.hudson.newsappstage1.adapter.ArticlesAdapter;
import com.example.hudson.newsappstage1.pojo.Article;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ArticlesAdapter adapter = new ArticlesAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);

        ArrayList<Article> articles = new ArrayList<>();

        articles.add(new Article("Project Fantasy? German exam question debates Brexit reality", "World news", new Date()));
        articles.add(new Article("The Guardian view on the Brexit bill debates: crash bang wallop | Editorial", "Opinion", new Date()));
        articles.add(new Article("Universities are part of the solution to dysfunctional Brexit debates", "Science", new Date()));
        articles.add(new Article("Putting the antisemitism debate in perspective | Letters", "News", new Date()));

        adapter.setArticles(articles);
    }
}
