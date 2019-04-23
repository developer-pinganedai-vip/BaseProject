package com.qzsy.baselibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.SparseArray;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UUID;


public class HardWare {
    private final static String TAG = "HardWare";

    static volatile HardWare instance;

    private static int screenHeight = 0;
    private static int screenWidth = 0;
    private static int densityDpi = 0;

    private static int status_bar_height = -1;
    private static String udid = "";
    private static String mac = "";

    private SparseArray<Handler> handlers = null;

    public final static int kNetType = 0;
    public final static int kWapType = 1;
    private final static int kWapType_172 = 3;
    private final static int kWapType_200 = 5;

    public static int netTryNum = 3;
    private static int netTryIndex = 0;
    private static int netTypeArray[] = new int[3];
    private static boolean netAvailable = false;
    public static boolean isWiFi = true;

    //   private BroadcastReceiver mScreenActionReceiver;
    private static long ScreenOffTime = 0;

    private static MediaPlayer mediaPlayer;
    private static final float BEEP_VOLUME = 0.10f;
    private static final long VIBRATE_DURATION = 200L;
    public final static String KeyDeviceID = "UserDeviceId";

    public static String getUdid(Context con) {
        if (checkUdidValid() == false) {
            SharedPreferences sharepre = PreferenceManager.getDefaultSharedPreferences(con);
            udid = sharepre.getString(KeyDeviceID, "");
            if (checkUdidValid() == false) {
                try {
                    TelephonyManager tm = (TelephonyManager) con.getSystemService(Context.TELEPHONY_SERVICE);
                    udid = "" + tm.getDeviceId();
                } catch (Exception e) {
                    udid = "";
                }

                if (checkUdidValid() == false) {
                    if (Validator.isEffective(mac))
                        udid = "MAC" + mac.replace(':', '0').replace('.', '0');
                    else
                        udid = "";
                    if (checkUdidValid() == false) {
                        try {
                            udid = "" + Settings.Secure.getString(con.getContentResolver(), Settings.Secure.ANDROID_ID);
                        } catch (Exception e) {
                            udid = "";
                        }
                        if (checkUdidValid() == false) {
                            getRandomUdidFromFile(con);
                        }
                    }
                }

                if (checkUdidValid() == true) {
                    Editor editor = sharepre.edit();
                    editor.putString("UserDeviceId", udid);
                    editor.commit();
                }
            }
        }

        return udid;
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

    private static String readInstallationFile(File installation)
            throws IOException {
        RandomAccessFile f = new RandomAccessFile(installation, "r");
        byte[] bytes = new byte[(int) f.length()];
        f.readFully(bytes);
        f.close();
        return new String(bytes);
    }

    private static void writeInstallationFile(File installation)
            throws IOException {
        FileOutputStream out = new FileOutputStream(installation);
        String id = UUID.randomUUID().toString();
        out.write(id.getBytes());
        out.close();
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

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }


    public static String getString(Context mContext, int resId) {
        return mContext.getResources().getString(resId);
    }


}
