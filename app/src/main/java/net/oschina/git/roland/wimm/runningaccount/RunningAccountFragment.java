package net.oschina.git.roland.wimm.runningaccount;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import net.oschina.git.roland.wimm.R;
import net.oschina.git.roland.wimm.common.base.HeaderFragment;
import net.oschina.git.roland.wimm.common.base.WIMMApplication;
import net.oschina.git.roland.wimm.common.base.WIMMConstants;
import net.oschina.git.roland.wimm.common.data.Account;
import net.oschina.git.roland.wimm.common.data.RunningAccount;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Roland on 2017/4/10.
 */

public class RunningAccountFragment extends HeaderFragment implements View.OnClickListener {

    private static final String TAG = RunningAccount.class.getSimpleName();

    private View contentView;

    private ExpandableListView elvRunningAccount;

    private ImageView ivAdd;

    private RunningAccountAdapter adapter;

    private List<RunningAccount> datas = new ArrayList<>();

    private Map<String, List<RunningAccount>> filteredDatas = new HashMap<>();

    private Account account = WIMMApplication.getApplication().getmAccount();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.fragment_running_account, container, false);
        initComp();
        initListener();
        initData();
        return contentView;
    }

    private void initComp() {
        adapter = new RunningAccountAdapter(getContext(), filteredDatas);
        ivAdd = (ImageView) contentView.findViewById(R.id.iv_add);
        elvRunningAccount = (ExpandableListView) contentView.findViewById(R.id.elv_running_account);
        elvRunningAccount.setAdapter(adapter);
        elvRunningAccount.setDivider(null);
        elvRunningAccount.setGroupIndicator(null);
    }

    private void initListener() {
        ivAdd.setOnClickListener(this);
    }

    private void initData() {
        datas.clear();
        filteredDatas.clear();

        List<RunningAccount> localData = RunningAccount.findByUserId(WIMMApplication.getApplication().getmUser().getUserId());
        if (localData != null && localData.size() > 0) {
            datas.addAll(localData);
            filterData();
        }

        refreshList();
    }

    private void filterData() {
        filteredDatas.clear();
        SimpleDateFormat dateFormat = new SimpleDateFormat(WIMMConstants.RUNNING_ACCOUNT_DATE_FORMAT, Locale.US);
        for (RunningAccount item : datas) {
            String strDate = dateFormat.format(new Date(item.getTimeStamp()));
            if (!filteredDatas.containsKey(strDate)) {
                filteredDatas.put(strDate, new ArrayList<RunningAccount>());
            }
            filteredDatas.get(strDate).add(item);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_add:
                AddRunningAccountDialog dialog = new AddRunningAccountDialog(getContext());
                dialog.setOnNewRunningAccountAddListener(onNewRunningAccountAddListener);
                dialog.show();
                break;

            default:
                break;
        }
    }

    private AddRunningAccountDialog.OnNewRunningAccountAddListener onNewRunningAccountAddListener = new AddRunningAccountDialog.OnNewRunningAccountAddListener() {
        @Override
        public void onNewRunningAccount(RunningAccount runningAccount) {
            runningAccount.setUserId(account.getUserId());
            runningAccount.saveOrUpdate();

            account.add(runningAccount.getAmount());
            account.saveOrUpdate();

            datas.add(runningAccount);
            filterData();
            refreshList();
        }
    };

    private void refreshList() {
        adapter.notifyDataSetChanged();
        if (adapter.getGroupCount() > 0) {
            for (int i = 0; i < adapter.getGroupCount(); i++) {
                elvRunningAccount.expandGroup(i);
            }
        }
    }

    @Override
    public void refreshHeader() {
        header.getLeftFun().setVisibility(View.INVISIBLE);
        header.getRightFunc().setVisibility(View.INVISIBLE);
        header.setTitle(R.string.str_running_acount);
    }
}
