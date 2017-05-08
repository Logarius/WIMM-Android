package net.oschina.git.roland.wimm.common.entities;

import net.oschina.git.roland.wimm.common.base.BaseDbObj;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * 房租缴纳记录表
 * Created by Roland on 2017/4/24.
 */
@Table(name = "Rental")
public class Rental extends BaseDbObj {

    @Column(name = "id", isId = true)
    private int id;

    /**
     * 租房记录ID
     */
    @Column(name = "apartmentId")
    private String apartmentId = "";

    /**
     * 缴纳时间
     */
    @Column(name = "time")
    private long time = 0;

    /**
     * 缴纳金额
     */
    @Column(name = "amount")
    private double amount = 0;

    public String getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(String apartmentId) {
        this.apartmentId = apartmentId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
