package defpackage;

import java.io.ObjectStreamException;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public final class vb implements Serializable {
    public static final vb E = new vb();

    private Object readResolve() throws ObjectStreamException {
        return E;
    }
}
