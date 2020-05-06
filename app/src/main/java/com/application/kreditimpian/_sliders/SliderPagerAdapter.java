package com.application.kreditimpian._sliders;



import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.application.kreditimpian.Model.ModelImagePromo.DataItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bagicode on 12/04/17.
 */

public class SliderPagerAdapter extends FragmentStatePagerAdapter {

    private static final String TAG = "SliderPagerAdapter";

    List<DataItem> mFrags = new ArrayList<>();

    public SliderPagerAdapter(FragmentManager fm, List<DataItem> frags) {
        super(fm);
        mFrags = frags;
    }

    @Override
    public Fragment getItem(int position) {
        int index = position % mFrags.size();
        return FragmentSlider.newInstance(mFrags.get(index).getImages());
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }



}