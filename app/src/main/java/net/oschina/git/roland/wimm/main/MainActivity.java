package net.oschina.git.roland.wimm.main;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import net.oschina.git.roland.wimm.R;
import net.oschina.git.roland.wimm.common.base.BaseActivity;
import net.oschina.git.roland.wimm.function.FunctionsFragment;
import net.oschina.git.roland.wimm.runningaccount.RunningAccountFragment;
import net.oschina.git.roland.wimm.settings.SettingsFragment;
import net.oschina.git.roland.wimm.statistics.StatisticsFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roland on 2017/4/10.
 */
@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @ViewInject(R.id.tabLayout)
    private TabLayout tabLayout;

    @ViewInject(R.id.viewPager)
    private ViewPager viewPager;

    private String[] titles;

    private List<Fragment> fragments;

    private MainActivityPagerAdapter adapter = null;

    @Override
    protected void initComp() {
        adapter = new MainActivityPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void initListener() {
        viewPager.addOnPageChangeListener(onPageChangeListener);
    }

    @Override
    protected void initData() {
        fragments = new ArrayList<>();
        fragments.add(new StatisticsFragment());
        fragments.add(new RunningAccountFragment());
        fragments.add(new FunctionsFragment());
        fragments.add(new SettingsFragment());

        titles = new String[] {
                getString(R.string.str_statistics),
                getString(R.string.str_running_acount),
                getString(R.string.str_functions),
                getString(R.string.str_settings)
        };

        adapter.setTitles(titles);
        adapter.setFragments(fragments);
        adapter.notifyDataSetChanged();
    }

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (fragments.get(position) instanceof StatisticsFragment) {
                ((StatisticsFragment) fragments.get(position)).notifyAccountChanged();
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}
