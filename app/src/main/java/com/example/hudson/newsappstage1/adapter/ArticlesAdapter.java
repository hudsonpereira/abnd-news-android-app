package com.example.hudson.newsappstage1.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hudson.newsappstage1.R;
import com.example.hudson.newsappstage1.pojo.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder> {

    private List<Article> articleList = new ArrayList<>();

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.article_list, viewGroup, false);

        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int i) {
        Article article = articleList.get(i);

        holder.bind(article);
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public void setArticles(List<Article> articles) {
        articleList = articles;
        notifyDataSetChanged();
    }

    class ArticleViewHolder extends RecyclerView.ViewHolder {

        ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void bind(Article article) {
            TextView articleTitle = itemView.findViewById(R.id.article_title);
            TextView articleSection = itemView.findViewById(R.id.article_section);
            TextView articleDate = itemView.findViewById(R.id.article_date);

            articleTitle.setText(article.getTitle());
            articleSection.setText(article.getSection());
            articleDate.setText(article.getPublicationDate());
        }
    }
}
