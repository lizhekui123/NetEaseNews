package com.lanou3g.dllo.neteasenews.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lanou3g.dllo.neteasenews.R;
import com.lanou3g.dllo.neteasenews.model.bean.NewsBean;
import com.lanou3g.dllo.neteasenews.model.bean.NewsInfoBean;
import com.lanou3g.dllo.neteasenews.model.db.CollectBean;
import com.lanou3g.dllo.neteasenews.model.db.LiteOrmInstance;
import com.lanou3g.dllo.neteasenews.model.db.UserInfoBean;
import com.lanou3g.dllo.neteasenews.model.net.UrlValues;
import com.lanou3g.dllo.neteasenews.model.net.VolleyInstance;
import com.lanou3g.dllo.neteasenews.model.net.VolleyResult;
import com.lanou3g.dllo.neteasenews.tools.ShareTool;
import com.lanou3g.dllo.neteasenews.tools.ToastTool;

import java.util.List;

/**
 * Created by dllo on 16/10/9.
 * 文字新闻页面
 */
public class NewsInfoActivity extends AbsBaseActivity{

    private ImageView backIv;
    private ImageView moreIv;
    private WebView webView;
    private WebSettings webSettings;

    private String username;
    private String title;
    private String fromUrl;
    private String url;
    private String userUrl;
    private String content,shareUrl,imgUrl;

    private PopupWindow window;
    private LinearLayout shareLl;
    private LinearLayout collectLl;
    private ImageView collectIv;
    private TextView collectTv;

    private boolean hasLogin;
    private boolean hasCollected;

    @Override
    protected int setLayout() {
        return R.layout.activity_news_info;
    }

    @Override
    protected void initViews() {
        backIv = byView(R.id.news_info_back_iv);
        moreIv = byView(R.id.news_info_more_iv);
        webView = byView(R.id.news_info_wv);
    }

