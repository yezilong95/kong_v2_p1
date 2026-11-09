package kotlin.jvm.internal;

import defpackage.ce0;
import defpackage.f00;
import defpackage.i00;
import defpackage.j00;
import defpackage.sz;

/* JADX INFO: loaded from: classes.dex */
public abstract class PropertyReference1 extends PropertyReference implements j00 {
    public PropertyReference1() {
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final sz a() {
        ce0.a.getClass();
        return this;
    }

    public abstract /* synthetic */ Object get(Object obj);

    @Override // defpackage.j00
    public Object getDelegate(Object obj) {
        return ((j00) b()).getDelegate(obj);
    }

    @Override // kotlin.jvm.internal.PropertyReference
    public /* bridge */ /* synthetic */ f00 getGetter() {
        getGetter();
        return null;
    }

    @Override // defpackage.pu
    public Object invoke(Object obj) {
        return get(obj);
    }

    public PropertyReference1(Object obj) {
        super(obj);
    }

    @Override // kotlin.jvm.internal.PropertyReference
    public i00 getGetter() {
        ((j00) b()).getGetter();
        return null;
    }

    public PropertyReference1(Object obj, Class cls, String str, String str2, int i) {
        super(obj, cls, str, str2, i);
    }
}
