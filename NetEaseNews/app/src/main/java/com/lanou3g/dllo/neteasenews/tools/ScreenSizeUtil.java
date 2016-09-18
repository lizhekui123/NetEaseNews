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

    /**
     * 获取屏幕尺寸的方法
     * @param context 就是context
     * @param state 枚举: WIDTH和HEIGHT分别代表两种状态,分别为宽和高
     * @return 在state为WIDTH时,返回宽度metrics.widthPixels;
     *         state为WIDTH时,返回高度metrics.heightPixels;
     *         默认返回metrics.heightPixels.
     */
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
