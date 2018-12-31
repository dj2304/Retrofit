package com.bawei.retrofit.model;

import com.bawei.retrofit.callback.MyCallBack;
import com.bawei.retrofit.netWork.RetrofitManager;
import com.google.gson.Gson;

import java.util.Map;

import okhttp3.RequestBody;

public class IModelImpl<T> implements IModel {
    @Override
    public void requestData(String url, Map<String, String> params, final Class clazz, final MyCallBack callBack) {
//        Map<String, RequestBody> map = RetrofitManager.getInstance().generateRequestBody(params);
        RetrofitManager.getInstance().post(url, params).result(new RetrofitManager.HttpListener() {
            @Override
            public void onSuccess(String data) {
                try{
                    Object o = new Gson().fromJson(data, clazz);
                    if(callBack != null){
                        callBack.onSuccess(o);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    if(callBack != null){
                        callBack.onFail(e.getMessage());
                    }
                }
            }

            @Override
            public void onFail(String error) {
                if(callBack != null){
                    callBack.onFail(error);
                }
            }
        });
    }

}