    @Override
    protected void initDatas() {
        Intent intent = getIntent();
        if (intent.getExtras().containsKey("bean")){
            fromUrl = intent.getStringExtra("url");
            Bundle bundle = intent.getExtras();
            if (fromUrl.equals(UrlValues.HEADLINENEWSURL)) {
                NewsBean.T1348647909107Bean bean = (NewsBean.T1348647909107Bean) bundle.getSerializable("bean");
                title = bean.getTitle();
                if(!bean.getDigest().isEmpty()){
                    content = bean.getDigest();
                } else {
                    content = "来自网易新闻";
                }
                shareUrl = "http://c.m.163.com/news/a/" + bean.getDocid() + ".html?spss=newsapp&spsw=1";
                imgUrl = bean.getImgsrc();
                url = "http://c.3g.163.com/nc/article/"+ bean.getDocid() + "/full.html";
            } else if (fromUrl.equals(UrlValues.SELECTEDNEWSURL)) {
                NewsBean.T1467284926140Bean bean = (NewsBean.T1467284926140Bean) bundle.getSerializable("bean");
                title = bean.getTitle();
                if(!bean.getDigest().isEmpty()){
                    content = bean.getDigest();
                } else {
                    content = "来自网易新闻";
                }
                shareUrl = "http://c.m.163.com/news/a/" + bean.getDocid() + ".html?spss=newsapp&spsw=1";
                imgUrl = bean.getImgsrc();
                url = bean.getUrl_3w();
            } else if (fromUrl.equals(UrlValues.ENTERTAINMENTNEWSURL)) {
                NewsBean.T1348648517839Bean bean = (NewsBean.T1348648517839Bean) bundle.getSerializable("bean");
                title = bean.getTitle();
                if(!bean.getDigest().isEmpty()){
                    content = bean.getDigest();
                } else {
                    content = "来自网易新闻";
                }
                shareUrl = "http://c.m.163.com/news/a/" + bean.getDocid() + ".html?spss=newsapp&spsw=1";
                imgUrl = bean.getImgsrc();
                url = bean.getUrl_3w();
            } else if (fromUrl.equals(UrlValues.SPORTSNEWSURL)) {
                NewsBean.T1348649079062Bean bean = (NewsBean.T1348649079062Bean) bundle.getSerializable("bean");
                title = bean.getTitle();
                if(!bean.getDigest().isEmpty()){
                    content = bean.getDigest();
                } else {
                    content = "来自网易新闻";
                }
                shareUrl = "http://c.m.163.com/news/a/" + bean.getDocid() + ".html?spss=newsapp&spsw=1";
                imgUrl = bean.getImgsrc();
                url = bean.getUrl_3w();
            } else if (fromUrl.equals(UrlValues.FINANCIALNEWSURL)) {
                NewsBean.T1348648756099Bean bean = (NewsBean.T1348648756099Bean) bundle.getSerializable("bean");
                title = bean.getTitle();
                if(!bean.getDigest().isEmpty()){
                    content = bean.getDigest();
                } else {
                    content = "来自网易新闻";
                }
                shareUrl = "http://c.m.163.com/news/a/" + bean.getDocid() + ".html?spss=newsapp&spsw=1";
                imgUrl = bean.getImgsrc();
                url = bean.getUrl_3w();
            } else if (fromUrl.equals(UrlValues.TECHNEWSURL)) {
                NewsBean.T1348649580692Bean bean = (NewsBean.T1348649580692Bean) bundle.getSerializable("bean");
                title = bean.getTitle();
                if(!bean.getDigest().isEmpty()){
                    content = bean.getDigest();
                } else {
                    content = "来自网易新闻";
                }
                shareUrl = "http://c.m.163.com/news/a/" + bean.getDocid() + ".html?spss=newsapp&spsw=1";
                imgUrl = bean.getImgsrc();
                url = bean.getUrl_3w();
            } else if (fromUrl.equals(UrlValues.AUTONEWSURL)) {
                NewsBean.ListBean bean = (NewsBean.ListBean) bundle.getSerializable("bean");
                title = bean.getTitle();
                if(!bean.getDigest().isEmpty()){
                    content = bean.getDigest();
                } else {
                    content = "来自网易新闻";
                }
                shareUrl = "http://c.m.163.com/news/a/" + bean.getDocid() + ".html?spss=newsapp&spsw=1";
                imgUrl = bean.getImgsrc();
                url = bean.getUrl_3w();
            } else if (fromUrl.equals(UrlValues.FASHIONNEWSURL)) {
                NewsBean.T1348650593803Bean bean = (NewsBean.T1348650593803Bean) bundle.getSerializable("bean");
                title = bean.getTitle();
                if(!bean.getDigest().isEmpty()){
                    content = bean.getDigest();
                } else {
                    content = "来自网易新闻";
                }
                shareUrl = "http://c.m.163.com/news/a/" + bean.getDocid() + ".html?spss=newsapp&spsw=1";
                imgUrl = bean.getImgsrc();
                url = bean.getUrl_3w();
            } else {
                NewsBean.T1348647909107Bean bean = (NewsBean.T1348647909107Bean) bundle.getSerializable("bean");
                title = bean.getTitle();
                if(!bean.getDigest().isEmpty()){
                    content = bean.getDigest();
                } else {
                    content = "来自网易新闻";
                }
                shareUrl = "http://c.m.163.com/news/a/" + bean.getDocid() + ".html?spss=newsapp&spsw=1";
                imgUrl = bean.getImgsrc();
                url = "http://c.3g.163.com/nc/article/"+ bean.getDocid() + "/full.html";
            }
        } else  if (intent.getExtras().containsKey("collectBean")){
            Bundle bundle = intent.getExtras();
            CollectBean cb = (CollectBean) bundle.getSerializable("collectBean");
            Log.d("NewsInfoActivity", "cb:" + cb);
            title = cb.getTitle();
            content = cb.getContent();
            shareUrl = cb.getShareUrl();
            fromUrl = cb.getFromUrl();
            imgUrl = cb.getImgUrl();
            url = cb.getUrl();
        }
        if (fromUrl.equals(UrlValues.HEADLINENEWSURL)){
            VolleyInstance.getInstance().startRequest(url, new VolleyResult() {
                @Override
                public void success(String resultStr) {
                    String docId = url.replace("http://c.3g.163.com/nc/article/", "").replace("/full.html", "");
                    resultStr = resultStr.replace(docId,"id");
                    Gson gson = new Gson();
                    NewsInfoBean newsInfoBean = gson.fromJson(resultStr,NewsInfoBean.class);
                    webView.loadData(newsInfoBean.getId().getBody(),"text/html; charset=UTF-8",null);
                }

                @Override
                public void failure() {
                    ToastTool.shortMsg("请求失败");
                    finish();
                }
            });
        } else {
            webView.loadUrl(url);
            webSettings = webView.getSettings();
            webSettings.setDefaultTextEncodingName("GBK");
            webView.loadUrl(url);
        }

        moreIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopWindow();
            }
        });

        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        webSettings = webView.getSettings();
        webSettings.setDefaultTextEncodingName("utf-8");
