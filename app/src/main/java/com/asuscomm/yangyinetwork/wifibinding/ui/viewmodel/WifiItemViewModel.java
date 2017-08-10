package com.asuscomm.yangyinetwork.wifibinding.ui.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.util.Log;

import com.asuscomm.yangyinetwork.wifibinding.R;
import com.asuscomm.yangyinetwork.wifibinding.data.models.WifiItem;

/**
 * Created by jaeyoung on 8/10/17.
 */


public class WifiItemViewModel extends ViewModel {
    private static final String TAG = "WifiItemViewModel";

    public ObservableField<String> wifiname = new ObservableField<>();
    public ObservableField<Integer> wifitypesrc = new ObservableField<>();
    private WifiItem item;

    public void setItem(WifiItem item) {
        this.item = item;
        wifiname.set(item.getWifiname());
        int src;
        if (item.getWifiname().length() > 8) {
            src = R.drawable.ic_signal_wifi_4_bar_black_24dp;
        } else {
            src = R.drawable.ic_signal_wifi_4_bar_lock_black_24dp;
        }
        wifitypesrc.set(src);
//        notifyChange();
    }

    public void onWifinameClicked() {
        Log.d(TAG, "onWifinameClicked: ");
    }

    public void onWifiiconClicked() {
        Log.d(TAG, "onWifiiconClicked: ");
    }

    @Bindable
    public boolean getSelected() {
        return true;
    }
}
