package com.lanou3g.dllo.neteasenews.ui.fragment.live;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lanou3g.dllo.neteasenews.R;
import com.lanou3g.dllo.neteasenews.model.bean.LiveBean;
import com.lanou3g.dllo.neteasenews.model.bean.LiveHeaderBean;
import com.lanou3g.dllo.neteasenews.model.net.UrlValues;
import com.lanou3g.dllo.neteasenews.model.net.VolleyInstance;
import com.lanou3g.dllo.neteasenews.model.net.VolleyResult;
import com.lanou3g.dllo.neteasenews.tools.UrlTool;
import com.lanou3g.dllo.neteasenews.ui.adapter.common.RotateVpAdapter;
import com.lanou3g.dllo.neteasenews.ui.adapter.live.LiveHeaderGVAdapter;
import com.lanou3g.dllo.neteasenews.ui.adapter.live.LiveListAdapter;
import com.lanou3g.dllo.neteasenews.ui.fragment.AbsBaseFragment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/12.
 * 直播解析实体类
 */
public class LiveTabFragment extends AbsBaseFragment {

    private static final int TIME = 3000;
    private ViewPager viewPager;
    private TextView textView;
    private ImageView imageView;
    private LinearLayout pointLl;
    private List<LiveBean.TopBean> rotateDatas;
    private RotateVpAdapter vpAdapter;

    private ListView listView;
    private String strUrl;
    private LiveListAdapter adapter;

    private GridView gridView;
    private LiveHeaderGVAdapter gvAdapter;
    private List<LiveHeaderBean> headerDatas;
    private View headerView;

    public static LiveTabFragment newInstance(String url) {
        Bundle args = new Bundle();
        args.putString("url", url);
        LiveTabFragment fragment = new LiveTabFragment();
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_live_tab;
    }

    @Override
    protected void initView() {
        listView = byView(R.id.live_lv);
        adapter = new LiveListAdapter(context);
        listView.setAdapter(adapter);
    }

