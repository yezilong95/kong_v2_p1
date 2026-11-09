package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class y7 implements Runnable {
    public final /* synthetic */ int E;
    public final /* synthetic */ a8 F;
    public final /* synthetic */ String G;
    public final /* synthetic */ String H;

    public /* synthetic */ y7(a8 a8Var, String str, String str2, int i) {
        this.E = i;
        this.F = a8Var;
        this.G = str;
        this.H = str2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.E) {
            case 0:
                this.F.g(this.G, this.H);
                break;
            default:
                this.F.g(this.G, this.H);
                break;
        }
    }
}
