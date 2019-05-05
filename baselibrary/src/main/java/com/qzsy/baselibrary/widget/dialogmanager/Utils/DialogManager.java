package com.qzsy.baselibrary.widget.dialogmanager.Utils;

import android.content.Context;
import android.util.Log;


import com.qzsy.baselibrary.widget.dialogmanager.bean.BuildBean;
import com.qzsy.baselibrary.widget.dialogmanager.listener.DialogDisMissListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 类描述:多弹窗显示管理
 * 只需将DialogBase,Push进队列中即可
 * DialogShowManager.getInstance().pushToQueue(BuildBean)
 */
public class DialogManager {

    private static final String TAG = "DialogShowManager";
    private static Queue<BuildBean> queue = new ConcurrentLinkedQueue<>(); //弹窗队列(线程安全)
    private static Map<Integer,BuildBean> map = new HashMap<>();
    private static DialogManager mInstance;
    private BuildBean mDialogBase;

    public static DialogManager getInstance() {
        if (mInstance == null) {
            synchronized (DialogManager.class) {
                if (mInstance == null) {
                    mInstance = new DialogManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 初始为0,pushToQueue 基数必然为1,所以小于2即可
     *
     * @return
     */
    public boolean canShow() {
        return queue.size() < 2/* && !isLock*/;
    }


    /**
     * 每次弹窗调用PushQueue方法
     *
     * @param dialogBase
     */
    public void  pushToQueue(BuildBean dialogBase) {
        //添加到队列中
        if (dialogBase != null) {
            dialogBase.setDisMissListener(new DialogDisMissListener(){

                @Override
                public void onDisMiss() {
                    Log.e(TAG, "nextTask");
                    nextTask();
                }
            });
            Log.e(TAG, "add..");
            queue.add(dialogBase);
            //只有当前队列数量为1时才能进行下一步操作
            if (canShow()) {
                startNextIf();
            }
        }
    }


    /**
     * 显示下一个弹窗任务
     */
    private void startNextIf() {
        if (queue != null && queue.isEmpty()) {
            return;
        }
        //Todo 可在此处对弹窗进行排序
        oderDialog();
        mDialogBase = queue.element();
        if (mDialogBase != null) {
            mDialogBase.show();
        } else {
            Log.e(TAG, "任务队列为空...");
        }
    }

    private void oderDialog() {


    }


    /**
     * 清除对象
     */
    public void clear() {
        queue.clear();
        if (mDialogBase != null){
            mDialogBase.dismiss();
            mDialogBase = null;
        }
    }

    /**
     * 移除队列的头,获取最新队列头
     */
    private void removeTopTask() {
        queue.poll(); //出栈
    }

    /**
     * 提供外部下一个任务的方法,在弹窗消失时候调用
     */
    private void nextTask() {
        removeTopTask();
        startNextIf();
    }

    /**
     * 根据传的type，放在前面的先显示
     * @param context
     * @param list
     */
    public void putType(Context context,int[] list){
        clear();
        if (list != null && list.length > 0){
            for (int i = 0; i<list.length;i++){

                BuildBean buildBean = map.get(list[i]);
                if (buildBean !=null){
                    pushToQueue(buildBean);
                }else {
                    BuildBean bean = DialogUIUtils.showDialog(context, list[i]);
                    pushToQueue(bean);
                }
            }
        }
    }

    /**
     * 用于根据type来排序，type数字越小，显示越靠前
     * @param context
     * @param nums
     */
    public void putTypeSort(Context context,int[] nums){
        int[] ints = bubbleSort(nums);
        putType(context,ints);

    }

    public int[] bubbleSort(int[] nums) {

        //传进来的数组只有0或者1个元素，则不需要排序
        int length = nums.length;
        if (length < 2) {
            return nums;
        }

        for (int i = length -1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j + 1]) {
                    int data = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = data;
                }
            }
        }
        return nums;
    }

}
