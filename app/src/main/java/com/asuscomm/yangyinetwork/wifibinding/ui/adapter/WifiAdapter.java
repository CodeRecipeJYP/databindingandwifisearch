package com.asuscomm.yangyinetwork.wifibinding.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asuscomm.yangyinetwork.wifibinding.R;
import com.asuscomm.yangyinetwork.wifibinding.data.models.WifiItem;
import com.asuscomm.yangyinetwork.wifibinding.databinding.WifiitemBinding;
import com.asuscomm.yangyinetwork.wifibinding.ui.adapter.viewholder.WifiViewHolder;
import com.asuscomm.yangyinetwork.wifibinding.ui.viewmodel.WifiItemViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaeyoung on 8/9/17.
 */

public class WifiAdapter extends RecyclerView.Adapter<WifiViewHolder> {
    private static final String TAG = "DeviceMenuAdapter";
    private List<WifiItem> mItems;

    public WifiAdapter() {
        this.mItems = new ArrayList<>();
    }

    @Override
    public WifiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wifiitem, parent, false);

        WifiItemViewModel viewModel = new WifiItemViewModel();

        WifiitemBinding binding = WifiitemBinding.bind(itemView);

        final WifiViewHolder viewholder =
                new WifiViewHolder(itemView, binding, viewModel);

        return viewholder;
    }

    @Override
    public void onBindViewHolder(WifiViewHolder holder, int position) {
        holder.configureWith(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
