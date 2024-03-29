package com.qzsy.baselibrary.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qzsy.baselibrary.R;


/**
 * Created by fengpeihao on 2017/6/10.
 */

public class LoadingDialog {
    private Builder mBuilder;

    public LoadingDialog(Builder builder) {
        mBuilder = builder;
    }

    public static class Builder {
        private Dialog mDialog;
        private TextView mContentText;

        public Builder createLoadingDialog(Context context) {

            LayoutInflater inflater = LayoutInflater.from(context);
            View v = inflater.inflate(R.layout.loading_dialog, null);// 得到加载view
            LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
            // main.xml中的ImageView
            ImageView spaceshipImage = (ImageView) v.findViewById(R.id.img);
            // 提示文字
            mContentText = (TextView) v.findViewById(R.id.tipTextView);
            // 加载动画
            AnimationDrawable drawable = (AnimationDrawable) spaceshipImage.getDrawable();
            drawable.start();
//        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
//                context, R.anim.loading_animation);
//        // 使用ImageView显示动画
//        spaceshipImage.startAnimation(hyperspaceJumpAnimation);
            mContentText.setText("数据加载中……");// 设置加载信息

            Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);// 创建自定义样式dialog
            loadingDialog.setCanceledOnTouchOutside(false);
            loadingDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//去掉这句话，背景会变暗
            loadingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            loadingDialog.setCancelable(false);// 不可以用“返回键”取消
            loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));// 设置布局
            mDialog = loadingDialog;
            return this;
        }

        public Builder setContent(String str) {
            mContentText.setText(str);
            return this;
        }

        public LoadingDialog build() {
            return new LoadingDialog(this);
        }
    }

    public void showDialog() {
        mBuilder.mDialog.show();
    }

    public void cancelDialog() {
//        if (mBuilder != null) {
            mBuilder.mDialog.cancel();
//            mBuilder.mDialog = null;
//            mBuilder = null;
//        }
    }
}
