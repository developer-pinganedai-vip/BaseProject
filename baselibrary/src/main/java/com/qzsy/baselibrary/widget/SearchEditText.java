package com.qzsy.baselibrary.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.qzsy.baselibrary.R;
import com.scwang.smartrefresh.layout.util.DensityUtil;


public class SearchEditText extends android.support.v7.widget.AppCompatEditText implements View.OnFocusChangeListener, View.OnKeyListener, TextWatcher {
    private Context mContext;

    //图标是否默认在左边
    private boolean isIconLeft = false;
    //是否点击软键盘搜索
    private boolean pressSearch = false;
    //软键盘搜索键监听
    private OnSearchClickListener listener;
    // 控件的图片资源
    private Drawable[] drawables;
    // 搜索图标和删除按钮图标
    private Drawable drawableLeft, drawableDel;
    private int eventY;
    // 控件区域
    private Rect rect;

    public SearchEditText(Context context) {
        this(context, null);
        this.mContext = context;
        init();
    }

    public SearchEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
        this.mContext = context;
        init();
    }

    public SearchEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    /**
     * 初始化函数
     */
    private void init() {
        setOnFocusChangeListener(this);
        setOnKeyListener(this);
        addTextChangedListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (isIconLeft) {
            if (length() < 1) {
                drawableDel = null;
            }

            //drawable的宽高是按drawable固定的宽高,即通过getIntrinsicWidth()与getIntrinsicHeight()获得
            //this.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, null, drawableDel, null);
            //drawable的宽高是按drawable.setBound()设置的宽高
            this.setCompoundDrawables(drawableLeft, null, drawableDel, null);
            super.onDraw(canvas);
        } else { // 如果不是默认样式，需要将图标绘制在中间
            if (drawables == null) drawables = getCompoundDrawables();
            if (drawableLeft == null) drawableLeft = drawables[0];
            float textWidth = getPaint().measureText(getHint().toString());
            int drawablePadding = getCompoundDrawablePadding();
            //int drawableWidth = drawableLeft.getBounds().width();
            int drawableWidth = drawableLeft.getIntrinsicWidth();
            float bodyWidth = textWidth + drawableWidth + drawablePadding;
            canvas.translate((getWidth() - bodyWidth - getPaddingLeft() - getPaddingRight()) / 2, 0);
            super.onDraw(canvas);
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        Log.e("TAG","focus:"+hasFocus);
        // 被点击时，恢复默认样式
        if (!pressSearch && TextUtils.isEmpty(getText().toString())) {
            isIconLeft = hasFocus;
        }
        if (!hasFocus){
            invalidate();
            /*隐藏软键盘*/
            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm.isActive()) {
                imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
            }
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        pressSearch = (keyCode == KeyEvent.KEYCODE_ENTER);
        if (pressSearch && listener != null) {
            /*隐藏软键盘*/
            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm.isActive()) {
                imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
            }
            listener.onSearchClick(v);
        }
        return false;
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (this.length() < 1) {
            drawableDel = null;
        } else {
            drawableDel = this.getResources().getDrawable(R.mipmap.search_delete);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 清空edit内容
        // 记录点击坐标
        int eventX;
        if (drawableDel != null && event.getAction() == MotionEvent.ACTION_UP) {
            eventX = (int) event.getRawX();
            eventY = (int) event.getRawY();
//            MyLogger.i(TAG, "eventX = " + eventX + "; eventY = " + eventY);
            if (rect == null) rect = new Rect();
            getGlobalVisibleRect(rect);
            rect.left = rect.right - drawableDel.getIntrinsicWidth();
            if (rect.contains(eventX, eventY)) {
                setText("");
            }
        }
        // 删除按钮被按下时改变图标样式
        if (drawableDel != null && event.getAction() == MotionEvent.ACTION_DOWN) {
            eventX = (int) event.getRawX();
            eventY = (int) event.getRawY();
//            MyLogger.i(TAG, "eventX = " + eventX + "; eventY = " + eventY);
            if (rect == null) rect = new Rect();
            getGlobalVisibleRect(rect);
            rect.left = rect.right - drawableDel.getIntrinsicWidth();
            if (rect.contains(eventX, eventY))
                drawableDel = this.getResources().getDrawable(R.mipmap.search_delete);
        } else {
            drawableDel = this.getResources().getDrawable(R.mipmap.search_delete);
        }
        return super.onTouchEvent(event);
    }



    /**
     * 测量
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (drawables == null) {
            drawables = getCompoundDrawables();
        }
        if (drawableLeft == null) {
            drawableLeft = drawables[0];
            //drawableLeft.setBounds(0, 0, DensityUtil.dip2px(mContext, 16), DensityUtil.dip2px(mContext, 16));
        }
        if (drawableDel != null) {
            drawableDel.setBounds(0, 0, DensityUtil.dp2px( 18), DensityUtil.dp2px( 18));
        }
    }

    /**
     * 设置接口
     *
     * @param listener
     */
    public void setOnSearchClickListener(OnSearchClickListener listener) {
        this.listener = listener;
    }
    /**
     * 定义接口
     */
    public interface OnSearchClickListener {
        void onSearchClick(View view);
    }
}
