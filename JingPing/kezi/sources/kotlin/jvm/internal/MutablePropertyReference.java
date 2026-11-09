package kotlin.jvm.internal;

import defpackage.f00;
import defpackage.yz;

/* JADX INFO: loaded from: classes.dex */
public abstract class MutablePropertyReference extends PropertyReference {
    public MutablePropertyReference() {
    }

    @Override // kotlin.jvm.internal.PropertyReference
    public abstract /* synthetic */ f00 getGetter();

    public abstract /* synthetic */ yz getSetter();

    public MutablePropertyReference(Object obj) {
        super(obj);
    }

    public MutablePropertyReference(Object obj, Class cls, String str, String str2, int i) {
        super(obj, cls, str, str2, i);
    }
}
