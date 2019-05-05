package com.qzsy.baselibrary.widget.bankscan;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.WindowManager;

public class RectOnCamera extends android.support.v7.widget.AppCompatImageView {
    private Paint mPaint;
    private RectF mRectF;
    // 圆
    private Point centerPoint;
    private int radio;
    private int width;
    private int height;

    public RectOnCamera(Context context) {
        this(context,null);
    }
    public RectOnCamera(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    public RectOnCamera(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getScreenMetrix(context);
//        initView(context);
    }

    private void getScreenMetrix(Context context) {
        WindowManager WM = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//        DisplayMetrics outMetrics = new DisplayMetrics();
//        WM.getDefaultDisplay().getMetrics(outMetrics);
//        mScreenWidth = outMetrics.widthPixels;
//        mScreenHeight = outMetrics.heightPixels;
        width = WM.getDefaultDisplay().getWidth();
        height = WM.getDefaultDisplay().getHeight();
    }


    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {

//        mPaint.setColor();
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#3c96fd"));
        paint.setAlpha(250);

        canvas.drawLine(200,150,300,150,paint);
        canvas.drawLine(200,150,200,200,paint);

        canvas.drawLine(200,height-150,300,height-150,paint);
        canvas.drawLine(200,height-150,200,height-200,paint);

        canvas.drawLine(width-355,150,width-455,150,paint);
        canvas.drawLine(width-355,150,width-355,200,paint);

        canvas.drawLine(width-355,height-150,width-455,height-150,paint);
        canvas.drawLine(width-355,height-150,width-355,height-200,paint);
        super.onDraw(canvas);
    }
}
