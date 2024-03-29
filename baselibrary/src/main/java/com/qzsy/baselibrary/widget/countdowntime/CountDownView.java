package com.qzsy.baselibrary.widget.countdowntime;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Chronometer;

import java.text.SimpleDateFormat;

public class CountDownView extends Chronometer {

    private long mTime;
    private long mNextTime;
    private OnTimeCompleteListener mListener;
    private SimpleDateFormat mTimeFormat;

    public CountDownView(Context context) {
        super(context);
    }

    public CountDownView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTimeFormat = new SimpleDateFormat("hh:mm:ss");
        this.setOnChronometerTickListener(listener);
    }

    //重新启动计时
    public void reStart(long _time_s) {
        if (_time_s == -1) {
            mNextTime = mTime;
        } else {
            mTime = mNextTime = _time_s;
        }
        this.start();
    }

    public void reStart() {
        reStart(-1);
    }

    //不建议方法名用onResume()或onPause()，容易和activity生命周期混淆
    //继续计时
    public void onResume() {
        this.start();
    }

    //暂停计时
    public void onPause() {
        this.stop();
    }

    /**
     * 设置时间格式
     *
     * @param pattern 计时格式
     */
    public void setTimeFormat(String pattern) {
        mTimeFormat = new SimpleDateFormat(pattern);
    }

    public void setOnTimeCompleteListener(OnTimeCompleteListener l) {
        mListener = l;
    }


    OnChronometerTickListener listener = new OnChronometerTickListener() {
        @Override
        public void onChronometerTick(Chronometer chronometer) {
            if (mNextTime <= 0) {
                if (mNextTime == 0) {
                    CountDownView.this.stop();
                    if (null != mListener)
                        mListener.onTimeComplete();
                }
                mNextTime = 0;
                updateTimeText();
                return;
            }

            mNextTime--;

            updateTimeText();
        }
    };


    //初始化时间(秒)
    public void initTime(long _time_s) {
        mTime = mNextTime = _time_s;
        updateTimeText();
    }

    //初始化时间（分秒）
    public void initTime(long _time_h, long _time_m, long _time_s) {
        initTime(_time_h * 3600 + _time_m * 60 + _time_s);
    }

    private void updateTimeText() {
        this.setText(FormatMiss(mNextTime));
    }


    // 将秒转化成分钟秒
    public String FormatMiss(long miss) {
        String hh = miss / 3600 > 9 ? miss / 3600 + "" : "0" + miss / 3600;
        String mm = (miss % 3600) / 60 > 9 ? (miss % 3600) / 60 + "" : "0" + (miss % 3600) / 60;
        String ss = (miss % 3600) % 60 > 9 ? (miss % 3600) % 60 + "" : "0" + (miss % 3600) % 60;
        return mm + ":" + ss;
    }

    public interface OnTimeCompleteListener {
        void onTimeComplete();
    }
}
