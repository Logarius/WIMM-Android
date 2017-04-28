package net.oschina.git.roland.wimm.runningaccount;

import android.widget.ExpandableListView;

import net.oschina.git.roland.wimm.R;
import net.oschina.git.roland.wimm.common.base.HeaderFragment;
import net.oschina.git.roland.wimm.common.base.WIMMApplication;
import net.oschina.git.roland.wimm.common.base.WIMMConstants;
import net.oschina.git.roland.wimm.common.data.Account;
import net.oschina.git.roland.wimm.common.data.RunningAccount;
import net.oschina.git.roland.wimm.common.view.CommonHeader;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

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
@ContentView(R.layout.fragment_running_account)
public class RunningAccountFragment extends HeaderFragment implements CommonHeader.CommonHeaderListener {

    @ViewInject(R.id.elv_running_account)
    private ExpandableListView elvRunningAccount;

    private RunningAccountAdapter adapter;

    private List<RunningAccount> datas = new ArrayList<>();

    private Map<String, List<RunningAccount>> filteredDatas = new HashMap<>();

    private Account account = WIMMApplication.getApplication().getAccount();

    @Override
    protected void initComp() {
        adapter = new RunningAccountAdapter(getContext(), filteredDatas);
        elvRunningAccount.setAdapter(adapter);
        elvRunningAccount.setDivider(null);
        elvRunningAccount.setGroupIndicator(null);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        datas.clear();
        filteredDatas.clear();

        List<RunningAccount> localData = RunningAccount.findByUserId(WIMMApplication.getApplication().getUser().getUserId());
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
        super.refreshHeader();
        header.setTitle(R.string.str_running_acount);
        header.setRightFuncIcon(R.drawable.plus);
        header.setCommonHeaderListener(this);
    }

    @Override
    public void onClick(int witch) {
        if (witch == CommonHeader.VIEW_IV_RIGHT_FUNC) {
            AddRunningAccountDialog dialog = new AddRunningAccountDialog(getContext());
            dialog.setOnNewRunningAccountAddListener(onNewRunningAccountAddListener);
            dialog.show();
        }
    }
}
