package com.example.bettingapp.Adapters;



import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.bettingapp.Views.TabToday;
import com.example.bettingapp.Views.TabYesterday;


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
