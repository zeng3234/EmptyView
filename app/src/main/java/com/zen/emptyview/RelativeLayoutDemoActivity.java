package com.zen.emptyview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.zen.lib.emptyview.EmptyView;

public class RelativeLayoutDemoActivity extends AppCompatActivity {

    private ListView mListView;
    private EmptyView mEmptyView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative);
        mListView = findViewById(R.id.relative_listview);
        mEmptyView = new EmptyView.Builder(mListView).build();
        findViewById(R.id.relative_tv_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEmptyView.showEmptyView();
            }
        });

        findViewById(R.id.relative_tv_bottom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEmptyView.hideEmptyView();
            }
        });

    }
}
