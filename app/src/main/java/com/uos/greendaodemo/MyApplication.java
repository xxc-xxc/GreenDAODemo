package com.uos.greendaodemo;

import android.app.Application;
import android.content.Context;

import com.uos.greendaodemo.db.GreenDAOManager;

/**
 * Create By xxc
 * Date: 2020/9/1 11:10
 * Desc:
 */
public class MyApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        GreenDAOManager.getInstance();
    }

    public static Context getContext() {
        return mContext;
    }
}
