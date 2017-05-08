package net.oschina.git.roland.wimm.model.settings;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import net.oschina.git.roland.wimm.R;
import net.oschina.git.roland.wimm.common.base.HeaderFragment;
import net.oschina.git.roland.wimm.model.settings.functionswitch.FunctionSwitchActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by Roland on 2017/4/10.
 */
@ContentView(R.layout.fragment_settings)
public class SettingsFragment extends HeaderFragment implements View.OnClickListener {

    @ViewInject(R.id.btnFunction)
    private Button btnFunction;

    @Override
    protected void initComp() {

    }

    @Override
    protected void initListener() {
        btnFunction.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void refreshHeader() {
        super.refreshHeader();
        header.setTitle(R.string.str_settings);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;

        switch (v.getId()) {
            case R.id.btnFunction:
                intent = new Intent(getContext(), FunctionSwitchActivity.class);
                break;

            default:
                break;
        }

        if (intent != null) {
            startActivity(intent);
        }
    }
}
