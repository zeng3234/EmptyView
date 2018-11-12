package com.zen.lib.emptyview;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 常用场景， listview/RecyclerView/详情界面等
 */
public class EmptyView {

    private static final String DEFAULT_EMPTY_TIPS = "暂无数据";
    private static final String DEFAULT_EMPTY_RELOAD = "点击重试";
    private static final int DEFAULT_HEIGHT = -66666666;

    private static final String TAG = "EmptyView";
    private ViewGroup.LayoutParams layoutParams;
    private int mLastHeight = DEFAULT_HEIGHT;
    private boolean isEmptyAttach;
    private boolean isErrAttach;

    //params start;
    private View mBindView;
    private View mEmptyView;
    private View mErrView;
    private View.OnClickListener mRetryClickListener;
    //params end;

    private EmptyView(View bindView, View emptyView, View errView, View.OnClickListener listener) {
        this.mBindView = bindView;
        this.mEmptyView = emptyView;
        this.mErrView = errView;
        this.mRetryClickListener = listener;


        if (mErrView != null && mRetryClickListener != null) {
            mErrView.setOnClickListener(mRetryClickListener);
        }

    }

    public void showEmptyView() {
        if (isEmptyAttach) {
            return;
        }
        layoutParams = mBindView.getLayoutParams();
        if (mLastHeight == DEFAULT_HEIGHT) {
            mLastHeight = layoutParams.height;
        }
        ViewGroup parent = (ViewGroup) mBindView.getParent();
        if (isLinearLayout(parent)) {
            if (mLastHeight != ViewGroup.LayoutParams.MATCH_PARENT) {
                layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            }
            parent.addView(mEmptyView, findIndex(parent, mBindView), layoutParams);
        } else {
            parent.addView(mEmptyView, layoutParams);
        }
        isEmptyAttach = true;
    }

    public void hideEmptyView() {
        if (mEmptyView != null && isEmptyAttach) {
            layoutParams.height = mLastHeight;
            ((ViewGroup) mEmptyView.getParent()).removeView(mEmptyView);
            isEmptyAttach = false;
        }
    }

    public void showErrView() {
        if (isErrAttach || mErrView == null) {
            return;
        }
        layoutParams = mBindView.getLayoutParams();
        if (mLastHeight == DEFAULT_HEIGHT) {
            mLastHeight = layoutParams.height;
        }
        ViewGroup parent = (ViewGroup) mBindView.getParent();
        if (isLinearLayout(parent)) {
            if (mLastHeight != ViewGroup.LayoutParams.MATCH_PARENT) {
                layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            }
            parent.addView(mErrView, findIndex(parent, mBindView), layoutParams);
        } else {
            parent.addView(mErrView, layoutParams);
        }
        isErrAttach = true;
    }

    public void hideErrView() {
        if (mErrView != null && isErrAttach) {
            layoutParams.height = mLastHeight;
            ((ViewGroup) mErrView.getParent()).removeView(mErrView);
            isErrAttach = false;
        }
    }

    public void onDestroy() {
        mBindView = null;
        mEmptyView = null;
        mErrView = null;
        mRetryClickListener = null;
    }


    private static void LOGE(String str) {
        Log.e(TAG, str);
    }

    private static View buildDefaultView(Context pContext) {
        LinearLayout.LayoutParams lps = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lps.gravity = Gravity.CENTER | Gravity.CENTER_VERTICAL;
        LinearLayout ll = new LinearLayout(pContext);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setGravity(Gravity.CENTER);
        TextView tv = new TextView(pContext);
        tv.setText(DEFAULT_EMPTY_TIPS);
        ll.addView(tv, lps);
        Button btn = new Button(pContext);
        btn.setBackgroundColor(Color.TRANSPARENT);
        btn.setText(DEFAULT_EMPTY_RELOAD);
        ll.addView(btn, lps);
        return ll;
    }

    private boolean isLinearLayout(ViewGroup view) {
        return view instanceof LinearLayout;
    }

    private int findIndex(ViewGroup viewGroup, View view) {
        int count = viewGroup.getChildCount();

        for (int i = 0; i < count; i++) {
            if (viewGroup.getChildAt(i) == view) {
                return i;
            }
        }
        return -1;
    }


    public static class Builder {
        private View bindView;
        private int emptyId;
        private View emptyView;
        private int errId;
        private View errView;
        private View.OnClickListener retryListener;

        public Builder(View pView) {
            this.bindView = pView;
        }

        public Builder emptyId(int resid) {
            this.emptyId = resid;
            return this;
        }

        public Builder emptyView(View pView) {
            this.emptyView = pView;
            return this;
        }

        public Builder errId(int resid) {
            this.errId = resid;
            return this;
        }

        public Builder errView(View pView) {
            this.errView = pView;
            return this;
        }

        public Builder retryListener(View.OnClickListener pClickListener) {
            this.retryListener = pClickListener;
            return this;
        }

        public EmptyView build() {
            if (bindView == null) {
                LOGE("view == null...");
                return null;
            }
            if (emptyView == null && emptyId != 0) {
                emptyView = LayoutInflater.from(bindView.getContext()).inflate(emptyId, null);
            } else if (emptyView == null) {
                emptyView = buildDefaultView(bindView.getContext());
                LOGE("empty==== null" + emptyView);
            }


            if (errView == null && errId != 0) {
                errView = LayoutInflater.from(bindView.getContext()).inflate(errId, null);
            }

            return new EmptyView(bindView, emptyView, errView, retryListener);
        }
    }


}
