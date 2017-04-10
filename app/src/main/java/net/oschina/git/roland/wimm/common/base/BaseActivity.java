package net.oschina.git.roland.wimm.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.xutils.x;

/**
 * Created by Roland on 2017/4/10.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        initComp();
        initListener();
        initData();
    }

    protected abstract void initComp();

    protected abstract void initListener();

    protected abstract void initData();
}
