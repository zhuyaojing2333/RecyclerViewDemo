package com.bw.zyj.recyclerviewdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bw.zyj.recyclerviewdemo.R;
import com.bw.zyj.recyclerviewdemo.adapter.MyAdapter;
import com.bw.zyj.recyclerviewdemo.bean.Goods;
import com.bw.zyj.recyclerviewdemo.http.OkHttp;
import com.bw.zyj.recyclerviewdemo.listener.RecyclerViewClickListener;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

/**
 * Created by PigGhost on 2016/11/10.
 */
public class FragmentOne extends Fragment{
    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private String url = "http://m.yunifang.com/yunifang/mobile/goods/getall?random=39986&encode=2092d7eb33e8ea0a7a2113f2d9886c90&category_id=17";
    private List<Goods.DataBean> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentone, container, false);

        initData();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.id_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mRecyclerView.addOnItemTouchListener(new RecyclerViewClickListener(getContext(), new RecyclerViewClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getContext(),"Click "+list.get(position).getGoods_name(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(getContext(),"Long Click "+list.get(position).getGoods_name(),Toast.LENGTH_SHORT).show();
            }
        }));

        mAdapter = new MyAdapter(getActivity(), list);

        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    protected void initData() {
        OkHttp.getAsync(url, new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(final String result) throws Exception {

                Gson gson = new Gson();
                Goods g = gson.fromJson(result, Goods.class);

                list.addAll(g.getData());

            }
        });
    }
}
