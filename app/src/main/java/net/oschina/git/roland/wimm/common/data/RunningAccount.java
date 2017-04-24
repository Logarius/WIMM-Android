package net.oschina.git.roland.wimm.common.data;

import net.oschina.git.roland.wimm.common.base.BaseDbObj;
import net.oschina.git.roland.wimm.common.base.WIMMApplication;
import net.oschina.git.roland.wimm.common.utils.NumericUtils;

import org.xutils.DbManager;
import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.List;

/**
 * 流水记录表
 * Created by Roland on 2017/4/10.
 */
@Table(name = "RunningAccount")
public class RunningAccount extends BaseDbObj {

    @Column(name = "id", isId = true)
    private int id;

    /**
     * 用户ID
     */
    @Column(name = "userId")
    private String userId = "";

    /**
     * 时间戳
     */
    @Column(name = "timeStamp")
    private long timeStamp = 0;

    /**
     * 收支
     */
    @Column(name = "amount")
    private double amount = 0;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark = "";

    public int getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public double getAmount() {
        return NumericUtils.getRoundDoubleValue(amount, 2);
    }

    public void setAmount(double amount) {
        this.amount = NumericUtils.getRoundDoubleValue(amount, 2);
    }

    public static List<RunningAccount> findByUserId(String userId) {
        DbManager dbManager = x.getDb(WIMMApplication.getApplication().getDaoConfig());
        List<RunningAccount> result = null;

        try {
            result = dbManager
                    .selector(RunningAccount.class)
                    .where("userId", "=", userId)
                    .orderBy("timeStamp", true)
                    .findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }

        return result;
    }
}
