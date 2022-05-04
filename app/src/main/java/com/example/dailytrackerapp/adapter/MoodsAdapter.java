package com.example.dailytrackerapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dailytrackerapp.R;
import com.example.dailytrackerapp.data.Mood;
import com.example.dailytrackerapp.util.Constants;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class MoodsAdapter extends RecyclerView.Adapter<MoodsAdapter.MoodViewHolder> {

    private static final String TAG = "MoodsAdapter";
    private final Context mContext;
    private final ArrayList<Mood> mMoods;

    //*** Constructor*
    public MoodsAdapter(Context context, ArrayList<Mood> moods) {
        this.mContext = context;
        this.mMoods = moods;
    }

    @NonNull
    @Override
    public MoodViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_mood, viewGroup, false);
        return new MoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoodViewHolder moodViewHolder, int i) {
        Mood mood = mMoods.get(i);
        Date currentDate = Calendar.getInstance().getTime();
        if (currentDate.getDay() == mood.getMoodDate()) {
            moodViewHolder.daysTextView.setText(R.string.today);
        } else if (currentDate.getDay() - 1 == mood.getMoodDate()) {
            moodViewHolder.daysTextView.setText(R.string.yesterday);
        } else {
            int day = currentDate.getDay() - mood.getMoodDate();
            String daysAgoText = day + " " + mContext.getString(R.string.days_ago);
            moodViewHolder.daysTextView.setText(daysAgoText);
        }


        LinearLayout.LayoutParams leftLayoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        LinearLayout.LayoutParams rightLayoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        float weight;
        switch (mood.getMoodStatus()) {
            case 0:
                weight = 0.2f;
                break;
            case 1:
                weight = 0.4f;
                break;
            case 2:
                weight = 0.6f;
                break;
            case 4:
                weight = 1.0f;
                break;
            default:
                weight = 0.8f;
        }
        leftLayoutParams.weight = weight;
        rightLayoutParams.weight = 1.0f - weight;
        moodViewHolder.leftFrameLayout.setLayoutParams(leftLayoutParams);
        moodViewHolder.rightFrameLayout.setLayoutParams(rightLayoutParams);
        moodViewHolder.leftFrameLayout.setBackgroundResource(Constants.moodColorsArray[mood.getMoodStatus()]);


        //** if there's a comment, show the icon and a toast on click*
        final String comment = mood.getComment();
        if (comment != null && !comment.isEmpty()) {
            moodViewHolder.commentButton.setVisibility(View.VISIBLE);
            moodViewHolder.commentButton.setOnClickListener(v ->
                    Toast.makeText(mContext, comment, Toast.LENGTH_LONG).show());
        } else {
            moodViewHolder.commentButton.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mMoods.size();
    }

    public static class MoodViewHolder extends RecyclerView.ViewHolder {
        private final FrameLayout leftFrameLayout;
        private final FrameLayout rightFrameLayout;
        private final ImageButton commentButton;
        private final TextView daysTextView;

        public MoodViewHolder(View itemView) {
            super(itemView);

            leftFrameLayout = itemView.findViewById(R.id.left_frame_layout);
            rightFrameLayout = itemView.findViewById(R.id.right_frame_layout);
            commentButton = itemView.findViewById(R.id.btn_show_comment);
            daysTextView = itemView.findViewById(R.id.tv_days);
        }
    }
}