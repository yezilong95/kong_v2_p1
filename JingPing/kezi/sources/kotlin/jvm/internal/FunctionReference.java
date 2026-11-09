package kotlin.jvm.internal;

import defpackage.ce0;
import defpackage.oy;
import defpackage.sz;
import defpackage.vz;
import defpackage.yu;

/* JADX INFO: loaded from: classes.dex */
public class FunctionReference extends CallableReference implements yu, vz {
    public final int h;
    public final int i;

    public FunctionReference(int i) {
        this(i, CallableReference.NO_RECEIVER, null, null, null, 0);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final sz a() {
        ce0.a.getClass();
        return this;
    }

    @Override // kotlin.jvm.internal.CallableReference
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public vz b() {
        return (vz) super.b();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof FunctionReference) {
            FunctionReference functionReference = (FunctionReference) obj;
            return getName().equals(functionReference.getName()) && getSignature().equals(functionReference.getSignature()) && this.i == functionReference.i && this.h == functionReference.h && oy.c(getBoundReceiver(), functionReference.getBoundReceiver()) && oy.c(getOwner(), functionReference.getOwner());
        }
        if (obj instanceof vz) {
            return obj.equals(compute());
        }
        return false;
    }

    @Override // defpackage.yu
    public int getArity() {
        return this.h;
    }

    public int hashCode() {
        return getSignature().hashCode() + ((getName().hashCode() + (getOwner() == null ? 0 : getOwner().hashCode() * 31)) * 31);
    }

    @Override // defpackage.vz
    public boolean isExternal() {
        return b().isExternal();
    }

    @Override // defpackage.vz
    public boolean isInfix() {
        return b().isInfix();
    }

    @Override // defpackage.vz
    public boolean isInline() {
        return b().isInline();
    }

    @Override // defpackage.vz
    public boolean isOperator() {
        return b().isOperator();
    }

    @Override // kotlin.jvm.internal.CallableReference, defpackage.sz
    public boolean isSuspend() {
        return b().isSuspend();
    }

    public String toString() {
        sz szVarCompute = compute();
        if (szVarCompute != this) {
            return szVarCompute.toString();
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
        this.h = i;
        this.i = i2 >> 1;
    }
}
