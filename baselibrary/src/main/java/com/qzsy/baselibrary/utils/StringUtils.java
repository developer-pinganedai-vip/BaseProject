package com.qzsy.baselibrary.utils;

import android.text.TextUtils;

/**
 * Created by z on 2018/11/19.
 */

public class StringUtils {

    //号码隐位规则
    /**
     * 国内手机号码和国际号码隐藏(针对于“-”后边)
     * @param cell
     * @return
     */
    public static String getPhoneNumHiddenText(String cell){
        if (TextUtils.isEmpty(cell)) {
            return "";
        }
        //11位正常的国内手机号码
        if (cell.length() == 11) {
            return StringUtils.getHiddenText(cell, 3, 4);
        }else if (cell.length()<6) {
            //1-5 位直接返回
            return cell;
        }else{
            //6-10位  前3位显示，第4-6位隐藏，后面都显示
            StringBuffer sBuffer=new StringBuffer(cell.substring(0,3));
            sBuffer.append("***").append(cell.substring(6, cell.length()));
            return sBuffer.toString();
        }
    }

    /*** *号指定替换，字符串
     * @param text
     * @param startIndex 起始位置 从0开始
     * @param length     替换长度
     * @return
     */
    public static String getHiddenText(String text, int startIndex, int length){
        if(TextUtils.isEmpty(text)){
            return text;
        }
        if(text.length() < startIndex || text.length() < (startIndex + length)){
            return text;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++){
            sb.append("*");
        }
        return text.substring(0, startIndex) + sb.toString() + text.substring(startIndex + length);
    }

}
