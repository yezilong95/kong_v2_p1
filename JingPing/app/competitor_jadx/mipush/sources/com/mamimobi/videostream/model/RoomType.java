package com.mamimobi.videostream.model;

/* JADX INFO: loaded from: classes.dex */
public enum RoomType {
    MEETING(0, "MEETING"),
    SINGLE(1, "SINGLE"),
    SINGLE_AUDIO(2, "SINGLE_AUDIO"),
    LIVE(3, "LIVE"),
    SCREEN(4, "SCREEN"),
    CAMERA1(5, "Camera1"),
    CAMERA2(6, "Camera2"),
    DEFAULT(-1, "DEFAULT");

    private int code;
    private String type;

    RoomType(int i, String str) {
        this.code = i;
        this.type = str;
    }

    public static RoomType getRooType(int i) {
        for (RoomType roomType : values()) {
            if (roomType.getCode() == i) {
                return roomType;
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
