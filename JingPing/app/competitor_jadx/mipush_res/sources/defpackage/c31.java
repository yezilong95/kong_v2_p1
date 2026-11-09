package defpackage;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class c31 extends ma0 {
    public final /* synthetic */ Method q;
    public final /* synthetic */ Object r;

    public c31(Method method, Object obj) {
        this.q = method;
        this.r = obj;
    }

    @Override // defpackage.ma0
    public final Object b0(Class cls) {
        ma0.f(cls);
        return this.q.invoke(this.r, cls);
    }
}
