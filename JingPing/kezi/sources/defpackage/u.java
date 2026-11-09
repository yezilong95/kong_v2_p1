package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class u extends rp0 {
    @Override // defpackage.rp0
    public final void P(v vVar, v vVar2) {
        vVar.b = vVar2;
    }

    @Override // defpackage.rp0
    public final void Q(v vVar, Thread thread) {
        vVar.a = thread;
    }

    @Override // defpackage.rp0
    public final boolean j(w wVar, s sVar, s sVar2) {
        synchronized (wVar) {
            try {
                if (wVar.b != sVar) {
                    return false;
                }
                wVar.b = sVar2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // defpackage.rp0
    public final boolean k(w wVar, Object obj, Object obj2) {
        synchronized (wVar) {
            try {
                if (wVar.a != obj) {
                    return false;
                }
                wVar.a = obj2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // defpackage.rp0
    public final boolean l(w wVar, v vVar, v vVar2) {
        synchronized (wVar) {
            try {
                if (wVar.c != vVar) {
                    return false;
                }
                wVar.c = vVar2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
