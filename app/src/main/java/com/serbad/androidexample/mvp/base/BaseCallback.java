package com.serbad.androidexample.mvp.base;


import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by zhoutingjie on 2016/12/12.
 */
public interface BaseCallback<T> {
    void onResponse(Call<T> call, Response<T> response);

    void onFailure(Call<T> call, Throwable t);
}