package kotlin.jvm.internal;

import defpackage.a00;
import defpackage.ce0;
import defpackage.f00;
import defpackage.g00;
import defpackage.sz;
import defpackage.yz;
import defpackage.zz;

/* JADX INFO: loaded from: classes.dex */
public abstract class MutablePropertyReference0 extends MutablePropertyReference implements a00 {
    public MutablePropertyReference0() {
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final sz a() {
        ce0.a.getClass();
        return this;
    }

    public abstract /* synthetic */ Object get();

    @Override // defpackage.h00
    public Object getDelegate() {
        return ((a00) b()).getDelegate();
    }

    @Override // kotlin.jvm.internal.MutablePropertyReference, kotlin.jvm.internal.PropertyReference
    public /* bridge */ /* synthetic */ f00 getGetter() {
        getGetter();
        return null;
    }

    @Override // kotlin.jvm.internal.MutablePropertyReference
    public /* bridge */ /* synthetic */ yz getSetter() {
        getSetter();
        return null;
    }

    @Override // defpackage.ou
    public Object invoke() {
        return get();
    }

    public abstract /* synthetic */ void set(Object obj);

    public MutablePropertyReference0(Object obj) {
        super(obj);
    }

    @Override // kotlin.jvm.internal.MutablePropertyReference, kotlin.jvm.internal.PropertyReference
    public g00 getGetter() {
        ((a00) b()).getGetter();
        return null;
    }

    @Override // kotlin.jvm.internal.MutablePropertyReference
    public zz getSetter() {
        ((a00) b()).getSetter();
        return null;
    }

    public MutablePropertyReference0(Object obj, Class cls, String str, String str2, int i) {
        super(obj, cls, str, str2, i);
    }
}
