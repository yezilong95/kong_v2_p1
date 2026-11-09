package defpackage;

import android.app.Application;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import androidx.appcompat.widget.ScrollingTabContainerView;
import androidx.appcompat.widget.b;
import com.google.android.material.behavior.SwipeDismissBehavior;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.coroutines.EmptyCoroutineContext;

/* JADX INFO: loaded from: classes.dex */
public final class m1 implements Runnable {
    public final /* synthetic */ int b;
    public Object c;
    public final /* synthetic */ Object d;

    public /* synthetic */ m1(Object obj, int i, Object obj2) {
        this.b = i;
        this.d = obj;
        this.c = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() throws IllegalAccessException, InvocationTargetException {
        y10 y10Var;
        g60 g60Var;
        int i = 0;
        Object obj = this.d;
        switch (this.b) {
            case 0:
                b bVar = (b) obj;
                i60 i60Var = bVar.d;
                if (i60Var != null && (g60Var = i60Var.e) != null) {
                    g60Var.i(i60Var);
                }
                View view = (View) bVar.i;
                if (view != null && view.getWindowToken() != null) {
                    k1 k1Var = (k1) this.c;
                    if (k1Var.b()) {
                        bVar.u = k1Var;
                    } else if (k1Var.e != null) {
                        k1Var.d(0, 0, false, false);
                        bVar.u = k1Var;
                    }
                }
                bVar.w = null;
                return;
            case 1:
                ((c2) this.c).a = obj;
                return;
            case 2:
                ((Application) this.c).unregisterActivityLifecycleCallbacks((c2) obj);
                return;
            case 3:
                try {
                    Method method = d2.d;
                    Object obj2 = this.c;
                    if (method != null) {
                        method.invoke(obj2, obj, Boolean.FALSE, "AppCompat recreation");
                    } else {
                        d2.e.invoke(obj2, obj, Boolean.FALSE);
                    }
                    return;
                } catch (RuntimeException e) {
                    if (e.getClass() == RuntimeException.class && e.getMessage() != null && e.getMessage().startsWith("Unable to stop")) {
                        throw e;
                    }
                    return;
                } catch (Throwable th) {
                    Log.e("ActivityRecreator", "Exception while invoking performStopActivity", th);
                    return;
                }
            case 4:
                rp0 rp0Var = (rp0) ((d90) this.c).b;
                if (rp0Var != null) {
                    rp0Var.M((Typeface) obj);
                    return;
                }
                return;
            case 5:
                po poVar = (po) obj;
                Iterator it = ((ConcurrentHashMap) poVar.c).keySet().iterator();
                while (it.hasNext()) {
                    List list = (List) ((ConcurrentHashMap) poVar.c).get(it.next());
                    if (list != null && !list.isEmpty()) {
                        Iterator it2 = list.iterator();
                        while (it2.hasNext()) {
                            if (it2.next() != null) {
                                throw new ClassCastException();
                            }
                            try {
                                throw null;
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                    }
                }
                return;
            case 6:
                break;
            case 7:
                ((ss) this.c).a(obj);
                return;
            case 8:
                View view2 = (View) this.c;
                ScrollingTabContainerView scrollingTabContainerView = (ScrollingTabContainerView) obj;
                scrollingTabContainerView.smoothScrollTo(view2.getLeft() - ((scrollingTabContainerView.getWidth() - view2.getWidth()) / 2), 0);
                scrollingTabContainerView.b = null;
                return;
            default:
                cs0 cs0Var = ((SwipeDismissBehavior) obj).b;
                if (cs0Var == null || !cs0Var.g()) {
                    return;
                }
                WeakHashMap weakHashMap = xr0.a;
                ((View) this.c).postOnAnimation(this);
                return;
        }
        do {
            try {
                ((Runnable) this.c).run();
            } catch (Throwable th2) {
                rp0.E(EmptyCoroutineContext.INSTANCE, th2);
            }
            y10Var = (y10) obj;
            Runnable runnableE = y10Var.E();
            if (runnableE == null) {
                return;
            }
            this.c = runnableE;
            i++;
        } while (i < 16);
        cq0 cq0Var = y10Var.d;
        cq0Var.getClass();
        cq0Var.t(y10Var, this);
    }

    public /* synthetic */ m1(Object obj, Object obj2, int i, boolean z) {
        this.b = i;
        this.c = obj;
        this.d = obj2;
    }

    public m1(SwipeDismissBehavior swipeDismissBehavior, View view, boolean z) {
        this.b = 9;
        this.d = swipeDismissBehavior;
        this.c = view;
    }
}
