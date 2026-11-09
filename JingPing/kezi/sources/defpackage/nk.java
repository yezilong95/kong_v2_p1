package defpackage;

import android.content.Context;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class nk implements Runnable {
    public final /* synthetic */ int b = 1;
    public final /* synthetic */ int c;
    public final /* synthetic */ Object d;
    public final /* synthetic */ Object e;

    public /* synthetic */ nk(ok okVar, int i, Serializable serializable) {
        this.d = okVar;
        this.c = i;
        this.e = serializable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.b) {
            case 0:
                ((ok) this.d).b.b(this.c, (Serializable) this.e);
                break;
            default:
                ((g80) this.d).e((Context) this.e, this.c + 1);
                break;
        }
    }

    public /* synthetic */ nk(g80 g80Var, Context context, int i) {
        this.d = g80Var;
        this.e = context;
        this.c = i;
    }
}
