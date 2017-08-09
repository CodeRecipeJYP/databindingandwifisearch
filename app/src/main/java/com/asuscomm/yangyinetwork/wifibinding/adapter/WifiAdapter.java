package com.asuscomm.yangyinetwork.wifibinding.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asuscomm.yangyinetwork.wifibinding.R;
import com.asuscomm.yangyinetwork.wifibinding.data.WifiItem;
import com.asuscomm.yangyinetwork.wifibinding.adapter.viewholder.WifiViewHolder;

import java.util.List;

/**
 * Created by jaeyoung on 8/9/17.
 */

public class WifiAdapter extends RecyclerView.Adapter<WifiViewHolder> {
    private static final String TAG = "DeviceMenuAdapter";
    private List<WifiItem> mItems;

    public WifiAdapter(List<WifiItem> items) {
        this.mItems = items;
    }

    @Override
    public WifiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View deviceMenuView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wifiitem, parent, false);

        final WifiViewHolder deviceMenuViewHolder =
                new WifiViewHolder(deviceMenuView);

        return deviceMenuViewHolder;
    }

    @Override
    public void onBindViewHolder(WifiViewHolder holder, int position) {
        WifiItem deviceItem = mItems.get(position);
        holder.configureWith(deviceItem);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
