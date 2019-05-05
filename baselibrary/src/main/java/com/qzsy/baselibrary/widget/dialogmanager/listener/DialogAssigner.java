package com.qzsy.baselibrary.widget.dialogmanager.listener;

import android.content.Context;

import com.qzsy.baselibrary.widget.dialogmanager.bean.BuildBean;
import com.qzsy.baselibrary.widget.dialogmanager.config.DialogConfig;


public class DialogAssigner implements Assignable {

    private static DialogAssigner instance;

    private DialogAssigner() {

    }

    public static DialogAssigner getInstance() {
        if (instance == null) {
            instance = new DialogAssigner();
        }
        return instance;
    }


    @Override
    public BuildBean appTip(Context context) {
        BuildBean bean = new BuildBean();
        bean.mContext = context;
        bean.type = DialogConfig.TYPE_TIP;
        return bean;
    }

}
