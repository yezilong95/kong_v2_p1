package kotlinx.serialization;

import defpackage.hk;

/* JADX INFO: loaded from: classes.dex */
public final class UnknownFieldException extends SerializationException {
    public UnknownFieldException(String str) {
        super(str);
    }

    public UnknownFieldException(int i) {
        this(hk.g(i, "An unknown field for index "));
    }
}
