package net.oschina.git.roland.wimm.common.data;

import net.oschina.git.roland.wimm.common.base.BaseDbObj;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * 租房记录表
 * Created by Roland on 2017/4/24.
 */
@Table(name = "Apartment")
public class Apartment extends BaseDbObj {

    /**
     * 租房记录ID
     */
    @Column(name = "apartmentId", isId = true)
    private String apartmentId = "";

    /**
     * 地址
     */
    @Column(name = "address")
    private String address = "";

    /**
     * 入住时间
     */
    @Column(name = "rentDateStart")
    private long rentDateStart = 0;

    /**
     * 搬出时间
     */
    @Column(name = "rentDateEnd")
    private long rentDateEnd = 0;

    /**
     * 月租金
     */
    @Column(name = "rental")
    private double rental = 0;

    /**
     * 押金
     */
    @Column(name = "deposit")
    private double deposit = 0;

    /**
     * 交租间隔(月)
     */
    @Column(name = "interval")
    private int interval = 1;

    public String getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(String apartmentId) {
        this.apartmentId = apartmentId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getRentDateStart() {
        return rentDateStart;
    }

    public void setRentDateStart(long rentDateStart) {
        this.rentDateStart = rentDateStart;
    }

    public long getRentDateEnd() {
        return rentDateEnd;
    }

    public void setRentDateEnd(long rentDateEnd) {
        this.rentDateEnd = rentDateEnd;
    }

    public double getRental() {
        return rental;
    }

    public void setRental(double rental) {
        this.rental = rental;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }
}