    @Override
    protected void initDatas() {
        Bundle bundle = getArguments();
        strUrl = bundle.getString("url");
        if (strUrl.equals(UrlValues.HOTLIVEURL)) {
            VolleyInstance.getInstance().startRequest(strUrl, new VolleyResult() {
                @Override
                public void success(String resultStr) {
                    Gson gson = new Gson();
                    LiveBean bean = gson.fromJson(resultStr, LiveBean.class);
                    List<LiveBean.LiveReviewBean> datas = bean.getLive_review();
                    adapter.setDatas(datas);
                    initRotate();// 初始化轮播图相关内容
                    doRotate(bean);// 设置轮播图
                }

                @Override
                public void failure() {

                }
            });

        } else {
            VolleyInstance.getInstance().startRequest(strUrl, new VolleyResult() {
                @Override
                public void success(String resultStr) {
                    Gson gson = new Gson();
                    LiveBean bean = gson.fromJson(resultStr, LiveBean.class);
                    List<LiveBean.LiveReviewBean> datas = bean.getLive_review();
                    adapter.setDatas(datas);
                }

                @Override
                public void failure() {

                }
            });
            headerView = LayoutInflater.from(context).inflate(R.layout.item_live_classified_header, null);
            listView.addHeaderView(headerView);
            gridView = (GridView) headerView.findViewById(R.id.item_live_classified_header_gv);
            gvAdapter = new LiveHeaderGVAdapter(context);
            gridView.setAdapter(gvAdapter);
//            VolleyInstance.getInstance().startRequest(UrlValues.CLASSIFIEDLIVEMENUURL, new VolleyResult() {
//                @Override
//                public void success(String resultStr) {
//                    Gson headerGson = new Gson();
//                    Type type = new TypeToken<List<LiveHeaderBean>>() {
//                    }.getType();
//                    headerDatas = headerGson.fromJson(resultStr, type);
//                    Log.d("LiveTabFragment", "headerDatas:" + headerDatas);
//                }
//
//                @Override
//                public void failure() {
//                    Log.d("LiveTabFragment", "请求失败");
//                }
//            });
            String json = "[{\"type\":\"column\",\"visible\":false,\"name\":\"TOP100\",\"id\":3},{\"type\":\"column\",\"visible\":false,\"name\":\"大直播\",\"id\":4},{\"type\":\"column\",\"visible\":false,\"name\":\"在现场\",\"id\":5},{\"type\":\"column\",\"visible\":false,\"name\":\"星在线\",\"id\":6},{\"type\":\"column\",\"visible\":false,\"name\":\"纵横谈\",\"id\":7},{\"type\":\"column\",\"visible\":false,\"name\":\"资讯\",\"id\":8},{\"type\":\"column\",\"visible\":false,\"name\":\"娱乐\",\"id\":9},{\"type\":\"column\",\"visible\":false,\"name\":\"本地\",\"id\":10},{\"type\":\"column\",\"visible\":false,\"name\":\"体育\",\"id\":11},{\"type\":\"column\",\"visible\":false,\"name\":\"时尚\",\"id\":12},{\"type\":\"column\",\"visible\":false,\"name\":\"汽车\",\"id\":13},{\"type\":\"column\",\"visible\":false,\"name\":\"科技\",\"id\":14},{\"type\":\"column\",\"visible\":false,\"name\":\"财经\",\"id\":15},{\"type\":\"column\",\"visible\":false,\"name\":\"生活\",\"id\":16}]";
            Gson headerGson = new Gson();
            Type type = new TypeToken<List<LiveHeaderBean>>() {
            }.getType();
            headerDatas = headerGson.fromJson(json, type);
            Log.d("LiveTabFragment", "headerDatas:" + headerDatas);

            if (headerDatas != null) {
                List<String> titles = new ArrayList<>();
                for (int i = 0; i < headerDatas.size(); i++) {
                    titles.add(headerDatas.get(i).getName());
                }
                if (titles != null) {
                    Log.d("LiveTabFragment", "titles:" + titles);
                    gvAdapter.setDatas(titles);
                    gvAdapter.setSelectedItem(0);
                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            gvAdapter.setSelectedItem(position);
                            String newUrl = UrlTool.doLiveClassifyUrl(headerDatas.get(position).getId(), 1);
                            VolleyInstance.getInstance().startRequest(newUrl, new VolleyResult() {
                                @Override
                                public void success(String resultStr) {
                                    Gson gson = new Gson();
                                    LiveBean bean = gson.fromJson(resultStr, LiveBean.class);
                                    List<LiveBean.LiveReviewBean> datas = bean.getLive_review();
                                    adapter.setDatas(datas);
                                }

                                @Override
                                public void failure() {

                                }
                            });
                        }
                    });
                }
            }
        }
    }

    private void initRotate() {
        View view = LayoutInflater.from(context).inflate(R.layout.item_header_rotate, null);
        listView.addHeaderView(view);
        viewPager = (ViewPager) view.findViewById(R.id.rotate_vp);
        textView = (TextView) view.findViewById(R.id.rotate_tv);
        imageView = (ImageView) view.findViewById(R.id.rotate_type_iv);
        pointLl = (LinearLayout) view.findViewById(R.id.rotate_point_container);
        vpAdapter = new RotateVpAdapter(context);
        viewPager.setAdapter(vpAdapter);
    }

    private void doRotate(LiveBean bean) {
        if (bean != null) {
//                    rotateDatas = new ArrayList<>();
            rotateDatas = bean.getTop();
//                    Log.d("rotate", "rotateDatas.size():" + rotateDatas.size()+"");
            List<String> rotateImgDatas = new ArrayList<>();
            if (rotateDatas != null) {
                for (int i = 0; i < rotateDatas.size(); i++) {
                    rotateImgDatas.add(rotateDatas.get(i).getImage());
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
                    LiveBean.TopBean topBean = rotateDatas.get(position % rotateDatas.size());
                    textView.setText(topBean.getRoomName());
                    int liveStatus = topBean.getLiveStatus();
                    boolean isVideo = topBean.isVideo();
                    boolean ismutilVideo = topBean.isMutilVideo();
                    boolean flag = isVideo || ismutilVideo;
                    /**
                     * liveStatus:
                     * 0: 提醒
                     * 1: 直播
                     * 3: 回顾
                     */
                    if (liveStatus == 0) {
                        imageView.setImageResource(R.drawable.selector_live_notice_btn);
                    } else if (liveStatus == 1) {
                        if (flag) {
                            imageView.setImageResource(R.mipmap.a5x);
                        } else {
                            imageView.setImageResource(R.mipmap.a5w);
                        }
                    } else {
                        if (flag) {
                            imageView.setImageResource(R.mipmap.a60);
                        } else {
                            imageView.setImageResource(R.mipmap.a5z);
                        }

                    }
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
                LiveBean.TopBean topBean = rotateDatas.get(0);
                textView.setText(topBean.getRoomName());
                int liveStatus = topBean.getLiveStatus();
                boolean isVideo = topBean.isVideo();
                boolean ismutilVideo = topBean.isMutilVideo();
                boolean flag = isVideo || ismutilVideo;
                /**
                 * liveStatus:
                 * 0: 提醒
                 * 1: 直播
                 * 3: 回顾
                 */
                if (liveStatus == 0) {
                    imageView.setImageResource(R.drawable.selector_live_notice_btn);
                } else if (liveStatus == 1) {
                    if (flag) {
                        imageView.setImageResource(R.mipmap.a5x);
                    } else {
                        imageView.setImageResource(R.mipmap.a5w);
                    }
                } else {
                    if (flag) {
                        imageView.setImageResource(R.mipmap.a60);
                    } else {
                        imageView.setImageResource(R.mipmap.a5z);
                    }

                }
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
