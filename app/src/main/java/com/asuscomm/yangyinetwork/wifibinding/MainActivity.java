package com.asuscomm.yangyinetwork.wifibinding;

import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.asuscomm.yangyinetwork.wifibinding.adapter.WifiAdapter;
import com.asuscomm.yangyinetwork.wifibinding.data.WifiItem;
import com.asuscomm.yangyinetwork.wifibinding.data.repo.WifiRepo;
import com.asuscomm.yangyinetwork.wifibinding.receiver.WifiBroadcastReceiver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_WIFI_STATE;
import static android.Manifest.permission.CHANGE_WIFI_STATE;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

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
//        chkPermissions();
        initWifiManager();
    }

    private void initWifiManager() {
        WifiBroadcastReceiver.init(this);
        WifiBroadcastReceiver.getInstance().startScans();
    }

    private void chkPermissions() {
        List<String> permissions = new ArrayList<>();
        permissions.add(ACCESS_COARSE_LOCATION);
        permissions.add(ACCESS_WIFI_STATE);
        permissions.add(CHANGE_WIFI_STATE);

        AppController.getInstance().chkPermissions(permissions, this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        WifiBroadcastReceiver.destroy();
    }

    private void initRecyclerView() {
        mAdapter = new WifiAdapter(
                WifiRepo.getDummys()
        );

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }
}
