package kotlin.jvm.internal;

import defpackage.cf;
import defpackage.i60;
import defpackage.j60;

/* JADX INFO: loaded from: classes.dex */
public class MutablePropertyReference2Impl extends MutablePropertyReference2 {
    public MutablePropertyReference2Impl(j60 j60Var, String str, String str2) {
        super(((cf) j60Var).a(), str, str2, !(j60Var instanceof i60) ? 1 : 0);
    }

    @Override // kotlin.jvm.internal.MutablePropertyReference2
    public Object get(Object obj, Object obj2) {
        getGetter();
        throw null;
    }

    @Override // kotlin.jvm.internal.MutablePropertyReference2
    public void set(Object obj, Object obj2, Object obj3) {
        getSetter();
        throw null;
    }

    public MutablePropertyReference2Impl(Class cls, String str, String str2, int i) {
        super(cls, str, str2, i);
    }
}
