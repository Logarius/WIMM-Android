package net.oschina.git.roland.wimm.common.entities;

import net.oschina.git.roland.wimm.common.base.BaseDbObj;
import net.oschina.git.roland.wimm.common.base.WIMMApplication;

import org.xutils.DbManager;
import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.List;

/**
 * 用户功能开关记录表
 * Created by Roland on 2017/4/27.
 */
@Table(name = "Function")
public class Function extends BaseDbObj {

    @Column(name = "id", isId = true)
    private int id;

    @Column(name = "userId")
    private String userId = "";

    @Column(name = "functionName")
    private String functionName = "";

    @Column(name = "enable")
    private boolean enable = false;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public static List<Function> findBy(String userId) {
        DbManager db = x.getDb(WIMMApplication.getApplication().getDaoConfig());
        List<Function> result = null;

        try {
            result = db.selector(Function.class).where("userId", "=", userId).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static Function findBy(String userId, String functionName) {
        DbManager db = x.getDb(WIMMApplication.getApplication().getDaoConfig());
        Function result = null;

        try {
            result = db.selector(Function.class)
                    .where("userId", "=", userId)
                    .and("functionName", "=", functionName)
                    .findFirst();
        } catch (DbException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void deleteByUserId(String userId) {
        DbManager db = x.getDb(WIMMApplication.getApplication().getDaoConfig());

        try {
            db.delete(Function.class, WhereBuilder.b("userId", "=", userId));
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
}
