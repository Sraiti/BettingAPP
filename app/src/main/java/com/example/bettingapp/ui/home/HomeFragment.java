package com.example.bettingapp.ui.home;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.bettingapp.Adapters.fragment_adapter;
import com.example.bettingapp.AdsManager.ads_manager;
import com.example.bettingapp.R;
import com.example.bettingapp.Views.TabToday;
import com.example.bettingapp.Views.TabYesterday;
import com.google.android.gms.ads.AdListener;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment implements TabToday.OnFragmentInteractionListener,
        TabYesterday.OnFragmentInteractionListener {

    Context context;
    private HomeViewModel homeViewModel;
    private ads_manager manager;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        context = getActivity();
        manager = ads_manager.getInstance();
        manager.loadIntersAdmob(context);
        manager.loadFbInterstitial(context);

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        TabLayout tabLayout = root.findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("Today"));
        tabLayout.addTab(tabLayout.newTab().setText("yesterday"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = root.findViewById(R.id.viewpagerToday);
        final fragment_adapter adapter = new fragment_adapter(getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                manager.showadmobInter(context);
                manager.mInterstitialAd.setAdListener(new AdListener(){
                    @Override
                    public void onAdFailedToLoad(int i) {
                        super.onAdFailedToLoad(i);
                        manager.showFbInterstitial(context);
                    }
                });

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return root;
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}