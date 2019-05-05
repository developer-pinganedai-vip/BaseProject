package com.qzsy.baselibrary.widget.dialogmanager.Utils;

import android.app.Activity;
import android.app.Dialog;
import android.os.Build;

import com.qzsy.baselibrary.widget.dialogmanager.bean.BuildBean;


/**
 * Created by Administrator on 2018/8/9 8
 */
public class ToolUtils {

    /**
     * 统一显示
     * 解决badtoken问题,一劳永逸
     *
     * @param dialog
     */
    public static void showDialog(Dialog dialog) {
        try {
            if (dialog != null && !dialog.isShowing()) {
                dialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 混合上下文
     */
    public static BuildBean fixContext(BuildBean bean) {
        if (bean.mContext == null) {
            bean.mContext = DialogUIUtils.appContext;
        } else if (bean.mContext instanceof Activity) {
            Activity activity = (Activity) bean.mContext;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if (activity.isDestroyed()) {
                    bean.mContext = DialogUIUtils.appContext;
                }
            }
        }
        return bean;
    }


}
