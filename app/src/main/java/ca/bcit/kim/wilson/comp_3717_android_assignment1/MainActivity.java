package ca.bcit.kim.wilson.comp_3717_android_assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private final static String TAG = MainActivity.class.getSimpleName();

    @Override
    public void onClick(View view){
        Button search_button = findViewById(R.id.search_button);
        EditText search_bar = findViewById(R.id.search_bar);
        if ((view == search_button && !search_bar.getText().toString().isEmpty())){
            TextView test = findViewById(R.id.header_text);
            Intent i = new Intent(this, SearchResults.class);
            startActivity(i);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.search_button);
        button.setOnClickListener(this);

        MyAsyncTask task = new MyAsyncTask();
        task.execute();
    }

    private class MyAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            String jsonStr = null;

            // Making a request to url and getting response
            jsonStr = sh.makeServiceCall("https://newsapi.org/v2/everything?q=bitcoin&from=2020-02-01&sortBy=publishedAt&apiKey=5673ff6e2da2418db21fa3139df172b5");

            Log.i(TAG, "Response from url: " + jsonStr);

            Gson gson = new Gson();
            NewsSearchResult newsSearchResult = gson.fromJson(jsonStr, NewsSearchResult.class);

            Log.i(TAG, newsSearchResult.getStatus());
            Log.i(TAG, String.valueOf(newsSearchResult.getTotalResult()));
            for (Article a : newsSearchResult.getArticles()) {
                if (a != null && a.getAuthor() != null) {
                    Log.i(TAG, a.getAuthor());
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
    }
}
