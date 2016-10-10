package com.lanou3g.dllo.neteasenews.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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
import com.lanou3g.dllo.neteasenews.model.net.UrlValues;
import com.lanou3g.dllo.neteasenews.model.net.VolleyInstance;
import com.lanou3g.dllo.neteasenews.model.net.VolleyResult;
import com.lanou3g.dllo.neteasenews.tools.ToastTool;

/**
 * Created by dllo on 16/10/9.
 * 文字新闻页面
 */
public class NewsInfoActivity extends AbsBaseActivity{

    private ImageView backIv;
    private ImageView moreIv;
    private WebView webView;
    private WebSettings webSettings;
    private String strUrl;

    private PopupWindow window;
    private LinearLayout shareLl;
    private LinearLayout collectLl;
    private ImageView collectIv;
    private TextView collectTv;

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
        strUrl = intent.getStringExtra("url");
        Bundle bundle = intent.getExtras();

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

        if (strUrl.equals(UrlValues.HEADLINENEWSURL)) {
            final NewsBean.T1348647909107Bean bean = (NewsBean.T1348647909107Bean) bundle.getSerializable("bean");
            String newUrl = "http://c.3g.163.com/nc/article/" + bean.getDocid() + "/full.html";
            VolleyInstance.getInstance().startRequest(newUrl, new VolleyResult() {
                @Override
                public void success(String resultStr) {
                    resultStr = resultStr.replace(bean.getDocid(),"id");
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
        } else if (strUrl.equals(UrlValues.SELECTEDNEWSURL)) {
            NewsBean.T1467284926140Bean bean = (NewsBean.T1467284926140Bean) bundle.getSerializable("bean");
            webView.loadUrl(bean.getUrl_3w());
        } else if (strUrl.equals(UrlValues.ENTERTAINMENTNEWSURL)) {
            NewsBean.T1348648517839Bean bean = (NewsBean.T1348648517839Bean) bundle.getSerializable("bean");
            webView.loadUrl(bean.getUrl_3w());
        } else if (strUrl.equals(UrlValues.SPORTSNEWSURL)) {
            NewsBean.T1348649079062Bean bean = (NewsBean.T1348649079062Bean) bundle.getSerializable("bean");
            webView.loadUrl(bean.getUrl_3w());
        } else if (strUrl.equals(UrlValues.FINANCIALNEWSURL)) {
            NewsBean.T1348648756099Bean bean = (NewsBean.T1348648756099Bean) bundle.getSerializable("bean");
            webView.loadUrl(bean.getUrl_3w());
        } else if (strUrl.equals(UrlValues.TECHNEWSURL)) {
            NewsBean.T1348649580692Bean bean = (NewsBean.T1348649580692Bean) bundle.getSerializable("bean");
            webView.loadUrl(bean.getUrl_3w());
        } else if (strUrl.equals(UrlValues.AUTONEWSURL)) {
            NewsBean.ListBean bean = (NewsBean.ListBean) bundle.getSerializable("bean");
            webView.loadUrl(bean.getUrl_3w());
        } else if (strUrl.equals(UrlValues.FASHIONNEWSURL)) {
            NewsBean.T1348650593803Bean bean = (NewsBean.T1348650593803Bean) bundle.getSerializable("bean");
            webView.loadUrl(bean.getUrl_3w());
        } else {
            NewsBean.T1348647909107Bean bean = (NewsBean.T1348647909107Bean) bundle.getSerializable("bean");
            webView.loadUrl(bean.getUrl_3w());
        }
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

        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

            }
        });
    }
}
