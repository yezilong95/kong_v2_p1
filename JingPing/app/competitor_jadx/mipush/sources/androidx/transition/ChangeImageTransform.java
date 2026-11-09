package androidx.transition;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import defpackage.a21;
import defpackage.ed;
import defpackage.f8;
import defpackage.fd;
import defpackage.j31;
import defpackage.qd0;
import defpackage.rd0;
import defpackage.y11;
import defpackage.yc;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class ChangeImageTransform extends Transition {
    public static final String[] f0 = {"android:changeImageTransform:matrix", "android:changeImageTransform:bounds"};
    public static final f8 g0 = new f8(1);
    public static final yc h0 = new yc(Matrix.class, "animatedTransform", 5);

    public ChangeImageTransform(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public static void I(a21 a21Var, boolean z) {
        Matrix matrix;
        View view = a21Var.b;
        if ((view instanceof ImageView) && view.getVisibility() == 0) {
            ImageView imageView = (ImageView) view;
            if (imageView.getDrawable() == null) {
                return;
            }
            HashMap map = a21Var.a;
            map.put("android:changeImageTransform:bounds", new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
            Matrix matrix2 = z ? (Matrix) imageView.getTag(R$id.transition_image_transform) : null;
            if (matrix2 == null) {
                Drawable drawable = imageView.getDrawable();
                if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
                    matrix2 = new Matrix(imageView.getImageMatrix());
                } else {
                    int i = ed.a[imageView.getScaleType().ordinal()];
                    if (i == 1) {
                        Drawable drawable2 = imageView.getDrawable();
                        matrix = new Matrix();
                        matrix.postScale(imageView.getWidth() / drawable2.getIntrinsicWidth(), imageView.getHeight() / drawable2.getIntrinsicHeight());
                    } else if (i != 2) {
                        matrix2 = new Matrix(imageView.getImageMatrix());
                    } else {
                        Drawable drawable3 = imageView.getDrawable();
                        int intrinsicWidth = drawable3.getIntrinsicWidth();
                        float width = imageView.getWidth();
                        float f = intrinsicWidth;
                        int intrinsicHeight = drawable3.getIntrinsicHeight();
                        float height = imageView.getHeight();
                        float f2 = intrinsicHeight;
                        float fMax = Math.max(width / f, height / f2);
                        int iRound = Math.round((width - (f * fMax)) / 2.0f);
                        int iRound2 = Math.round((height - (f2 * fMax)) / 2.0f);
                        matrix = new Matrix();
                        matrix.postScale(fMax, fMax);
                        matrix.postTranslate(iRound, iRound2);
                    }
                    matrix2 = matrix;
                }
            }
            map.put("android:changeImageTransform:matrix", matrix2);
        }
    }

    @Override // androidx.transition.Transition
    public final void d(a21 a21Var) {
        I(a21Var, false);
    }

    @Override // androidx.transition.Transition
    public final void g(a21 a21Var) {
        I(a21Var, true);
    }

    @Override // androidx.transition.Transition
    public final Animator k(ViewGroup viewGroup, a21 a21Var, a21 a21Var2) {
        if (a21Var == null || a21Var2 == null) {
            return null;
        }
        HashMap map = a21Var.a;
        Rect rect = (Rect) map.get("android:changeImageTransform:bounds");
        HashMap map2 = a21Var2.a;
        Rect rect2 = (Rect) map2.get("android:changeImageTransform:bounds");
        if (rect == null || rect2 == null) {
            return null;
        }
        Matrix matrix = (Matrix) map.get("android:changeImageTransform:matrix");
        Matrix matrix2 = (Matrix) map2.get("android:changeImageTransform:matrix");
        boolean z = (matrix == null && matrix2 == null) || (matrix != null && matrix.equals(matrix2));
        if (rect.equals(rect2) && z) {
            return null;
        }
        ImageView imageView = (ImageView) a21Var2.b;
        Drawable drawable = imageView.getDrawable();
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        yc ycVar = h0;
        if (intrinsicWidth <= 0 || intrinsicHeight <= 0) {
            f8 f8Var = g0;
            qd0 qd0Var = rd0.a;
            return ObjectAnimator.ofObject(imageView, ycVar, f8Var, qd0Var, qd0Var);
        }
        if (matrix == null) {
            matrix = rd0.a;
        }
        if (matrix2 == null) {
            matrix2 = rd0.a;
        }
        ycVar.getClass();
        j31.g(imageView, matrix);
        ObjectAnimator objectAnimatorOfObject = ObjectAnimator.ofObject(imageView, ycVar, new y11(), matrix, matrix2);
        fd fdVar = new fd(imageView, matrix, matrix2);
        objectAnimatorOfObject.addListener(fdVar);
        objectAnimatorOfObject.addPauseListener(fdVar);
        a(fdVar);
        return objectAnimatorOfObject;
    }

    @Override // androidx.transition.Transition
    public final String[] q() {
        return f0;
    }
}
