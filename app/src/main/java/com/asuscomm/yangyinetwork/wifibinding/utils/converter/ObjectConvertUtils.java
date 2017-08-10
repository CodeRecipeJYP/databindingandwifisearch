package com.asuscomm.yangyinetwork.wifibinding.utils.converter;

import android.net.wifi.ScanResult;

import com.asuscomm.yangyinetwork.wifibinding.data.models.WifiItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaeyoung on 8/10/17.
 */

public class ObjectConvertUtils {
    public static List<WifiItem> convertToWifiItem(List<ScanResult> scanResults) {
        List<WifiItem> wifiItems = new ArrayList<>();
        for (ScanResult scanResult :
                scanResults) {
            wifiItems.add(convertToWifiItem(scanResult));
        }

        return wifiItems;
    }

    private static WifiItem convertToWifiItem(ScanResult scanResult) {
        WifiItem wifiItem = new WifiItem(scanResult.SSID);
        return wifiItem;
    }
}
