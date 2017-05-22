package net.oschina.git.roland.wimm.main

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.View
import android.widget.Toast

import net.oschina.git.roland.wimm.R
import net.oschina.git.roland.wimm.common.base.BaseActivity
import net.oschina.git.roland.wimm.common.base.WIMMConstants
import net.oschina.git.roland.wimm.common.entities.Account
import net.oschina.git.roland.wimm.common.entities.User
import net.oschina.git.roland.wimm.module.function.FunctionsSwitchUtil

import kotlinx.android.synthetic.main.activity_login.*
import net.oschina.git.roland.androidtoolkit.common.StringUtils

/**
 * Created by Roland on 2017/4/10.
 */
class LoginActivity : BaseActivity(), View.OnClickListener {

    private var sp: SharedPreferences? = null

    override fun getContentViewLayout(): Int {
        return R.layout.activity_login
    }

    override fun initComp() {
        sp = getSharedPreferences(WIMMConstants.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    override fun initListener() {
        btn_register!!.setOnClickListener(this)
        btn_login!!.setOnClickListener(this)
    }

    override fun initData() {
        val userId = sp!!.getString(WIMMConstants.SP_KEY_LAST_LOGIN_USER, "")
        et_userid!!.setText(userId)
        et_userid.setSelection(userId!!.length)
    }

    override fun onClick(v: View) {

        val userId = et_userid!!.text.toString()
        val password = et_password!!.text.toString()

        if (StringUtils.isEmpty(userId)) {
            Toast.makeText(this@LoginActivity, R.string.warning_userid_empty, Toast.LENGTH_SHORT).show()
            return
        } else if (StringUtils.isEmpty(password)) {
            Toast.makeText(this@LoginActivity, R.string.warning_password_empty, Toast.LENGTH_SHORT).show()
            return
        }

        val temp: User?

        when (v.id) {
            R.id.btn_register -> {
                temp = User.findByUserId(userId)
                if (temp != null) {
                    Toast.makeText(this@LoginActivity, R.string.warning_userid_duplicate, Toast.LENGTH_SHORT).show()
                } else {
                    val user = User()
                    user.userId = userId
                    user.password = password
                    user.name = userId
                    user.saveOrUpdate()
                    application.user = user

                    val account = Account()
                    account.userId = userId
                    account.saveOrUpdate()
                    application.account = account

                    FunctionsSwitchUtil.getInstance().enableAllFunctions(userId)

                    toMainActivity()
                }
            }

            R.id.btn_login -> {
                temp = User.findByUserIdPassword(userId, password)
                if (temp == null) {
                    Toast.makeText(this@LoginActivity, R.string.warning_userid_password_error, Toast.LENGTH_SHORT).show()
                } else {
                    application.user = temp
                    application.account = Account.findByUserId(temp.userId)
                    toMainActivity()
                }
            }

            else -> {
            }
        }
    }

    private fun toMainActivity() {
        val editor = sp!!.edit()
        editor.putString(WIMMConstants.SP_KEY_LAST_LOGIN_USER, application.user.userId)
        editor.apply()

        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
