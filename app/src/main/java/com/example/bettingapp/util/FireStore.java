package com.example.bettingapp.util;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.bettingapp.AdsManager.DataFireStore;
import com.example.bettingapp.Moduls.match;
import com.example.bettingapp.R;
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

public class FireStore {

    public static final int NUMBER_OF_ADS = 3;
    public static FireStore Instence;
    public boolean isloadData = false;
    public List<Object> mRecyclerViewItemsToday;
    public List<Object> mRecyclerViewItemsyesterday;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DataFireStore dataFireStore = DataFireStore.getInstance();
    private AdLoader adLoader;
    private List<UnifiedNativeAd> mNativeAds = new ArrayList<>();

    public static FireStore getInstence() {
        if (Instence == null)
            Instence = new FireStore();
        return Instence;
    }

    public List<Object> LoadDataToday(Context context) {
        if (!isloadData) {
            mRecyclerViewItemsToday = new ArrayList<>();
            db.collection("match")
                    .whereEqualTo("Day", "t")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d("TAG", document.getId() + " => " + document.getData());
                                    mRecyclerViewItemsToday.add(document.toObject(match.class));
                                    Log.d("TAG", "TODAY DATA  GETED");

                                }
                                isloadData = true;
                            } else {

                                Log.d("TAG", "Error getting documents: ", task.getException());
                            }
                        }
                    });
            loadNativeAds(context, mRecyclerViewItemsToday);
        }
        return mRecyclerViewItemsToday;
    }

    public List<Object> LoadDatayesterday(Context context) {
        if (!isloadData) {
            mRecyclerViewItemsyesterday = new ArrayList<>();
            db.collection("match")
                    .whereEqualTo("Day", "y")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d("TAG", document.getId() + " => " + document.getData());
                                    mRecyclerViewItemsyesterday.add(document.toObject(match.class));
                                    Log.d("TAG", "Yesterday DATA  GETED");
                                    isloadData = true;
                                }
                            } else {
                                Log.d("TAG", "Error getting documents: ", task.getException());
                            }
                        }
                    });

            loadNativeAds(context, mRecyclerViewItemsyesterday);
        }
        return mRecyclerViewItemsyesterday;
    }


    private void loadNativeAds(final Context context, final List<Object> mRecyclerViewItemsToday) {
        String nativeid;
        try {
            nativeid = dataFireStore.ObjectFirebase.native_ads;
        } catch (Exception ex) {
            nativeid = context.getString(R.string.ad_unit_id);
        }

        AdLoader.Builder builder = new AdLoader.Builder(context, nativeid);
        adLoader = builder.forUnifiedNativeAd(
                new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
                    @Override
                    public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                        // A native ad loaded successfully, check if the ad loader has finished loading
//                        // and if so, insert the ads into the list.
                        mNativeAds.add(unifiedNativeAd);
                        if (!adLoader.isLoading()) {
                            insertAdsInMenuItems(mRecyclerViewItemsToday, context);
                        }
                    }
                }).withAdListener(
                new AdListener() {
                    @Override
                    public void onAdFailedToLoad(int errorCode) {
                        // A native ad failed to load, check if the ad loader has finished loading
                        // and if so, insert the ads into the list.
                        Log.e("MainActivity", "The previous native ad failed to load. Attempting to"
                                + " load another.");
                        if (!adLoader.isLoading()) {
                            insertAdsInMenuItems(mRecyclerViewItemsToday, context);
                        }
                    }
                }).build();

        // Load the Native ads.
        adLoader.loadAds(new AdRequest.Builder().build(), NUMBER_OF_ADS);
    }

    private void insertAdsInMenuItems(List<Object> mRecyclerViewItems, Context context) {
        if (mNativeAds.size() <= 0) {
            return;
        }
        int index = 0;
        for (UnifiedNativeAd ad : mNativeAds) {
            try { //Comment this to close the native Ads
                if (index != 0) {
                    mRecyclerViewItems.add(index, ad);
                    Log.d("TAG", "NATIVE ADDED");
                }
                index = index + 3;
            } catch (IndexOutOfBoundsException ex) {

                LoadDataToday(context);
                LoadDatayesterday(context);

            }

        }
    }
}
