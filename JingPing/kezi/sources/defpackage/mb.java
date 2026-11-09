package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Matrix;
import android.view.View;
import androidx.transition.ChangeTransform;
import androidx.transition.R$id;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class mb extends AnimatorListenerAdapter {
    public boolean a;
    public final Matrix b = new Matrix();
    public final /* synthetic */ boolean c;
    public final /* synthetic */ Matrix d;
    public final /* synthetic */ View e;
    public final /* synthetic */ pb f;
    public final /* synthetic */ ob g;
    public final /* synthetic */ ChangeTransform h;

    public mb(ChangeTransform changeTransform, boolean z, Matrix matrix, View view, pb pbVar, ob obVar) {
        this.h = changeTransform;
        this.c = z;
        this.d = matrix;
        this.e = view;
        this.f = pbVar;
        this.g = obVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        this.a = true;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        boolean z = this.a;
        pb pbVar = this.f;
        View view = this.e;
        if (!z) {
            if (this.c && this.h.y) {
                Matrix matrix = this.d;
                Matrix matrix2 = this.b;
                matrix2.set(matrix);
                view.setTag(R$id.transition_transform, matrix2);
                pbVar.getClass();
                String[] strArr = ChangeTransform.B;
                view.setTranslationX(pbVar.a);
                view.setTranslationY(pbVar.b);
                WeakHashMap weakHashMap = xr0.a;
                lr0.w(view, pbVar.c);
                view.setScaleX(pbVar.d);
                view.setScaleY(pbVar.e);
                view.setRotationX(pbVar.f);
                view.setRotationY(pbVar.g);
                view.setRotation(pbVar.h);
            } else {
                view.setTag(R$id.transition_transform, null);
                view.setTag(R$id.parent_matrix, null);
            }
        }
        jt0.a.u0(view, null);
        pbVar.getClass();
        String[] strArr2 = ChangeTransform.B;
        view.setTranslationX(pbVar.a);
        view.setTranslationY(pbVar.b);
        WeakHashMap weakHashMap2 = xr0.a;
        lr0.w(view, pbVar.c);
        view.setScaleX(pbVar.d);
        view.setScaleY(pbVar.e);
        view.setRotationX(pbVar.f);
        view.setRotationY(pbVar.g);
        view.setRotation(pbVar.h);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
    public final void onAnimationPause(Animator animator) {
        Matrix matrix = this.g.a;
        Matrix matrix2 = this.b;
        matrix2.set(matrix);
        int i = R$id.transition_transform;
        View view = this.e;
        view.setTag(i, matrix2);
        pb pbVar = this.f;
        pbVar.getClass();
        String[] strArr = ChangeTransform.B;
        view.setTranslationX(pbVar.a);
        view.setTranslationY(pbVar.b);
        WeakHashMap weakHashMap = xr0.a;
        lr0.w(view, pbVar.c);
        view.setScaleX(pbVar.d);
        view.setScaleY(pbVar.e);
        view.setRotationX(pbVar.f);
        view.setRotationY(pbVar.g);
        view.setRotation(pbVar.h);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
    public final void onAnimationResume(Animator animator) {
        String[] strArr = ChangeTransform.B;
        View view = this.e;
        view.setTranslationX(0.0f);
        view.setTranslationY(0.0f);
        WeakHashMap weakHashMap = xr0.a;
        lr0.w(view, 0.0f);
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
        view.setRotationX(0.0f);
        view.setRotationY(0.0f);
        view.setRotation(0.0f);
    }
}
