package com.asuscomm.yangyinetwork.wifibinding.utils.rxjava;

import android.databinding.ObservableField;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;

/**
 * Created by jaeyoung on 8/10/17.
 */

public class FieldUtils {
    @NonNull
    public static <T> Observable<T> toObservable(@NonNull final ObservableField<T> field) {
        return Observable.fromPublisher(asyncEmitter -> {
            final android.databinding.Observable.OnPropertyChangedCallback callback = new android.databinding.Observable.OnPropertyChangedCallback() {
                @Override
                public void onPropertyChanged(android.databinding.Observable dataBindingObservable, int propertyId) {
                    if (dataBindingObservable == field) {
                        asyncEmitter.onNext(field.get());
                    }
                }
            };
            field.addOnPropertyChangedCallback(callback);
        });
    }
}
