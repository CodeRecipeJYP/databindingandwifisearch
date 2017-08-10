package com.asuscomm.yangyinetwork.wifibinding;

import android.Manifest;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.asuscomm.yangyinetwork.wifibinding.adapter.WifiAdapter;
import com.asuscomm.yangyinetwork.wifibinding.consts.Configs;
import com.asuscomm.yangyinetwork.wifibinding.data.WifiItemList;
import com.asuscomm.yangyinetwork.wifibinding.data.repo.WifiRepo;
import com.asuscomm.yangyinetwork.wifibinding.databinding.ActivityMainBinding;
import com.asuscomm.yangyinetwork.wifibinding.receiver.WifiBroadcastReceiver;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @BindView(R.id.recyclerview_wifi)
    RecyclerView mRecyclerView;

    private WifiAdapter mAdapter;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        databind();

        mUnbinder = ButterKnife.bind(this);
        initRecyclerView();
        chkPermissions();
    }

    private void databind() {
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setWifiitemlist(new WifiItemList("length = 4"));
    }

    private void chkPermissions() {
        List<String> permissions = new ArrayList<>();
        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);

        AppController.getInstance().chkPermissions(permissions, this, this::initWifiManager);
    }

    private void initWifiManager() {
        Log.d(TAG, "initWifiManager: ");
        WifiBroadcastReceiver.init(this);
        WifiBroadcastReceiver.getInstance().startScans();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Configs.WIFIPERMISSION_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "onRequestPermissionsResult: PERMISSION_GRANTED");
                initWifiManager();
            } else {
                Log.d(TAG, "onRequestPermissionsResult: PERMISSION_DENIED");
                AppController.getInstance().chkPermissions(permissions, this);
            }
        }
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
