package kotlin.jvm.internal;

import defpackage.b00;
import defpackage.c00;
import defpackage.ce0;
import defpackage.f00;
import defpackage.i00;
import defpackage.sz;
import defpackage.yz;

/* JADX INFO: loaded from: classes.dex */
public abstract class MutablePropertyReference1 extends MutablePropertyReference implements c00 {
    public MutablePropertyReference1() {
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final sz a() {
        ce0.a.getClass();
        return this;
    }

    public abstract /* synthetic */ Object get(Object obj);

    @Override // defpackage.j00
    public Object getDelegate(Object obj) {
        return ((c00) b()).getDelegate(obj);
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

    @Override // defpackage.pu
    public Object invoke(Object obj) {
        return get(obj);
    }

    public abstract /* synthetic */ void set(Object obj, Object obj2);

    public MutablePropertyReference1(Object obj) {
        super(obj);
    }

    @Override // kotlin.jvm.internal.MutablePropertyReference, kotlin.jvm.internal.PropertyReference
    public i00 getGetter() {
        ((c00) b()).getGetter();
        return null;
    }

    @Override // kotlin.jvm.internal.MutablePropertyReference
    public b00 getSetter() {
        ((c00) b()).getSetter();
        return null;
    }

    public MutablePropertyReference1(Object obj, Class cls, String str, String str2, int i) {
        super(obj, cls, str, str2, i);
    }
}
