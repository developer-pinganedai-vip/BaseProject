package com.qzsy.baselibrary.utils.dialogqueue;

import android.app.Dialog;

import java.util.LinkedList;
import java.util.Queue;

public class DialogQueue {
    private Queue<Dialog> mDialogQueue = new LinkedList<>();

    /**
     * 进队
     *
     * @param dialog
     */
    public void offer(Dialog dialog) {
        mDialogQueue.offer(dialog);
    }

    /**
     * 出队
     *
     * @return
     */
    public Dialog poll() {
        return mDialogQueue.poll();
    }
}
