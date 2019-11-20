package com.example.bettingapp.ViewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bettingapp.R;

import java.util.Date;

public class MatchViewHolder extends RecyclerView.ViewHolder {


    public TextView TeamA;
    public TextView TeamB;
    public TextView League;
    public TextView Time;
    public TextView cote;
    public TextView expectation;
    public ImageView image;
    public View mView;
    public MatchViewHolder (@NonNull View itemView) {
        super(itemView);

        TeamA = itemView.findViewById(R.id.txt_teamA);
        TeamB = itemView.findViewById(R.id.txt_TeamB);
        League = itemView.findViewById(R.id.txt_league);
        Time = itemView.findViewById(R.id.txt_time);
        expectation = itemView.findViewById(R.id.txt_expectation);
        cote = itemView.findViewById(R.id.txt_cote);
        image = itemView.findViewById(R.id.img_match);

        mView = itemView;


    }

    public View getmView() {
        return mView;
    }
}

