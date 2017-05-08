package net.oschina.git.roland.wimm.common.base;

import android.app.Application;

import net.oschina.git.roland.wimm.common.entities.Account;
import net.oschina.git.roland.wimm.common.entities.User;

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

    public User getUser() {
        return mUser;
    }

    public void setUser(User mUser) {
        this.mUser = mUser;
    }

    public Account getAccount() {
        return mAccount;
    }

    public void setAccount(Account mAccount) {
        this.mAccount = mAccount;
    }

    public DbManager.DaoConfig getDaoConfig() {
        return daoConfig;
    }
}
