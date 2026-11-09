package defpackage;

import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class np extends lg0 {
    public final HashMap f = new HashMap();

    @Override // defpackage.lg0
    public final ig0 a(Object obj) {
        return (ig0) this.f.get(obj);
    }

    @Override // defpackage.lg0
    public final Object b(Object obj) {
        Object objB = super.b(obj);
        this.f.remove(obj);
        return objB;
    }
}
