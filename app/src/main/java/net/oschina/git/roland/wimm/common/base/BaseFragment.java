package net.oschina.git.roland.wimm.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Roland on 2017/4/26.
 */

public abstract class BaseFragment extends Fragment {

    protected View contentView;

    protected abstract int getContentViewLayout();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = inflater.inflate(getContentViewLayout(), container, false);
        initComp();
        initListener();
        initData();
        return contentView;
    }

    protected abstract void initComp();

    protected abstract void initListener();

    protected abstract void initData();

    protected View findViewById(int id) {
        return contentView.findViewById(id);
    }
}
