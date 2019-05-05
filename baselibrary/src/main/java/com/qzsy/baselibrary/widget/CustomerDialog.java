package com.qzsy.baselibrary.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qzsy.baselibrary.R;


public class CustomerDialog extends Dialog {
    public CustomerDialog(Context context) {
        super(context);
    }

    public CustomerDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder{
        private Context context;
        private String title;
        private String message;
        private String comfirmText;
        private OnClickListener positiveListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setTitle(String title){
            if (title==null){
                this.title = "提示";
            }
            this.title = title;
            return this;
        }

        public Builder setMessage(String message){
            if (message==null){
                this.message = "提示内容为空";
            }
            this.message = message;
            return this;
        }

        public Builder setConfrimClickListener(String conformText,OnClickListener positiveListener){
            if (conformText==null){
                this.comfirmText = "确定";
            }
            this.comfirmText = conformText;
            this.positiveListener = positiveListener;
            return this;
        }

        public CustomerDialog create(){
            final CustomerDialog customerDialog = new CustomerDialog(context, R.style.customer_dialog);
            View view = LayoutInflater.from(context).inflate(R.layout.customer_dialog, null);
            customerDialog.setContentView(view, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            TextView tvTitle = view.findViewById(R.id.tv_customerDialogTitle);
            TextView tvContent = view.findViewById(R.id.tv_customerDialogContent);
            TextView tvConfirm = view.findViewById(R.id.tv_customerDialogConfirm);

            tvTitle.setText(title);
            tvContent.setText(message);

            tvConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    positiveListener.onClick(customerDialog,Dialog.BUTTON_POSITIVE);
                }
            });
            return customerDialog;
        }

    }
    //    private Builder mBuilder;
//
//    public CustomerDialog(Builder builder) {
//        this.mBuilder = builder;
//    }
//    public static class Builder{
//
//        private final Dialog dialog;
//        private String message;
//        public Builder(Context context){
//            dialog =  new Dialog(context, R.style.customer_dialog);
//            View view = LayoutInflater.from(context).inflate(R.layout.customer_dialog, null);
//            TextView tvContent = view.findViewById(R.id.tv_customerDialogContent);
//            TextView tvConfirm = view.findViewById(R.id.tv_customerDialogConfirm);
//            tvConfirm.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dialog.dismiss();
//                }
//            });
//            tvContent.setText(message);
//            dialog.setContentView(view);
//        }
//
//        public Builder setCancelable(boolean b) {
//            dialog.setCancelable(b);
//            dialog.setCanceledOnTouchOutside(b);
//            return this;
//        }
//
//
//        public Builder setMessage(String message){
//            this.message  = message;
//            return this;
//        }
//
//
//        public CustomerDialog build() {
//            return new CustomerDialog(this);
//        }
//
//    }
//
//    public void showDialog() {
//        mBuilder.dialog.show();
//    }
}
