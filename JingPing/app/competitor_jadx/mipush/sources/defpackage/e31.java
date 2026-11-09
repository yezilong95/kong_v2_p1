package defpackage;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class e31 extends ma0 {
    public final /* synthetic */ Method q;

    public e31(Method method) {
        this.q = method;
    }

    @Override // defpackage.ma0
    public final Object b0(Class cls) {
        ma0.f(cls);
        return this.q.invoke(null, cls, Object.class);
    }
}
