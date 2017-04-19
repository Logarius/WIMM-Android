package net.oschina.git.roland.wimm.main;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import net.oschina.git.roland.wimm.R;
import net.oschina.git.roland.wimm.common.base.BaseActivity;
import net.oschina.git.roland.wimm.login.LoginActivity;

import org.xutils.view.annotation.ContentView;

/**
 * Created by Roland on 2017/4/10.
 */
@ContentView(R.layout.activity_welcome)
public class WelcomeActivity extends BaseActivity {

    private Handler welcomeHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
    });

    @Override
    protected void initComp() {
        welcomeHandler.sendEmptyMessageDelayed(0, 1000);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }
}
