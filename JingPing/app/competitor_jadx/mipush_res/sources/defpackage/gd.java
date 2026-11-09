package defpackage;

import android.os.Build;
import android.util.Log;
import android.view.View;
import androidx.transition.R$id;
import androidx.transition.Transition;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class gd extends t11 {
    public final /* synthetic */ int a = 0;
    public Object b;
    public Object c;

    public /* synthetic */ gd() {
    }

    @Override // defpackage.t11, defpackage.s11
    public void b() {
        switch (this.a) {
            case 0:
                ((e20) this.c).setVisibility(4);
                break;
        }
    }

    @Override // defpackage.t11, defpackage.s11
    public void e() {
        switch (this.a) {
            case 0:
                ((e20) this.c).setVisibility(0);
                break;
        }
    }

    @Override // defpackage.s11
    public final void g(Transition transition) {
        switch (this.a) {
            case 0:
                transition.x(this);
                int i = Build.VERSION.SDK_INT;
                View view = (View) this.b;
                if (i == 28) {
                    if (!z8.N) {
                        try {
                            z8.w();
                            Method declaredMethod = z8.I.getDeclaredMethod("removeGhost", View.class);
                            z8.M = declaredMethod;
                            declaredMethod.setAccessible(true);
                        } catch (NoSuchMethodException e) {
                            Log.i("GhostViewApi21", "Failed to retrieve removeGhost method", e);
                        }
                        z8.N = true;
                    }
                    Method method = z8.M;
                    if (method != null) {
                        try {
                            method.invoke(null, view);
                            break;
                        } catch (IllegalAccessException unused) {
                        } catch (InvocationTargetException e2) {
                            throw new RuntimeException(e2.getCause());
                        }
                    }
                    break;
                } else {
                    int i2 = h20.K;
                    h20 h20Var = (h20) view.getTag(R$id.ghost_view);
                    if (h20Var != null) {
                        int i3 = h20Var.H - 1;
                        h20Var.H = i3;
                        if (i3 <= 0) {
                            ((g20) h20Var.getParent()).removeView(h20Var);
                        }
                    }
                }
                view.setTag(R$id.transition_transform, null);
                view.setTag(R$id.parent_matrix, null);
                return;
            default:
                ((ArrayList) ((k8) this.b).getOrDefault(((u11) this.c).F, null)).remove(transition);
                transition.x(this);
                return;
        }
    }

    public gd(u11 u11Var, k8 k8Var) {
        this.c = u11Var;
        this.b = k8Var;
    }
}
