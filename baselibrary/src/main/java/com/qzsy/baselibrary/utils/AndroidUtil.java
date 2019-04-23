package com.qzsy.baselibrary.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;


import com.qzsy.baselibrary.BuildConfig;
import com.qzsy.baselibrary.R;
import com.qzsy.baselibrary.utils.encypt.rsa.BASE64Encoder;
import com.qzsy.baselibrary.utils.encypt.rsa.DESUtils;
import com.qzsy.baselibrary.utils.encypt.rsa.JsonUtils;
import com.qzsy.baselibrary.utils.encypt.rsa.MD5Utils;
import com.qzsy.baselibrary.utils.prefs.ImplPreferencesHelper;
import com.qzsy.baselibrary.utils.prefs.SpHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.Context.ACTIVITY_SERVICE;
import static android.content.Context.TELEPHONY_SERVICE;


/**
 * android系统相关常用操作
 * 说明：提供一些android系统常用操作：如系统版本，图片操作等
 */

public class AndroidUtil {


    private static String mac = "";

    public static String getUA(Context context) {

        String ua = "feilu://" + AndroidUtil.getAppVersionName(context) + " (Android;android" + AndroidUtil.getAndroidSDKVersion() + ";zh_CN;ID:2-" + "-"
                + HardWare.getUdid(context) + "-" + getMarketId(context) + "-" + ")";

        return ua;
    }

    /**
     * 获取应用程序名称
     */
   /* public static synchronized String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.applicationInfo.nonLocalizedLabel.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }*/

    /**
     * 获取应用程序名称
     */
    public static synchronized String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取sdk版本
     *
     * @return
     */
    public static int getAndroidSDKVersion() {
        return Build.VERSION.SDK_INT;
    }


    /**
     * 返回当前程序版本名
     */
    public static String getAppVersionName(Context context) {
        String versionName = "0.0.0";
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            // 这里的context.getPackageName()可以换成你要查看的程序的包名
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            //Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }

    /**
     * 是否软键盘显示
     *
     * @param rootView
     * @return
     */
    public static boolean isKeyboardShown(View rootView) {
        Rect r = new Rect();
        rootView.getWindowVisibleDisplayFrame(r);
        int heightDiff = r.bottom - rootView.getBottom();
        return heightDiff < 0;
    }

    public static GradientDrawable buildGradientDrawable() {
        int strokeWidth = 1; // 1dp 边框宽度
        int roundRadius = 100; // 2dp 圆角半径
        int strokeColor = 0xab000000;//边框颜色
        int fillColor = 0xab000000;//内部填充颜色
        GradientDrawable gradientDrawable = new GradientDrawable();//创建drawable
        gradientDrawable.setColor(fillColor);
        gradientDrawable.setCornerRadius(roundRadius);
        gradientDrawable.setStroke(strokeWidth, strokeColor);
        return gradientDrawable;
    }

    /**
     * 获取屏幕宽
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * 获得设备识别认证码
     *
     * @return
     */
    public static String getIMEI(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
        if (tm == null) {
            return null;
        }
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return tm.getDeviceId();
        }
        return null;
    }

    public static String getSHA1(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_SIGNATURES);
            byte[] cert = info.signatures[0].toByteArray();
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(cert);
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < publicKey.length; i++) {
                String appendString = Integer.toHexString(0xFF & publicKey[i]).toUpperCase(Locale.US);
                if (appendString.length() == 1)
                    hexString.append("0");
                hexString.append(appendString);
                hexString.append(":");
            }
            String result = hexString.toString();
            return result.substring(0, result.length() - 1);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

