package defpackage;

import com.mamimobi.AccessService;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class s1 implements Runnable {
    public final /* synthetic */ int E;
    public final /* synthetic */ int F;
    public final /* synthetic */ int G;

    public /* synthetic */ s1(int i, int i2, int i3) {
        this.E = i3;
        this.F = i;
        this.G = i2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.E) {
            case 0:
                d91.a(AccessService.e0, this.F, this.G);
                break;
            case 1:
                d91.a(AccessService.e0, this.F, this.G);
                break;
            case 2:
                if (d91.b(AccessService.e0, this.F, this.G)) {
                    AccessService.e0.B();
                    AccessService.e0.B();
                    uj0.a.set(true);
                }
                break;
            case 3:
                d91.a(AccessService.e0, this.F, this.G);
                break;
            case 4:
                d91.a(AccessService.e0, this.F, this.G);
                break;
            case 5:
                d91.a(AccessService.e0, this.F, this.G);
                break;
            case 6:
                d91.a(AccessService.e0, this.F, this.G);
                break;
            case 7:
                d91.a(AccessService.e0, this.F, this.G);
                break;
            case 8:
                d91.a(AccessService.e0, this.F, this.G);
                break;
            default:
                d91.a(AccessService.e0, this.F, this.G);
                break;
        }
    }
}
