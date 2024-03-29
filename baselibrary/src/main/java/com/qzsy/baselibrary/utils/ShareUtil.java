package com.qzsy.baselibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;


/**
 * Created by liwk on 2018/4/19.
 */

public class ShareUtil {
    private Context mContext;
    private Activity mActivity;

    public ShareUtil(Context context) {
        this.mContext = context;
    }

    public ShareUtil(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public void ShareToSms(String shareUrl) {
        Uri smsToUri = Uri.parse("smsto:");
        Intent mIntent = new Intent(Intent.ACTION_SENDTO, smsToUri);
        mIntent.putExtra("sms_body", shareUrl);
        mActivity.startActivity(mIntent);
//        new ShareAction(mActivity).setPlatform(SHARE_MEDIA.SMS).withText(shareUrl).setCallback(shareListener).share();
    }

    public void ShareToQQ(String shareUrl, String title, String description, String thumb) {
//        UMWeb umWeb = new UMWeb(shareUrl);
//        umWeb.setTitle(title);//标题
////        umWeb.setThumb();//缩略图
//        umWeb.setDescription(description);//描述
//        new ShareAction(mActivity).setPlatform(SHARE_MEDIA.QQ).withMedia(umWeb).setCallback(shareListener).share();

    }

    public void ShareToWeiXin(String shareUrl, String title, String description, String thumb) {
//        UMWeb umWeb = new UMWeb(shareUrl);
//        umWeb.setTitle(title);//标题
////        umWeb.setThumb(null);//缩略图
//        umWeb.setDescription(description);//描述
//        new ShareAction(mActivity).setPlatform(SHARE_MEDIA.WEIXIN).withMedia(umWeb).setCallback(shareListener).share();

    }

    public void ShareToWeixinCircle(String shareUrl, String title, String description, String thumb) {
//        UMWeb umWeb = new UMWeb(shareUrl);
//        umWeb.setTitle(title);//标题
////        umWeb.setThumb(null);//缩略图
////        umWeb.setThumb(UMImage.);
//        umWeb.setDescription(description);//描述
//        new ShareAction(mActivity).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE).withMedia(umWeb).setCallback(shareListener).share();

    }


//    private UMShareListener shareListener = new UMShareListener() {
//        /**
//         * @descrption 分享开始的回调
//         * @param share_media 平台类型
//         */
//        @Override
//        public void onStart(SHARE_MEDIA share_media) {
//            switch (share_media) {
//                case WEIXIN:
//                    LogUtils.d("微信分享开始");
//                    break;
//                case WEIXIN_CIRCLE:
//                    LogUtils.d("朋友圈分享开始");
//                    break;
//                case QQ:
//                    LogUtils.d("QQ分享开始");
//                    break;
//                case SMS:
//                    LogUtils.d("短信分享开始");
//                    break;
//            }
//        }
//
//
//        /**
//         * @descrption 分享成功的回调
//         * @param share_media 平台类型
//         */
//        @Override
//        public void onResult(SHARE_MEDIA share_media) {
//            switch (share_media) {
//                case WEIXIN:
//                    LogUtils.d("微信分享成功");
//                    break;
//                case WEIXIN_CIRCLE:
//                    LogUtils.d("朋友圈分享成功");
//                    break;
//                case QQ:
//                    LogUtils.d("QQ分享成功");
//                    break;
//                case SMS:
//                    LogUtils.d("短信分享成功");
//                    break;
//            }
//        }
//
//        /**
//         * @descrption 分享失败的回调
//         * @param share_media 平台类型
//         * @param throwable 错误原因
//         */
//        @Override
//        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
//            switch (share_media) {
//                case WEIXIN:
////                    40001    invalid credential    不合法的调用凭证
////                    40002    invalid grant_type    不合法的grant_type
////                    40003    invalid openid    不合法的OpenID
////                    40004    invalid media type    不合法的媒体文件类型
////                    40007    invalid media_id    不合法的media_id
////                    40008    invalid message type    不合法的message_type
////                    40009    invalid image size    不合法的图片大小
////                    40010    invalid voice size    不合法的语音大小
////                    40011    invalid video size    不合法的视频大小
////                    40012    invalid thumb size    不合法的缩略图大小
////                    40013    invalid appid    不合法的AppID
////                    40014    invalid access_token    不合法的access_token
////                    40015    invalid menu type    不合法的菜单类型
////                    40016    invalid button size    不合法的菜单按钮个数
////                    40017    invalid button type    不合法的按钮类型
////                    40018    invalid button name size    不合法的按钮名称长度
////                    40019    invalid button key size    不合法的按钮KEY长度
////                    40020    invalid button url size    不合法的url长度
////                    40023    invalid sub button size    不合法的子菜单按钮个数
////                    40024    invalid sub button type    不合法的子菜单类型
////                    40025    invalid sub button name size    不合法的子菜单按钮名称长度
////                    40026    invalid sub button key size    不合法的子菜单按钮KEY长度
////                    40027    invalid sub button url size    不合法的子菜单按钮url长度
////                    40029    invalid code    不合法或已过期的code
////                    40030    invalid refresh_token    不合法的refresh_token
////                    40036    invalid template_id size    不合法的template_id长度
////                    40037    invalid template_id    不合法的template_id
////                    40039    invalid url size    不合法的url长度
////                    40048    invalid url domain    不合法的url域名
////                    40054    invalid sub button url domain    不合法的子菜单按钮url域名
////                    40055    invalid button url domain    不合法的菜单按钮url域名
////                    40066    invalid url    不合法的url
////                    41001    access_token missing    缺失access_token参数
////                    41002    appid missing    缺失appid参数
////                    41003    refresh_token missing    缺失refresh_token参数
////                    41004    appsecret missing    缺失secret参数
////                    41005    media data missing    缺失二进制媒体文件
////                    41006    media_id missing    缺失media_id参数
////                    41007    sub_menu data missing    缺失子菜单数据
////                    41008    missing code    缺失code参数
////                    41009    missing openid    缺失openid参数
////                    41010    missing url    缺失url参数
////                    42001    access_token expired    access_token超时
////                    42002    refresh_token expired    refresh_token超时
////                    42003    code expired    code超时
////                    43001    require GET method    需要使用GET方法请求
////                    43002    require POST method    需要使用POST方法请求
////                    43003    require https    需要使用HTTPS
////                    43004    require subscribe    需要订阅关系
////                    44001    empty media data    空白的二进制数据
////                    44002    empty post data    空白的POST数据
////                    44003    empty news data    空白的news数据
////                    44004    empty content    空白的内容
////                    44005    empty list size    空白的列表
////                    45001    media size out of limit    二进制文件超过限制
////                    45002    content size out of limit    content参数超过限制
////                    45003    title size out of limit    title参数超过限制
////                    45004    description size out of limit    description参数超过限制
////                    45005    url size out of limit    url参数长度超过限制
////                    45006    picurl size out of limit    picurl参数超过限制
////                    45007    playtime out of limit    播放时间超过限制（语音为60s最大）
////                    45008    article size out of limit    article参数超过限制
////                    45009    api freq out of limit    接口调动频率超过限制
////                    45010    create menu limit    建立菜单被限制
////                    45011    api limit    频率限制
////                    45012    template size out of limit    模板大小超过限制
////                    45016    can't modify sys group    不能修改默认组
////                    45017    can't set group name too long sys group    修改组名过长
////                    45018    too many group now, no need to add new    组数量过多
////                    50001    api unauthorized    接口未授权
//                    LogUtils.d("微信分享失败，错误原因：" + throwable.getMessage());
//                    break;
//                case WEIXIN_CIRCLE:
//                    LogUtils.d("朋友圈分享失败，错误原因：" + throwable.getMessage());
//                    break;
//                case QQ:
////                    110201：未登陆
////                    110405：登录请求被限制
////                    110404：请求参数缺少appid
////                    110401：请求的应用不存在
////                    110407：应用已经下架
////                    110406：应用没有通过审核
////                    100044：未经过安全校验的包名和签名
////                    110500：获取用户授权信息失败
////                    110501：获取应用的授权信息失败
////                    110502：设置用户授权失败
////                    110503：获取token失败
////                    110504：系统内部错误
//                    LogUtils.d("QQ分享失败，错误原因：" + throwable.getMessage());
//                    break;
//                case SMS:
//                    LogUtils.d("短信分享失败，错误原因：" + throwable.getMessage());
//                    break;
//            }
//        }
//
//        /**
//         * @descrption 分享取消的回调
//         * @param share_media 平台类型
//         */
//        @Override
//        public void onCancel(SHARE_MEDIA share_media) {
//            switch (share_media) {
//                case WEIXIN:
//                    LogUtils.d("微信分享取消");
//                    break;
//                case WEIXIN_CIRCLE:
//                    LogUtils.d("朋友圈分享取消");
//                    break;
//                case QQ:
//                    LogUtils.d("QQ分享取消");
//                    break;
//                case SMS:
//                    LogUtils.d("短信分享取消");
//                    break;
//            }
//        }
//    };
}
