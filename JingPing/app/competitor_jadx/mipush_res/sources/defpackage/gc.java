package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class gc {
    public boolean a;
    public fc b;
    public boolean c;

    public void setOnCancelListener(fc fcVar) {
        synchronized (this) {
            while (this.c) {
                try {
                    try {
                        wait();
                    } catch (InterruptedException unused) {
                    }
                } finally {
                }
            }
            if (this.b == fcVar) {
                return;
            }
            this.b = fcVar;
            if (this.a && fcVar != null) {
                fcVar.d();
            }
        }
    }
}
