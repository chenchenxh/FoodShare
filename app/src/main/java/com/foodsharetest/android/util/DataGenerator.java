package com.foodsharetest.android.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import com.foodsharetest.android.R;
import com.foodsharetest.android.ui.fragment.HomePageFragment;
import com.foodsharetest.android.ui.fragment.MessagePageFragment;
import com.foodsharetest.android.ui.fragment.PersonPageFragment;
import com.foodsharetest.android.ui.fragment.ShopPageFragment;

public class DataGenerator {

    public static final int []mTabRes = new int[]{R.drawable.outline_fastfood_black_18,R.drawable.outline_shopping_cart_black_18,R.drawable.add_box,R.drawable.outline_textsms_black_18,R.drawable.outline_person_black_18};
    public static final int []mTabResPressed = new int[]{R.drawable.baseline_fastfood_black_18,R.drawable.baseline_shopping_cart_black_18,R.drawable.add_box,R.drawable.baseline_textsms_black_18,R.drawable.baseline_person_black_18};
    public static final String []mTabTitle = new String[]{"首页","商城"," ","消息","我的"};

    public static Fragment[] getFragments(){
        Fragment fragments[] = new Fragment[4];
        fragments[0] = new HomePageFragment();
        fragments[1] = new ShopPageFragment();
        fragments[2] = new MessagePageFragment();
        fragments[3] = new PersonPageFragment();
        return fragments;
    }

// 获取了position的视图view，并初始化里面的元素，用于init
    public static View getTabView(Context context, int position){
        if(position == 2){
            return LayoutInflater.from(context).inflate(R.layout.fragment_tab2, null);
        }
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_tab,null);
        ImageView tabIcon = (ImageView) view.findViewById(R.id.tab_content_image);
        tabIcon.setImageResource(DataGenerator.mTabRes[position]);
        TextView tabText = (TextView) view.findViewById(R.id.tab_content_text);
        tabText.setText(mTabTitle[position]);
        return view;
    }
}
