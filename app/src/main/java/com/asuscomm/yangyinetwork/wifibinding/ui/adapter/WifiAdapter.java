package com.asuscomm.yangyinetwork.wifibinding.ui.adapter;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wifiitem, parent, false);

        WifiItemViewModel viewModel = new WifiItemViewModel();
        WifiitemBinding binding = WifiitemBinding.bind(view);

        final WifiViewHolder viewholder =
                new WifiViewHolder(view, binding, viewModel);

        return viewholder;
    }

    @BindingAdapter("items")
    public static void setItemsFromXml(RecyclerView recyclerView, List<WifiItem> items) {
        Log.d(TAG, "setItemsFromXml() called with: recyclerView = [" + recyclerView + "], items = [" + items + "]");
        WifiAdapter adapter = (WifiAdapter) recyclerView.getAdapter();

        adapter.setItems(items);
    }

    private void setItems(List<WifiItem> items) {
        Log.d(TAG, "setItems() called with: items = [" + items + "]");
        mItems = items;
        notifyDataSetChanged();
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
