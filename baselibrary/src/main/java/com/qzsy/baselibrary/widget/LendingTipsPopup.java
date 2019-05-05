package com.qzsy.baselibrary.widget;

import android.content.Context;
import android.view.View;


import com.qzsy.baselibrary.R;

import razerdp.basepopup.BasePopupWindow;


public class LendingTipsPopup extends BasePopupWindow {
    public LendingTipsPopup(Context context) {
        super(context);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.layout_lending_tips);
    }
}
