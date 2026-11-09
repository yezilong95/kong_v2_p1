package defpackage;

import android.os.Process;

/* JADX INFO: loaded from: classes.dex */
public final class qe0 extends Thread {
    public final int b;

    public qe0(Runnable runnable) {
        super(runnable, "fonts-androidx");
        this.b = 10;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        Process.setThreadPriority(this.b);
        super.run();
    }
}
