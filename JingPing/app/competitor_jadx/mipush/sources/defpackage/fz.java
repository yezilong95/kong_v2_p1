package defpackage;

import android.content.Context;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public final class fz implements Callable {
    public final /* synthetic */ int a;
    public final /* synthetic */ String b;
    public final /* synthetic */ Context c;
    public final /* synthetic */ c4 d;
    public final /* synthetic */ int e;

    public /* synthetic */ fz(String str, Context context, c4 c4Var, int i, int i2) {
        this.a = i2;
        this.b = str;
        this.c = context;
        this.d = c4Var;
        this.e = i;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        switch (this.a) {
            case 0:
                return iz.a(this.b, this.c, this.d, this.e);
            default:
                try {
                    return iz.a(this.b, this.c, this.d, this.e);
                } catch (Throwable unused) {
                    return new hz(-3);
                }
        }
    }
}
