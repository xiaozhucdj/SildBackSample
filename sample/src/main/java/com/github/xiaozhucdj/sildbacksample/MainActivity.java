package com.github.xiaozhucdj.sildbacksample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.title);
        textView.setText("主页面，该页面没有加入slidback页面");
    }

    public void onClick(View v) {
//        super.onClick(v);
        switch (v.getId()) {
            case R.id.button:
                startActivity(new Intent(this,SecondActivity.class));
                break;
            default:
                break;
        }
    }
}
