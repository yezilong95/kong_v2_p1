package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.View;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import androidx.transition.Transition;
import androidx.work.impl.WorkDatabaseVersions;
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.sidesheet.SideSheetBehavior;
import com.google.android.material.transformation.ExpandableTransformationBehavior;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class d1 extends AnimatorListenerAdapter {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ d1(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
        switch (this.a) {
            case 0:
                ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) this.b;
                actionBarOverlayLayout.x = null;
                actionBarOverlayLayout.l = false;
                break;
            case 5:
                ((z7) this.b).d();
                break;
            case WorkDatabaseVersions.VERSION_12 /* 12 */:
                ((dt0) this.b).b();
                break;
            default:
                super.onAnimationCancel(animator);
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        switch (this.a) {
            case 0:
                ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) this.b;
                actionBarOverlayLayout.x = null;
                actionBarOverlayLayout.l = false;
                break;
            case 1:
                q3 q3Var = (q3) this.b;
                ArrayList arrayList = new ArrayList(q3Var.f);
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    ((d8) arrayList.get(i)).a(q3Var);
                }
                break;
            case 2:
                BottomSheetBehavior bottomSheetBehavior = (BottomSheetBehavior) this.b;
                bottomSheetBehavior.J(5);
                WeakReference weakReference = bottomSheetBehavior.V;
                if (weakReference != null && weakReference.get() != null) {
                    ((View) bottomSheetBehavior.V.get()).requestLayout();
                    break;
                }
                break;
            case 3:
                gm gmVar = (gm) this.b;
                gmVar.q();
                gmVar.r.start();
                break;
            case 4:
                ((ExpandableTransformationBehavior) this.b).c = null;
                break;
            case 5:
                ((z7) this.b).e();
                break;
            case 6:
                ((HideBottomViewOnScrollBehavior) this.b).j = null;
                break;
            case 7:
            default:
                super.onAnimationEnd(animator);
                break;
            case 8:
                a50 a50Var = (a50) this.b;
                a50Var.b.setTranslationY(0.0f);
                a50Var.c(0.0f);
                break;
            case WorkDatabaseVersions.VERSION_9 /* 9 */:
                SideSheetBehavior sideSheetBehavior = (SideSheetBehavior) this.b;
                sideSheetBehavior.x(5);
                WeakReference weakReference2 = sideSheetBehavior.q;
                if (weakReference2 != null && weakReference2.get() != null) {
                    ((View) sideSheetBehavior.q.get()).requestLayout();
                    break;
                }
                break;
            case WorkDatabaseVersions.VERSION_10 /* 10 */:
                po poVar = (po) this.b;
                if (((ValueAnimator) poVar.d) == animator) {
                    poVar.d = null;
                }
                break;
            case WorkDatabaseVersions.VERSION_11 /* 11 */:
                ((Transition) this.b).l();
                animator.removeListener(this);
                break;
            case WorkDatabaseVersions.VERSION_12 /* 12 */:
                ((dt0) this.b).a();
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationRepeat(Animator animator) {
        switch (this.a) {
            case 7:
                super.onAnimationRepeat(animator);
                a20 a20Var = (a20) this.b;
                a20Var.g = (a20Var.g + 1) % a20Var.f.c.length;
                a20Var.h = true;
                break;
            default:
                super.onAnimationRepeat(animator);
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        switch (this.a) {
            case 1:
                q3 q3Var = (q3) this.b;
                ArrayList arrayList = new ArrayList(q3Var.f);
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    ((d8) arrayList.get(i)).b(q3Var);
                }
                break;
            case 5:
                ((z7) this.b).f(animator);
                break;
            case WorkDatabaseVersions.VERSION_12 /* 12 */:
                ((dt0) this.b).c();
                break;
            default:
                super.onAnimationStart(animator);
                break;
        }
    }

    public d1(dt0 dt0Var, View view) {
        this.a = 12;
        this.b = dt0Var;
    }
}
