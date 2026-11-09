package androidx.transition;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import defpackage.a60;
import defpackage.fb;
import defpackage.kp0;
import defpackage.mp0;
import defpackage.u6;
import defpackage.z50;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class ChangeImageTransform extends Transition {
    public static final String[] y = {"android:changeImageTransform:matrix", "android:changeImageTransform:bounds"};
    public static final u6 z = new u6(1);
    public static final fb A = new fb(Matrix.class, "animatedTransform", 5);

    public ChangeImageTransform(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00ba  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void F(defpackage.mp0 r6) {
        /*
            Method dump skipped, instruction units count: 202
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.transition.ChangeImageTransform.F(mp0):void");
    }

    @Override // androidx.transition.Transition
    public final void c(mp0 mp0Var) {
        F(mp0Var);
    }

    @Override // androidx.transition.Transition
    public final void f(mp0 mp0Var) {
        F(mp0Var);
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
    @Override // androidx.transition.Transition
    public final Animator j(ViewGroup viewGroup, mp0 mp0Var, mp0 mp0Var2) {
        if (mp0Var == null || mp0Var2 == null) {
            return null;
        }
        HashMap map = mp0Var.a;
        Rect rect = (Rect) map.get("android:changeImageTransform:bounds");
        HashMap map2 = mp0Var2.a;
        Rect rect2 = (Rect) map2.get("android:changeImageTransform:bounds");
        if (rect == null || rect2 == null) {
            return null;
        }
        Matrix matrix = (Matrix) map.get("android:changeImageTransform:matrix");
        Matrix matrix2 = (Matrix) map2.get("android:changeImageTransform:matrix");
        boolean z2 = (matrix == null && matrix2 == null) || (matrix != null && matrix.equals(matrix2));
        if (rect.equals(rect2) && z2) {
            return null;
        }
        ImageView imageView = (ImageView) mp0Var2.b;
        Drawable drawable = imageView.getDrawable();
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        fb fbVar = A;
        if (intrinsicWidth <= 0 || intrinsicHeight <= 0) {
            u6 u6Var = z;
            z50 z50Var = a60.a;
            return ObjectAnimator.ofObject(imageView, fbVar, u6Var, z50Var, z50Var);
        }
        if (matrix == null) {
            matrix = a60.a;
        }
        if (matrix2 == null) {
            matrix2 = a60.a;
        }
        fbVar.set(imageView, matrix);
        return ObjectAnimator.ofObject(imageView, fbVar, new kp0(), matrix, matrix2);
    }

    @Override // androidx.transition.Transition
    public final String[] o() {
        return y;
    }
}
