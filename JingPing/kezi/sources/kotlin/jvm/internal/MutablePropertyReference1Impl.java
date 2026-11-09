package kotlin.jvm.internal;

import defpackage.id;
import defpackage.tz;
import defpackage.uz;

/* JADX INFO: loaded from: classes.dex */
public class MutablePropertyReference1Impl extends MutablePropertyReference1 {
    public MutablePropertyReference1Impl(uz uzVar, String str, String str2) {
        super(CallableReference.NO_RECEIVER, ((id) uzVar).a(), str, str2, !(uzVar instanceof tz) ? 1 : 0);
    }

    @Override // kotlin.jvm.internal.MutablePropertyReference1
    public Object get(Object obj) {
        getGetter();
        throw null;
    }

    @Override // kotlin.jvm.internal.MutablePropertyReference1
    public void set(Object obj, Object obj2) {
        getSetter();
        throw null;
    }

    public MutablePropertyReference1Impl(Class cls, String str, String str2, int i) {
        super(CallableReference.NO_RECEIVER, cls, str, str2, i);
    }

    public MutablePropertyReference1Impl(Object obj, Class cls, String str, String str2, int i) {
        super(obj, cls, str, str2, i);
    }
}
