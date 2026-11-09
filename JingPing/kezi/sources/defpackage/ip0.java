package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.transition.Transition;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class ip0 implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {
    public Transition b;
    public ViewGroup c;

    /* JADX WARN: Removed duplicated region for block: B:102:0x0217  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0243  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01ef A[EDGE_INSN: B:137:0x01ef->B:92:0x01ef BREAK  A[LOOP:1: B:19:0x0088->B:91:0x01e5], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x01f6  */
    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onPreDraw() {
        /*
            Method dump skipped, instruction units count: 700
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ip0.onPreDraw():boolean");
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        ViewGroup viewGroup = this.c;
        viewGroup.getViewTreeObserver().removeOnPreDrawListener(this);
        viewGroup.removeOnAttachStateChangeListener(this);
        ArrayList arrayList = jp0.c;
        ViewGroup viewGroup2 = this.c;
        arrayList.remove(viewGroup2);
        ArrayList arrayList2 = (ArrayList) jp0.b().getOrDefault(viewGroup2, null);
        if (arrayList2 != null && arrayList2.size() > 0) {
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                ((Transition) it.next()).v(viewGroup2);
            }
        }
        this.b.h(true);
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
    }
}
