package com.example.hudson.newsappstage1.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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

    private OnArticleAdapterInteraction listener;

    public ArticlesAdapter(OnArticleAdapterInteraction listener) {
        this.listener = listener;
    }

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

        holder.bind(article, listener);
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

        void bind(final Article article, final OnArticleAdapterInteraction listener) {
            TextView articleTitle = itemView.findViewById(R.id.article_title);
            TextView articleSection = itemView.findViewById(R.id.article_section);
            TextView articleDate = itemView.findViewById(R.id.article_date);
            TextView articleAuthor = itemView.findViewById(R.id.article_author);

            articleTitle.setText(article.getTitle());
            articleSection.setText(article.getSection());
            articleDate.setText(article.getPublicationDate());
            articleAuthor.setText(article.getAuthor());

            if (TextUtils.isEmpty(article.getAuthor())) {
                articleAuthor.setVisibility(View.GONE);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onArticleClicked(article);
                }
            });
        }
    }

    public interface OnArticleAdapterInteraction {
        void onArticleClicked(Article article);
    }
}
