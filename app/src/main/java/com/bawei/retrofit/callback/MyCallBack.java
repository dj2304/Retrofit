package com.bawei.retrofit.callback;

/**
 * @param <T>
 */
public interface MyCallBack<T> {
    void onSuccess(T data);
    void onFail(String error);
}
