package com.qzsy.baselibrary.utils.dialogqueue;

import android.app.Dialog;
import android.content.DialogInterface;


import com.qzsy.baselibrary.utils.LogUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class DialogQueueUtils {

    private DialogQueue dialogQueue;
    private Dialog mCurrentDialog = null;

    private DialogQueueUtils(){
        dialogQueue = new DialogQueue();
    }

    public static DialogQueueUtils getInstance(){
        return DialogQueueHolder.singleton;
    }

    /**
     * 单例模式->静态内部类<br/>
     * 多线程情况下，使用合理一些,推荐
     */
    private static class DialogQueueHolder {
        private static DialogQueueUtils singleton = new DialogQueueUtils();
    }

    public void addDialog(Map<Integer,Dialog> mapList){
        List<Map.Entry<Integer,Dialog>> list = new ArrayList<Map.Entry<Integer, Dialog>>(mapList.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Dialog>>() {
            @Override
            public int compare(Map.Entry<Integer, Dialog> o1, Map.Entry<Integer, Dialog> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });

        for (Map.Entry<Integer, Dialog> integerDialogEntry : list) {
            if (integerDialogEntry.getValue()!=null){
                LogUtils.e("addDialog="+integerDialogEntry.getValue().toString());
                dialogQueue.offer(integerDialogEntry.getValue());
            }
        }


    }

    public void addDialog(Dialog dialog){
        if (dialog!=null){
            dialogQueue.offer(dialog);
        }
    }


    public void show(){
        if (mCurrentDialog==null){
            mCurrentDialog = dialogQueue.poll();
            if (mCurrentDialog!=null){
                mCurrentDialog.show();
                mCurrentDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        mCurrentDialog = null;
                        show();
                    }
                });
            }
        }
    }

}
