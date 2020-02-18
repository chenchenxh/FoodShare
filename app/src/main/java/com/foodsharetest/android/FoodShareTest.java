package com.foodsharetest.android;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.foodsharetest.android.ui.activity.Login;
import com.foodsharetest.android.util.ToastUtils;

import org.litepal.LitePal;

public class FoodShareTest extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean delete = false;
        if (!delete){
            Intent intent = new Intent(FoodShareTest.this, Login.class);
            intent.putExtra("first_in", true);
            startActivity(intent);
        }else{
            LitePal.deleteDatabase("food_db");
            (new ToastUtils()).showShort(this,"删除数据库成功");
        }

    }
}
