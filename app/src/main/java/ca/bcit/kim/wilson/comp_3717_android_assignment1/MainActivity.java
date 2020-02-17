package ca.bcit.kim.wilson.comp_3717_android_assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private final static String TAG = MainActivity.class.getSimpleName();
    public final static String KEYWORD = "ca.bcit.kim.wilson.comp_3717_android_assignment1_keyword";

    @Override
    public void onClick(View view){
        Button search_button = findViewById(R.id.search_button);
        EditText search_bar = findViewById(R.id.search_bar);
        String keyword = search_bar.getText().toString();
        if ((view == search_button && !keyword.isEmpty())){
            // Use keyword variable and place in get request URL
            // Place JSON code here

            Log.i(TAG, keyword);

            Intent i = new Intent(this, SearchResults.class);
            i.putExtra(KEYWORD, keyword);
            startActivity(i);
        } else {
            Toast.makeText(this, "Please enter a keyword", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.search_button);
        button.setOnClickListener(this);
    }
}
