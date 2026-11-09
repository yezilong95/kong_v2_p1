package defpackage;

import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public abstract class aj0 {
    private final pf0 database;
    private final AtomicBoolean lock;
    private final i10 stmt$delegate;

    public aj0(pf0 pf0Var) {
        oy.j(pf0Var, "database");
        this.database = pf0Var;
        this.lock = new AtomicBoolean(false);
        this.stmt$delegate = oy.G(new ju(4, this));
    }

    public wl0 acquire() {
        assertNotMainThread();
        if (this.lock.compareAndSet(false, true)) {
            return (wl0) this.stmt$delegate.getValue();
        }
        return this.database.compileStatement(createQuery());
    }

    public void assertNotMainThread() {
        this.database.assertNotMainThread();
    }

    public abstract String createQuery();

    public void release(wl0 wl0Var) {
        oy.j(wl0Var, "statement");
        if (wl0Var == ((wl0) this.stmt$delegate.getValue())) {
            this.lock.set(false);
        }
    }
}
