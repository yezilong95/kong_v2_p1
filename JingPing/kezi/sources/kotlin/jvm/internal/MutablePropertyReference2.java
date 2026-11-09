package kotlin.jvm.internal;

import defpackage.ce0;
import defpackage.d00;
import defpackage.e00;
import defpackage.f00;
import defpackage.k00;
import defpackage.sz;
import defpackage.yz;

/* JADX INFO: loaded from: classes.dex */
public abstract class MutablePropertyReference2 extends MutablePropertyReference implements e00 {
    public MutablePropertyReference2() {
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final sz a() {
        ce0.a.getClass();
        return this;
    }

    public abstract /* synthetic */ Object get(Object obj, Object obj2);

    @Override // defpackage.l00
    public Object getDelegate(Object obj, Object obj2) {
        return ((e00) b()).getDelegate(obj, obj2);
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

    @Override // defpackage.ru
    public Object invoke(Object obj, Object obj2) {
        return get(obj, obj2);
    }

    public abstract /* synthetic */ void set(Object obj, Object obj2, Object obj3);

    public MutablePropertyReference2(Class cls, String str, String str2, int i) {
        super(CallableReference.NO_RECEIVER, cls, str, str2, i);
    }

    @Override // kotlin.jvm.internal.MutablePropertyReference, kotlin.jvm.internal.PropertyReference
    public k00 getGetter() {
        ((e00) b()).getGetter();
        return null;
    }

    @Override // kotlin.jvm.internal.MutablePropertyReference
    public d00 getSetter() {
        ((e00) b()).getSetter();
        return null;
    }
}
