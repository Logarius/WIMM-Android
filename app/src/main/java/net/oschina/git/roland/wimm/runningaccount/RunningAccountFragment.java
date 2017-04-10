package net.oschina.git.roland.wimm.runningaccount;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import net.oschina.git.roland.wimm.R;

/**
 * Created by Roland on 2017/4/10.
 */

public class RunningAccountFragment extends Fragment {

    private View contentView;

    private ExpandableListView elvRunningAccount;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.fragment_running_account, container, false);
        initComp();
        return contentView;
    }

    private void initComp() {
        elvRunningAccount = (ExpandableListView) contentView.findViewById(R.id.elv_running_account);
    }
}
