package net.oschina.git.roland.wimm.runningaccount;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import net.oschina.git.roland.wimm.R;
import net.oschina.git.roland.wimm.common.base.WIMMApplication;
import net.oschina.git.roland.wimm.common.data.RunningAccount;

import java.text.ParseException;
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

public class RunningAccountFragment extends Fragment {

    private View contentView;

    private ExpandableListView elvRunningAccount;

    private RunningAccountAdapter adapter;

    private List<RunningAccount> datas = new ArrayList<>();

    private Map<String, List<RunningAccount>> filteredDatas = new HashMap<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.fragment_running_account, container, false);
        initComp();
        initData();
        return contentView;
    }

    private void initComp() {
        adapter = new RunningAccountAdapter(getContext(), filteredDatas);
        elvRunningAccount = (ExpandableListView) contentView.findViewById(R.id.elv_running_account);
        elvRunningAccount.setAdapter(adapter);
        elvRunningAccount.setDivider(null);
        elvRunningAccount.setGroupIndicator(null);
    }

    private void initData() {
        datas.clear();
        filteredDatas.clear();

//        List<RunningAccount> localData = RunningAccount.findByUserId(WIMMApplication.getApplication().getmUser().getUserId());
//        if (localData != null && localData.size() > 0) {
//            datas.addAll(localData);
//            filterData();
//        }

        makeFakeData();
        filterData();

        adapter.notifyDataSetChanged();
    }

    private void filterData() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(RunningAccount.DATE_FORMAT, Locale.US);
        for (RunningAccount item : datas) {
            String strDate = dateFormat.format(new Date(item.getTimeStamp()));
            if (!filteredDatas.containsKey(strDate)) {
                filteredDatas.put(strDate, new ArrayList<RunningAccount>());
            }
            filteredDatas.get(strDate).add(item);
        }
    }

    /**
     * 假数据
     */
    private void makeFakeData() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(RunningAccount.DATE_FORMAT, Locale.US);
        String userId = WIMMApplication.getApplication().getmUser().getUserId();

        try {
            RunningAccount item = new RunningAccount();
            item.setUserId(userId);
            item.setTimeStamp(dateFormat.parse("2016-03-21").getTime());
            item.setAmount(100);
            item.setRemark("工资");
            datas.add(item);

            item = new RunningAccount();
            item.setUserId(userId);
            item.setTimeStamp(dateFormat.parse("2016-05-15").getTime());
            item.setAmount(-100);
            item.setRemark("买衣服");
            datas.add(item);

            item = new RunningAccount();
            item.setUserId(userId);
            item.setTimeStamp(dateFormat.parse("2016-03-14").getTime());
            item.setAmount(-100);
            item.setRemark("吃饭");
            datas.add(item);

            item = new RunningAccount();
            item.setUserId(userId);
            item.setTimeStamp(dateFormat.parse("2016-03-14").getTime());
            item.setAmount(100);
            item.setRemark("工资");
            datas.add(item);

            item = new RunningAccount();
            item.setUserId(userId);
            item.setTimeStamp(dateFormat.parse("2016-03-14").getTime());
            item.setAmount(100);
            item.setRemark("工资");
            datas.add(item);

            item = new RunningAccount();
            item.setUserId(userId);
            item.setTimeStamp(dateFormat.parse("2016-03-22").getTime());
            item.setAmount(100);
            item.setRemark("工资");
            datas.add(item);

            item = new RunningAccount();
            item.setUserId(userId);
            item.setTimeStamp(System.currentTimeMillis());
            item.setAmount(100);
            item.setRemark("工资");
            datas.add(item);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
