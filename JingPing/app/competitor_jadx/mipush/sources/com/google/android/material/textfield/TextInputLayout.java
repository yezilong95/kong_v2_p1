package com.google.android.material.textfield;

import android.R;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.customview.view.AbsSavedState;
import androidx.transition.Fade;
import com.google.android.material.R$attr;
import com.google.android.material.R$color;
import com.google.android.material.R$dimen;
import com.google.android.material.R$id;
import com.google.android.material.R$string;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.internal.CheckableImageButton;
import defpackage.a01;
import defpackage.ax0;
import defpackage.bb;
import defpackage.bo;
import defpackage.cb;
import defpackage.d40;
import defpackage.dg;
import defpackage.e40;
import defpackage.f;
import defpackage.f51;
import defpackage.f6;
import defpackage.fa0;
import defpackage.i01;
import defpackage.ik;
import defpackage.ip;
import defpackage.is;
import defpackage.j31;
import defpackage.jk;
import defpackage.jq;
import defpackage.kd0;
import defpackage.l21;
import defpackage.la0;
import defpackage.ld0;
import defpackage.ma0;
import defpackage.na;
import defpackage.no;
import defpackage.o0;
import defpackage.op;
import defpackage.ou;
import defpackage.pd0;
import defpackage.qi;
import defpackage.qm;
import defpackage.s90;
import defpackage.sf;
import defpackage.t40;
import defpackage.tz0;
import defpackage.up0;
import defpackage.uv0;
import defpackage.v11;
import defpackage.vt;
import defpackage.w41;
import defpackage.wz0;
import defpackage.xt;
import defpackage.xu0;
import defpackage.xz0;
import defpackage.y4;
import defpackage.yi;
import defpackage.yz0;
import defpackage.z91;
import defpackage.zu0;
import defpackage.zz0;
import io.nats.client.support.JsonUtils;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class TextInputLayout extends LinearLayout implements ViewTreeObserver.OnGlobalLayoutListener {
    public static final int g1 = R$style.Widget_Design_TextInputLayout;
    public static final int[][] h1 = {new int[]{R.attr.state_pressed}, new int[0]};
    public int A0;
    public int B0;
    public final Rect C0;
    public final Rect D0;
    public final FrameLayout E;
    public final RectF E0;
    public final ax0 F;
    public Typeface F0;
    public final xt G;
    public ColorDrawable G0;
    public EditText H;
    public int H0;
    public CharSequence I;
    public final LinkedHashSet I0;
    public int J;
    public ColorDrawable J0;
    public int K;
    public int K0;
    public int L;
    public Drawable L0;
    public int M;
    public ColorStateList M0;
    public final e40 N;
    public ColorStateList N0;
    public boolean O;
    public int O0;
    public int P;
    public int P0;
    public boolean Q;
    public int Q0;
    public yz0 R;
    public ColorStateList R0;
    public AppCompatTextView S;
    public int S0;
    public int T;
    public int T0;
    public int U;
    public int U0;
    public CharSequence V;
    public int V0;
    public boolean W;
    public int W0;
    public int X0;
    public boolean Y0;
    public final sf Z0;
    public AppCompatTextView a0;
    public boolean a1;
    public ColorStateList b0;
    public boolean b1;
    public int c0;
    public ValueAnimator c1;
    public Fade d0;
    public boolean d1;
    public Fade e0;
    public boolean e1;
    public ColorStateList f0;
    public boolean f1;
    public ColorStateList g0;
    public ColorStateList h0;
    public ColorStateList i0;
    public boolean j0;
    public CharSequence k0;
    public boolean l0;
    public ld0 m0;
    public ld0 n0;
    public StateListDrawable o0;
    public boolean p0;
    public ld0 q0;
    public ld0 r0;
    public zu0 s0;
    public boolean t0;
    public final int u0;
    public int v0;
    public int w0;
    public int x0;
    public int y0;
    public int z0;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public CharSequence G;
        public boolean H;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.G = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.H = parcel.readInt() == 1;
        }

        public final String toString() {
            return "TextInputLayout.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " error=" + ((Object) this.G) + JsonUtils.CLOSE;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            TextUtils.writeToParcel(this.G, parcel, i);
            parcel.writeInt(this.H ? 1 : 0);
        }
    }

    public TextInputLayout(Context context) {
        this(context, null);
    }

    private Drawable getEditTextBoxBackground() {
        EditText editText = this.H;
        if (!(editText instanceof AutoCompleteTextView) || j31.z(editText)) {
            return this.m0;
        }
        int iP = fa0.P(this.H, R$attr.colorControlHighlight);
        int i = this.v0;
        int[][] iArr = h1;
        if (i != 2) {
            if (i != 1) {
                return null;
            }
            ld0 ld0Var = this.m0;
            int i2 = this.B0;
            return new RippleDrawable(new ColorStateList(iArr, new int[]{fa0.k0(iP, 0.1f, i2), i2}), ld0Var, ld0Var);
        }
        Context context = getContext();
        ld0 ld0Var2 = this.m0;
        TypedValue typedValueW = s90.W(context, R$attr.colorSurface, "TextInputLayout");
        int i3 = typedValueW.resourceId;
        int iA = i3 != 0 ? qi.a(context, i3) : typedValueW.data;
        ld0 ld0Var3 = new ld0(ld0Var2.E.a);
        int iK0 = fa0.k0(iP, 0.1f, iA);
        ld0Var3.m(new ColorStateList(iArr, new int[]{iK0, 0}));
        ld0Var3.setTint(iA);
        ColorStateList colorStateList = new ColorStateList(iArr, new int[]{iK0, iA});
        ld0 ld0Var4 = new ld0(ld0Var2.E.a);
        ld0Var4.setTint(-1);
        return new LayerDrawable(new Drawable[]{new RippleDrawable(colorStateList, ld0Var3, ld0Var4), ld0Var2});
    }

    private Drawable getOrCreateFilledDropDownMenuBackground() {
        if (this.o0 == null) {
            StateListDrawable stateListDrawable = new StateListDrawable();
            this.o0 = stateListDrawable;
            stateListDrawable.addState(new int[]{R.attr.state_above_anchor}, getOrCreateOutlinedDropDownMenuBackground());
            this.o0.addState(new int[0], f(false));
        }
        return this.o0;
    }

    private Drawable getOrCreateOutlinedDropDownMenuBackground() {
        if (this.n0 == null) {
            this.n0 = f(true);
        }
        return this.n0;
    }

    public static void k(ViewGroup viewGroup, boolean z) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            childAt.setEnabled(z);
            if (childAt instanceof ViewGroup) {
                k((ViewGroup) childAt, z);
            }
        }
    }

    private void setEditText(EditText editText) {
        if (this.H != null) {
            throw new IllegalArgumentException("We already have an EditText, can only have one");
        }
        if (getEndIconMode() != 3 && !(editText instanceof TextInputEditText)) {
            Log.i("TextInputLayout", "EditText added is not a TextInputEditText. Please switch to using that class instead.");
        }
        this.H = editText;
        int i = this.J;
        if (i != -1) {
            setMinEms(i);
        } else {
            setMinWidth(this.L);
        }
        int i2 = this.K;
        if (i2 != -1) {
            setMaxEms(i2);
        } else {
            setMaxWidth(this.M);
        }
        this.p0 = false;
        i();
        setTextInputAccessibilityDelegate(new xz0(this));
        Typeface typeface = this.H.getTypeface();
        sf sfVar = this.Z0;
        boolean zM = sfVar.m(typeface);
        boolean zO = sfVar.o(typeface);
        if (zM || zO) {
            sfVar.i(false);
        }
        float textSize = this.H.getTextSize();
        if (sfVar.l != textSize) {
            sfVar.l = textSize;
            sfVar.i(false);
        }
        int i3 = Build.VERSION.SDK_INT;
        float letterSpacing = this.H.getLetterSpacing();
        if (sfVar.g0 != letterSpacing) {
            sfVar.g0 = letterSpacing;
            sfVar.i(false);
        }
        int gravity = this.H.getGravity();
        sfVar.l((gravity & (-113)) | 48);
        if (sfVar.j != gravity) {
            sfVar.j = gravity;
            sfVar.i(false);
        }
        WeakHashMap weakHashMap = f51.a;
        this.X0 = editText.getMinimumHeight();
        this.H.addTextChangedListener(new wz0(this, editText));
        if (this.M0 == null) {
            this.M0 = this.H.getHintTextColors();
        }
        if (this.j0) {
            if (TextUtils.isEmpty(this.k0)) {
                CharSequence hint = this.H.getHint();
                this.I = hint;
                setHint(hint);
                this.H.setHint((CharSequence) null);
            }
            this.l0 = true;
        }
        if (i3 >= 29) {
            p();
        }
        if (this.S != null) {
            n(this.H.getText());
        }
        r();
        this.N.b();
        this.F.bringToFront();
        xt xtVar = this.G;
        xtVar.bringToFront();
        Iterator it = this.I0.iterator();
        while (it.hasNext()) {
            ((vt) ((zz0) it.next())).a(this);
        }
        xtVar.m();
        if (!isEnabled()) {
            editText.setEnabled(false);
        }
        u(false, true);
    }

    private void setHintInternal(CharSequence charSequence) {
        if (TextUtils.equals(charSequence, this.k0)) {
            return;
        }
        this.k0 = charSequence;
        sf sfVar = this.Z0;
        if (charSequence == null || !TextUtils.equals(sfVar.G, charSequence)) {
            sfVar.G = charSequence;
            sfVar.H = null;
            Bitmap bitmap = sfVar.K;
            if (bitmap != null) {
                bitmap.recycle();
                sfVar.K = null;
            }
            sfVar.i(false);
        }
        if (this.Y0) {
            return;
        }
        j();
    }

    private void setPlaceholderTextEnabled(boolean z) {
        if (this.W == z) {
            return;
        }
        if (z) {
            AppCompatTextView appCompatTextView = this.a0;
            if (appCompatTextView != null) {
                this.E.addView(appCompatTextView);
                this.a0.setVisibility(0);
            }
        } else {
            AppCompatTextView appCompatTextView2 = this.a0;
            if (appCompatTextView2 != null) {
                appCompatTextView2.setVisibility(8);
            }
            this.a0 = null;
        }
        this.W = z;
    }

    public final void a(float f) {
        sf sfVar = this.Z0;
        if (sfVar.b == f) {
            return;
        }
        if (this.c1 == null) {
            ValueAnimator valueAnimator = new ValueAnimator();
            this.c1 = valueAnimator;
            valueAnimator.setInterpolator(s90.V(getContext(), R$attr.motionEasingEmphasizedInterpolator, y4.b));
            this.c1.setDuration(s90.U(getContext(), R$attr.motionDurationMedium4, 167));
            this.c1.addUpdateListener(new bb(5, this));
        }
        this.c1.setFloatValues(sfVar.b, f);
        this.c1.start();
    }

    public void addOnEditTextAttachedListener(zz0 zz0Var) {
        this.I0.add(zz0Var);
        if (this.H != null) {
            ((vt) zz0Var).a(this);
        }
    }

    public void addOnEndIconChangedListener(a01 a01Var) {
        this.G.addOnEndIconChangedListener(a01Var);
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (!(view instanceof EditText)) {
            super.addView(view, i, layoutParams);
            return;
        }
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(layoutParams);
        layoutParams2.gravity = (layoutParams2.gravity & (-113)) | 16;
        FrameLayout frameLayout = this.E;
        frameLayout.addView(view, layoutParams2);
        frameLayout.setLayoutParams(layoutParams);
        t();
        setEditText((EditText) view);
    }

    public final void b() {
        int i;
        int i2;
        ld0 ld0Var = this.m0;
        if (ld0Var == null) {
            return;
        }
        zu0 zu0Var = ld0Var.E.a;
        zu0 zu0Var2 = this.s0;
        if (zu0Var != zu0Var2) {
            ld0Var.setShapeAppearanceModel(zu0Var2);
        }
        if (this.v0 == 2 && (i = this.x0) > -1 && (i2 = this.A0) != 0) {
            ld0 ld0Var2 = this.m0;
            ld0Var2.s(i);
            ld0Var2.r(ColorStateList.valueOf(i2));
        }
        int iC = this.B0;
        if (this.v0 == 1) {
            iC = dg.c(this.B0, fa0.O(getContext(), R$attr.colorSurface, 0));
        }
        this.B0 = iC;
        this.m0.m(ColorStateList.valueOf(iC));
        ld0 ld0Var3 = this.q0;
        if (ld0Var3 != null && this.r0 != null) {
            if (this.x0 > -1 && this.A0 != 0) {
                ld0Var3.m(this.H.isFocused() ? ColorStateList.valueOf(this.O0) : ColorStateList.valueOf(this.A0));
                this.r0.m(ColorStateList.valueOf(this.A0));
            }
            invalidate();
        }
        s();
    }

    public final int c() {
        float fE;
        if (!this.j0) {
            return 0;
        }
        int i = this.v0;
        sf sfVar = this.Z0;
        if (i == 0) {
            fE = sfVar.e();
        } else {
            if (i != 2) {
                return 0;
            }
            fE = sfVar.e() / 2.0f;
        }
        return (int) fE;
    }

    public final Fade d() {
        Fade fade = new Fade();
        fade.G = s90.U(getContext(), R$attr.motionDurationShort2, 87);
        fade.H = s90.V(getContext(), R$attr.motionEasingLinearInterpolator, y4.a);
        return fade;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchProvideAutofillStructure(ViewStructure viewStructure, int i) {
        EditText editText = this.H;
        if (editText == null) {
            super.dispatchProvideAutofillStructure(viewStructure, i);
            return;
        }
        if (this.I != null) {
            boolean z = this.l0;
            this.l0 = false;
            CharSequence hint = editText.getHint();
            this.H.setHint(this.I);
            try {
                super.dispatchProvideAutofillStructure(viewStructure, i);
                return;
            } finally {
                this.H.setHint(hint);
                this.l0 = z;
            }
        }
        viewStructure.setAutofillId(getAutofillId());
        onProvideAutofillStructure(viewStructure, i);
        onProvideAutofillVirtualStructure(viewStructure, i);
        FrameLayout frameLayout = this.E;
        viewStructure.setChildCount(frameLayout.getChildCount());
        for (int i2 = 0; i2 < frameLayout.getChildCount(); i2++) {
            View childAt = frameLayout.getChildAt(i2);
            ViewStructure viewStructureNewChild = viewStructure.newChild(i2);
            childAt.dispatchProvideAutofillStructure(viewStructureNewChild, i);
            if (childAt == this.H) {
                viewStructureNewChild.setHint(getHint());
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchRestoreInstanceState(SparseArray sparseArray) {
        this.e1 = true;
        super.dispatchRestoreInstanceState(sparseArray);
        this.e1 = false;
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        ld0 ld0Var;
        super.draw(canvas);
        boolean z = this.j0;
        sf sfVar = this.Z0;
        if (z) {
            sfVar.d(canvas);
        }
        if (this.r0 == null || (ld0Var = this.q0) == null) {
            return;
        }
        ld0Var.draw(canvas);
        if (this.H.isFocused()) {
            Rect bounds = this.r0.getBounds();
            Rect bounds2 = this.q0.getBounds();
            float f = sfVar.b;
            int iCenterX = bounds2.centerX();
            bounds.left = y4.c(iCenterX, f, bounds2.left);
            bounds.right = y4.c(iCenterX, f, bounds2.right);
            this.r0.draw(canvas);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x002f  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void drawableStateChanged() {
        /*
            r4 = this;
            boolean r0 = r4.d1
            if (r0 == 0) goto L5
            return
        L5:
            r0 = 1
            r4.d1 = r0
            super.drawableStateChanged()
            int[] r1 = r4.getDrawableState()
            r2 = 0
            sf r3 = r4.Z0
            if (r3 == 0) goto L2f
            r3.R = r1
            android.content.res.ColorStateList r1 = r3.o
            if (r1 == 0) goto L20
            boolean r1 = r1.isStateful()
            if (r1 != 0) goto L2a
        L20:
            android.content.res.ColorStateList r1 = r3.n
            if (r1 == 0) goto L2f
            boolean r1 = r1.isStateful()
            if (r1 == 0) goto L2f
        L2a:
            r3.i(r2)
            r1 = r0
            goto L30
        L2f:
            r1 = r2
        L30:
            android.widget.EditText r3 = r4.H
            if (r3 == 0) goto L47
            java.util.WeakHashMap r3 = defpackage.f51.a
            boolean r3 = r4.isLaidOut()
            if (r3 == 0) goto L43
            boolean r3 = r4.isEnabled()
            if (r3 == 0) goto L43
            goto L44
        L43:
            r0 = r2
        L44:
            r4.u(r0, r2)
        L47:
            r4.r()
            r4.x()
            if (r1 == 0) goto L52
            r4.invalidate()
        L52:
            r4.d1 = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.drawableStateChanged():void");
    }

    public final boolean e() {
        return this.j0 && !TextUtils.isEmpty(this.k0) && (this.m0 instanceof jk);
    }

    public final ld0 f(boolean z) {
        int i = 0;
        float dimensionPixelOffset = getResources().getDimensionPixelOffset(R$dimen.mtrl_shape_corner_size_small_component);
        float f = z ? dimensionPixelOffset : 0.0f;
        EditText editText = this.H;
        float popupElevation = editText instanceof MaterialAutoCompleteTextView ? ((MaterialAutoCompleteTextView) editText).getPopupElevation() : getResources().getDimensionPixelOffset(R$dimen.m3_comp_outlined_autocomplete_menu_container_elevation);
        int dimensionPixelOffset2 = getResources().getDimensionPixelOffset(R$dimen.mtrl_exposed_dropdown_menu_popup_vertical_padding);
        up0 up0Var = new up0();
        up0 up0Var2 = new up0();
        up0 up0Var3 = new up0();
        up0 up0Var4 = new up0();
        is isVar = new is(i);
        is isVar2 = new is(i);
        is isVar3 = new is(i);
        is isVar4 = new is(i);
        o0 o0Var = new o0(f);
        o0 o0Var2 = new o0(f);
        o0 o0Var3 = new o0(dimensionPixelOffset);
        o0 o0Var4 = new o0(dimensionPixelOffset);
        zu0 zu0Var = new zu0();
        zu0Var.a = up0Var;
        zu0Var.b = up0Var2;
        zu0Var.c = up0Var3;
        zu0Var.d = up0Var4;
        zu0Var.e = o0Var;
        zu0Var.f = o0Var2;
        zu0Var.g = o0Var4;
        zu0Var.h = o0Var3;
        zu0Var.i = isVar;
        zu0Var.j = isVar2;
        zu0Var.k = isVar3;
        zu0Var.l = isVar4;
        EditText editText2 = this.H;
        ColorStateList dropDownBackgroundTintList = editText2 instanceof MaterialAutoCompleteTextView ? ((MaterialAutoCompleteTextView) editText2).getDropDownBackgroundTintList() : null;
        Context context = getContext();
        if (dropDownBackgroundTintList == null) {
            Paint paint = ld0.b0;
            TypedValue typedValueW = s90.W(context, R$attr.colorSurface, ld0.class.getSimpleName());
            int i2 = typedValueW.resourceId;
            dropDownBackgroundTintList = ColorStateList.valueOf(i2 != 0 ? qi.a(context, i2) : typedValueW.data);
        }
        ld0 ld0Var = new ld0();
        ld0Var.k(context);
        ld0Var.m(dropDownBackgroundTintList);
        ld0Var.l(popupElevation);
        ld0Var.setShapeAppearanceModel(zu0Var);
        kd0 kd0Var = ld0Var.E;
        if (kd0Var.g == null) {
            kd0Var.g = new Rect();
        }
        ld0Var.E.g.set(0, dimensionPixelOffset2, 0, dimensionPixelOffset2);
        ld0Var.invalidateSelf();
        return ld0Var;
    }

    public final int g(int i, boolean z) {
        return ((z || getPrefixText() == null) ? (!z || getSuffixText() == null) ? this.H.getCompoundPaddingLeft() : this.G.c() : this.F.a()) + i;
    }

    @Override // android.widget.LinearLayout, android.view.View
    public int getBaseline() {
        EditText editText = this.H;
        if (editText == null) {
            return super.getBaseline();
        }
        return c() + getPaddingTop() + editText.getBaseline();
    }

    public ld0 getBoxBackground() {
        int i = this.v0;
        if (i == 1 || i == 2) {
            return this.m0;
        }
        throw new IllegalStateException();
    }

    public int getBoxBackgroundColor() {
        return this.B0;
    }

    public int getBoxBackgroundMode() {
        return this.v0;
    }

    public int getBoxCollapsedPaddingTop() {
        return this.w0;
    }

    public float getBoxCornerRadiusBottomEnd() {
        boolean zD0 = fa0.d0(this);
        RectF rectF = this.E0;
        return zD0 ? this.s0.h.a(rectF) : this.s0.g.a(rectF);
    }

    public float getBoxCornerRadiusBottomStart() {
        boolean zD0 = fa0.d0(this);
        RectF rectF = this.E0;
        return zD0 ? this.s0.g.a(rectF) : this.s0.h.a(rectF);
    }

    public float getBoxCornerRadiusTopEnd() {
        boolean zD0 = fa0.d0(this);
        RectF rectF = this.E0;
        return zD0 ? this.s0.e.a(rectF) : this.s0.f.a(rectF);
    }

    public float getBoxCornerRadiusTopStart() {
        boolean zD0 = fa0.d0(this);
        RectF rectF = this.E0;
        return zD0 ? this.s0.f.a(rectF) : this.s0.e.a(rectF);
    }

    public int getBoxStrokeColor() {
        return this.Q0;
    }

    public ColorStateList getBoxStrokeErrorColor() {
        return this.R0;
    }

    public int getBoxStrokeWidth() {
        return this.y0;
    }

    public int getBoxStrokeWidthFocused() {
        return this.z0;
    }

    public int getCounterMaxLength() {
        return this.P;
    }

    public CharSequence getCounterOverflowDescription() {
        AppCompatTextView appCompatTextView;
        if (this.O && this.Q && (appCompatTextView = this.S) != null) {
            return appCompatTextView.getContentDescription();
        }
        return null;
    }

    public ColorStateList getCounterOverflowTextColor() {
        return this.g0;
    }

    public ColorStateList getCounterTextColor() {
        return this.f0;
    }

    public ColorStateList getCursorColor() {
        return this.h0;
    }

    public ColorStateList getCursorErrorColor() {
        return this.i0;
    }

    public ColorStateList getDefaultHintTextColor() {
        return this.M0;
    }

    public EditText getEditText() {
        return this.H;
    }

    public CharSequence getEndIconContentDescription() {
        return this.G.K.getContentDescription();
    }

    public Drawable getEndIconDrawable() {
        return this.G.K.getDrawable();
    }

    public int getEndIconMinSize() {
        return this.G.Q;
    }

    public int getEndIconMode() {
        return this.G.M;
    }

    public ImageView.ScaleType getEndIconScaleType() {
        return this.G.R;
    }

    public CheckableImageButton getEndIconView() {
        return this.G.K;
    }

    public CharSequence getError() {
        e40 e40Var = this.N;
        if (e40Var.q) {
            return e40Var.p;
        }
        return null;
    }

    public int getErrorAccessibilityLiveRegion() {
        return this.N.t;
    }

    public CharSequence getErrorContentDescription() {
        return this.N.s;
    }

    public int getErrorCurrentTextColors() {
        AppCompatTextView appCompatTextView = this.N.r;
        if (appCompatTextView != null) {
            return appCompatTextView.getCurrentTextColor();
        }
        return -1;
    }

    public Drawable getErrorIconDrawable() {
        return this.G.G.getDrawable();
    }

    public CharSequence getHelperText() {
        e40 e40Var = this.N;
        if (e40Var.x) {
            return e40Var.w;
        }
        return null;
    }

    public int getHelperTextCurrentTextColor() {
        AppCompatTextView appCompatTextView = this.N.y;
        if (appCompatTextView != null) {
            return appCompatTextView.getCurrentTextColor();
        }
        return -1;
    }

    public CharSequence getHint() {
        if (this.j0) {
            return this.k0;
        }
        return null;
    }

    public final float getHintCollapsedTextHeight() {
        return this.Z0.e();
    }

    public final int getHintCurrentCollapsedTextColor() {
        sf sfVar = this.Z0;
        return sfVar.f(sfVar.o);
    }

    public ColorStateList getHintTextColor() {
        return this.N0;
    }

    public yz0 getLengthCounter() {
        return this.R;
    }

    public int getMaxEms() {
        return this.K;
    }

    public int getMaxWidth() {
        return this.M;
    }

    public int getMinEms() {
        return this.J;
    }

    public int getMinWidth() {
        return this.L;
    }

    @Deprecated
    public CharSequence getPasswordVisibilityToggleContentDescription() {
        return this.G.K.getContentDescription();
    }

    @Deprecated
    public Drawable getPasswordVisibilityToggleDrawable() {
        return this.G.K.getDrawable();
    }

    public CharSequence getPlaceholderText() {
        if (this.W) {
            return this.V;
        }
        return null;
    }

    public int getPlaceholderTextAppearance() {
        return this.c0;
    }

    public ColorStateList getPlaceholderTextColor() {
        return this.b0;
    }

    public CharSequence getPrefixText() {
        return this.F.G;
    }

    public ColorStateList getPrefixTextColor() {
        return this.F.F.getTextColors();
    }

    public TextView getPrefixTextView() {
        return this.F.F;
    }

    public zu0 getShapeAppearanceModel() {
        return this.s0;
    }

    public CharSequence getStartIconContentDescription() {
        return this.F.H.getContentDescription();
    }

    public Drawable getStartIconDrawable() {
        return this.F.H.getDrawable();
    }

    public int getStartIconMinSize() {
        return this.F.K;
    }

    public ImageView.ScaleType getStartIconScaleType() {
        return this.F.L;
    }

    public CharSequence getSuffixText() {
        return this.G.T;
    }

    public ColorStateList getSuffixTextColor() {
        return this.G.U.getTextColors();
    }

    public TextView getSuffixTextView() {
        return this.G.U;
    }

    public Typeface getTypeface() {
        return this.F0;
    }

    public final int h(int i, boolean z) {
        return i - ((z || getSuffixText() == null) ? (!z || getPrefixText() == null) ? this.H.getCompoundPaddingRight() : this.F.a() : this.G.c());
    }

    public final void i() {
        int i = this.v0;
        if (i == 0) {
            this.m0 = null;
            this.q0 = null;
            this.r0 = null;
        } else if (i == 1) {
            this.m0 = new ld0(this.s0);
            this.q0 = new ld0();
            this.r0 = new ld0();
        } else {
            if (i != 2) {
                throw new IllegalArgumentException(this.v0 + " is illegal; only @BoxBackgroundMode constants are supported.");
            }
            if (!this.j0 || (this.m0 instanceof jk)) {
                this.m0 = new ld0(this.s0);
            } else {
                zu0 zu0Var = this.s0;
                int i2 = jk.d0;
                if (zu0Var == null) {
                    zu0Var = new zu0();
                }
                ik ikVar = new ik(zu0Var, new RectF());
                jk jkVar = new jk(ikVar);
                jkVar.c0 = ikVar;
                this.m0 = jkVar;
            }
            this.q0 = null;
            this.r0 = null;
        }
        s();
        x();
        if (this.v0 == 1) {
            if (getContext().getResources().getConfiguration().fontScale >= 2.0f) {
                this.w0 = getResources().getDimensionPixelSize(R$dimen.material_font_2_0_box_collapsed_padding_top);
            } else if (la0.P(getContext())) {
                this.w0 = getResources().getDimensionPixelSize(R$dimen.material_font_1_3_box_collapsed_padding_top);
            }
        }
        if (this.H != null && this.v0 == 1) {
            if (getContext().getResources().getConfiguration().fontScale >= 2.0f) {
                EditText editText = this.H;
                WeakHashMap weakHashMap = f51.a;
                editText.setPaddingRelative(editText.getPaddingStart(), getResources().getDimensionPixelSize(R$dimen.material_filled_edittext_font_2_0_padding_top), this.H.getPaddingEnd(), getResources().getDimensionPixelSize(R$dimen.material_filled_edittext_font_2_0_padding_bottom));
            } else if (la0.P(getContext())) {
                EditText editText2 = this.H;
                WeakHashMap weakHashMap2 = f51.a;
                editText2.setPaddingRelative(editText2.getPaddingStart(), getResources().getDimensionPixelSize(R$dimen.material_filled_edittext_font_1_3_padding_top), this.H.getPaddingEnd(), getResources().getDimensionPixelSize(R$dimen.material_filled_edittext_font_1_3_padding_bottom));
            }
        }
        if (this.v0 != 0) {
            t();
        }
        EditText editText3 = this.H;
        if (editText3 instanceof AutoCompleteTextView) {
            AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) editText3;
            if (autoCompleteTextView.getDropDownBackground() == null) {
                int i3 = this.v0;
                if (i3 == 2) {
                    autoCompleteTextView.setDropDownBackgroundDrawable(getOrCreateOutlinedDropDownMenuBackground());
                } else if (i3 == 1) {
                    autoCompleteTextView.setDropDownBackgroundDrawable(getOrCreateFilledDropDownMenuBackground());
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x008d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void j() {
        /*
            Method dump skipped, instruction units count: 241
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.j():void");
    }

    public final void l(AppCompatTextView appCompatTextView, int i) {
        try {
            appCompatTextView.setTextAppearance(i);
            if (appCompatTextView.getTextColors().getDefaultColor() != -65281) {
                return;
            }
        } catch (Exception unused) {
        }
        appCompatTextView.setTextAppearance(R$style.TextAppearance_AppCompat_Caption);
        appCompatTextView.setTextColor(qi.a(getContext(), R$color.design_error));
    }

    public final boolean m() {
        e40 e40Var = this.N;
        return (e40Var.o != 1 || e40Var.r == null || TextUtils.isEmpty(e40Var.p)) ? false : true;
    }

    public final void n(Editable editable) {
        ((no) this.R).getClass();
        int length = editable != null ? editable.length() : 0;
        boolean z = this.Q;
        int i = this.P;
        String string = null;
        if (i == -1) {
            this.S.setText(String.valueOf(length));
            this.S.setContentDescription(null);
            this.Q = false;
        } else {
            this.Q = length > i;
            Context context = getContext();
            this.S.setContentDescription(context.getString(this.Q ? R$string.character_counter_overflowed_content_description : R$string.character_counter_content_description, Integer.valueOf(length), Integer.valueOf(this.P)));
            if (z != this.Q) {
                o();
            }
            String str = na.b;
            na naVar = TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 1 ? na.e : na.d;
            AppCompatTextView appCompatTextView = this.S;
            String string2 = getContext().getString(R$string.character_counter_pattern, Integer.valueOf(length), Integer.valueOf(this.P));
            if (string2 == null) {
                naVar.getClass();
            } else {
                naVar.getClass();
                cb cbVar = tz0.a;
                string = naVar.c(string2).toString();
            }
            appCompatTextView.setText(string);
        }
        if (this.H == null || z == this.Q) {
            return;
        }
        u(false, false);
        x();
        r();
    }

    public final void o() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        AppCompatTextView appCompatTextView = this.S;
        if (appCompatTextView != null) {
            l(appCompatTextView, this.Q ? this.T : this.U);
            if (!this.Q && (colorStateList2 = this.f0) != null) {
                this.S.setTextColor(colorStateList2);
            }
            if (!this.Q || (colorStateList = this.g0) == null) {
                return;
            }
            this.S.setTextColor(colorStateList);
        }
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.Z0.h(configuration);
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        int iMax;
        xt xtVar = this.G;
        xtVar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        boolean z = false;
        this.f1 = false;
        if (this.H != null && this.H.getMeasuredHeight() < (iMax = Math.max(xtVar.getMeasuredHeight(), this.F.getMeasuredHeight()))) {
            this.H.setMinimumHeight(iMax);
            z = true;
        }
        boolean zQ = q();
        if (z || zQ) {
            this.H.post(new uv0(3, this));
        }
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        EditText editText = this.H;
        if (editText != null) {
            Rect rect = this.C0;
            bo.a(this, editText, rect);
            ld0 ld0Var = this.q0;
            if (ld0Var != null) {
                int i5 = rect.bottom;
                ld0Var.setBounds(rect.left, i5 - this.y0, rect.right, i5);
            }
            ld0 ld0Var2 = this.r0;
            if (ld0Var2 != null) {
                int i6 = rect.bottom;
                ld0Var2.setBounds(rect.left, i6 - this.z0, rect.right, i6);
            }
            if (this.j0) {
                float textSize = this.H.getTextSize();
                sf sfVar = this.Z0;
                if (sfVar.l != textSize) {
                    sfVar.l = textSize;
                    sfVar.i(false);
                }
                int gravity = this.H.getGravity();
                sfVar.l((gravity & (-113)) | 48);
                if (sfVar.j != gravity) {
                    sfVar.j = gravity;
                    sfVar.i(false);
                }
                if (this.H == null) {
                    throw new IllegalStateException();
                }
                boolean zD0 = fa0.d0(this);
                int i7 = rect.bottom;
                Rect rect2 = this.D0;
                rect2.bottom = i7;
                int i8 = this.v0;
                if (i8 == 1) {
                    rect2.left = g(rect.left, zD0);
                    rect2.top = rect.top + this.w0;
                    rect2.right = h(rect.right, zD0);
                } else if (i8 != 2) {
                    rect2.left = g(rect.left, zD0);
                    rect2.top = getPaddingTop();
                    rect2.right = h(rect.right, zD0);
                } else {
                    rect2.left = this.H.getPaddingLeft() + rect.left;
                    rect2.top = rect.top - c();
                    rect2.right = rect.right - this.H.getPaddingRight();
                }
                int i9 = rect2.left;
                int i10 = rect2.top;
                int i11 = rect2.right;
                int i12 = rect2.bottom;
                Rect rect3 = sfVar.h;
                if (rect3.left != i9 || rect3.top != i10 || rect3.right != i11 || rect3.bottom != i12) {
                    rect3.set(i9, i10, i11, i12);
                    sfVar.S = true;
                }
                if (this.H == null) {
                    throw new IllegalStateException();
                }
                TextPaint textPaint = sfVar.U;
                textPaint.setTextSize(sfVar.l);
                textPaint.setTypeface(sfVar.z);
                textPaint.setLetterSpacing(sfVar.g0);
                float f = -textPaint.ascent();
                rect2.left = this.H.getCompoundPaddingLeft() + rect.left;
                rect2.top = (this.v0 != 1 || this.H.getMinLines() > 1) ? rect.top + this.H.getCompoundPaddingTop() : (int) (rect.centerY() - (f / 2.0f));
                rect2.right = rect.right - this.H.getCompoundPaddingRight();
                int compoundPaddingBottom = (this.v0 != 1 || this.H.getMinLines() > 1) ? rect.bottom - this.H.getCompoundPaddingBottom() : (int) (rect2.top + f);
                rect2.bottom = compoundPaddingBottom;
                int i13 = rect2.left;
                int i14 = rect2.top;
                int i15 = rect2.right;
                Rect rect4 = sfVar.g;
                if (rect4.left != i13 || rect4.top != i14 || rect4.right != i15 || rect4.bottom != compoundPaddingBottom) {
                    rect4.set(i13, i14, i15, compoundPaddingBottom);
                    sfVar.S = true;
                }
                sfVar.i(false);
                if (!e() || this.Y0) {
                    return;
                }
                j();
            }
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        EditText editText;
        super.onMeasure(i, i2);
        boolean z = this.f1;
        xt xtVar = this.G;
        if (!z) {
            xtVar.getViewTreeObserver().addOnGlobalLayoutListener(this);
            this.f1 = true;
        }
        if (this.a0 != null && (editText = this.H) != null) {
            this.a0.setGravity(editText.getGravity());
            this.a0.setPadding(this.H.getCompoundPaddingLeft(), this.H.getCompoundPaddingTop(), this.H.getCompoundPaddingRight(), this.H.getCompoundPaddingBottom());
        }
        xtVar.m();
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.E);
        setError(savedState.G);
        if (savedState.H) {
            post(new f(24, this));
        }
        requestLayout();
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onRtlPropertiesChanged(int i) {
        super.onRtlPropertiesChanged(i);
        boolean z = i == 1;
        if (z != this.t0) {
            yi yiVar = this.s0.e;
            RectF rectF = this.E0;
            float fA = yiVar.a(rectF);
            float fA2 = this.s0.f.a(rectF);
            float fA3 = this.s0.h.a(rectF);
            float fA4 = this.s0.g.a(rectF);
            zu0 zu0Var = this.s0;
            z91 z91Var = zu0Var.a;
            z91 z91Var2 = zu0Var.b;
            z91 z91Var3 = zu0Var.d;
            z91 z91Var4 = zu0Var.c;
            is isVar = new is(0);
            is isVar2 = new is(0);
            is isVar3 = new is(0);
            is isVar4 = new is(0);
            xu0.b(z91Var2);
            xu0.b(z91Var);
            xu0.b(z91Var4);
            xu0.b(z91Var3);
            o0 o0Var = new o0(fA2);
            o0 o0Var2 = new o0(fA);
            o0 o0Var3 = new o0(fA4);
            o0 o0Var4 = new o0(fA3);
            zu0 zu0Var2 = new zu0();
            zu0Var2.a = z91Var2;
            zu0Var2.b = z91Var;
            zu0Var2.c = z91Var3;
            zu0Var2.d = z91Var4;
            zu0Var2.e = o0Var;
            zu0Var2.f = o0Var2;
            zu0Var2.g = o0Var4;
            zu0Var2.h = o0Var3;
            zu0Var2.i = isVar;
            zu0Var2.j = isVar2;
            zu0Var2.k = isVar3;
            zu0Var2.l = isVar4;
            this.t0 = z;
            setShapeAppearanceModel(zu0Var2);
        }
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        if (m()) {
            savedState.G = getError();
        }
        xt xtVar = this.G;
        savedState.H = xtVar.M != 0 && xtVar.K.H;
        return savedState;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0012  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void p() {
        /*
            r4 = this;
            android.content.res.ColorStateList r0 = r4.h0
            if (r0 == 0) goto L5
            goto L25
        L5:
            android.content.Context r0 = r4.getContext()
            int r1 = com.google.android.material.R$attr.colorControlActivated
            android.util.TypedValue r1 = defpackage.s90.S(r0, r1)
            r2 = 0
            if (r1 != 0) goto L14
        L12:
            r0 = r2
            goto L25
        L14:
            int r3 = r1.resourceId
            if (r3 == 0) goto L1d
            android.content.res.ColorStateList r0 = defpackage.t40.Y(r0, r3)
            goto L25
        L1d:
            int r0 = r1.data
            if (r0 == 0) goto L12
            android.content.res.ColorStateList r0 = android.content.res.ColorStateList.valueOf(r0)
        L25:
            android.widget.EditText r1 = r4.H
            if (r1 == 0) goto L50
            android.graphics.drawable.Drawable r1 = defpackage.a2.e(r1)
            if (r1 != 0) goto L30
            goto L50
        L30:
            android.widget.EditText r1 = r4.H
            android.graphics.drawable.Drawable r1 = defpackage.a2.e(r1)
            android.graphics.drawable.Drawable r1 = r1.mutate()
            boolean r2 = r4.m()
            if (r2 != 0) goto L48
            androidx.appcompat.widget.AppCompatTextView r2 = r4.S
            if (r2 == 0) goto L4d
            boolean r2 = r4.Q
            if (r2 == 0) goto L4d
        L48:
            android.content.res.ColorStateList r2 = r4.i0
            if (r2 == 0) goto L4d
            r0 = r2
        L4d:
            defpackage.ip.h(r1, r0)
        L50:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.p():void");
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x005f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean q() {
        /*
            Method dump skipped, instruction units count: 304
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.q():boolean");
    }

    public final void r() {
        Drawable background;
        AppCompatTextView appCompatTextView;
        EditText editText = this.H;
        if (editText == null || this.v0 != 0 || (background = editText.getBackground()) == null) {
            return;
        }
        int[] iArr = op.a;
        Drawable drawableMutate = background.mutate();
        if (m()) {
            drawableMutate.setColorFilter(f6.c(getErrorCurrentTextColors(), PorterDuff.Mode.SRC_IN));
        } else if (this.Q && (appCompatTextView = this.S) != null) {
            drawableMutate.setColorFilter(f6.c(appCompatTextView.getCurrentTextColor(), PorterDuff.Mode.SRC_IN));
        } else {
            drawableMutate.clearColorFilter();
            this.H.refreshDrawableState();
        }
    }

    public void removeOnEditTextAttachedListener(zz0 zz0Var) {
        this.I0.remove(zz0Var);
    }

    public void removeOnEndIconChangedListener(a01 a01Var) {
        this.G.removeOnEndIconChangedListener(a01Var);
    }

    public final void s() {
        EditText editText = this.H;
        if (editText == null || this.m0 == null) {
            return;
        }
        if ((this.p0 || editText.getBackground() == null) && this.v0 != 0) {
            Drawable editTextBoxBackground = getEditTextBoxBackground();
            EditText editText2 = this.H;
            WeakHashMap weakHashMap = f51.a;
            editText2.setBackground(editTextBoxBackground);
            this.p0 = true;
        }
    }

    public void setBoxBackgroundColor(int i) {
        if (this.B0 != i) {
            this.B0 = i;
            this.S0 = i;
            this.U0 = i;
            this.V0 = i;
            b();
        }
    }

    public void setBoxBackgroundColorResource(int i) {
        setBoxBackgroundColor(qi.a(getContext(), i));
    }

    public void setBoxBackgroundColorStateList(ColorStateList colorStateList) {
        int defaultColor = colorStateList.getDefaultColor();
        this.S0 = defaultColor;
        this.B0 = defaultColor;
        this.T0 = colorStateList.getColorForState(new int[]{-16842910}, -1);
        this.U0 = colorStateList.getColorForState(new int[]{R.attr.state_focused, R.attr.state_enabled}, -1);
        this.V0 = colorStateList.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, -1);
        b();
    }

    public void setBoxBackgroundMode(int i) {
        if (i == this.v0) {
            return;
        }
        this.v0 = i;
        if (this.H != null) {
            i();
        }
    }

    public void setBoxCollapsedPaddingTop(int i) {
        this.w0 = i;
    }

    public void setBoxCornerFamily(int i) {
        xu0 xu0VarG = this.s0.g();
        yi yiVar = this.s0.e;
        z91 z91VarN = ma0.n(i);
        xu0VarG.a = z91VarN;
        xu0.b(z91VarN);
        xu0VarG.e = yiVar;
        yi yiVar2 = this.s0.f;
        z91 z91VarN2 = ma0.n(i);
        xu0VarG.b = z91VarN2;
        xu0.b(z91VarN2);
        xu0VarG.f = yiVar2;
        yi yiVar3 = this.s0.h;
        z91 z91VarN3 = ma0.n(i);
        xu0VarG.d = z91VarN3;
        xu0.b(z91VarN3);
        xu0VarG.h = yiVar3;
        yi yiVar4 = this.s0.g;
        z91 z91VarN4 = ma0.n(i);
        xu0VarG.c = z91VarN4;
        xu0.b(z91VarN4);
        xu0VarG.g = yiVar4;
        this.s0 = xu0VarG.a();
        b();
    }

    public void setBoxStrokeColor(int i) {
        if (this.Q0 != i) {
            this.Q0 = i;
            x();
        }
    }

    public void setBoxStrokeColorStateList(ColorStateList colorStateList) {
        if (colorStateList.isStateful()) {
            this.O0 = colorStateList.getDefaultColor();
            this.W0 = colorStateList.getColorForState(new int[]{-16842910}, -1);
            this.P0 = colorStateList.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, -1);
            this.Q0 = colorStateList.getColorForState(new int[]{R.attr.state_focused, R.attr.state_enabled}, -1);
        } else if (this.Q0 != colorStateList.getDefaultColor()) {
            this.Q0 = colorStateList.getDefaultColor();
        }
        x();
    }

    public void setBoxStrokeErrorColor(ColorStateList colorStateList) {
        if (this.R0 != colorStateList) {
            this.R0 = colorStateList;
            x();
        }
    }

    public void setBoxStrokeWidth(int i) {
        this.y0 = i;
        x();
    }

    public void setBoxStrokeWidthFocused(int i) {
        this.z0 = i;
        x();
    }

    public void setBoxStrokeWidthFocusedResource(int i) {
        setBoxStrokeWidthFocused(getResources().getDimensionPixelSize(i));
    }

    public void setBoxStrokeWidthResource(int i) {
        setBoxStrokeWidth(getResources().getDimensionPixelSize(i));
    }

    public void setCounterEnabled(boolean z) {
        if (this.O != z) {
            e40 e40Var = this.N;
            if (z) {
                AppCompatTextView appCompatTextView = new AppCompatTextView(getContext());
                this.S = appCompatTextView;
                appCompatTextView.setId(R$id.textinput_counter);
                Typeface typeface = this.F0;
                if (typeface != null) {
                    this.S.setTypeface(typeface);
                }
                this.S.setMaxLines(1);
                e40Var.a(this.S, 2);
                ((ViewGroup.MarginLayoutParams) this.S.getLayoutParams()).setMarginStart(getResources().getDimensionPixelOffset(R$dimen.mtrl_textinput_counter_margin_start));
                o();
                if (this.S != null) {
                    EditText editText = this.H;
                    n(editText != null ? editText.getText() : null);
                }
            } else {
                e40Var.g(this.S, 2);
                this.S = null;
            }
            this.O = z;
        }
    }

    public void setCounterMaxLength(int i) {
        if (this.P != i) {
            if (i > 0) {
                this.P = i;
            } else {
                this.P = -1;
            }
            if (!this.O || this.S == null) {
                return;
            }
            EditText editText = this.H;
            n(editText == null ? null : editText.getText());
        }
    }

    public void setCounterOverflowTextAppearance(int i) {
        if (this.T != i) {
            this.T = i;
            o();
        }
    }

    public void setCounterOverflowTextColor(ColorStateList colorStateList) {
        if (this.g0 != colorStateList) {
            this.g0 = colorStateList;
            o();
        }
    }

    public void setCounterTextAppearance(int i) {
        if (this.U != i) {
            this.U = i;
            o();
        }
    }

    public void setCounterTextColor(ColorStateList colorStateList) {
        if (this.f0 != colorStateList) {
            this.f0 = colorStateList;
            o();
        }
    }

    public void setCursorColor(ColorStateList colorStateList) {
        if (this.h0 != colorStateList) {
            this.h0 = colorStateList;
            p();
        }
    }

    public void setCursorErrorColor(ColorStateList colorStateList) {
        if (this.i0 != colorStateList) {
            this.i0 = colorStateList;
            if (m() || (this.S != null && this.Q)) {
                p();
            }
        }
    }

    public void setDefaultHintTextColor(ColorStateList colorStateList) {
        this.M0 = colorStateList;
        this.N0 = colorStateList;
        if (this.H != null) {
            u(false, false);
        }
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        k(this, z);
        super.setEnabled(z);
    }

    public void setEndIconActivated(boolean z) {
        this.G.K.setActivated(z);
    }

    public void setEndIconCheckable(boolean z) {
        this.G.K.setCheckable(z);
    }

    public void setEndIconContentDescription(int i) {
        xt xtVar = this.G;
        CharSequence text = i != 0 ? xtVar.getResources().getText(i) : null;
        CheckableImageButton checkableImageButton = xtVar.K;
        if (checkableImageButton.getContentDescription() != text) {
            checkableImageButton.setContentDescription(text);
        }
    }

    public void setEndIconDrawable(int i) {
        xt xtVar = this.G;
        Drawable drawableZ = i != 0 ? qm.z(xtVar.getContext(), i) : null;
        CheckableImageButton checkableImageButton = xtVar.K;
        checkableImageButton.setImageDrawable(drawableZ);
        if (drawableZ != null) {
            ColorStateList colorStateList = xtVar.O;
            PorterDuff.Mode mode = xtVar.P;
            TextInputLayout textInputLayout = xtVar.E;
            l21.l(textInputLayout, checkableImageButton, colorStateList, mode);
            l21.N0(textInputLayout, checkableImageButton, xtVar.O);
        }
    }

    public void setEndIconMinSize(int i) {
        xt xtVar = this.G;
        if (i < 0) {
            xtVar.getClass();
            throw new IllegalArgumentException("endIconSize cannot be less than 0");
        }
        if (i != xtVar.Q) {
            xtVar.Q = i;
            CheckableImageButton checkableImageButton = xtVar.K;
            checkableImageButton.setMinimumWidth(i);
            checkableImageButton.setMinimumHeight(i);
            CheckableImageButton checkableImageButton2 = xtVar.G;
            checkableImageButton2.setMinimumWidth(i);
            checkableImageButton2.setMinimumHeight(i);
        }
    }

    public void setEndIconMode(int i) {
        this.G.g(i);
    }

    public void setEndIconOnClickListener(View.OnClickListener onClickListener) {
        this.G.setEndIconOnClickListener(onClickListener);
    }

    public void setEndIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.G.setEndIconOnLongClickListener(onLongClickListener);
    }

    public void setEndIconScaleType(ImageView.ScaleType scaleType) {
        xt xtVar = this.G;
        xtVar.R = scaleType;
        xtVar.K.setScaleType(scaleType);
        xtVar.G.setScaleType(scaleType);
    }

    public void setEndIconTintList(ColorStateList colorStateList) {
        xt xtVar = this.G;
        if (xtVar.O != colorStateList) {
            xtVar.O = colorStateList;
            l21.l(xtVar.E, xtVar.K, colorStateList, xtVar.P);
        }
    }

    public void setEndIconTintMode(PorterDuff.Mode mode) {
        xt xtVar = this.G;
        if (xtVar.P != mode) {
            xtVar.P = mode;
            l21.l(xtVar.E, xtVar.K, xtVar.O, mode);
        }
    }

    public void setEndIconVisible(boolean z) {
        this.G.h(z);
    }

    public void setError(CharSequence charSequence) {
        e40 e40Var = this.N;
        if (!e40Var.q) {
            if (TextUtils.isEmpty(charSequence)) {
                return;
            } else {
                setErrorEnabled(true);
            }
        }
        if (TextUtils.isEmpty(charSequence)) {
            e40Var.f();
            return;
        }
        e40Var.c();
        e40Var.p = charSequence;
        e40Var.r.setText(charSequence);
        int i = e40Var.n;
        if (i != 1) {
            e40Var.o = 1;
        }
        e40Var.i(i, e40Var.o, e40Var.h(e40Var.r, charSequence));
    }

    public void setErrorAccessibilityLiveRegion(int i) {
        e40 e40Var = this.N;
        e40Var.t = i;
        AppCompatTextView appCompatTextView = e40Var.r;
        if (appCompatTextView != null) {
            WeakHashMap weakHashMap = f51.a;
            appCompatTextView.setAccessibilityLiveRegion(i);
        }
    }

    public void setErrorContentDescription(CharSequence charSequence) {
        e40 e40Var = this.N;
        e40Var.s = charSequence;
        AppCompatTextView appCompatTextView = e40Var.r;
        if (appCompatTextView != null) {
            appCompatTextView.setContentDescription(charSequence);
        }
    }

    public void setErrorEnabled(boolean z) {
        e40 e40Var = this.N;
        if (e40Var.q == z) {
            return;
        }
        e40Var.c();
        TextInputLayout textInputLayout = e40Var.h;
        if (z) {
            AppCompatTextView appCompatTextView = new AppCompatTextView(e40Var.g);
            e40Var.r = appCompatTextView;
            appCompatTextView.setId(R$id.textinput_error);
            e40Var.r.setTextAlignment(5);
            Typeface typeface = e40Var.B;
            if (typeface != null) {
                e40Var.r.setTypeface(typeface);
            }
            int i = e40Var.u;
            e40Var.u = i;
            AppCompatTextView appCompatTextView2 = e40Var.r;
            if (appCompatTextView2 != null) {
                textInputLayout.l(appCompatTextView2, i);
            }
            ColorStateList colorStateList = e40Var.v;
            e40Var.v = colorStateList;
            AppCompatTextView appCompatTextView3 = e40Var.r;
            if (appCompatTextView3 != null && colorStateList != null) {
                appCompatTextView3.setTextColor(colorStateList);
            }
            CharSequence charSequence = e40Var.s;
            e40Var.s = charSequence;
            AppCompatTextView appCompatTextView4 = e40Var.r;
            if (appCompatTextView4 != null) {
                appCompatTextView4.setContentDescription(charSequence);
            }
            int i2 = e40Var.t;
            e40Var.t = i2;
            AppCompatTextView appCompatTextView5 = e40Var.r;
            if (appCompatTextView5 != null) {
                WeakHashMap weakHashMap = f51.a;
                appCompatTextView5.setAccessibilityLiveRegion(i2);
            }
            e40Var.r.setVisibility(4);
            e40Var.a(e40Var.r, 0);
        } else {
            e40Var.f();
            e40Var.g(e40Var.r, 0);
            e40Var.r = null;
            textInputLayout.r();
            textInputLayout.x();
        }
        e40Var.q = z;
    }

    public void setErrorIconDrawable(int i) {
        xt xtVar = this.G;
        xtVar.i(i != 0 ? qm.z(xtVar.getContext(), i) : null);
        l21.N0(xtVar.E, xtVar.G, xtVar.H);
    }

    public void setErrorIconOnClickListener(View.OnClickListener onClickListener) {
        this.G.setErrorIconOnClickListener(onClickListener);
    }

    public void setErrorIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.G.setErrorIconOnLongClickListener(onLongClickListener);
    }

    public void setErrorIconTintList(ColorStateList colorStateList) {
        xt xtVar = this.G;
        if (xtVar.H != colorStateList) {
            xtVar.H = colorStateList;
            l21.l(xtVar.E, xtVar.G, colorStateList, xtVar.I);
        }
    }

    public void setErrorIconTintMode(PorterDuff.Mode mode) {
        xt xtVar = this.G;
        if (xtVar.I != mode) {
            xtVar.I = mode;
            l21.l(xtVar.E, xtVar.G, xtVar.H, mode);
        }
    }

    public void setErrorTextAppearance(int i) {
        e40 e40Var = this.N;
        e40Var.u = i;
        AppCompatTextView appCompatTextView = e40Var.r;
        if (appCompatTextView != null) {
            e40Var.h.l(appCompatTextView, i);
        }
    }

    public void setErrorTextColor(ColorStateList colorStateList) {
        e40 e40Var = this.N;
        e40Var.v = colorStateList;
        AppCompatTextView appCompatTextView = e40Var.r;
        if (appCompatTextView == null || colorStateList == null) {
            return;
        }
        appCompatTextView.setTextColor(colorStateList);
    }

    public void setExpandedHintEnabled(boolean z) {
        if (this.a1 != z) {
            this.a1 = z;
            u(false, false);
        }
    }

    public void setHelperText(CharSequence charSequence) {
        boolean zIsEmpty = TextUtils.isEmpty(charSequence);
        e40 e40Var = this.N;
        if (zIsEmpty) {
            if (e40Var.x) {
                setHelperTextEnabled(false);
                return;
            }
            return;
        }
        if (!e40Var.x) {
            setHelperTextEnabled(true);
        }
        e40Var.c();
        e40Var.w = charSequence;
        e40Var.y.setText(charSequence);
        int i = e40Var.n;
        if (i != 2) {
            e40Var.o = 2;
        }
        e40Var.i(i, e40Var.o, e40Var.h(e40Var.y, charSequence));
    }

    public void setHelperTextColor(ColorStateList colorStateList) {
        e40 e40Var = this.N;
        e40Var.A = colorStateList;
        AppCompatTextView appCompatTextView = e40Var.y;
        if (appCompatTextView == null || colorStateList == null) {
            return;
        }
        appCompatTextView.setTextColor(colorStateList);
    }

    public void setHelperTextEnabled(boolean z) {
        e40 e40Var = this.N;
        if (e40Var.x == z) {
            return;
        }
        e40Var.c();
        if (z) {
            AppCompatTextView appCompatTextView = new AppCompatTextView(e40Var.g);
            e40Var.y = appCompatTextView;
            appCompatTextView.setId(R$id.textinput_helper_text);
            e40Var.y.setTextAlignment(5);
            Typeface typeface = e40Var.B;
            if (typeface != null) {
                e40Var.y.setTypeface(typeface);
            }
            e40Var.y.setVisibility(4);
            AppCompatTextView appCompatTextView2 = e40Var.y;
            WeakHashMap weakHashMap = f51.a;
            appCompatTextView2.setAccessibilityLiveRegion(1);
            int i = e40Var.z;
            e40Var.z = i;
            AppCompatTextView appCompatTextView3 = e40Var.y;
            if (appCompatTextView3 != null) {
                appCompatTextView3.setTextAppearance(i);
            }
            ColorStateList colorStateList = e40Var.A;
            e40Var.A = colorStateList;
            AppCompatTextView appCompatTextView4 = e40Var.y;
            if (appCompatTextView4 != null && colorStateList != null) {
                appCompatTextView4.setTextColor(colorStateList);
            }
            e40Var.a(e40Var.y, 1);
            e40Var.y.setAccessibilityDelegate(new d40(e40Var));
        } else {
            e40Var.c();
            int i2 = e40Var.n;
            if (i2 == 2) {
                e40Var.o = 0;
            }
            e40Var.i(i2, e40Var.o, e40Var.h(e40Var.y, ""));
            e40Var.g(e40Var.y, 1);
            e40Var.y = null;
            TextInputLayout textInputLayout = e40Var.h;
            textInputLayout.r();
            textInputLayout.x();
        }
        e40Var.x = z;
    }

    public void setHelperTextTextAppearance(int i) {
        e40 e40Var = this.N;
        e40Var.z = i;
        AppCompatTextView appCompatTextView = e40Var.y;
        if (appCompatTextView != null) {
            appCompatTextView.setTextAppearance(i);
        }
    }

    public void setHint(CharSequence charSequence) {
        if (this.j0) {
            setHintInternal(charSequence);
            sendAccessibilityEvent(2048);
        }
    }

    public void setHintAnimationEnabled(boolean z) {
        this.b1 = z;
    }

    public void setHintEnabled(boolean z) {
        if (z != this.j0) {
            this.j0 = z;
            if (z) {
                CharSequence hint = this.H.getHint();
                if (!TextUtils.isEmpty(hint)) {
                    if (TextUtils.isEmpty(this.k0)) {
                        setHint(hint);
                    }
                    this.H.setHint((CharSequence) null);
                }
                this.l0 = true;
            } else {
                this.l0 = false;
                if (!TextUtils.isEmpty(this.k0) && TextUtils.isEmpty(this.H.getHint())) {
                    this.H.setHint(this.k0);
                }
                setHintInternal(null);
            }
            if (this.H != null) {
                t();
            }
        }
    }

    public void setHintTextAppearance(int i) {
        sf sfVar = this.Z0;
        sfVar.k(i);
        this.N0 = sfVar.o;
        if (this.H != null) {
            u(false, false);
            t();
        }
    }

    public void setHintTextColor(ColorStateList colorStateList) {
        if (this.N0 != colorStateList) {
            if (this.M0 == null) {
                sf sfVar = this.Z0;
                if (sfVar.o != colorStateList) {
                    sfVar.o = colorStateList;
                    sfVar.i(false);
                }
            }
            this.N0 = colorStateList;
            if (this.H != null) {
                u(false, false);
            }
        }
    }

    public void setLengthCounter(yz0 yz0Var) {
        this.R = yz0Var;
    }

    public void setMaxEms(int i) {
        this.K = i;
        EditText editText = this.H;
        if (editText == null || i == -1) {
            return;
        }
        editText.setMaxEms(i);
    }

    public void setMaxWidth(int i) {
        this.M = i;
        EditText editText = this.H;
        if (editText == null || i == -1) {
            return;
        }
        editText.setMaxWidth(i);
    }

    public void setMaxWidthResource(int i) {
        setMaxWidth(getContext().getResources().getDimensionPixelSize(i));
    }

    public void setMinEms(int i) {
        this.J = i;
        EditText editText = this.H;
        if (editText == null || i == -1) {
            return;
        }
        editText.setMinEms(i);
    }

    public void setMinWidth(int i) {
        this.L = i;
        EditText editText = this.H;
        if (editText == null || i == -1) {
            return;
        }
        editText.setMinWidth(i);
    }

    public void setMinWidthResource(int i) {
        setMinWidth(getContext().getResources().getDimensionPixelSize(i));
    }

    @Deprecated
    public void setPasswordVisibilityToggleContentDescription(int i) {
        xt xtVar = this.G;
        xtVar.K.setContentDescription(i != 0 ? xtVar.getResources().getText(i) : null);
    }

    @Deprecated
    public void setPasswordVisibilityToggleDrawable(int i) {
        xt xtVar = this.G;
        xtVar.K.setImageDrawable(i != 0 ? qm.z(xtVar.getContext(), i) : null);
    }

    @Deprecated
    public void setPasswordVisibilityToggleEnabled(boolean z) {
        xt xtVar = this.G;
        if (z && xtVar.M != 1) {
            xtVar.g(1);
        } else if (z) {
            xtVar.getClass();
        } else {
            xtVar.g(0);
        }
    }

    @Deprecated
    public void setPasswordVisibilityToggleTintList(ColorStateList colorStateList) {
        xt xtVar = this.G;
        xtVar.O = colorStateList;
        l21.l(xtVar.E, xtVar.K, colorStateList, xtVar.P);
    }

    @Deprecated
    public void setPasswordVisibilityToggleTintMode(PorterDuff.Mode mode) {
        xt xtVar = this.G;
        xtVar.P = mode;
        l21.l(xtVar.E, xtVar.K, xtVar.O, mode);
    }

    public void setPlaceholderText(CharSequence charSequence) {
        if (this.a0 == null) {
            AppCompatTextView appCompatTextView = new AppCompatTextView(getContext());
            this.a0 = appCompatTextView;
            appCompatTextView.setId(R$id.textinput_placeholder);
            AppCompatTextView appCompatTextView2 = this.a0;
            WeakHashMap weakHashMap = f51.a;
            appCompatTextView2.setImportantForAccessibility(2);
            Fade fadeD = d();
            this.d0 = fadeD;
            fadeD.F = 67L;
            this.e0 = d();
            setPlaceholderTextAppearance(this.c0);
            setPlaceholderTextColor(this.b0);
        }
        if (TextUtils.isEmpty(charSequence)) {
            setPlaceholderTextEnabled(false);
        } else {
            if (!this.W) {
                setPlaceholderTextEnabled(true);
            }
            this.V = charSequence;
        }
        EditText editText = this.H;
        v(editText == null ? null : editText.getText());
    }

    public void setPlaceholderTextAppearance(int i) {
        this.c0 = i;
        AppCompatTextView appCompatTextView = this.a0;
        if (appCompatTextView != null) {
            appCompatTextView.setTextAppearance(i);
        }
    }

    public void setPlaceholderTextColor(ColorStateList colorStateList) {
        if (this.b0 != colorStateList) {
            this.b0 = colorStateList;
            AppCompatTextView appCompatTextView = this.a0;
            if (appCompatTextView == null || colorStateList == null) {
                return;
            }
            appCompatTextView.setTextColor(colorStateList);
        }
    }

    public void setPrefixText(CharSequence charSequence) {
        ax0 ax0Var = this.F;
        ax0Var.getClass();
        ax0Var.G = TextUtils.isEmpty(charSequence) ? null : charSequence;
        ax0Var.F.setText(charSequence);
        ax0Var.e();
    }

    public void setPrefixTextAppearance(int i) {
        this.F.F.setTextAppearance(i);
    }

    public void setPrefixTextColor(ColorStateList colorStateList) {
        this.F.F.setTextColor(colorStateList);
    }

    public void setShapeAppearanceModel(zu0 zu0Var) {
        ld0 ld0Var = this.m0;
        if (ld0Var == null || ld0Var.E.a == zu0Var) {
            return;
        }
        this.s0 = zu0Var;
        b();
    }

    public void setStartIconCheckable(boolean z) {
        this.F.H.setCheckable(z);
    }

    public void setStartIconContentDescription(CharSequence charSequence) {
        CheckableImageButton checkableImageButton = this.F.H;
        if (checkableImageButton.getContentDescription() != charSequence) {
            checkableImageButton.setContentDescription(charSequence);
        }
    }

    public void setStartIconDrawable(int i) {
        setStartIconDrawable(i != 0 ? qm.z(getContext(), i) : null);
    }

    public void setStartIconMinSize(int i) {
        ax0 ax0Var = this.F;
        if (i < 0) {
            ax0Var.getClass();
            throw new IllegalArgumentException("startIconSize cannot be less than 0");
        }
        if (i != ax0Var.K) {
            ax0Var.K = i;
            CheckableImageButton checkableImageButton = ax0Var.H;
            checkableImageButton.setMinimumWidth(i);
            checkableImageButton.setMinimumHeight(i);
        }
    }

    public void setStartIconOnClickListener(View.OnClickListener onClickListener) {
        this.F.setStartIconOnClickListener(onClickListener);
    }

    public void setStartIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.F.setStartIconOnLongClickListener(onLongClickListener);
    }

    public void setStartIconScaleType(ImageView.ScaleType scaleType) {
        ax0 ax0Var = this.F;
        ax0Var.L = scaleType;
        ax0Var.H.setScaleType(scaleType);
    }

    public void setStartIconTintList(ColorStateList colorStateList) {
        ax0 ax0Var = this.F;
        if (ax0Var.I != colorStateList) {
            ax0Var.I = colorStateList;
            l21.l(ax0Var.E, ax0Var.H, colorStateList, ax0Var.J);
        }
    }

    public void setStartIconTintMode(PorterDuff.Mode mode) {
        ax0 ax0Var = this.F;
        if (ax0Var.J != mode) {
            ax0Var.J = mode;
            l21.l(ax0Var.E, ax0Var.H, ax0Var.I, mode);
        }
    }

    public void setStartIconVisible(boolean z) {
        this.F.c(z);
    }

    public void setSuffixText(CharSequence charSequence) {
        xt xtVar = this.G;
        xtVar.getClass();
        xtVar.T = TextUtils.isEmpty(charSequence) ? null : charSequence;
        xtVar.U.setText(charSequence);
        xtVar.n();
    }

    public void setSuffixTextAppearance(int i) {
        this.G.U.setTextAppearance(i);
    }

    public void setSuffixTextColor(ColorStateList colorStateList) {
        this.G.U.setTextColor(colorStateList);
    }

    public void setTextInputAccessibilityDelegate(xz0 xz0Var) {
        EditText editText = this.H;
        if (editText != null) {
            f51.o(editText, xz0Var);
        }
    }

    public void setTypeface(Typeface typeface) {
        if (typeface != this.F0) {
            this.F0 = typeface;
            sf sfVar = this.Z0;
            boolean zM = sfVar.m(typeface);
            boolean zO = sfVar.o(typeface);
            if (zM || zO) {
                sfVar.i(false);
            }
            e40 e40Var = this.N;
            if (typeface != e40Var.B) {
                e40Var.B = typeface;
                AppCompatTextView appCompatTextView = e40Var.r;
                if (appCompatTextView != null) {
                    appCompatTextView.setTypeface(typeface);
                }
                AppCompatTextView appCompatTextView2 = e40Var.y;
                if (appCompatTextView2 != null) {
                    appCompatTextView2.setTypeface(typeface);
                }
            }
            AppCompatTextView appCompatTextView3 = this.S;
            if (appCompatTextView3 != null) {
                appCompatTextView3.setTypeface(typeface);
            }
        }
    }

    public final void t() {
        if (this.v0 != 1) {
            FrameLayout frameLayout = this.E;
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) frameLayout.getLayoutParams();
            int iC = c();
            if (iC != layoutParams.topMargin) {
                layoutParams.topMargin = iC;
                frameLayout.requestLayout();
            }
        }
    }

    public final void u(boolean z, boolean z2) {
        ColorStateList colorStateList;
        AppCompatTextView appCompatTextView;
        boolean zIsEnabled = isEnabled();
        EditText editText = this.H;
        boolean z3 = (editText == null || TextUtils.isEmpty(editText.getText())) ? false : true;
        EditText editText2 = this.H;
        boolean z4 = editText2 != null && editText2.hasFocus();
        ColorStateList colorStateList2 = this.M0;
        sf sfVar = this.Z0;
        if (colorStateList2 != null) {
            sfVar.j(colorStateList2);
        }
        if (!zIsEnabled) {
            ColorStateList colorStateList3 = this.M0;
            sfVar.j(ColorStateList.valueOf(colorStateList3 != null ? colorStateList3.getColorForState(new int[]{-16842910}, this.W0) : this.W0));
        } else if (m()) {
            AppCompatTextView appCompatTextView2 = this.N.r;
            sfVar.j(appCompatTextView2 != null ? appCompatTextView2.getTextColors() : null);
        } else if (this.Q && (appCompatTextView = this.S) != null) {
            sfVar.j(appCompatTextView.getTextColors());
        } else if (z4 && (colorStateList = this.N0) != null && sfVar.o != colorStateList) {
            sfVar.o = colorStateList;
            sfVar.i(false);
        }
        xt xtVar = this.G;
        ax0 ax0Var = this.F;
        if (z3 || !this.a1 || (isEnabled() && z4)) {
            if (z2 || this.Y0) {
                ValueAnimator valueAnimator = this.c1;
                if (valueAnimator != null && valueAnimator.isRunning()) {
                    this.c1.cancel();
                }
                if (z && this.b1) {
                    a(1.0f);
                } else {
                    sfVar.p(1.0f);
                }
                this.Y0 = false;
                if (e()) {
                    j();
                }
                EditText editText3 = this.H;
                v(editText3 != null ? editText3.getText() : null);
                ax0Var.N = false;
                ax0Var.e();
                xtVar.V = false;
                xtVar.n();
                return;
            }
            return;
        }
        if (z2 || !this.Y0) {
            ValueAnimator valueAnimator2 = this.c1;
            if (valueAnimator2 != null && valueAnimator2.isRunning()) {
                this.c1.cancel();
            }
            if (z && this.b1) {
                a(0.0f);
            } else {
                sfVar.p(0.0f);
            }
            if (e() && !((jk) this.m0).c0.s.isEmpty() && e()) {
                ((jk) this.m0).w(0.0f, 0.0f, 0.0f, 0.0f);
            }
            this.Y0 = true;
            AppCompatTextView appCompatTextView3 = this.a0;
            if (appCompatTextView3 != null && this.W) {
                appCompatTextView3.setText((CharSequence) null);
                v11.a(this.E, this.e0);
                this.a0.setVisibility(4);
            }
            ax0Var.N = true;
            ax0Var.e();
            xtVar.V = true;
            xtVar.n();
        }
    }

    public final void v(Editable editable) {
        ((no) this.R).getClass();
        int length = editable != null ? editable.length() : 0;
        FrameLayout frameLayout = this.E;
        if (length != 0 || this.Y0) {
            AppCompatTextView appCompatTextView = this.a0;
            if (appCompatTextView == null || !this.W) {
                return;
            }
            appCompatTextView.setText((CharSequence) null);
            v11.a(frameLayout, this.e0);
            this.a0.setVisibility(4);
            return;
        }
        if (this.a0 == null || !this.W || TextUtils.isEmpty(this.V)) {
            return;
        }
        this.a0.setText(this.V);
        v11.a(frameLayout, this.d0);
        this.a0.setVisibility(0);
        this.a0.bringToFront();
        announceForAccessibility(this.V);
    }

    public final void w(boolean z, boolean z2) {
        int defaultColor = this.R0.getDefaultColor();
        int colorForState = this.R0.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, defaultColor);
        int colorForState2 = this.R0.getColorForState(new int[]{R.attr.state_activated, R.attr.state_enabled}, defaultColor);
        if (z) {
            this.A0 = colorForState2;
        } else if (z2) {
            this.A0 = colorForState;
        } else {
            this.A0 = defaultColor;
        }
    }

    public final void x() {
        AppCompatTextView appCompatTextView;
        EditText editText;
        EditText editText2;
        if (this.m0 == null || this.v0 == 0) {
            return;
        }
        boolean z = false;
        boolean z2 = isFocused() || ((editText2 = this.H) != null && editText2.hasFocus());
        if (isHovered() || ((editText = this.H) != null && editText.isHovered())) {
            z = true;
        }
        if (!isEnabled()) {
            this.A0 = this.W0;
        } else if (m()) {
            if (this.R0 != null) {
                w(z2, z);
            } else {
                this.A0 = getErrorCurrentTextColors();
            }
        } else if (!this.Q || (appCompatTextView = this.S) == null) {
            if (z2) {
                this.A0 = this.Q0;
            } else if (z) {
                this.A0 = this.P0;
            } else {
                this.A0 = this.O0;
            }
        } else if (this.R0 != null) {
            w(z2, z);
        } else {
            this.A0 = appCompatTextView.getCurrentTextColor();
        }
        if (Build.VERSION.SDK_INT >= 29) {
            p();
        }
        xt xtVar = this.G;
        xtVar.l();
        CheckableImageButton checkableImageButton = xtVar.G;
        ColorStateList colorStateList = xtVar.H;
        TextInputLayout textInputLayout = xtVar.E;
        l21.N0(textInputLayout, checkableImageButton, colorStateList);
        ColorStateList colorStateList2 = xtVar.O;
        CheckableImageButton checkableImageButton2 = xtVar.K;
        l21.N0(textInputLayout, checkableImageButton2, colorStateList2);
        if (xtVar.b() instanceof jq) {
            if (!textInputLayout.m() || checkableImageButton2.getDrawable() == null) {
                l21.l(textInputLayout, checkableImageButton2, xtVar.O, xtVar.P);
            } else {
                Drawable drawableMutate = checkableImageButton2.getDrawable().mutate();
                ip.g(drawableMutate, textInputLayout.getErrorCurrentTextColors());
                checkableImageButton2.setImageDrawable(drawableMutate);
            }
        }
        ax0 ax0Var = this.F;
        l21.N0(ax0Var.E, ax0Var.H, ax0Var.I);
        if (this.v0 == 2) {
            int i = this.x0;
            if (z2 && isEnabled()) {
                this.x0 = this.z0;
            } else {
                this.x0 = this.y0;
            }
            if (this.x0 != i && e() && !this.Y0) {
                if (e()) {
                    ((jk) this.m0).w(0.0f, 0.0f, 0.0f, 0.0f);
                }
                j();
            }
        }
        if (this.v0 == 1) {
            if (!isEnabled()) {
                this.B0 = this.T0;
            } else if (z && !z2) {
                this.B0 = this.V0;
            } else if (z2) {
                this.B0 = this.U0;
            } else {
                this.B0 = this.S0;
            }
        }
        b();
    }

    public TextInputLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.textInputStyle);
    }

    public void setStartIconDrawable(Drawable drawable) {
        this.F.b(drawable);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public TextInputLayout(Context context, AttributeSet attributeSet, int i) {
        int i2 = g1;
        super(pd0.a(context, attributeSet, i, i2), attributeSet, i);
        this.J = -1;
        this.K = -1;
        this.L = -1;
        this.M = -1;
        this.N = new e40(this);
        this.R = new no(23);
        this.C0 = new Rect();
        this.D0 = new Rect();
        this.E0 = new RectF();
        this.I0 = new LinkedHashSet();
        sf sfVar = new sf(this);
        this.Z0 = sfVar;
        this.f1 = false;
        Context context2 = getContext();
        setOrientation(1);
        setWillNotDraw(false);
        setAddStatesFromChildren(true);
        FrameLayout frameLayout = new FrameLayout(context2);
        this.E = frameLayout;
        frameLayout.setAddStatesFromChildren(true);
        LinearInterpolator linearInterpolator = y4.a;
        sfVar.W = linearInterpolator;
        sfVar.i(false);
        sfVar.V = linearInterpolator;
        sfVar.i(false);
        sfVar.l(8388659);
        ou ouVarE = i01.e(context2, attributeSet, R$styleable.TextInputLayout, i, i2, R$styleable.TextInputLayout_counterTextAppearance, R$styleable.TextInputLayout_counterOverflowTextAppearance, R$styleable.TextInputLayout_errorTextAppearance, R$styleable.TextInputLayout_helperTextTextAppearance, R$styleable.TextInputLayout_hintTextAppearance);
        ax0 ax0Var = new ax0(this, ouVarE);
        this.F = ax0Var;
        int i3 = R$styleable.TextInputLayout_hintEnabled;
        TypedArray typedArray = (TypedArray) ouVarE.G;
        this.j0 = typedArray.getBoolean(i3, true);
        setHint(typedArray.getText(R$styleable.TextInputLayout_android_hint));
        this.b1 = typedArray.getBoolean(R$styleable.TextInputLayout_hintAnimationEnabled, true);
        this.a1 = typedArray.getBoolean(R$styleable.TextInputLayout_expandedHintEnabled, true);
        if (typedArray.hasValue(R$styleable.TextInputLayout_android_minEms)) {
            setMinEms(typedArray.getInt(R$styleable.TextInputLayout_android_minEms, -1));
        } else if (typedArray.hasValue(R$styleable.TextInputLayout_android_minWidth)) {
            setMinWidth(typedArray.getDimensionPixelSize(R$styleable.TextInputLayout_android_minWidth, -1));
        }
        if (typedArray.hasValue(R$styleable.TextInputLayout_android_maxEms)) {
            setMaxEms(typedArray.getInt(R$styleable.TextInputLayout_android_maxEms, -1));
        } else if (typedArray.hasValue(R$styleable.TextInputLayout_android_maxWidth)) {
            setMaxWidth(typedArray.getDimensionPixelSize(R$styleable.TextInputLayout_android_maxWidth, -1));
        }
        this.s0 = zu0.c(context2, attributeSet, i, i2).a();
        this.u0 = context2.getResources().getDimensionPixelOffset(R$dimen.mtrl_textinput_box_label_cutout_padding);
        this.w0 = typedArray.getDimensionPixelOffset(R$styleable.TextInputLayout_boxCollapsedPaddingTop, 0);
        this.y0 = typedArray.getDimensionPixelSize(R$styleable.TextInputLayout_boxStrokeWidth, context2.getResources().getDimensionPixelSize(R$dimen.mtrl_textinput_box_stroke_width_default));
        this.z0 = typedArray.getDimensionPixelSize(R$styleable.TextInputLayout_boxStrokeWidthFocused, context2.getResources().getDimensionPixelSize(R$dimen.mtrl_textinput_box_stroke_width_focused));
        this.x0 = this.y0;
        float dimension = typedArray.getDimension(R$styleable.TextInputLayout_boxCornerRadiusTopStart, -1.0f);
        float dimension2 = typedArray.getDimension(R$styleable.TextInputLayout_boxCornerRadiusTopEnd, -1.0f);
        float dimension3 = typedArray.getDimension(R$styleable.TextInputLayout_boxCornerRadiusBottomEnd, -1.0f);
        float dimension4 = typedArray.getDimension(R$styleable.TextInputLayout_boxCornerRadiusBottomStart, -1.0f);
        xu0 xu0VarG = this.s0.g();
        if (dimension >= 0.0f) {
            xu0VarG.e = new o0(dimension);
        }
        if (dimension2 >= 0.0f) {
            xu0VarG.f = new o0(dimension2);
        }
        if (dimension3 >= 0.0f) {
            xu0VarG.g = new o0(dimension3);
        }
        if (dimension4 >= 0.0f) {
            xu0VarG.h = new o0(dimension4);
        }
        this.s0 = xu0VarG.a();
        ColorStateList colorStateListO = la0.o(context2, ouVarE, R$styleable.TextInputLayout_boxBackgroundColor);
        if (colorStateListO != null) {
            int defaultColor = colorStateListO.getDefaultColor();
            this.S0 = defaultColor;
            this.B0 = defaultColor;
            if (colorStateListO.isStateful()) {
                this.T0 = colorStateListO.getColorForState(new int[]{-16842910}, -1);
                this.U0 = colorStateListO.getColorForState(new int[]{R.attr.state_focused, R.attr.state_enabled}, -1);
                this.V0 = colorStateListO.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, -1);
            } else {
                this.U0 = this.S0;
                ColorStateList colorStateListY = t40.Y(context2, R$color.mtrl_filled_background_color);
                this.T0 = colorStateListY.getColorForState(new int[]{-16842910}, -1);
                this.V0 = colorStateListY.getColorForState(new int[]{R.attr.state_hovered}, -1);
            }
        } else {
            this.B0 = 0;
            this.S0 = 0;
            this.T0 = 0;
            this.U0 = 0;
            this.V0 = 0;
        }
        if (typedArray.hasValue(R$styleable.TextInputLayout_android_textColorHint)) {
            ColorStateList colorStateListY2 = ouVarE.y(R$styleable.TextInputLayout_android_textColorHint);
            this.N0 = colorStateListY2;
            this.M0 = colorStateListY2;
        }
        ColorStateList colorStateListO2 = la0.o(context2, ouVarE, R$styleable.TextInputLayout_boxStrokeColor);
        this.Q0 = typedArray.getColor(R$styleable.TextInputLayout_boxStrokeColor, 0);
        this.O0 = qi.a(context2, R$color.mtrl_textinput_default_box_stroke_color);
        this.W0 = qi.a(context2, R$color.mtrl_textinput_disabled_color);
        this.P0 = qi.a(context2, R$color.mtrl_textinput_hovered_box_stroke_color);
        if (colorStateListO2 != null) {
            setBoxStrokeColorStateList(colorStateListO2);
        }
        if (typedArray.hasValue(R$styleable.TextInputLayout_boxStrokeErrorColor)) {
            setBoxStrokeErrorColor(la0.o(context2, ouVarE, R$styleable.TextInputLayout_boxStrokeErrorColor));
        }
        if (typedArray.getResourceId(R$styleable.TextInputLayout_hintTextAppearance, -1) != -1) {
            setHintTextAppearance(typedArray.getResourceId(R$styleable.TextInputLayout_hintTextAppearance, 0));
        }
        this.h0 = ouVarE.y(R$styleable.TextInputLayout_cursorColor);
        this.i0 = ouVarE.y(R$styleable.TextInputLayout_cursorErrorColor);
        int resourceId = typedArray.getResourceId(R$styleable.TextInputLayout_errorTextAppearance, 0);
        CharSequence text = typedArray.getText(R$styleable.TextInputLayout_errorContentDescription);
        int i4 = typedArray.getInt(R$styleable.TextInputLayout_errorAccessibilityLiveRegion, 1);
        boolean z = typedArray.getBoolean(R$styleable.TextInputLayout_errorEnabled, false);
        int resourceId2 = typedArray.getResourceId(R$styleable.TextInputLayout_helperTextTextAppearance, 0);
        boolean z2 = typedArray.getBoolean(R$styleable.TextInputLayout_helperTextEnabled, false);
        CharSequence text2 = typedArray.getText(R$styleable.TextInputLayout_helperText);
        int resourceId3 = typedArray.getResourceId(R$styleable.TextInputLayout_placeholderTextAppearance, 0);
        CharSequence text3 = typedArray.getText(R$styleable.TextInputLayout_placeholderText);
        boolean z3 = typedArray.getBoolean(R$styleable.TextInputLayout_counterEnabled, false);
        setCounterMaxLength(typedArray.getInt(R$styleable.TextInputLayout_counterMaxLength, -1));
        this.U = typedArray.getResourceId(R$styleable.TextInputLayout_counterTextAppearance, 0);
        this.T = typedArray.getResourceId(R$styleable.TextInputLayout_counterOverflowTextAppearance, 0);
        setBoxBackgroundMode(typedArray.getInt(R$styleable.TextInputLayout_boxBackgroundMode, 0));
        setErrorContentDescription(text);
        setErrorAccessibilityLiveRegion(i4);
        setCounterOverflowTextAppearance(this.T);
        setHelperTextTextAppearance(resourceId2);
        setErrorTextAppearance(resourceId);
        setCounterTextAppearance(this.U);
        setPlaceholderText(text3);
        setPlaceholderTextAppearance(resourceId3);
        if (typedArray.hasValue(R$styleable.TextInputLayout_errorTextColor)) {
            setErrorTextColor(ouVarE.y(R$styleable.TextInputLayout_errorTextColor));
        }
        if (typedArray.hasValue(R$styleable.TextInputLayout_helperTextTextColor)) {
            setHelperTextColor(ouVarE.y(R$styleable.TextInputLayout_helperTextTextColor));
        }
        if (typedArray.hasValue(R$styleable.TextInputLayout_hintTextColor)) {
            setHintTextColor(ouVarE.y(R$styleable.TextInputLayout_hintTextColor));
        }
        if (typedArray.hasValue(R$styleable.TextInputLayout_counterTextColor)) {
            setCounterTextColor(ouVarE.y(R$styleable.TextInputLayout_counterTextColor));
        }
        if (typedArray.hasValue(R$styleable.TextInputLayout_counterOverflowTextColor)) {
            setCounterOverflowTextColor(ouVarE.y(R$styleable.TextInputLayout_counterOverflowTextColor));
        }
        if (typedArray.hasValue(R$styleable.TextInputLayout_placeholderTextColor)) {
            setPlaceholderTextColor(ouVarE.y(R$styleable.TextInputLayout_placeholderTextColor));
        }
        xt xtVar = new xt(this, ouVarE);
        this.G = xtVar;
        boolean z4 = typedArray.getBoolean(R$styleable.TextInputLayout_android_enabled, true);
        ouVarE.S();
        WeakHashMap weakHashMap = f51.a;
        setImportantForAccessibility(2);
        int i5 = Build.VERSION.SDK_INT;
        if (i5 >= 26 && i5 >= 26) {
            w41.m(this, 1);
        }
        frameLayout.addView(ax0Var);
        frameLayout.addView(xtVar);
        addView(frameLayout);
        setEnabled(z4);
        setHelperTextEnabled(z2);
        setErrorEnabled(z);
        setCounterEnabled(z3);
        setHelperText(text2);
    }

    public void setHint(int i) {
        setHint(i != 0 ? getResources().getText(i) : null);
    }

    public void setStartIconContentDescription(int i) {
        setStartIconContentDescription(i != 0 ? getResources().getText(i) : null);
    }

    @Deprecated
    public void setPasswordVisibilityToggleContentDescription(CharSequence charSequence) {
        this.G.K.setContentDescription(charSequence);
    }

    @Deprecated
    public void setPasswordVisibilityToggleDrawable(Drawable drawable) {
        this.G.K.setImageDrawable(drawable);
    }

    public void setErrorIconDrawable(Drawable drawable) {
        this.G.i(drawable);
    }

    public void setEndIconContentDescription(CharSequence charSequence) {
        CheckableImageButton checkableImageButton = this.G.K;
        if (checkableImageButton.getContentDescription() != charSequence) {
            checkableImageButton.setContentDescription(charSequence);
        }
    }

    public void setEndIconDrawable(Drawable drawable) {
        xt xtVar = this.G;
        CheckableImageButton checkableImageButton = xtVar.K;
        checkableImageButton.setImageDrawable(drawable);
        if (drawable != null) {
            ColorStateList colorStateList = xtVar.O;
            PorterDuff.Mode mode = xtVar.P;
            TextInputLayout textInputLayout = xtVar.E;
            l21.l(textInputLayout, checkableImageButton, colorStateList, mode);
            l21.N0(textInputLayout, checkableImageButton, xtVar.O);
        }
    }
}
