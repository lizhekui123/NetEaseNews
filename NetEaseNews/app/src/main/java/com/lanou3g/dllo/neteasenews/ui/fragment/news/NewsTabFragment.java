package com.lanou3g.dllo.neteasenews.ui.fragment.news;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lanou3g.dllo.neteasenews.R;
import com.lanou3g.dllo.neteasenews.model.bean.NewsBean;
import com.lanou3g.dllo.neteasenews.model.bean.RotateAdsBean;
import com.lanou3g.dllo.neteasenews.model.net.UrlValues;
import com.lanou3g.dllo.neteasenews.model.net.VolleyInstance;
import com.lanou3g.dllo.neteasenews.model.net.VolleyResult;
import com.lanou3g.dllo.neteasenews.tools.ToastTool;
import com.lanou3g.dllo.neteasenews.ui.adapter.common.RotateVpAdapter;
import com.lanou3g.dllo.neteasenews.ui.adapter.news.NewsListAdapter;
import com.lanou3g.dllo.neteasenews.ui.fragment.AbsBaseFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/12.
 * 新闻解释实体类
 */
public class NewsTabFragment extends AbsBaseFragment {

    private static final int TIME = 3000;
    private ViewPager viewPager;
    private TextView textView;
    private ImageView imageView;
    private LinearLayout pointLl;
    private List<RotateAdsBean> rotateDatas;
    private RotateVpAdapter vpAdapter;

    private PullToRefreshListView refreshListView;
    private ListView listView;
    private NewsListAdapter adapter;
    private String strUrl;

    int[] urlNums = {0,0,0,0,0,0,0,0};

    public static NewsTabFragment newInstance(String url) {
        Bundle args = new Bundle();
        args.putString("url", url);
        NewsTabFragment fragment = new NewsTabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_news_tab;
    }

    @Override
    protected void initView() {
        refreshListView = byView(R.id.news_tab_lv);
        adapter = new NewsListAdapter(context);
        refreshListView.setAdapter(adapter);
    }

