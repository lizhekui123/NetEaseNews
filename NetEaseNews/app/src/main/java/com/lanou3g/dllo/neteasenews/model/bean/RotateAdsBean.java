package com.lanou3g.dllo.neteasenews.model.bean;

/**
 * Created by dllo on 16/10/8.
 */
public class RotateAdsBean {

    private String docid;
    private String title;
    private String tag;
    private String imgsrc;
    private String subtitle;
    private String url;

    public RotateAdsBean() {
    }

    public RotateAdsBean(String docid, String title, String tag, String imgsrc, String subtitle, String url) {
        this.docid = docid;
        this.title = title;
        this.tag = tag;
        this.imgsrc = imgsrc;
        this.subtitle = subtitle;
        this.url = url;
    }

    public RotateAdsBean(String title, String imgsrc, String url) {
        this.title = title;
        this.imgsrc = imgsrc;
        this.url = url;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
