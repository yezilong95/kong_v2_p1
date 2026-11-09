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
public abstract class xw {
    public static final sv C = y4.c;
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
    public xi B;
    public zu0 a;
    public ld0 b;
    public Drawable c;
    public qa d;
    public LayerDrawable e;
    public boolean f;
    public float h;
    public float i;
    public float j;
    public int k;
    public Animator l;
    public rf0 m;
    public rf0 n;
    public float o;
    public int q;
    public ArrayList s;
    public ArrayList t;
    public ArrayList u;
    public final FloatingActionButton v;
    public final z8 w;
    public boolean g = true;
    public float p = 1.0f;
    public int r = 0;
    public final Rect x = new Rect();
    public final RectF y = new RectF();
    public final RectF z = new RectF();
    public final Matrix A = new Matrix();

    public xw(FloatingActionButton floatingActionButton, z8 z8Var) {
        this.v = floatingActionButton;
        this.w = z8Var;
        ou ouVar = new ou(15);
        zw zwVar = (zw) this;
        ouVar.k(H, d(new vw(zwVar, 1)));
        ouVar.k(I, d(new vw(zwVar, 0)));
        ouVar.k(J, d(new vw(zwVar, 0)));
        ouVar.k(K, d(new vw(zwVar, 0)));
        ouVar.k(L, d(new vw(zwVar, 2)));
        ouVar.k(M, d(new uw(zwVar)));
        this.o = floatingActionButton.getRotation();
    }

    public static ValueAnimator d(ww wwVar) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setInterpolator(C);
        valueAnimator.setDuration(100L);
        valueAnimator.addListener(wwVar);
        valueAnimator.addUpdateListener(wwVar);
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

    public final AnimatorSet b(rf0 rf0Var, float f, float f2, float f3) {
        ArrayList arrayList = new ArrayList();
        Property property = View.ALPHA;
        int i = 1;
        float[] fArr = {f};
        FloatingActionButton floatingActionButton = this.v;
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(floatingActionButton, (Property<FloatingActionButton, Float>) property, fArr);
        rf0Var.f("opacity").a(objectAnimatorOfFloat);
        arrayList.add(objectAnimatorOfFloat);
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(floatingActionButton, (Property<FloatingActionButton, Float>) View.SCALE_X, f2);
        rf0Var.f("scale").a(objectAnimatorOfFloat2);
        int i2 = Build.VERSION.SDK_INT;
        if (i2 == 26) {
            ow owVar = new ow(i);
            owVar.b = new FloatEvaluator();
            objectAnimatorOfFloat2.setEvaluator(owVar);
        }
        arrayList.add(objectAnimatorOfFloat2);
        ObjectAnimator objectAnimatorOfFloat3 = ObjectAnimator.ofFloat(floatingActionButton, (Property<FloatingActionButton, Float>) View.SCALE_Y, f2);
        rf0Var.f("scale").a(objectAnimatorOfFloat3);
        if (i2 == 26) {
            ow owVar2 = new ow(i);
            owVar2.b = new FloatEvaluator();
            objectAnimatorOfFloat3.setEvaluator(owVar2);
        }
        arrayList.add(objectAnimatorOfFloat3);
        Matrix matrix = this.A;
        a(f3, matrix);
        ObjectAnimator objectAnimatorOfObject = ObjectAnimator.ofObject(floatingActionButton, new u9(), new sw(this), new Matrix(matrix));
        rf0Var.f("iconScale").a(objectAnimatorOfObject);
        arrayList.add(objectAnimatorOfObject);
        AnimatorSet animatorSet = new AnimatorSet();
        j31.I(animatorSet, arrayList);
        return animatorSet;
    }

