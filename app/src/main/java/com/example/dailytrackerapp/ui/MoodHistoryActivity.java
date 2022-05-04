package com.example.dailytrackerapp.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dailytrackerapp.R;
import com.example.dailytrackerapp.adapter.MoodsAdapter;
import com.example.dailytrackerapp.data.Mood;
import com.example.dailytrackerapp.data.SharedPreferencesHelper;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MoodHistoryActivity extends AppCompatActivity {

    private final ArrayList<Mood> mMood = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_history);

        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        RecyclerView moodsRecyclerView = findViewById(R.id.reycler_moods);
        moodsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
        Gson gson = new Gson();
        for (int i = 0; i <= 7; i++) {
            if (mPreferences.contains(SharedPreferencesHelper.KEY_MOOD + i)) {
                Mood mood = gson.fromJson(mPreferences.getString(SharedPreferencesHelper.KEY_MOOD + i, ""), Mood.class);
                mMood.add(mood);
            }
        }
        MoodsAdapter moodsAdapter = new MoodsAdapter(this, mMood);
        moodsRecyclerView.setAdapter(moodsAdapter);
    }
}
