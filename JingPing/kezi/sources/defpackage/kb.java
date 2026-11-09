package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class kb extends AnimatorListenerAdapter {
    public final /* synthetic */ int a;
    public final /* synthetic */ View b;

    public /* synthetic */ kb(View view, int i) {
        this.a = i;
        this.b = view;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        View view = this.b;
        switch (this.a) {
            case 0:
                WeakHashMap weakHashMap = xr0.a;
                view.setClipBounds(null);
                break;
            default:
                if (view != null) {
                    view.setVisibility(0);
                }
                break;
        }
    }
}
