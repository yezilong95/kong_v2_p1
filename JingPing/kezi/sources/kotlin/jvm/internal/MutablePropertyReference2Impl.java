package kotlin.jvm.internal;

import defpackage.id;
import defpackage.tz;
import defpackage.uz;

/* JADX INFO: loaded from: classes.dex */
public class MutablePropertyReference2Impl extends MutablePropertyReference2 {
    public MutablePropertyReference2Impl(uz uzVar, String str, String str2) {
        super(((id) uzVar).a(), str, str2, !(uzVar instanceof tz) ? 1 : 0);
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
