package defpackage;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class om {
    private volatile /* synthetic */ int installations$volatile;
    private volatile /* synthetic */ long sequenceNumber$volatile;

    static {
        AtomicIntegerFieldUpdater.newUpdater(om.class, "installations$volatile");
        AtomicLongFieldUpdater.newUpdater(om.class, "sequenceNumber$volatile");
    }
}
