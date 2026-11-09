package kotlin.jvm.internal;

import defpackage.a70;
import defpackage.h60;
import defpackage.j60;
import defpackage.vb;
import defpackage.wj0;
import defpackage.yn0;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import kotlin.jvm.KotlinReflectionNotSupportedError;
import kotlin.reflect.KVisibility;

/* JADX INFO: loaded from: classes.dex */
public abstract class CallableReference implements h60, Serializable {
    public static final Object NO_RECEIVER = vb.E;
    public transient h60 E;
    public final Object F;
    public final Class G;
    public final String H;
    public final String I;
    public final boolean J;

    public CallableReference() {
        this(NO_RECEIVER);
    }

    public abstract h60 a();

    public h60 b() {
        h60 h60VarCompute = compute();
        if (h60VarCompute != this) {
            return h60VarCompute;
        }
        throw new KotlinReflectionNotSupportedError();
    }

    @Override // defpackage.h60
    public Object call(Object... objArr) {
        return b().call(objArr);
    }

    @Override // defpackage.h60
    public Object callBy(Map map) {
        return b().callBy(map);
    }

    public h60 compute() {
        h60 h60Var = this.E;
        if (h60Var != null) {
            return h60Var;
        }
        h60 h60VarA = a();
        this.E = h60VarA;
        return h60VarA;
    }

    @Override // defpackage.g60
    public List<Annotation> getAnnotations() {
        return b().getAnnotations();
    }

    public Object getBoundReceiver() {
        return this.F;
    }

    public String getName() {
        return this.H;
    }

    public j60 getOwner() {
        Class cls = this.G;
        if (cls == null) {
            return null;
        }
        if (!this.J) {
            return yn0.a(cls);
        }
        yn0.a.getClass();
        return new wj0(cls);
    }

    @Override // defpackage.h60
    public List<Object> getParameters() {
        return b().getParameters();
    }

    @Override // defpackage.h60
    public a70 getReturnType() {
        b().getReturnType();
        return null;
    }

    public String getSignature() {
        return this.I;
    }

    @Override // defpackage.h60
    public List<Object> getTypeParameters() {
        return b().getTypeParameters();
    }

    @Override // defpackage.h60
    public KVisibility getVisibility() {
        return b().getVisibility();
    }

    @Override // defpackage.h60
    public boolean isAbstract() {
        return b().isAbstract();
    }

    @Override // defpackage.h60
    public boolean isFinal() {
        return b().isFinal();
    }

    @Override // defpackage.h60
    public boolean isOpen() {
        return b().isOpen();
    }

    @Override // defpackage.h60
    public boolean isSuspend() {
        return b().isSuspend();
    }

    public CallableReference(Object obj) {
        this(obj, null, null, null, false);
    }

    public CallableReference(Object obj, Class cls, String str, String str2, boolean z) {
        this.F = obj;
        this.G = cls;
        this.H = str;
        this.I = str2;
        this.J = z;
    }
}
