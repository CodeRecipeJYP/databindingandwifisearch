package com.asuscomm.yangyinetwork.wifibinding.ui.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.support.v7.widget.RecyclerView;

/**
 * Created by jaeyoung on 8/10/17.
 */

public class WifiRecyclerViewModel extends ViewModel {
    private RecyclerView.LayoutManager mLayoutManager;

    public final void setupRecyclerView(RecyclerView recyclerView) {
        mLayoutManager = createLayoutManager();
        recyclerView.setAdapter(getAdapter());
        recyclerView.setLayoutManager(mLayoutManager);
    }

    private RecyclerView.Adapter getAdapter() {
        return null;
    }

    private RecyclerView.LayoutManager createLayoutManager() {
        return null;
    }
}
