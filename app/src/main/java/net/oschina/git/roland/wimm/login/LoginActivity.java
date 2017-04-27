package net.oschina.git.roland.wimm.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.oschina.git.roland.wimm.R;
import net.oschina.git.roland.wimm.common.base.BaseActivity;
import net.oschina.git.roland.wimm.common.base.WIMMConstants;
import net.oschina.git.roland.wimm.common.data.Account;
import net.oschina.git.roland.wimm.common.data.User;
import net.oschina.git.roland.wimm.common.utils.StringUtils;
import net.oschina.git.roland.wimm.function.FunctionsSwitchUtil;
import net.oschina.git.roland.wimm.main.MainActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

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

    private SharedPreferences sp;

    @Override
    protected void initComp() {
        sp = getSharedPreferences(WIMMConstants.SHARED_PREFERENCE_NAME, MODE_PRIVATE);
    }

    @Override
    protected void initListener() {
        btnRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        String userId = sp.getString(WIMMConstants.SP_KEY_LAST_LOGIN_USER, "");
        etUserId.setText(userId);
        etUserId.setSelection(userId.length());
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

        switch (v.getId()) {
            case R.id.btn_register:
                temp = User.findByUserId(userId);
                if (temp != null) {
                    Toast.makeText(LoginActivity.this, R.string.warning_userid_duplicate, Toast.LENGTH_SHORT).show();
                } else {
                    User user = new User();
                    user.setUserId(userId);
                    user.setPassword(password);
                    user.setName(userId);
                    user.saveOrUpdate();
                    application.setmUser(user);

                    Account account = new Account();
                    account.setUserId(userId);
                    account.saveOrUpdate();
                    application.setmAccount(account);

                    FunctionsSwitchUtil.getInstance().enableAllFunctions(userId);

                    toMainActivity();
                }
                break;

            case R.id.btn_login:
                temp = User.findByUserIdPassword(userId, password);
                if (temp == null) {
                    Toast.makeText(LoginActivity.this, R.string.warning_userid_password_error, Toast.LENGTH_SHORT).show();
                } else {
                    application.setmUser(temp);
                    application.setmAccount(Account.findByUserId(temp.getUserId()));
                    toMainActivity();
                }
                break;

            default:
                break;
        }
    }

    private void toMainActivity() {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(WIMMConstants.SP_KEY_LAST_LOGIN_USER, application.getmUser().getUserId());
        editor.apply();

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
