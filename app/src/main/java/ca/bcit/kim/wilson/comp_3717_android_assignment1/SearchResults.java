package ca.bcit.kim.wilson.comp_3717_android_assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

public class SearchResults extends AppCompatActivity {
    private final static String TAG = SearchResults.class.getSimpleName();
    public final static String ARTICLE = "ca.bcit.kim.wilson.comp_3717_android_assignment1_article";
    private ListView searchResultListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        searchResultListView = findViewById(R.id.search_results);
        MyAsyncTask task = new MyAsyncTask();
        task.setKeyword(getIntent().getStringExtra(MainActivity.KEYWORD));
        task.execute();
    }

    private class MyAsyncTask extends AsyncTask<Void, Void, Void> {

        private String keyword = "";
        private NewsSearchResult newsSearchResult;

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            String url = String.format("https://newsapi.org/v2/everything?q=%s&from=2020-02-01&sortBy=publishedAt&apiKey=5673ff6e2da2418db21fa3139df172b5", this.keyword);
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.i(TAG, "Response from url: " + jsonStr);

            Gson gson = new Gson();
            newsSearchResult = gson.fromJson(jsonStr, NewsSearchResult.class);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
             ArticleAdapter articleAdapter= new ArticleAdapter(SearchResults.this, newsSearchResult.getArticles());
             searchResultListView.setAdapter(articleAdapter);
        }
    }
}
