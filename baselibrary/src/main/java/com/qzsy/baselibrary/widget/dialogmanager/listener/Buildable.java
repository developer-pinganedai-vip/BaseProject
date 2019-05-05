package com.qzsy.baselibrary.widget.dialogmanager.listener;

import android.app.Dialog;
import android.view.View;
import android.widget.LinearLayout;

import com.qzsy.baselibrary.R;
import com.qzsy.baselibrary.widget.dialogmanager.Utils.ToolUtils;
import com.qzsy.baselibrary.widget.dialogmanager.bean.BuildBean;
import com.qzsy.baselibrary.widget.dialogmanager.config.DialogConfig;


public class Buildable {


    protected BuildBean buildByType(BuildBean bean) {
        ToolUtils.fixContext(bean);
        switch (bean.type) {
            case DialogConfig.TYPE_TIP:
                buildAppTip(bean);
                break;

        }
        return bean;
    }


    private BuildBean buildAppTip(final BuildBean bean) {
        final Dialog builder = new Dialog(bean.mContext);
        View root = View.inflate(bean.mContext, R.layout.dialog_show_must_updata_check, null);

        LinearLayout flFirst = (LinearLayout) root
                .findViewById(R.id.ll_content);

        builder.setContentView(root);


        flFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.dismiss();
            }
        });

        bean.dialog = builder;
        return bean;
    }


}
