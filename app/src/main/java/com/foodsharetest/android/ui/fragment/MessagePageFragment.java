package com.foodsharetest.android.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.foodsharetest.android.R;
import com.foodsharetest.android.ui.adapter.ListPageAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MessagePageFragment extends Fragment{
    private ViewPager vpager;
    private TabLayout tabLayout;
    private View view;
    private ArrayList<View> aList;
    private String[] titles = new String[]{"通知","消息"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_message_page, container, false);
        vpager = (ViewPager)view.findViewById(R.id.view_pager);
        tabLayout = (TabLayout)view.findViewById(R.id.tab_layout);

        initView();

        return view;
    }
    private void initView(){
        //添加pager，设置Adapter
        aList = new ArrayList<>();
        LayoutInflater li = getLayoutInflater();
        aList.add(li.inflate(R.layout.fragment_message_inform_pager,null,false));
        aList.add(li.inflate(R.layout.fragment_message_message_pager,null,false));
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
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}
