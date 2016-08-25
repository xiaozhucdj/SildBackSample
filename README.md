# SildBackSample
viewpager实现简单右滑返回

先看看效果图

![这里写图片描述](https://github.com/xiaozhucdj/SildBackSample/blob/master/art/GIF.gif)


[这里是下载地址](https://github.com/xiaozhucdj/SildBackSample/blob/master/art/sample-debug.apk)



##使用方式：

在module的build.gradle中执行compile操作
```
compile 'com.github.xiaozhucdj:sildbacklibrary:1.1'
```

##使用说明
需要滑动返回的界面继承`SildBaseActivity`并实现以下方法
```
/**
     * @return 返回当前页面展示的view布局id
     */
    public abstract int setContentViewId();

    /**
     * 初始化view，可以butterknif结合使用
     * @param contentView
     */
    public abstract void initView(View contentView);

```
当展示的布局中存在viewpager时，需要使用自定义布局
```
<com.github.xiaozhucdj.sildbacklibrary.MatchViewPager
        android:id="@+id/viewpager"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
```


[详细介绍可参看blog：viewpager实现简单右滑返回](http://blog.csdn.net/chudaijiang/article/details/52314490)
