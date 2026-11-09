package defpackage;

import android.animation.ValueAnimator;
import android.os.Handler;
import android.view.View;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class jn implements Runnable {
    public final /* synthetic */ int E = 1;
    public Object F;
    public Object G;
    public Object H;

    public /* synthetic */ jn() {
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object objCall;
        switch (this.E) {
            case 0:
                ArrayList arrayList = (ArrayList) this.F;
                nw0 nw0Var = (nw0) this.G;
                if (arrayList.contains(nw0Var)) {
                    arrayList.remove(nw0Var);
                    ((on) this.H).getClass();
                    zn.a(nw0Var.c.i0, nw0Var.a);
                }
                break;
            case 1:
                try {
                    objCall = ((fz) this.F).call();
                } catch (Exception unused) {
                    objCall = null;
                }
                ((Handler) this.H).post(new h3(7, (gz) this.G, objCall, false));
                break;
            default:
                a81.h((View) this.F, (n41) this.G);
                ((ValueAnimator) this.H).start();
                break;
        }
    }

    public jn(on onVar, ArrayList arrayList, nw0 nw0Var) {
        this.H = onVar;
        this.F = arrayList;
        this.G = nw0Var;
    }

    public jn(View view, e81 e81Var, n41 n41Var, ValueAnimator valueAnimator) {
        this.F = view;
        this.G = n41Var;
        this.H = valueAnimator;
    }
}
