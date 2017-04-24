package net.oschina.git.roland.wimm.statistics;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.oschina.git.roland.wimm.R;
import net.oschina.git.roland.wimm.common.base.HeaderFragment;
import net.oschina.git.roland.wimm.common.base.WIMMApplication;
import net.oschina.git.roland.wimm.common.data.Account;
import net.oschina.git.roland.wimm.common.data.User;

/**
 * Created by Roland on 2017/4/10.
 */

public class StatisticsFragment extends HeaderFragment {

    private static final String TAG = StatisticsFragment.class.getSimpleName();

    private View contentView;

    private TextView tvName;

    private TextView tvAmount;

    private User user = WIMMApplication.getApplication().getmUser();

    private Account account = WIMMApplication.getApplication().getmAccount();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.fragment_statistics, container, false);
        initComp();
        initListener();
        initData();
        return contentView;
    }

    private void initComp() {
        tvName = (TextView) contentView.findViewById(R.id.tv_name);
        tvAmount = (TextView) contentView.findViewById(R.id.tv_amount);
    }

    private void initListener() {

    }

    private void initData() {
        tvName.setText(user.getName());
        tvAmount.setText(String.valueOf(account.getAmount()));
    }

    public void notifyAccountChanged() {
        tvAmount.setText(String.valueOf(account.getAmount()));
    }

    @Override
    public void refreshHeader() {
        header.getLeftFun().setVisibility(View.INVISIBLE);
        header.getRightFunc().setVisibility(View.INVISIBLE);
        header.setTitle(R.string.str_statistics);
    }
}
