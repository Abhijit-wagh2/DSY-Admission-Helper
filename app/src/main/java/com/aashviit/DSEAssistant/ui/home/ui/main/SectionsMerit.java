package com.aashviit.DSEAssistant.ui.home.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.aashviit.DSEAssistant.ui.home.merit.Merit_2018;
import com.aashviit.DSEAssistant.ui.home.merit.Merit_2019;
import com.aashviit.DSEAssistant.ui.home.merit.Merit_2020;
import com.aashviit.DSEAssistant.ui.home.merit.Merit_2021;

public class SectionsMerit extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{};
    private final Context mContext;

    public SectionsMerit(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment=new Merit_2018();
                break;

            case 1:
                fragment=new Merit_2019();
                break;

            case 2:
                fragment=new Merit_2020();
                break;


            case 3:
                fragment=new Merit_2021();
                break;

        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 0;
    }
}