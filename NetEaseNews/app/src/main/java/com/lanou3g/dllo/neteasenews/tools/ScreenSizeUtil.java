package com.lanou3g.dllo.neteasenews.tools;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by dllo on 16/9/13.
 * 获取屏幕宽高的工具类
 */
public class ScreenSizeUtil {
    public enum ScreenState {
        WIDTH,HEIGHT
    }
    public static int getScreenSize(Context context, ScreenState state){
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        if (state.equals(ScreenState.WIDTH)) {
            return metrics.widthPixels;
        } else if (state.equals(ScreenState.HEIGHT)){
            return metrics.heightPixels;
        } else {
            return metrics.heightPixels;
        }
    }
}
