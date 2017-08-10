package com.asuscomm.yangyinetwork.wifibinding.ui.adapter.viewholder;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.asuscomm.yangyinetwork.wifibinding.R;
import com.asuscomm.yangyinetwork.wifibinding.data.models.WifiItem;
import com.asuscomm.yangyinetwork.wifibinding.databinding.WifiitemBinding;
import com.asuscomm.yangyinetwork.wifibinding.ui.viewmodel.WifiItemViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by jaeyoung on 8/9/17.
 */

public class WifiViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "WifiViewHolder";

    private final WifiItemViewModel mViewModel;

    @BindView(R.id.iv_wifi)
    ImageView mIvWifi;
    @BindView(R.id.tv_wifi_name)
    TextView mTvWifiname;

    public WifiViewHolder(View view, WifiitemBinding binding, WifiItemViewModel viewModel) {
        super(view);
        ButterKnife.bind(this, view);
        binding.setViewmodel(viewModel);
        mViewModel = viewModel;
    }

    public void configureWith(WifiItem item) {
        mViewModel.setItem(item);
    }
}
