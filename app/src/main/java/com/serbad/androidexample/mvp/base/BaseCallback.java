package com.serbad.androidexample.mvp.base;


import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by zhoutingjie on 2016/12/12.
 */
public interface BaseCallback<T> {
    void onResponse(Response<T> response);

    void onFailure(Throwable t);
}