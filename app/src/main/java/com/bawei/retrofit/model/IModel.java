package com.bawei.retrofit.model;


import com.bawei.retrofit.callback.MyCallBack;

import java.util.Map;

public interface IModel {
    void requestData(String url, Map<String, String> params, Class clazz, MyCallBack callBack);
}
