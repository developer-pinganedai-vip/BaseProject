package com.qzsy.baselibrary.utils.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.qzsy.baselibrary.application.MyApplication;

import javax.inject.Inject;

import static com.qzsy.baselibrary.utils.prefs.SpConstants.SHAREDPREFERENCES_NAME;


/**
 * @author: Est <codeest.dev@gmail.com>
 * @date: 2017/4/21
 * @description:
 */

public class ImplPreferencesHelper implements PreferencesHelper {

    private static final boolean DEFAULT_NIGHT_MODE = false;
    private static final boolean DEFAULT_NO_IMAGE = false;
    private static final boolean DEFAULT_AUTO_SAVE = true;

    private static final boolean DEFAULT_LIKE_POINT = false;
    private static final boolean DEFAULT_VERSION_POINT = false;
    private static final boolean DEFAULT_MANAGER_POINT = false;

    private final SharedPreferences mSPrefs;

    @Inject
    public ImplPreferencesHelper() {
        mSPrefs = MyApplication.getInstance().getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public void clear() {
        mSPrefs.edit().clear().apply();
    }

    @Override
    public String getToken() {
        return mSPrefs.getString(SpConstants.TOKEN, "");
    }

    @Override
    public void setToken(String token) {
        mSPrefs.edit().putString(SpConstants.TOKEN, token).apply();
    }

    public void setCustomerId(String customerId) {
        mSPrefs.edit().putString(SpConstants.CUSTOMERID, customerId).apply();
    }

    public void setPhoneNum(String phoneNum) {
        mSPrefs.edit().putString(SpConstants.PHONENUM, phoneNum).apply();
    }

    public String getGoodsInfoId(){
       return mSPrefs.getString(SpConstants.GOODS_INFO_ID,"");
    }
    @Override
    public String getCustomerId() {
        return mSPrefs.getString(SpConstants.CUSTOMERID, "");
    }

    @Override
    public String getPhoneNum() {
        return mSPrefs.getString(SpConstants.PHONENUM, "");
    }

    @Override
    public String getNickName() {
        return mSPrefs.getString(SpConstants.NICKNAME, "");
    }

    public String getSevericeCode(){
        return mSPrefs.getString(SpConstants.SERVICE_CODE,"");
    }

    public String getNetIp() {
        return mSPrefs.getString(SpConstants.NETIP, "");
    }

    public boolean getHaveBusinessPwd() {
        return mSPrefs.getBoolean(SpConstants.FIRSTSETBUNPWD, false);
    }

    public void setWeChatCode(String weChatCode){
        mSPrefs.edit().putString(SpConstants.WECHAT_CODE, weChatCode).apply();
    }

    public String getWeChatCode(){
        return mSPrefs.getString(SpConstants.WECHAT_CODE,"");
    }


    public void setAppId(String appId){
        mSPrefs.edit().putString(SpConstants.PAY_APP_ID,appId).apply();
    }

    public String getAppId(){
        return mSPrefs.getString(SpConstants.PAY_APP_ID,"");
    }

    public void setCountData(long data){
        mSPrefs.edit().putLong(SpConstants.FIRST_COUNT_DOWN,data).apply();
    }

    public long getCountData(){
        return mSPrefs.getLong(SpConstants.FIRST_COUNT_DOWN,0);
    }

    public void clearSP() {
        SharedPreferences.Editor editor = mSPrefs.edit();
        editor.clear();
        editor.commit();
    }





}
