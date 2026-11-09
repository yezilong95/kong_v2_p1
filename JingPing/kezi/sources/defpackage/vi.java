package defpackage;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public final class vi {
    private volatile long sequenceNumber;

    static {
        AtomicLongFieldUpdater.newUpdater(vi.class, "sequenceNumber");
    }
}
