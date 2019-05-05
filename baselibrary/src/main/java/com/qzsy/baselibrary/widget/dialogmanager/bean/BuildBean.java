package com.qzsy.baselibrary.widget.dialogmanager.bean;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.qzsy.baselibrary.widget.dialogmanager.Utils.ToolUtils;
import com.qzsy.baselibrary.widget.dialogmanager.listener.Buildable;
import com.qzsy.baselibrary.widget.dialogmanager.listener.DialogDisMissListener;
import com.qzsy.baselibrary.widget.dialogmanager.listener.Styleable;


/**
 * DialaLog构建处理包装类
 */
public class BuildBean extends Buildable implements Styleable {


    /**
     * 上下文
     */
    public Context mContext;
    /**
     * 构建dialog的类型
     */
    public int type;

    public Dialog dialog;
    public AlertDialog alertDialog;

    DialogDisMissListener disMissListener;



    @Override
    public Dialog show() {
        buildByType(this);
        if (dialog != null && !dialog.isShowing()) {
            ToolUtils.showDialog(dialog);

            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    disMissListener.onDisMiss();
                }
            });
            return dialog;
        } else if (alertDialog != null && !alertDialog.isShowing()) {
            ToolUtils.showDialog(alertDialog);

            alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    disMissListener.onDisMiss();
                }
            });

            return alertDialog;
        }
        return null;
    }




    public void dismiss() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        } else if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
            alertDialog = null;
        }
    }


    public void setDisMissListener(final DialogDisMissListener listener) {
        disMissListener = listener;
    }
}
