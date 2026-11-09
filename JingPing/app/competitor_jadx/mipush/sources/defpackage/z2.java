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
public final class z2 extends AnimatorListenerAdapter {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ z2(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
        switch (this.a) {
            case 0:
                ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) this.b;
                actionBarOverlayLayout.d0 = null;
                actionBarOverlayLayout.O = false;
                break;
            case 5:
                ((v9) this.b).d();
                break;
            case WorkDatabaseVersions.VERSION_13 /* 13 */:
                ((j61) this.b).b();
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
                actionBarOverlayLayout.d0 = null;
                actionBarOverlayLayout.O = false;
                break;
            case 1:
                v4 v4Var = (v4) this.b;
                ArrayList arrayList = new ArrayList(v4Var.I);
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    ((z9) arrayList.get(i)).a(v4Var);
                }
                break;
            case 2:
                BottomSheetBehavior bottomSheetBehavior = (BottomSheetBehavior) this.b;
                bottomSheetBehavior.J(5);
                WeakReference weakReference = bottomSheetBehavior.y0;
                if (weakReference != null && weakReference.get() != null) {
                    ((View) bottomSheetBehavior.y0.get()).requestLayout();
                    break;
                }
                break;
            case 3:
                jq jqVar = (jq) this.b;
                jqVar.q();
                jqVar.r.start();
                break;
            case 4:
                ((ExpandableTransformationBehavior) this.b).F = null;
                break;
            case 5:
                ((v9) this.b).e();
                break;
            case 6:
                ((HideBottomViewOnScrollBehavior) this.b).M = null;
                break;
            case 7:
            default:
                super.onAnimationEnd(animator);
                break;
            case 8:
                qc0 qc0Var = (qc0) this.b;
                qc0Var.b.setTranslationY(0.0f);
                qc0Var.c(0.0f);
                break;
            case WorkDatabaseVersions.VERSION_9 /* 9 */:
                View view = (View) this.b;
                if (view != null) {
                    view.setVisibility(0);
                }
                break;
            case 10:
                SideSheetBehavior sideSheetBehavior = (SideSheetBehavior) this.b;
                sideSheetBehavior.x(5);
                WeakReference weakReference2 = sideSheetBehavior.T;
                if (weakReference2 != null && weakReference2.get() != null) {
                    ((View) sideSheetBehavior.T.get()).requestLayout();
                    break;
                }
                break;
            case WorkDatabaseVersions.VERSION_11 /* 11 */:
                ou ouVar = (ou) this.b;
                if (((ValueAnimator) ouVar.G) == animator) {
                    ouVar.G = null;
                }
                break;
            case WorkDatabaseVersions.VERSION_12 /* 12 */:
                ((Transition) this.b).m();
                animator.removeListener(this);
                break;
            case WorkDatabaseVersions.VERSION_13 /* 13 */:
                ((j61) this.b).a();
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationRepeat(Animator animator) {
        switch (this.a) {
            case 7:
                super.onAnimationRepeat(animator);
                e90 e90Var = (e90) this.b;
                e90Var.f = (e90Var.f + 1) % e90Var.e.c.length;
                e90Var.g = true;
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
                v4 v4Var = (v4) this.b;
                ArrayList arrayList = new ArrayList(v4Var.I);
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    ((z9) arrayList.get(i)).b(v4Var);
                }
                break;
            case 5:
                ((v9) this.b).f(animator);
                break;
            case WorkDatabaseVersions.VERSION_13 /* 13 */:
                ((j61) this.b).e();
                break;
            default:
                super.onAnimationStart(animator);
                break;
        }
    }

    public z2(j61 j61Var, View view) {
        this.a = 13;
        this.b = j61Var;
    }
}
