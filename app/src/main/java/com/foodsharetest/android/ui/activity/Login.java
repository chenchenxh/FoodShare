package com.foodsharetest.android.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.foodsharetest.android.R;
import com.foodsharetest.android.db.LoginUser;
import com.foodsharetest.android.db.model.User;
import com.foodsharetest.android.util.ActivityCollector;
import com.foodsharetest.android.util.MD5;
import com.foodsharetest.android.util.ToastUtils;

import org.litepal.LitePal;

import java.util.List;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private EditText et_name,et_password;
    private Button login,register;
    private ImageView iv_eye,iv_more_account;
    private CheckBox cb_remember;
    private boolean passwordVisible = false;
    private ToastUtils toastUtils = new ToastUtils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        setContentView(R.layout.activity_login);


        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        et_name = (EditText) findViewById(R.id.et_account_name);
        et_password = (EditText) findViewById(R.id.et_password);
        iv_eye = (ImageView) findViewById(R.id.iv_eye);
        iv_more_account = (ImageView) findViewById(R.id.iv_more_accout);
        cb_remember = (CheckBox) findViewById(R.id.cb_remember);


        //设置监听器
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        iv_eye.setOnClickListener(this);
        iv_more_account.setOnClickListener(this);

        //如果是第一次打开并且不是退出登录，则直接登录
        if(getIntent().getBooleanExtra("first_in",false)) {
            Log.d("food","first_in");
            List<User> users = LitePal.findAll(User.class);
            for (User u : users) {
                if (u.getRemember().equals(1)) {
                    //登入并存入LoginUser
                    LoginUser.getInstance().login(u);
                    //启动主界面
                    Intent intent1 = new Intent(Login.this, ButtomTab.class);
                    startActivity(intent1);
                    toastUtils.showShort(Login.this, "账户" + u.getName() + " 登录成功");
                    break;
                }
            }
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    @Override
    protected void onStart(){
        super.onStart();
        //从本地数据库判断是否记住密码
        List<User> users = LitePal.findAll(User.class);
        for (User u:users){
            if(u.getRemember().equals(1)){
                et_name.setText(u.getName());
                et_password.setText("12345678");  //因为限制密码不能是全数字，利用12345678为通用密码简化验证，但会降低安全性
                cb_remember.setChecked(true);
                break;
            }
        }
    }

    @Override
    public void onClick(View v){
        String name = et_name.getText().toString();
        String password=et_password.getText().toString();
        switch (v.getId()){
            //注册按钮的逻辑
            case R.id.register:
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
                break;
            //登录按钮的逻辑
            case R.id.login:
                boolean login_flag = false; //是否登录成功的标志，
                User user = LitePal.where("name=?",name).findFirst(User.class);

                //根据user的remember状态，判断是否需要MD5加密
                if (password.equals("12345678")) password = user.getPassword();
                else password = MD5.md5(password);
                //密码正确则登录成功
                if (user.checkPassword(password)){
                    //更新remember状态
                    if(cb_remember.isChecked()) {
                        user.setRemember(1);
                    }else{
                        user.setRemember(0);
                    }
                    user.update(user.getId());
                    //用户登入，存入LoginUser
                    LoginUser.getInstance().login(user);
                    //启动主界面
                    Intent intent1 = new Intent(Login.this, ButtomTab.class);
                    startActivity(intent1);
                    login_flag = true;
                    toastUtils.showShort(Login.this,"账户"+user.getName()+" 登录成功");
                    break;
                }else {
                    user.setRemember(0);
                }

                if(login_flag == false){
                    toastUtils.showShort(Login.this,"登录失败");
                }
                break;
            //隐藏密码功能
            case R.id.iv_eye:
                if(passwordVisible){  //如果可见，则转为不可见
                    iv_eye.setSelected(false);
                    et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    passwordVisible = false;
                }else {  //如果不可见，则转为可见
                    iv_eye.setSelected(true);
                    et_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    passwordVisible = true;
                }
                break;
            //显示本地所有登录信息
            case R.id.iv_more_accout:
                List<User> users1 = LitePal.findAll(User.class);
                for(User u:users1) Log.d("food",""+u.toString());
                break;
        }
    }

}
