package kotlin.jvm.internal;

import defpackage.id;
import defpackage.tz;
import defpackage.uz;

/* JADX INFO: loaded from: classes.dex */
public class PropertyReference0Impl extends PropertyReference0 {
    public PropertyReference0Impl(uz uzVar, String str, String str2) {
        super(CallableReference.NO_RECEIVER, ((id) uzVar).a(), str, str2, !(uzVar instanceof tz) ? 1 : 0);
    }

    @Override // kotlin.jvm.internal.PropertyReference0
    public Object get() {
        getGetter();
        throw null;
    }

    public PropertyReference0Impl(Class cls, String str, String str2, int i) {
        super(CallableReference.NO_RECEIVER, cls, str, str2, i);
    }

    public PropertyReference0Impl(Object obj, Class cls, String str, String str2, int i) {
        super(obj, cls, str, str2, i);
    }
}
