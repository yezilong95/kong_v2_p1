package com.mamimobi.videostream.model;

/* JADX INFO: loaded from: classes.dex */
public class User {
    private DeviceType deviceType;
    private String userId;
    private String userName;

    public User() {
    }

    public DeviceType getDeviceType() {
        return this.deviceType;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public void setUserName(String str) {
        this.userName = str;
    }

    public User(String str, String str2) {
        this.userId = str;
        this.userName = str2;
    }
}
