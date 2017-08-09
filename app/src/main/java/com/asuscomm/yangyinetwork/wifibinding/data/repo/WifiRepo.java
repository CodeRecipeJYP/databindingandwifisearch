package com.asuscomm.yangyinetwork.wifibinding.data.repo;

import com.asuscomm.yangyinetwork.wifibinding.data.WifiItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaeyoung on 8/9/17.
 */

public class WifiRepo {
    public static List<WifiItem> getDummys() {
        List<WifiItem> items;

        items = new ArrayList<>();
        items.add(new WifiItem("wifi1"));
        items.add(new WifiItem("wifi2"));
        items.add(new WifiItem("wifi3"));

        return items;
    }
}
