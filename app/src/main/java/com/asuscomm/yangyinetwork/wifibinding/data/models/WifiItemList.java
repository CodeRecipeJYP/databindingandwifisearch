package com.asuscomm.yangyinetwork.wifibinding.data.models;

import android.databinding.ObservableField;


/**
 * Created by jaeyoung on 8/10/17.
 */

public class WifiItemList {
    private static final String TAG = "WifiItemList";

    public final ObservableField<String> length = new ObservableField<>();

    public WifiItemList(String length) {
        this.length.set(length);
    }
}
