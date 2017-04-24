package net.oschina.git.roland.wimm.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Roland on 2017/4/10.
 */

class MainActivityPagerAdapter extends FragmentPagerAdapter {

    private String[] titles = null;

    private List<Fragment> fragments;

    MainActivityPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    void setTitles(String[] titles) {
        this.titles = titles;
    }

    void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        if (position >= 0 && position < fragments.size()) {
            return fragments.get(position);
        }
        return null;
    }

    @Override
    public int getCount() {
        return titles == null ? 0 : titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
