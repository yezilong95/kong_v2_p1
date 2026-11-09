package kotlin.jvm.internal;

import defpackage.id;
import defpackage.tz;
import defpackage.uz;

/* JADX INFO: loaded from: classes.dex */
public class MutablePropertyReference0Impl extends MutablePropertyReference0 {
    public MutablePropertyReference0Impl(uz uzVar, String str, String str2) {
        super(CallableReference.NO_RECEIVER, ((id) uzVar).a(), str, str2, !(uzVar instanceof tz) ? 1 : 0);
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
