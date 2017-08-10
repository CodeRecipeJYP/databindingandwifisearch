package com.asuscomm.yangyinetwork.wifibinding;

import android.app.Activity;
import android.app.Application;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.asuscomm.yangyinetwork.wifibinding.consts.Configs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import io.reactivex.functions.Action;

/**
 * Created by jaeyoung on 8/9/17.
 */

public class AppController extends Application {
    private static final String TAG = "AppController";

    private static AppController sInstance;

    public void chkPermissions(List<String> permissions, Activity activity) {
        chkPermissions(permissions, activity, () -> {});
    }

    public void chkPermissions(String[] permissions, Activity activity) {
        chkPermissions(Arrays.asList(permissions), activity);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    @Override
    public void onTerminate() {
        sInstance = null;
        super.onTerminate();
    }

    public static AppController getInstance() {
        return sInstance;
    }

    public void chkPermissions(List<String> permissions, Activity activity, Action onSuccessed) {
        List<String> requestPermissions = new ArrayList<>();

        for (String permission :
                permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "chkPermission: " + permission + " Granted");
            } else {
                requestPermissions.add(permission);
            }
        }

        if (!requestPermissions.isEmpty()) {
            ActivityCompat.requestPermissions(activity,
                    requestPermissions.toArray(new String[0]), Configs.WIFIPERMISSION_REQUEST_CODE);
        } else {
            try {
                onSuccessed.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
