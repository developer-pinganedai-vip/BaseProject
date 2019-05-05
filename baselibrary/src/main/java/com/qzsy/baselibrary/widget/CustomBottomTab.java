package com.qzsy.baselibrary.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.qzsy.baselibrary.R;
import com.qzsy.baselibrary.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class CustomBottomTab extends LinearLayout implements View.OnClickListener {
    private List<View> mTabViews;//保存TabView
    private List<Tab> mTabs;// 保存Tab
    private OnTabCheckListener mOnTabCheckListener;
    private Context context;
    private Timer timer;

    public void setOnTabCheckListener(OnTabCheckListener onTabCheckListener) {
        mOnTabCheckListener = onTabCheckListener;
    }

    public CustomBottomTab(Context context) {
        super(context);
        init(context);
    }

    public CustomBottomTab(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomBottomTab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomBottomTab(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }


    private void init(Context context) {
        this.context = context;
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
        setBackgroundColor(context.getResources().getColor(R.color.white));
        mTabViews = new ArrayList<>();
        mTabs = new ArrayList<>();
//        timer = new Timer();
    }


    /**
     * 添加Tab
     *
     * @param tab
     */
    public void addTab(Tab tab) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_custom_tab, null);
        TextView textView = (TextView) view.findViewById(R.id.custom_tab_text);
        ImageView imageView = (ImageView) view.findViewById(R.id.custom_tab_icon);
        LinearLayout layout = view.findViewById(R.id.custom_tab_layout);
        imageView.setImageResource(tab.mIconNormalResId);
        textView.setText(tab.mText);
        textView.setTextColor(tab.mNormalColor);

        view.setTag(mTabViews.size());
        view.setOnClickListener(this);

        mTabViews.add(view);
        mTabs.add(tab);

        addView(view);

        LayoutParams layoutParams = new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        view.setLayoutParams(layoutParams);
    }

    public void addTab(List<Tab> tabList) {
        mTabs.clear();
        mTabs.addAll(tabList);
//        mTabViews.clear();
        for (int i = 0; i < tabList.size(); i++) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_custom_tab, null);
            TextView textView = (TextView) view.findViewById(R.id.custom_tab_text);
            ImageView imageView = (ImageView) view.findViewById(R.id.custom_tab_icon);
            Tab tab = tabList.get(i);
            imageView.setImageResource(tab.mIconNormalResId);
            LogUtils.e("text=" + tab.mText);
            textView.setText(tab.mText);
            textView.setTextColor(tab.mNormalColor);
            view.setTag(i);
            view.setOnClickListener(this);

            mTabViews.add(view);

            addView(view);

        }
    }

    public void replaceTab(List<Tab> tabList, int replacePosition) {
        mTabs.clear();
        mTabs.addAll(tabList);
        LogUtils.e("replaceList=" + tabList.size()+"tab="+tabList.get(replacePosition).getmText());
//        for (int i = 0; i < tabList.size(); i++) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_custom_tab, null);
        TextView textView = (TextView) view.findViewById(R.id.custom_tab_text);
        ImageView imageView = (ImageView) view.findViewById(R.id.custom_tab_icon);
        Tab tab = tabList.get(replacePosition);
        imageView.setImageResource(tab.mIconNormalResId);
//        LogUtils.e("text=" + tab.mText);
        textView.setText(tab.mText);
        textView.setTextColor(tab.mNormalColor);
        view.setTag(replacePosition);
        view.setOnClickListener(this);
        mTabViews.set(replacePosition, view);
        addView(view);

//        }
    }

    /**
     * 设置选中Tab
     *
     * @param position
     */
    public void setCurrentItem(int position) {
        if (position >= mTabs.size() || position < 0) {
            position = 0;
        }

        mTabViews.get(position).performClick();

        updateState(position);


    }


    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        if (mOnTabCheckListener != null) {
            mOnTabCheckListener.onTabSelected(v, position);
        }

        updateState(position);
    }


    /**
     * 更新状态
     *
     * @param position
     */
    private void updateState(int position) {
        for (int i = 0; i < mTabViews.size(); i++) {
            View view = mTabViews.get(i);
            TextView textView = (TextView) view.findViewById(R.id.custom_tab_text);
            ImageView imageView = (ImageView) view.findViewById(R.id.custom_tab_icon);
            final ImageView ivCustomTabFrame = view.findViewById(R.id.custom_tab_frame);
            LinearLayout layout = view.findViewById(R.id.custom_tab_layout);

            LogUtils.e("text=" + textView.getText().toString());
            if (i == position) {
                ivCustomTabFrame.setVisibility(GONE);
                layout.setVisibility(VISIBLE);
                imageView.setImageResource(mTabs.get(i).mIconPressedResId);
                textView.setTextColor(mTabs.get(i).mSelectColor);
                textView.setText(mTabs.get(i).mText);
                if (i == 2) {
                    if (timer != null) {
                        timer.cancel();
                        timer = null;
                    }
                }
            } else {
                imageView.setImageResource(mTabs.get(i).mIconNormalResId);
                textView.setTextColor(mTabs.get(i).mNormalColor);
                textView.setText(mTabs.get(i).mText);
                //定制，赚钱tab抖动效果
                if (i == 2) {
                    ivCustomTabFrame.setVisibility(VISIBLE);
                    layout.setVisibility(GONE);
                    ivCustomTabFrame.setImageResource(mTabs.get(i).mIconFrameResId);

                    final int finalI = i;
                    if (timer == null) {
                        timer = new Timer();

                        timer.scheduleAtFixedRate(new TimerTask() {
                            @Override
                            public void run() {
                                ((android.app.Activity) context).runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (mTabs.size() < 1) {
                                            return;
                                        }
                                        Animation animation = AnimationUtils.loadAnimation(getContext(), mTabs.get(finalI).mIconFrameAnim);
                                        ivCustomTabFrame.startAnimation(animation);
                                    }
                                });

                            }
                        }, 1000, 3000);
                    }
                }
            }
        }
    }

    public interface OnTabCheckListener {
        public void onTabSelected(View v, int position);
    }


    public static class Tab {
        private int mIconNormalResId;
        private int mIconPressedResId;
        private int mIconFrameResId;
        private int mIconFrameAnim;
        private int mNormalColor;
        private int mSelectColor;
        private String mText;


        public Tab setText(String text) {
            mText = text;
            return this;
        }

        public String getmText() {
            return mText;
        }

        public void setmText(String mText) {
            this.mText = mText;
        }

        public Tab setNormalIcon(int res) {
            mIconNormalResId = res;
            return this;
        }

        public Tab setPressedIcon(int res) {
            mIconPressedResId = res;
            return this;
        }

        public Tab setColor(int color) {
            mNormalColor = color;
            return this;
        }

        public Tab setCheckedColor(int color) {
            mSelectColor = color;
            return this;
        }

        public Tab setFrameIcon(int res) {
            mIconFrameResId = res;
            return this;
        }

        public Tab setFrameAnim(int anim) {
            mIconFrameAnim = anim;
            return this;
        }
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mTabViews != null) {
            mTabViews.clear();
        }
        if (mTabs != null) {
            mTabs.clear();
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        // 调整每个Tab的大小
        for (int i = 0; i < mTabViews.size(); i++) {
            View view = mTabViews.get(i);
            int width = getResources().getDisplayMetrics().widthPixels / (mTabs.size());
            LayoutParams params = new LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT);

            view.setLayoutParams(params);
        }

    }

    public void onDestroy() {
        if (timer != null) {
            timer.cancel();
        }
    }

}
