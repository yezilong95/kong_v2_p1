package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import androidx.appcompat.widget.ScrollingTabContainerView;
import com.google.android.material.internal.ClippableRoundedCornerLayout;
import com.google.android.material.search.g;

/* JADX INFO: loaded from: classes.dex */
public final class pp extends AnimatorListenerAdapter {
    public final /* synthetic */ int a;
    public boolean b;
    public final /* synthetic */ Object c;

    public /* synthetic */ pp(int i, Object obj) {
        this.a = i;
        this.c = obj;
        this.b = false;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
        switch (this.a) {
            case 0:
                this.b = true;
                break;
            case 1:
                this.b = true;
                break;
            default:
                super.onAnimationCancel(animator);
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        switch (this.a) {
            case 0:
                if (!this.b) {
                    qp qpVar = (qp) this.c;
                    if (((Float) qpVar.z.getAnimatedValue()).floatValue() != 0.0f) {
                        qpVar.A = 2;
                        qpVar.s.invalidate();
                    } else {
                        qpVar.A = 0;
                        qpVar.g(0);
                    }
                } else {
                    this.b = false;
                }
                break;
            case 1:
                if (!this.b) {
                    ((ScrollingTabContainerView) this.c).setVisibility(0);
                    break;
                }
                break;
            default:
                float f = this.b ? 1.0f : 0.0f;
                g gVar = (g) this.c;
                g.a(gVar, f);
                ClippableRoundedCornerLayout clippableRoundedCornerLayout = gVar.c;
                clippableRoundedCornerLayout.b = null;
                clippableRoundedCornerLayout.c = 0.0f;
                clippableRoundedCornerLayout.invalidate();
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        switch (this.a) {
            case 1:
                ((ScrollingTabContainerView) this.c).setVisibility(0);
                this.b = false;
                break;
            case 2:
                g.a((g) this.c, this.b ? 0.0f : 1.0f);
                break;
            default:
                super.onAnimationStart(animator);
                break;
        }
    }

    public pp(g gVar, boolean z) {
        this.a = 2;
        this.c = gVar;
        this.b = z;
    }
}
