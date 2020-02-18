package com.zx.itemgroup;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Edittext输入内容改变的观察器
 * <p>
 * 作者： 周旭 on 2017年9月27日 0027.
 * 邮箱：374952705@qq.com
 * 博客：http://www.jianshu.com/u/56db5d78044d
 */

public abstract class TextChangeWatcher implements TextWatcher {

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        
    }


    @Override
    public void afterTextChanged(Editable s) {

    }
}
