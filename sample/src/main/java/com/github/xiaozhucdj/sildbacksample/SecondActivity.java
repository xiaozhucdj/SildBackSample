package com.github.xiaozhucdj.sildbacksample;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.github.xiaozhucdj.sildbacklibrary.SildBaseActivity;

public class SecondActivity extends SildBaseActivity {

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }*/

    @Override
    public int setContentViewId() {
        // TODO Auto-generated method stub
        return R.layout.activity_main;
    }

    @Override
    public void initView(View contentView) {
        TextView textView = (TextView) contentView.findViewById(R.id.title);
        textView.setText("普通的sildback页面");
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.button:
                startActivity(new Intent(this,ThreeActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
