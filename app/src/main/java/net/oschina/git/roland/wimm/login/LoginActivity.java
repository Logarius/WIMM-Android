package net.oschina.git.roland.wimm.login;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import net.oschina.git.roland.wimm.main.MainActivity;
import net.oschina.git.roland.wimm.R;
import net.oschina.git.roland.wimm.common.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by Roland on 2017/4/10.
 */
@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity {

    @ViewInject(R.id.btnLogin)
    private Button btnLogin;

    @Override
    protected void initComp() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Event(type = View.OnClickListener.class, value = R.id.btnLogin)
    private void login(View v) {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
