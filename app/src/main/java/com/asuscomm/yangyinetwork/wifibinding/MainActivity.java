package com.asuscomm.yangyinetwork.wifibinding;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.asuscomm.yangyinetwork.wifibinding.adapter.WifiAdapter;
import com.asuscomm.yangyinetwork.wifibinding.data.WifiItem;
import com.asuscomm.yangyinetwork.wifibinding.data.repo.WifiRepo;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerview_wifi)
    RecyclerView mRecyclerView;

    private WifiAdapter mAdapter;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUnbinder = ButterKnife.bind(this);
        initRecyclerView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    private void initRecyclerView() {
        mAdapter = new WifiAdapter(
                WifiRepo.getDummys()
        );

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }
}