//    /**
//     * app是否被安装
//     *
//     * @param context
//     * @param packageName
//     * @return
//     */
//    public static boolean isAppInstalled(Context context, String packageName) {
//        final PackageManager packageManager = context.getPackageManager();
//        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
//        List<String> pName = new ArrayList<String>();
//        if (pinfo != null) {
//            for (int i = 0; i < pinfo.size(); i++) {
//                String pn = pinfo.get(i).packageName;
//                pName.add(pn);
//            }
//        }
//        return pName.contains(packageName);
//    }

    /**
     * 窗口背景透明度改变
     *
     * @param context
     * @param bgAlpha
     */
    public static void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha; // 0.0-1.0
        context.getWindow().setAttributes(lp);
    }

    /**
     * 获取Umeng渠道号
     *
     * @param context
     * @return 渠道号
     */
    public static String getChannel(Context context) {
        String channel = "official";
        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            if (info != null && info.metaData != null) {
                String metaData = "";
                try {
                    metaData = info.metaData.getString("UMENG_CHANNEL");
                } catch (Exception e) {
                    metaData = "";
                }

                if (!TextUtils.isEmpty(metaData)) {
                    channel = metaData;
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return channel;
    }

    private static String udid = "";

    /**
     * 获取设备号
     *
     * @param con
     * @return
     */
    public final static String KeyDeviceID = "UserDeviceId";

    @SuppressLint("HardwareIds")
    public static String getUdid(Context con) {
        if (!checkUdidValid()) {
            SharedPreferences sharepre = PreferenceManager.getDefaultSharedPreferences(con);
            udid = sharepre.getString(KeyDeviceID, "");
            if (!checkUdidValid()) {
                udid = "" + getUniquePsuedoID();
//                try {
//                    TelephonyManager tm = (TelephonyManager) con.getSystemService(Context.TELEPHONY_SERVICE);
//                    if (ActivityCompat.checkSelfPermission(con, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//                        // TODO: Consider calling
//                        //    ActivityCompat#requestPermissions
//                        // here to request the missing permissions, and then overriding
//                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                        //                                          int[] grantResults)
//                        // to handle the case where the user grants the permission. See the documentation
//                        // for ActivityCompat#requestPermissions for more details.
//                        if (tm != null) {
//                            return udid = "" + tm.getDeviceId();
//                        }
//                    }
//                } catch (Exception e) {
//                    udid = "";
//                }

//                if (!checkUdidValid()) {
//                    if (Validator.isEffective(mac))
//                        udid = "MAC" + mac.replace(':', '0').replace('.', '0');
//                    else
//                        udid = "";
//                    if (!checkUdidValid()) {
//                        try {
//                            udid = "" + Settings.Secure.getString(con.getContentResolver(), Settings.Secure.ANDROID_ID);
//                        } catch (Exception e) {
//                            udid = "";
//                        }
//                        if (!checkUdidValid()) {
//                            getRandomUdidFromFile(con);
//                        }
//                    }
//                }

                if (checkUdidValid()) {
                    SharedPreferences.Editor editor = sharepre.edit();
                    editor.putString("UserDeviceId", udid);
                    editor.apply();
                }
            }
        }

        return udid;
    }

    //获得独一无二的Psuedo ID
    public static String getUniquePsuedoID() {
        String serial = null;

        String m_szDevIDShort = "35" +
                Build.BOARD.length() % 10 + Build.BRAND.length() % 10 +

                Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10 +

                Build.DISPLAY.length() % 10 + Build.HOST.length() % 10 +

                Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10 +

                Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10 +

                Build.TAGS.length() % 10 + Build.TYPE.length() % 10 +

                Build.USER.length() % 10; //13 位

        try {
            serial = Build.class.getField("SERIAL").get(null).toString();
            //API>=9 使用serial号
            return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
        } catch (Exception exception) {
            //serial需要一个初始化
            serial = "serial"; // 随便一个初始化
        }
        //使用硬件信息拼凑出来的15位号码
        return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
    }

    private static boolean checkUdidValid() {
        if (udid != null && !"".equals(udid) && !"null".equals(udid)
                && !"NULL".equals(udid) && !checkUdidZero()
                && !"9774d56d682e549c".equals(udid)) // SDK version 2.2, some devices have the same id
        {
            int len = 10 - udid.length();
            for (int i = 0; i < len; i++) {
                udid = "0" + udid;
            }
            return true;
        } else
            return false;
    }

    private static boolean checkUdidZero() {
        try {
            int val = Integer.parseInt(udid);
            return val == 0;
        } catch (Exception e) {
            return false;
        }
    }

    private synchronized static void getRandomUdidFromFile(Context context) {
        File installation = new File(context.getFilesDir(), "INSTALLATION");
        try {
            if (!installation.exists())
                writeInstallationFile(installation);
            udid = readInstallationFile(installation);
        } catch (Exception e) {
            udid = "";
        }
    }

    private static void writeInstallationFile(File installation)
            throws IOException {
        FileOutputStream out = new FileOutputStream(installation);
        String id = UUID.randomUUID().toString();
        out.write(id.getBytes());
        out.close();
    }


    /**
     * @param map 根据传入的map参数,加密后返回新的Map,第一个参数为:XN_CIPHER,第二个参数为XN_TOKEN
     */
    public static Map RsaEncrypt(Map map) {
        String jsonString = JsonUtils.toJson(map);
        String XN_CIPHER = DESUtils.getInstance().encryptDES(jsonString, "tZSvHgxs");

        String md5Sign = MD5Utils.getInstance().createMD532(XN_CIPHER);
        byte[] cipher = md5Sign.getBytes();
        String XN_TOKEN = new BASE64Encoder().encode(cipher);
        Map<String, String> map_encrypt = new HashMap<>();
        map_encrypt.put("XN_CIPHER", XN_CIPHER);
        map_encrypt.put("XN_TOKEN", XN_TOKEN);
        return map_encrypt;
    }

    private static String readInstallationFile(File installation)
            throws IOException {
        RandomAccessFile f = new RandomAccessFile(installation, "r");
        byte[] bytes = new byte[(int) f.length()];
        f.readFully(bytes);
        f.close();
        return new String(bytes);
    }

    /**
     * @return MAC or empty
     */
    public static String getMacAddress(Context con) {
        try {
            WifiManager mWifiManager = (WifiManager) con.getSystemService(Context.WIFI_SERVICE);
            if (mWifiManager != null) {
                WifiInfo wifiInfo = mWifiManager.getConnectionInfo();
                if (wifiInfo != null) {
                    return wifiInfo.getMacAddress();
                }
            }
        } catch (Exception e) {
        }
        return "";
    }

    /**
     * @return MAC or empty
     */
    public static String getMacAddress2(Context con) {
        try {
            WifiManager mWifiManager = (WifiManager) con.getSystemService(Context.WIFI_SERVICE);
            if (mWifiManager != null) {
                WifiInfo wifiInfo = mWifiManager.getConnectionInfo();
                if (wifiInfo != null) {
                    return wifiInfo.getMacAddress();
                }
            }
        } catch (Exception e) {
        }
        return "";
    }

    /**
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014年06月14日16时09分00秒"）
     *
     * @param time
     * @return
     */
    public static String getDate(String time) {

        return time;
    }

    /**
     * 获取ip地址
     *
     * @return
     */
    public static String getHostIP() {

        String hostIp = null;
        try {
            Enumeration nis = NetworkInterface.getNetworkInterfaces();
            InetAddress ia = null;
            while (nis.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) nis.nextElement();
                Enumeration<InetAddress> ias = ni.getInetAddresses();
                while (ias.hasMoreElements()) {
                    ia = ias.nextElement();
                    if (ia instanceof Inet6Address) {
                        continue;// skip ipv6
                    }
                    String ip = ia.getHostAddress();
                    if (!"127.0.0.1".equals(ip)) {
                        hostIp = ia.getHostAddress();
                        break;
                    }
                }
            }
        } catch (SocketException e) {
            Log.i("yao", "SocketException");
            e.printStackTrace();
        }
        return hostIp;

    }

    /**
     * 获取市场渠道名
     *
     * @param context
     * @return
     */
    public static String getMarketId(Context context) {
        String appType = "0";
        try {
            ApplicationInfo appInfo = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
            appType = String.valueOf(appInfo.metaData.get("UMENG_CHANNEL"));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return appType;
    }

    public static String remand(String str){
        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (i%5==0){
                sb.append(chars[i]);
            }
        }

        return sb.toString();
    }

    public static String md5(String str) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

        char[] charArray = str.toCharArray();
        byte[] byteArray = new byte[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    /**
     * 获取首页借款后各种状态的高度
     *
     * @param context
     * @return
     */
    public static int getHomeHeight(Context context) {
//        int screenWidth = getScreenWidth(context);
//        int width1 = screenWidth - DeviceUtils.dip2px(context, 24);
//        int bann_height = 182 * width1 / 702;
//        int ivInvite_width = (screenWidth - DeviceUtils.dip2px(context, 24)) / 2;
//        int ivInvite_height = ivInvite_width * 165 / 350;
//        int offsetHeight=DeviceUtils.dip2px(context, 108);
//       return bann_height+ivInvite_height+offsetHeight;
        //92
        return DeviceUtils.dip2px(context, 272);
    }

    /**
     * 设置edittext 禁止输入表情
     */
    public static InputFilter getEmojiFilter() {
        InputFilter emojiFilter = new InputFilter() {
            Pattern pattern = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|" +
                    "[\u2600-\u27ff]", Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                Matcher matcher = pattern.matcher(source);
                if (matcher.find()) {
                    ToastUtils.showShort("暂不支持表情输入!");
                    return "";
                }
                return null;
            }
        };
        return emojiFilter;
    }


    /**
     * 判断邮编
     *
     * @return
     */
    public static boolean isZipNO(String zipString) {
        String str = "^[1-9][0-9]{5}$";
        return Pattern.compile(str).matcher(zipString).matches();
    }


    public static boolean isMobileNO(String mobiles) {
        String regExp = "^((13[0-9])|(15[^4])|(166)|(17[01345678])|(18[0-9])|(19[8-9])|(14[05789]))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 判断是否含有特殊字符
     *
     * @param str
     * @return true为包含，false为不包含
     */
    public static boolean isSpecialChar(String str) {
        String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }

    public static boolean isLogin(){
        return !TextUtils.isEmpty(implPreferencesHelper.getCustomerId());
    }

    static ImplPreferencesHelper implPreferencesHelper = new ImplPreferencesHelper();

    public static String getToken() {
        return implPreferencesHelper.getToken();
    }

    public static void clearUserInfo() {
        implPreferencesHelper.clear();
    }

    public static String getCustomerId() {
        return implPreferencesHelper.getCustomerId();
    }


    public static String getPhoneNum() {
        return implPreferencesHelper.getPhoneNum();
    }


    public static String getNickName() {
        return implPreferencesHelper.getNickName();
    }

    public static String getNetIp() {
        return implPreferencesHelper.getNetIp();
    }


    public static String getAppId(){
        return implPreferencesHelper.getAppId();
    }

    public static void clearSp() {
        implPreferencesHelper.clearSP();
    }

    public static String getCreateTime() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }


    public static void getServiceCall(final Context context, final String phone) {
        /*final Dialog dialog = new Dialog(context, R.style.custom_dialog);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_call_service, null);
        TextView tvTel = (TextView) view.findViewById(R.id.tv_tel);
        tvTel.setText(phone);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE},
                            8908);
                } else {
                    //拨打电话
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
                            + phone));
                    context.startActivity(intent);
                }
            }
        });
        view.setLayoutParams(params);
        dialog.setContentView(view);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();*/
    }

    public static boolean checkAliPayInstalled(Context context) {

        Uri uri = Uri.parse("alipays://platformapi/startApp");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        ComponentName componentName = intent.resolveActivity(context.getPackageManager());
        return componentName != null;
    }

    public static boolean isAppInstalled(Context context, String packagename) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packagename, 0);
        } catch (PackageManager.NameNotFoundException e) {
            packageInfo = null;
            e.printStackTrace();
        }

        return packageInfo != null;
    }


    public static String getAppMetaData(Context context, String key) {
        if (context == null || TextUtils.isEmpty(key)) {
            return null;
        }

        String channelNumber = null;
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        channelNumber = applicationInfo.metaData.getString(key);
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return channelNumber;
    }


    public static int getAppNum(Context context){
        String appName = getAppName(context);
        if (appName.equals("无忧贷款钱包")){
            return 2;
        }else if (appName.equals("简单极速贷")){
            return 8;
        }else if (appName.equals("无忧简单极速贷")){
            return 9;
        }else if (appName.equals("蚂蚁闪电借")){
            return 20000;
        }
        return -1;
    }

    public static String getAName(Context context){
        String appName = getAppName(context);
        if (appName.equals("无忧贷款钱包")){
            return "wydkqb";
        }else if (appName.equals("简单极速贷")){
            return "jdjsd";
        }else if (appName.equals("无忧简单极速贷")){
            return "wyjdjsd";
        }else if (appName.equals("蚂蚁闪电借")){
            return "mysdj";
        }
        return null;
    }

    public static String getCloundVersion() {
        return "1.0.0";
    }


    static SpHelper spHelper = new SpHelper();

    public static String getBlackBox() {
        return spHelper.getBlackBox();
    }


   /* public static String getNewAppId(){
        return BuildConfig.APP_ID;
    }

    public static String getLoanUid(){
        return SingletonCommon.getInstance().getLoanUid();
    }
*/
    /**
     * 判断某一个类是否存在任务栈里面
     *
     * @return
     */
    public static boolean isExitMainActivity(Context activity, Class<?> cls) {
        if (activity == null) {
            return false;
        }
        Intent intent = new Intent(activity, cls);
        ComponentName cmpName = intent.resolveActivity(activity.getPackageManager());
        boolean flag = false;
        if (cmpName != null) { // 说明系统中存在这个activity
            ActivityManager am = (ActivityManager) activity.getSystemService(ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> taskInfoList = am.getRunningTasks(10);
            for (ActivityManager.RunningTaskInfo taskInfo : taskInfoList) {
                if (taskInfo.baseActivity.equals(cmpName)) { // 说明它已经启动了
                    flag = true;
                    break;  //跳出循环，优化效率
                }
            }
        }
        return flag;
    }



}
