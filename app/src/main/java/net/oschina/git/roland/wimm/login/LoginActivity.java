package net.oschina.git.roland.wimm.login;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.oschina.git.roland.wimm.R;
import net.oschina.git.roland.wimm.common.base.BaseActivity;
import net.oschina.git.roland.wimm.common.data.User;
import net.oschina.git.roland.wimm.common.utils.StringUtils;
import net.oschina.git.roland.wimm.main.MainActivity;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by Roland on 2017/4/10.
 */
@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    @ViewInject(R.id.et_userid)
    private EditText etUserId;

    @ViewInject(R.id.et_password)
    private EditText etPassword;

    @ViewInject(R.id.btn_register)
    private Button btnRegister;

    @ViewInject(R.id.btn_login)
    private Button btnLogin;

    private DbManager dbManager;

    @Override
    protected void initComp() {
        dbManager = x.getDb(application.getUserDao());
    }

    @Override
    protected void initListener() {
        btnRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {

        String userId = etUserId.getText().toString();
        String password = etPassword.getText().toString();

        if (StringUtils.isEmpty(userId)) {
            Toast.makeText(LoginActivity.this, R.string.warning_userid_empty, Toast.LENGTH_SHORT).show();
            return;
        } else if (StringUtils.isEmpty(password)) {
            Toast.makeText(LoginActivity.this, R.string.warning_password_empty, Toast.LENGTH_SHORT).show();
            return;
        }

        User temp;

        try {
            switch (v.getId()) {
                case R.id.btn_register:
                    temp = dbManager.selector(User.class).where("userId", "=", userId).findFirst();
                    if (temp != null) {
                        Toast.makeText(LoginActivity.this, R.string.warning_userid_duplicate, Toast.LENGTH_SHORT).show();
                    } else {
                        User user = new User();
                        user.setUserId(userId);
                        user.setPassword(password);
                        user.setName(userId);
                        dbManager.saveOrUpdate(user);
                        application.setmUser(user);
                        toMainActivity();
                    }
                    break;

                case R.id.btn_login:
                    temp = dbManager.selector(User.class)
                            .where("userId", "=", userId)
                            .and("password", "=", password)
                            .findFirst();
                    if (temp == null) {
                        Toast.makeText(LoginActivity.this, R.string.warning_userid_password_error, Toast.LENGTH_SHORT).show();
                    } else {
                        application.setmUser(temp);
                        toMainActivity();
                    }
                    break;

                default:
                    break;
            }

        } catch (DbException e) {
            e.printStackTrace();
        }

    }

    private void toMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
