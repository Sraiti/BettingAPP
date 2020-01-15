package com.example.bettingapp.ViewHolders;

import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bettingapp.Adapters.Match_adapter;
import com.example.bettingapp.R;

public class MatchViewHolder extends RecyclerView.ViewHolder {
	
	
	public TextView team1;
	public TextView team2;
	public TextView League;
	public TextView time;
	public TextView cote;
	public TextView expectation;
	public TextView expectation2;
	public TextView expectation3;
	public Button Vote;
	public View mView;
	
	public MatchViewHolder(@NonNull View itemView, final Match_adapter.onItemClickListener listener) {
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

