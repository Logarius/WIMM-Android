package net.oschina.git.roland.wimm.main;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import net.oschina.git.roland.wimm.R;
import net.oschina.git.roland.wimm.common.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

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

    private MainActivityPagerAdapter adapter = null;

    @Override
    protected void initComp() {
        adapter = new MainActivityPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void initListener() {
    }

    @Override
    protected void initData() {

    }

}
