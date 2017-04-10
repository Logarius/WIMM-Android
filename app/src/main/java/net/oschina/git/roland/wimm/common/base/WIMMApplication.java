package net.oschina.git.roland.wimm.common.base;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Roland on 2017/4/10.
 */

public class WIMMApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(false);
    }
}
