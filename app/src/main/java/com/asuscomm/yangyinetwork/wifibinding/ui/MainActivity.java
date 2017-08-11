package com.asuscomm.yangyinetwork.wifibinding.ui;

import android.Manifest;
import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.PackageManager;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.OnRebindCallback;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.ViewGroup;

import com.asuscomm.yangyinetwork.wifibinding.AppController;
import com.asuscomm.yangyinetwork.wifibinding.R;
import com.asuscomm.yangyinetwork.wifibinding.data.repo.WifiRepo;
import com.asuscomm.yangyinetwork.wifibinding.databinding.ActivityMainBinding;
import com.asuscomm.yangyinetwork.wifibinding.ui.adapter.WifiAdapter;
import com.asuscomm.yangyinetwork.wifibinding.ui.viewmodel.MainViewModel;
import com.asuscomm.yangyinetwork.wifibinding.utils.consts.Configs;
import com.asuscomm.yangyinetwork.wifibinding.utils.receiver.WifiBroadcastReceiver;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MainActivity extends LifecycleActivity {
    private static final String TAG = "MainActivity";

    @BindView(R.id.recyclerview_wifi)
    RecyclerView mRecyclerView;

    private WifiAdapter mAdapter;
    private Unbinder mUnbinder;
    private MainViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindviewmodel();

        mUnbinder = ButterKnife.bind(this);
        initRecyclerView();
        chkPermissions();
    }

    private void bindviewmodel() {
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        binding.setViewmodel(mViewModel);
        binding.addOnRebindCallback(new OnRebindCallback() {
            @Override
            public boolean onPreBind(ViewDataBinding binding) {
                Log.d(TAG, "onPreBind: ");
                ViewGroup sceneRoot = (ViewGroup) binding.getRoot();
                TransitionManager.beginDelayedTransition(sceneRoot);

                return true;
            }
        });
    }

    private void chkPermissions() {
        List<String> permissions = new ArrayList<>();
        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);

        AppController.getInstance().chkPermissions(permissions, this, this::initWifiManager);
    }

    private void initWifiManager() {
        Log.d(TAG, "initWifiManager: ");
        WifiBroadcastReceiver.init(this);
        mViewModel.wifireceiverInitialized();
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
        mAdapter = new WifiAdapter();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }
}