    public final AnimatorSet c(float f, float f2, float f3, int i, int i2) {
        AnimatorSet animatorSet = new AnimatorSet();
        ArrayList arrayList = new ArrayList();
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        FloatingActionButton floatingActionButton = this.v;
        valueAnimatorOfFloat.addUpdateListener(new tw(this, floatingActionButton.getAlpha(), f, floatingActionButton.getScaleX(), f2, floatingActionButton.getScaleY(), this.p, f3, new Matrix(this.A)));
        arrayList.add(valueAnimatorOfFloat);
        j31.I(animatorSet, arrayList);
        animatorSet.setDuration(s90.U(floatingActionButton.getContext(), i, floatingActionButton.getContext().getResources().getInteger(R$integer.material_motion_duration_long_1)));
        animatorSet.setInterpolator(s90.V(floatingActionButton.getContext(), i2, y4.b));
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
        ArrayList<qw> arrayList = this.u;
        if (arrayList != null) {
            for (qw qwVar : arrayList) {
                z8 z8Var = qwVar.a;
                z8Var.getClass();
                BottomAppBar bottomAppBar = (BottomAppBar) z8Var.F;
                ld0 ld0Var = bottomAppBar.z0;
                FloatingActionButton floatingActionButton = qwVar.b;
                ld0Var.n((floatingActionButton.getVisibility() == 0 && bottomAppBar.E0 == 1) ? floatingActionButton.getScaleY() : 0.0f);
            }
        }
    }

    public final void m() {
        ArrayList<qw> arrayList = this.u;
        if (arrayList != null) {
            for (qw qwVar : arrayList) {
                z8 z8Var = qwVar.a;
                z8Var.getClass();
                BottomAppBar bottomAppBar = (BottomAppBar) z8Var.F;
                if (bottomAppBar.E0 == 1) {
                    FloatingActionButton floatingActionButton = qwVar.b;
                    float translationX = floatingActionButton.getTranslationX();
                    float f = bottomAppBar.getTopEdgeTreatment().T;
                    ld0 ld0Var = bottomAppBar.z0;
                    if (f != translationX) {
                        bottomAppBar.getTopEdgeTreatment().T = translationX;
                        ld0Var.invalidateSelf();
                    }
                    float fMax = Math.max(0.0f, -floatingActionButton.getTranslationY());
                    if (bottomAppBar.getTopEdgeTreatment().S != fMax) {
                        bottomAppBar.getTopEdgeTreatment().p(fMax);
                        ld0Var.invalidateSelf();
                    }
                    ld0Var.n(floatingActionButton.getVisibility() == 0 ? floatingActionButton.getScaleY() : 0.0f);
                }
            }
        }
    }

    public void n(ColorStateList colorStateList) {
        Drawable drawable = this.c;
        if (drawable != null) {
            ip.h(drawable, gp0.c(colorStateList));
        }
    }

    public final void o(zu0 zu0Var) {
        this.a = zu0Var;
        ld0 ld0Var = this.b;
        if (ld0Var != null) {
            ld0Var.setShapeAppearanceModel(zu0Var);
        }
        Object obj = this.c;
        if (obj instanceof kv0) {
            ((kv0) obj).setShapeAppearanceModel(zu0Var);
        }
        qa qaVar = this.d;
        if (qaVar != null) {
            qaVar.o = zu0Var;
            qaVar.invalidateSelf();
        }
    }

    public abstract boolean p();

    public abstract void q();

    public final void r() {
        Rect rect = this.x;
        f(rect);
        la0.i(this.e, "Didn't initialize content background");
        boolean zP = p();
        z8 z8Var = this.w;
        if (zP) {
            super/*android.view.View*/.setBackgroundDrawable(new InsetDrawable((Drawable) this.e, rect.left, rect.top, rect.right, rect.bottom));
        } else {
            LayerDrawable layerDrawable = this.e;
            if (layerDrawable != null) {
                super/*android.view.View*/.setBackgroundDrawable(layerDrawable);
            } else {
                z8Var.getClass();
            }
        }
        int i = rect.left;
        int i2 = rect.top;
        int i3 = rect.right;
        int i4 = rect.bottom;
        FloatingActionButton floatingActionButton = (FloatingActionButton) z8Var.F;
        floatingActionButton.P.set(i, i2, i3, i4);
        int i5 = floatingActionButton.M;
        floatingActionButton.setPadding(i + i5, i2 + i5, i3 + i5, i4 + i5);
    }
}
