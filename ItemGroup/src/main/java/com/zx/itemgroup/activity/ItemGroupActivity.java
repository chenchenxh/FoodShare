package com.zx.itemgroup.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zx.itemgroup.R;
import com.zx.itemgroup.widget.ItemGroup;

/**
 * 组合控件封装（提交信息及编辑信息界面及功能）
 */
public class ItemGroupActivity extends AppCompatActivity implements ItemGroup.ItemOnClickListener {

    private static final String TAG = "ItemGroupActivity";
    private Context mContext;
    private ItemGroup nameIG, idCardIG, birthdayIG, cityIG;
    private TextView resultTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_group);

        mContext = this;
        initView();
    }

    private void initView() {
        nameIG = (ItemGroup) findViewById(R.id.name_ig);
        idCardIG = (ItemGroup) findViewById(R.id.id_card_ig);
        birthdayIG = (ItemGroup) findViewById(R.id.select_birthday_ig);
        cityIG = (ItemGroup) findViewById(R.id.select_city_ig);
        birthdayIG.setItemOnClickListener(this);
        cityIG.setItemOnClickListener(this);
        resultTv = (TextView) findViewById(R.id.result_tv);
        findViewById(R.id.submit_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //提交
                String name = nameIG.getText();
                String idCard = idCardIG.getText();
                Log.i(TAG, "name: " + name);
                Log.i(TAG, "idCard: " + idCard);
                resultTv.setText("姓名:" + name + "\n" + "身份证:" + idCard);
            }
        });
    }

    @Override
    public void onItemClick(View v) {
        switch (v.getId()) {
            case R.id.select_birthday_ig:
                Toast.makeText(mContext, "点击了选择出生日期", Toast.LENGTH_SHORT).show();
                break;
            case R.id.select_city_ig:
                Toast.makeText(mContext, "点击了选择城市", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
