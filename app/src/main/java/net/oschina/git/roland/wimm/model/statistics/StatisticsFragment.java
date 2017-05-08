package net.oschina.git.roland.wimm.model.statistics;

import android.widget.TextView;

import net.oschina.git.roland.wimm.R;
import net.oschina.git.roland.wimm.common.view.HeaderFragment;
import net.oschina.git.roland.wimm.common.base.WIMMApplication;
import net.oschina.git.roland.wimm.common.entities.Account;
import net.oschina.git.roland.wimm.common.entities.User;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by Roland on 2017/4/10.
 */
@ContentView(R.layout.fragment_statistics)
public class StatisticsFragment extends HeaderFragment {

    @ViewInject(R.id.tv_name)
    private TextView tvName;

    @ViewInject(R.id.tv_amount)
    private TextView tvAmount;

    private User user = WIMMApplication.getApplication().getUser();

    private Account account = WIMMApplication.getApplication().getAccount();

    @Override
    protected void initComp() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        tvName.setText(user.getName());
        tvAmount.setText(String.valueOf(account.getAmount()));
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            tvAmount.setText(String.valueOf(account.getAmount()));
        }
    }

    @Override
    public void refreshHeader() {
        super.refreshHeader();
        header.setTitle(R.string.str_home);
    }
}
