package net.oschina.git.roland.wimm.common.data;

import net.oschina.git.roland.wimm.common.base.BaseDbObj;
import net.oschina.git.roland.wimm.common.base.WIMMApplication;

import org.xutils.DbManager;
import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;
import org.xutils.ex.DbException;
import org.xutils.x;

/**
 * 用户表
 * Created by Roland on 2017/4/10.
 */
@Table(name = "User")
public class User extends BaseDbObj {

    /**
     * 用户名
     */
    @Column(name = "userId", isId = true)
    private String userId = "";

    /**
     * 密码
     */
    @Column(name = "password")
    private String password = "";

    /**
     * 姓名
     */
    @Column(name = "name")
    private String name = "";

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static User findByUserId(String userId) {
        DbManager dbManager = x.getDb(WIMMApplication.getApplication().getDaoConfig());
        User result = null;
        try {
            result = dbManager.selector(User.class).where("userId", "=", userId).findFirst();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static User findByUserIdPassword(String userId, String password) {
        DbManager dbManager = x.getDb(WIMMApplication.getApplication().getDaoConfig());
        User result = null;
        try {
            result = dbManager.selector(User.class)
                    .where("userId", "=", userId)
                    .and("password", "=", password)
                    .findFirst();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return result;
    }
}
