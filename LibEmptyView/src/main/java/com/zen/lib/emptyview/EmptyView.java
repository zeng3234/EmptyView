package com.zen.lib.emptyview;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 常用场景， listview/RecyclerView/详情界面等
 */
public class EmptyView {

    public static final String DEFAULT_EMPTY_TIPS = "暂无数据";
    public static final String DEFAULT_EMPTY_RELOAD = "点击重试";

    private static final String TAG = "EmptyView";

    public static View addEmptyView(View view) {

        if (view == null) {
            LOGE("view == null!");
            return null;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        ViewGroup parent = (ViewGroup) view.getParent();
        return null;
    }

    private void test() {

    }

    private View.OnClickListener mRetryClickListener;


    private static void LOGE(String str) {
        Log.e(TAG, str);
    }

    private View buildDefaultView(Context pContext) {
        LinearLayout.LayoutParams lps = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lps.gravity = Gravity.CENTER;
        LinearLayout ll = new LinearLayout(pContext);
        TextView tv = new TextView(pContext);
        tv.setText(DEFAULT_EMPTY_TIPS);
        ll.addView(tv, lps);
        Button btn = new Button(pContext);
        btn.setBackgroundColor(Color.TRANSPARENT);
        btn.setText(DEFAULT_EMPTY_RELOAD);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRetryClickListener != null) {
                    mRetryClickListener.onClick(v);
                }
            }
        });

        ll.addView(btn, lps);
        return ll;
    }


}
