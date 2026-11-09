package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class oa {
    public boolean a;
    public na b;
    public boolean c;

    public void setOnCancelListener(na naVar) {
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
            if (this.b == naVar) {
                return;
            }
            this.b = naVar;
            if (this.a && naVar != null) {
                naVar.h();
            }
        }
    }
}
