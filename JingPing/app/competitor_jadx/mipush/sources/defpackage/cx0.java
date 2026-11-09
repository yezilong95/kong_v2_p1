package defpackage;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Result;

/* JADX INFO: loaded from: classes.dex */
public final class cx0 extends h1 implements bx, dx {
    public static final /* synthetic */ AtomicReferenceFieldUpdater I = AtomicReferenceFieldUpdater.newUpdater(cx0.class, Object.class, "_state$volatile");
    public int H;
    private volatile /* synthetic */ Object _state$volatile;

    public cx0(Object obj) {
        this._state$volatile = obj;
    }

    /* JADX WARN: Code restructure failed: missing block: B:110:0x0165, code lost:
    
        if (r4 != r3) goto L90;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0162, code lost:
    
        if (r16 != r3) goto L90;
     */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00ce A[Catch: all -> 0x003f, TryCatch #2 {all -> 0x003f, blocks: (B:14:0x0039, B:50:0x00c6, B:52:0x00ce, B:55:0x00d5, B:56:0x00db, B:58:0x00de, B:68:0x00ff, B:71:0x010f, B:77:0x013f, B:78:0x0141, B:84:0x0157, B:81:0x014a, B:83:0x0150, B:74:0x012b, B:76:0x0135, B:60:0x00e4, B:64:0x00eb, B:21:0x0054, B:24:0x005f, B:49:0x00b7), top: B:108:0x0027 }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00de A[Catch: all -> 0x003f, TryCatch #2 {all -> 0x003f, blocks: (B:14:0x0039, B:50:0x00c6, B:52:0x00ce, B:55:0x00d5, B:56:0x00db, B:58:0x00de, B:68:0x00ff, B:71:0x010f, B:77:0x013f, B:78:0x0141, B:84:0x0157, B:81:0x014a, B:83:0x0150, B:74:0x012b, B:76:0x0135, B:60:0x00e4, B:64:0x00eb, B:21:0x0054, B:24:0x005f, B:49:0x00b7), top: B:108:0x0027 }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x010f A[Catch: all -> 0x003f, TryCatch #2 {all -> 0x003f, blocks: (B:14:0x0039, B:50:0x00c6, B:52:0x00ce, B:55:0x00d5, B:56:0x00db, B:58:0x00de, B:68:0x00ff, B:71:0x010f, B:77:0x013f, B:78:0x0141, B:84:0x0157, B:81:0x014a, B:83:0x0150, B:74:0x012b, B:76:0x0135, B:60:0x00e4, B:64:0x00eb, B:21:0x0054, B:24:0x005f, B:49:0x00b7), top: B:108:0x0027 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:70:0x010e -> B:50:0x00c6). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final kotlin.coroutines.intrinsics.CoroutineSingletons a(defpackage.dx r18, kotlin.coroutines.jvm.internal.ContinuationImpl r19) {
        /*
            Method dump skipped, instruction units count: 390
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.cx0.a(dx, kotlin.coroutines.jvm.internal.ContinuationImpl):kotlin.coroutines.intrinsics.CoroutineSingletons");
    }

    public final void b(Object obj) {
        int i;
        dx0[] dx0VarArr;
        wy0 wy0Var;
        if (obj == null) {
            obj = j31.w;
        }
        synchronized (this) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = I;
            if (t40.f(atomicReferenceFieldUpdater.get(this), obj)) {
                return;
            }
            atomicReferenceFieldUpdater.set(this, obj);
            int i2 = this.H;
            if ((i2 & 1) != 0) {
                this.H = i2 + 2;
                return;
            }
            int i3 = i2 + 1;
            this.H = i3;
            dx0[] dx0VarArr2 = this.E;
            while (true) {
                if (dx0VarArr2 != null) {
                    for (dx0 dx0Var : dx0VarArr2) {
                        if (dx0Var != null) {
                            AtomicReference atomicReference = dx0Var.a;
                            while (true) {
                                Object obj2 = atomicReference.get();
                                if (obj2 != null && obj2 != (wy0Var = z91.Y)) {
                                    wy0 wy0Var2 = z91.X;
                                    if (obj2 != wy0Var2) {
                                        while (!atomicReference.compareAndSet(obj2, wy0Var2)) {
                                            if (atomicReference.get() != obj2) {
                                                break;
                                            }
                                        }
                                        ((ec) obj2).resumeWith(Result.m3constructorimpl(y21.a));
                                        break;
                                    }
                                    while (!atomicReference.compareAndSet(obj2, wy0Var)) {
                                        if (atomicReference.get() != obj2) {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                synchronized (this) {
                    i = this.H;
                    if (i == i3) {
                        this.H = i3 + 1;
                        return;
                    }
                    dx0VarArr = this.E;
                }
                dx0VarArr2 = dx0VarArr;
                i3 = i;
            }
        }
    }

    @Override // defpackage.dx
    public final Object emit(Object obj, ui uiVar) {
        b(obj);
        return y21.a;
    }
}
