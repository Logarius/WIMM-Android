package net.oschina.git.roland.wimm.common.base;

import android.app.Application;

import net.oschina.git.roland.wimm.common.data.User;

import org.xutils.DbManager;
import org.xutils.x;

/**
 * Created by Roland on 2017/4/10.
 */

public class WIMMApplication extends Application {

    private static WIMMApplication application;

    private User mUser;

    private DbManager.DaoConfig userDao;

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(false);
        application = this;

        userDao = new DbManager.DaoConfig()
                .setDbName("user.db")
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

    public DbManager.DaoConfig getUserDao() {
        return userDao;
    }
}
