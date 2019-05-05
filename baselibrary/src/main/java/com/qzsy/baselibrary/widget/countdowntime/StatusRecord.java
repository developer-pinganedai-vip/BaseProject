package com.qzsy.baselibrary.widget.countdowntime;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class StatusRecord {
    /**
     * 共享参数
     */
    private SharedPreferences pref;

    public StatusRecord(Context context) {
        pref = PreferenceManager.getDefaultSharedPreferences(context);
    }



    /**
     * 是否是第一次进入
     */
    public void setIsFirstIn(boolean isFirstIn){

        pref.edit().putBoolean("isFirstCount", isFirstIn).apply();
    }

    public boolean getIsFirstIn(){

        return  pref.getBoolean("isFirstCount",true);
    }




    /**
     * 签到 倒计时结束时间
     *
     * @param endTime
     */
    public void setEndTime(long endTime) {

        pref.edit().putLong("endTime", endTime).apply();

    }

    public long getEndTime() {

        return pref.getLong("endTime", 0);

    }
}
