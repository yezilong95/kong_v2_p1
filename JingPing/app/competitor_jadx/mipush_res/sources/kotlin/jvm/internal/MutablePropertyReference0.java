package kotlin.jvm.internal;

import defpackage.h60;
import defpackage.l60;
import defpackage.m60;
import defpackage.n60;
import defpackage.s60;
import defpackage.t60;
import defpackage.yn0;

/* JADX INFO: loaded from: classes.dex */
public abstract class MutablePropertyReference0 extends MutablePropertyReference implements n60 {
    public MutablePropertyReference0() {
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final h60 a() {
        yn0.a.getClass();
        return this;
    }

    public abstract /* synthetic */ Object get();

    @Override // defpackage.u60
    public Object getDelegate() {
        return ((n60) b()).getDelegate();
    }

    @Override // kotlin.jvm.internal.MutablePropertyReference, kotlin.jvm.internal.PropertyReference
    public /* bridge */ /* synthetic */ s60 getGetter() {
        getGetter();
        return null;
    }

    @Override // kotlin.jvm.internal.MutablePropertyReference
    public /* bridge */ /* synthetic */ l60 getSetter() {
        getSetter();
        return null;
    }

    @Override // defpackage.a10
    public Object invoke() {
        return get();
    }

    public abstract /* synthetic */ void set(Object obj);

    public MutablePropertyReference0(Object obj) {
        super(obj);
    }

    @Override // kotlin.jvm.internal.MutablePropertyReference, kotlin.jvm.internal.PropertyReference
    public t60 getGetter() {
        ((n60) b()).getGetter();
        return null;
    }

    @Override // kotlin.jvm.internal.MutablePropertyReference
    public m60 getSetter() {
        ((n60) b()).getSetter();
        return null;
    }

    public MutablePropertyReference0(Object obj, Class cls, String str, String str2, int i) {
        super(obj, cls, str, str2, i);
    }
}
