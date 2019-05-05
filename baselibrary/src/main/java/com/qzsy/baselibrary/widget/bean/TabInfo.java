package com.qzsy.baselibrary.widget.bean;

public class TabInfo {
    private int mIconNormalResId;
    private int mIconPressedResId;
    private int mIconFrameResId;
    private int mIconFrameAnim;
    private int mNormalColor;
    private int mSelectColor;
    private String mText;
    private boolean isSelect;
    private boolean isFrame;

    public int getmIconNormalResId() {
        return mIconNormalResId;
    }

    public void setmIconNormalResId(int mIconNormalResId) {
        this.mIconNormalResId = mIconNormalResId;
    }

    public int getmIconPressedResId() {
        return mIconPressedResId;
    }

    public void setmIconPressedResId(int mIconPressedResId) {
        this.mIconPressedResId = mIconPressedResId;
    }

    public int getmIconFrameResId() {
        return mIconFrameResId;
    }

    public void setmIconFrameResId(int mIconFrameResId) {
        this.mIconFrameResId = mIconFrameResId;
    }

    public int getmIconFrameAnim() {
        return mIconFrameAnim;
    }

    public void setmIconFrameAnim(int mIconFrameAnim) {
        this.mIconFrameAnim = mIconFrameAnim;
    }

    public int getmNormalColor() {
        return mNormalColor;
    }

    public void setmNormalColor(int mNormalColor) {
        this.mNormalColor = mNormalColor;
    }

    public int getmSelectColor() {
        return mSelectColor;
    }

    public void setmSelectColor(int mSelectColor) {
        this.mSelectColor = mSelectColor;
    }

    public String getmText() {
        return mText;
    }

    public void setmText(String mText) {
        this.mText = mText;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public boolean isFrame() {
        return isFrame;
    }

    public void setFrame(boolean frame) {
        isFrame = frame;
    }


    public TabInfo(String mText, int mIconNormalResId, int mIconPressedResId, int mNormalColor, int mSelectColor, int mIconFrameAnim, int mIconFrameResId, boolean isFrame) {
        this.mIconNormalResId = mIconNormalResId;
        this.mIconPressedResId = mIconPressedResId;
        this.mIconFrameResId = mIconFrameResId;
        this.mIconFrameAnim = mIconFrameAnim;
        this.mNormalColor = mNormalColor;
        this.mSelectColor = mSelectColor;
        this.mText = mText;
        this.isFrame = isFrame;
    }
}
