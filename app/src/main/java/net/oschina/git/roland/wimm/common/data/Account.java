package net.oschina.git.roland.wimm.common.data;

import net.oschina.git.roland.wimm.common.base.BaseDbObj;
import net.oschina.git.roland.wimm.common.base.WIMMApplication;
import net.oschina.git.roland.wimm.common.utils.NumericUtils;

import org.xutils.DbManager;
import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;
import org.xutils.ex.DbException;
import org.xutils.x;

/**
 * Created by Roland on 2017/4/10.
 */
@Table(name = "Account")
public class Account extends BaseDbObj {

    @Column(name = "id", isId = true)
    private int id;

    @Column(name = "userId")
    private String userId = "";

    @Column(name = "amount")
    private double amount = 0;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return NumericUtils.getRoundDoubleValue(amount, 2);
    }

    public void setAmount(double amount) {
        this.amount = NumericUtils.getRoundDoubleValue(amount, 2);
    }

    public void add(double amount) {
        this.amount += amount;
    }

    public static Account findByUserId(String userId) {
        DbManager dbManager = x.getDb(WIMMApplication.getApplication().getDaoConfig());
        Account result = null;
        try {
            result = dbManager.selector(Account.class).where("userId", "=", userId).findFirst();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return result;
    }
}
