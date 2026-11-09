package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class wb0 extends vb0 {
    public final Object d;

    public wb0(int i) {
        super(i);
        this.d = new Object();
    }

    @Override // defpackage.vb0
    public final Object a() {
        Object objA;
        synchronized (this.d) {
            objA = super.a();
        }
        return objA;
    }

    @Override // defpackage.vb0
    public final boolean c(Object obj) {
        boolean zC;
        synchronized (this.d) {
            zC = super.c(obj);
        }
        return zC;
    }
}
