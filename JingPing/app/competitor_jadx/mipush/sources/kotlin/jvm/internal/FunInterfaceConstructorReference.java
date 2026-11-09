package kotlin.jvm.internal;

import defpackage.h60;
import defpackage.k60;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class FunInterfaceConstructorReference extends FunctionReference implements Serializable {
    public final Class M;

    public FunInterfaceConstructorReference(Class cls) {
        super(1);
        this.M = cls;
    }

    @Override // kotlin.jvm.internal.FunctionReference, kotlin.jvm.internal.CallableReference
    public final /* bridge */ /* synthetic */ h60 b() {
        b();
        throw null;
    }

    @Override // kotlin.jvm.internal.FunctionReference
    /* JADX INFO: renamed from: c */
    public final k60 b() {
        throw new UnsupportedOperationException("Functional interface constructor does not support reflection");
    }

    @Override // kotlin.jvm.internal.FunctionReference
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FunInterfaceConstructorReference) {
            return this.M.equals(((FunInterfaceConstructorReference) obj).M);
        }
        return false;
    }

    @Override // kotlin.jvm.internal.FunctionReference
    public int hashCode() {
        return this.M.hashCode();
    }

    @Override // kotlin.jvm.internal.FunctionReference
    public String toString() {
        return "fun interface ".concat(this.M.getName());
    }
}
