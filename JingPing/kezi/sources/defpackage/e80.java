package defpackage;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class e80 implements Runnable {
    public final /* synthetic */ int b;
    public final /* synthetic */ f80 c;
    public final /* synthetic */ Context d;
    public final /* synthetic */ int e;

    public /* synthetic */ e80(f80 f80Var, Context context, int i, int i2) {
        this.b = i2;
        this.c = f80Var;
        this.d = context;
        this.e = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.b) {
            case 0:
                this.c.e.e(this.d, this.e + 1);
                break;
            case 1:
                this.c.e.e(this.d, this.e + 1);
                break;
            default:
                this.c.e.e(this.d, this.e + 1);
                break;
        }
    }
}
