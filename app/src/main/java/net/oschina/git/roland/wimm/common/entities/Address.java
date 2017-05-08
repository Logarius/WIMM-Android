package net.oschina.git.roland.wimm.common.entities;

import net.oschina.git.roland.wimm.common.base.BaseDbObj;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * 地址记录表
 * Created by Roland on 2017/4/25.
 */
@Table(name = "Address")
public class Address extends BaseDbObj {

    @Column(name = "id", isId = true)
    private int id;

    /**
     * 用户ID
     */
    @Column(name = "userId")
    private String userId = "";

    /**
     * 住房地址
     */
    @Column(name = "address")
    private String address = "";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
