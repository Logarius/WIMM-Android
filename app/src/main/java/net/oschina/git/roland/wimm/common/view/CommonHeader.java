package net.oschina.git.roland.wimm.common.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.oschina.git.roland.wimm.R;

/**
 * Created by Roland on 2017/4/24.
 */

public class CommonHeader extends RelativeLayout implements View.OnClickListener {

    public static final int VIEW_IV_LEFT_FUNC = 0;

    public static final int VIEW_TV_LEFT_FUNC = 1;

    public static final int VIEW_TV_RIGHT_FUNC = 2;

    public static final int VIEW_IV_RIGHT_FUNC = 3;

    private View contentView;

    private TextView tvLeftFunc, tvTitle, tvRightFunc;

    private ImageView ivLeftFunc, ivRightFunc;

    private CommonHeaderListener commonHeaderListener;

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
        ivLeftFunc = (ImageView) contentView.findViewById(R.id.iv_leftFunc);
        ivRightFunc = (ImageView) contentView.findViewById(R.id.iv_rightFunc);

        tvLeftFunc.setOnClickListener(this);
        tvRightFunc.setOnClickListener(this);
        ivLeftFunc.setOnClickListener(this);
        ivRightFunc.setOnClickListener(this);
    }

    public void reset() {
        tvLeftFunc.setVisibility(GONE);
        tvRightFunc.setVisibility(GONE);
        ivLeftFunc.setVisibility(GONE);
        ivRightFunc.setVisibility(GONE);
        tvTitle.setText(null);
        commonHeaderListener = null;
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void setTitle(int titleId) {
        tvTitle.setText(titleId);
    }

    public void setLeftFuncText(String text) {
        tvLeftFunc.setVisibility(VISIBLE);
        tvLeftFunc.setText(text);
    }

    public void setLeftFuncText(int text) {
        tvLeftFunc.setVisibility(VISIBLE);
        tvLeftFunc.setText(text);
    }

    public void setLeftFuncIcon(Drawable icon) {
        ivLeftFunc.setVisibility(VISIBLE);
        ivLeftFunc.setImageDrawable(icon);
    }

    public void setLeftFuncIcon(int icon) {
        ivLeftFunc.setVisibility(VISIBLE);
        ivLeftFunc.setImageResource(icon);
    }

    public void setRightFuncText(String text) {
        tvRightFunc.setVisibility(VISIBLE);
        tvRightFunc.setText(text);
    }

    public void setRightFuncText(int text) {
        tvRightFunc.setVisibility(VISIBLE);
        tvRightFunc.setText(text);
    }

    public void setRightFuncText(Drawable icon) {
        ivRightFunc.setVisibility(VISIBLE);
        ivRightFunc.setImageDrawable(icon);
    }

    public void setRightFuncIcon(int icon) {
        ivRightFunc.setVisibility(VISIBLE);
        ivRightFunc.setImageResource(icon);
    }

    public void setCommonHeaderListener(CommonHeaderListener listener) {
        commonHeaderListener = listener;
    }

    @Override
    public void onClick(View v) {
        int viewId = -1;

        switch (v.getId()) {
            case R.id.iv_leftFunc:
                viewId = VIEW_IV_LEFT_FUNC;
                break;

            case R.id.tv_leftFunc:
                viewId = VIEW_TV_LEFT_FUNC;
                break;

            case R.id.tv_rightFunc:
                viewId = VIEW_TV_RIGHT_FUNC;
                break;

            case R.id.iv_rightFunc:
                viewId = VIEW_IV_RIGHT_FUNC;
                break;

            default:
                break;
        }

        if (viewId >= 0 && commonHeaderListener != null) {
            commonHeaderListener.onClick(viewId);
        }
    }

    public interface CommonHeaderListener {
        void onClick(int viewId);
    }
}
