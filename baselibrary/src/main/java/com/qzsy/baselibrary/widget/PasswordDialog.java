package com.qzsy.baselibrary.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.qzsy.baselibrary.R;
import com.qzsy.baselibrary.callback.OnButtonClickListener;
import com.qzsy.baselibrary.callback.OnCancelClickListener;
import com.qzsy.baselibrary.callback.OnGetCodeListener;


/**
 * Created by fengpeihao on 2017/6/28.
 */

public class PasswordDialog {

    private final Builder mBuilder;
    private CountDownTimer countDownTimer;

    public PasswordDialog(Builder builder) {
        mBuilder = builder;
    }

    public static class Builder {

        private Dialog mDialog;
        private TextView mTv_get_code;
        private Context mContext;
        private final EditText etVerifyCode;
//                private final VerificationCodeInput mVerify_code;

        public Builder(final Context context, final OnButtonClickListener buttonClickListener, final OnGetCodeListener getCodeListener, final OnCancelClickListener cancelClickListener) {
            mContext = context;
            mDialog = new Dialog(context, R.style.custom_dialog);
            View view = View.inflate(context, R.layout.dialog_password, null);
//            mVerify_code = (VerificationCodeInput) view.findViewById(R.id.verify_code);
            etVerifyCode = (EditText) view.findViewById(R.id.verify_code);
            mTv_get_code = (TextView) view.findViewById(R.id.tv_get_code);
            TextView tv_back = (TextView) view.findViewById(R.id.tv_back);
            final TextView tv_confirm = (TextView) view.findViewById(R.id.tv_confirm);
            tv_confirm.setTextColor(context.getResources().getColor(R.color.color_e0e0e0));
//            if (etVerifyCode.getText().toString().length()<6){
//                tv_confirm.setEnabled(false);
//            }else{
//                tv_confirm.setEnabled(true);
//            }

            tv_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cancelClickListener != null)
                        cancelClickListener.onCancelClick();
                    mDialog.cancel();
                }
            });
//            mTv_get_code.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    getCodeListener.click();
//                }
//            });
            tv_confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    buttonClickListener.onClick();
                }
            });
//            mVerify_code.setOnTextWatcher(new VerificationCodeInput.OnTextWatcher() {
//                @Override
//                public void textWatcher(String content) {
//                    if (content.length() < 6) {
//                        tv_confirm.setTextColor(context.getResources().getColor(R.color.color_e0e0e0));
//                        tv_confirm.setEnabled(false);
//                    } else {
//                        tv_confirm.setEnabled(true);
//                        tv_confirm.setTextColor(context.getResources().getColor(R.color.color_fb296b));
//                    }
//                }
//            });
            mDialog.setCanceledOnTouchOutside(false);
            mDialog.setContentView(view);
            mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    if (cancelClickListener != null)
                        cancelClickListener.onCancelClick();
                }
            });
        }

        public PasswordDialog build() {
            return new PasswordDialog(this);
        }
    }

    public void showDialog() {
        mBuilder.mDialog.show();
    }

    public String getVerifyPassword() {
        return mBuilder.etVerifyCode.getText().toString();
    }

    public void clearVerifyPassword() {
        mBuilder.etVerifyCode.setText("");
    }

    public void cancleDialog() {
        cancelTimer();
        mBuilder.mDialog.cancel();
    }

    /**
     * 验证码倒计时
     */
    public void startCountDownTimer() {
        countDownTimer = new CountDownTimer(60 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mBuilder.mTv_get_code.setText(millisUntilFinished / 1000 + "秒后重新获取");
                mBuilder.mTv_get_code.setBackgroundColor(mBuilder.mContext.getResources().getColor(R.color.white));
                mBuilder.mTv_get_code.setClickable(false);
                mBuilder.mTv_get_code.setTextColor(mBuilder.mContext.getResources().getColor(R.color.color_bdbcbc));
            }

            @Override
            public void onFinish() {
                mBuilder.mTv_get_code.setText("重新获取验证码");
                mBuilder.mTv_get_code.setTextColor(mBuilder.mContext.getResources().getColor(R.color.color_fb296b));
                mBuilder.mTv_get_code.setBackgroundResource(R.drawable.shape_get_code_bg);
                mBuilder.mTv_get_code.setClickable(true);
            }
        }.start();
    }

    public void cancelTimer() {
        if (countDownTimer != null)
            countDownTimer.cancel();
    }
}
