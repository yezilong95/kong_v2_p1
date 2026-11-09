package defpackage;

import java.io.ObjectStreamException;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public final class fa implements Serializable {
    public static final fa b = new fa();

    private Object readResolve() throws ObjectStreamException {
        return b;
    }
}