//        //是否支持js
//        webSettings.setJavaScriptEnabled(true);
//        // 设置支持缩放
//        webSettings.setSupportZoom(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;// 不跳转浏览器
            }

            @Override
            public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
                return super.shouldOverrideKeyEvent(view, event);// 是否触发点击事件
            }
        });
    }

    private void showPopWindow() {
        View view = LayoutInflater.from(this).inflate(R.layout.item_news_info_popwindow,null);
        window = new PopupWindow(view, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        window.setFocusable(true);
        window.setTouchable(true);
        window.setOutsideTouchable(true);
        window.showAsDropDown(moreIv);

        shareLl = (LinearLayout) view.findViewById(R.id.item_news_info_pw_share_ll);
        collectLl = (LinearLayout) view.findViewById(R.id.item_news_info_pw_collect_ll);
        collectIv = (ImageView) view.findViewById(R.id.item_news_info_pw_collect_iv);
        collectTv = (TextView) view.findViewById(R.id.item_news_info_pw_collect_tv);

        shareLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareTool.showShare(title,content,shareUrl,imgUrl);
            }
        });


        if (hasLogin){
            userUrl = username + url;
            List<CollectBean> cbs = LiteOrmInstance.getInstance().queryByUserUrl(userUrl);
            if(cbs.size() > 0){
                hasCollected = true;
            } else {
                hasCollected = false;
            }
        } else {
            hasCollected = false;
        }
        if (hasCollected){
            collectIv.setImageResource(R.mipmap.collect);
            collectTv.setText("取消收藏");
        } else {
            collectIv.setImageResource(R.mipmap.no_collect);
            collectTv.setText("收藏");
        }
        collectLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hasLogin) {
                    if (hasCollected){
                        LiteOrmInstance.getInstance().deleteByUserUrl(userUrl);
                        collectIv.setImageResource(R.mipmap.no_collect);
                        collectTv.setText("收藏");
                        ToastTool.shortMsg("已取消收藏");
                    } else {
                        CollectBean cb = new CollectBean(username,title,content,shareUrl,imgUrl,fromUrl,url,"Info",username + url);
                        LiteOrmInstance.getInstance().insert(cb);
                        collectIv.setImageResource(R.mipmap.collect);
                        collectTv.setText("取消收藏");
                        ToastTool.shortMsg("收藏成功");
                    }
                } else {
                    ToastTool.shortMsg("您还没有登录");
                }
            }
        });

        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

            }
        });
    }



    @Override
    public void onResume() {
        super.onResume();
        List<UserInfoBean> userList = LiteOrmInstance.getInstance().queryAllUser();
        if (userList.size() > 0) {
            UserInfoBean userInfoBean = userList.get(0);
            username = userInfoBean.getUsername();
            hasLogin = true;
        } else {
            hasLogin = false;
        }
    }
}
