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


    public fragment_adapter(FragmentManager fm, int numberPage) {
        super(fm);
        this.mapage = numberPage;
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









}
