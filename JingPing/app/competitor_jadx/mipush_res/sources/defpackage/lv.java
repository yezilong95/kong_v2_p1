package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class lv implements Runnable {
    public final /* synthetic */ int E;
    public final /* synthetic */ pv F;

    public /* synthetic */ lv(pv pvVar, int i) {
        this.E = i;
        this.F = pvVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.E) {
            case 0:
                this.F.d();
                break;
            default:
                this.F.f();
                break;
        }
    }
}
