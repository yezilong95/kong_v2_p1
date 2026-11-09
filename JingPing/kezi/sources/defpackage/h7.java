package defpackage;

import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
public final class h7 extends Thread {
    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        ReentrantLock reentrantLock;
        k7 k7VarD;
        while (true) {
            try {
                reentrantLock = k7.h;
                reentrantLock.lock();
                try {
                    k7VarD = oy.d();
                } finally {
                    reentrantLock.unlock();
                }
            } catch (InterruptedException unused) {
            }
            if (k7VarD == k7.l) {
                k7.l = null;
                return;
            } else {
                reentrantLock.unlock();
                if (k7VarD != null) {
                    k7VarD.j();
                }
            }
        }
    }
}
