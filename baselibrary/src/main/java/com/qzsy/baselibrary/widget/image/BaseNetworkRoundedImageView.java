package com.qzsy.baselibrary.widget.image;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;


public class BaseNetworkRoundedImageView extends RoundedImageView{
    private String imageUrl;
    private boolean dontLoadSameUrl;
    
    private int currDisplayedDafaultResId;
    private int defaultResId;

    public BaseNetworkRoundedImageView(Context context) {
        super(context);
    }
    
    public BaseNetworkRoundedImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public void setDefaultDrawableRes(int res){
        defaultResId = res;
    }

    public void setDontLoadSameUrl(boolean dontLoadSameUrl){
        this.dontLoadSameUrl = dontLoadSameUrl;
    }

    public void setImageUrl(String url, String thumbUrl){
        setImageUrl(url, null, thumbUrl);
    }

    public void setImageUrl(String url, final OnGetImageSizeListener listener, final String thumbUrl){
//        if(url != null && url.equals(imageUrl)){
//            return;
//        }
        if(dontLoadSameUrl){
            if(url != null && url.equals(imageUrl) && (currDisplayedDafaultResId == defaultResId)){
                return;
            }
        }
        
        imageUrl = (url == null)? "" : url;

        currDisplayedDafaultResId = defaultResId;

        if(Build.VERSION.SDK_INT >= 17 && getContext() instanceof Activity){
            if(((Activity) getContext()).isDestroyed()){
                return;
            }
        }

        try {
            if(listener == null){
                Glide.with(this)
                        .load(thumbUrl)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                return false;
                            }
                        })
                        .apply(new RequestOptions().centerInside())
                        .thumbnail(Glide.with(this).load(defaultResId))
                        .into(this);


            }else {
                Glide.with(this)
                        .load(thumbUrl)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                return false;
                            }
                        })
                        .apply(new RequestOptions().centerInside())
                        .thumbnail(Glide.with(this).load(defaultResId))
                        .into(this);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setImageUrl(String url, final String thumbUrl, int width, int height){
        if(dontLoadSameUrl){
            if(url != null && url.equals(imageUrl) && (currDisplayedDafaultResId == defaultResId)){
                return;
            }
        }
        imageUrl = (url == null)? "" : url;
        currDisplayedDafaultResId = defaultResId;
        if(Build.VERSION.SDK_INT >= 17 && getContext() instanceof Activity){
            if(((Activity) getContext()).isDestroyed()){
                return;
            }
        }
        try {
            Glide.with(this)
                    .load(thumbUrl)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }
                    })
                    .apply(new RequestOptions().centerInside())
                    .thumbnail(Glide.with(this).load(defaultResId))
                    .into(this);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public interface OnGetImageSizeListener{
        public void onGetImageSize(String url, int width, int height);
    }

}
