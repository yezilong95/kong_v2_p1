package defpackage;

import android.os.Trace;

/* JADX INFO: loaded from: classes.dex */
public final class kn implements Runnable {
    @Override // java.lang.Runnable
    public final void run() {
        try {
            int i = ap0.a;
            Trace.beginSection("EmojiCompat.EmojiCompatInitializer.run");
            if (hn.j != null) {
                hn.a().c();
            }
            Trace.endSection();
        } catch (Throwable th) {
            int i2 = ap0.a;
            Trace.endSection();
            throw th;
        }
    }
}
