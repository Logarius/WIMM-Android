package net.oschina.git.roland.wimm.common.data;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.math.BigDecimal;

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
    private BigDecimal amount;

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
}
