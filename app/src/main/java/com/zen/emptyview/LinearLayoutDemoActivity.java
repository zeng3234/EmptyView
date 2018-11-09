package com.zen.emptyview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zen.lib.emptyview.EmptyView;

public class LinearLayoutDemoActivity extends AppCompatActivity {

    private Button mTempBtn;
    private LinearLayout mRoot;
    private LinearLayout mOther;
    private Button mAddBtn;
    private Button mRemoveBtn;
    private Button mRemoveOther;

    private TextView mTestView;

    private EmptyView emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear);
        mRoot = findViewById(R.id.root);
        mOther = findViewById(R.id.root2);
        mAddBtn = findViewById(R.id.add);
        mRemoveBtn = findViewById(R.id.remove);
        mRemoveOther = findViewById(R.id.other);
        mTempBtn = new Button(this);
        mTempBtn.setText("我我我我");
        mTestView = findViewById(R.id.testview);

        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (emptyView == null) {
                    EmptyView.Builder builder = new EmptyView.Builder(mTestView);
                    emptyView = builder.build();
                }
                emptyView.showEmptyView();
            }
        });

        mRemoveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emptyView.hideEmptyView();
            }
        });

        mRemoveOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = mRoot.getChildCount();
                int index = -1;
                for (int i = 0; i < count; i++) {
                    if (mRoot.getChildAt(i) == mTempBtn) {
                        index = i;
                        break;
                    }
                }
                Toast.makeText(LinearLayoutDemoActivity.this, "iii " + index, Toast.LENGTH_SHORT).show();
                Button btn = new Button(LinearLayoutDemoActivity.this);
                btn.setText("wa哈哈哈哈");
                mRoot.addView(btn, index);
            }
        });
    }
}
