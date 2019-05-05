package com.qzsy.baselibrary.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qzsy.baselibrary.R;


/**
 * 作者: 张炎
 * 时间: 2018/4/20
 * 文件: com.vip.pinganedai.widget.AlertDialogView
 * 注释:
 */

public class ShareAlertDialog extends AlertDialog implements View.OnClickListener {

    TextView tvWchat;
    TextView tvWcircle;
    TextView tvQq;
    TextView tvSms;
    TextView tvCancel;

    ShareCallBack shareCallBack;

    public void setShareCallBack(ShareCallBack shareCallBack) {
        this.shareCallBack = shareCallBack;
    }

    public ShareAlertDialog(@NonNull Context context) {
        super(context);
    }

    public ShareAlertDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public ShareAlertDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_share);
        //设置window背景，默认的背景有默认背景有inset值，不能全屏。当然不一定要是透明，你可以设置其他背景，替换默认的背景即可。
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //一定要在setContentView之后调用，否则无效
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        tvWchat = findViewById(R.id.tv_wchat);
        tvWcircle = findViewById(R.id.tv_wcircle);
        tvQq = findViewById(R.id.tv_qq);
        tvSms = findViewById(R.id.tv_sms);
        tvCancel = findViewById(R.id.tv_cancel);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == tvWchat.getId()){
            this.dismiss();
            shareCallBack.shareWchat();
        }else if (v.getId() == tvWcircle.getId()){
            this.dismiss();
            shareCallBack.shareWcircle();
        }else if (v.getId() ==tvSms.getId() ){
            this.dismiss();
            shareCallBack.shareSms();
        }else if (v.getId() == tvQq.getId()){
            this.dismiss();
            shareCallBack.shareQQ();
        }else if (v.getId() == tvCancel.getId()){
            this.dismiss();
        }
    }


    public interface ShareCallBack {
        void shareWchat();

        void shareWcircle();

        void shareQQ();

        void shareSms();

    }

}
