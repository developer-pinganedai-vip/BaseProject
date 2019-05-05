package com.qzsy.baselibrary.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qzsy.baselibrary.R;
import com.qzsy.baselibrary.callback.OnButtonClickListener;


/**
 * Created by fengpeihao on 2017/6/15.
 */

public class RemindDialog {
    private Builder mBuilder;

    public RemindDialog(Builder builder) {
        mBuilder = builder;
    }

    public static class Builder {
        private Dialog mDialog;

        public Builder(Context context, String content, int imgResource, String btnStr, final OnButtonClickListener listener) {
            mDialog = new Dialog(context, R.style.custom_dialog);
            final View view = View.inflate(context, R.layout.dialog_face_success, null);
            TextView tvContent = (TextView) view.findViewById(R.id.text_content);
            tvContent.setText(content);
            ImageView imgIcon = (ImageView) view.findViewById(R.id.img_icon);
            imgIcon.setImageResource(imgResource);
            TextView tvNext = (TextView) view.findViewById(R.id.tv_next);
            tvNext.setText(btnStr);
            tvNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick();
                    mDialog.cancel();
                }
            });
            mDialog.setCanceledOnTouchOutside(false);
            mDialog.setContentView(view);
        }

        public Builder setCancelable(boolean b) {
            mDialog.setCancelable(b);
            return this;
        }

        public RemindDialog build() {
            return new RemindDialog(this);
        }
    }


    public void showDialog() {
        mBuilder.mDialog.show();
    }

    public void cancleDialog() {
//        if (mBuilder != null) {
            mBuilder.mDialog.cancel();
//            mBuilder.mDialog = null;
//            mBuilder = null;
//        }
    }
}
