package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class ml extends AnimatorListenerAdapter {
    public final /* synthetic */ int a;
    public final /* synthetic */ nl b;

    public /* synthetic */ ml(nl nlVar, int i) {
        this.a = i;
        this.b = nlVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        switch (this.a) {
            case 1:
                super.onAnimationEnd(animator);
                nl nlVar = this.b;
                super/*android.graphics.drawable.Drawable*/.setVisible(false, false);
                ArrayList arrayList = nlVar.g;
                if (arrayList != null && !nlVar.h) {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        ((d8) it.next()).a(nlVar);
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
                nl nlVar = this.b;
                ArrayList arrayList = nlVar.g;
                if (arrayList != null && !nlVar.h) {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        ((d8) it.next()).b(nlVar);
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
