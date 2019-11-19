package com.example.bettingapp.Adapters;


import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;

import com.example.bettingapp.R;
import com.example.bettingapp.Views.TabToday;
import com.example.bettingapp.Views.TabYesterday;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class fragment_adapter extends FragmentStatePagerAdapter {

    int mapage;
    public static final int NUMBER_OF_ADS = 3;


    private AdLoader adLoader;



    private List<Object> mRecyclerViewItems = new ArrayList<>();


    private List<UnifiedNativeAd> mNativeAds = new ArrayList<>();
    Context context;

    public fragment_adapter(FragmentManager fm, int numberPage, Context context) {
        super(fm);
        this.mapage = numberPage;
        this.context=context;
        loadNativeAds();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                TabToday tabToday = new TabToday();
                return tabToday;
            case 1:
                TabYesterday tabYesterday = new TabYesterday();
                return tabYesterday;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return mapage;
    }


    private void loadNativeAds() {
        AdLoader.Builder builder = new AdLoader.Builder(context,   context.getString(R.string.ad_unit_id));
        adLoader = builder.forUnifiedNativeAd(
                new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
                    @Override
                    public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                        // A native ad loaded successfully, check if the ad loader has finished loading
//                        // and if so, insert the ads into the list.
                        mNativeAds.add(unifiedNativeAd);

                    }
                }).withAdListener(
                new AdListener() {
                    @Override
                    public void onAdFailedToLoad(int errorCode) {
                        // A native ad failed to load, check if the ad loader has finished loading
                        // and if so, insert the ads into the list.
                        Log.e("MainActivity", "The previous native ad failed to load. Attempting to"
                                + " load another.");

                    }
                }).build();

        // Load the Native ads.
        adLoader.loadAds(new AdRequest.Builder().build(), NUMBER_OF_ADS);
    }

    public List<Object> getRecyclerViewItems() {
        return mRecyclerViewItems;
    }





}
