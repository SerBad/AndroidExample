package com.serbad.androidexample.common.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.serbad.androidexample.R;
import com.serbad.androidexample.common.results.Beauty;
import com.serbad.androidexample.common.utils.LogUtil;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by zhoutingjie on 2016/12/12.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<Beauty> list;

    public RecyclerViewAdapter(Context context, List<Beauty> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_mvp, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        String url =  list.get(0).getUrl();
        Picasso.with(context)
                .load(Uri.parse(list.get(position).getUrl()))
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(viewHolder.image_view);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image_view;


        public ViewHolder(View view) {
            super(view);
            image_view = (ImageView) view.findViewById(R.id.image_view);

        }
    }
}