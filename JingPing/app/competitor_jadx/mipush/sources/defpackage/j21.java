package defpackage;

import com.google.gson.stream.JsonToken;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class j21 {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[JsonToken.values().length];
        a = iArr;
        try {
            iArr[JsonToken.NUMBER.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            a[JsonToken.BOOLEAN.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            a[JsonToken.STRING.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            a[JsonToken.NULL.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            a[JsonToken.BEGIN_ARRAY.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            a[JsonToken.BEGIN_OBJECT.ordinal()] = 6;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            a[JsonToken.END_DOCUMENT.ordinal()] = 7;
        } catch (NoSuchFieldError unused7) {
        }
        try {
            a[JsonToken.NAME.ordinal()] = 8;
        } catch (NoSuchFieldError unused8) {
        }
        try {
            a[JsonToken.END_OBJECT.ordinal()] = 9;
        } catch (NoSuchFieldError unused9) {
        }
        try {
            a[JsonToken.END_ARRAY.ordinal()] = 10;
        } catch (NoSuchFieldError unused10) {
        }
    }
}
