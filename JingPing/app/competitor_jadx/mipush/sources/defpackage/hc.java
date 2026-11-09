package defpackage;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public final class hc extends og {
    public static final /* synthetic */ AtomicIntegerFieldUpdater c = AtomicIntegerFieldUpdater.newUpdater(hc.class, "_resumed$volatile");
    private volatile /* synthetic */ int _resumed$volatile;

    public hc(ec ecVar, Throwable th, boolean z) {
        super(z, th);
        this._resumed$volatile = 0;
    }
}
