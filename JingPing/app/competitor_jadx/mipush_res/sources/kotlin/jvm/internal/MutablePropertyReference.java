package kotlin.jvm.internal;

import defpackage.l60;
import defpackage.s60;

/* JADX INFO: loaded from: classes.dex */
public abstract class MutablePropertyReference extends PropertyReference {
    public MutablePropertyReference() {
    }

    @Override // kotlin.jvm.internal.PropertyReference
    public abstract /* synthetic */ s60 getGetter();

    public abstract /* synthetic */ l60 getSetter();

    public MutablePropertyReference(Object obj) {
        super(obj);
    }

    public MutablePropertyReference(Object obj, Class cls, String str, String str2, int i) {
        super(obj, cls, str, str2, i);
    }
}
