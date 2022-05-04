package com.example.dailytrackerapp.ui;
/**
 * @author Sandeep, Silja
 */

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GestureDetectorCompat;

import com.example.dailytrackerapp.R;
import com.example.dailytrackerapp.data.Mood;
import com.example.dailytrackerapp.data.SharedPreferencesHelper;
import com.example.dailytrackerapp.util.Constants;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;
    private final Mood mood = new Mood();
    public int currentDay;
    private ImageView moodImageView;
    private TextView moodText;
    private ConstraintLayout parentConstraintLayout;
    private GestureDetectorCompat mDetector;
    private SharedPreferences mPreferences;
    private int currentMoodIndex;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parentConstraintLayout = findViewById(R.id.parent_constraint_layout);

        moodImageView = findViewById(R.id.my_mood_image);
        moodText = findViewById(R.id.my_mood_text);

        ImageButton addCommentButton = findViewById(R.id.btn_add_comment);
        ImageButton moodHistoryButton = findViewById(R.id.btn_mood_history);
        ImageButton shareAppButton = findViewById(R.id.btn_share);
        ImageButton leftButton = findViewById(R.id.btn_left);
        ImageButton rightButton = findViewById(R.id.btn_right);

        /**
         * finds a button for support numbers by it's id and sets clickListener for it
         * after clicking starts new activity named ListActivity
         */
        Button supportBtn = (Button) findViewById(R.id.supportBtn);
        supportBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ListActivity.class));
            }
        });

        mDetector = new GestureDetectorCompat(this, this);
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        currentDay = Calendar.getInstance().getTime().getDay();
        currentMoodIndex = mPreferences.getInt(SharedPreferencesHelper.KEY_CURRENT_MOOD, 3);

        changeUiForMood(currentMoodIndex);
        mood.setMoodStatus(currentMoodIndex);
        mood.setMoodDate(currentDay);
        SharedPreferencesHelper.saveMood(mPreferences, mood);


        // Add comment to the Mood
        addCommentButton.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            final EditText editText = new EditText(MainActivity.this);
            editText.setId(R.id.commentEditText);
            builder.setMessage("Add Comment").setView(editText)
                    .setPositiveButton("CONFIRM", (dialog, which) -> {
                        if (!editText.getText().toString().isEmpty()) {
                            mood.setComment(editText.getText().toString());
                            SharedPreferencesHelper.saveMood(mPreferences, mood);
                        }


                        dialog.dismiss();

                        Toast.makeText(MainActivity.this, "Comment Saved", Toast.LENGTH_SHORT).show();

                    });
            builder.setNegativeButton(android.R.string.cancel, (dialog, which) -> {
                dialog.cancel();
                Toast.makeText(MainActivity.this, "Comment Canceled", Toast.LENGTH_SHORT).show();
            }).create().show();
        });

        // History Button to view Mood history screen
        moodHistoryButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MoodHistoryActivity.class);
            startActivity(intent);
        });

        // Share your mood Button with image and comment
        shareAppButton.setOnClickListener(v -> {
            Bitmap image = BitmapFactory.decodeResource(getResources(), Constants.moodImagesArray[currentMoodIndex]);
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Hello! I would like to share with you my mood of the day from DailyMood Tracker App.Today my Mood is " + Constants.moodTextArray[currentMoodIndex] + ".");
            sendIntent.putExtra(Intent.EXTRA_STREAM, getImageUri(this, image));
            sendIntent.setType("*/*");
            startActivity(Intent.createChooser(sendIntent, "Hello! I would like to share with you my mood of the day from DailyMood Tracker App.Today my Mood is " + Constants.moodTextArray[currentMoodIndex] + "."));
        });

        // Change mood to negative
        leftButton.setOnClickListener(v -> {
            changeMoodNegative();
        });

        // Change mood to positive
        rightButton.setOnClickListener(v -> {
            changeMoodPositive();
        });
    }

    @Override
    public boolean onDown(MotionEvent e) {
        // Not needed for this project
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        // Not needed for this project
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        // Not needed for this project
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        // Not needed for this project
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        // Not needed for this project
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        boolean result = false;
        try {
            float diffY = e2.getY() - e1.getY();
            float diffX = e2.getX() - e1.getX();
            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        changeMoodNegative();
                    } else {
                        changeMoodPositive();
                    }
                    result = true;
                }
            } else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffY > 0) {
                    changeMoodNegative();
                } else {
                    changeMoodPositive();
                }
                result = true;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mDetector.onTouchEvent(event);
    }

    //************************* change mood methods***************************************/
    private void changeMoodPositive() {
        if (currentMoodIndex < 4) {
            currentMoodIndex++;
            changeUiForMood(currentMoodIndex);
            mood.setMoodStatus(currentMoodIndex);
            mood.setMoodDate(currentDay);
            SharedPreferencesHelper.saveMood(mPreferences, mood);
        }
    }

    private void changeMoodNegative() {
        if (currentMoodIndex > 0) {
            currentMoodIndex--;
            changeUiForMood(currentMoodIndex);
            mood.setMoodStatus(currentMoodIndex);
            mood.setMoodDate(currentDay);
            SharedPreferencesHelper.saveMood(mPreferences, mood);
        }
    }

    //************************* change mood methods***************************************/
    private void changeUiForMood(int currentMoodIndex) {
        moodImageView.setImageResource(Constants.moodImagesArray[currentMoodIndex]);
        moodText.setText(Constants.moodTextArray[currentMoodIndex]);
        parentConstraintLayout.setBackgroundResource(Constants.moodColorsArray[currentMoodIndex]);
        MediaPlayer mediaPlayer = MediaPlayer.create(this, Constants.moodSoundsArray[currentMoodIndex]);
        mediaPlayer.start();
    }

    //************************* get uri from bitmap method***************************************/
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Mood", "My Mood for today");
        return Uri.parse(path);
    }
}