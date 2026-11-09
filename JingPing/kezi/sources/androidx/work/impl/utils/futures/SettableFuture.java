package androidx.work.impl.utils.futures;

import defpackage.x20;

/* JADX INFO: loaded from: classes.dex */
public final class SettableFuture<V> extends AbstractFuture<V> {
    private SettableFuture() {
    }

    public static <V> SettableFuture<V> create() {
        return new SettableFuture<>();
    }

    @Override // androidx.work.impl.utils.futures.AbstractFuture
    public boolean set(V v) {
        return super.set(v);
    }

    @Override // androidx.work.impl.utils.futures.AbstractFuture
    public boolean setException(Throwable th) {
        return super.setException(th);
    }

    @Override // androidx.work.impl.utils.futures.AbstractFuture
    public boolean setFuture(x20 x20Var) {
        return super.setFuture(x20Var);
    }
}
