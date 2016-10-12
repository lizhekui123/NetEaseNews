package com.lanou3g.dllo.neteasenews.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lanou3g.dllo.neteasenews.R;
import com.lanou3g.dllo.neteasenews.model.bean.NewsBean;
import com.lanou3g.dllo.neteasenews.model.bean.NewsPhotoBean;
import com.lanou3g.dllo.neteasenews.model.db.CollectBean;
import com.lanou3g.dllo.neteasenews.model.db.LiteOrmInstance;
import com.lanou3g.dllo.neteasenews.model.db.UserInfoBean;
import com.lanou3g.dllo.neteasenews.model.net.UrlValues;
import com.lanou3g.dllo.neteasenews.model.net.VolleyInstance;
import com.lanou3g.dllo.neteasenews.model.net.VolleyResult;
import com.lanou3g.dllo.neteasenews.tools.ShareTool;
import com.lanou3g.dllo.neteasenews.tools.ToastTool;
import com.lanou3g.dllo.neteasenews.ui.adapter.news.NewsPhotoVpAdapter;

import java.util.List;

/**
 * Created by dllo on 16/10/9.
 * 图片新闻页面
 */
public class NewsPhotoActivity extends AbsBaseActivity{

    private ImageView backIv;
    private ImageView moreIv;
    private ViewPager viewPager;
    private TextView titleTv;
    private TextView pageTv;
    private TextView bodyTv;
    private String username;
    private String title;
    private String fromUrl;
    private String url;
    private String userUrl;
    private String content,shareUrl,imgUrl;

    private NewsPhotoVpAdapter adapter;

    private PopupWindow window;
    private LinearLayout shareLl;
    private LinearLayout collectLl;
    private ImageView collectIv;
    private TextView collectTv;

    private boolean hasLogin;
    private boolean hasCollected;

    @Override
    protected int setLayout() {
        return R.layout.activity_news_photo;
    }

    @Override
    protected void initViews() {
        backIv = byView(R.id.news_photo_back_iv);
        moreIv = byView(R.id.news_photo_more_iv);
        viewPager = byView(R.id.news_photo_vp);
        titleTv = byView(R.id.news_photo_title_tv);
        pageTv = byView(R.id.news_photo_page_tv);
        bodyTv = byView(R.id.news_photo_body_tv);
    }

