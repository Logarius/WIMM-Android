package net.oschina.git.roland.wimm.common.data;

import net.oschina.git.roland.wimm.common.utils.NumericUtils;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Roland on 2017/4/10.
 */
@Table(name = "Account")
public class Account {

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
}
