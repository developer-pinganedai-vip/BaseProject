package com.qzsy.baselibrary.utils.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.qzsy.insuranceloan.app.AppApplication;

import javax.inject.Inject;

import static com.qzsy.insuranceloan.utils.prefs.SpConstants.NOCLEARSPHELPER_NAME;

/**
 * Created by fengpeihao on 2017/8/4.
 */

public class NoClearSPHelper {
    private final SharedPreferences mSPrefs;

    @Inject
    public NoClearSPHelper() {
        mSPrefs = AppApplication.getInstance().getSharedPreferences(NOCLEARSPHELPER_NAME, Context.MODE_PRIVATE);
    }

    //存储用户手机号，用来判断用户是否是第一次上传设备信息
    public void setPhoneDevice(String PhoneDevice) {
        mSPrefs.edit().putString(SpConstants.PHONE_DEVICE, PhoneDevice).apply();
    }

    //存储用户手机号，用来判断用户是否是第一次上传应用列表
    public void setAppInfo(String appInfo) {
        mSPrefs.edit().putString(SpConstants.APP_INFO, appInfo).apply();
    }

    //存储用户手机号，用来判断用户是否是第一次上传通讯录
    public void setContacts(String appInfo) {
        mSPrefs.edit().putString(SpConstants.CONSTANTS_INFO, appInfo).apply();
    }

    //存储统计用户激活率
    public void setIsFristStart(boolean isFristStart) {
        mSPrefs.edit().putBoolean("isFristStart", isFristStart).apply();
    }

    //存储第一次进入提额页面
    public void setIsFristQuota(boolean isFristQuota) {
        mSPrefs.edit().putBoolean("isFristQuota", isFristQuota).apply();
    }

    //存储是否是第一上传短息记录
    public void setIsUpdateSms(String phoneNumber) {
        mSPrefs.edit().putString(SpConstants.IS_UPDATE_SMS, phoneNumber).apply();
    }

//    //获取是否是第一上传短息记录
//    public boolean getIsUpdateSms() {
//        return !AndroidUtil.getPhoneNum().equalsIgnoreCase(mSPrefs.getString(SpConstants.IS_UPDATE_SMS, ""));
//    }

    //身份认证页面是否是第一次请求权限状态
    public void setIsPermissionFirst() {
        mSPrefs.edit().putBoolean(SpConstants.IS_PERMISSION_FIRST, false).apply();
    }

    public boolean isPermissionFirst() {
        return mSPrefs.getBoolean(SpConstants.IS_PERMISSION_FIRST, true);
    }

    public void setContactPermission(){
        mSPrefs.edit().putBoolean(SpConstants.CONTACT_PERMISSION, false).apply();
    }

    //联系人权限是否是第一次读取
    public boolean isContactPermissionFirst(){
        return mSPrefs.getBoolean(SpConstants.CONTACT_PERMISSION, true);
    }

    public boolean getIsFristQuota() {
        return mSPrefs.getBoolean("isFristQuota", false);
    }

    public boolean getIsFristStart() {
        return mSPrefs.getBoolean("isFristStart", false);
    }

//    public boolean getContacts() {
//        return !AndroidUtil.getPhoneNum().equalsIgnoreCase(mSPrefs.getString(SpConstants.CONSTANTS_INFO, ""));
//    }
//
//    public boolean getAppInfo() {
//        return !AndroidUtil.getPhoneNum().equalsIgnoreCase(mSPrefs.getString(SpConstants.APP_INFO, ""));
//    }
//
//    public boolean getPhoneDevice() {
//        return !AndroidUtil.getPhoneNum().equalsIgnoreCase(mSPrefs.getString(SpConstants.PHONE_DEVICE, ""));
//    }
}
