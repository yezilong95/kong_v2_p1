package defpackage;

import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class im0 implements Runnable {
    public final /* synthetic */ int E;
    public final /* synthetic */ lm0 F;

    public /* synthetic */ im0(lm0 lm0Var, int i) {
        this.E = i;
        this.F = lm0Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        km0 km0Var;
        switch (this.E) {
            case 0:
                this.F.i.set(false);
                return;
            case 1:
                lm0 lm0Var = this.F;
                AtomicBoolean atomicBoolean = lm0Var.g;
                try {
                    if (lm0Var.e != null && ((km0Var = lm0Var.a) == null || !km0Var.isOpen())) {
                        lm0Var.b(lm0Var.e);
                    }
                    break;
                } catch (Exception unused) {
                } catch (Throwable th) {
                    atomicBoolean.set(false);
                    throw th;
                }
                atomicBoolean.set(false);
                return;
            default:
                this.F.a.send(lm0.n);
                return;
        }
    }
}
