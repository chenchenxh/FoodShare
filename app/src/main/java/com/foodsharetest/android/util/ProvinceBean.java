package com.foodsharetest.android.util;

import com.contrarywind.interfaces.IPickerViewData;

public class ProvinceBean implements IPickerViewData {
    private long id;
    private String name;
    private String code;
    private String province;

//    public ProvinceBean(long id,String name){
//        this.id = id;
//        this.name = name;
//    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    //这个用来显示在PickerView上面的字符串,PickerView会通过getPickerViewText方法获取字符串显示出来。
    @Override
    public String getPickerViewText() {
        return name;
    }
}
