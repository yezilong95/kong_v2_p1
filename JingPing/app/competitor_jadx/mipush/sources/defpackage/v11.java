package defpackage;

import android.view.ViewGroup;
import androidx.transition.AutoTransition;
import androidx.transition.R$id;
import androidx.transition.Transition;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public abstract class v11 {
    public static final AutoTransition a = new AutoTransition();
    public static final ThreadLocal b = new ThreadLocal();
    public static final ArrayList c = new ArrayList();

    public static void a(ViewGroup viewGroup, Transition transition) {
        ArrayList arrayList = c;
        if (arrayList.contains(viewGroup) || !viewGroup.isLaidOut()) {
            return;
        }
        arrayList.add(viewGroup);
        if (transition == null) {
            transition = a;
        }
        Transition transitionClone = transition.clone();
        ArrayList arrayList2 = (ArrayList) b().getOrDefault(viewGroup, null);
        if (arrayList2 != null && arrayList2.size() > 0) {
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                ((Transition) it.next()).w(viewGroup);
            }
        }
        transitionClone.h(viewGroup, true);
        if (viewGroup.getTag(R$id.transition_current_scene) != null) {
            throw new ClassCastException();
        }
        viewGroup.setTag(R$id.transition_current_scene, null);
        u11 u11Var = new u11();
        u11Var.E = transitionClone;
        u11Var.F = viewGroup;
        viewGroup.addOnAttachStateChangeListener(u11Var);
        viewGroup.getViewTreeObserver().addOnPreDrawListener(u11Var);
    }

    public static k8 b() {
        k8 k8Var;
        ThreadLocal threadLocal = b;
        WeakReference weakReference = (WeakReference) threadLocal.get();
        if (weakReference != null && (k8Var = (k8) weakReference.get()) != null) {
            return k8Var;
        }
        k8 k8Var2 = new k8();
        threadLocal.set(new WeakReference(k8Var2));
        return k8Var2;
    }
}
