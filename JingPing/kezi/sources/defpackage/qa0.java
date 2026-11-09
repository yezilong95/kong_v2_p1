package defpackage;

import android.os.Build;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.fragment.app.c;
import java.util.Iterator;
import java.util.ListIterator;

/* JADX INFO: loaded from: classes.dex */
public final class qa0 {
    public final Runnable a;
    public final z6 b = new z6();
    public ot c;
    public final OnBackInvokedCallback d;
    public OnBackInvokedDispatcher e;
    public boolean f;
    public boolean g;

    public qa0(Runnable runnable) {
        this.a = runnable;
        int i = Build.VERSION.SDK_INT;
        if (i >= 33) {
            this.d = i >= 34 ? ma0.a.a(new ia0(this, 0), new ia0(this, 1), new ja0(this, 0), new ja0(this, 1)) : ka0.a.a(new ja0(this, 2));
        }
    }

    public final void a() {
        Object objPrevious;
        z6 z6Var = this.b;
        ListIterator listIterator = z6Var.listIterator(z6Var.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                objPrevious = null;
                break;
            } else {
                objPrevious = listIterator.previous();
                if (((ot) objPrevious).a) {
                    break;
                }
            }
        }
        ot otVar = (ot) objPrevious;
        this.c = null;
        if (otVar == null) {
            this.a.run();
            return;
        }
        c cVar = otVar.d;
        cVar.u(true);
        if (cVar.h.a) {
            cVar.I();
        } else {
            cVar.g.a();
        }
    }

    public final void b(boolean z) {
        OnBackInvokedDispatcher onBackInvokedDispatcher = this.e;
        OnBackInvokedCallback onBackInvokedCallback = this.d;
        if (onBackInvokedDispatcher == null || onBackInvokedCallback == null) {
            return;
        }
        ka0 ka0Var = ka0.a;
        if (z && !this.f) {
            ka0Var.b(onBackInvokedDispatcher, 0, onBackInvokedCallback);
            this.f = true;
        } else {
            if (z || !this.f) {
                return;
            }
            ka0Var.c(onBackInvokedDispatcher, onBackInvokedCallback);
            this.f = false;
        }
    }

    public final void c() {
        boolean z = this.g;
        boolean z2 = false;
        z6 z6Var = this.b;
        if (z6Var == null || !z6Var.isEmpty()) {
            Iterator it = z6Var.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (((ot) it.next()).a) {
                    z2 = true;
                    break;
                }
            }
        }
        this.g = z2;
        if (z2 == z || Build.VERSION.SDK_INT < 33) {
            return;
        }
        b(z2);
    }
}
