package kotlin.jvm.internal;

import defpackage.uz;

/* JADX INFO: loaded from: classes.dex */
public class MutableLocalVariableReference extends MutablePropertyReference0 {
    @Override // kotlin.jvm.internal.MutablePropertyReference0
    public Object get() {
        throw new UnsupportedOperationException("Not supported for local property reference.");
    }

    @Override // kotlin.jvm.internal.CallableReference
    public uz getOwner() {
        throw new UnsupportedOperationException("Not supported for local property reference.");
    }

    @Override // kotlin.jvm.internal.MutablePropertyReference0
    public void set(Object obj) {
        throw new UnsupportedOperationException("Not supported for local property reference.");
    }
}
