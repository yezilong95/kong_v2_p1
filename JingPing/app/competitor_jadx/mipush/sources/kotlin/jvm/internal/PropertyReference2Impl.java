package kotlin.jvm.internal;

import defpackage.cf;
import defpackage.i60;
import defpackage.j60;

/* JADX INFO: loaded from: classes.dex */
public class PropertyReference2Impl extends PropertyReference2 {
    public PropertyReference2Impl(j60 j60Var, String str, String str2) {
        super(((cf) j60Var).a(), str, str2, !(j60Var instanceof i60) ? 1 : 0);
    }

    @Override // kotlin.jvm.internal.PropertyReference2
    public Object get(Object obj, Object obj2) {
        getGetter();
        throw null;
    }

    public PropertyReference2Impl(Class cls, String str, String str2, int i) {
        super(cls, str, str2, i);
    }
}
