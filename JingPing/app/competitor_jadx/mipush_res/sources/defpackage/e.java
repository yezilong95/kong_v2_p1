package defpackage;

import io.nats.client.Connection;
import io.nats.client.Dispatcher;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class e implements Runnable {
    public final /* synthetic */ int E;
    public final /* synthetic */ Object F;

    public /* synthetic */ e(int i, Object obj) {
        this.E = i;
        this.F = obj;
    }

    private final void a() {
        jg0 jg0Var = (jg0) this.F;
        synchronized (jg0Var.j) {
            try {
                try {
                    Dispatcher dispatcher = jg0Var.e;
                    if (dispatcher != null) {
                        dispatcher.unsubscribe(jg0Var.h);
                        jg0Var.e = null;
                        jg0Var.g.set(false);
                    }
                    Connection connection = jg0Var.d;
                    if (connection != null) {
                        connection.close();
                        jg0Var.d = null;
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:92:0x007c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0050 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void b() {
        /*
            Method dump skipped, instruction units count: 452
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.e.b():void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0228 A[Catch: all -> 0x021f, TryCatch #19 {all -> 0x021f, blocks: (B:59:0x01f9, B:62:0x01fe, B:78:0x0222, B:80:0x0228, B:81:0x022e), top: B:275:0x01f9 }] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x022e A[Catch: all -> 0x021f, TRY_LEAVE, TryCatch #19 {all -> 0x021f, blocks: (B:59:0x01f9, B:62:0x01fe, B:78:0x0222, B:80:0x0228, B:81:0x022e), top: B:275:0x01f9 }] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0235 A[Catch: IOException -> 0x0238, TRY_ENTER, TRY_LEAVE, TryCatch #3 {IOException -> 0x0238, blocks: (B:73:0x021b, B:83:0x0235), top: B:275:0x01f9 }] */
    /* JADX WARN: Type inference failed for: r5v19 */
    /* JADX WARN: Type inference failed for: r5v20 */
    /* JADX WARN: Type inference failed for: r5v21 */
    /* JADX WARN: Type inference failed for: r5v4, types: [int] */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r5v6, types: [android.os.Handler] */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v8, types: [android.os.Handler] */
    /* JADX WARN: Type inference failed for: r6v23 */
    /* JADX WARN: Type inference failed for: r6v24 */
    /* JADX WARN: Type inference failed for: r6v25, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v26 */
    /* JADX WARN: Type inference failed for: r6v27, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v28 */
    /* JADX WARN: Type inference failed for: r6v29 */
    /* JADX WARN: Type inference failed for: r6v37 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v46 */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v6, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference failed for: r7v8 */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void run() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1342
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.e.run():void");
    }
}
