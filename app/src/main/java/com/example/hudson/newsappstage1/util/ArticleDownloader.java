package com.example.hudson.newsappstage1.util;

import com.example.hudson.newsappstage1.pojo.Article;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class ArticleDownloader {
    public static String downloadArticles(String stringUrl) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            URL url = new URL(stringUrl);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setReadTimeout(15 * 1000);
            httpURLConnection.connect();

            BufferedInputStream bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream));

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }

    public static List<Article> parseJSON(String json) throws JSONException {
        JSONArray results = new JSONObject(json).getJSONObject("response").getJSONArray("results");
        ArrayList<Article> articles = new ArrayList<>();

        for(int i = 0; i < results.length(); i++) {
            JSONObject result = results.getJSONObject(i);

            String title = result.getString("webTitle");
            String section = result.getString("sectionName");
            String date = result.getString("webPublicationDate");

            articles.add(new Article(title, section, date));
        }

        return articles;
    }
}
