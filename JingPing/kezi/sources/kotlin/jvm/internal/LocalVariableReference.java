package kotlin.jvm.internal;

import defpackage.uz;

/* JADX INFO: loaded from: classes.dex */
public class LocalVariableReference extends PropertyReference0 {
    @Override // kotlin.jvm.internal.PropertyReference0
    public Object get() {
        throw new UnsupportedOperationException("Not supported for local property reference.");
    }

    @Override // kotlin.jvm.internal.CallableReference
    public uz getOwner() {
        throw new UnsupportedOperationException("Not supported for local property reference.");
    }
}
