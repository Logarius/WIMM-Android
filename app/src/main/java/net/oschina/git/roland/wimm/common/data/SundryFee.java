package net.oschina.git.roland.wimm.common.data;

import net.oschina.git.roland.wimm.common.base.BaseDbObj;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * 杂项费用表
 * （水电燃气等）
 * Created by Roland on 2017/4/24.
 */
@Table(name = "SundryFee")
public class SundryFee extends BaseDbObj {

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
     * 项目名称
     */
    @Column(name = "name")
    private String name = "";

    /**
     * 上次读数
     */
    @Column(name = "lastReading")
    private double lastReading = 0;

    /**
     * 读数
     */
    @Column(name = "reading")
    private double reading = 0;

    /**
     * 费用
     */
    @Column(name = "fee")
    private double fee = 0;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLastReading() {
        return lastReading;
    }

    public void setLastReading(double lastReading) {
        this.lastReading = lastReading;
    }

    public double getReading() {
        return reading;
    }

    public void setReading(double reading) {
        this.reading = reading;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }
}
