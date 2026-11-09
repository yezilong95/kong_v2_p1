package defpackage;

import java.util.concurrent.ThreadFactory;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class z7 implements ThreadFactory {
    public final /* synthetic */ int a;
    public final /* synthetic */ String b;

    public /* synthetic */ z7(String str, int i) {
        this.a = i;
        this.b = str;
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        switch (this.a) {
            case 0:
                Thread thread = new Thread(runnable, this.b);
                thread.setDaemon(true);
                return thread;
            default:
                Thread thread2 = new Thread(runnable, this.b);
                thread2.setPriority(10);
                return thread2;
        }
    }
}
