package kotlin.jvm.internal;

import defpackage.sz;
import defpackage.vz;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class FunInterfaceConstructorReference extends FunctionReference implements Serializable {
    public final Class j;

    public FunInterfaceConstructorReference(Class cls) {
        super(1);
        this.j = cls;
    }

    @Override // kotlin.jvm.internal.FunctionReference, kotlin.jvm.internal.CallableReference
    public final /* bridge */ /* synthetic */ sz b() {
        b();
        throw null;
    }

    @Override // kotlin.jvm.internal.FunctionReference
    /* JADX INFO: renamed from: c */
    public final vz b() {
        throw new UnsupportedOperationException("Functional interface constructor does not support reflection");
    }

    @Override // kotlin.jvm.internal.FunctionReference
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FunInterfaceConstructorReference) {
            return this.j.equals(((FunInterfaceConstructorReference) obj).j);
        }
        return false;
    }

    @Override // kotlin.jvm.internal.FunctionReference
    public int hashCode() {
        return this.j.hashCode();
    }

    @Override // kotlin.jvm.internal.FunctionReference
    public String toString() {
        return "fun interface ".concat(this.j.getName());
    }
}
