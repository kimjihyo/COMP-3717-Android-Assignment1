package ca.bcit.kim.wilson.comp_3717_android_assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DetailsActivity extends AppCompatActivity {
    private static final String TAG = DetailsActivity.class.getSimpleName();
    private Article article;
    private ImageView articleImage;
    private ProgressBar imageProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_article_details);
        String articleInStr = getIntent().getStringExtra(SearchResults.ARTICLE);
        if (articleInStr == null) {
            return;
        }

        Gson gson = new Gson();
        article = gson.fromJson(articleInStr, Article.class);
        Log.i(TAG, articleInStr);

        imageProgressBar = findViewById(R.id.imageProgressBar);
        articleImage = findViewById(R.id.article_image);
        TextView articleTitle = findViewById(R.id.article_title);
        TextView articleAuthor = findViewById(R.id.article_author);
        TextView articleBody = findViewById(R.id.article_body);
        TextView articleDescription = findViewById(R.id.article_description);
        TextView articlePublishedDate = findViewById(R.id.article_publish_date);
        TextView articleSource = findViewById(R.id.article_source);

        articleTitle.setText(article.getTitle());
        articleAuthor.setText(article.getAuthor());
        articleBody.setText(article.getContent());
        articleDescription.setText(article.getDescription());
        articlePublishedDate.setText(article.getPublishedAt());
        articleSource.setText(article.getSource().getName());

        Log.i(TAG, article.getSource().getName());

        ImageDownloader downloader = new ImageDownloader();
        downloader.execute();
    }

    private class ImageDownloader extends AsyncTask<Void, Void, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            imageProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Bitmap doInBackground(Void... arg0) {
            return downloadBitmap(article.getUrlToImage());
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            if (result != null && articleImage != null) {
                Log.i(TAG, articleImage.toString());
                articleImage.setImageBitmap(result);
            } else {
                Log.i(TAG, "IMAGE IS NULL!!");
            }
            imageProgressBar.setVisibility(View.GONE);
        }

        private Bitmap downloadBitmap(String url) {
            HttpURLConnection urlConnection = null;
            try {
                URL uri = new URL(url);
                urlConnection = (HttpURLConnection) uri.openConnection();
                int statusCode = urlConnection.getResponseCode();
                if (statusCode !=  HttpURLConnection.HTTP_OK) {
                    return null;
                }

                InputStream inputStream = urlConnection.getInputStream();
                if (inputStream != null) {
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    return bitmap;
                }
            } catch (Exception e) {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                Log.w("ImageDownloader", "Error downloading image from " + url);
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
            return null;
        }
    }
}
