package com.qzsy.baselibrary.utils.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.qzsy.insuranceloan.app.AppApplication;

import javax.inject.Inject;

public class SpHelper {
    private static SharedPreferences mSPrefs = null;

    @Inject
    public SpHelper() {
        mSPrefs = AppApplication.getInstance().getSharedPreferences(AppApplication.getInstance().getPackageName(), Context.MODE_PRIVATE);
    }

    public void setBlackBox(String blackBox){
        mSPrefs.edit().putString(SpConstants.BLACK_BOX,blackBox).apply();
    }

    public String getBlackBox(){
        return mSPrefs.getString(SpConstants.BLACK_BOX,"");
    }




}
