package defpackage;

import io.nats.client.support.NatsJetStreamConstants;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class tl implements y30 {
    public final AtomicBoolean a;
    public final y30 b;
    public final AtomicReference d = new AtomicReference();
    public final AtomicBoolean e = new AtomicBoolean(false);
    public final int c = 32;

    public tl(AtomicBoolean atomicBoolean, sl slVar) {
        this.a = atomicBoolean;
        this.b = (y30) slVar.a(NatsJetStreamConstants.MAX_PULL_SIZE);
    }

    @Override // defpackage.y30
    public final byte[] a(long j) {
        byte[] bArr = (byte[]) this.d.getAndSet(null);
        if (bArr == null || bArr.length != this.c) {
            return this.b.a(j);
        }
        this.e.set(false);
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
        return this.c * 8;
    }
}
