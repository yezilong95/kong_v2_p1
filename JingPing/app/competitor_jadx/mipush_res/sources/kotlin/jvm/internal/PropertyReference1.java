package kotlin.jvm.internal;

import defpackage.h60;
import defpackage.s60;
import defpackage.v60;
import defpackage.w60;
import defpackage.yn0;

/* JADX INFO: loaded from: classes.dex */
public abstract class PropertyReference1 extends PropertyReference implements w60 {
    public PropertyReference1() {
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final h60 a() {
        yn0.a.getClass();
        return this;
    }

    public abstract /* synthetic */ Object get(Object obj);

    @Override // defpackage.w60
    public Object getDelegate(Object obj) {
        return ((w60) b()).getDelegate(obj);
    }

    @Override // kotlin.jvm.internal.PropertyReference
    public /* bridge */ /* synthetic */ s60 getGetter() {
        getGetter();
        return null;
    }

    @Override // defpackage.b10
    public Object invoke(Object obj) {
        return get(obj);
    }

    public PropertyReference1(Object obj) {
        super(obj);
    }

    @Override // kotlin.jvm.internal.PropertyReference
    public v60 getGetter() {
        ((w60) b()).getGetter();
        return null;
    }

    public PropertyReference1(Object obj, Class cls, String str, String str2, int i) {
        super(obj, cls, str, str2, i);
    }
}
