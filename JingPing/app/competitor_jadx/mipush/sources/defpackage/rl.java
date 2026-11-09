package defpackage;

import io.nats.client.support.NatsJetStreamConstants;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class rl implements y30 {
    public final cu a;
    public final AtomicBoolean b;
    public final y30 c;
    public final AtomicReference e = new AtomicReference();
    public final AtomicBoolean f = new AtomicBoolean(false);
    public final int d = 32;

    public rl(cu cuVar, AtomicBoolean atomicBoolean, sl slVar) {
        this.a = cuVar;
        this.b = atomicBoolean;
        this.c = (y30) slVar.a(NatsJetStreamConstants.MAX_PULL_SIZE);
    }

    @Override // defpackage.y30
    public final byte[] a(long j) {
        byte[] bArr = (byte[]) this.e.getAndSet(null);
        if (bArr == null || bArr.length != this.d) {
            return this.c.a(j);
        }
        this.f.set(false);
        return bArr;
    }

    @Override // defpackage.fu
    public final byte[] b() {
        try {
            return a(0L);
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("initial entropy fetch interrupted");
        }
    }

    @Override // defpackage.fu
    public final int c() {
        return this.d * 8;
    }
}
