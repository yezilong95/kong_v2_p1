package defpackage;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public final class pa extends ue {
    public static final AtomicIntegerFieldUpdater c = AtomicIntegerFieldUpdater.newUpdater(pa.class, "_resumed");
    private volatile int _resumed;

    public pa(ma maVar, Throwable th, boolean z) {
        super(z, th);
        this._resumed = 0;
    }
}
