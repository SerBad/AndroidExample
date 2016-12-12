package com.serbad.androidexample.mvp;

import com.serbad.androidexample.mvp.base.BaseCallback;
import com.serbad.androidexample.mvp.base.BaseModel;

/**
 * Created by zhoutingjie on 2016/12/12.
 */

public interface MVPModel<T> extends BaseModel<T> {
    void loadData(int num,int page,final BaseCallback callback);
}
