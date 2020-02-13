package ca.bcit.kim.wilson.comp_3717_android_assignment1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ArticleAdapter extends ArrayAdapter<Article> {
    Context context;

    public ArticleAdapter(Context context, ArrayList<Article> articles) {
        super(context, 0, articles);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Activity activity = (Activity) context;
        // Get the data item for this position
        Article article = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_article_list_adapter, parent, false);
        }
        TextView textView = convertView.findViewById(R.id.article_title);
        if (article.getTitle() != null) {
            textView.setText(article.getTitle());
        } else {
            textView.setText("NULL");
        }

        // Return the completed view to render on screen
        return convertView;
    }
}
