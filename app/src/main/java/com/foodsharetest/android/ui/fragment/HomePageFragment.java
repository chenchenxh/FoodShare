package com.foodsharetest.android.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.foodsharetest.android.R;
import com.foodsharetest.android.db.LoginUser;
import com.foodsharetest.android.db.model.Article;
import com.foodsharetest.android.db.model.User;
import com.foodsharetest.android.ui.adapter.ArticleAdapter;
import com.foodsharetest.android.ui.adapter.ListPageAdapter;
import com.foodsharetest.android.ui.adapter.MessageAdapter;
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
    private RecyclerView recyclerView_follow,recyclerView_recommend;
    private ArticleAdapter followAdapter, recommendAdapter;
    private List<Article> followList = new ArrayList<>();
    private List<Article> recommendList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_home_page, container, false);
        layoutInflater = getLayoutInflater();
        vpager = (ViewPager)view.findViewById(R.id.view_pager);
        tabLayout = (TabLayout)view.findViewById(R.id.tab_layout);
        follow_view = layoutInflater.inflate(R.layout.viewpager_follow_pager, null,false);
        recommend_view = layoutInflater.inflate(R.layout.viewpager_recommend_pager, null,false);
        recyclerView_follow = (RecyclerView)follow_view.findViewById(R.id.recycler_view_follow);  //两个recycleView
        recyclerView_recommend = (RecyclerView)recommend_view.findViewById(R.id.recycler_view_recommmend);

        initView();

        return view;
    }

    private void initView(){
        //初始化数据，存储在messageList中
        initArticle();
        //初始化两个界面的recyclerVie（设置Manager，Adapter）
        recyclerView_follow.setLayoutManager(new LinearLayoutManager(follow_view.getContext()));
        followAdapter = new ArticleAdapter(followList);
        recyclerView_follow.setAdapter(followAdapter);

        recyclerView_recommend.setLayoutManager(new LinearLayoutManager(recommend_view.getContext()));
        recommendAdapter = new ArticleAdapter(recommendList);
        recyclerView_recommend.setAdapter(recommendAdapter);

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

    public void initArticle(){
        String[] title = {"这是一篇文章", "美食分享"};
        String[] text = {"测试一下文章", "蛋挞做法分享\n首先开始制作蛋挞皮\n" + "【配料】 低筋面粉220克，高筋面粉30克，黄油40克，细砂糖5克，盐2克，水125克，黄油180克（裹入时用）"+"\n将40克黄油室温软化。面粉和糖、盐混合，加入软化好的黄油。倒入清水，揉成面团。水要酌情加入。"+"\n大力揉成光滑面团（个人经验，抓上家里的男劳力可劲的使唤，大力揉上十多分钟）\n把揉好的光滑面团用保鲜膜包好，放进冰箱冷藏松驰20分钟。\n把180克黄油切成小片，放入保鲜袋排好（裹入用的黄油必须使用冷藏状态下比较坚硬的块状黄油）。用擀面杖把黄油压成厚薄均匀的一大片薄片。这时黄油会轻微软化，放入冰箱冷藏至重新变硬。"};
        for (int i = 0; i< title.length; i++){
            Article article = new Article(title[i], text[i], 1, LoginUser.getInstance().getUser());
            followList.add(article);
        }
        recommendList.add(LitePal.findFirst(Article.class));
    }

}
