package net.oschina.git.roland.wimm.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.oschina.git.roland.wimm.R;

/**
 * Created by Roland on 2017/4/24.
 */

public class CommonHeader extends RelativeLayout {

    private View contentView;

    private TextView tvLeftFunc, tvTitle, tvRightFunc;

    public CommonHeader(Context context) {
        super(context);
        initComp(context);
    }

    public CommonHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        initComp(context);
    }

    public CommonHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initComp(context);
    }

    private void initComp(Context context) {
        contentView = View.inflate(context, R.layout.layout_common_header, this);
        tvLeftFunc = (TextView) contentView.findViewById(R.id.tv_leftFunc);
        tvTitle = (TextView) contentView.findViewById(R.id.tv_title);
        tvRightFunc = (TextView) contentView.findViewById(R.id.tv_rightFunc);
    }

    public TextView getLeftFun() {
        return tvLeftFunc;
    }

    public TextView getRightFunc() {
        return tvRightFunc;
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void setTitle(int titleId) {
        tvTitle.setText(titleId);
    }
}
