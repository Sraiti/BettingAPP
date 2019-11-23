package com.example.bettingapp.AdsManager;

import android.content.Context;
import android.widget.Toast;

import com.example.bettingapp.Moduls.module_firebase;
import com.example.bettingapp.util.ConsentSDK;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSettings;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


public class ads_manager {


    public static final int NUMBER_OF_ADS = 3;
    public static module_firebase obj;
    public static String id_native = null;
    public static boolean objloaded = false;
    public static ads_manager Instance;
    public AdView adView;
    public InterstitialAd mInterstitialAd;
    public com.facebook.ads.AdView fbadView;
    public com.facebook.ads.InterstitialAd mInterstitialAdfb;
    public int counterads = 0;
    DataFireStore dataFireStore = DataFireStore.getInstance();

    public static ads_manager getInstance() {
        if (Instance == null)
            Instance = new ads_manager();
        return Instance;
    }


    public AdView load_admob_banner(Context context) {
        try {
            adView = new AdView(context);
            adView.setAdSize(AdSize.SMART_BANNER);
            adView.setAdUnitId(dataFireStore.ObjectFirebase.admob_banner);
            AdRequest adRequest = new AdRequest.Builder().build();
            adView.loadAd(adRequest);
        } catch (Exception e) {
        }

        return adView;

    }


    public com.facebook.ads.AdView fbLoadBanner(final Context context) {

        try {
            fbadView = new com.facebook.ads.AdView(context,
                    dataFireStore.ObjectFirebase.getFb_banner(),
                    com.facebook.ads.AdSize.BANNER_HEIGHT_50);
            AdSettings.addTestDevice("2B3D0A13A0A6EF98B4E2A18C2984F989");
            fbadView.loadAd();

            fbadView.setAdListener(new AdListener() {
                @Override
                public void onError(Ad ad, AdError adError) {

                }

                @Override
                public void onAdLoaded(Ad ad) {

                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            });
        } catch (Exception e) {
        }

        return fbadView;
    }


    public void loadIntersAdmob(Context context) {
        if (mInterstitialAd == null) {
            mInterstitialAd = new InterstitialAd(context);
            mInterstitialAd.setAdUnitId(dataFireStore.ObjectFirebase.getAdmob_interstitial());
        }
        mInterstitialAd.loadAd(ConsentSDK.getAdRequest(context));

    }

    public void showadmobInter(Context context) {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            mInterstitialAd.loadAd(ConsentSDK.getAdRequest(context));
        }
    }

    public com.facebook.ads.InterstitialAd loadFbInterstitial(final Context context) {
        if (mInterstitialAdfb == null) {
            try {
                mInterstitialAdfb = new com.facebook.ads.InterstitialAd(context, dataFireStore.ObjectFirebase.fb_interstitial);
                AdSettings.addTestDevice("2B3D0A13A0A6EF98B4E2A18C2984F989");
                mInterstitialAdfb.loadAd();
            } catch (Exception e) {
                return mInterstitialAdfb;
            }

        } else if (!mInterstitialAdfb.isAdLoaded())
            mInterstitialAdfb.loadAd();
        mInterstitialAdfb.setAdListener(new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                // Interstitial ad displayed callback

            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                // Interstitial dismissed callback

            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
                Toast.makeText(context, adError.getErrorMessage() + " It's a me ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLoaded(Ad ad) {

            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        });
        return mInterstitialAdfb;

    }

    public void showFbInterstitial(Context context) {
        if (mInterstitialAdfb.isAdLoaded())
            mInterstitialAdfb.show();
        else loadFbInterstitial(context);
    }


}
