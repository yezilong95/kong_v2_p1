package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import androidx.appcompat.widget.ScrollingTabContainerView;
import com.google.android.material.internal.ClippableRoundedCornerLayout;
import com.google.android.material.search.g;

/* JADX INFO: loaded from: classes.dex */
public final class ev extends AnimatorListenerAdapter {
    public final /* synthetic */ int a;
    public boolean b;
    public final /* synthetic */ Object c;

    public /* synthetic */ ev(int i, Object obj) {
        this.a = i;
        this.c = obj;
        this.b = false;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
        switch (this.a) {
            case 1:
                this.b = true;
                break;
            case 2:
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
                    ((View) this.c).setVisibility(4);
                }
                break;
            case 1:
                if (!this.b) {
                    vv vvVar = (vv) this.c;
                    if (((Float) vvVar.z.getAnimatedValue()).floatValue() != 0.0f) {
                        vvVar.A = 2;
                        vvVar.s.invalidate();
                    } else {
                        vvVar.A = 0;
                        vvVar.g(0);
                    }
                } else {
                    this.b = false;
                }
                break;
            case 2:
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
                clippableRoundedCornerLayout.E = null;
                clippableRoundedCornerLayout.F = 0.0f;
                clippableRoundedCornerLayout.invalidate();
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        switch (this.a) {
            case 0:
                if (this.b) {
                    ((View) this.c).setVisibility(0);
                }
                break;
            case 1:
            default:
                super.onAnimationStart(animator);
                break;
            case 2:
                ((ScrollingTabContainerView) this.c).setVisibility(0);
                this.b = false;
                break;
            case 3:
                g.a((g) this.c, this.b ? 0.0f : 1.0f);
                break;
        }
    }

    public ev(View view, boolean z) {
        this.a = 0;
        this.b = z;
        this.c = view;
    }

    public ev(g gVar, boolean z) {
        this.a = 3;
        this.c = gVar;
        this.b = z;
    }
}
