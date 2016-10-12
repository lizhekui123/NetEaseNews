package com.lanou3g.dllo.neteasenews.model.db;

import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by dllo on 16/10/11.
 * 用户信息实体类
 */
@Table("user")
public class UserInfoBean {
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;

    @NotNull
    private boolean hasLogin;

    @NotNull
    private String username;

    @NotNull
    private String iconUrl;

    public UserInfoBean() {
    }

    public UserInfoBean(int id, boolean hasLogin, String username, String iconUrl) {
        this.id = id;
        this.hasLogin = hasLogin;
        this.username = username;
        this.iconUrl = iconUrl;
    }

    public UserInfoBean(boolean hasLogin, String username, String iconUrl) {
        this.hasLogin = hasLogin;
        this.username = username;
        this.iconUrl = iconUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isHasLogin() {
        return hasLogin;
    }

    public void setHasLogin(boolean hasLogin) {
        this.hasLogin = hasLogin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
