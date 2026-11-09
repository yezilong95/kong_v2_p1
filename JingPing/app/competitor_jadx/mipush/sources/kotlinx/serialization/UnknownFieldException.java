package kotlinx.serialization;

import defpackage.zn;

/* JADX INFO: loaded from: classes.dex */
public final class UnknownFieldException extends SerializationException {
    public UnknownFieldException(String str) {
        super(str);
    }

    public UnknownFieldException(int i) {
        this(zn.j(i, "An unknown field for index "));
    }
}
