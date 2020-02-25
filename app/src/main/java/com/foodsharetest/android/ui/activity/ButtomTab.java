package com.foodsharetest.android.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.foodsharetest.android.R;
import com.foodsharetest.android.util.ActivityCollector;
import com.foodsharetest.android.util.DataGenerator;
import com.google.android.material.tabs.TabLayout;

public class ButtomTab extends AppCompatActivity {
    private TabLayout tab_layout;
    private Fragment[] framensts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        setContentView(R.layout.buttom_tab);


        framensts = DataGenerator.getFragments();
        initView();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    private void initView() {
        tab_layout = (TabLayout) findViewById(R.id.bottom_tab_layout);


        //设置监听器
        tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d("food","click");
                //如果是点击第三个，则是添加好友圈，否则则更新fragment
                if(tab.getPosition()==2){
                    Intent intent = new Intent(ButtomTab.this, AddArticle.class);
                    startActivity(intent);
                }else {
                    onTabItemSelected(tab.getPosition());

                    // Tab 选中之后，改变各个Tab的状态
                    for (int i = 0; i < tab_layout.getTabCount(); i++) {
                        if(i==2) continue;
                        View view = tab_layout.getTabAt(i).getCustomView();
                        ImageView icon = (ImageView) view.findViewById(R.id.tab_content_image);
                        TextView text = (TextView) view.findViewById(R.id.tab_content_text);

                        if (i == tab.getPosition()) { // 选中状态，修改字体颜色和图片，背景未实现
                            //                        view.setBackgroundColor(Color.parseColor("#a9a9a9"));
                            icon.setImageResource(DataGenerator.mTabResPressed[i]);
                            text.setTextColor(getResources().getColor(android.R.color.black));
                        } else {// 未选中状态
                            //                        view.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            icon.setImageResource(DataGenerator.mTabRes[i]);
                            text.setTextColor(getResources().getColor(android.R.color.darker_gray));
                        }
                    }
                }
            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // 提供自定义的布局添加Tab，注意此处的加载页面需要在设置Listener之后，不然会导致第一次点击事件失效
        for(int i = 0; i<5; i++){
            tab_layout.addTab(tab_layout.newTab().setCustomView(DataGenerator.getTabView(this,i)));
        }

        //默认进入首页（需放在加载页面之后）
        tab_layout.getTabAt(0).select();
    }

    private void onTabItemSelected(int position){
        Fragment frag = null;
        switch (position){
            case 0:
                frag = framensts[0];
                break;
            case 1:
                frag = framensts[1];
                break;

            case 3:
                frag = framensts[2];
                break;
            case 4:
                frag = framensts[3];
                break;
        }
        //替换fragment
        if(frag!=null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.home_container,frag).commit();
        }
    }

    //当在主界面按下返回键，程序后台运行
    @Override
    public void onBackPressed(){
        moveTaskToBack(true);
    }
}

