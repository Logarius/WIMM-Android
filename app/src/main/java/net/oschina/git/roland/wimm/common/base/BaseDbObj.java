package net.oschina.git.roland.wimm.common.base;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

/**
 * Created by Roland on 2017/4/10.
 */

public class BaseDbObj {

    protected DbManager dbManager = x.getDb(WIMMApplication.getApplication().getDaoConfig());

    public void saveOrUpdate() {
        try {
            dbManager.saveOrUpdate(this);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public void deleteFromDb() {
        try {
            dbManager.delete(this);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
}
