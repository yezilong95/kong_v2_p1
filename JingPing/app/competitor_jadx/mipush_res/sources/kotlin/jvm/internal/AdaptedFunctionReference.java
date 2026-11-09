package kotlin.jvm.internal;

import defpackage.ao0;
import defpackage.j60;
import defpackage.k10;
import defpackage.t40;
import defpackage.wj0;
import defpackage.yn0;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class AdaptedFunctionReference implements k10, Serializable {
    public final Object E;
    public final Class F;
    public final String G;
    public final String H;
    public final boolean I;
    public final int J;
    public final int K;

    public AdaptedFunctionReference(int i, Class cls, String str, String str2, int i2) {
        this(i, CallableReference.NO_RECEIVER, cls, str, str2, i2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AdaptedFunctionReference)) {
            return false;
        }
        AdaptedFunctionReference adaptedFunctionReference = (AdaptedFunctionReference) obj;
        return this.I == adaptedFunctionReference.I && this.J == adaptedFunctionReference.J && this.K == adaptedFunctionReference.K && t40.f(this.E, adaptedFunctionReference.E) && t40.f(this.F, adaptedFunctionReference.F) && this.G.equals(adaptedFunctionReference.G) && this.H.equals(adaptedFunctionReference.H);
    }

    @Override // defpackage.k10
    public int getArity() {
        return this.J;
    }

    public j60 getOwner() {
        Class cls = this.F;
        if (cls == null) {
            return null;
        }
        if (!this.I) {
            return yn0.a(cls);
        }
        yn0.a.getClass();
        return new wj0(cls);
    }

    public int hashCode() {
        Object obj = this.E;
        int iHashCode = (obj != null ? obj.hashCode() : 0) * 31;
        Class cls = this.F;
        return ((((((this.H.hashCode() + ((this.G.hashCode() + ((iHashCode + (cls != null ? cls.hashCode() : 0)) * 31)) * 31)) * 31) + (this.I ? 1231 : 1237)) * 31) + this.J) * 31) + this.K;
    }

    public String toString() {
        yn0.a.getClass();
        return ao0.a(this);
    }

    public AdaptedFunctionReference(int i, Object obj, Class cls, String str, String str2, int i2) {
        this.E = obj;
        this.F = cls;
        this.G = str;
        this.H = str2;
        this.I = (i2 & 1) == 1;
        this.J = i;
        this.K = i2 >> 1;
    }
}
