package defpackage;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Result;

/* JADX INFO: loaded from: classes.dex */
public final class ok0 extends x implements d80, oq {
    public static final AtomicReferenceFieldUpdater f = AtomicReferenceFieldUpdater.newUpdater(ok0.class, Object.class, "_state");
    private volatile Object _state;
    public int e;

    public ok0(Object obj) {
        this._state = obj;
    }

    /* JADX WARN: Code restructure failed: missing block: B:109:0x016d, code lost:
    
        if (r4 != r3) goto L89;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x016a, code lost:
    
        if (r16 != r3) goto L89;
     */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00d3 A[Catch: all -> 0x003f, TryCatch #2 {all -> 0x003f, blocks: (B:14:0x0039, B:50:0x00cb, B:52:0x00d3, B:55:0x00da, B:56:0x00e0, B:58:0x00e3, B:68:0x0104, B:71:0x0117, B:77:0x0147, B:83:0x015f, B:80:0x0152, B:82:0x0158, B:74:0x0133, B:76:0x013d, B:60:0x00e9, B:64:0x00f0, B:21:0x0054, B:24:0x005f, B:49:0x00bc), top: B:107:0x0027 }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00e3 A[Catch: all -> 0x003f, TryCatch #2 {all -> 0x003f, blocks: (B:14:0x0039, B:50:0x00cb, B:52:0x00d3, B:55:0x00da, B:56:0x00e0, B:58:0x00e3, B:68:0x0104, B:71:0x0117, B:77:0x0147, B:83:0x015f, B:80:0x0152, B:82:0x0158, B:74:0x0133, B:76:0x013d, B:60:0x00e9, B:64:0x00f0, B:21:0x0054, B:24:0x005f, B:49:0x00bc), top: B:107:0x0027 }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0117 A[Catch: all -> 0x003f, TryCatch #2 {all -> 0x003f, blocks: (B:14:0x0039, B:50:0x00cb, B:52:0x00d3, B:55:0x00da, B:56:0x00e0, B:58:0x00e3, B:68:0x0104, B:71:0x0117, B:77:0x0147, B:83:0x015f, B:80:0x0152, B:82:0x0158, B:74:0x0133, B:76:0x013d, B:60:0x00e9, B:64:0x00f0, B:21:0x0054, B:24:0x005f, B:49:0x00bc), top: B:107:0x0027 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:70:0x0116 -> B:50:0x00cb). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final kotlin.coroutines.intrinsics.CoroutineSingletons a(defpackage.qq r18, kotlin.coroutines.jvm.internal.ContinuationImpl r19) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 398
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ok0.a(qq, kotlin.coroutines.jvm.internal.ContinuationImpl):kotlin.coroutines.intrinsics.CoroutineSingletons");
    }

    public final void b(Object obj) {
        int i;
        y[] yVarArr;
        nm nmVar;
        if (obj == null) {
            obj = oy.d;
        }
        synchronized (this) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f;
            if (oy.c(atomicReferenceFieldUpdater.get(this), obj)) {
                return;
            }
            atomicReferenceFieldUpdater.set(this, obj);
            int i2 = this.e;
            if ((i2 & 1) != 0) {
                this.e = i2 + 2;
                return;
            }
            int i3 = i2 + 1;
            this.e = i3;
            y[] yVarArr2 = this.b;
            while (true) {
                pk0[] pk0VarArr = (pk0[]) yVarArr2;
                if (pk0VarArr != null) {
                    for (pk0 pk0Var : pk0VarArr) {
                        if (pk0Var != null) {
                            while (true) {
                                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = pk0.a;
                                Object obj2 = atomicReferenceFieldUpdater2.get(pk0Var);
                                if (obj2 != null && obj2 != (nmVar = oy.g)) {
                                    nm nmVar2 = oy.f;
                                    if (obj2 != nmVar2) {
                                        while (!atomicReferenceFieldUpdater2.compareAndSet(pk0Var, obj2, nmVar2)) {
                                            if (atomicReferenceFieldUpdater2.get(pk0Var) != obj2) {
                                                break;
                                            }
                                        }
                                        ((ma) obj2).resumeWith(Result.m3constructorimpl(bq0.a));
                                        break;
                                    }
                                    while (!atomicReferenceFieldUpdater2.compareAndSet(pk0Var, obj2, nmVar)) {
                                        if (atomicReferenceFieldUpdater2.get(pk0Var) != obj2) {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                synchronized (this) {
                    i = this.e;
                    if (i == i3) {
                        this.e = i3 + 1;
                        return;
                    }
                    yVarArr = this.b;
                }
                yVarArr2 = yVarArr;
                i3 = i;
            }
        }
    }

    @Override // defpackage.qq
    public final Object emit(Object obj, fh fhVar) {
        b(obj);
        return bq0.a;
    }
}
