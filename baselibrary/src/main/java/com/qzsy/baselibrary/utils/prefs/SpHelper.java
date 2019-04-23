package com.qzsy.baselibrary.utils.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.qzsy.baselibrary.application.MyApplication;

import javax.inject.Inject;


public class SpHelper {
    private static SharedPreferences mSPrefs = null;

    @Inject
    public SpHelper() {
        mSPrefs = MyApplication.getInstance().getSharedPreferences(MyApplication.getInstance().getPackageName(), Context.MODE_PRIVATE);
    }

    public void setBlackBox(String blackBox){
        mSPrefs.edit().putString(SpConstants.BLACK_BOX,blackBox).apply();
    }

    public String getBlackBox(){
        return mSPrefs.getString(SpConstants.BLACK_BOX,"");
    }




}
