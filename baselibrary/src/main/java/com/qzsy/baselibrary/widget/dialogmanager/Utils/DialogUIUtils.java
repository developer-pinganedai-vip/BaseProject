package com.qzsy.baselibrary.widget.dialogmanager.Utils;


import android.content.Context;

import com.qzsy.baselibrary.widget.dialogmanager.bean.BuildBean;
import com.qzsy.baselibrary.widget.dialogmanager.config.DialogConfig;
import com.qzsy.baselibrary.widget.dialogmanager.listener.DialogAssigner;


public class DialogUIUtils {


    /**
     * 全局上下文
     */
    public static Context appContext;


    public static BuildBean showAppTip(Context context) {
        return DialogAssigner.getInstance().appTip(context);
    }


    public static BuildBean showDialog(Context context, int type){
        BuildBean bean = null;
        switch (type){
            case DialogConfig.TYPE_TIP:
                bean = DialogUIUtils.showAppTip(context);
                break;
        }

        return bean;
    }

}
