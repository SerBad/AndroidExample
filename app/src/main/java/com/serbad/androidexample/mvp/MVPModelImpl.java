package com.serbad.androidexample.mvp;

import android.content.Context;

import com.serbad.androidexample.HttpAPI;
import com.serbad.androidexample.common.results.BeautyResults;
import com.serbad.androidexample.mvp.base.BaseCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhoutingjie on 2016/12/12.
 */

public class MVPModelImpl implements MVPModel {
    private Context context;
    private HttpAPI.API api;

    public MVPModelImpl(Context context) {
        this.context = context;
        this.api=new HttpAPI().getAPI();
    }

    @Override
    public void loadData(int num, int page, final BaseCallback callback) {
        api.getBeauty(num, page).enqueue(new Callback<BeautyResults>() {
            @Override
            public void onResponse(Call<BeautyResults> call, Response<BeautyResults> response) {
                callback.onResponse(call, response);
            }

            @Override
            public void onFailure(Call<BeautyResults> call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }
}