    @Override
    protected void initDatas() {
        adapter = new NewsPhotoVpAdapter(this);
        viewPager.setAdapter(adapter);
        Intent intent = getIntent();
        content = "图片新闻";
        if (intent.getExtras().containsKey("bean")){
            fromUrl = intent.getStringExtra("url");
            Bundle bundle = intent.getExtras();
            if (fromUrl.equals(UrlValues.HEADLINENEWSURL)) {
                NewsBean.T1348647909107Bean bean = (NewsBean.T1348647909107Bean) bundle.getSerializable("bean");
                String skipID = bean.getSkipID();
                String str1 = skipID.substring(4,8);
                String str2 = skipID.substring(9);
                title = bean.getTitle();
                if(!bean.getDigest().isEmpty()){
                    content = bean.getDigest();
                } else {
                    content = "来自网易新闻";
                }
                shareUrl = "http://c.m.163.com/news/p/" + str1 + "/" + str2 + ".html?spss=newsapp&spsw=1";
                imgUrl = bean.getImgsrc();
                url = "http://c.3g.163.com/photo/api/set/" + str1 + "/" + str2 + ".json";
            } else if (fromUrl.equals(UrlValues.SELECTEDNEWSURL)) {
                NewsBean.T1467284926140Bean bean = (NewsBean.T1467284926140Bean) bundle.getSerializable("bean");
                String skipID = bean.getSkipID();
                String str1 = skipID.substring(4,8);
                String str2 = skipID.substring(9);
                title = bean.getTitle();
                if(!bean.getDigest().isEmpty()){
                    content = bean.getDigest();
                } else {
                    content = "来自网易新闻";
                }
                shareUrl = "http://c.m.163.com/news/p/" + str1 + "/" + str2 + ".html?spss=newsapp&spsw=1";
                imgUrl = bean.getImgsrc();
                url = "http://c.3g.163.com/photo/api/set/" + str1 + "/" + str2 + ".json";
            } else if (fromUrl.equals(UrlValues.ENTERTAINMENTNEWSURL)) {
                NewsBean.T1348648517839Bean bean = (NewsBean.T1348648517839Bean) bundle.getSerializable("bean");
                String skipID = bean.getSkipID();
                String str1 = skipID.substring(4,8);
                String str2 = skipID.substring(9);
                title = bean.getTitle();
                if(!bean.getDigest().isEmpty()){
                    content = bean.getDigest();
                } else {
                    content = "来自网易新闻";
                }
                shareUrl = "http://c.m.163.com/news/p/" + str1 + "/" + str2 + ".html?spss=newsapp&spsw=1";
                imgUrl = bean.getImgsrc();
                url = "http://c.3g.163.com/photo/api/set/" + str1 + "/" + str2 + ".json";
            } else if (fromUrl.equals(UrlValues.SPORTSNEWSURL)) {
                NewsBean.T1348649079062Bean bean = (NewsBean.T1348649079062Bean) bundle.getSerializable("bean");
                String skipID = bean.getSkipID();
                String str1 = skipID.substring(4,8);
                String str2 = skipID.substring(9);
                title = bean.getTitle();
                if(!bean.getDigest().isEmpty()){
                    content = bean.getDigest();
                } else {
                    content = "来自网易新闻";
                }
                shareUrl = "http://c.m.163.com/news/p/" + str1 + "/" + str2 + ".html?spss=newsapp&spsw=1";
                imgUrl = bean.getImgsrc();
                url = "http://c.3g.163.com/photo/api/set/" + str1 + "/" + str2 + ".json";
            } else if (fromUrl.equals(UrlValues.FINANCIALNEWSURL)) {
                NewsBean.T1348648756099Bean bean = (NewsBean.T1348648756099Bean) bundle.getSerializable("bean");
                String skipID = bean.getSkipID();
                String str1 = skipID.substring(4,8);
                String str2 = skipID.substring(9);
                title = bean.getTitle();
                if(!bean.getDigest().isEmpty()){
                    content = bean.getDigest();
                } else {
                    content = "来自网易新闻";
                }
                shareUrl = "http://c.m.163.com/news/p/" + str1 + "/" + str2 + ".html?spss=newsapp&spsw=1";
                imgUrl = bean.getImgsrc();
                url = "http://c.3g.163.com/photo/api/set/" + str1 + "/" + str2 + ".json";
            } else if (fromUrl.equals(UrlValues.TECHNEWSURL)) {
                NewsBean.T1348649580692Bean bean = (NewsBean.T1348649580692Bean) bundle.getSerializable("bean");
                String skipID = bean.getSkipID();
                String str1 = skipID.substring(4,8);
                String str2 = skipID.substring(9);
                title = bean.getTitle();
                if(!bean.getDigest().isEmpty()){
                    content = bean.getDigest();
                } else {
                    content = "来自网易新闻";
                }
                shareUrl = "http://c.m.163.com/news/p/" + str1 + "/" + str2 + ".html?spss=newsapp&spsw=1";
                imgUrl = bean.getImgsrc();
                url = "http://c.3g.163.com/photo/api/set/" + str1 + "/" + str2 + ".json";
            } else if (fromUrl.equals(UrlValues.AUTONEWSURL)) {
                NewsBean.ListBean bean = (NewsBean.ListBean) bundle.getSerializable("bean");
                String skipID = bean.getSkipID();
                String str1 = skipID.substring(4,8);
                String str2 = skipID.substring(9);
                title = bean.getTitle();
                if(!bean.getDigest().isEmpty()){
                    content = bean.getDigest();
                } else {
                    content = "来自网易新闻";
                }
                shareUrl = "http://c.m.163.com/news/p/" + str1 + "/" + str2 + ".html?spss=newsapp&spsw=1";
                imgUrl = bean.getImgsrc();
                url = "http://c.3g.163.com/photo/api/set/" + str1 + "/" + str2 + ".json";
            } else if (fromUrl.equals(UrlValues.FASHIONNEWSURL)) {
                NewsBean.T1348650593803Bean bean = (NewsBean.T1348650593803Bean) bundle.getSerializable("bean");
                String skipID = bean.getSkipID();
                String str1 = skipID.substring(4,8);
                String str2 = skipID.substring(9);
                title = bean.getTitle();
                if(!bean.getDigest().isEmpty()){
                    content = bean.getDigest();
                } else {
                    content = "来自网易新闻";
                }
                shareUrl = "http://c.m.163.com/news/p/" + str1 + "/" + str2 + ".html?spss=newsapp&spsw=1";
                imgUrl = bean.getImgsrc();
                url = "http://c.3g.163.com/photo/api/set/" + str1 + "/" + str2 + ".json";
            } else {
                NewsBean.T1348647909107Bean bean = (NewsBean.T1348647909107Bean) bundle.getSerializable("bean");
                String skipID = bean.getSkipID();
                String str1 = skipID.substring(4,8);
                String str2 = skipID.substring(9);
                title = bean.getTitle();
                if(!bean.getDigest().isEmpty()){
                    content = bean.getDigest();
                } else {
                    content = "来自网易新闻";
                }
                shareUrl = "http://c.m.163.com/news/p/" + str1 + "/" + str2 + ".html?spss=newsapp&spsw=1";
                imgUrl = bean.getImgsrc();
                url = "http://c.3g.163.com/photo/api/set/" + str1 + "/" + str2 + ".json";
            }
        } else if (intent.getExtras().containsKey("collectBean")){
            Bundle bundle = intent.getExtras();
            CollectBean cb = (CollectBean) bundle.getSerializable("collectBean");
            title = cb.getTitle();
            content = cb.getContent();
            shareUrl = cb.getShareUrl();
            fromUrl = cb.getFromUrl();
            imgUrl = cb.getImgUrl();
            url = cb.getUrl();
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


        VolleyInstance.getInstance().startRequest(url, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                final NewsPhotoBean data = gson.fromJson(resultStr,NewsPhotoBean.class);
                titleTv.setText(data.getSetname());
                final List<NewsPhotoBean.PhotosBean> beans =  data.getPhotos();
                pageTv.setText(1 + "/" + data.getImgsum());
                bodyTv.setText(beans.get(0).getImgtitle() + beans.get(0).getNote());
                adapter.setDatas(beans);
                viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        pageTv.setText((position + 1) + "/" + data.getImgsum());
                        bodyTv.setText(beans.get(position).getImgtitle() + beans.get(position).getNote());
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
            }

            @Override
            public void failure() {
                ToastTool.shortMsg("请求失败");
                finish();
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
                        CollectBean cb = new CollectBean(username,title,content,shareUrl,imgUrl,fromUrl,url,"photo",username + url);
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
