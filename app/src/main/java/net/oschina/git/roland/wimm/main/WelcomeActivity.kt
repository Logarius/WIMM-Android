package net.oschina.git.roland.wimm.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler

import net.oschina.git.roland.wimm.R
import net.oschina.git.roland.wimm.common.base.BaseActivity

/**
 * Created by Roland on 2017/4/10.
 */
class WelcomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        kotlin = true
        super.onCreate(savedInstanceState)
    }

    private val welcomeHandler = Handler(Handler.Callback {
        val intent = Intent(this@WelcomeActivity, LoginActivity::class.java)
        startActivity(intent)
        finish()
        true
    })

    override fun getContentViewLayout(): Int {
        return R.layout.activity_welcome
    }

    override fun initComp() {
        welcomeHandler.sendEmptyMessageDelayed(0, 1000)
    }

    override fun initListener() {

    }

    override fun initData() {

    }
}
