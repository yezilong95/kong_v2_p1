package kotlinx.coroutines.flow.internal;

import defpackage.bj;
import defpackage.cj;
import defpackage.dx;
import defpackage.f50;
import defpackage.gp;
import defpackage.is;
import defpackage.jg;
import defpackage.mj;
import defpackage.nu0;
import defpackage.p0;
import defpackage.qq0;
import defpackage.qx0;
import defpackage.rq0;
import defpackage.sh0;
import defpackage.sq0;
import defpackage.t40;
import defpackage.t50;
import defpackage.ui;
import defpackage.vf;
import defpackage.wn;
import defpackage.y21;
import defpackage.zn;
import io.nats.client.support.NatsConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.Result;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
public final class SafeCollector<T> extends ContinuationImpl implements dx {
    public bj H;
    public ui I;
    public final bj collectContext;
    public final int collectContextSize;
    public final dx collector;

    public SafeCollector(dx dxVar, bj bjVar) {
        super(sh0.E, EmptyCoroutineContext.INSTANCE);
        this.collector = dxVar;
        this.collectContext = bjVar;
        this.collectContextSize = ((Number) bjVar.fold(0, jg.L)).intValue();
    }

    public final Object a(ui uiVar, Object obj) {
        Comparable comparable;
        String strSubstring;
        bj context = uiVar.getContext();
        f50 f50Var = (f50) context.get(is.H);
        if (f50Var != null && !f50Var.isActive()) {
            throw ((t50) f50Var).k();
        }
        bj bjVar = this.H;
        if (bjVar != context) {
            int i = 0;
            if (bjVar instanceof gp) {
                String str = "\n            Flow exception transparency is violated:\n                Previous 'emit' call has thrown exception " + ((gp) bjVar).E + ", but then emission attempt of value '" + obj + "' has been detected.\n                Emissions from 'catch' blocks are prohibited in order to avoid unspecified behaviour, 'Flow.catch' operator can be used instead.\n                For a more detailed explanation, please refer to Flow documentation.\n            ";
                t40.n(str, "<this>");
                String[] strArr = {NatsConstants.CRLF, "\n", "\r"};
                qx0.r0(0);
                List listAsList = Arrays.asList(strArr);
                t40.m(listAsList, "asList(...)");
                List listS0 = nu0.S0(new wn(new wn(str, new sq0(1, listAsList), 0), new p0(1, str), 2));
                ArrayList<String> arrayList = new ArrayList();
                for (T t : listS0) {
                    if (!qx0.o0((String) t)) {
                        arrayList.add(t);
                    }
                }
                ArrayList arrayList2 = new ArrayList(arrayList.size());
                for (String str2 : arrayList) {
                    int length = str2.length();
                    int length2 = 0;
                    while (true) {
                        if (length2 >= length) {
                            length2 = -1;
                            break;
                        }
                        char cCharAt = str2.charAt(length2);
                        if (!Character.isWhitespace(cCharAt) && !Character.isSpaceChar(cCharAt)) {
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
                listS0.size();
                int size = listS0.size() - 1;
                ArrayList arrayList3 = new ArrayList();
                for (T t2 : listS0) {
                    int i2 = i + 1;
                    if (i < 0) {
                        throw new ArithmeticException("Index overflow has happened.");
                    }
                    String str3 = (String) t2;
                    if ((i == 0 || i == size) && qx0.o0(str3)) {
                        strSubstring = null;
                    } else {
                        t40.n(str3, "<this>");
                        if (iIntValue < 0) {
                            throw new IllegalArgumentException(zn.k(iIntValue, "Requested character count ", " is less than zero.").toString());
                        }
                        int length4 = str3.length();
                        if (iIntValue <= length4) {
                            length4 = iIntValue;
                        }
                        strSubstring = str3.substring(length4);
                        t40.m(strSubstring, "substring(...)");
                        cj.H.getClass();
                    }
                    if (strSubstring != null) {
                        arrayList3.add(strSubstring);
                    }
                    i = i2;
                }
                StringBuilder sb = new StringBuilder(length3);
                vf.j1(arrayList3, sb, "\n", "", "", "...", null);
                String string = sb.toString();
                t40.m(string, "toString(...)");
                throw new IllegalStateException(string.toString());
            }
            if (((Number) context.fold(0, new sq0(0, this))).intValue() != this.collectContextSize) {
                throw new IllegalStateException(("Flow invariant is violated:\n\t\tFlow was collected in " + this.collectContext + ",\n\t\tbut emission happened in " + context + ".\n\t\tPlease refer to 'flow' documentation or use 'flowOn' instead").toString());
            }
            this.H = context;
        }
        this.I = uiVar;
        qq0 qq0Var = rq0.a;
        dx dxVar = this.collector;
        t40.l(dxVar, "null cannot be cast to non-null type kotlinx.coroutines.flow.FlowCollector<kotlin.Any?>");
        qq0Var.getClass();
        Object objEmit = dxVar.emit(obj, this);
        if (!t40.f(objEmit, CoroutineSingletons.COROUTINE_SUSPENDED)) {
            this.I = null;
        }
        return objEmit;
    }

    @Override // defpackage.dx
    public Object emit(T t, ui uiVar) {
        try {
            Object objA = a(uiVar, t);
            return objA == CoroutineSingletons.COROUTINE_SUSPENDED ? objA : y21.a;
        } catch (Throwable th) {
            this.H = new gp(uiVar.getContext(), th);
            throw th;
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl, defpackage.mj
    public mj getCallerFrame() {
        ui uiVar = this.I;
        if (uiVar instanceof mj) {
            return (mj) uiVar;
        }
        return null;
    }

    @Override // kotlin.coroutines.jvm.internal.ContinuationImpl, kotlin.coroutines.jvm.internal.BaseContinuationImpl, defpackage.ui
    public bj getContext() {
        bj bjVar = this.H;
        return bjVar == null ? EmptyCoroutineContext.INSTANCE : bjVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public StackTraceElement getStackTraceElement() {
        return null;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public Object invokeSuspend(Object obj) {
        Throwable thM6exceptionOrNullimpl = Result.m6exceptionOrNullimpl(obj);
        if (thM6exceptionOrNullimpl != null) {
            this.H = new gp(getContext(), thM6exceptionOrNullimpl);
        }
        ui uiVar = this.I;
        if (uiVar != null) {
            uiVar.resumeWith(obj);
        }
        return CoroutineSingletons.COROUTINE_SUSPENDED;
    }

    @Override // kotlin.coroutines.jvm.internal.ContinuationImpl, kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public void releaseIntercepted() {
        super.releaseIntercepted();
    }
}
