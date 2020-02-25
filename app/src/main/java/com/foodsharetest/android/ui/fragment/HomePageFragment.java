package com.foodsharetest.android.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.foodsharetest.android.R;
import com.foodsharetest.android.db.LoginUser;
import com.foodsharetest.android.db.model.Article;
import com.foodsharetest.android.db.model.User;
import com.foodsharetest.android.ui.adapter.ListPageAdapter;
import com.google.android.material.tabs.TabLayout;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class HomePageFragment extends Fragment {
    private View view,follow_view,recommend_view;
    private ArrayList<View> aList = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private ViewPager vpager;
    private TabLayout tabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_home_page, container, false);
        layoutInflater = getLayoutInflater();
        vpager = (ViewPager)view.findViewById(R.id.view_pager);
        tabLayout = (TabLayout)view.findViewById(R.id.tab_layout);
        follow_view = layoutInflater.inflate(R.layout.viewpager_follow_pager, null,false);
        recommend_view = layoutInflater.inflate(R.layout.viewpager_recommend_pager, null,false);

        initView();

        return view;
    }

    private void initView(){
        //将两个view加入到List中，注意此处的add顺序就是pager的顺序
        aList.add(follow_view);
        aList.add(recommend_view);

        //设置pager的Adapter
        vpager.setAdapter(new ListPageAdapter(aList));
        //设置viewpage的监听器
        vpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //使用selected
            @Override
            public void onPageSelected(int position) {
                tabLayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //设置tablayout的监听器
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //初始化进入为消息界面
        //因为互相调用，pager和tablayout两个设置一个就可以了
        vpager.setCurrentItem(0);
    }
}
