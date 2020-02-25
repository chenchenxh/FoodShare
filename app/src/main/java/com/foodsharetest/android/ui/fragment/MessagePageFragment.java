package com.foodsharetest.android.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.foodsharetest.android.R;
import com.foodsharetest.android.db.model.Message;
import com.foodsharetest.android.ui.adapter.ListPageAdapter;
import com.foodsharetest.android.ui.adapter.MessageAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MessagePageFragment extends Fragment{
    private ViewPager vpager;
    private TabLayout tabLayout;
    private View view, message_view, inform_view;
    private RecyclerView recyclerView_message, recyclerView_inform;
    private LayoutInflater layoutInflater;
    private MessageAdapter messageAdapter,informAdapter;
    private ArrayList<View> aList = new ArrayList<>();
    private List<Message> messageList = new ArrayList<>();
    private List<Message> informList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        layoutInflater = getLayoutInflater();
        view = inflater.inflate(R.layout.fragment_message_page, container, false);
        vpager = (ViewPager)view.findViewById(R.id.view_pager);
        tabLayout = (TabLayout)view.findViewById(R.id.tab_layout);
        message_view = layoutInflater.inflate(R.layout.viewpager_message_pager, null,false);  //两个View
        inform_view = layoutInflater.inflate(R.layout.viewpager_inform_pager, null,false);
        recyclerView_message = (RecyclerView)message_view.findViewById(R.id.recycler_view_message);  //两个recycleView
        recyclerView_inform = (RecyclerView)inform_view.findViewById(R.id.recycler_view_inform);

        initView();

        return view;

    }
    //初始化整个界面
    private void initView(){
        //初始化数据，存储在messageList中
        initMessage();
        //初始化两个界面的recyclerVie（设置Manager，Adapter）
        recyclerView_message.setLayoutManager(new LinearLayoutManager(message_view.getContext()));
        messageAdapter = new MessageAdapter(messageList);
        recyclerView_message.setAdapter(messageAdapter);

        recyclerView_inform.setLayoutManager(new LinearLayoutManager(message_view.getContext()));
        informAdapter = new MessageAdapter(informList);
        recyclerView_inform.setAdapter(informAdapter);

        //将两个view加入到List中，注意此处的add顺序就是pager的顺序
        aList.add(inform_view);
        aList.add(message_view);

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

        //用自定义的适配器中的接口Listener来定义监听器
        messageAdapter.setOnMyItemClickListener(new MessageAdapter.OnMyItemClickListener() {
            @Override
            public void myClick(View v, int position) {
                messageAdapter.removeItem(position);
            }
        });
        informAdapter.setOnMyItemClickListener(new MessageAdapter.OnMyItemClickListener() {
            @Override
            public void myClick(View v, int position) {
                informAdapter.removeItem(position);
            }
        });

        //初始化进入为消息界面
        //因为互相调用，pager和tablayout两个设置一个就可以了
        vpager.setCurrentItem(1);
    }

    private void initMessage(){
        String[] title = {"Breeze","Drizzle","陈宇","陈钊"};
        String[] text = {"亲爱的xiaotong你好，我是你的大号，很高兴认识你，希望这次程序不会出现问题","猪猪你好","你好","打个招呼"};
        for (int i = 0; i < title.length; i++){
            Message message = new Message(title[i],text[i]);
            messageList.add(message);
        }
        Message message = new Message("系统通知","您已获得美食家称号！");
        informList.add(message);
    }


}
