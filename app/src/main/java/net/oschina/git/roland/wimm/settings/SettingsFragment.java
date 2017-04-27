package net.oschina.git.roland.wimm.settings;

import android.widget.ListView;

import net.oschina.git.roland.wimm.R;
import net.oschina.git.roland.wimm.common.base.HeaderFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by Roland on 2017/4/10.
 */
@ContentView(R.layout.fragment_settings)
public class SettingsFragment extends HeaderFragment {

    @ViewInject(R.id.lv_settings)
    private ListView lvSettings;

    @Override
    protected void initComp() {

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
