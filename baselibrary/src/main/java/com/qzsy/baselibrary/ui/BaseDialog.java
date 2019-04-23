package com.qzsy.baselibrary.ui;

import android.app.Dialog;
import android.content.Context;

public class BaseDialog extends Dialog {

    private int level;

    public BaseDialog(Context context) {
        super(context);
    }
    public BaseDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected BaseDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
