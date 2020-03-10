package com.pixelhubllc.dictionary;

import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPageAdapter extends androidx.fragment.app.FragmentPagerAdapter{

    ViewPageAdapter(@NonNull FragmentManager fm) {
        super(fm,FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

//    private final List<Fragment> mFragmentList = new ArrayList<>();
//    private final List<String> fragmentListTitle = new ArrayList<String>();
//
//    @NonNull
//    @Override
//    public Fragment getItem(int position) {
//        return mFragmentList.get(position);
//    }
//
//    @Override
//    public int getCount() {
//        return fragmentListTitle.size();
//    }
//
//    @Nullable
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return (CharSequence) fragmentListTitle.get(position);
//    }
//
//    public void addFragment(Fragment fragment, String title){
//        mFragmentList.add(fragment);
//        fragmentListTitle.add(title);
//    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new SearchFragment();
            case 1:
                return new FavouriteFragment();
            case 2:
                return new WordsFragment();
            case 3:
                return new SettingFragment();
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return 4;
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "SEARCH";
            case 1:
                return "FAVOURITE";
            case 2:
                return "WORDS";
            case 3:
                return "SETTING";
            default:
                return null;
        }
    }
}
