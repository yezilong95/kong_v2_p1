package defpackage;

import android.content.Context;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public final class rs implements Callable {
    public final /* synthetic */ int a;
    public final /* synthetic */ String b;
    public final /* synthetic */ Context c;
    public final /* synthetic */ o2 d;
    public final /* synthetic */ int e;

    public /* synthetic */ rs(String str, Context context, o2 o2Var, int i, int i2) {
        this.a = i2;
        this.b = str;
        this.c = context;
        this.d = o2Var;
        this.e = i;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        switch (this.a) {
            case 0:
                return us.a(this.b, this.c, this.d, this.e);
            default:
                try {
                    return us.a(this.b, this.c, this.d, this.e);
                } catch (Throwable unused) {
                    return new ts(-3);
                }
        }
    }
}
