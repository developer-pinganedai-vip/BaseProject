package com.qzsy.baselibrary.widget.bean;



/**
 * Created by fengpeihao on 2017/6/5.
 */

public class MessageEvent {
    private String type;
    private String message;
    private boolean isUpdateDialogShowing;

    public MessageEvent(String type) {
        this.type = type;
    }


    public MessageEvent() {
    }




    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isUpdateDialogShowing() {
        return isUpdateDialogShowing;
    }

    public void setUpdateDialogShowing(boolean updateDialogShowing) {
        isUpdateDialogShowing = updateDialogShowing;
    }

    public MessageEvent(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public MessageEvent(String type, boolean isUpdateDialogShowing) {
        this.type = type;
        this.isUpdateDialogShowing = isUpdateDialogShowing;
    }
}
