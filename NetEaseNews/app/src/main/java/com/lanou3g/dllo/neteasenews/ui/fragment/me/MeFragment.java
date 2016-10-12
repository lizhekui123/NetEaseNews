package com.lanou3g.dllo.neteasenews.ui.fragment.me;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lanou3g.dllo.neteasenews.R;
import com.lanou3g.dllo.neteasenews.model.db.LiteOrmInstance;
import com.lanou3g.dllo.neteasenews.model.db.UserInfoBean;
import com.lanou3g.dllo.neteasenews.tools.ToastTool;
import com.lanou3g.dllo.neteasenews.ui.activity.CollectActivity;
import com.lanou3g.dllo.neteasenews.ui.fragment.AbsBaseFragment;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by dllo on 16/9/9.
 * 我页面
 */
public class MeFragment extends AbsBaseFragment {
    private LinearLayout meUnLoginLl;
    private LinearLayout meLoginQqLl;
    private LinearLayout meHasLoginLl;
    private ImageView meDpIv;
    private TextView meUsernameTv;
    private Button meLogoutBtn;
    private LinearLayout meCollectLl;
    private boolean hasLogin;
    private String name;
    private String icon;

    public static MeFragment newInstance() {
        MeFragment fragment = new MeFragment();
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initView() {
        meUnLoginLl = byView(R.id.me_unLogin_ll);
        meLoginQqLl = byView(R.id.me_login_qq_ll);
        meHasLoginLl = byView(R.id.me_has_login_ll);
        meDpIv = byView(R.id.me_dp_iv);
        meUsernameTv = byView(R.id.me_username_tv);
        meLogoutBtn = byView(R.id.me_logout_btn);
        meCollectLl = byView(R.id.me_collect_ll);
    }

    @Override
    protected void initDatas() {
        meLoginQqLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        meLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LiteOrmInstance.getInstance().deleteAllUser();
                meUnLoginLl.setVisibility(View.VISIBLE);
                meHasLoginLl.setVisibility(View.GONE);
                hasLogin = false;
            }
        });
        meCollectLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasLogin) {
                    goTo(CollectActivity.class, name);
                } else {
                    ToastTool.shortMsg("您还没有登录");
                }
            }
        });
    }

    private void login() {
        /**
         * 获取第三方平台
         */
        Platform platform = ShareSDK.getPlatform(context, QQ.NAME);
        /**
         * 授权
         */
        platform.authorize();
        /**
         * 获取用户信息
         */
        platform.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                ToastTool.shortMsg("登录完成");
                meHasLoginLl.setVisibility(View.VISIBLE);
                meUnLoginLl.setVisibility(View.GONE);
                /**
                 * 获取qq的头像和名字
                 */
                PlatformDb db = platform.getDb();
                name = db.getUserName();
                icon = db.getUserIcon();
                if (!icon.isEmpty()) {
                    UserInfoBean uib = new UserInfoBean(true, name, icon);
                    LiteOrmInstance.getInstance().insert(uib);
                    meUsernameTv.setText(name);
                    Picasso.with(context).load(icon).into(meDpIv);
                }
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                ToastTool.shortMsg("登录错误");
            }

            @Override
            public void onCancel(Platform platform, int i) {
                ToastTool.shortMsg("取消登录");
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        List<UserInfoBean> userList = LiteOrmInstance.getInstance().queryAllUser();
        if (userList.size() > 0) {
            meUnLoginLl.setVisibility(View.GONE);
            meHasLoginLl.setVisibility(View.VISIBLE);
            UserInfoBean userInfoBean = userList.get(0);
            hasLogin = true;
            name = userInfoBean.getUsername();
            icon = userInfoBean.getIconUrl();
            meUsernameTv.setText(name);
            Picasso.with(context).load(icon).into(meDpIv);
        } else {
            meUnLoginLl.setVisibility(View.VISIBLE);
            meHasLoginLl.setVisibility(View.GONE);
            hasLogin = false;
        }
    }
}
