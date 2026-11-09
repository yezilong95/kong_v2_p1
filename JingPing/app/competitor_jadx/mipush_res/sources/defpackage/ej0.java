package defpackage;

import android.os.Build;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.fragment.app.c;
import java.util.Iterator;
import java.util.ListIterator;

/* JADX INFO: loaded from: classes.dex */
public final class ej0 {
    public final Runnable a;
    public final h8 b = new h8();
    public a00 c;
    public final OnBackInvokedCallback d;
    public OnBackInvokedDispatcher e;
    public boolean f;
    public boolean g;

    public ej0(Runnable runnable) {
        this.a = runnable;
        int i = Build.VERSION.SDK_INT;
        if (i >= 33) {
            this.d = i >= 34 ? aj0.a.a(new wi0(this, 0), new wi0(this, 1), new xi0(this, 0), new xi0(this, 1)) : yi0.a.a(new xi0(this, 2));
        }
    }

    public final void a() {
        Object objPrevious;
        h8 h8Var = this.b;
        ListIterator listIterator = h8Var.listIterator(h8Var.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                objPrevious = null;
                break;
            } else {
                objPrevious = listIterator.previous();
                if (((a00) objPrevious).a) {
                    break;
                }
            }
        }
        a00 a00Var = (a00) objPrevious;
        this.c = null;
        if (a00Var == null) {
            this.a.run();
            return;
        }
        c cVar = a00Var.d;
        cVar.t(true);
        if (cVar.h.a) {
            cVar.H();
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
        yi0 yi0Var = yi0.a;
        if (z && !this.f) {
            yi0Var.b(onBackInvokedDispatcher, 0, onBackInvokedCallback);
            this.f = true;
        } else {
            if (z || !this.f) {
                return;
            }
            yi0Var.c(onBackInvokedDispatcher, onBackInvokedCallback);
            this.f = false;
        }
    }

    public final void c() {
        boolean z = this.g;
        boolean z2 = false;
        h8 h8Var = this.b;
        if (h8Var == null || !h8Var.isEmpty()) {
            Iterator it = h8Var.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (((a00) it.next()).a) {
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
