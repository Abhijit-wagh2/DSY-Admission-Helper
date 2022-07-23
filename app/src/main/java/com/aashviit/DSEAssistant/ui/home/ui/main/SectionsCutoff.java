package com.aashviit.DSEAssistant.ui.home.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.aashviit.DSEAssistant.R;
import com.aashviit.DSEAssistant.ui.home.cutoff.Cutoff_2018;
import com.aashviit.DSEAssistant.ui.home.cutoff.Cutoff_2019;
import com.aashviit.DSEAssistant.ui.home.cutoff.Cutoff_2020;
import com.aashviit.DSEAssistant.ui.home.cutoff.Cutoff_2021;

public class SectionsCutoff extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2,R.string.tab_text_3,R.string.tab_text_4};
    private final Context mContext;

    public SectionsCutoff(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment=new Cutoff_2018();
                break;

            case 1:
                fragment=new Cutoff_2019();
                break;

            case 2:
                fragment=new Cutoff_2020();
                break;


            case 3:
                fragment=new Cutoff_2021();
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
        return 4;
    }
}