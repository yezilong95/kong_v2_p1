package defpackage;

import java.io.IOException;
import kotlin.jvm.internal.Ref$ObjectRef;
import okhttp3.internal.http2.ErrorCode;

/* JADX INFO: loaded from: classes.dex */
public final class mw extends vm0 {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ mw(String str, Object obj, Object obj2, int i) {
        super(str, true);
        this.e = i;
        this.f = obj;
        this.g = obj2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v0, types: [T, gi0] */
    @Override // defpackage.vm0
    public final long a() {
        long jA;
        bx[] bxVarArr;
        int i = 0;
        switch (this.e) {
            case 0:
                uw uwVar = (uw) this.f;
                uwVar.b.a(uwVar, (gi0) ((Ref$ObjectRef) this.g).element);
                return -1L;
            case 1:
                try {
                    ((uw) this.f).b.b((bx) this.g);
                    break;
                } catch (IOException e) {
                    rb0 rb0Var = rb0.a;
                    rb0 rb0Var2 = rb0.a;
                    String str = "Http2Connection.Listener failure for " + ((uw) this.f).d;
                    rb0Var2.getClass();
                    rb0.i(4, str, e);
                    try {
                        ((bx) this.g).c(ErrorCode.PROTOCOL_ERROR, e);
                        break;
                    } catch (IOException unused) {
                    }
                }
                return -1L;
            default:
                ow owVar = (ow) this.f;
                gi0 gi0Var = (gi0) this.g;
                owVar.getClass();
                Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
                uw uwVar2 = owVar.c;
                synchronized (uwVar2.x) {
                    synchronized (uwVar2) {
                        try {
                            gi0 gi0Var2 = uwVar2.r;
                            ?? gi0Var3 = new gi0();
                            gi0Var3.b(gi0Var2);
                            gi0Var3.b(gi0Var);
                            ref$ObjectRef.element = gi0Var3;
                            jA = ((long) gi0Var3.a()) - ((long) gi0Var2.a());
                            bxVarArr = (jA == 0 || uwVar2.c.isEmpty()) ? null : (bx[]) uwVar2.c.values().toArray(new bx[0]);
                            gi0 gi0Var4 = (gi0) ref$ObjectRef.element;
                            oy.j(gi0Var4, "<set-?>");
                            uwVar2.r = gi0Var4;
                            uwVar2.k.c(new mw(uwVar2.d + " onSettings", uwVar2, ref$ObjectRef, i), 0L);
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                    try {
                        uwVar2.x.a((gi0) ref$ObjectRef.element);
                    } catch (IOException e2) {
                        uwVar2.t(e2);
                    }
                    break;
                }
                if (bxVarArr != null) {
                    int length = bxVarArr.length;
                    while (i < length) {
                        bx bxVar = bxVarArr[i];
                        synchronized (bxVar) {
                            bxVar.f += jA;
                            if (jA > 0) {
                                bxVar.notifyAll();
                            }
                        }
                        i++;
                    }
                }
                return -1L;
        }
    }
}
