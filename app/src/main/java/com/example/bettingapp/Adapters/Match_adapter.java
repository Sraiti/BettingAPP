package com.example.bettingapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bettingapp.Moduls.match;
import com.example.bettingapp.R;
import com.example.bettingapp.ViewHolders.MatchViewHolder;
import com.example.bettingapp.ViewHolders.UnifiedNativeAdViewHolder;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;

import java.util.ArrayList;
import java.util.List;

public class Match_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
	
	// A menu item view type.
	private static final int MENU_ITEM_VIEW_TYPE = 0;
	
	private static final int UNIFIED_NATIVE_AD_VIEW_TYPE = 1;
	
	private final Context mContext;
	private onItemClickListener mListerner;
	List<match> MatchList;
	List<UnifiedNativeAd> NativeList;
	private List<Object> mRecyclerViewItems = new ArrayList<>();
	
	public Match_adapter(Context context, List<Object> recyclerViewItems) {
		this.mContext = context;
		mRecyclerViewItems = recyclerViewItems;
	}
	
	public void setOnItemClickListerner(onItemClickListener listerner){
		mListerner=listerner;
	}
	
	
	@Override
	public int getItemCount() {
		return mRecyclerViewItems.size();
	}
	
	@Override
	public int getItemViewType(int position) {
		if (mRecyclerViewItems.get(position) instanceof UnifiedNativeAd) {
			return UNIFIED_NATIVE_AD_VIEW_TYPE;
		} else {
			return MENU_ITEM_VIEW_TYPE;
		}
	}
	
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
		switch (viewType) {
			case UNIFIED_NATIVE_AD_VIEW_TYPE:
				
				View unifiedNativeLayoutView = LayoutInflater.from(
						viewGroup.getContext()).inflate(R.layout.ad_unified,
						viewGroup, false);
				return new UnifiedNativeAdViewHolder(unifiedNativeLayoutView);
			
			case MENU_ITEM_VIEW_TYPE:
				// Continue to default
			default:
				View menuItemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
						R.layout.item, viewGroup, false);
				return new MatchViewHolder(menuItemLayoutView,mListerner);
			
		}
	}
	
	@Override
	public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
		int viewType = getItemViewType(position);
		Object Item = mRecyclerViewItems.get(position);
		switch (viewType) {
			case UNIFIED_NATIVE_AD_VIEW_TYPE:
				
				UnifiedNativeAd nativeAd = (UnifiedNativeAd) Item;
				populateNativeAdView(nativeAd, ((UnifiedNativeAdViewHolder) holder).getAdView());
				break;
			case MENU_ITEM_VIEW_TYPE:
				// execute the default
			
			default:
				MatchViewHolder MatchHolder = (MatchViewHolder) holder;
				final match menuItem = (match) Item;
				
				
				MatchHolder.team1.setText(menuItem.getTeam1());
				MatchHolder.team2.setText(menuItem.getTeam2());
				MatchHolder.League.setText(menuItem.getLeague());
				MatchHolder.cote.setText(menuItem.getCote());
				MatchHolder.expectation.setText(menuItem.getExpectation());
				MatchHolder.time.setText(menuItem.getTime());
				if (menuItem.getStatue() == 0) {
					MatchHolder.cote.setBackgroundColor(mContext.getResources().getColor(R.color.flatui_pomegranate));
				} else if (menuItem.getStatue() == 1) {
					MatchHolder.cote.setBackgroundColor(mContext.getResources().getColor(R.color.color1));
				}
			
			
		}
		
	}
	
	
	private void populateNativeAdView(UnifiedNativeAd nativeAd,
	                                  UnifiedNativeAdView adView) {
		// Some assets are guaranteed to be in every UnifiedNativeAd.
		((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
		((TextView) adView.getBodyView()).setText(nativeAd.getBody());
		((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
		
		// These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
		// check before trying to display them.
		NativeAd.Image icon = nativeAd.getIcon();
		
		if (icon == null) {
			adView.getIconView().setVisibility(View.INVISIBLE);
		} else {
			((ImageView) adView.getIconView()).setImageDrawable(icon.getDrawable());
			adView.getIconView().setVisibility(View.VISIBLE);
		}
		
		if (nativeAd.getPrice() == null) {
			adView.getPriceView().setVisibility(View.INVISIBLE);
		} else {
			adView.getPriceView().setVisibility(View.VISIBLE);
			((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
		}
		
		if (nativeAd.getStore() == null) {
			adView.getStoreView().setVisibility(View.INVISIBLE);
		} else {
			adView.getStoreView().setVisibility(View.VISIBLE);
			((TextView) adView.getStoreView()).setText(nativeAd.getStore());
		}
		
		if (nativeAd.getStarRating() == null) {
			adView.getStarRatingView().setVisibility(View.INVISIBLE);
		} else {
			((RatingBar) adView.getStarRatingView())
					.setRating(nativeAd.getStarRating().floatValue());
			adView.getStarRatingView().setVisibility(View.VISIBLE);
		}
		
		if (nativeAd.getAdvertiser() == null) {
			adView.getAdvertiserView().setVisibility(View.INVISIBLE);
		} else {
			((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
			adView.getAdvertiserView().setVisibility(View.VISIBLE);
		}
		
		// Assign native ad object to the native view.
		adView.setNativeAd(nativeAd);
	}
	
	
	public interface onItemClickListener{
		void onItemClick(int posistion);
	}
}
