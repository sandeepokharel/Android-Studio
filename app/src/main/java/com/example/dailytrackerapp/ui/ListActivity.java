package com.example.dailytrackerapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dailytrackerapp.R;

/**
 * This class handless the first activity after choosing to check different support numbers.
 * @author Silja
 */

public class ListActivity extends AppCompatActivity {
    public static final String MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        /**
         * set text for text view
         */
        TextView textView = (TextView) findViewById(R.id.supportText);
        textView.setText("Alla listattuna Suomessa toimivia tuki/hätänumeroita. Numeroa klikkaamalla pääset näkemään enemmän infoa, mitä tarkoitusta varten mikäkin numero on.\n\nSupport/emergency numbers operating in Finland are listed below. You can see more information about the number by clicking it.");
        /**
        * set the layout for the list named numberList and adapter for it. Also gets the numbers and "name" of it to the list
        */
        ListView lv = findViewById(R.id.numberList);
        lv.setAdapter(new ArrayAdapter<Number>(this, android.R.layout.simple_list_item_1, Contents.getInstance().getNumbers()));
        /**
        * set click listener for the list
        */
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * set what happens after the one item is clicked on the list.
             * opens a new activity MoreInfo
             */
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ListActivity.this, MoreInfo.class);
                intent.putExtra(MESSAGE, i);
                startActivity(intent);
            }
        });
    }
}