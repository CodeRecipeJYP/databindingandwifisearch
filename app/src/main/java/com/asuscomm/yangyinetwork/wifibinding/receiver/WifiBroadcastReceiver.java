package com.asuscomm.yangyinetwork.wifibinding.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;

import java.util.List;

/**
 * Created by jaeyoung on 8/9/17.
 */

public class WifiBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "WifiBroadcastReceiver";
    private static WifiBroadcastReceiver sInstance;
    private final WifiManager mWifiManager;

    public static WifiBroadcastReceiver getInstance() {
        if (sInstance == null) {
            throw new ExceptionInInitializerError("call init(Context) first");
        }

        return sInstance;
    }

    private WifiBroadcastReceiver(Context context) {
        WifiManager wifiManager;
        wifiManager = (WifiManager) context.getApplicationContext()
                .getSystemService(Context.WIFI_SERVICE);
        mWifiManager = wifiManager;

        IntentFilter intentFilter = new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        context.registerReceiver(this,
                intentFilter);
    }

    public void startScans() {
        mWifiManager.startScan();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive() called with: context = [" + context.toString() + "]," +
                " intent = [" + intent.toString() + "]");

        List<ScanResult> scanResults = mWifiManager.getScanResults();
        Log.d(TAG, "initWifiManager: scanresult=" + scanResults.toString());
    }

    public static void init(Context context) {
        sInstance = new WifiBroadcastReceiver(context);
    }

    public static void destroy() {
        Log.d(TAG, "destroy: ");
        sInstance = null;
    }
}
