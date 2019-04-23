package com.qzsy.baselibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceUtil {
    public static final String mTAG = "test";
    // 创建一个写入器
    private static SharedPreferences mPreferences;
    private static SharedPreferences.Editor mEditor;
    private static SharedPreferenceUtil mSharedPreferencesUtil;

    // 构造方法
    public SharedPreferenceUtil(Context context) {
        mPreferences = context.getSharedPreferences(mTAG, Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
    }

    // 单例模式
    public static SharedPreferenceUtil getInstance(Context context) {
        if (mSharedPreferencesUtil == null) {
            mSharedPreferencesUtil = new SharedPreferenceUtil(context);
        }
        return mSharedPreferencesUtil;
    }

    // 存入数据
    public void putSP(String key, String value) {
        mEditor.putString(key, value);
        mEditor.commit();
    }

    public void putLongSP(String key,Long value){
        mEditor.putLong(key,value);
        mEditor.commit();
    }

    // 获取数据
    public String getSP(String key) {
        return mPreferences.getString(key, "");
    }


    public long getLongSP(String key){
        return mPreferences.getLong(key,0);
    }
    // 移除数据
    public void removeSP(String key) {
        mEditor.remove(key);
        mEditor.commit();
    }
}
