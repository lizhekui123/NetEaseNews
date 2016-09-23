package com.lanou3g.dllo.neteasenews.ui.fragment.topic;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lanou3g.dllo.neteasenews.R;
import com.lanou3g.dllo.neteasenews.model.bean.TopicTpBean;
import com.lanou3g.dllo.neteasenews.model.net.UrlValues;
import com.lanou3g.dllo.neteasenews.model.net.VolleyInstance;
import com.lanou3g.dllo.neteasenews.model.net.VolleyResult;
import com.lanou3g.dllo.neteasenews.ui.fragment.AbsBaseFragment;

/**
 * Created by dllo on 16/9/10.
 * 话题-关注页面
 */
public class TopicTabFollowFragment extends AbsBaseFragment {
    private ImageView seeFirstIv;
    private TextView seeFirstTitleTv;
    private TextView seeFirstNumTv;
    private ImageView seeFirstAddIv;
    private ImageView seeSecondIv;
    private TextView seeSecondTitleTv;
    private TextView seeSecondNumTv;
    private ImageView seeSecondAddIv;
    private ImageView seeThirdIv;
    private TextView seeThirdTitleTv;
    private TextView seeThirdNumTv;
    private ImageView seeThirdAddIv;
    private ImageView seeFourthIv;
    private TextView seeFourthTitleTv;
    private TextView seeFourthNumTv;
    private ImageView seeFourthAddIv;
    private ImageView quesFirstIv;
    private TextView quesFirstTitleTv;
    private TextView quesFirstNumTv;
    private ImageView quesFirstAddIv;
    private ImageView quesSecondIv;
    private TextView quesSecondTitleTv;
    private TextView quesSecondNumTv;
    private ImageView quesSecondAddIv;
    private ImageView quesThirdIv;
    private TextView quesThirdTitleTv;
    private TextView quesThirdNumTv;
    private ImageView quesThirdAddIv;
    private ImageView quesFourthIv;
    private TextView quesFourthTitleTv;
    private TextView quesFourthNumTv;
    private ImageView quesFourthAddIv;


    public static TopicTabFollowFragment newInstance() {
        
        Bundle args = new Bundle();
        
        TopicTabFollowFragment fragment = new TopicTabFollowFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_topic_follow;
    }

    @Override
    protected void initView() {
        seeFirstIv = byView(R.id.topic_follow_see_first_iv);
        seeFirstTitleTv = byView(R.id.topic_follow_see_first_title_tv);
        seeFirstNumTv = byView(R.id.topic_follow_see_first_num_tv);
        seeFirstAddIv = byView(R.id.topic_follow_see_first_add_iv);
        seeSecondIv = byView(R.id.topic_follow_see_second_iv);
        seeSecondTitleTv = byView(R.id.topic_follow_see_second_title_tv);
        seeSecondNumTv = byView(R.id.topic_follow_see_second_num_tv);
        seeSecondAddIv = byView(R.id.topic_follow_see_second_add_iv);
        seeThirdIv = byView(R.id.topic_follow_see_third_iv);
        seeThirdTitleTv = byView(R.id.topic_follow_see_third_title_tv);
        seeThirdNumTv = byView(R.id.topic_follow_see_third_num_tv);
        seeThirdAddIv = byView(R.id.topic_follow_see_third_add_iv);
        seeFourthIv = byView(R.id.topic_follow_see_fourth_iv);
        seeFourthTitleTv = byView(R.id.topic_follow_see_fourth_title_tv);
        seeFourthNumTv = byView(R.id.topic_follow_see_fourth_num_tv);
        seeFourthAddIv = byView(R.id.topic_follow_see_fourth_add_iv);
        quesFirstIv = byView(R.id.topic_follow_ques_first_iv);
        quesFirstTitleTv = byView(R.id.topic_follow_ques_first_title_tv);
        quesFirstNumTv = byView(R.id.topic_follow_ques_first_num_tv);
        quesFirstAddIv = byView(R.id.topic_follow_ques_first_add_iv);
        quesSecondIv = byView(R.id.topic_follow_ques_second_iv);
        quesSecondTitleTv = byView(R.id.topic_follow_ques_second_title_tv);
        quesSecondNumTv = byView(R.id.topic_follow_ques_second_num_tv);
        quesSecondAddIv = byView(R.id.topic_follow_ques_second_add_iv);
        quesThirdIv = byView(R.id.topic_follow_ques_third_iv);
        quesThirdTitleTv = byView(R.id.topic_follow_ques_third_title_tv);
        quesThirdNumTv = byView(R.id.topic_follow_ques_third_num_tv);
        quesThirdAddIv = byView(R.id.topic_follow_ques_third_add_iv);
        quesFourthIv = byView(R.id.topic_follow_ques_fourth_iv);
        quesFourthTitleTv = byView(R.id.topic_follow_ques_fourth_title_tv);
        quesFourthNumTv = byView(R.id.topic_follow_ques_fourth_num_tv);
        quesFourthAddIv = byView(R.id.topic_follow_ques_fourth_add_iv);
    }

    @Override
    protected void initDatas() {
        VolleyInstance.getInstance().startRequest(UrlValues.TOPICCONTENTURL, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                TopicTpBean bean = gson.fromJson(resultStr,TopicTpBean.class);
                setView(bean);
            }

            @Override
            public void failure() {

            }
        });
    }

    /**
     * 设置页面
     */
    private void setView(TopicTpBean bean) {

    }
}
