package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class pp extends AnimatorListenerAdapter {
    public final /* synthetic */ int a;
    public final /* synthetic */ qp b;

    public /* synthetic */ pp(qp qpVar, int i) {
        this.a = i;
        this.b = qpVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        switch (this.a) {
            case 1:
                super.onAnimationEnd(animator);
                qp qpVar = this.b;
                super/*android.graphics.drawable.Drawable*/.setVisible(false, false);
                ArrayList arrayList = qpVar.J;
                if (arrayList != null && !qpVar.K) {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        ((z9) it.next()).a(qpVar);
                    }
                    break;
                }
                break;
            default:
                super.onAnimationEnd(animator);
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        switch (this.a) {
            case 0:
                super.onAnimationStart(animator);
                qp qpVar = this.b;
                ArrayList arrayList = qpVar.J;
                if (arrayList != null && !qpVar.K) {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        ((z9) it.next()).b(qpVar);
                    }
                    break;
                }
                break;
            default:
                super.onAnimationStart(animator);
                break;
        }
    }
}
