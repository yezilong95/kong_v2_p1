package com.mamimobi.videostream;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class VideoSourceType {
    public static final VideoSourceType CAMERA;
    public static final VideoSourceType CAMERA_BACK;
    public static final VideoSourceType CAMERA_FRONT;
    public static final /* synthetic */ VideoSourceType[] E;
    public static final VideoSourceType SCREEN;
    public static final VideoSourceType SCREENHELPER;

    static {
        VideoSourceType videoSourceType = new VideoSourceType("SCREEN", 0);
        SCREEN = videoSourceType;
        VideoSourceType videoSourceType2 = new VideoSourceType("CAMERA_FRONT", 1);
        CAMERA_FRONT = videoSourceType2;
        VideoSourceType videoSourceType3 = new VideoSourceType("CAMERA_BACK", 2);
        CAMERA_BACK = videoSourceType3;
        VideoSourceType videoSourceType4 = new VideoSourceType("SCREENHELPER", 3);
        SCREENHELPER = videoSourceType4;
        VideoSourceType videoSourceType5 = new VideoSourceType("CAMERA", 4);
        CAMERA = videoSourceType5;
        E = new VideoSourceType[]{videoSourceType, videoSourceType2, videoSourceType3, videoSourceType4, videoSourceType5};
    }

    public static VideoSourceType valueOf(String str) {
        return (VideoSourceType) Enum.valueOf(VideoSourceType.class, str);
    }

    public static VideoSourceType[] values() {
        return (VideoSourceType[]) E.clone();
    }
}
