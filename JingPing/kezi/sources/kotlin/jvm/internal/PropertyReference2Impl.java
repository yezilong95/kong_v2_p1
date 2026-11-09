package kotlin.jvm.internal;

import defpackage.id;
import defpackage.tz;
import defpackage.uz;

/* JADX INFO: loaded from: classes.dex */
public class PropertyReference2Impl extends PropertyReference2 {
    public PropertyReference2Impl(uz uzVar, String str, String str2) {
        super(((id) uzVar).a(), str, str2, !(uzVar instanceof tz) ? 1 : 0);
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
