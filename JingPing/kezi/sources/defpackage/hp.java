package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class hp extends AnimatorListenerAdapter {
    public final /* synthetic */ int a;
    public final View b;
    public boolean c;

    public hp(View view, boolean z) {
        this.a = 0;
        this.c = z;
        this.b = view;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        switch (this.a) {
            case 0:
                if (!this.c) {
                    this.b.setVisibility(4);
                }
                break;
            default:
                View view = this.b;
                jt0.b(view, 1.0f);
                if (this.c) {
                    view.setLayerType(0, null);
                }
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        View view = this.b;
        switch (this.a) {
            case 0:
                if (this.c) {
                    view.setVisibility(0);
                }
                break;
            default:
                WeakHashMap weakHashMap = xr0.a;
                if (view.hasOverlappingRendering() && view.getLayerType() == 0) {
                    this.c = true;
                    view.setLayerType(2, null);
                    break;
                }
                break;
        }
    }

    public hp(View view) {
        this.a = 1;
        this.c = false;
        this.b = view;
    }
}
