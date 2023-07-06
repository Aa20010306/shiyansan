package com.android.forecast.base;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.forecast.R;
import com.android.forecast.city.CityFragmentPagerAdapter;
import com.android.forecast.city.CityWeatherFragment;
import com.android.forecast.citymanager.CityManagerActivity;
import com.android.forecast.database.DBManager;

import java.util.ArrayList;
import java.util.List;
//将所有的信息显示到界面上  他最主要的工作是View兼容和页面主题相关处理
// AppCompatTextView之类的内部都是有兼容操作的,而继承AppCompatActivity后,其会在内部将TextView替换为AppCompatTextView
//class 子类名 extends 父类名 implenments 接口名  创建了监听器
//Android中的View类代表用户界面中基本的构建块。一个View在屏幕中占据一个矩形区域、并且负责绘制和事件处理。
// View是所有widgets的基础类，widgets是我们通常用于创建和用户交互的组件，比如按钮、文本输入框等等。
// 子类ViewGroup是所有布局（layout）的基础类。layout是一个不看见的容器，里面堆放着其他的view或者ViewGroup，并且设置他们的布局属性。
//1，设置属性，比如可以给一个TextView设置文本属性。每一个view都有特定的属性和设置他们的方法。一些可以遇见的属性可以在view的xml文件中提前设置。
//2，设置焦点，Android框架本身可以处理用户移动的焦点事件。你可以强制调用requestFocus()方法，来聚焦一个view。
//3，设置监听器，view允许客户端来设置监听器，监听感兴趣的事件。当感兴趣的事件发生，系统会通知监听器。
// 比如，所有的view都支持你设置获得和失去焦点事件的监听器，你只需要通过方法setOnFocusChangedListener（android.view.View.OnFocusChangedListener）来设置。其他的view子类都支持自己特性的事件监听器设置，比如Button支持设置点击事件监听器。
//4，设置可见性，你可以通过方法setVisibility（）来设置view的隐藏和可见。
//Android框架会负责view的测量（measure）、布局（layout）、绘制（draw）。一般情况下你不需要调用相关的方法，除非你自己定义了一个view。
//通过内部类实现
//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        MyClickListener mcl = new MyClickListener();
//        Button btn1 = findViewById(R.id.btn1);
//        btn1.setOnClickListener(mcl);
//    }
//
//    class MyClickListener implements View.OnClickListener {
//
//        @Override
//        public void onClick(View v) {
//            Log.e("Button1","通过内部类来实现按钮点击监听");
//        }
//    }
//
//}
      //      通过当前Activity来实现
//public class ButtonActivity extends AppCompatActivity implements View.OnClickListener {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.button_activity);
//        Button btn3= findViewById(R.id.btn3);
//        * 方法3： 通过当前activity 实现OnClickListener接口，然后绑定到指定的button上，
//        * this表示当前类的实例化对象
//        btn3.setOnClickListener(this);
//    @Override
//    public void onClick(View v) {
//        Log.e("Button3","通过当前activity来实现按钮点击监听");
//    }
//}
       // 通过匿名内部类实现
//
//btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("Button2","通过匿名内部类来实现按钮点击监听");
//            }
//        });
       // 通过xml绑定来实现（使用最多的方法之一）
//<Button
//        android:id="@+id/btn5"
//        android:layout_width="match_parent"
//        android:layout_height="wrap_content"
//        android:text="@string/btn4"
//        android:onClick="myclick"
//        />
//将所有的button点击事件放在一个click中处理。v.getId会获取点击button的标志位，
// 返回一个int类型的整数。可以根据这个返回值和通过R.id获取到的标志位来比较，然后执行相应的操作。
//    public void myclick(View v){
//        switch(v.getId()){
//            case R.id.btn4 :
//                Log.e("Button4","通过xml文件绑定监听");
//                break;
//            case R.id.btn5:
//                Log.e("Button5","通过xml文件绑定监听");
//                break;
//        }
//    }
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //ImageView（图像视图），直接继承自View类，
    // 它的主要功能是用于显示图片，实际上它不仅仅可以用来显示图片，任何Drawable对象都可以使用ImageView来显示。
    ImageView addCityIv,moreIv;
    //线性布局
    LinearLayout pointLayout;
    //相对布局
    RelativeLayout outLayout;
    //布局管理器允许左右翻转带数据的页面，
    ViewPager mainVp;
    //ViewPager经常和Fragment一起使用，
    // 并且官方还提供了专门的FragmentPagerAdapterFragmentStatePagerAdapter类供Fragment中的ViewPager使用
//    ViewPager的数据源
    //fragment 定义和管理自己的布局，具有自己的生命周期，并且可以处理自己的输入事件。
    List<Fragment>fragmentList;