    @Override
    protected void initDatas() {

        refreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                /**
                 * 下拉刷新任务
                 */
                VolleyInstance.getInstance().startRequest(strUrl, new VolleyResult() {
                    @Override
                    public void success(String resultStr) {
                        Gson gson = new Gson();
                        NewsBean newsBean = gson.fromJson(resultStr, NewsBean.class);
                        if (strUrl.equals(UrlValues.HEADLINENEWSURL)) {
                            List<NewsBean.T1348647909107Bean> datas = newsBean.getT1348647909107();
                            adapter.setDatas(strUrl, datas);
                        } else if (strUrl.equals(UrlValues.SELECTEDNEWSURL)) {
                            List<NewsBean.T1467284926140Bean> datas = newsBean.getT1467284926140();
                            adapter.setDatas(strUrl, datas);
                        } else if (strUrl.equals(UrlValues.ENTERTAINMENTNEWSURL)) {
                            List<NewsBean.T1348648517839Bean> datas = newsBean.getT1348648517839();
                            adapter.setDatas(strUrl, datas);
                        } else if (strUrl.equals(UrlValues.SPORTSNEWSURL)) {
                            List<NewsBean.T1348649079062Bean> datas = newsBean.getT1348649079062();
                            adapter.setDatas(strUrl, datas);
                        } else if (strUrl.equals(UrlValues.FINANCIALNEWSURL)) {
                            List<NewsBean.T1348648756099Bean> datas = newsBean.getT1348648756099();
                            adapter.setDatas(strUrl, datas);
                        } else if (strUrl.equals(UrlValues.TECHNEWSURL)) {
                            List<NewsBean.T1348649580692Bean> datas = newsBean.getT1348649580692();
                            adapter.setDatas(strUrl, datas);
                        } else if (strUrl.equals(UrlValues.AUTONEWSURL)) {
                            List<NewsBean.ListBean> datas = newsBean.getList();
                            adapter.setDatas(strUrl, datas);
                        } else if (strUrl.equals(UrlValues.FASHIONNEWSURL)) {
                            List<NewsBean.T1348650593803Bean> datas = newsBean.getT1348650593803();
                            adapter.setDatas(strUrl, datas);
                        } else {
                            List<NewsBean.T1348647909107Bean> datas = newsBean.getT1348647909107();
                            adapter.setDatas(strUrl, datas);
                        }
                        refreshListView.onRefreshComplete();
                    }

                    @Override
                    public void failure() {

                    }
                });
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                /**
                 * 上拉加载任务
                 */
                if (strUrl.equals(UrlValues.HEADLINENEWSURL)) {
                    final List<NewsBean.T1348647909107Bean> datas = adapter.getDatas();
                    if (urlNums[0] < 210) {
                        urlNums[0] = urlNums[0] + 10;
                        String newUrl = "http://c.3g.163.com/recommend/getSubDocPic?tid=T1348647909107&from=toutiao&offset=" + urlNums[0] + "&size=10";
                        VolleyInstance.getInstance().startRequest(newUrl, new VolleyResult() {
                            @Override
                            public void success(String resultStr) {
                                Gson gson = new Gson();
                                NewsBean newsBean = gson.fromJson(resultStr, NewsBean.class);
                                datas.addAll(newsBean.getT1348647909107());
                                adapter.setDatas(strUrl, datas);
                            }

                            @Override
                            public void failure() {
                                ToastTool.shortMsg("请求失败");
                            }
                        });
                    } else {
                        ToastTool.shortMsg("没有更多数据了");
                    }
                } else if (strUrl.equals(UrlValues.SELECTEDNEWSURL)) {
                    final List<NewsBean.T1467284926140Bean> datas = adapter.getDatas();
                    if (urlNums[1] < 420) {
                        urlNums[1] = urlNums[1] + 20;
                        String newUrl = "http://c.m.163.com/nc/article/list/T1467284926140/" + urlNums[1] + "-20.html";
                        VolleyInstance.getInstance().startRequest(newUrl, new VolleyResult() {
                            @Override
                            public void success(String resultStr) {
                                Gson gson = new Gson();
                                NewsBean newsBean = gson.fromJson(resultStr, NewsBean.class);
                                datas.addAll(newsBean.getT1467284926140());
                                adapter.setDatas(strUrl, datas);
                            }

                            @Override
                            public void failure() {
                                ToastTool.shortMsg("请求失败");
                            }
                        });
                    } else {
                        ToastTool.shortMsg("没有更多数据了");
                    }
                } else if (strUrl.equals(UrlValues.ENTERTAINMENTNEWSURL)) {
                    final List<NewsBean.T1348648517839Bean> datas = adapter.getDatas();
                    if (urlNums[2] < 420) {
                        urlNums[2] = urlNums[2] + 20;
                        String newUrl = "http://c.m.163.com/nc/article/list/T1348648517839/" + urlNums[2] + "-20.html";
                        VolleyInstance.getInstance().startRequest(newUrl, new VolleyResult() {
                            @Override
                            public void success(String resultStr) {
                                Gson gson = new Gson();
                                NewsBean newsBean = gson.fromJson(resultStr, NewsBean.class);
                                datas.addAll(newsBean.getT1348648517839());
                                adapter.setDatas(strUrl, datas);
                            }

                            @Override
                            public void failure() {
                                ToastTool.shortMsg("请求失败");
                            }
                        });
                    } else {
                        ToastTool.shortMsg("没有更多数据了");
                    }
                } else if (strUrl.equals(UrlValues.SPORTSNEWSURL)) {
                    final List<NewsBean.T1348649079062Bean> datas = adapter.getDatas();
                    if (urlNums[3] < 420) {
                        urlNums[3] = urlNums[3] + 20;
                        String newUrl = "http://c.m.163.com/nc/article/list/T1348649079062/" + urlNums[3] + "-20.html";
                        VolleyInstance.getInstance().startRequest(newUrl, new VolleyResult() {
                            @Override
                            public void success(String resultStr) {
                                Gson gson = new Gson();
                                NewsBean newsBean = gson.fromJson(resultStr, NewsBean.class);
                                datas.addAll(newsBean.getT1348649079062());
                                adapter.setDatas(strUrl, datas);
                            }

                            @Override
                            public void failure() {
                                ToastTool.shortMsg("请求失败");
                            }
                        });
                    } else {
                        ToastTool.shortMsg("没有更多数据了");
                    }
                } else if (strUrl.equals(UrlValues.FINANCIALNEWSURL)) {
                    final List<NewsBean.T1348648756099Bean> datas = adapter.getDatas();
                    if (urlNums[4] < 420) {
                        urlNums[4] = urlNums[4] + 20;
                        String newUrl = "http://c.m.163.com/nc/article/list/T1348648756099/" + urlNums[4] + "-20.html";
                        VolleyInstance.getInstance().startRequest(newUrl, new VolleyResult() {
                            @Override
                            public void success(String resultStr) {
                                Gson gson = new Gson();
                                NewsBean newsBean = gson.fromJson(resultStr, NewsBean.class);
                                datas.addAll(newsBean.getT1348648756099());
                                adapter.setDatas(strUrl, datas);
                            }

                            @Override
                            public void failure() {
                                ToastTool.shortMsg("请求失败");
                            }
                        });
                    } else {
                        ToastTool.shortMsg("没有更多数据了");
                    }
                } else if (strUrl.equals(UrlValues.TECHNEWSURL)) {
                    final List<NewsBean.T1348649580692Bean> datas = adapter.getDatas();
                    if (urlNums[5] < 420) {
                        urlNums[5] = urlNums[5] + 20;
                        String newUrl = "http://c.m.163.com/nc/article/list/T1348649580692/" + urlNums[5] + "-20.html";
                        VolleyInstance.getInstance().startRequest(newUrl, new VolleyResult() {
                            @Override
                            public void success(String resultStr) {
                                Gson gson = new Gson();
                                NewsBean newsBean = gson.fromJson(resultStr, NewsBean.class);
                                datas.addAll(newsBean.getT1348649580692());
                                adapter.setDatas(strUrl, datas);
                            }

                            @Override
                            public void failure() {
                                ToastTool.shortMsg("请求失败");
                            }
                        });
                    } else {
                        ToastTool.shortMsg("没有更多数据了");
                    }
                } else if (strUrl.equals(UrlValues.AUTONEWSURL)) {
                    final List<NewsBean.ListBean> datas = adapter.getDatas();
                    if (urlNums[6] < 420) {
                        urlNums[6] = urlNums[6] + 20;
                        String newUrl = "http://c.m.163.com/nc/article/list/5YWo5Zu9/" + urlNums[6] + "-20.html";
                        VolleyInstance.getInstance().startRequest(newUrl, new VolleyResult() {
                            @Override
                            public void success(String resultStr) {
                                Gson gson = new Gson();
                                NewsBean newsBean = gson.fromJson(resultStr, NewsBean.class);
                                datas.addAll(newsBean.getList());
                                adapter.setDatas(strUrl, datas);
                            }

                            @Override
                            public void failure() {
                                ToastTool.shortMsg("请求失败");
                            }
                        });
                    } else {
                        ToastTool.shortMsg("没有更多数据了");
                    }
                } else if (strUrl.equals(UrlValues.FASHIONNEWSURL)) {
                    final List<NewsBean.T1348650593803Bean> datas = adapter.getDatas();
                    if (urlNums[7] < 420) {
                        urlNums[7] = urlNums[7] + 20;
                        String newUrl = "http://c.m.163.com/nc/article/list/T1348650593803/" + urlNums[7] + "-20.html";
                        VolleyInstance.getInstance().startRequest(newUrl, new VolleyResult() {
                            @Override
                            public void success(String resultStr) {
                                Gson gson = new Gson();
                                NewsBean newsBean = gson.fromJson(resultStr, NewsBean.class);
                                datas.addAll(newsBean.getT1348650593803());
                                adapter.setDatas(strUrl, datas);
                            }

                            @Override
                            public void failure() {
                                ToastTool.shortMsg("请求失败");
                            }
                        });
                    } else {
                        ToastTool.shortMsg("没有更多数据了");
                    }
                } else {
                    final List<NewsBean.T1348647909107Bean> datas = adapter.getDatas();
                    if (urlNums[0] < 210) {
                        urlNums[0] = urlNums[0] + 10;
                        String newUrl = "http://c.3g.163.com/recommend/getSubDocPic?tid=T1348647909107&from=toutiao&offset=" + urlNums[0] + "&size=10";
                        VolleyInstance.getInstance().startRequest(newUrl, new VolleyResult() {
                            @Override
                            public void success(String resultStr) {
                                Gson gson = new Gson();
                                NewsBean newsBean = gson.fromJson(resultStr, NewsBean.class);
                                datas.addAll(newsBean.getT1348647909107());
                                adapter.setDatas(strUrl, datas);
                            }

                            @Override
                            public void failure() {
                                ToastTool.shortMsg("请求失败");
                            }
                        });
                    } else {
                        ToastTool.shortMsg("没有更多数据了");
                    }
                }
                refreshListView.onRefreshComplete();
            }
        });

        Bundle bundle = getArguments();
        strUrl = bundle.getString("url");
        VolleyInstance.getInstance().startRequest(strUrl, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                NewsBean newsBean = gson.fromJson(resultStr, NewsBean.class);
                if (strUrl.equals(UrlValues.HEADLINENEWSURL)) {
                    List<NewsBean.T1348647909107Bean> datas = newsBean.getT1348647909107();
                    adapter.setDatas(strUrl, datas);
                    rotateDatas = new ArrayList<RotateAdsBean>();
                    for (int i = 0; i < datas.get(0).getAds().size(); i++) {
                        rotateDatas.add(new RotateAdsBean(datas.get(0).getAds().get(i).getTitle(),
                                datas.get(0).getAds().get(i).getImgsrc(),
                                datas.get(0).getAds().get(i).getUrl()));
                    }
                    if (rotateDatas.size() > 1) {
                        initRotate();// 初始化轮播图相关内容
                        doRotate(rotateDatas);// 设置轮播图
                    } else {
                        View view = LayoutInflater.from(context).inflate(R.layout.item_header_img, null);
                        listView = refreshListView.getRefreshableView();
                        listView.addHeaderView(view);
                        textView = (TextView) view.findViewById(R.id.image_tv);
                        imageView = (ImageView) view.findViewById(R.id.image_iv);
                        textView.setText(rotateDatas.get(0).getTitle());
                        Picasso.with(context).load(rotateDatas.get(0).getImgsrc()).into(imageView);
                    }
                } else if (strUrl.equals(UrlValues.SELECTEDNEWSURL)) {
                    List<NewsBean.T1467284926140Bean> datas = newsBean.getT1467284926140();
                    adapter.setDatas(strUrl, datas);
                    View view = LayoutInflater.from(context).inflate(R.layout.item_header_img, null);
                    listView = refreshListView.getRefreshableView();
                    listView.addHeaderView(view);
                    textView = (TextView) view.findViewById(R.id.image_tv);
                    imageView = (ImageView) view.findViewById(R.id.image_iv);
                    textView.setText(datas.get(0).getTitle());
                    Picasso.with(context).load(datas.get(0).getImgsrc()).into(imageView);
                } else if (strUrl.equals(UrlValues.ENTERTAINMENTNEWSURL)) {
                    List<NewsBean.T1348648517839Bean> datas = newsBean.getT1348648517839();
                    adapter.setDatas(strUrl, datas);
                    rotateDatas = new ArrayList<RotateAdsBean>();
                    for (int i = 0; i < datas.get(0).getAds().size(); i++) {
                        rotateDatas.add(new RotateAdsBean(datas.get(0).getAds().get(i).getTitle(),
                                datas.get(0).getAds().get(i).getImgsrc(),
                                datas.get(0).getAds().get(i).getUrl()));
                    }
                    if (rotateDatas.size() > 1) {
                        initRotate();// 初始化轮播图相关内容
                        doRotate(rotateDatas);// 设置轮播图
                    } else {
                        View view = LayoutInflater.from(context).inflate(R.layout.item_header_img, null);
                        listView = refreshListView.getRefreshableView();
                        listView.addHeaderView(view);
                        textView = (TextView) view.findViewById(R.id.image_tv);
                        imageView = (ImageView) view.findViewById(R.id.image_iv);
                        textView.setText(rotateDatas.get(0).getTitle());
                        Picasso.with(context).load(rotateDatas.get(0).getImgsrc()).into(imageView);
                    }

                } else if (strUrl.equals(UrlValues.SPORTSNEWSURL)) {
                    List<NewsBean.T1348649079062Bean> datas = newsBean.getT1348649079062();
                    adapter.setDatas(strUrl, datas);
                    rotateDatas = new ArrayList<RotateAdsBean>();
                    for (int i = 0; i < datas.get(0).getAds().size(); i++) {
                        rotateDatas.add(new RotateAdsBean(datas.get(0).getAds().get(i).getTitle(),
                                datas.get(0).getAds().get(i).getImgsrc(),
                                datas.get(0).getAds().get(i).getUrl()));
                    }
                    if (rotateDatas.size() > 1) {
                        initRotate();// 初始化轮播图相关内容
                        doRotate(rotateDatas);// 设置轮播图
                    } else {
                        View view = LayoutInflater.from(context).inflate(R.layout.item_header_img, null);
                        listView = refreshListView.getRefreshableView();
                        listView.addHeaderView(view);
                        textView = (TextView) view.findViewById(R.id.image_tv);
                        imageView = (ImageView) view.findViewById(R.id.image_iv);
                        textView.setText(rotateDatas.get(0).getTitle());
                        Picasso.with(context).load(rotateDatas.get(0).getImgsrc()).into(imageView);
                    }
                } else if (strUrl.equals(UrlValues.FINANCIALNEWSURL)) {
                    List<NewsBean.T1348648756099Bean> datas = newsBean.getT1348648756099();
                    adapter.setDatas(strUrl, datas);
                    rotateDatas = new ArrayList<RotateAdsBean>();
                    for (int i = 0; i < datas.get(0).getAds().size(); i++) {
                        rotateDatas.add(new RotateAdsBean(datas.get(0).getAds().get(i).getTitle(),
                                datas.get(0).getAds().get(i).getImgsrc(),
                                datas.get(0).getAds().get(i).getUrl()));
                    }
                    if (rotateDatas.size() > 1) {
                        initRotate();// 初始化轮播图相关内容
                        doRotate(rotateDatas);// 设置轮播图
                    } else {
                        View view = LayoutInflater.from(context).inflate(R.layout.item_header_img, null);
                        listView = refreshListView.getRefreshableView();
                        listView.addHeaderView(view);
                        textView = (TextView) view.findViewById(R.id.image_tv);
                        imageView = (ImageView) view.findViewById(R.id.image_iv);
                        textView.setText(rotateDatas.get(0).getTitle());
                        Picasso.with(context).load(rotateDatas.get(0).getImgsrc()).into(imageView);
                    }
                } else if (strUrl.equals(UrlValues.TECHNEWSURL)) {
                    List<NewsBean.T1348649580692Bean> datas = newsBean.getT1348649580692();
                    adapter.setDatas(strUrl, datas);
                    rotateDatas = new ArrayList<RotateAdsBean>();
                    for (int i = 0; i < datas.get(0).getAds().size(); i++) {
                        rotateDatas.add(new RotateAdsBean(datas.get(0).getAds().get(i).getTitle(),
                                datas.get(0).getAds().get(i).getImgsrc(),
                                datas.get(0).getAds().get(i).getUrl()));
                    }
                    if (rotateDatas.size() > 1) {
                        initRotate();// 初始化轮播图相关内容
                        doRotate(rotateDatas);// 设置轮播图
                    } else {
                        View view = LayoutInflater.from(context).inflate(R.layout.item_header_img, null);
                        listView = refreshListView.getRefreshableView();
                        listView.addHeaderView(view);
                        textView = (TextView) view.findViewById(R.id.image_tv);
                        imageView = (ImageView) view.findViewById(R.id.image_iv);
                        textView.setText(rotateDatas.get(0).getTitle());
                        Picasso.with(context).load(rotateDatas.get(0).getImgsrc()).into(imageView);
                    }
                } else if (strUrl.equals(UrlValues.AUTONEWSURL)) {
                    List<NewsBean.ListBean> datas = newsBean.getList();
                    adapter.setDatas(strUrl, datas);
                    rotateDatas = new ArrayList<RotateAdsBean>();
                    for (int i = 0; i < datas.get(0).getAds().size(); i++) {
                        rotateDatas.add(new RotateAdsBean(datas.get(0).getAds().get(i).getTitle(),
                                datas.get(0).getAds().get(i).getImgsrc(),
                                datas.get(0).getAds().get(i).getUrl()));
                    }
                    if (rotateDatas.size() > 1) {
                        initRotate();// 初始化轮播图相关内容
                        doRotate(rotateDatas);// 设置轮播图
                    } else {
                        View view = LayoutInflater.from(context).inflate(R.layout.item_header_img, null);
                        listView = refreshListView.getRefreshableView();
                        listView.addHeaderView(view);
                        textView = (TextView) view.findViewById(R.id.image_tv);
                        imageView = (ImageView) view.findViewById(R.id.image_iv);
                        textView.setText(rotateDatas.get(0).getTitle());
                        Picasso.with(context).load(rotateDatas.get(0).getImgsrc()).into(imageView);
                    }
                } else if (strUrl.equals(UrlValues.FASHIONNEWSURL)) {
                    List<NewsBean.T1348650593803Bean> datas = newsBean.getT1348650593803();
                    adapter.setDatas(strUrl, datas);
                    rotateDatas = new ArrayList<RotateAdsBean>();
                    for (int i = 0; i < datas.get(0).getAds().size(); i++) {
                        rotateDatas.add(new RotateAdsBean(datas.get(0).getAds().get(i).getTitle(),
                                datas.get(0).getAds().get(i).getImgsrc(),
                                datas.get(0).getAds().get(i).getUrl()));
                    }
                    if (rotateDatas.size() > 1) {
                        initRotate();// 初始化轮播图相关内容
                        doRotate(rotateDatas);// 设置轮播图
                    } else {
                        View view = LayoutInflater.from(context).inflate(R.layout.item_header_img, null);
                        listView = refreshListView.getRefreshableView();
                        listView.addHeaderView(view);
                        textView = (TextView) view.findViewById(R.id.image_tv);
                        imageView = (ImageView) view.findViewById(R.id.image_iv);
                        textView.setText(rotateDatas.get(0).getTitle());
                        Picasso.with(context).load(rotateDatas.get(0).getImgsrc()).into(imageView);
                    }
                } else {
                    List<NewsBean.T1348647909107Bean> datas = newsBean.getT1348647909107();
                    adapter.setDatas(strUrl, datas);
                    rotateDatas = new ArrayList<RotateAdsBean>();
                    for (int i = 0; i < datas.get(0).getAds().size(); i++) {
                        rotateDatas.add(new RotateAdsBean(datas.get(0).getAds().get(i).getTitle(),
                                datas.get(0).getAds().get(i).getImgsrc(),
                                datas.get(0).getAds().get(i).getUrl()));
                    }
                    if (rotateDatas.size() > 1) {
                        initRotate();// 初始化轮播图相关内容
                        doRotate(rotateDatas);// 设置轮播图
                    } else {
                        View view = LayoutInflater.from(context).inflate(R.layout.item_header_img, null);
                        listView = refreshListView.getRefreshableView();
                        listView.addHeaderView(view);
                        textView = (TextView) view.findViewById(R.id.image_tv);
                        imageView = (ImageView) view.findViewById(R.id.image_iv);
                        textView.setText(rotateDatas.get(0).getTitle());
                        Picasso.with(context).load(rotateDatas.get(0).getImgsrc()).into(imageView);
                    }
                }

            }

            @Override
            public void failure() {
                ToastTool.shortMsg("请求失败");
            }
        });
    }

    private void initRotate() {
        View view = LayoutInflater.from(context).inflate(R.layout.item_header_rotate, null);
        listView = refreshListView.getRefreshableView();
        listView.addHeaderView(view);
        viewPager = (ViewPager) view.findViewById(R.id.rotate_vp);
        textView = (TextView) view.findViewById(R.id.rotate_tv);
        imageView = (ImageView) view.findViewById(R.id.rotate_type_iv);
        pointLl = (LinearLayout) view.findViewById(R.id.rotate_point_container);
        vpAdapter = new RotateVpAdapter(context);
        viewPager.setAdapter(vpAdapter);
    }

    private void doRotate(List<RotateAdsBean> rotateDatas) {
        List<String> rotateImgDatas = new ArrayList<>();
        if (rotateDatas != null) {
            for (int i = 0; i < rotateDatas.size(); i++) {
                rotateImgDatas.add(rotateDatas.get(i).getImgsrc());
            }
            textView.setMaxEms(19 - rotateDatas.size());
            vpAdapter.setDatas(rotateImgDatas);
            /**
             * ViewPager的页数为int最大值,设置当前页多一些,可以上来就向前滑动
             * 为了保证第一页始终为数据的第0条 取余要为0,因此设置数据集合大小的倍数
             */
            viewPager.setCurrentItem(rotateDatas.size() * 100);
            /**
             * 开始轮播
             */
            handler = new Handler();
            startRotate();
            /**
             * 添加轮播小点
             */
            addPoints();
            /**
             * 随着轮播改变小点,文字和图片
             */
            changePoints();
        }
//                    Log.d("rotate", "rotateImgDatas.size():" + rotateImgDatas.size()+"");


    }

    private void changePoints() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (isRotate) {
                    /**
                     * 把所有小点设置为灰色
                     */
                    for (int i = 0; i < rotateDatas.size(); i++) {
                        ImageView pointIv = (ImageView) pointLl.getChildAt(i);
                        pointIv.setImageResource(R.mipmap.point_gray);
                    }
                    /**
                     * 设置当前位置小点为白色
                     */
                    ImageView iv = (ImageView) pointLl.getChildAt(position % rotateDatas.size());
                    iv.setImageResource(R.mipmap.point_white);
                    /**
                     * 设置当前文字和图片
                     */
                    RotateAdsBean adsBean = rotateDatas.get(position % rotateDatas.size());
                    textView.setText(adsBean.getTitle());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 添加轮播切换小点
     */
    private void addPoints() {
        // 有多少张图加载多少个小点
        for (int i = 0; i < rotateDatas.size(); i++) {
            ImageView pointIv = new ImageView(context);
            pointIv.setPadding(5, 5, 5, 5);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            pointIv.setLayoutParams(params);

            // 设置第0页小点的为白色
            if (i == 0) {
                pointIv.setImageResource(R.mipmap.point_white);
                /**
                 * 设置当前文字和图片
                 */
                RotateAdsBean adsBean = rotateDatas.get(0);
                textView.setText(adsBean.getTitle());
            } else {
                pointIv.setImageResource(R.mipmap.point_gray);
            }
            pointLl.addView(pointIv);
        }
    }

    private Handler handler;
    private boolean isRotate = false;
    private Runnable rotateRunnable;

    /**
     * 开始轮播
     */
    private void startRotate() {
        rotateRunnable = new Runnable() {
            @Override
            public void run() {
                int nowIndex = viewPager.getCurrentItem();
                viewPager.setCurrentItem(++nowIndex);
                if (isRotate) {
                    handler.postDelayed(rotateRunnable, TIME);
                }
            }
        };
        handler.postDelayed(rotateRunnable, TIME);
    }

    @Override
    public void onResume() {
        super.onResume();
        isRotate = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        isRotate = false;
    }

}
