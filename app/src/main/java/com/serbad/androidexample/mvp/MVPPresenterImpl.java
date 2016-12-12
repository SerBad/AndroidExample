package com.serbad.androidexample.mvp;

import android.content.Context;
import android.os.Handler;

import com.serbad.androidexample.common.results.Beauty;
import com.serbad.androidexample.common.results.BeautyResults;
import com.serbad.androidexample.mvp.base.BaseCallback;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by zhoutingjie on 2016/12/12.
 */

public class MVPPresenterImpl implements MVPPresenter {
    private Context context;
    private Handler handler;
    private MVPView view;
    private MVPModel model;
    private List<Beauty> list;

    public MVPPresenterImpl(Context context, final MVPView view) {
        this.context = context;
        this.handler = new Handler(context.getMainLooper());
        this.view = view;
        this.model = new MVPModelImpl(context);
        this.list = new ArrayList<>();

        handler.post(new Runnable() {
            @Override
            public void run() {
                model.loadData(10, 1, new BaseCallback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        list = ((BeautyResults) response.body()).results;
                        view.setAdapter(list);
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {

                    }
                });
            }
        });

    }

    @Override
    public void onStart() {


    }

    @Override
    public void onDestroy() {
        this.context = null;
        this.handler.removeCallbacksAndMessages(null);
        this.view = null;
        this.model = null;
        this.list = null;
    }
}