//    表示需要显示的城市的集合
    List<String>cityList;
//    表示ViewPager的页数指数器显示集合
    List<ImageView>imgList;
    //
    private CityFragmentPagerAdapter adapter;
    //保存的相对较小键值对集合，
    private SharedPreferences pref;
    private int bgNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //通过findViewById找到id将按钮和相应的active绑定
        addCityIv = findViewById(R.id.main_iv_add);
        moreIv = findViewById(R.id.main_iv_more);
        pointLayout = findViewById(R.id.main_layout_point);
        outLayout = findViewById(R.id.main_out_layout);
        exchangeBg();
        //找到main_vp的id,
        mainVp = findViewById(R.id.main_vp);
//        添加点击事件，设置监听器
        addCityIv.setOnClickListener(this);
        moreIv.setOnClickListener(this);

        fragmentList = new ArrayList<>();
        cityList = DBManager.queryAllCityName();  //获取数据库包含的城市信息列表
        imgList = new ArrayList<>();

        if (cityList.size()==0) {
            cityList.add("北京");
        }
        /* 因为可能搜索界面点击跳转此界面，会传值，所以此处获取一下*/
        try {
            Intent intent = getIntent();
            String city = intent.getStringExtra("city");
            if (!cityList.contains(city)&&!TextUtils.isEmpty(city)) {
                cityList.add(city);
            }
        }catch (Exception e){
            Log.i("animee","程序出现问题了！！");
        }
//        初始化ViewPager页面的方法
        initPager();
        adapter = new CityFragmentPagerAdapter(getSupportFragmentManager(), fragmentList);
        mainVp.setAdapter(adapter);
//        创建小圆点指示器
        initPoint();
//        设置最后一个城市信息
        mainVp.setCurrentItem(fragmentList.size()-1);
//        设置ViewPager页面监听
        setPagerListener();
    }


    // 换壁纸的函数
    public void exchangeBg(){
        pref = getSharedPreferences("bg_pref", MODE_PRIVATE);
        bgNum = pref.getInt("bg", 2);
        switch (bgNum) {
            case 0:
                outLayout.setBackgroundResource(R.mipmap.bg);
                break;
            case 1:
                outLayout.setBackgroundResource(R.mipmap.bg2);
                break;
            case 2:
                outLayout.setBackgroundResource(R.mipmap.bg3);
                break;
        }

    }
    private void setPagerListener() {
        /* 设置监听事件
        * 添加一个侦听器，该侦听器将在页面更改或增量滚动时被调用*/
        mainVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < imgList.size(); i++) {
                    imgList.get(i).setImageResource(R.mipmap.a1);
                }
                imgList.get(position).setImageResource(R.mipmap.a2);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void initPoint() {
//        创建小圆点 ViewPager页面指示器的函数
        for (int i = 0; i < fragmentList.size(); i++) {
            ImageView pIv = new ImageView(this);
            pIv.setImageResource(R.mipmap.a1);
            pIv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) pIv.getLayoutParams();
            lp.setMargins(0,0,20,0);
            imgList.add(pIv);
            pointLayout.addView(pIv);
        }
        imgList.get(imgList.size()-1).setImageResource(R.mipmap.a2);
    }

    private void initPager() {
        /* 创建Fragment对象，添加到ViewPager数据源当中*/
        for (int i = 0; i < cityList.size(); i++) {
            CityWeatherFragment cwFrag = new CityWeatherFragment();
            Bundle bundle = new Bundle();
            bundle.putString("city",cityList.get(i));
            cwFrag.setArguments(bundle);
            fragmentList.add(cwFrag);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.main_iv_add:
                intent.setClass(this,CityManagerActivity.class);
                break;
            case R.id.main_iv_more:
                intent.setClass(this, MoreActivity.class);
                break;
        }
        startActivity(intent);
    }

    /* 当页面重写加载时会调用的函数，这个函数在页面获取焦点之前进行调用，此处完成ViewPager页数的更新*/
    @Override
    protected void onRestart() {
        super.onRestart();
//        获取数据库当中还剩下的城市集合
        List<String> list = DBManager.queryAllCityName();
        if (list.size()==0) {
            list.add("北京");
        }
        cityList.clear();    //重写加载之前，清空原本数据源
        cityList.addAll(list);
//        剩余城市也要创建对应的fragment页面
        fragmentList.clear();
        initPager();
        adapter.notifyDataSetChanged();
//        页面数量发生改变，指示器的数量也会发生变化，重写设置添加指示器
        imgList.clear();
        pointLayout.removeAllViews();   //将布局当中所有元素全部移除
        initPoint();
        mainVp.setCurrentItem(fragmentList.size()-1);
    }
}
