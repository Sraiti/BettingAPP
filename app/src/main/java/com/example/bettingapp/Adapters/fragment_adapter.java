package com.example.bettingapp.Adapters;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.bettingapp.Views.Odds_10;
import com.example.bettingapp.Views.Odds_5;
import com.example.bettingapp.Views.TabYesterday;


public class fragment_adapter extends FragmentStatePagerAdapter {

    int mapage;


    public fragment_adapter(FragmentManager fm, int numberPage) {
        super(fm);
        this.mapage = numberPage;
    }

    @Override
    public Fragment getItem(int position) {
        Odds_5 odds5 = new Odds_5();
        TabYesterday tabYesterday = new TabYesterday();
        Odds_10 odds10=new Odds_10();
        

        switch (position) {
            case 0:
                return odds5;
            case 1:
                return odds10;
            case 2:
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
