package com.arialyy.aria.core.inf;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class ReceiverType {
    public static final ReceiverType DOWNLOAD;
    public static final /* synthetic */ ReceiverType[] E;
    public static final ReceiverType UPLOAD;

    static {
        ReceiverType receiverType = new ReceiverType("DOWNLOAD", 0);
        DOWNLOAD = receiverType;
        ReceiverType receiverType2 = new ReceiverType("UPLOAD", 1);
        UPLOAD = receiverType2;
        E = new ReceiverType[]{receiverType, receiverType2};
    }

    public static ReceiverType valueOf(String str) {
        return (ReceiverType) Enum.valueOf(ReceiverType.class, str);
    }

    public static ReceiverType[] values() {
        return (ReceiverType[]) E.clone();
    }
}
