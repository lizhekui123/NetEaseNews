package com.lanou3g.dllo.neteasenews.tools;

/**
 * Created by dllo on 16/9/28.
 * 网址拼接工具类
 */
public class UrlTool {
    /**
     * 用于拼接直播-分类页面的各个网址
     * @param id 根据头布局的按钮获取的id
     * @param page 当前页数,上拉加载时换页
     * @return 拼接的网址
     */
    public static String doLiveClassifyUrl (int id,int page) {
        String str  = "http://data.live.126.net/livechannel/classify/" + id + "/" + page + ".json";
        return str;
    }
}
