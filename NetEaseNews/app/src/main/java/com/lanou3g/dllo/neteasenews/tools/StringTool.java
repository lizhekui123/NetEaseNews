package com.lanou3g.dllo.neteasenews.tools;

/**
 * Created by dllo on 16/9/23.
 * 文字处理工具类
 */
public class StringTool {

    /**
     * 修改跟帖数文字
     */
    public static String doReplyNumText(int replyNum) {
        String str;
        if (replyNum >= 10000) {
            double temp = replyNum/10000.0;
            double finalNum = (double) (Math.round(temp*10))/10;
            str = finalNum + "万跟帖";
        } else {
            str = replyNum + "跟帖";
        }
        return str;
    }

    /**
     * 修改视频时长和播放数文字
     */
    public static String doVideoText (int length, int playCount) {
        String str;
        String str1;
        if (length/60 < 10){
            if(length%60 < 10){
                str1 = "0" + length/60 + ":0" + length%60;
            } else {
                str1 = "0" + length/60 + ":" + length%60;
            }
        } else {
            if(length%60 < 10){
                str1 = length/60 + ":0" + length%60;
            } else {
                str1 = length/60 + ":" + length%60;
            }
        }
        String str2;
        if (playCount >= 10000) {
            double temp = playCount/10000.0;
            double finalNum = (double) (Math.round(temp*10))/10;
            str2 = finalNum + "万播放";
        } else {
            str2 = playCount + "播放";
        }
        str = str1 + "/" + str2;
        return str;
    }

    /**
     * 修改标题文字
     */
    public static String doTitleText (String title) {
        String str  = "#" + title + "#";
        return str;
    }

    /**
     * 修改关注数文字
     */
    public static String doFollowNumText(int followNum) {
        String str;
        if (followNum >= 10000) {
            double temp = followNum/10000.0;
            double finalNum = (double) (Math.round(temp*10))/10;
            str = finalNum + "万关注";
        } else {
            str = followNum + "关注";
        }
        return str;
    }

    /**
     * 修改讨论数文字
     */
    public static String doDiscussionNumText(int discussionNum) {
        String str;
        if (discussionNum >= 10000) {
            double temp = discussionNum/10000.0;
            double finalNum = (double) (Math.round(temp*10))/10;
            str = finalNum + "万讨论";
        } else {
            str = discussionNum + "讨论";
        }
        return str;
    }

    /**
     * 修改提问数文字
     */
    public static String doQuesNumText(int quesNum){
        String str;
        if (quesNum >= 10000) {
            double temp = quesNum/10000.0;
            double finalNum = (double) (Math.round(temp*10))/10;
            str = finalNum + "万提问";
        } else {
            str = quesNum + "提问";
        }
        return str;
    }

    /**
     * 修改 关注数 + 提问数
     */
    public static String doQuesTwoNumText(int followNum, int quesNum){
        String str, str1, str2;
        str1 = doFollowNumText(followNum);
        str2 = doQuesNumText(quesNum);
        str = str1 + " · " + str2;
        return str;
    }

    /**
     * 修改 关注数 + 提问数
     */
    public static String doDiscussionTwoNumText(int followNum, int discussionNum){
        String str, str1, str2;
        str1 = doFollowNumText(followNum);
        str2 = doDiscussionNumText(discussionNum);
        str = str1 + " · " + str2;
        return str;
    }
}
