package com.asuscomm.yangyinetwork.wifibinding.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.util.Log;

/**
 * Created by jaeyoung on 8/9/17.
 */

public class WifiBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "WifiBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: ");
        final String action = intent.getAction();
//
//        Log.d(TAG, "onReceive: Action RESULTSAVAILABLE is "+action.equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION) +" Action RSSICHANGED is "+action.equals(WifiManager.RSSI_CHANGED_ACTION));
//        if(action.equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION) | action.equals(WifiManager.RSSI_CHANGED_ACTION)) { // todo rssi_changed 아직 뭔지도 모름
//            mArr=mWifiManager.getScanResults();
//            Log.d(TAG, "BroadcastReceiver/onReceive: SCAN_RESULTS_AVAILABLE_ACTION ScanResults is "+mArr.toString());
//            mWifiManager.startScan();
//            Log.d(TAG, "BroadcastReceiver/onReceive: restartScan");
//
//            for(int i=0;i<mArr.size();i++) {
////                    mWifiNames.add(i,mArr.get(i).BSSID);
////                    mWifiLevels.add(i,new Integer(mArr.get(i).level).toString());
////                    mWifiSeceret.add(i,mArr.get(i).capabilities);
//
//                mAdapter.setWifilist(mArr);
//                mAdapter.notifyDataSetChanged();
//            }
//        } else if(action.equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {
//            Log.d(TAG, "BroadcastReceiver/onReceive: NETWORK_STATE_CHANGED_ACTION");
//            getActivity().getApplicationContext().sendBroadcast(new Intent("wifi.ON_NETWORK_STATE_CHANGED"));
//        }
//            if(intent.getAction().equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION))
//            {
//                mArr=mWifiManager.getScanResults();
//                Log.d(TAG, "AddDeviceWifiFragment/onReceive: SCAN_RESULTS_AVAILABLE_ACTION ScanResults is "+mArr.toString());
//                for(int i=0;i<mArr.size();i++)
//                {
//                    mWifiNames.add(i,mArr.get(i).BSSID);
//                    mWifiLevels.add(i,new Integer(mArr.get(i).level).toString());
//                    mWifiSeceret.add(i,mArr.get(i).capabilities);
//                    mAdapter.setWifilist(mWifiNames);
//                    mAdapter.notifyDataSetChanged();
//                }
//            }

    }
}
