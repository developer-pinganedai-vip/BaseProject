package com.qzsy.baselibrary.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qzsy.baselibrary.R;


public class InsureDetailItem extends LinearLayout {

    private TextView mTvInsureType;
    private TextView mTvInsurePrice;
    public InsureDetailItem(Context context) {
        super(context);
//        initView();
    }



    public InsureDetailItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
//        initView();
    }

   /* private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_insure_detail, this);
        mTvInsureType = view.findViewById(R.id.tv_insure_type);
        mTvInsurePrice = view.findViewById(R.id.tv_insure_price);
    }

    public void setContent(InsureDetailBean.DataBean.BodyBean.ProductDetailListBean bean){
        mTvInsureType.setText(bean.getBewrite());
        mTvInsurePrice.setText(getResources().getString(R.string.text_money_tip) + bean.getInsureSaveLimit());
    }*/
}
