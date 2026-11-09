package kotlinx.coroutines.flow.internal;

import defpackage.ae;
import defpackage.be;
import defpackage.bq0;
import defpackage.bz;
import defpackage.ce;
import defpackage.dg0;
import defpackage.eg0;
import defpackage.fg0;
import defpackage.fh;
import defpackage.fl;
import defpackage.hk;
import defpackage.jq0;
import defpackage.nh;
import defpackage.nz;
import defpackage.o90;
import defpackage.oh;
import defpackage.oy;
import defpackage.pe;
import defpackage.qm;
import defpackage.qq;
import defpackage.vk0;
import defpackage.yh;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Result;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import org.java_websocket.extensions.ExtensionRequestData;

/* JADX INFO: loaded from: classes.dex */
public final class SafeCollector<T> extends ContinuationImpl implements qq {
    public final nh collectContext;
    public final int collectContextSize;
    public final qq collector;
    public nh e;
    public fh f;

    public SafeCollector(qq qqVar, nh nhVar) {
        super(o90.b, EmptyCoroutineContext.INSTANCE);
        this.collector = qqVar;
        this.collectContext = nhVar;
        this.collectContextSize = ((Number) nhVar.fold(0, pe.i)).intValue();
    }

    public final Object a(fh fhVar, Object obj) {
        Comparable comparable;
        String strSubstring;
        nh context = fhVar.getContext();
        bz bzVar = (bz) context.get(qm.f);
        if (bzVar != null && !bzVar.a()) {
            throw ((nz) bzVar).l();
        }
        nh nhVar = this.e;
        if (nhVar != context) {
            int i = 0;
            if (nhVar instanceof fl) {
                String str = "\n            Flow exception transparency is violated:\n                Previous 'emit' call has thrown exception " + ((fl) nhVar).b + ", but then emission attempt of value '" + obj + "' has been detected.\n                Emissions from 'catch' blocks are prohibited in order to avoid unspecified behaviour, 'Flow.catch' operator can be used instead.\n                For a more detailed explanation, please refer to Flow documentation.\n            ";
                oy.j(str, "<this>");
                List listT0 = vk0.t0(str);
                ArrayList<String> arrayList = new ArrayList();
                for (T t : listT0) {
                    if (!vk0.r0((String) t)) {
                        arrayList.add(t);
                    }
                }
                ArrayList arrayList2 = new ArrayList(ce.Z(arrayList));
                for (String str2 : arrayList) {
                    int length = str2.length();
                    int length2 = 0;
                    while (true) {
                        if (length2 >= length) {
                            length2 = -1;
                            break;
                        }
                        if (!jq0.J(str2.charAt(length2))) {
                            break;
                        }
                        length2++;
                    }
                    if (length2 == -1) {
                        length2 = str2.length();
                    }
                    arrayList2.add(Integer.valueOf(length2));
                }
                Iterator it = arrayList2.iterator();
                if (it.hasNext()) {
                    comparable = (Comparable) it.next();
                    while (it.hasNext()) {
                        Comparable comparable2 = (Comparable) it.next();
                        if (comparable.compareTo(comparable2) > 0) {
                            comparable = comparable2;
                        }
                    }
                } else {
                    comparable = null;
                }
                Integer num = (Integer) comparable;
                int iIntValue = num != null ? num.intValue() : 0;
                int length3 = str.length();
                listT0.size();
                int size = listT0.size() - 1;
                ArrayList arrayList3 = new ArrayList();
                for (T t2 : listT0) {
                    int i2 = i + 1;
                    if (i < 0) {
                        be.Y();
                        throw null;
                    }
                    String str3 = (String) t2;
                    if ((i == 0 || i == size) && vk0.r0(str3)) {
                        strSubstring = null;
                    } else {
                        oy.j(str3, "<this>");
                        if (iIntValue < 0) {
                            throw new IllegalArgumentException(hk.h(iIntValue, "Requested character count ", " is less than zero.").toString());
                        }
                        int length4 = str3.length();
                        if (iIntValue <= length4) {
                            length4 = iIntValue;
                        }
                        strSubstring = str3.substring(length4);
                        oy.i(strSubstring, "substring(...)");
                        oh.e.getClass();
                    }
                    if (strSubstring != null) {
                        arrayList3.add(strSubstring);
                    }
                    i = i2;
                }
                StringBuilder sb = new StringBuilder(length3);
                ae.b0(arrayList3, sb, "\n", ExtensionRequestData.EMPTY_VALUE, ExtensionRequestData.EMPTY_VALUE, "...", null);
                String string = sb.toString();
                oy.i(string, "toString(...)");
                throw new IllegalStateException(string.toString());
            }
            if (((Number) context.fold(0, new fg0(0, this))).intValue() != this.collectContextSize) {
                throw new IllegalStateException(("Flow invariant is violated:\n\t\tFlow was collected in " + this.collectContext + ",\n\t\tbut emission happened in " + context + ".\n\t\tPlease refer to 'flow' documentation or use 'flowOn' instead").toString());
            }
            this.e = context;
        }
        this.f = fhVar;
        dg0 dg0Var = eg0.a;
        qq qqVar = this.collector;
        oy.h(qqVar, "null cannot be cast to non-null type kotlinx.coroutines.flow.FlowCollector<kotlin.Any?>");
        dg0Var.getClass();
        Object objEmit = qqVar.emit(obj, this);
        if (!oy.c(objEmit, CoroutineSingletons.COROUTINE_SUSPENDED)) {
            this.f = null;
        }
        return objEmit;
    }

    @Override // defpackage.qq
    public Object emit(T t, fh fhVar) {
        try {
            Object objA = a(fhVar, t);
            return objA == CoroutineSingletons.COROUTINE_SUSPENDED ? objA : bq0.a;
        } catch (Throwable th) {
            this.e = new fl(fhVar.getContext(), th);
            throw th;
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl, defpackage.yh
    public yh getCallerFrame() {
        fh fhVar = this.f;
        if (fhVar instanceof yh) {
            return (yh) fhVar;
        }
        return null;
    }

    @Override // kotlin.coroutines.jvm.internal.ContinuationImpl, kotlin.coroutines.jvm.internal.BaseContinuationImpl, defpackage.fh
    public nh getContext() {
        nh nhVar = this.e;
        return nhVar == null ? EmptyCoroutineContext.INSTANCE : nhVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public StackTraceElement getStackTraceElement() {
        return null;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public Object invokeSuspend(Object obj) {
        Throwable thM6exceptionOrNullimpl = Result.m6exceptionOrNullimpl(obj);
        if (thM6exceptionOrNullimpl != null) {
            this.e = new fl(getContext(), thM6exceptionOrNullimpl);
        }
        fh fhVar = this.f;
        if (fhVar != null) {
            fhVar.resumeWith(obj);
        }
        return CoroutineSingletons.COROUTINE_SUSPENDED;
    }

    @Override // kotlin.coroutines.jvm.internal.ContinuationImpl, kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public void releaseIntercepted() {
        super.releaseIntercepted();
    }
}
