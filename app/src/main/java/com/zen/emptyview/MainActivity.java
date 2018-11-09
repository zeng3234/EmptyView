package com.zen.emptyview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<ItemBean> mDemoList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        MainAdapter adapter = new MainAdapter(mDemoList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent it = new Intent(MainActivity.this, mDemoList.get(position).clsName);
                startActivity(it);
            }
        });
    }

    private void initData() {
        mDemoList.add(new ItemBean("LinearLayout", LinearLayoutDemoActivity.class));
    }

    static class ItemBean {
        public String name;
        public Class clsName;

        public ItemBean() {
        }

        public ItemBean(String name, Class clsName) {
            this.name = name;
            this.clsName = clsName;
        }
    }

    public class MainAdapter extends BaseQuickAdapter<ItemBean, BaseViewHolder> {


        public MainAdapter(@Nullable List<ItemBean> data) {
            super(R.layout.main_item, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ItemBean item) {
            helper.setText(R.id.main_item_tv_name, item.name);
        }
    }
}
