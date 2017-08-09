package com.asuscomm.yangyinetwork.wifibinding;

import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.asuscomm.yangyinetwork.wifibinding.adapter.WifiAdapter;
import com.asuscomm.yangyinetwork.wifibinding.data.WifiItem;
import com.asuscomm.yangyinetwork.wifibinding.data.repo.WifiRepo;

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
        chkPermissions();
    }

    private void chkPermissions() {
        List<String> permissions = new ArrayList<>();
        permissions.add(ACCESS_COARSE_LOCATION);
        permissions.add(ACCESS_WIFI_STATE);
        permissions.add(CHANGE_WIFI_STATE);

        AppController.getInstance().chkPermissions(permissions, this);
    }

    private void initWifiSearch() {
        Log.d(TAG, "initWifiSearch: ");

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        intentFilter.addAction(WifiManager.RSSI_CHANGED_ACTION);
        intentFilter.addAction(WifiManager.ACTION_REQUEST_SCAN_ALWAYS_AVAILABLE);
//        getApplicationContext().registerReceiver(mReceiver, intentFilter);
//        mWifiManager = (WifiManager) this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
//
//        if(!mWifiManager.isWifiEnabled()) {
//            Log.d(TAG, "wifiSetting: Wifi Turn on");
//            mWifiManager.setWifiEnabled(true);
//            Log.d(TAG, "wifiSetting: Wifi Turned On");
//        }
//        Log.d(TAG, "wifiSetting: Wifi Scan start");
//        mWifiManager.startScan();
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
