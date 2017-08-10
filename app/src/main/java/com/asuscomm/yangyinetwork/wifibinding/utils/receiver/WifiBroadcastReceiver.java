package com.asuscomm.yangyinetwork.wifibinding.utils.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.asuscomm.yangyinetwork.wifibinding.data.models.WifiItem;
import com.asuscomm.yangyinetwork.wifibinding.utils.converter.ObjectConvertUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.ReplaySubject;

/**
 * Created by jaeyoung on 8/9/17.
 */

public class WifiBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "WifiBroadcastReceiver";
    private static WifiBroadcastReceiver sInstance;
    private WifiManager mWifiManager;
    private ReplaySubject<List<WifiItem>> mNotifier;

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
//        intentFilter.addAction(WifiManager.RSSI_CHANGED_ACTION);
//        intentFilter.addAction(WifiManager.ACTION_REQUEST_SCAN_ALWAYS_AVAILABLE);
        context.registerReceiver(this,
                intentFilter);

        mNotifier = ReplaySubject.create();
    }

    public void startScans() {
        Log.d(TAG, "startScans: ");
        mWifiManager.startScan();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
//        Log.d(TAG, "onReceive() called with: context = [" + context.toString() + "]," +
//                " intent = [" + intent.toString() + "]");

        List<ScanResult> scanResults = mWifiManager.getScanResults();
        mWifiManager.startScan();
        if (scanResults.size() > 0) {
            Log.d(TAG, "initWifiManager: scanresult.size=" + scanResults.size());
            List<WifiItem> wifiItems = ObjectConvertUtils.convertToWifiItem(scanResults);
            mNotifier.onNext(wifiItems);
        }
    }

    public static void init(Context context) {
        sInstance = new WifiBroadcastReceiver(context);
    }

    public static void destroy() {
        Log.d(TAG, "destroy: ");
        sInstance = null;
    }

    public Observable<List<WifiItem>> asObserverble() {
        return mNotifier;
    }
}
