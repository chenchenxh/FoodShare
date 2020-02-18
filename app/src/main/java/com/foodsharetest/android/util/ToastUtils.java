package com.foodsharetest.android.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils{
    private Toast mToast;

    public void showShort(Context context, String  string) {
        mToast = Toast.makeText(context, null, Toast.LENGTH_SHORT); //下面用setText不用makeText，为了取消小米手机自带的Toast应用名
        mToast.setText(string);
        mToast.show();
    }
}
