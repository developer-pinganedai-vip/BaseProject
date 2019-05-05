package com.qzsy.baselibrary.widget;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qzsy.baselibrary.R;
import com.qzsy.baselibrary.callback.OnButtonClickListener;
import com.qzsy.baselibrary.utils.DeviceUtils;


/**
 * Created by fengpeihao on 2017/8/24.
 */

public class PermissionDialog {

    private final Builder mBuilder;
    public static final int CALL_LOG = 0;
    public static final int SMS_INBOX = 1;
    public static final int CONTACTS = 2;
    public static final int CAMERA = 3;
    public static final int SD_CARD = 4;

    public PermissionDialog(Builder builder) {
        mBuilder = builder;
    }

    public static class Builder {

        private Dialog mDialog;
        private final String[] mPermissions = {"通话记录权限", "短信息权限", "通讯录权限", "相机权限", "存储卡权限"};
        private final int[] mPermissionIcons = {R.mipmap.qxpoup_phone, R.mipmap.qxpoup_meil, R.mipmap.qxpoup_dianhb, R.mipmap.qxpoup_cramer, R.mipmap.qxpoup_neicunka};

        public Builder(Activity context, int[] permissionId) {
            init(context, permissionId, null);
        }

        public Builder(Activity context, int[] permissionId, OnButtonClickListener listener) {
            init(context, permissionId, listener);
        }

        private void init(final Activity context, int[] permissionId, final OnButtonClickListener listener) {
            mDialog = new Dialog(context, R.style.custom_dialog);
            View view = View.inflate(context, R.layout.dialog_permission, null);
            LinearLayout layout_permission = (LinearLayout) view.findViewById(R.id.layout_permission);
            ImageView img_close = (ImageView) view.findViewById(R.id.img_close);
            Button btn_open = (Button) view.findViewById(R.id.btn_open);
            img_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    mDialog.setCancelable(false);
//                    mDialog.cancel();
                    mDialog.setCancelable(false);
//                    ToastUtils.showLong("没有权限什么也做不了");
                }
            });
            btn_open.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    JumpPermissionManager.with().GoToSetting(context);
                    if (listener != null)
                        listener.onClick();
                    mDialog.cancel();
                }
            });
            for (int i = 0; i < permissionId.length; i++) {
                TextView textview = new TextView(context);
                Drawable drawable = context.getResources().getDrawable(mPermissionIcons[permissionId[i]]);
                drawable.setBounds(0, 0, DeviceUtils.dip2px(context, 20), DeviceUtils.dip2px(context, 20));
                textview.setCompoundDrawablePadding(DeviceUtils.dip2px(context, 8));
                textview.setCompoundDrawables(drawable, null, null, null);
                textview.setTextColor(context.getResources().getColor(R.color.color_252222));
                textview.setTextSize(16);
                if (i != 0)
                    textview.setPadding(0, DeviceUtils.dip2px(context, 18), 0, 0);
                textview.setText(mPermissions[permissionId[i]]);
                layout_permission.addView(textview);
            }
            mDialog.setContentView(view);
            mDialog.setCanceledOnTouchOutside(false);
            mDialog.setCancelable(false);
        }

        public PermissionDialog build() {
            return new PermissionDialog(this);
        }
    }

    public void showDialog() {
        mBuilder.mDialog.show();
    }

    public void cancleDialog() {
        mBuilder.mDialog.cancel();
    }

    public boolean isShowing() {
        return mBuilder.mDialog.isShowing();
    }
}
