package net.oschina.git.roland.wimm.common.base;

import android.app.Application;

import net.oschina.git.roland.wimm.common.data.Account;
import net.oschina.git.roland.wimm.common.data.User;

import org.xutils.DbManager;
import org.xutils.x;

/**
 * Created by Roland on 2017/4/10.
 */

public class WIMMApplication extends Application {

    private static WIMMApplication application;

    private User mUser;

    private Account mAccount;

    private DbManager.DaoConfig daoConfig;

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(false);
        application = this;

        daoConfig = new DbManager.DaoConfig()
                .setDbName("wimm.db")
                .setDbVersion(1);
    }

    public static WIMMApplication getApplication() {
        return application;
    }

    public User getmUser() {
        return mUser;
    }

    public void setmUser(User mUser) {
        this.mUser = mUser;
    }

    public Account getmAccount() {
        return mAccount;
    }

    public void setmAccount(Account mAccount) {
        this.mAccount = mAccount;
    }

    public DbManager.DaoConfig getDaoConfig() {
        return daoConfig;
    }
}
