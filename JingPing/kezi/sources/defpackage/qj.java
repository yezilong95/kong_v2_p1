package defpackage;

import android.animation.ValueAnimator;
import android.os.Handler;
import android.view.View;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class qj implements Runnable {
    public final /* synthetic */ int b = 1;
    public Object c;
    public Object d;
    public Object e;

    public /* synthetic */ qj() {
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object objCall;
        switch (this.b) {
            case 0:
                ArrayList arrayList = (ArrayList) this.c;
                zj0 zj0Var = (zj0) this.d;
                if (arrayList.contains(zj0Var)) {
                    arrayList.remove(zj0Var);
                    ((vj) this.e).getClass();
                    hk.a(zj0Var.c.F, zj0Var.a);
                }
                break;
            case 1:
                try {
                    objCall = ((rs) this.c).call();
                } catch (Exception unused) {
                    objCall = null;
                }
                ((Handler) this.e).post(new m1((ss) this.d, objCall, 7, false));
                break;
            default:
                fu0.h((View) this.c, (e5) this.d);
                ((ValueAnimator) this.e).start();
                break;
        }
    }

    public qj(vj vjVar, ArrayList arrayList, zj0 zj0Var) {
        this.e = vjVar;
        this.c = arrayList;
        this.d = zj0Var;
    }

    public qj(View view, ju0 ju0Var, e5 e5Var, ValueAnimator valueAnimator) {
        this.c = view;
        this.d = e5Var;
        this.e = valueAnimator;
    }
}
