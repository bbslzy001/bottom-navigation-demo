package com.example.myapplication3.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.myapplication3.R;
import com.example.myapplication3.adapter.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity
{
    private ViewPager2 viewPager;
    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* 初始化 ViewPager，并设置适配器 */
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new ViewPagerAdapter(this));

        /* 初始化 BottomNavigationView，并设置监听 */
        navigation = findViewById(R.id.navigation);
        /* lambda 表达式和函数式接口：
         * lambda 表达式的目标类型必须是“函数式接口（functional interface）”
         * 函数式接口代表只包含一个抽象方法的接口
         * 函数式接口可以包含多个默认方法、类方法，但是只能声明一个抽象方法
         */
        navigation.setOnItemSelectedListener(item ->
        {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) viewPager.setCurrentItem(0);
            else if (itemId == R.id.navigation_analyse) viewPager.setCurrentItem(1);
            else if (itemId == R.id.navigation_individual) viewPager.setCurrentItem(2);
            else return false;
            return true;
        });

        /* 实现滑动时底部导航栏的选中项联动
         *
         * 使用 ViewPager2 的 OnPageChangeListener 接口，并在 onPageSelected() 回调方法中设置底部导航栏的选中项
         * 在这个回调方法中，首先获取当前 ViewPager2 中选中的 Fragment 的位置，然后通过底部导航栏的 getMenu() 方法获取菜单项，最后将菜单项中对应位置的子项设置为选中状态
         */
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback()
        {
            @Override
            public void onPageSelected(int position)
            {
                navigation.getMenu().getItem(position).setChecked(true);
            }
        });
    }
}