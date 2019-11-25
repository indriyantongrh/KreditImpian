package com.application.kreditimpian.Api;

import android.database.CursorJoiner;

import com.google.gson.annotations.SerializedName;

import javax.xml.transform.Result;

/**
 * Created by Indriyanto Nugroho on 01/07/2018.
 */

public class SuccessMessage {

    @SerializedName("status")
    private int status;
    @SerializedName("reason")
    private String reason;
    @SerializedName("success")
    private String success;
    @SerializedName("message")
    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Result getRs() {
        return rs;
    }

    public void setRs(Result rs) {
        this.rs = rs;
    }

    @SerializedName("rs")
    private Result rs;




/*
    String  success;
    String message;

    public String getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }*/

}
