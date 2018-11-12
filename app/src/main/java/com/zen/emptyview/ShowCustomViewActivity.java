package com.zen.emptyview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.zen.lib.emptyview.EmptyView;

public class ShowCustomViewActivity extends AppCompatActivity {

    private ListView mListView;
    private EmptyView mEmptyView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        mListView = findViewById(R.id.relative_listview);
        mEmptyView = new EmptyView.Builder(mListView).emptyId(R.layout.custom_empty_view).errId(R.layout.custom_err_view).retryListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ShowCustomViewActivity.this, "点击重试", Toast.LENGTH_SHORT).show();
            }
        }).build();
        findViewById(R.id.custom_tv_showEmpty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEmptyView.showEmptyView();
            }
        });
        findViewById(R.id.custom_tv_hideEmpty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEmptyView.hideEmptyView();
            }
        });

        findViewById(R.id.relative_tv_showErr).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEmptyView.showErrView();
            }
        });
        findViewById(R.id.relative_tv_hideErr).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEmptyView.hideErrView();
            }
        });
    }
}
