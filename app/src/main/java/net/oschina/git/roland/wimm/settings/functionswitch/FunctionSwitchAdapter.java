package net.oschina.git.roland.wimm.settings.functionswitch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.oschina.git.roland.wimm.R;
import net.oschina.git.roland.wimm.module.function.FunctionItem;

import java.util.List;

/**
 * Created by Roland on 2017/4/28.
 */

public class FunctionSwitchAdapter extends BaseAdapter {

    private List<FunctionItem> datas;

    private LayoutInflater inflater;

    private OnFunctionItemSwitchListener listener;

    public FunctionSwitchAdapter(Context context, List<FunctionItem> datas) {
        this.inflater = LayoutInflater.from(context);
        this.datas = datas;
    }

    public void setOnFunctionItemSwitchListener(OnFunctionItemSwitchListener listener) {
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layout_function_switch_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        FunctionItem item = datas.get(position);
        holder.ivIcon.setImageDrawable(item.getIcon());
        holder.tvTitle.setText(item.getTitle());
        holder.ivSwitch.setImageResource(item.isEnable() ? R.drawable.switch_on : R.drawable.switch_off);
        holder.ivSwitch.setTag(position);

        return convertView;
    }

    private View.OnClickListener ivSwitchClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (listener != null) {
                ImageView ivSwitch = (ImageView) v;
                int position = (int) v.getTag();
                FunctionItem functionItem = datas.get(position);
                functionItem.setEnable(!functionItem.isEnable());
                ivSwitch.setImageResource(functionItem.isEnable() ? R.drawable.switch_on : R.drawable.switch_off);
                listener.onSwitch(position);
            }
        }
    };

    private class ViewHolder {
        ImageView ivIcon;
        TextView tvTitle;
        ImageView ivSwitch;

        ViewHolder(View view) {
            this.ivIcon = (ImageView) view.findViewById(R.id.iv_icon);
            this.tvTitle = (TextView) view.findViewById(R.id.tv_title);
            this.ivSwitch = (ImageView) view.findViewById(R.id.iv_switch);

            this.ivSwitch.setOnClickListener(ivSwitchClickListener);
        }
    }
}
