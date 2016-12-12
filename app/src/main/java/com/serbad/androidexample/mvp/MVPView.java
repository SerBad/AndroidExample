package com.serbad.androidexample.mvp;

import com.serbad.androidexample.common.results.Beauty;

import java.util.List;

/**
 * Created by zhoutingjie on 2016/12/12.
 */

public interface MVPView {

    void setAdapter(List<Beauty> list);
}
