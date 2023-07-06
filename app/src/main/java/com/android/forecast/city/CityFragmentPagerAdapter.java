package com.android.forecast.city;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
//FragmentManager 类负责在应用的 fragment 上执行一些操作，
// 如添加、移除或替换操作，以及将操作添加到返回堆栈。
//FragmentActivity 及其子类（如 AppCompatActivity）都可以通过 getSupportFragmentManager() 方法访问 FragmentManager。
//fragment 可以托管一个或多个子 fragment。在 fragment 内，您可以通过 getChildFragmentManager() 获取对管理 fragment
// 子级的 FragmentManager 的引用。如果您需要访问其宿主 FragmentManager，可以使用 getParentFragmentManager()。
import android.support.v4.app.FragmentManager;
//
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class CityFragmentPagerAdapter extends FragmentStatePagerAdapter{
    List<Fragment>fragmentList;
    public CityFragmentPagerAdapter(FragmentManager fm,List<Fragment>fragmentLis) {
        super(fm);
        this.fragmentList = fragmentLis;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    int childCount = 0;   //表示ViewPager包含的页数
//    当ViewPager的页数发生改变时，必须要重写两个函数
    @Override
    public void notifyDataSetChanged() {
        this.childCount = getCount();
        super.notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        if (childCount>0) {
            childCount--;
            return POSITION_NONE;
        }
        return super.getItemPosition(object);
    }
}
