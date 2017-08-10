package com.asuscomm.yangyinetwork.wifibinding.utils.rxjava;

import android.databinding.ObservableField;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by jaeyoung on 8/10/17.
 */

public class RxObservableField<T> extends ObservableField<T> {
    final Observable<T> source;
    final HashMap<OnPropertyChangedCallback, Disposable> disposables = new HashMap<>();

    protected RxObservableField(@NonNull Observable<T> source) {
        super();
        this.source = source
                .doOnNext(new Consumer<T>() {
                    @Override
                    public void accept(T t) {
                        set(t);
                    }
                })
                .share();
    }

    @Override
    public synchronized void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        super.addOnPropertyChangedCallback(callback);
        disposables.put(callback, source.subscribe());
    }

    @Override
    public synchronized void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        super.removeOnPropertyChangedCallback(callback);
        Disposable disposable = disposables.remove(callback);
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
