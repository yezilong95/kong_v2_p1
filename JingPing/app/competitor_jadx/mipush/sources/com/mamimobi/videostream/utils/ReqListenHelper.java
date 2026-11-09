package com.mamimobi.videostream.utils;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class ReqListenHelper implements Serializable {
    public String E;
    public String F;
    public String G;
    public Integer H;
    public String I;
    public Integer J;
    public String K;

    public ReqListenHelper() {
        this.I = "GESTURE_POINTS";
    }

    public static ReqListenHelper clone(ReqListenHelper reqListenHelper) {
        if (reqListenHelper == null) {
            return null;
        }
        ReqListenHelper reqListenHelper2 = new ReqListenHelper();
        reqListenHelper2.setListenType(reqListenHelper.getListenType());
        reqListenHelper2.setListenId(reqListenHelper.getListenId());
        reqListenHelper2.setSubscribeId(reqListenHelper.getSubscribeId());
        reqListenHelper2.setDelegateId(reqListenHelper.getDelegateId());
        reqListenHelper2.setBatchId(reqListenHelper.getBatchId());
        reqListenHelper2.setProp(reqListenHelper.getProp());
        reqListenHelper2.setScreenState(reqListenHelper.getScreenState());
        return reqListenHelper2;
    }

    public String getBatchId() {
        return this.E;
    }

    public String getDelegateId() {
        return this.F;
    }

    public String getListenId() {
        return this.G;
    }

    public Integer getListenType() {
        return this.H;
    }

    public String getProp() {
        return this.I;
    }

    public Integer getScreenState() {
        return this.J;
    }

    public String getSubscribeId() {
        return this.K;
    }

    public void setBatchId(String str) {
        this.E = str;
    }

    public void setDelegateId(String str) {
        this.F = str;
    }

    public void setListenId(String str) {
        this.G = str;
    }

    public void setListenType(Integer num) {
        this.H = num;
    }

    public void setProp(String str) {
        this.I = str;
    }

    public void setScreenState(Integer num) {
        this.J = num;
    }

    public void setSubscribeId(String str) {
        this.K = str;
    }

    public String toString() {
        return "ReqListenHelper{subscribeId='" + this.K + "', listenId='" + this.G + "', delegateId='" + this.F + "', prop='" + this.I + "', screenState=" + this.J + "', batchId=" + this.E + "', listenType=" + this.H + "'}";
    }

    public ReqListenHelper(Integer num, String str, Integer num2) {
        this.I = "GESTURE_POINTS";
        this.H = num;
        this.K = str;
        this.J = num2;
    }

    public ReqListenHelper(Integer num, String str, String str2, String str3, String str4, String str5, Integer num2) {
        this.H = num;
        this.E = str;
        this.G = str2;
        this.K = str3;
        this.F = str4;
        this.I = str5;
        this.J = num2;
    }

    public ReqListenHelper(String str, Integer num) {
        this.I = "GESTURE_POINTS";
        this.K = str;
        this.J = num;
    }
}
