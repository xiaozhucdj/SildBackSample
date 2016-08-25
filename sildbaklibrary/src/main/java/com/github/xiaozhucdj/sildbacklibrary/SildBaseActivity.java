package com.github.xiaozhucdj.sildbacklibrary;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public abstract class SildBaseActivity extends AppCompatActivity {


    //关闭当前页面
    private final static int FINISH_ACTIVITY = 997;
    //展示内容view，第二页
    private final static int GOTO_CONTENTVIEW = 998;
    //展示第一页，透明页面
    private final static int GOTO_TRANSPARENTVIEW = 999;

    private ViewPager localViewPager;
    private ArrayList<View> views = new ArrayList<View>();
    private Handler baseHandler = new Handler() {
        public void handleMessage(Message paramMessage) {
            switch (paramMessage.what) {
                default:
                    return;
                case GOTO_CONTENTVIEW:
                    localViewPager.setCurrentItem(views.size() - 1, true);
                    return;
                case GOTO_TRANSPARENTVIEW:
                    localViewPager.setCurrentItem(0, true);
                    return;
                case FINISH_ACTIVITY:
                    finish();
                    return;
            }
        }
    };
    private View contentView;

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        //设置界面notitle
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.base);
        localViewPager = (ViewPager) findViewById(R.id.view_switch);

        View contentView =  LayoutInflater.from(this).inflate(setContentViewId(), null);


        initFramwork(this, contentView);

        initView(contentView);

    }

    /**
     * @return 返回当前页面展示的view布局id
     */
    public abstract int setContentViewId();
    public abstract void initView(View contentView);


    /**
     * 实例化viewpager两页滑动框架
     *
     * @param contentView 第二页显示的界面
     */
    protected void initFramwork(Context context, View contentView) {

        View localView = LayoutInflater.from(this).inflate(R.layout.temp, null);

//        ColorDrawable localColorDrawable = new ColorDrawable(Color.TRANSPARENT);
//        localView.setBackgroundDrawable(localColorDrawable);
        localView.setBackgroundResource(android.R.color.transparent);
        views.add(localView);
        Drawable drawable = contentView.getBackground();
        if (drawable == null) {
//            contentView.setBackgroundColor(Color.parseColor("#fffafafa"));
            contentView.setBackgroundResource(R.drawable.common_background);
        }

        views.add(contentView);
        SildBaseAdapter localNanLuoBaseAdapter = new SildBaseAdapter(views);
        localViewPager.setAdapter(localNanLuoBaseAdapter);

//        localViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        Message localMessage = Message.obtain();
        localMessage.what = GOTO_CONTENTVIEW;
        //开启第二页
        baseHandler.sendMessageDelayed(localMessage, 200L);

        //设置滑动到上一页时finish掉该activity
        localViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.e("onPageScrolled", "position:" + position);
                if (position != 0)
                    return;
                //当到第一页时发送消息将当前activity finish掉
                baseHandler.sendEmptyMessageDelayed(FINISH_ACTIVITY, 200L);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    /**
     * 回到第一页（透明页）
     */
    protected void pageOut() {
        if (baseHandler == null)
            return;
        Message localMessage = Message.obtain();
        localMessage.what = GOTO_TRANSPARENTVIEW;
        baseHandler.sendMessage(localMessage);
    }

    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.left_back) {
            pageOut();
        }
    }

    @Override
    public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
        if (paramInt == KeyEvent.KEYCODE_BACK) {
            pageOut();
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        pageOut();
    }

    class SildBaseAdapter extends PagerAdapter {
        private ArrayList<View> viewList;

        public SildBaseAdapter(ArrayList<View> paramArrayList) {
            this.viewList = paramArrayList;
        }

        /**
         * 这个方法，是从ViewGroup中移出当前View
         **/

        public void destroyItem(ViewGroup paramViewGroup, int paramInt,
                                Object paramObject) {
            View localView = (View) this.viewList.get(paramInt);
            paramViewGroup.removeView(localView);
        }

        public int getCount() {
            return this.viewList.size();
        }

        /**
         * 这个方法，return一个对象，这个对象表明了PagerAdapter适配器选择哪个对象*放在当前的ViewPager中
         **/
        public Object instantiateItem(ViewGroup paramViewGroup, int paramInt) {
            View localView = (View) this.viewList.get(paramInt);
            paramViewGroup.addView(localView, 0);
            return this.viewList.get(paramInt);
        }

        /**
         * 这个方法，在帮助文档中原文是could be implemented as return view ==
         * object,*也就是用于判断是否由对象生成界面
         **/
        public boolean isViewFromObject(View paramView, Object paramObject) {
            return paramView == paramObject;
        }
    }

}
