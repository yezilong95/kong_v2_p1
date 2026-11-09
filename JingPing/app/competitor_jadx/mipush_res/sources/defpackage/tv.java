package defpackage;

import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class tv extends yq0 {
    public final HashMap I = new HashMap();

    @Override // defpackage.yq0
    public final vq0 a(Object obj) {
        return (vq0) this.I.get(obj);
    }

    @Override // defpackage.yq0
    public final Object b(Object obj) {
        Object objB = super.b(obj);
        this.I.remove(obj);
        return objB;
    }
}
