package com.asuscomm.yangyinetwork.wifibinding.ui.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;

import com.asuscomm.yangyinetwork.wifibinding.data.models.WifiItem;
import com.asuscomm.yangyinetwork.wifibinding.utils.receiver.WifiBroadcastReceiver;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.subjects.ReplaySubject;


/**
 * Created by jaeyoung on 8/10/17.
 */

public class MainViewModel extends ViewModel {
    private static final String TAG = "MainViewModel";
    public ObservableField<List<WifiItem>> items = new ObservableField<>(new ArrayList<>());
    public ObservableField<String> length = new ObservableField<>();

    private ReplaySubject<String> notifier = ReplaySubject.create();
    private int clicked = 0;

    public MainViewModel() {
        initField();
//        loadData();
    }

    private void initField() {
        Log.d(TAG, "initField: ");
        notifier.subscribe(
                (l) -> {
                    Log.d(TAG, "initField: length=" + l);
                    length.set(l);
                }
        );
//        FieldUtils.toObservable(items);
//        items
    }

//    private void loadData() {

//        Handler handler = new Handler();
//        handler.postDelayed(() -> {
//            Log.d(TAG, "loadItems/run: ");
//            items.set(WifiRepo.getDummys());
//        }, 3000);
//    }

    public void onScanClicked(View v) {
        Log.d(TAG, "onScanClicked: ");
//        clicked++;
//        notifier.onNext("Length : " + clicked);
    }

    public void wifireceiverInitialized() {
        Log.d(TAG, "wifireceiverInitialized: ");
        WifiBroadcastReceiver.getInstance().asObserverble().subscribe(
                (i) -> Log.d(TAG, "wifireceiverInitialized: i=" + i)
        );

        WifiBroadcastReceiver.getInstance().asObserverble().subscribe(
                items::set
        );
        WifiBroadcastReceiver.getInstance().startScans();
    }
}
