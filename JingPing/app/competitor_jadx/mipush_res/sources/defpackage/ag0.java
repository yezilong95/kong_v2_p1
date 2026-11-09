package defpackage;

import java.io.ObjectStreamException;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public abstract class ag0 implements ua0, Serializable {
    private static final long serialVersionUID = 7535258609338176893L;

    @Override // defpackage.ua0
    public abstract String getName();

    public Object readResolve() throws ObjectStreamException {
        return wa0.c(getName());
    }
}
