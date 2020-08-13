package com.yun.uiframev2.adapter;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.yun.uiframev2.fragment.LiveListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lovelin on 2019/7/31
 * <p>
 * Describe:主界面的adapter
 */
public class ChannelPagerAdapter extends FragmentStatePagerAdapter {

    private List<LiveListFragment> mFragmentList;
    private List<String> mChannelList;

    public ChannelPagerAdapter(FragmentManager fm, List<LiveListFragment> mFragmentList, List<String> mChannelList) {
        super(fm);
        this.mFragmentList = mFragmentList;
        this.mChannelList = mChannelList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mChannelList.get(position);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        //        if (mChildCount > 0) {
        //            mChildCount--;
        return POSITION_NONE;
        //        }
        //        return super.getItemPosition(object);
    }
}
