package com.qzsy.baselibrary.utils;

public class CountDownTimeUtil {
    private int DURATION = 0;

    public boolean check(int duration) {
        long data = System.currentTimeMillis();
        long time = 0;
        if (data > time) {
            time = data + duration * 1000;
            return false;
        }else{
            return true;
        }
    }
}
