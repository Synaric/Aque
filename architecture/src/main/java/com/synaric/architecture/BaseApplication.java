package com.synaric.architecture;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.synaric.architecture.utils.Utils;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

public class BaseApplication extends Application implements ViewModelStoreOwner {

    private ViewModelStore appViewModelStore;

    @SuppressLint("StaticFieldLeak")
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        appViewModelStore = new ViewModelStore();
        context = getApplicationContext();
    }

    @NonNull
    @Override
    public ViewModelStore getViewModelStore() {
        return appViewModelStore;
    }
}
