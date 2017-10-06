package com.popland.pop.mn.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by hai on 17/05/2017.
 */

public class AdapterPagerFragment extends FragmentPagerAdapter {
ArrayList<Fragment> arrFragment = new ArrayList<>();

    public AdapterPagerFragment(FragmentManager fm){
        super(fm);
    }

    @Override
    public int getCount() {
        return arrFragment.size();//must
    }

    @Override
    public Fragment getItem(int position) {
        return arrFragment.get(position);//must
    }

    public void AddFragment(Fragment f){
        arrFragment.add(f);
    }

}
