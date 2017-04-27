package net.oschina.git.roland.wimm.settings;

import android.widget.ListView;

import net.oschina.git.roland.wimm.R;
import net.oschina.git.roland.wimm.common.base.HeaderFragment;

/**
 * Created by Roland on 2017/4/10.
 */

public class SettingsFragment extends HeaderFragment {

    private ListView lvSettings;

    @Override
    protected int getContentViewLayout() {
        return R.layout.fragment_settings;
    }

    @Override
    protected void initComp() {
        lvSettings = (ListView) findViewById(R.id.lv_settings);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void refreshHeader() {
        super.refreshHeader();
        header.setTitle(R.string.str_settings);
    }
}
