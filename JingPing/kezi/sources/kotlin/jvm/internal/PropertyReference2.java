package kotlin.jvm.internal;

import defpackage.ce0;
import defpackage.f00;
import defpackage.k00;
import defpackage.l00;
import defpackage.sz;

/* JADX INFO: loaded from: classes.dex */
public abstract class PropertyReference2 extends PropertyReference implements l00 {
    public PropertyReference2() {
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final sz a() {
        ce0.a.getClass();
        return this;
    }

    public abstract /* synthetic */ Object get(Object obj, Object obj2);

    @Override // defpackage.l00
    public Object getDelegate(Object obj, Object obj2) {
        return ((l00) b()).getDelegate(obj, obj2);
    }

    @Override // kotlin.jvm.internal.PropertyReference
    public /* bridge */ /* synthetic */ f00 getGetter() {
        getGetter();
        return null;
    }

    @Override // defpackage.ru
    public Object invoke(Object obj, Object obj2) {
        return get(obj, obj2);
    }

    public PropertyReference2(Class cls, String str, String str2, int i) {
        super(CallableReference.NO_RECEIVER, cls, str, str2, i);
    }

    @Override // kotlin.jvm.internal.PropertyReference
    public k00 getGetter() {
        ((l00) b()).getGetter();
        return null;
    }
}
