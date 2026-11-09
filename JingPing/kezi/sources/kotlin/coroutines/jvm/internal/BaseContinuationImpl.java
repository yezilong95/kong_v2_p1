package kotlin.coroutines.jvm.internal;

import defpackage.fh;
import defpackage.nh;
import defpackage.oy;
import defpackage.si;
import defpackage.vd;
import defpackage.xi;
import defpackage.yh;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import kotlin.Result;
import kotlin.a;
import kotlin.coroutines.intrinsics.CoroutineSingletons;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseContinuationImpl implements fh, yh, Serializable {
    public final fh b;

    public BaseContinuationImpl(fh fhVar) {
        this.b = fhVar;
    }

    public fh create(fh fhVar) {
        oy.j(fhVar, "completion");
        throw new UnsupportedOperationException("create(Continuation) has not been overridden");
    }

    @Override // defpackage.yh
    public yh getCallerFrame() {
        fh fhVar = this.b;
        if (fhVar instanceof yh) {
            return (yh) fhVar;
        }
        return null;
    }

    public final fh getCompletion() {
        return this.b;
    }

    @Override // defpackage.fh
    public abstract /* synthetic */ nh getContext();

    public StackTraceElement getStackTraceElement() {
        int iIntValue;
        String strC;
        si siVar = (si) getClass().getAnnotation(si.class);
        String str = null;
        if (siVar == null) {
            return null;
        }
        int iV = siVar.v();
        if (iV > 1) {
            throw new IllegalStateException(("Debug metadata version mismatch. Expected: 1, got " + iV + ". Please update the Kotlin standard library.").toString());
        }
        try {
            Field declaredField = getClass().getDeclaredField("label");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(this);
            Integer num = obj instanceof Integer ? (Integer) obj : null;
            iIntValue = (num != null ? num.intValue() : 0) - 1;
        } catch (Exception unused) {
            iIntValue = -1;
        }
        int i = iIntValue >= 0 ? siVar.l()[iIntValue] : -1;
        vd vdVar = xi.j;
        vd vdVar2 = xi.i;
        if (vdVar == null) {
            try {
                vd vdVar3 = new vd(Class.class.getDeclaredMethod("getModule", new Class[0]), getClass().getClassLoader().loadClass("java.lang.Module").getDeclaredMethod("getDescriptor", new Class[0]), getClass().getClassLoader().loadClass("java.lang.module.ModuleDescriptor").getDeclaredMethod("name", new Class[0]));
                xi.j = vdVar3;
                vdVar = vdVar3;
            } catch (Exception unused2) {
                xi.j = vdVar2;
                vdVar = vdVar2;
            }
        }
        if (vdVar != vdVar2) {
            Method method = vdVar.a;
            Object objInvoke = method != null ? method.invoke(getClass(), new Object[0]) : null;
            if (objInvoke != null) {
                Method method2 = vdVar.b;
                Object objInvoke2 = method2 != null ? method2.invoke(objInvoke, new Object[0]) : null;
                if (objInvoke2 != null) {
                    Method method3 = vdVar.c;
                    Object objInvoke3 = method3 != null ? method3.invoke(objInvoke2, new Object[0]) : null;
                    if (objInvoke3 instanceof String) {
                        str = (String) objInvoke3;
                    }
                }
            }
        }
        if (str == null) {
            strC = siVar.c();
        } else {
            strC = str + '/' + siVar.c();
        }
        return new StackTraceElement(strC, siVar.m(), siVar.f(), i);
    }

    public abstract Object invokeSuspend(Object obj);

    public void releaseIntercepted() {
    }

    @Override // defpackage.fh
    public final void resumeWith(Object obj) {
        Object objInvokeSuspend;
        fh fhVar = this;
        while (true) {
            BaseContinuationImpl baseContinuationImpl = (BaseContinuationImpl) fhVar;
            fh fhVar2 = baseContinuationImpl.b;
            oy.g(fhVar2);
            try {
                objInvokeSuspend = baseContinuationImpl.invokeSuspend(obj);
            } catch (Throwable th) {
                obj = Result.m3constructorimpl(a.a(th));
            }
            if (objInvokeSuspend == CoroutineSingletons.COROUTINE_SUSPENDED) {
                return;
            }
            obj = Result.m3constructorimpl(objInvokeSuspend);
            baseContinuationImpl.releaseIntercepted();
            if (!(fhVar2 instanceof BaseContinuationImpl)) {
                fhVar2.resumeWith(obj);
                return;
            }
            fhVar = fhVar2;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Continuation at ");
        Object stackTraceElement = getStackTraceElement();
        if (stackTraceElement == null) {
            stackTraceElement = getClass().getName();
        }
        sb.append(stackTraceElement);
        return sb.toString();
    }

    public fh create(Object obj, fh fhVar) {
        oy.j(fhVar, "completion");
        throw new UnsupportedOperationException("create(Any?;Continuation) has not been overridden");
    }
}
