package com.qzsy.baselibrary.utils;

import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qzsy.baselibrary.application.MyApplication;


/**
 * Created by fengpeihao on 2017/2/8.
 */

public class ToastUtils {

    /**
     * 短时间显示Toast
     *
     * @param message
     */
    public static void showShort(String message) {
        showToast(message,Toast.LENGTH_SHORT);
    }


    /**
     * 短时间显示Toast
     *
     * @param strResId
     */
    public static void showShort(int strResId) {
        showShort(MyApplication.getInstance().getResources().getString(strResId));
    }

    /**
     * 长时间显示Toast
     *
     * @param message
     */
    public static void showLong(String message) {
       showToast(message,Toast.LENGTH_LONG);
    }

    /**
     * 长时间显示Toast
     *
     * @param strResId
     */
    public static void showLong(int strResId) {
        showLong(MyApplication.getInstance().getResources().getString(strResId));
    }


    private static void showToast(final String msg, final int duration){
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast toast = Toast.makeText(MyApplication.getInstance(), msg, duration);
                toast.setGravity(Gravity.CENTER,0,0);
                LinearLayout linearLayout = (LinearLayout) toast.getView();
                TextView messageTextView = (TextView) linearLayout.getChildAt(0);
                messageTextView.setTextSize(14);
                toast.show();
            }
        });

    }
}
