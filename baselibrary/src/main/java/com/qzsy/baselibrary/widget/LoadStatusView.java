package com.qzsy.baselibrary.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qzsy.baselibrary.R;


/**
 * Created by z on 2018/11/23.
 */

public class LoadStatusView extends LinearLayout {

    private TextView mTvEmpty;
    private ImageView mImEmpty;
    private View mViewEmpty;

    private TextView mBtnRefresh;

    private OnRefreshClickListener mListener;

    public void setOnRefreshClickListener(OnRefreshClickListener listener) {
        this.mListener = listener;
    }

    public LoadStatusView(Context context) {
        super(context);
        intView();
    }

    public LoadStatusView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        intView();
    }

    public LoadStatusView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        intView();
    }

    private void intView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.empty_view, this);

        mViewEmpty = view.findViewById(R.id.rl_empty);
        mTvEmpty = view.findViewById(R.id.tv_empty);
        mImEmpty = view.findViewById(R.id.img_empty);
        mBtnRefresh = view.findViewById(R.id.btn_refresh);
        mBtnRefresh.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mListener != null) {
                    mListener.onRefresh();
                }
            }
        });
    }


    public void setStatusEmptyView(String tipStr, int imgId) {
        mViewEmpty.setVisibility(VISIBLE);
        mBtnRefresh.setVisibility(GONE);
        mTvEmpty.setVisibility(VISIBLE);
        mTvEmpty.setText(tipStr);
        mImEmpty.setImageResource(imgId);
    }

    public void setStatusErrorView() {
        mViewEmpty.setVisibility(VISIBLE);
        mBtnRefresh.setVisibility(VISIBLE);
        mTvEmpty.setVisibility(GONE);
        mBtnRefresh.setText("没有网络了,请下拉刷新重试！");
        mImEmpty.setImageResource(R.mipmap.network_empty);
    }

    public void setStatusErrorView(String errorStr) {
        mViewEmpty.setVisibility(VISIBLE);
        mTvEmpty.setVisibility(GONE);
        mBtnRefresh.setVisibility(VISIBLE);
        mBtnRefresh.setText(errorStr);
        mImEmpty.setImageResource(R.mipmap.network_empty);
    }


    public void setLoadStatusVisible(boolean visible) {
        mViewEmpty.setVisibility(visible ? VISIBLE : GONE);
    }

    public interface OnRefreshClickListener {
        void onRefresh();  //刷新
    }
}
