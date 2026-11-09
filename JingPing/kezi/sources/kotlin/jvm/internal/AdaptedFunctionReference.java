package kotlin.jvm.internal;

import defpackage.cb0;
import defpackage.ce0;
import defpackage.de0;
import defpackage.oy;
import defpackage.uz;
import defpackage.yu;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class AdaptedFunctionReference implements yu, Serializable {
    public final Object b;
    public final Class c;
    public final String d;
    public final String e;
    public final boolean f;
    public final int g;
    public final int h;

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
        return this.f == adaptedFunctionReference.f && this.g == adaptedFunctionReference.g && this.h == adaptedFunctionReference.h && oy.c(this.b, adaptedFunctionReference.b) && oy.c(this.c, adaptedFunctionReference.c) && this.d.equals(adaptedFunctionReference.d) && this.e.equals(adaptedFunctionReference.e);
    }

    @Override // defpackage.yu
    public int getArity() {
        return this.g;
    }

    public uz getOwner() {
        Class cls = this.c;
        if (cls == null) {
            return null;
        }
        if (!this.f) {
            return ce0.a(cls);
        }
        ce0.a.getClass();
        return new cb0(cls);
    }

    public int hashCode() {
        Object obj = this.b;
        int iHashCode = (obj != null ? obj.hashCode() : 0) * 31;
        Class cls = this.c;
        return ((((((this.e.hashCode() + ((this.d.hashCode() + ((iHashCode + (cls != null ? cls.hashCode() : 0)) * 31)) * 31)) * 31) + (this.f ? 1231 : 1237)) * 31) + this.g) * 31) + this.h;
    }

    public String toString() {
        ce0.a.getClass();
        return de0.a(this);
    }

    public AdaptedFunctionReference(int i, Object obj, Class cls, String str, String str2, int i2) {
        this.b = obj;
        this.c = cls;
        this.d = str;
        this.e = str2;
        this.f = (i2 & 1) == 1;
        this.g = i;
        this.h = i2 >> 1;
    }
}
