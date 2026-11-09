package kotlin.jvm.internal;

import defpackage.h60;
import defpackage.l60;
import defpackage.o60;
import defpackage.p60;
import defpackage.s60;
import defpackage.v60;
import defpackage.yn0;

/* JADX INFO: loaded from: classes.dex */
public abstract class MutablePropertyReference1 extends MutablePropertyReference implements p60 {
    public MutablePropertyReference1() {
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final h60 a() {
        yn0.a.getClass();
        return this;
    }

    public abstract /* synthetic */ Object get(Object obj);

    @Override // defpackage.w60
    public Object getDelegate(Object obj) {
        return ((p60) b()).getDelegate(obj);
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

    @Override // defpackage.b10
    public Object invoke(Object obj) {
        return get(obj);
    }

    public abstract /* synthetic */ void set(Object obj, Object obj2);

    public MutablePropertyReference1(Object obj) {
        super(obj);
    }

    @Override // kotlin.jvm.internal.MutablePropertyReference, kotlin.jvm.internal.PropertyReference
    public v60 getGetter() {
        ((p60) b()).getGetter();
        return null;
    }

    @Override // kotlin.jvm.internal.MutablePropertyReference
    public o60 getSetter() {
        ((p60) b()).getSetter();
        return null;
    }

    public MutablePropertyReference1(Object obj, Class cls, String str, String str2, int i) {
        super(obj, cls, str, str2, i);
    }
}
