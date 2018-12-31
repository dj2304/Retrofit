package com.example.guoxinyu20181229.view;

public interface Iview<E> {
    void requestData(E e);
    void requestFail(E e);
}
