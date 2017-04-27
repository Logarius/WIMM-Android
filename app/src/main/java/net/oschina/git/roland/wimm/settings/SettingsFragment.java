package net.oschina.git.roland.wimm.settings;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import net.oschina.git.roland.wimm.R;
import net.oschina.git.roland.wimm.common.base.HeaderFragment;
import net.oschina.git.roland.wimm.common.base.WIMMApplication;
import net.oschina.git.roland.wimm.common.data.User;
import net.oschina.git.roland.wimm.function.FunctionsSwitchUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by Roland on 2017/4/10.
 */
@ContentView(R.layout.fragment_settings)
public class SettingsFragment extends HeaderFragment {

    @ViewInject(R.id.lv_settings)
    private ListView lvSettings;

    @ViewInject(R.id.btnFunction)
    private Button btnFunction;

    private User user = WIMMApplication.getApplication().getmUser();

    private boolean enable = false;

    @Override
    protected void initComp() {

    }

    @Override
    protected void initListener() {
        btnFunction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enable = !enable;
                if (enable) {
                    FunctionsSwitchUtil.getInstance().enableFunction(user.getUserId(), "rental_assistant");
                } else {
                    FunctionsSwitchUtil.getInstance().disableFunction(user.getUserId(), "rental_assistant");
                }
            }
        });
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
