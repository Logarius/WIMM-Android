package net.oschina.git.roland.wimm.runningaccount;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import net.oschina.git.roland.wimm.R;
import net.oschina.git.roland.wimm.common.data.RunningAccount;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Roland on 2017/4/10.
 */

public class RunningAccountAdapter extends BaseExpandableListAdapter {

    private Context context;

    private LayoutInflater inflater;

    private Map<String, List<RunningAccount>> datas;

    private List<String> groupNames = new ArrayList<>();

    public RunningAccountAdapter(Context context, Map<String, List<RunningAccount>> datas) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.datas = datas;
        if (datas != null) {
            groupNames.addAll(datas.keySet());
            Collections.sort(groupNames);
        }
    }

    @Override
    public int getGroupCount() {
        return groupNames.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return datas.get(groupNames.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return datas.get(groupNames.get(groupPosition));
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        List<RunningAccount> group = datas.get(groupNames.get(groupPosition));
        return group.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layout_group_running_account, null);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layout_item_running_account, null);
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public void notifyDataSetChanged() {
        if (datas != null) {
            groupNames.addAll(datas.keySet());
            Collections.sort(groupNames);
        }
        super.notifyDataSetChanged();
    }
}
