package kotlin.jvm.internal;

import defpackage.h60;
import defpackage.s60;
import defpackage.t60;
import defpackage.u60;
import defpackage.yn0;

/* JADX INFO: loaded from: classes.dex */
public abstract class PropertyReference0 extends PropertyReference implements u60 {
    public PropertyReference0() {
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final h60 a() {
        yn0.a.getClass();
        return this;
    }

    public abstract /* synthetic */ Object get();

    @Override // defpackage.u60
    public Object getDelegate() {
        return ((u60) b()).getDelegate();
    }

    @Override // kotlin.jvm.internal.PropertyReference
    public /* bridge */ /* synthetic */ s60 getGetter() {
        getGetter();
        return null;
    }

    @Override // defpackage.a10
    public Object invoke() {
        return get();
    }

    public PropertyReference0(Object obj) {
        super(obj);
    }

    @Override // kotlin.jvm.internal.PropertyReference
    public t60 getGetter() {
        ((u60) b()).getGetter();
        return null;
    }

    public PropertyReference0(Object obj, Class cls, String str, String str2, int i) {
        super(obj, cls, str, str2, i);
    }
}
