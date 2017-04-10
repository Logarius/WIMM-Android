package net.oschina.git.roland.wimm.common.data;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Roland on 2017/4/10.
 */
@Table(name = "User")
public class User {

    @Column(name = "userId", isId = true)
    private String userId = "";

    @Column(name = "password")
    private String password = "";

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
}
