package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Matrix;
import android.view.View;
import androidx.transition.ChangeTransform;
import androidx.transition.R$id;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class hd extends AnimatorListenerAdapter {
    public boolean a;
    public final Matrix b = new Matrix();
    public final boolean c;
    public final boolean d;
    public final View e;
    public final jd f;
    public final id g;
    public final Matrix h;

    public hd(View view, jd jdVar, id idVar, Matrix matrix, boolean z, boolean z2) {
        this.c = z;
        this.d = z2;
        this.e = view;
        this.f = jdVar;
        this.g = idVar;
        this.h = matrix;
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
        jd jdVar = this.f;
        View view = this.e;
        if (!z) {
            if (this.c && this.d) {
                Matrix matrix = this.b;
                matrix.set(this.h);
                view.setTag(R$id.transition_transform, matrix);
                jdVar.getClass();
                String[] strArr = ChangeTransform.i0;
                view.setTranslationX(jdVar.a);
                view.setTranslationY(jdVar.b);
                WeakHashMap weakHashMap = f51.a;
                t41.w(view, jdVar.c);
                view.setScaleX(jdVar.d);
                view.setScaleY(jdVar.e);
                view.setRotationX(jdVar.f);
                view.setRotationY(jdVar.g);
                view.setRotation(jdVar.h);
            } else {
                view.setTag(R$id.transition_transform, null);
                view.setTag(R$id.parent_matrix, null);
            }
        }
        p61.a.n0(view, null);
        jdVar.getClass();
        String[] strArr2 = ChangeTransform.i0;
        view.setTranslationX(jdVar.a);
        view.setTranslationY(jdVar.b);
        WeakHashMap weakHashMap2 = f51.a;
        t41.w(view, jdVar.c);
        view.setScaleX(jdVar.d);
        view.setScaleY(jdVar.e);
        view.setRotationX(jdVar.f);
        view.setRotationY(jdVar.g);
        view.setRotation(jdVar.h);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
    public final void onAnimationPause(Animator animator) {
        Matrix matrix = this.g.a;
        Matrix matrix2 = this.b;
        matrix2.set(matrix);
        int i = R$id.transition_transform;
        View view = this.e;
        view.setTag(i, matrix2);
        jd jdVar = this.f;
        jdVar.getClass();
        String[] strArr = ChangeTransform.i0;
        view.setTranslationX(jdVar.a);
        view.setTranslationY(jdVar.b);
        WeakHashMap weakHashMap = f51.a;
        t41.w(view, jdVar.c);
        view.setScaleX(jdVar.d);
        view.setScaleY(jdVar.e);
        view.setRotationX(jdVar.f);
        view.setRotationY(jdVar.g);
        view.setRotation(jdVar.h);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
    public final void onAnimationResume(Animator animator) {
        String[] strArr = ChangeTransform.i0;
        View view = this.e;
        view.setTranslationX(0.0f);
        view.setTranslationY(0.0f);
        WeakHashMap weakHashMap = f51.a;
        t41.w(view, 0.0f);
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
        view.setRotationX(0.0f);
        view.setRotationY(0.0f);
        view.setRotation(0.0f);
    }
}
