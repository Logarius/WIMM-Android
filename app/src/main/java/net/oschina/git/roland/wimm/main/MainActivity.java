package net.oschina.git.roland.wimm.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.MenuItem;

import net.oschina.git.roland.wimm.R;
import net.oschina.git.roland.wimm.common.base.BaseActivity;
import net.oschina.git.roland.wimm.common.view.HeaderFragment;
import net.oschina.git.roland.wimm.common.view.CommonHeader;
import net.oschina.git.roland.wimm.model.function.FunctionsFragment;
import net.oschina.git.roland.wimm.model.runningaccount.RunningAccountFragment;
import net.oschina.git.roland.wimm.settings.SettingsFragment;
import net.oschina.git.roland.wimm.model.statistics.StatisticsFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by Roland on 2017/4/10.
 */
@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @ViewInject(R.id.navigation)
    private BottomNavigationView navigationView;

    @ViewInject(R.id.header)
    private CommonHeader header;

    private SparseArray<HeaderFragment> fragments;

    private FragmentManager fragmentManager;

    private HeaderFragment displayingFragment;

    @Override
    protected void initComp() {
        fragments = new SparseArray<>();
        fragments.put(R.id.home, new StatisticsFragment().setHeader(header));
        fragments.put(R.id.running_account, new RunningAccountFragment().setHeader(header));
        fragments.put(R.id.functions, new FunctionsFragment().setHeader(header));
        fragments.put(R.id.settings, new SettingsFragment().setHeader(header));

        navigationView.setItemIconTintList(null);
        fragmentManager = getSupportFragmentManager();

        showFragment(R.id.home);
    }

    @Override
    protected void initListener() {
        navigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            confirmExit();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

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

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            showFragment(item.getItemId());
            return true;
        }
    };

    private void showFragment(int menuId) {
        displayingFragment = fragments.get(menuId);
        if (displayingFragment != null) {
            fragmentManager.beginTransaction().replace(R.id.content, displayingFragment).commit();
            displayingFragment.refreshHeader();
        }
    }

}
