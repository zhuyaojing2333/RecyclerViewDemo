package com.bw.zyj.recyclerviewdemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bw.zyj.recyclerviewdemo.R;
import com.bw.zyj.recyclerviewdemo.activity.AnimatorActivity;
import com.bw.zyj.recyclerviewdemo.adapter.MyAdapter;
import com.bw.zyj.recyclerviewdemo.bean.Goods;
import com.bw.zyj.recyclerviewdemo.http.OkHttp;
import com.bw.zyj.recyclerviewdemo.listener.RecyclerViewClickListener;
import com.bw.zyj.recyclerviewdemo.view.PullBaseView;
import com.bw.zyj.recyclerviewdemo.view.PullRecyclerView;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

/**
 * Created by PigGhost on 2016/11/10.
 */
public class FragmentOne extends Fragment implements PullBaseView.OnHeaderRefreshListener,
        PullBaseView.OnFooterRefreshListener {
    private PullRecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private String url = "http://m.yunifang.com/yunifang/mobile/goods/getall?random=39986&encode=2092d7eb33e8ea0a7a2113f2d9886c90&category_id=17";
    private List<Goods.DataBean> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentone, container, false);

        initData();

        mRecyclerView = (PullRecyclerView) view.findViewById(R.id.recyclerView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mRecyclerView.setOnHeaderRefreshListener(this); //设置下拉监听
        mRecyclerView.setOnFooterRefreshListener(this);//设置上拉监听



        mRecyclerView.addOnItemTouchListener(new RecyclerViewClickListener(getContext(), new RecyclerViewClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getContext(),"Click "+list.get(position).getGoods_name(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), AnimatorActivity.class);

                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(getContext(),"Long Click "+list.get(position).getGoods_name(),Toast.LENGTH_SHORT).show();
            }
        }));



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

                mAdapter = new MyAdapter(getActivity(), list);

                mRecyclerView.setAdapter(mAdapter);

            }
        });
    }

    @Override
    public void onFooterRefresh(PullBaseView view) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.notifyDataSetChanged();
                mRecyclerView.onFooterRefreshComplete();
            }
        }, 2000);
    }

    @Override
    public void onHeaderRefresh(PullBaseView view) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.notifyDataSetChanged();
                mRecyclerView.onHeaderRefreshComplete();
            }
        }, 3000);
    }
}
