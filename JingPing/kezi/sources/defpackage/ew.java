package defpackage;

import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class ew extends cw {
    public final fx e;
    public long f;
    public boolean g;
    public final /* synthetic */ iw h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ew(iw iwVar, fx fxVar) {
        super(iwVar);
        oy.j(fxVar, "url");
        this.h = iwVar;
        this.e = fxVar;
        this.f = -1L;
        this.g = true;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.c) {
            return;
        }
        if (this.g) {
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            if (!kq0.h(this)) {
                ((yc0) this.h.c).k();
                a();
            }
        }
        this.c = true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0094, code lost:
    
        if (r8.g == false) goto L28;
     */
    @Override // defpackage.cw, defpackage.uj0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long d(long r9, defpackage.p9 r11) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 237
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ew.d(long, p9):long");
    }
}
