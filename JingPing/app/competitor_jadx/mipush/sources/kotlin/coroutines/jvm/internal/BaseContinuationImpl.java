package kotlin.coroutines.jvm.internal;

import defpackage.bf0;
import defpackage.bj;
import defpackage.mj;
import defpackage.mm;
import defpackage.qm;
import defpackage.t40;
import defpackage.ui;
import io.nats.client.support.ApiConstants;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import kotlin.Result;
import kotlin.a;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import org.bouncycastle.jcajce.util.AnnotatedPrivateKey;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseContinuationImpl implements ui, mj, Serializable {
    public final ui E;

    public BaseContinuationImpl(ui uiVar) {
        this.E = uiVar;
    }

    public ui create(ui uiVar) {
        t40.n(uiVar, "completion");
        throw new UnsupportedOperationException("create(Continuation) has not been overridden");
    }

    @Override // defpackage.mj
    public mj getCallerFrame() {
        ui uiVar = this.E;
        if (uiVar instanceof mj) {
            return (mj) uiVar;
        }
        return null;
    }

    public final ui getCompletion() {
        return this.E;
    }

    @Override // defpackage.ui
    public abstract /* synthetic */ bj getContext();

    public StackTraceElement getStackTraceElement() {
        int iIntValue;
        String strC;
        mm mmVar = (mm) getClass().getAnnotation(mm.class);
        String str = null;
        if (mmVar == null) {
            return null;
        }
        int iV = mmVar.v();
        if (iV > 1) {
            throw new IllegalStateException(("Debug metadata version mismatch. Expected: 1, got " + iV + ". Please update the Kotlin standard library.").toString());
        }
        try {
            Field declaredField = getClass().getDeclaredField(AnnotatedPrivateKey.LABEL);
            declaredField.setAccessible(true);
            Object obj = declaredField.get(this);
            Integer num = obj instanceof Integer ? (Integer) obj : null;
            iIntValue = (num != null ? num.intValue() : 0) - 1;
        } catch (Exception unused) {
            iIntValue = -1;
        }
        int i = iIntValue >= 0 ? mmVar.l()[iIntValue] : -1;
        bf0 bf0Var = qm.g;
        bf0 bf0Var2 = qm.f;
        if (bf0Var == null) {
            try {
                bf0 bf0Var3 = new bf0(Class.class.getDeclaredMethod("getModule", new Class[0]), getClass().getClassLoader().loadClass("java.lang.Module").getDeclaredMethod("getDescriptor", new Class[0]), getClass().getClassLoader().loadClass("java.lang.module.ModuleDescriptor").getDeclaredMethod(ApiConstants.NAME, new Class[0]));
                qm.g = bf0Var3;
                bf0Var = bf0Var3;
            } catch (Exception unused2) {
                qm.g = bf0Var2;
                bf0Var = bf0Var2;
            }
        }
        if (bf0Var != bf0Var2) {
            Method method = bf0Var.a;
            Object objInvoke = method != null ? method.invoke(getClass(), new Object[0]) : null;
            if (objInvoke != null) {
                Method method2 = bf0Var.b;
                Object objInvoke2 = method2 != null ? method2.invoke(objInvoke, new Object[0]) : null;
                if (objInvoke2 != null) {
                    Method method3 = bf0Var.c;
                    Object objInvoke3 = method3 != null ? method3.invoke(objInvoke2, new Object[0]) : null;
                    if (objInvoke3 instanceof String) {
                        str = (String) objInvoke3;
                    }
                }
            }
        }
        if (str == null) {
            strC = mmVar.c();
        } else {
            strC = str + '/' + mmVar.c();
        }
        return new StackTraceElement(strC, mmVar.m(), mmVar.f(), i);
    }

    public abstract Object invokeSuspend(Object obj);

    public void releaseIntercepted() {
    }

    @Override // defpackage.ui
    public final void resumeWith(Object obj) {
        Object objInvokeSuspend;
        ui uiVar = this;
        while (true) {
            BaseContinuationImpl baseContinuationImpl = (BaseContinuationImpl) uiVar;
            ui uiVar2 = baseContinuationImpl.E;
            t40.k(uiVar2);
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
            if (!(uiVar2 instanceof BaseContinuationImpl)) {
                uiVar2.resumeWith(obj);
                return;
            }
            uiVar = uiVar2;
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

    public ui create(Object obj, ui uiVar) {
        t40.n(uiVar, "completion");
        throw new UnsupportedOperationException("create(Any?;Continuation) has not been overridden");
    }
}
