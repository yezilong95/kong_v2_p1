package kotlin.jvm.internal;

import defpackage.h60;
import defpackage.l60;
import defpackage.q60;
import defpackage.r60;
import defpackage.s60;
import defpackage.x60;
import defpackage.yn0;

/* JADX INFO: loaded from: classes.dex */
public abstract class MutablePropertyReference2 extends MutablePropertyReference implements r60 {
    public MutablePropertyReference2() {
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final h60 a() {
        yn0.a.getClass();
        return this;
    }

    public abstract /* synthetic */ Object get(Object obj, Object obj2);

    @Override // defpackage.y60
    public Object getDelegate(Object obj, Object obj2) {
        return ((r60) b()).getDelegate(obj, obj2);
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

    @Override // defpackage.d10
    public Object invoke(Object obj, Object obj2) {
        return get(obj, obj2);
    }

    public abstract /* synthetic */ void set(Object obj, Object obj2, Object obj3);

    public MutablePropertyReference2(Class cls, String str, String str2, int i) {
        super(CallableReference.NO_RECEIVER, cls, str, str2, i);
    }

    @Override // kotlin.jvm.internal.MutablePropertyReference, kotlin.jvm.internal.PropertyReference
    public x60 getGetter() {
        ((r60) b()).getGetter();
        return null;
    }

    @Override // kotlin.jvm.internal.MutablePropertyReference
    public q60 getSetter() {
        ((r60) b()).getSetter();
        return null;
    }
}
