package com.qzsy.baselibrary.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;


import com.qzsy.baselibrary.R;
import com.qzsy.baselibrary.utils.ConvertUtils;

import java.util.List;

public class MarqueeTextView extends LinearLayout {
    private Context mContext;
    private ViewFlipper viewFlipper;
    private View marqueeTextView;
    private List<?> textArrays;
    private MarqueeTextViewClickListener marqueeTextViewClickListener;
    private int textColor;
    public MarqueeTextView(Context context) {
        super(context);
        mContext = context;
        initBasicView();
    }
    public MarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initBasicView();
    }
    public MarqueeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initBasicView();
    }

//    public void setTextArrays(List<?> textArrays){
//        this.textArrays = textArrays;
//    }
//
//    public void setMarqueeTextViewClickListener(MarqueeTextViewClickListener marqueeTextViewClickListener){
//        this.marqueeTextViewClickListener = marqueeTextViewClickListener;
//    }
    public void setTextArraysAndClickListener(List<?> textArrays,int textColor, MarqueeTextViewClickListener marqueeTextViewClickListener) {
        this.textArrays = textArrays;
        this.marqueeTextViewClickListener = marqueeTextViewClickListener;
        initMarqueeTextView(textArrays, textColor,marqueeTextViewClickListener);
    }

    public void initBasicView() {//加载布局，初始化ViewFlipper组件及效果
        marqueeTextView = LayoutInflater.from(mContext).inflate(R.layout.layout_marquee_textview, null);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        addView(marqueeTextView, layoutParams);
        viewFlipper = (ViewFlipper) marqueeTextView.findViewById(R.id.viewFlipper);
        viewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.push_up_in));//设置上下的动画效果（自定义动画，所以改左右也很简单）
        viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.push_up_out));
        viewFlipper.startFlipping();
    }

    public void initMarqueeTextView(List<?> textArrays, int textColor,MarqueeTextViewClickListener marqueeTextViewClickListener) {
        if (textArrays.size() == 0) {
            return;
        }

        int i = 0;
        viewFlipper.removeAllViews();
        while (i < textArrays.size()) {
            TextView textView = new TextView(mContext);
            textView.setText(textArrays.get(i).toString());
            textView.setTextColor(textColor);
            float dimension = getResources().getDimension(R.dimen.sp_14);
            int spValue = ConvertUtils.px2sp(getContext(), dimension);
            textView.setTextSize(spValue);
            textView.setSingleLine();
            textView.setEllipsize(TextUtils.TruncateAt.END);
            if (marqueeTextViewClickListener!=null) {
                textView.setOnClickListener(marqueeTextViewClickListener);
            }
            LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            viewFlipper.addView(textView, lp);
            i++;
        }
    }

    public void releaseResources() {
        if (marqueeTextView != null) {
            if (viewFlipper != null) {
                viewFlipper.stopFlipping();
                viewFlipper.removeAllViews();
                viewFlipper = null;
            }
            marqueeTextView = null;
        }
    }

    public interface MarqueeTextViewClickListener extends OnClickListener {
        void onClick(View view);
    }
}
