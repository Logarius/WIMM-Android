package net.oschina.git.roland.wimm.common.data;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.math.BigDecimal;

/**
 * Created by Roland on 2017/4/10.
 */
@Table(name = "RunningAccount")
public class RunningAccount {

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
    private BigDecimal amount;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    public int getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
}
