package defpackage;

import java.io.ObjectStreamException;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public abstract class i80 implements n30, Serializable {
    private static final long serialVersionUID = 7535258609338176893L;

    @Override // defpackage.n30
    public abstract String getName();

    public Object readResolve() throws ObjectStreamException {
        return p30.c(getName());
    }
}
