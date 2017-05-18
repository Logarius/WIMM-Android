package net.oschina.git.roland.wimm.main

import android.app.AlertDialog
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentManager
import android.util.SparseArray
import android.view.KeyEvent

import net.oschina.git.roland.wimm.R
import net.oschina.git.roland.wimm.common.base.BaseActivity
import net.oschina.git.roland.wimm.common.view.HeaderFragment
import net.oschina.git.roland.wimm.module.function.FunctionsFragment
import net.oschina.git.roland.wimm.module.runningaccount.RunningAccountFragment
import net.oschina.git.roland.wimm.settings.SettingsFragment
import net.oschina.git.roland.wimm.module.statistics.StatisticsFragment

import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Roland on 2017/4/10.
 */
class MainActivity : BaseActivity() {
    private var fragments: SparseArray<HeaderFragment>? = null

    private var fragmentManager: FragmentManager? = null

    private var displayingFragment: HeaderFragment? = null

    override fun getContentViewLayout(): Int {
        return R.layout.activity_main
    }

    override fun initComp() {
        fragments = SparseArray<HeaderFragment>()
        fragments!!.put(R.id.home, StatisticsFragment().setHeader(header))
        fragments!!.put(R.id.running_account, RunningAccountFragment().setHeader(header))
        fragments!!.put(R.id.functions, FunctionsFragment().setHeader(header))
        fragments!!.put(R.id.settings, SettingsFragment().setHeader(header))

        navigation!!.itemIconTintList = null
        fragmentManager = supportFragmentManager

        showFragment(R.id.home)
    }

    override fun initListener() {
        navigation!!.setOnNavigationItemSelectedListener(navigationItemSelectedListener)
    }

    override fun initData() {

    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            confirmExit()
            return true
        }
        return super.onKeyUp(keyCode, event)
    }

    private fun confirmExit() {
        val dialog = AlertDialog.Builder(this)
        dialog.setMessage(R.string.confirm_exit)
        dialog.setNegativeButton(R.string.str_cancel, null)
        dialog.setPositiveButton(R.string.str_confirm) { dialog, which -> finish() }
        dialog.show()
    }

    private val navigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        showFragment(item.itemId)
        true
    }

    private fun showFragment(menuId: Int) {
        displayingFragment = fragments!!.get(menuId)
        if (displayingFragment != null) {
            fragmentManager!!.beginTransaction().replace(R.id.content, displayingFragment).commit()
            displayingFragment!!.refreshHeader()
        }
    }

}
