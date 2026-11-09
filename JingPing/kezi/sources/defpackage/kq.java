package defpackage;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.util.Property;
import android.view.View;
import com.google.android.material.R$attr;
import com.google.android.material.R$integer;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class kq {
    public static final mp C = t3.c;
    public static final int D = R$attr.motionDurationLong2;
    public static final int E = R$attr.motionEasingEmphasizedInterpolator;
    public static final int F = R$attr.motionDurationMedium1;
    public static final int G = R$attr.motionEasingEmphasizedAccelerateInterpolator;
    public static final int[] H = {R.attr.state_pressed, R.attr.state_enabled};
    public static final int[] I = {R.attr.state_hovered, R.attr.state_focused, R.attr.state_enabled};
    public static final int[] J = {R.attr.state_focused, R.attr.state_enabled};
    public static final int[] K = {R.attr.state_hovered, R.attr.state_enabled};
    public static final int[] L = {R.attr.state_enabled};
    public static final int[] M = new int[0];
    public jh B;
    public ki0 a;
    public u50 b;
    public Drawable c;
    public r8 d;
    public LayerDrawable e;
    public boolean f;
    public float h;
    public float i;
    public float j;
    public int k;
    public Animator l;
    public x70 m;
    public x70 n;
    public float o;
    public int q;
    public ArrayList s;
    public ArrayList t;
    public ArrayList u;
    public final FloatingActionButton v;
    public final o70 w;
    public boolean g = true;
    public float p = 1.0f;
    public int r = 0;
    public final Rect x = new Rect();
    public final RectF y = new RectF();
    public final RectF z = new RectF();
    public final Matrix A = new Matrix();

    public kq(FloatingActionButton floatingActionButton, o70 o70Var) {
        this.v = floatingActionButton;
        this.w = o70Var;
        po poVar = new po(11);
        mq mqVar = (mq) this;
        poVar.l(H, d(new iq(mqVar, 1)));
        poVar.l(I, d(new iq(mqVar, 0)));
        poVar.l(J, d(new iq(mqVar, 0)));
        poVar.l(K, d(new iq(mqVar, 0)));
        poVar.l(L, d(new iq(mqVar, 2)));
        poVar.l(M, d(new hq(mqVar)));
        this.o = floatingActionButton.getRotation();
    }

    public static ValueAnimator d(jq jqVar) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setInterpolator(C);
        valueAnimator.setDuration(100L);
        valueAnimator.addListener(jqVar);
        valueAnimator.addUpdateListener(jqVar);
        valueAnimator.setFloatValues(0.0f, 1.0f);
        return valueAnimator;
    }

    public final void a(float f, Matrix matrix) {
        matrix.reset();
        if (this.v.getDrawable() == null || this.q == 0) {
            return;
        }
        RectF rectF = this.y;
        RectF rectF2 = this.z;
        rectF.set(0.0f, 0.0f, r0.getIntrinsicWidth(), r0.getIntrinsicHeight());
        float f2 = this.q;
        rectF2.set(0.0f, 0.0f, f2, f2);
        matrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.CENTER);
        float f3 = this.q / 2.0f;
        matrix.postScale(f, f, f3, f3);
    }

    public final AnimatorSet b(x70 x70Var, float f, float f2, float f3) {
        ArrayList arrayList = new ArrayList();
        Property property = View.ALPHA;
        float[] fArr = {f};
        FloatingActionButton floatingActionButton = this.v;
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(floatingActionButton, (Property<FloatingActionButton, Float>) property, fArr);
        x70Var.f("opacity").a(objectAnimatorOfFloat);
        arrayList.add(objectAnimatorOfFloat);
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(floatingActionButton, (Property<FloatingActionButton, Float>) View.SCALE_X, f2);
        x70Var.f("scale").a(objectAnimatorOfFloat2);
        int i = Build.VERSION.SDK_INT;
        if (i == 26) {
            bq bqVar = new bq(1);
            bqVar.b = new FloatEvaluator();
            objectAnimatorOfFloat2.setEvaluator(bqVar);
        }
        arrayList.add(objectAnimatorOfFloat2);
        ObjectAnimator objectAnimatorOfFloat3 = ObjectAnimator.ofFloat(floatingActionButton, (Property<FloatingActionButton, Float>) View.SCALE_Y, f2);
        x70Var.f("scale").a(objectAnimatorOfFloat3);
        if (i == 26) {
            bq bqVar2 = new bq(1);
            bqVar2.b = new FloatEvaluator();
            objectAnimatorOfFloat3.setEvaluator(bqVar2);
        }
        arrayList.add(objectAnimatorOfFloat3);
        Matrix matrix = this.A;
        a(f3, matrix);
        ObjectAnimator objectAnimatorOfObject = ObjectAnimator.ofObject(floatingActionButton, new y7(), new fq(this), new Matrix(matrix));
        x70Var.f("iconScale").a(objectAnimatorOfObject);
        arrayList.add(objectAnimatorOfObject);
        AnimatorSet animatorSet = new AnimatorSet();
        xi.O(animatorSet, arrayList);
        return animatorSet;
    }

    public final AnimatorSet c(float f, float f2, float f3, int i, int i2) {
        AnimatorSet animatorSet = new AnimatorSet();
        ArrayList arrayList = new ArrayList();
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        FloatingActionButton floatingActionButton = this.v;
        valueAnimatorOfFloat.addUpdateListener(new gq(this, floatingActionButton.getAlpha(), f, floatingActionButton.getScaleX(), f2, floatingActionButton.getScaleY(), this.p, f3, new Matrix(this.A)));
        arrayList.add(valueAnimatorOfFloat);
        xi.O(animatorSet, arrayList);
        animatorSet.setDuration(k9.E(floatingActionButton.getContext(), i, floatingActionButton.getContext().getResources().getInteger(R$integer.material_motion_duration_long_1)));
        animatorSet.setInterpolator(k9.F(floatingActionButton.getContext(), i2, t3.b));
        return animatorSet;
    }

    public abstract float e();

    public void f(Rect rect) {
        int iMax = this.f ? Math.max((this.k - this.v.getSizeDimension()) / 2, 0) : 0;
        int iMax2 = Math.max(iMax, (int) Math.ceil(this.g ? e() + this.j : 0.0f));
        int iMax3 = Math.max(iMax, (int) Math.ceil(r0 * 1.5f));
        rect.set(iMax2, iMax3, iMax2, iMax3);
    }

    public abstract void g(ColorStateList colorStateList, PorterDuff.Mode mode, ColorStateList colorStateList2, int i);

    public abstract void h();

    public abstract void i();

    public abstract void j(int[] iArr);

    public abstract void k(float f, float f2, float f3);

    public final void l() {
        ArrayList<dq> arrayList = this.u;
        if (arrayList != null) {
            for (dq dqVar : arrayList) {
                u8 u8Var = dqVar.a;
                u8Var.getClass();
                BottomAppBar bottomAppBar = u8Var.b;
                u50 u50Var = bottomAppBar.T;
                FloatingActionButton floatingActionButton = dqVar.b;
                u50Var.n((floatingActionButton.getVisibility() == 0 && bottomAppBar.b0 == 1) ? floatingActionButton.getScaleY() : 0.0f);
            }
        }
    }

    public final void m() {
        ArrayList<dq> arrayList = this.u;
        if (arrayList != null) {
            for (dq dqVar : arrayList) {
                u8 u8Var = dqVar.a;
                u8Var.getClass();
                BottomAppBar bottomAppBar = u8Var.b;
                if (bottomAppBar.b0 == 1) {
                    FloatingActionButton floatingActionButton = dqVar.b;
                    float translationX = floatingActionButton.getTranslationX();
                    float f = bottomAppBar.getTopEdgeTreatment().p;
                    u50 u50Var = bottomAppBar.T;
                    if (f != translationX) {
                        bottomAppBar.getTopEdgeTreatment().p = translationX;
                        u50Var.invalidateSelf();
                    }
                    float fMax = Math.max(0.0f, -floatingActionButton.getTranslationY());
                    if (bottomAppBar.getTopEdgeTreatment().o != fMax) {
                        bottomAppBar.getTopEdgeTreatment().o(fMax);
                        u50Var.invalidateSelf();
                    }
                    u50Var.n(floatingActionButton.getVisibility() == 0 ? floatingActionButton.getScaleY() : 0.0f);
                }
            }
        }
    }

    public void n(ColorStateList colorStateList) {
        Drawable drawable = this.c;
        if (drawable != null) {
            hl.h(drawable, lf0.c(colorStateList));
        }
    }

    public final void o(ki0 ki0Var) {
        this.a = ki0Var;
        u50 u50Var = this.b;
        if (u50Var != null) {
            u50Var.setShapeAppearanceModel(ki0Var);
        }
        Object obj = this.c;
        if (obj instanceof vi0) {
            ((vi0) obj).setShapeAppearanceModel(ki0Var);
        }
        r8 r8Var = this.d;
        if (r8Var != null) {
            r8Var.o = ki0Var;
            r8Var.invalidateSelf();
        }
    }

    public abstract boolean p();

    public abstract void q();

    public final void r() {
        Rect rect = this.x;
        f(rect);
        k9.h(this.e, "Didn't initialize content background");
        boolean zP = p();
        o70 o70Var = this.w;
        if (zP) {
            super/*android.view.View*/.setBackgroundDrawable(new InsetDrawable((Drawable) this.e, rect.left, rect.top, rect.right, rect.bottom));
        } else {
            LayerDrawable layerDrawable = this.e;
            if (layerDrawable != null) {
                super/*android.view.View*/.setBackgroundDrawable(layerDrawable);
            } else {
                o70Var.getClass();
            }
        }
        int i = rect.left;
        int i2 = rect.top;
        int i3 = rect.right;
        int i4 = rect.bottom;
        FloatingActionButton floatingActionButton = (FloatingActionButton) o70Var.c;
        floatingActionButton.m.set(i, i2, i3, i4);
        int i5 = floatingActionButton.j;
        floatingActionButton.setPadding(i + i5, i2 + i5, i3 + i5, i4 + i5);
    }
}
