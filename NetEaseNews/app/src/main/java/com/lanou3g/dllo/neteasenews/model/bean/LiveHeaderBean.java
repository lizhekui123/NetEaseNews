package com.lanou3g.dllo.neteasenews.model.bean;

/**
 * Created by dllo on 16/9/29.
 * 直播-分类头布局实体类
 */
public class LiveHeaderBean {

    /**
     * type : column
     * visible : false
     * name : TOP100
     * id : 3
     */

    private String type;
    private boolean visible;
    private String name;
    private int id;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
