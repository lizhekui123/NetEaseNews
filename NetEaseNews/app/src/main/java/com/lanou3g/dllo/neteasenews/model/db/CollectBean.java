package com.lanou3g.dllo.neteasenews.model.db;

import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

import java.io.Serializable;

/**
 * Created by dllo on 16/10/11.
 * 收藏的实体类
 */
@Table("collect")
public class CollectBean implements Serializable{

    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;

    @NotNull
    private String username;

    @NotNull
    private String title;

    private String content;

    @NotNull
    private String shareUrl;

    @NotNull
    private String imgUrl;

    @NotNull
    private String fromUrl;

    @NotNull
    private String url;

    @NotNull
    private String urlType;

    /**
     * userUrl: 用户名加url,用于解决不同用户关注相同内容,但本人只会按一个条件删除的问题
     */
    @NotNull
    private String userUrl;

    public CollectBean() {
    }

    public CollectBean(int id, String username, String title, String content, String shareUrl, String imgUrl, String fromUrl, String url, String urlType, String userUrl) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.content = content;
        this.shareUrl = shareUrl;
        this.imgUrl = imgUrl;
        this.fromUrl = fromUrl;
        this.url = url;
        this.urlType = urlType;
        this.userUrl = userUrl;
    }

    public CollectBean(String username, String title, String content, String shareUrl, String imgUrl, String fromUrl, String url, String urlType, String userUrl) {
        this.username = username;
        this.title = title;
        this.content = content;
        this.shareUrl = shareUrl;
        this.imgUrl = imgUrl;
        this.fromUrl = fromUrl;
        this.url = url;
        this.urlType = urlType;
        this.userUrl = userUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getFromUrl() {
        return fromUrl;
    }

    public void setFromUrl(String fromUrl) {
        this.fromUrl = fromUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlType() {
        return urlType;
    }

    public void setUrlType(String urlType) {
        this.urlType = urlType;
    }

    public String getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }
}
