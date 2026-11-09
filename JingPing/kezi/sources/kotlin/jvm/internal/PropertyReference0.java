package kotlin.jvm.internal;

import defpackage.ce0;
import defpackage.f00;
import defpackage.g00;
import defpackage.h00;
import defpackage.sz;

/* JADX INFO: loaded from: classes.dex */
public abstract class PropertyReference0 extends PropertyReference implements h00 {
    public PropertyReference0() {
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final sz a() {
        ce0.a.getClass();
        return this;
    }

    public abstract /* synthetic */ Object get();

    @Override // defpackage.h00
    public Object getDelegate() {
        return ((h00) b()).getDelegate();
    }

    @Override // kotlin.jvm.internal.PropertyReference
    public /* bridge */ /* synthetic */ f00 getGetter() {
        getGetter();
        return null;
    }

    @Override // defpackage.ou
    public Object invoke() {
        return get();
    }

    public PropertyReference0(Object obj) {
        super(obj);
    }

    @Override // kotlin.jvm.internal.PropertyReference
    public g00 getGetter() {
        ((h00) b()).getGetter();
        return null;
    }

    public PropertyReference0(Object obj, Class cls, String str, String str2, int i) {
        super(obj, cls, str, str2, i);
    }
}
