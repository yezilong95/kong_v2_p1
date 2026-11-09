package kotlin.jvm.internal;

import defpackage.h60;
import defpackage.k10;
import defpackage.k60;
import defpackage.t40;
import defpackage.yn0;

/* JADX INFO: loaded from: classes.dex */
public class FunctionReference extends CallableReference implements k10, k60 {
    public final int K;
    public final int L;

    public FunctionReference(int i) {
        this(i, CallableReference.NO_RECEIVER, null, null, null, 0);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final h60 a() {
        yn0.a.getClass();
        return this;
    }

    @Override // kotlin.jvm.internal.CallableReference
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public k60 b() {
        return (k60) super.b();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof FunctionReference) {
            FunctionReference functionReference = (FunctionReference) obj;
            return getName().equals(functionReference.getName()) && getSignature().equals(functionReference.getSignature()) && this.L == functionReference.L && this.K == functionReference.K && t40.f(getBoundReceiver(), functionReference.getBoundReceiver()) && t40.f(getOwner(), functionReference.getOwner());
        }
        if (obj instanceof k60) {
            return obj.equals(compute());
        }
        return false;
    }

    @Override // defpackage.k10
    public int getArity() {
        return this.K;
    }

    public int hashCode() {
        return getSignature().hashCode() + ((getName().hashCode() + (getOwner() == null ? 0 : getOwner().hashCode() * 31)) * 31);
    }

    @Override // defpackage.k60
    public boolean isExternal() {
        return b().isExternal();
    }

    @Override // defpackage.k60
    public boolean isInfix() {
        return b().isInfix();
    }

    @Override // defpackage.k60
    public boolean isInline() {
        return b().isInline();
    }

    @Override // defpackage.k60
    public boolean isOperator() {
        return b().isOperator();
    }

    @Override // kotlin.jvm.internal.CallableReference, defpackage.h60
    public boolean isSuspend() {
        return b().isSuspend();
    }

    public String toString() {
        h60 h60VarCompute = compute();
        if (h60VarCompute != this) {
            return h60VarCompute.toString();
        }
        if ("<init>".equals(getName())) {
            return "constructor (Kotlin reflection is not available)";
        }
        return "function " + getName() + " (Kotlin reflection is not available)";
    }

    public FunctionReference(int i, Object obj) {
        this(i, obj, null, null, null, 0);
    }

    public FunctionReference(int i, Object obj, Class cls, String str, String str2, int i2) {
        super(obj, cls, str, str2, (i2 & 1) == 1);
        this.K = i;
        this.L = i2 >> 1;
    }
}
