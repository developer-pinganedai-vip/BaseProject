package com.qzsy.baselibrary.widget.dialogmanager.dialog;

import android.app.AlertDialog;
import android.app.Dialog;

import com.qzsy.baselibrary.widget.dialogmanager.listener.DialogDisMissListener;


public class DialogBean {

    int level;
    Dialog dialog;
    AlertDialog alertDialog;

    private DialogDisMissListener listener;

    public DialogDisMissListener getListener() {
        return listener;
    }

    public void setListener(DialogDisMissListener listener) {
        this.listener = listener;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Dialog getDialog() {
        return dialog;
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public AlertDialog getAlertDialog() {
        return alertDialog;
    }

    public void setAlertDialog(AlertDialog alertDialog) {
        this.alertDialog = alertDialog;
    }
}
