package com.qzsy.baselibrary.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.qzsy.baselibrary.R;


public class ProgressWebView extends WebView {
    private int progressHeight = 10;  //进度条的高度，默认10px
    private ProgressBar progressBar;
    private Context context;

    public ProgressWebView(Context context) {
        super(context);
        initView(context);
    }
    public ProgressWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }
    public ProgressWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView(context);
    }

    private void initView(Context context){
        this.context = context;
        getSettings().setJavaScriptEnabled(true);

        progressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        progressBar.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,progressHeight));

        Drawable drawable = context.getResources().getDrawable(R.drawable.progress_webview);
        progressBar.setProgressDrawable(drawable);

        //适配手机大小
        getSettings().setUseWideViewPort(true);
        getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        getSettings().setLoadWithOverviewMode(true);
        getSettings().setSupportZoom(true);
        getSettings().setBuiltInZoomControls(true);
        getSettings().setDisplayZoomControls(false);


        setWebChromeClient(new WVChromeClient());
//        setWebViewClient(new WVClient());
    }

    //进度显示
    private class WVChromeClient extends WebChromeClient {


        @Override
        public void onProgressChanged(WebView view, int newProgress) {


            if (newProgress == 100) {
                progressBar.setVisibility(GONE);
            } else {
                if (progressBar.getVisibility() == GONE)
                    progressBar.setVisibility(VISIBLE);
                progressBar.setProgress(newProgress);
            }

//            if (mListener != null) {
//                mListener.onProgressChange(view, newProgress);
//            }

            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView webView, String s) {
            super.onReceivedTitle(webView, s);
//            if (mListener!=null){
//                mListener.onReceivedTitle(webView,s);
//            }
        }
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    private class WVClient extends WebViewClient {


    }

    private onWebViewListener mListener;

    public void setOnWebViewListener(onWebViewListener listener) {
        this.mListener = listener;
    }

    //进度回调接口
    public interface onWebViewListener {

//        void onReceivedTitle(WebView webView,String title);

        void onPageFinished(WebView webView, String url);
    }

}
