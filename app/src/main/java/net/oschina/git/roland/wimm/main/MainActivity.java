package net.oschina.git.roland.wimm.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;

import net.oschina.git.roland.wimm.R;
import net.oschina.git.roland.wimm.common.base.BaseActivity;
import net.oschina.git.roland.wimm.common.base.HeaderFragment;
import net.oschina.git.roland.wimm.common.view.CommonHeader;
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

    @ViewInject(R.id.header)
    private CommonHeader header;

    @ViewInject(R.id.tabLayout)
    private TabLayout tabLayout;

    @ViewInject(R.id.viewPager)
    private ViewPager viewPager;

    private List<Fragment> fragments;

    private MainActivityPagerAdapter adapter = null;

    @Override
    protected void initComp() {
        adapter = new MainActivityPagerAdapter(getSupportFragmentManager());
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

        for (Fragment fragment : fragments) {
            if (fragment instanceof HeaderFragment) {
                ((HeaderFragment) fragment).setHeader(header);
            }
        }

        String[] titles = new String[]{
                getString(R.string.str_statistics),
                getString(R.string.str_running_acount),
                getString(R.string.str_functions),
                getString(R.string.str_settings)
        };

        adapter.setTitles(titles);
        adapter.setFragments(fragments);
        adapter.notifyDataSetChanged();

        ((HeaderFragment) fragments.get(0)).refreshHeader();
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            confirmExit();
            return true;
        }
        return super.onKeyUp(keyCode, event);
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

            if (fragments.get(position) instanceof HeaderFragment) {
                ((HeaderFragment) fragments.get(position)).refreshHeader();
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void confirmExit() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage(R.string.confirm_exit);
        dialog.setNegativeButton(R.string.str_cancel, null);
        dialog.setPositiveButton(R.string.str_confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        dialog.show();
    }

}
