package com.snh.recyclerviewdemo.util;
import android.util.Log;

public class Logger {
    private static final String TAG =Logger.class.getSimpleName();
    private Logger(){}
    public static void logger(String msg){
        Log.d(TAG,msg);
    }
}
