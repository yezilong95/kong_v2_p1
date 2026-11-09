package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.transition.Transition;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class u11 implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {
    public Transition E;
    public ViewGroup F;

    /* JADX WARN: Removed duplicated region for block: B:103:0x0228  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0252  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x01ff A[EDGE_INSN: B:138:0x01ff->B:93:0x01ff BREAK  A[LOOP:1: B:19:0x0088->B:92:0x01f4], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0207  */
    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onPreDraw() {
        /*
            Method dump skipped, instruction units count: 720
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.u11.onPreDraw():boolean");
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        ViewGroup viewGroup = this.F;
        viewGroup.getViewTreeObserver().removeOnPreDrawListener(this);
        viewGroup.removeOnAttachStateChangeListener(this);
        ArrayList arrayList = v11.c;
        ViewGroup viewGroup2 = this.F;
        arrayList.remove(viewGroup2);
        ArrayList arrayList2 = (ArrayList) v11.b().getOrDefault(viewGroup2, null);
        if (arrayList2 != null && arrayList2.size() > 0) {
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                ((Transition) it.next()).y(viewGroup2);
            }
        }
        this.E.i(true);
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
    }
}
