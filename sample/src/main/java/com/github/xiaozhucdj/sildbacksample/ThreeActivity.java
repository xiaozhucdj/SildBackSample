package com.github.xiaozhucdj.sildbacksample;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.xiaozhucdj.sildbacklibrary.MatchViewPager;
import com.github.xiaozhucdj.sildbacklibrary.NanLuoBaseActivity;

import java.util.ArrayList;
import java.util.List;

public class ThreeActivity extends NanLuoBaseActivity {


    @Override
    public int getContentViewId() {
        // TODO Auto-generated method stub
        return R.layout.activity_three;
    }

    @Override
    public void initView(View contentView) {
        TextView textView = (TextView) contentView.findViewById(R.id.title);
        textView.setText("带有viewpager 的 sildback页面");

        MatchViewPager viewPager = (MatchViewPager) contentView.findViewById(R.id.viewpager);

        LayoutInflater inflater = LayoutInflater.from(this);
        List<View> list = new ArrayList<View>();
        View view = null;
        for (int i = 0; i < 3; i++) {
            view = inflater.inflate(R.layout.child_viewpager_layout, null);
            TextView testtextview = (TextView) view.findViewById(R.id.testtextview);
            testtextview.setText("viewpager：" + i);
            list.add(view);
        }
        viewPager.setAdapter(new ViewPagerAdapter(list));
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    class ViewPagerAdapter extends PagerAdapter {
        private List<View> viewList;

        public ViewPagerAdapter(List<View> paramArrayList) {
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
