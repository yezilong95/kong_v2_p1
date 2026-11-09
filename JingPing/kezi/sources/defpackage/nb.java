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
public final class nb extends hp0 {
    public final /* synthetic */ int a = 0;
    public Object b;
    public Object c;

    public /* synthetic */ nb() {
    }

    @Override // defpackage.hp0, defpackage.gp0
    public void c() {
        switch (this.a) {
            case 0:
                ((ev) this.c).setVisibility(4);
                break;
        }
    }

    @Override // defpackage.gp0
    public final void d(Transition transition) {
        switch (this.a) {
            case 0:
                transition.u(this);
                int i = Build.VERSION.SDK_INT;
                View view = (View) this.b;
                if (i == 28) {
                    if (!gv.h) {
                        try {
                            gv.b();
                            Method declaredMethod = gv.c.getDeclaredMethod("removeGhost", View.class);
                            gv.g = declaredMethod;
                            declaredMethod.setAccessible(true);
                        } catch (NoSuchMethodException e) {
                            Log.i("GhostViewApi21", "Failed to retrieve removeGhost method", e);
                        }
                        gv.h = true;
                    }
                    Method method = gv.g;
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
                    int i2 = hv.h;
                    hv hvVar = (hv) view.getTag(R$id.ghost_view);
                    if (hvVar != null) {
                        int i3 = hvVar.e - 1;
                        hvVar.e = i3;
                        if (i3 <= 0) {
                            ((fv) hvVar.getParent()).removeView(hvVar);
                        }
                    }
                }
                view.setTag(R$id.transition_transform, null);
                view.setTag(R$id.parent_matrix, null);
                return;
            default:
                ((ArrayList) ((c7) this.b).getOrDefault(((ip0) this.c).c, null)).remove(transition);
                transition.u(this);
                return;
        }
    }

    @Override // defpackage.hp0, defpackage.gp0
    public void e() {
        switch (this.a) {
            case 0:
                ((ev) this.c).setVisibility(0);
                break;
        }
    }

    public nb(ip0 ip0Var, c7 c7Var) {
        this.c = ip0Var;
        this.b = c7Var;
    }
}
