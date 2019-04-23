package com.qzsy.baselibrary.utils;

import android.app.Dialog;
import android.content.DialogInterface;


import com.qzsy.baselibrary.ui.BaseDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DialogShowManager {
    private int level = 1;
    private ArrayList<Integer> skipLevel = new ArrayList<>();
    private ArrayList<BaseDialog> baseDialog = new ArrayList<>();
    private Map<Integer,Dialog> dialogMap = new HashMap<>();
    private static DialogShowManager mInstance;
    public static DialogShowManager getInstance() {
        if (mInstance == null) {
            synchronized (DialogShowManager.class) {
                if (mInstance == null) {
                    mInstance = new DialogShowManager();
                }
            }
        }
        return mInstance;
    }

    public void addDialog(Dialog dialog,int level){
//        baseDialog.add(dialog);
        dialogMap.put(level,dialog);
        showDialog();
    }

    private void showDialog() {
//        for (int i = 1; i < dialogMap.size(); i++) {
            Dialog dialog1 = dialogMap.get(1);
            dialog1.show();
            dialog1.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    Dialog dialog2 = dialogMap.get(2);
                    dialog2.show();
                }
            });
            if (!dialog1.isShowing()){
                dialogMap.get(2).show();
            }
//        }
//        Dialog dialog1 = dialogMap.get(1);
//        if (dialog1.isShowing()){
//
//        }
//        if (baseDialog.size()>0){
////            if (!skipLevel.contains(level)){
//                for (BaseDialog mDialog : baseDialog) {
//                    if (mDialog.getLevel()==1&&!mDialog.isShowing()){
//                        mDialog.show();
//                        mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//                            @Override
//                            public void onDismiss(DialogInterface dialog) {
//                                baseDialog.remove(mDialog);
//                            }
//                        });
//                    }
//                    if (mDialog.getLevel()==level){
//                        mDialog.show();
//                        mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//                            @Override
//                            public void onDismiss(DialogInterface dialog) {
//                                level++;
//                                baseDialog.remove(mDialog);
//                                showDialog();
//                            }
//                        });
//                        break;
//                    }
//                }
////            }else{
////                level++;
////                showDialog();
////            }
//        }
    }

}
