package com.asuscomm.yangyinetwork.wifibinding.ui.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.asuscomm.yangyinetwork.wifibinding.R;
import com.asuscomm.yangyinetwork.wifibinding.data.models.WifiItem;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jaeyoung on 8/9/17.
 */

public class WifiViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "WifiViewHolder";

    @BindView(R.id.iv_wifi)
    ImageView mIvWifi;
    @BindView(R.id.tv_wifi_name)
    TextView mTvWifiname;

    public WifiViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void configureWith(WifiItem item) {
        mTvWifiname.setText(item.getWifiname());
    }
}
