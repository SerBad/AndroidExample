package com.serbad.androidexample.mvp;

import android.content.Context;

import com.serbad.androidexample.HttpAPI;
import com.serbad.androidexample.common.results.BaseResult;
import com.serbad.androidexample.common.results.Beauty;
import com.serbad.androidexample.common.utils.LogUtil;
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
        this.api = new HttpAPI().getAPI();
    }

    @Override
    public void loadData(int num, int page, final BaseCallback callback) {
        api.getBeauty(num, page).enqueue(new Callback<BaseResult<Beauty>>() {
            @Override
            public void onResponse(Call<BaseResult<Beauty>> call, Response<BaseResult<Beauty>> response) {
                response.body().getResults().get(0);

                LogUtil.i(response.body().getResults().get(0).getUrl());
                callback.onResponse(response);

            }

            @Override
            public void onFailure(Call<BaseResult<Beauty>> call, Throwable t) {
                LogUtil.i(t + "");
                callback.onFailure(t);
            }
        });

    }
}
