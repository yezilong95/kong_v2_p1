package defpackage;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class d31 extends ma0 {
    public final /* synthetic */ Method q;
    public final /* synthetic */ int r;

    public d31(int i, Method method) {
        this.q = method;
        this.r = i;
    }

    @Override // defpackage.ma0
    public final Object b0(Class cls) {
        ma0.f(cls);
        return this.q.invoke(null, cls, Integer.valueOf(this.r));
    }
}
