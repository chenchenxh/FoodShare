package com.foodsharetest.android.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.foodsharetest.android.R;
import com.foodsharetest.android.db.model.User;
import com.foodsharetest.android.util.ActivityCollector;
import com.foodsharetest.android.util.MD5;
import com.foodsharetest.android.util.PhotoUtils;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class Register extends AppCompatActivity implements View.OnClickListener {
    private EditText et_account_name,et_password;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        setContentView(R.layout.activity_register);


        et_account_name = (EditText) findViewById(R.id.et_account_name);
        et_password = (EditText) findViewById(R.id.et_password);
        register = (Button)findViewById(R.id.register);
        Connector.getDatabase();
        //注册逻辑实现
        register.setOnClickListener(this);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                String name = et_account_name.getText().toString();
                String password = et_password.getText().toString();
                password = MD5.md5(password);  //MD5加密
                List<User> users = LitePal.where("name==?", name).find(User.class);
                Toast mToast = Toast.makeText(this, null, Toast.LENGTH_SHORT); //下面用setText不用makeText，为了取消小米手机自带的Toast应用名

                //判断用户名是否存在
                if (!users.isEmpty()) {
                    mToast.setText("该用户名已存在");
                    mToast.show();
                }
                //如果用户名不存在，则新建用户
                else {
                    User user = new User();
                    user.setName(name);
                    user.setPassword(password);
                    //默认不记住密码，并设置默认头像
                    user.setPortrait((new PhotoUtils()).file2byte(this ,"default_portrait.jpg"));
                    user.setRemember(0);
                    user.save();

                    mToast.setText("注册成功");
                    mToast.show();

                    Intent intent = new Intent(Register.this, Login.class);
                    startActivity(intent);
                }
        }
    }

}
