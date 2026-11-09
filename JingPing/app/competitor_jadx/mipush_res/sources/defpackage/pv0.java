package defpackage;

import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public abstract class pv0 {
    private final kp0 database;
    private final AtomicBoolean lock;
    private final i80 stmt$delegate;

    public pv0(kp0 kp0Var) {
        t40.n(kp0Var, "database");
        this.database = kp0Var;
        this.lock = new AtomicBoolean(false);
        this.stmt$delegate = z91.N(new v00(1, this));
    }

    public ky0 acquire() {
        assertNotMainThread();
        if (this.lock.compareAndSet(false, true)) {
            return (ky0) this.stmt$delegate.getValue();
        }
        return this.database.compileStatement(createQuery());
    }

    public void assertNotMainThread() {
        this.database.assertNotMainThread();
    }

    public abstract String createQuery();

    public void release(ky0 ky0Var) {
        t40.n(ky0Var, "statement");
        if (ky0Var == ((ky0) this.stmt$delegate.getValue())) {
            this.lock.set(false);
        }
    }
}
