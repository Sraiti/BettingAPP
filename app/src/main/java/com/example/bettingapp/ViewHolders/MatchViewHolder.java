package com.example.bettingapp.ViewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bettingapp.R;

import java.util.Date;

public class MatchViewHolder extends RecyclerView.ViewHolder {


    public TextView team1;
    public TextView team2;
    public TextView League;
    public TextView time;
    public TextView cote;
    public TextView expectation;
    public View mView;
    public MatchViewHolder (@NonNull View itemView) {
        super(itemView);

        team1 = itemView.findViewById(R.id.txt_teamA);
        team2 = itemView.findViewById(R.id.txt_TeamB);
        League = itemView.findViewById(R.id.txt_league);
        time = itemView.findViewById(R.id.txt_time);
        expectation = itemView.findViewById(R.id.txt_expectation);
        cote = itemView.findViewById(R.id.txt_cote);


        mView = itemView;


    }

    public View getmView() {
        return mView;
    }
}

