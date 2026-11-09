package kotlin.jvm.internal;

import defpackage.h60;
import defpackage.s60;
import defpackage.x60;
import defpackage.y60;
import defpackage.yn0;

/* JADX INFO: loaded from: classes.dex */
public abstract class PropertyReference2 extends PropertyReference implements y60 {
    public PropertyReference2() {
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final h60 a() {
        yn0.a.getClass();
        return this;
    }

    public abstract /* synthetic */ Object get(Object obj, Object obj2);

    @Override // defpackage.y60
    public Object getDelegate(Object obj, Object obj2) {
        return ((y60) b()).getDelegate(obj, obj2);
    }

    @Override // kotlin.jvm.internal.PropertyReference
    public /* bridge */ /* synthetic */ s60 getGetter() {
        getGetter();
        return null;
    }

    @Override // defpackage.d10
    public Object invoke(Object obj, Object obj2) {
        return get(obj, obj2);
    }

    public PropertyReference2(Class cls, String str, String str2, int i) {
        super(CallableReference.NO_RECEIVER, cls, str, str2, i);
    }

    @Override // kotlin.jvm.internal.PropertyReference
    public x60 getGetter() {
        ((y60) b()).getGetter();
        return null;
    }
}
