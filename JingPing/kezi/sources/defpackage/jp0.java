package defpackage;

import android.view.ViewGroup;
import androidx.transition.AutoTransition;
import androidx.transition.R$id;
import androidx.transition.Transition;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class jp0 {
    public static final AutoTransition a = new AutoTransition();
    public static final ThreadLocal b = new ThreadLocal();
    public static final ArrayList c = new ArrayList();

    public static void a(ViewGroup viewGroup, Transition transition) {
        ArrayList arrayList = c;
        if (arrayList.contains(viewGroup)) {
            return;
        }
        WeakHashMap weakHashMap = xr0.a;
        if (viewGroup.isLaidOut()) {
            arrayList.add(viewGroup);
            if (transition == null) {
                transition = a;
            }
            Transition transitionClone = transition.clone();
            ArrayList arrayList2 = (ArrayList) b().getOrDefault(viewGroup, null);
            if (arrayList2 != null && arrayList2.size() > 0) {
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    ((Transition) it.next()).t(viewGroup);
                }
            }
            if (transitionClone != null) {
                transitionClone.g(viewGroup, true);
            }
            if (viewGroup.getTag(R$id.transition_current_scene) != null) {
                throw new ClassCastException();
            }
            viewGroup.setTag(R$id.transition_current_scene, null);
            if (transitionClone != null) {
                ip0 ip0Var = new ip0();
                ip0Var.b = transitionClone;
                ip0Var.c = viewGroup;
                viewGroup.addOnAttachStateChangeListener(ip0Var);
                viewGroup.getViewTreeObserver().addOnPreDrawListener(ip0Var);
            }
        }
    }

    public static c7 b() {
        c7 c7Var;
        ThreadLocal threadLocal = b;
        WeakReference weakReference = (WeakReference) threadLocal.get();
        if (weakReference != null && (c7Var = (c7) weakReference.get()) != null) {
            return c7Var;
        }
        c7 c7Var2 = new c7();
        threadLocal.set(new WeakReference(c7Var2));
        return c7Var2;
    }
}
