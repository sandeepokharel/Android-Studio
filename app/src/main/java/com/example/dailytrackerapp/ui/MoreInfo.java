package com.example.dailytrackerapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
/**
 * This MoreInfo class handless the second activity after choosing something from the ListView.
 * @author Silja
 */

import com.example.dailytrackerapp.R;

public class MoreInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);
        Bundle b = getIntent().getExtras();
        int i = b.getInt(ListActivity.MESSAGE, 0);
        /**
         * set description text for text view named desText
         */
        TextView tvDescription = findViewById(R.id.desText);
        tvDescription.setText(Contents.getInstance().getNumber(i).getDescription());
    }
}
