package kotlin.jvm.internal;

import defpackage.cb0;
import defpackage.ce0;
import defpackage.fa;
import defpackage.n00;
import defpackage.sz;
import defpackage.uz;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import kotlin.jvm.KotlinReflectionNotSupportedError;
import kotlin.reflect.KVisibility;

/* JADX INFO: loaded from: classes.dex */
public abstract class CallableReference implements sz, Serializable {
    public static final Object NO_RECEIVER = fa.b;
    public transient sz b;
    public final Object c;
    public final Class d;
    public final String e;
    public final String f;
    public final boolean g;

    public CallableReference() {
        this(NO_RECEIVER);
    }

    public abstract sz a();

    public sz b() {
        sz szVarCompute = compute();
        if (szVarCompute != this) {
            return szVarCompute;
        }
        throw new KotlinReflectionNotSupportedError();
    }

    @Override // defpackage.sz
    public Object call(Object... objArr) {
        return b().call(objArr);
    }

    @Override // defpackage.sz
    public Object callBy(Map map) {
        return b().callBy(map);
    }

    public sz compute() {
        sz szVar = this.b;
        if (szVar != null) {
            return szVar;
        }
        sz szVarA = a();
        this.b = szVarA;
        return szVarA;
    }

    @Override // defpackage.rz
    public List<Annotation> getAnnotations() {
        return b().getAnnotations();
    }

    public Object getBoundReceiver() {
        return this.c;
    }

    public String getName() {
        return this.e;
    }

    public uz getOwner() {
        Class cls = this.d;
        if (cls == null) {
            return null;
        }
        if (!this.g) {
            return ce0.a(cls);
        }
        ce0.a.getClass();
        return new cb0(cls);
    }

    @Override // defpackage.sz
    public List<Object> getParameters() {
        return b().getParameters();
    }

    @Override // defpackage.sz
    public n00 getReturnType() {
        b().getReturnType();
        return null;
    }

    public String getSignature() {
        return this.f;
    }

    @Override // defpackage.sz
    public List<Object> getTypeParameters() {
        return b().getTypeParameters();
    }

    @Override // defpackage.sz
    public KVisibility getVisibility() {
        return b().getVisibility();
    }

    @Override // defpackage.sz
    public boolean isAbstract() {
        return b().isAbstract();
    }

    @Override // defpackage.sz
    public boolean isFinal() {
        return b().isFinal();
    }

    @Override // defpackage.sz
    public boolean isOpen() {
        return b().isOpen();
    }

    @Override // defpackage.sz
    public boolean isSuspend() {
        return b().isSuspend();
    }

    public CallableReference(Object obj) {
        this(obj, null, null, null, false);
    }

    public CallableReference(Object obj, Class cls, String str, String str2, boolean z) {
        this.c = obj;
        this.d = cls;
        this.e = str;
        this.f = str2;
        this.g = z;
    }
}
