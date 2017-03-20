package com.serbad.androidexample.common.results;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoutingjie on 2016/12/12.
 */

public class BaseResult<T> implements Serializable {

    private boolean error;
    private ArrayList<T> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public ArrayList<T> getResults() {
        return results;
    }

    public void setResults(ArrayList<T> results) {
        this.results = results;
    }
}
