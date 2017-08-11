package com.asuscomm.yangyinetwork.wifibinding.ui.adapter;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.databinding.BindingAdapter;
import android.databinding.OnRebindCallback;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

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

        binding.addOnRebindCallback(new OnRebindCallback() {
            @Override
            public boolean onPreBind(ViewDataBinding binding) {
                ViewGroup sceneRoot = (ViewGroup) binding.getRoot();
                TransitionManager.beginDelayedTransition(sceneRoot);
                return true;
            }
        });

        final WifiViewHolder viewholder =
                new WifiViewHolder(view, binding, viewModel);

        return viewholder;
    }

    @BindingAdapter("adText")
    public static void adText(TextView view, String newText){
        int DURATION_SHORT = 250;


        String toText = newText;


        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
        final ObjectAnimator restore = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
        alpha.setDuration(DURATION_SHORT);
        alpha.setInterpolator(new AccelerateDecelerateInterpolator());
        restore.setDuration(DURATION_SHORT);
        restore.setInterpolator(new AccelerateDecelerateInterpolator());
        alpha.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                // Do nothing.
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                view.setText(toText);
                restore.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                view.setText(toText);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                // Do nothing.
            }
        });
        alpha.start();

//        TextViewBindingAdapter.setText(tv, newText);
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
