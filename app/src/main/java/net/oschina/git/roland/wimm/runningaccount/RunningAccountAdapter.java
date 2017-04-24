package net.oschina.git.roland.wimm.runningaccount;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import net.oschina.git.roland.wimm.R;
import net.oschina.git.roland.wimm.common.base.WIMMConstants;
import net.oschina.git.roland.wimm.common.data.RunningAccount;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Roland on 2017/4/10.
 */

class RunningAccountAdapter extends BaseExpandableListAdapter {

    private Context context;

    private LayoutInflater inflater;

    private Map<String, List<RunningAccount>> datas;

    private List<String> groupNames = new ArrayList<>();

    private String dateToday = null;

    RunningAccountAdapter(Context context, Map<String, List<RunningAccount>> datas) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.datas = datas;
        if (datas != null) {
            groupNames.addAll(datas.keySet());
            sortGroupNames();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(WIMMConstants.RUNNING_ACCOUNT_DATE_FORMAT, Locale.US);
        dateToday = simpleDateFormat.format(new Date());
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
        GroupViewHolder groupViewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layout_group_running_account, null);
            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }

        if (dateToday.equals(groupNames.get(groupPosition))) {
            groupViewHolder.tvDate.setText(R.string.str_today);
        } else {
            groupViewHolder.tvDate.setText(groupNames.get(groupPosition));
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layout_item_running_account, null);
            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }

        RunningAccount item = (RunningAccount) getChild(groupPosition, childPosition);

        childViewHolder.tvAmount.setTextColor(context.getResources().getColor(item.getAmount() < 0 ? R.color.red : R.color.green));

        childViewHolder.tvAmount.setText(String.valueOf(item.getAmount()));
        childViewHolder.tvRemark.setText(item.getRemark());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public void notifyDataSetChanged() {
        groupNames.clear();
        if (datas != null) {
            groupNames.addAll(datas.keySet());
            sortGroupNames();
        }
        super.notifyDataSetChanged();
    }

    private class GroupViewHolder {
        TextView tvDate;

        GroupViewHolder(View v) {
            this.tvDate = (TextView) v.findViewById(R.id.tv_date);
        }
    }

    private class ChildViewHolder {
        TextView tvAmount;
        TextView tvRemark;

        ChildViewHolder(View v) {
            this.tvAmount = (TextView) v.findViewById(R.id.tv_amount);
            this.tvRemark = (TextView) v.findViewById(R.id.tv_remark);
        }
    }

    private void sortGroupNames() {
        Collections.sort(groupNames, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
    }
}
