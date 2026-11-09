package com.mamimobi.videostream.model;

/* JADX INFO: loaded from: classes.dex */
public enum DeviceType {
    COMPUTER(0, "COMPUTER"),
    PHONE(1, "PHONE"),
    DEFAULT(-1, "DEFAULT");

    private int code;
    private String type;

    DeviceType(int i, String str) {
        this.code = i;
        this.type = str;
    }

    public static DeviceType getDeviceType(int i) {
        for (DeviceType deviceType : values()) {
            if (deviceType.getCode() == i) {
                return deviceType;
            }
        }
        return DEFAULT;
    }

    public int getCode() {
        return this.code;
    }

    public String getType() {
        return this.type;
    }
}
