package com.asuscomm.yangyinetwork.wifibinding.ui.presenter;

import android.util.Log;
import android.view.View;

import com.asuscomm.yangyinetwork.wifibinding.data.models.WifiItemList;

/**
 * Created by jaeyoung on 8/10/17.
 */

public class MainPresenter {
    private static final String TAG = "MainPresenter";
    private WifiItemList wifiItemList;
    private int length = 0;

    public void onScanClicked(View v) {
        length++;
        wifiItemList.length.set("Length : " + length);
        Log.d(TAG, "onScanClicked: ");
    }

    public void setWifiItemList(WifiItemList wifiItemList) {
        this.wifiItemList = wifiItemList;
    }
}
