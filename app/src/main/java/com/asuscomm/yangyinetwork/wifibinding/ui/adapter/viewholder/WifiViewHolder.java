package com.asuscomm.yangyinetwork.wifibinding.ui.adapter.viewholder;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.asuscomm.yangyinetwork.wifibinding.R;
import com.asuscomm.yangyinetwork.wifibinding.data.models.WifiItem;
import com.asuscomm.yangyinetwork.wifibinding.ui.viewmodel.WifiItemViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jaeyoung on 8/9/17.
 */

public class WifiViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "WifiViewHolder";
    private final ViewDataBinding mBinding;
    private final WifiItemViewModel mViewModel;

    @BindView(R.id.iv_wifi)
    ImageView mIvWifi;
    @BindView(R.id.tv_wifi_name)
    TextView mTvWifiname;

    public WifiViewHolder(View itemView, ViewDataBinding binding, WifiItemViewModel viewModel) {
        super(itemView);
        mBinding = binding;
        mViewModel = viewModel;
        ButterKnife.bind(this, itemView);
    }

    public void configureWith(WifiItem item) {
        mViewModel.setItem(item);
        mBinding.executePendingBindings();
    }

    @OnClick(R.id.iv_wifi)
    void onWifiiconClicked() {
        Log.d(TAG, "onWifiiconClicked: ");
        mViewModel.onWifiiconClicked();
    }

    @OnClick(R.id.tv_wifi_name)
    void onWifinameClicked() {
        Log.d(TAG, "onWifinameClicked: ");
        mViewModel.onWifinameClicked();
    }
}
