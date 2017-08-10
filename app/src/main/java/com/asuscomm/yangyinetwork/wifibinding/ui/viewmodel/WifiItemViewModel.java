package com.asuscomm.yangyinetwork.wifibinding.ui.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import com.asuscomm.yangyinetwork.wifibinding.data.models.WifiItem;

/**
 * Created by jaeyoung on 8/10/17.
 */

public class WifiItemViewModel extends ViewModel {
    public ObservableField<WifiItem> wifiitem = new ObservableField<>();

    public void setItem(WifiItem item) {
        wifiitem.set(item);
    }
}
