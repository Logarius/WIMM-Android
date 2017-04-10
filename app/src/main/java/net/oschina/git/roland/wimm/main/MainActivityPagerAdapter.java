package net.oschina.git.roland.wimm.main;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import net.oschina.git.roland.wimm.R;
import net.oschina.git.roland.wimm.runningaccount.RunningAccountFragment;
import net.oschina.git.roland.wimm.settings.SettingsFragment;
import net.oschina.git.roland.wimm.statistics.StatisticsFragment;

/**
 * Created by Roland on 2017/4/10.
 */

class MainActivityPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    private String[] titles = null;

    private StatisticsFragment statisticsFragment;

    private RunningAccountFragment runningAcountFragment;

    private SettingsFragment settingsFragment;

    MainActivityPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;

        titles = new String[] {
                mContext.getString(R.string.str_statistics),
                mContext.getString(R.string.str_running_acount),
                mContext.getString(R.string.str_settings)
        };
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                if (statisticsFragment == null) {
                    statisticsFragment = new StatisticsFragment();
                }
                return statisticsFragment;

            case 1:
                if (runningAcountFragment == null) {
                    runningAcountFragment = new RunningAccountFragment();
                }
                return runningAcountFragment;

            case 2:
                if (settingsFragment == null) {
                    settingsFragment = new SettingsFragment();
                }
                return settingsFragment;

            default:
                return null;
        }
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
