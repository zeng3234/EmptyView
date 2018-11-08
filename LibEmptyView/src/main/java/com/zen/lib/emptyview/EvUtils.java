package com.zen.lib.emptyview;

import android.content.Context;
import android.util.TypedValue;

public class EvUtils {
    static int dp2px(Context context, float dpValue) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.getResources().getDisplayMetrics()) + 0.5f);
    }

    static int sp2px(Context context, float spValue) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, context.getResources().getDisplayMetrics()) + 0.5f);
    }

    static int px2dp(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    static int px2sp(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + .05f);
    }
}
