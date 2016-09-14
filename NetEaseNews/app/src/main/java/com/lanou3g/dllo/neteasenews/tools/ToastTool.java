package com.lanou3g.dllo.neteasenews.tools;

import android.widget.Toast;

import com.lanou3g.dllo.neteasenews.ui.app.NetEaseNewsApp;

/**
 * Created by dllo on 16/9/8.
 * Toast的工具类
 */
public final class ToastTool {
    private ToastTool() {}
    private static boolean isDebug = true;
    public static void shortMsg (String msg){
        if (isDebug) {
            Toast.makeText(NetEaseNewsApp.getContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }public static void longMsg (String msg){
        if (isDebug) {
            Toast.makeText(NetEaseNewsApp.getContext(), msg, Toast.LENGTH_LONG).show();
        }
    }
}
