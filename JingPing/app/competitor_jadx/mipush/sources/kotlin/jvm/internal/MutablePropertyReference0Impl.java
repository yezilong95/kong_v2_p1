package kotlin.jvm.internal;

import defpackage.cf;
import defpackage.i60;
import defpackage.j60;

/* JADX INFO: loaded from: classes.dex */
public class MutablePropertyReference0Impl extends MutablePropertyReference0 {
    public MutablePropertyReference0Impl(j60 j60Var, String str, String str2) {
        super(CallableReference.NO_RECEIVER, ((cf) j60Var).a(), str, str2, !(j60Var instanceof i60) ? 1 : 0);
    }

    @Override // kotlin.jvm.internal.MutablePropertyReference0
    public Object get() {
        getGetter();
        throw null;
    }

    @Override // kotlin.jvm.internal.MutablePropertyReference0
    public void set(Object obj) {
        getSetter();
        throw null;
    }

    public MutablePropertyReference0Impl(Class cls, String str, String str2, int i) {
        super(CallableReference.NO_RECEIVER, cls, str, str2, i);
    }

    public MutablePropertyReference0Impl(Object obj, Class cls, String str, String str2, int i) {
        super(obj, cls, str, str2, i);
    }
}
