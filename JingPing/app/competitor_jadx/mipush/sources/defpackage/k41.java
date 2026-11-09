package defpackage;

import com.mamimobi.videostream.VideoSourceType;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class k41 {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[VideoSourceType.values().length];
        a = iArr;
        try {
            iArr[VideoSourceType.SCREEN.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            a[VideoSourceType.CAMERA_FRONT.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            a[VideoSourceType.CAMERA_BACK.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            a[VideoSourceType.SCREENHELPER.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
    }
}
