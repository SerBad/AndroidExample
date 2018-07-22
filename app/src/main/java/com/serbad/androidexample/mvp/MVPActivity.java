package com.serbad.androidexample.mvp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.serbad.androidexample.R;
import com.serbad.androidexample.common.adapter.RecyclerViewAdapter;
import com.serbad.androidexample.common.results.Beauty;

import java.util.ArrayList;
import java.util.List;

public class MVPActivity extends Activity implements MVPView {
    private Context context;
    private RecyclerView recycler_view;
    private MVPPresenter presenter;
    private List<Beauty> list = new ArrayList<>();
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        this.context = this;
        initView();
    }

    private void initView() {
        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);

        recycler_view.setLayoutManager(new LinearLayoutManager(context));
        adapter = new RecyclerViewAdapter(context, list);
        recycler_view.setAdapter(adapter);

        presenter = new MVPPresenterImpl(context, this);
        presenter.onStart();

    }


    @Override
    public void setAdapter(List<Beauty> list) {
        this.list.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

}

