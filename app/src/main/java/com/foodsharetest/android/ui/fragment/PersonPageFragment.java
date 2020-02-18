package com.foodsharetest.android.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.foodsharetest.android.R;
import com.foodsharetest.android.db.LoginUser;
import com.foodsharetest.android.ui.activity.PersonInfo;
import com.foodsharetest.android.ui.activity.Setting;
import com.foodsharetest.android.util.PhotoUtils;
import com.foodsharetest.android.widget.RoundImageView;

public class PersonPageFragment extends Fragment implements View.OnClickListener {
    private ImageView setting;
    private LinearLayout info;
    private TextView info_name,info_account;
    private RoundImageView portrait;
    private LoginUser loginUser = LoginUser.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_person_page, container, false);
        setting = (ImageView)view.findViewById(R.id.setting);
        info = (LinearLayout)view.findViewById(R.id.info);
        info_name = (TextView)view.findViewById(R.id.info_name);
        portrait = (RoundImageView)view.findViewById(R.id.portrait);


        info.setOnClickListener(this);
        setting.setOnClickListener(this);

        //登录则初始化用户的信息
        initInfo();

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        //在onStart中init，修改信息后返回不会出现没有修改的情况
        initInfo();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //点击设置按钮的逻辑
            case R.id.setting:
                Intent intent = new Intent(getActivity(), Setting.class);
                getActivity().startActivity(intent);
                break;
            case R.id.info:
                Intent intent1 = new Intent(getActivity(), PersonInfo.class);
                startActivity(intent1);
                break;
            default:
                break;
        }
    }

    //
    private void initInfo(){
        info_name.setText(loginUser.getName());
        portrait.setImageBitmap((new PhotoUtils()).byte2bitmap(loginUser.getPortrait()));
    }
}

