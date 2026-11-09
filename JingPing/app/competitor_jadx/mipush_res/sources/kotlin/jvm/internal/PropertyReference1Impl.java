package kotlin.jvm.internal;

import defpackage.cf;
import defpackage.i60;
import defpackage.j60;

/* JADX INFO: loaded from: classes.dex */
public class PropertyReference1Impl extends PropertyReference1 {
    public PropertyReference1Impl(j60 j60Var, String str, String str2) {
        super(CallableReference.NO_RECEIVER, ((cf) j60Var).a(), str, str2, !(j60Var instanceof i60) ? 1 : 0);
    }

    @Override // kotlin.jvm.internal.PropertyReference1
    public Object get(Object obj) {
        getGetter();
        throw null;
    }

    public PropertyReference1Impl(Class cls, String str, String str2, int i) {
        super(CallableReference.NO_RECEIVER, cls, str, str2, i);
    }

    public PropertyReference1Impl(Object obj, Class cls, String str, String str2, int i) {
        super(obj, cls, str, str2, i);
    }
}
