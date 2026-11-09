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
import defpackage.a5;
import defpackage.bh;
import defpackage.co;
import defpackage.d9;
import defpackage.e;
import defpackage.e9;
import defpackage.fn0;
import defpackage.fo;
import defpackage.gm;
import defpackage.hl;
import defpackage.ii0;
import defpackage.in0;
import defpackage.je;
import defpackage.jh0;
import defpackage.ji;
import defpackage.jk;
import defpackage.jn0;
import defpackage.jp0;
import defpackage.k9;
import defpackage.kh;
import defpackage.ki;
import defpackage.ki0;
import defpackage.kn0;
import defpackage.ll;
import defpackage.ln0;
import defpackage.mk0;
import defpackage.mn0;
import defpackage.nv0;
import defpackage.or0;
import defpackage.oy;
import defpackage.p8;
import defpackage.po;
import defpackage.qm;
import defpackage.rp0;
import defpackage.t3;
import defpackage.t50;
import defpackage.u50;
import defpackage.un0;
import defpackage.vk;
import defpackage.xi;
import defpackage.xr0;
import defpackage.y50;
import defpackage.yd;
import defpackage.yf0;
import defpackage.yx;
import defpackage.zx;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.WeakHashMap;
import org.java_websocket.extensions.ExtensionRequestData;

/* JADX INFO: loaded from: classes.dex */
public class TextInputLayout extends LinearLayout {
    public static final int B0 = R$style.Widget_Design_TextInputLayout;
    public static final int[][] C0 = {new int[]{R.attr.state_pressed}, new int[0]};
    public ColorStateList A;
    public boolean A0;
    public ColorStateList B;
    public ColorStateList C;
    public boolean D;
    public CharSequence E;
    public boolean F;
    public u50 G;
    public u50 H;
    public StateListDrawable I;
    public boolean J;
    public u50 K;
    public u50 L;
    public ki0 M;
    public boolean N;
    public final int O;
    public int P;
    public int Q;
    public int R;
    public int S;
    public int T;
    public int U;
    public int V;
    public final Rect W;
    public final Rect a0;
    public final FrameLayout b;
    public final RectF b0;
    public final mk0 c;
    public Typeface c0;
    public final fo d;
    public ColorDrawable d0;
    public EditText e;
    public int e0;
    public CharSequence f;
    public final LinkedHashSet f0;
    public int g;
    public ColorDrawable g0;
    public int h;
    public int h0;
    public int i;
    public Drawable i0;
    public int j;
    public ColorStateList j0;
    public final zx k;
    public ColorStateList k0;
    public boolean l;
    public int l0;
    public int m;
    public int m0;
    public boolean n;
    public int n0;
    public kn0 o;
    public ColorStateList o0;
    public AppCompatTextView p;
    public int p0;
    public int q;
    public int q0;
    public int r;
    public int r0;
    public CharSequence s;
    public int s0;
    public boolean t;
    public int t0;
    public AppCompatTextView u;
    public boolean u0;
    public ColorStateList v;
    public final yd v0;
    public int w;
    public boolean w0;
    public Fade x;
    public boolean x0;
    public Fade y;
    public ValueAnimator y0;
    public ColorStateList z;
    public boolean z0;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public CharSequence d;
        public boolean e;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.d = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.e = parcel.readInt() == 1;
        }

        public final String toString() {
            return "TextInputLayout.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " error=" + ((Object) this.d) + "}";
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            TextUtils.writeToParcel(this.d, parcel, i);
            parcel.writeInt(this.e ? 1 : 0);
        }
    }

    public TextInputLayout(Context context) {
        this(context, null);
    }

    private Drawable getEditTextBoxBackground() {
        EditText editText = this.e;
        if (!(editText instanceof AutoCompleteTextView) || oy.F(editText)) {
            return this.G;
        }
        int iQ = k9.q(this.e, R$attr.colorControlHighlight);
        int i = this.P;
        int[][] iArr = C0;
        if (i != 2) {
            if (i != 1) {
                return null;
            }
            u50 u50Var = this.G;
            int i2 = this.V;
            return new RippleDrawable(new ColorStateList(iArr, new int[]{k9.x(iQ, 0.1f, i2), i2}), u50Var, u50Var);
        }
        Context context = getContext();
        u50 u50Var2 = this.G;
        TypedValue typedValueL = nv0.L(context, R$attr.colorSurface, "TextInputLayout");
        int i3 = typedValueL.resourceId;
        int iA = i3 != 0 ? bh.a(context, i3) : typedValueL.data;
        u50 u50Var3 = new u50(u50Var2.b.a);
        int iX = k9.x(iQ, 0.1f, iA);
        u50Var3.m(new ColorStateList(iArr, new int[]{iX, 0}));
        u50Var3.setTint(iA);
        ColorStateList colorStateList = new ColorStateList(iArr, new int[]{iX, iA});
        u50 u50Var4 = new u50(u50Var2.b.a);
        u50Var4.setTint(-1);
        return new LayerDrawable(new Drawable[]{new RippleDrawable(colorStateList, u50Var3, u50Var4), u50Var2});
    }

    private Drawable getOrCreateFilledDropDownMenuBackground() {
        if (this.I == null) {
            StateListDrawable stateListDrawable = new StateListDrawable();
            this.I = stateListDrawable;
            stateListDrawable.addState(new int[]{R.attr.state_above_anchor}, getOrCreateOutlinedDropDownMenuBackground());
            this.I.addState(new int[0], f(false));
        }
        return this.I;
    }

    private Drawable getOrCreateOutlinedDropDownMenuBackground() {
        if (this.H == null) {
            this.H = f(true);
        }
        return this.H;
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
        if (this.e != null) {
            throw new IllegalArgumentException("We already have an EditText, can only have one");
        }
        if (getEndIconMode() != 3 && !(editText instanceof TextInputEditText)) {
            Log.i("TextInputLayout", "EditText added is not a TextInputEditText. Please switch to using that class instead.");
        }
        this.e = editText;
        int i = this.g;
        if (i != -1) {
            setMinEms(i);
        } else {
            setMinWidth(this.i);
        }
        int i2 = this.h;
        if (i2 != -1) {
            setMaxEms(i2);
        } else {
            setMaxWidth(this.j);
        }
        this.J = false;
        i();
        setTextInputAccessibilityDelegate(new jn0(this));
        Typeface typeface = this.e.getTypeface();
        yd ydVar = this.v0;
        boolean zM = ydVar.m(typeface);
        boolean zO = ydVar.o(typeface);
        if (zM || zO) {
            ydVar.i(false);
        }
        float textSize = this.e.getTextSize();
        if (ydVar.l != textSize) {
            ydVar.l = textSize;
            ydVar.i(false);
        }
        int i3 = Build.VERSION.SDK_INT;
        float letterSpacing = this.e.getLetterSpacing();
        if (ydVar.g0 != letterSpacing) {
            ydVar.g0 = letterSpacing;
            ydVar.i(false);
        }
        int gravity = this.e.getGravity();
        ydVar.l((gravity & (-113)) | 48);
        if (ydVar.j != gravity) {
            ydVar.j = gravity;
            ydVar.i(false);
        }
        this.e.addTextChangedListener(new jh0(this, 2));
        if (this.j0 == null) {
            this.j0 = this.e.getHintTextColors();
        }
        if (this.D) {
            if (TextUtils.isEmpty(this.E)) {
                CharSequence hint = this.e.getHint();
                this.f = hint;
                setHint(hint);
                this.e.setHint((CharSequence) null);
            }
            this.F = true;
        }
        if (i3 >= 29) {
            p();
        }
        if (this.p != null) {
            n(this.e.getText());
        }
        r();
        this.k.b();
        this.c.bringToFront();
        fo foVar = this.d;
        foVar.bringToFront();
        Iterator it = this.f0.iterator();
        while (it.hasNext()) {
            ((co) ((ln0) it.next())).a(this);
        }
        foVar.m();
        if (!isEnabled()) {
            editText.setEnabled(false);
        }
        u(false, true);
    }

    private void setHintInternal(CharSequence charSequence) {
        if (TextUtils.equals(charSequence, this.E)) {
            return;
        }
        this.E = charSequence;
        yd ydVar = this.v0;
        if (charSequence == null || !TextUtils.equals(ydVar.G, charSequence)) {
            ydVar.G = charSequence;
            ydVar.H = null;
            Bitmap bitmap = ydVar.K;
            if (bitmap != null) {
                bitmap.recycle();
                ydVar.K = null;
            }
            ydVar.i(false);
        }
        if (this.u0) {
            return;
        }
        j();
    }

    private void setPlaceholderTextEnabled(boolean z) {
        if (this.t == z) {
            return;
        }
        if (z) {
            AppCompatTextView appCompatTextView = this.u;
            if (appCompatTextView != null) {
                this.b.addView(appCompatTextView);
                this.u.setVisibility(0);
            }
        } else {
            AppCompatTextView appCompatTextView2 = this.u;
            if (appCompatTextView2 != null) {
                appCompatTextView2.setVisibility(8);
            }
            this.u = null;
        }
        this.t = z;
    }

    public final void a(float f) {
        yd ydVar = this.v0;
        if (ydVar.b == f) {
            return;
        }
        if (this.y0 == null) {
            ValueAnimator valueAnimator = new ValueAnimator();
            this.y0 = valueAnimator;
            valueAnimator.setInterpolator(k9.F(getContext(), R$attr.motionEasingEmphasizedInterpolator, t3.b));
            this.y0.setDuration(k9.E(getContext(), R$attr.motionDurationMedium4, 167));
            this.y0.addUpdateListener(new d9(5, this));
        }
        this.y0.setFloatValues(ydVar.b, f);
        this.y0.start();
    }

    public void addOnEditTextAttachedListener(ln0 ln0Var) {
        this.f0.add(ln0Var);
        if (this.e != null) {
            ((co) ln0Var).a(this);
        }
    }

    public void addOnEndIconChangedListener(mn0 mn0Var) {
        this.d.addOnEndIconChangedListener(mn0Var);
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (!(view instanceof EditText)) {
            super.addView(view, i, layoutParams);
            return;
        }
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(layoutParams);
        layoutParams2.gravity = (layoutParams2.gravity & (-113)) | 16;
        FrameLayout frameLayout = this.b;
        frameLayout.addView(view, layoutParams2);
        frameLayout.setLayoutParams(layoutParams);
        t();
        setEditText((EditText) view);
    }

    public final void b() {
        int i;
        int i2;
        u50 u50Var = this.G;
        if (u50Var == null) {
            return;
        }
        ki0 ki0Var = u50Var.b.a;
        ki0 ki0Var2 = this.M;
        if (ki0Var != ki0Var2) {
            u50Var.setShapeAppearanceModel(ki0Var2);
        }
        if (this.P == 2 && (i = this.R) > -1 && (i2 = this.U) != 0) {
            u50 u50Var2 = this.G;
            u50Var2.s(i);
            u50Var2.r(ColorStateList.valueOf(i2));
        }
        int iC = this.V;
        if (this.P == 1) {
            iC = je.c(this.V, k9.p(getContext(), R$attr.colorSurface, 0));
        }
        this.V = iC;
        this.G.m(ColorStateList.valueOf(iC));
        u50 u50Var3 = this.K;
        if (u50Var3 != null && this.L != null) {
            if (this.R > -1 && this.U != 0) {
                u50Var3.m(this.e.isFocused() ? ColorStateList.valueOf(this.l0) : ColorStateList.valueOf(this.U));
                this.L.m(ColorStateList.valueOf(this.U));
            }
            invalidate();
        }
        s();
    }

    public final int c() {
        float fE;
        if (!this.D) {
            return 0;
        }
        int i = this.P;
        yd ydVar = this.v0;
        if (i == 0) {
            fE = ydVar.e();
        } else {
            if (i != 2) {
                return 0;
            }
            fE = ydVar.e() / 2.0f;
        }
        return (int) fE;
    }

    public final Fade d() {
        Fade fade = new Fade();
        fade.d = k9.E(getContext(), R$attr.motionDurationShort2, 87);
        fade.e = k9.F(getContext(), R$attr.motionEasingLinearInterpolator, t3.a);
        return fade;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchProvideAutofillStructure(ViewStructure viewStructure, int i) {
        EditText editText = this.e;
        if (editText == null) {
            super.dispatchProvideAutofillStructure(viewStructure, i);
            return;
        }
        if (this.f != null) {
            boolean z = this.F;
            this.F = false;
            CharSequence hint = editText.getHint();
            this.e.setHint(this.f);
            try {
                super.dispatchProvideAutofillStructure(viewStructure, i);
                return;
            } finally {
                this.e.setHint(hint);
                this.F = z;
            }
        }
        viewStructure.setAutofillId(getAutofillId());
        onProvideAutofillStructure(viewStructure, i);
        onProvideAutofillVirtualStructure(viewStructure, i);
        FrameLayout frameLayout = this.b;
        viewStructure.setChildCount(frameLayout.getChildCount());
        for (int i2 = 0; i2 < frameLayout.getChildCount(); i2++) {
            View childAt = frameLayout.getChildAt(i2);
            ViewStructure viewStructureNewChild = viewStructure.newChild(i2);
            childAt.dispatchProvideAutofillStructure(viewStructureNewChild, i);
            if (childAt == this.e) {
                viewStructureNewChild.setHint(getHint());
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchRestoreInstanceState(SparseArray sparseArray) {
        this.A0 = true;
        super.dispatchRestoreInstanceState(sparseArray);
        this.A0 = false;
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        u50 u50Var;
        super.draw(canvas);
        boolean z = this.D;
        yd ydVar = this.v0;
        if (z) {
            ydVar.d(canvas);
        }
        if (this.L == null || (u50Var = this.K) == null) {
            return;
        }
        u50Var.draw(canvas);
        if (this.e.isFocused()) {
            Rect bounds = this.L.getBounds();
            Rect bounds2 = this.K.getBounds();
            float f = ydVar.b;
            int iCenterX = bounds2.centerX();
            bounds.left = t3.c(iCenterX, f, bounds2.left);
            bounds.right = t3.c(iCenterX, f, bounds2.right);
            this.L.draw(canvas);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x002f  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void drawableStateChanged() {
        /*
            r4 = this;
            boolean r0 = r4.z0
            if (r0 == 0) goto L5
            return
        L5:
            r0 = 1
            r4.z0 = r0
            super.drawableStateChanged()
            int[] r1 = r4.getDrawableState()
            r2 = 0
            yd r3 = r4.v0
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
            android.widget.EditText r3 = r4.e
            if (r3 == 0) goto L47
            java.util.WeakHashMap r3 = defpackage.xr0.a
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
            r4.z0 = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.drawableStateChanged():void");
    }

    public final boolean e() {
        return this.D && !TextUtils.isEmpty(this.E) && (this.G instanceof ki);
    }

    public final u50 f(boolean z) {
        float dimensionPixelOffset = getResources().getDimensionPixelOffset(R$dimen.mtrl_shape_corner_size_small_component);
        float f = z ? dimensionPixelOffset : 0.0f;
        EditText editText = this.e;
        float popupElevation = editText instanceof MaterialAutoCompleteTextView ? ((MaterialAutoCompleteTextView) editText).getPopupElevation() : getResources().getDimensionPixelOffset(R$dimen.m3_comp_outlined_autocomplete_menu_container_elevation);
        int dimensionPixelOffset2 = getResources().getDimensionPixelOffset(R$dimen.mtrl_exposed_dropdown_menu_popup_vertical_padding);
        yf0 yf0Var = new yf0();
        yf0 yf0Var2 = new yf0();
        yf0 yf0Var3 = new yf0();
        yf0 yf0Var4 = new yf0();
        qm qmVar = new qm();
        qm qmVar2 = new qm();
        qm qmVar3 = new qm();
        qm qmVar4 = new qm();
        e eVar = new e(f);
        e eVar2 = new e(f);
        e eVar3 = new e(dimensionPixelOffset);
        e eVar4 = new e(dimensionPixelOffset);
        ki0 ki0Var = new ki0();
        ki0Var.a = yf0Var;
        ki0Var.b = yf0Var2;
        ki0Var.c = yf0Var3;
        ki0Var.d = yf0Var4;
        ki0Var.e = eVar;
        ki0Var.f = eVar2;
        ki0Var.g = eVar4;
        ki0Var.h = eVar3;
        ki0Var.i = qmVar;
        ki0Var.j = qmVar2;
        ki0Var.k = qmVar3;
        ki0Var.l = qmVar4;
        EditText editText2 = this.e;
        ColorStateList dropDownBackgroundTintList = editText2 instanceof MaterialAutoCompleteTextView ? ((MaterialAutoCompleteTextView) editText2).getDropDownBackgroundTintList() : null;
        Context context = getContext();
        if (dropDownBackgroundTintList == null) {
            Paint paint = u50.x;
            TypedValue typedValueL = nv0.L(context, R$attr.colorSurface, u50.class.getSimpleName());
            int i = typedValueL.resourceId;
            dropDownBackgroundTintList = ColorStateList.valueOf(i != 0 ? bh.a(context, i) : typedValueL.data);
        }
        u50 u50Var = new u50();
        u50Var.k(context);
        u50Var.m(dropDownBackgroundTintList);
        u50Var.l(popupElevation);
        u50Var.setShapeAppearanceModel(ki0Var);
        t50 t50Var = u50Var.b;
        if (t50Var.g == null) {
            t50Var.g = new Rect();
        }
        u50Var.b.g.set(0, dimensionPixelOffset2, 0, dimensionPixelOffset2);
        u50Var.invalidateSelf();
        return u50Var;
    }

    public final int g(int i, boolean z) {
        return ((z || getPrefixText() == null) ? (!z || getSuffixText() == null) ? this.e.getCompoundPaddingLeft() : this.d.c() : this.c.a()) + i;
    }

    @Override // android.widget.LinearLayout, android.view.View
    public int getBaseline() {
        EditText editText = this.e;
        if (editText == null) {
            return super.getBaseline();
        }
        return c() + getPaddingTop() + editText.getBaseline();
    }

    public u50 getBoxBackground() {
        int i = this.P;
        if (i == 1 || i == 2) {
            return this.G;
        }
        throw new IllegalStateException();
    }

    public int getBoxBackgroundColor() {
        return this.V;
    }

    public int getBoxBackgroundMode() {
        return this.P;
    }

    public int getBoxCollapsedPaddingTop() {
        return this.Q;
    }

    public float getBoxCornerRadiusBottomEnd() {
        boolean zI = rp0.I(this);
        RectF rectF = this.b0;
        return zI ? this.M.h.a(rectF) : this.M.g.a(rectF);
    }

    public float getBoxCornerRadiusBottomStart() {
        boolean zI = rp0.I(this);
        RectF rectF = this.b0;
        return zI ? this.M.g.a(rectF) : this.M.h.a(rectF);
    }

    public float getBoxCornerRadiusTopEnd() {
        boolean zI = rp0.I(this);
        RectF rectF = this.b0;
        return zI ? this.M.e.a(rectF) : this.M.f.a(rectF);
    }

    public float getBoxCornerRadiusTopStart() {
        boolean zI = rp0.I(this);
        RectF rectF = this.b0;
        return zI ? this.M.f.a(rectF) : this.M.e.a(rectF);
    }

    public int getBoxStrokeColor() {
        return this.n0;
    }

    public ColorStateList getBoxStrokeErrorColor() {
        return this.o0;
    }

    public int getBoxStrokeWidth() {
        return this.S;
    }

    public int getBoxStrokeWidthFocused() {
        return this.T;
    }

    public int getCounterMaxLength() {
        return this.m;
    }

    public CharSequence getCounterOverflowDescription() {
        AppCompatTextView appCompatTextView;
        if (this.l && this.n && (appCompatTextView = this.p) != null) {
            return appCompatTextView.getContentDescription();
        }
        return null;
    }

    public ColorStateList getCounterOverflowTextColor() {
        return this.A;
    }

    public ColorStateList getCounterTextColor() {
        return this.z;
    }

    public ColorStateList getCursorColor() {
        return this.B;
    }

    public ColorStateList getCursorErrorColor() {
        return this.C;
    }

    public ColorStateList getDefaultHintTextColor() {
        return this.j0;
    }

    public EditText getEditText() {
        return this.e;
    }

    public CharSequence getEndIconContentDescription() {
        return this.d.h.getContentDescription();
    }

    public Drawable getEndIconDrawable() {
        return this.d.h.getDrawable();
    }

    public int getEndIconMinSize() {
        return this.d.n;
    }

    public int getEndIconMode() {
        return this.d.j;
    }

    public ImageView.ScaleType getEndIconScaleType() {
        return this.d.o;
    }

    public CheckableImageButton getEndIconView() {
        return this.d.h;
    }

    public CharSequence getError() {
        zx zxVar = this.k;
        if (zxVar.q) {
            return zxVar.p;
        }
        return null;
    }

    public int getErrorAccessibilityLiveRegion() {
        return this.k.t;
    }

    public CharSequence getErrorContentDescription() {
        return this.k.s;
    }

    public int getErrorCurrentTextColors() {
        AppCompatTextView appCompatTextView = this.k.r;
        if (appCompatTextView != null) {
            return appCompatTextView.getCurrentTextColor();
        }
        return -1;
    }

    public Drawable getErrorIconDrawable() {
        return this.d.d.getDrawable();
    }

    public CharSequence getHelperText() {
        zx zxVar = this.k;
        if (zxVar.x) {
            return zxVar.w;
        }
        return null;
    }

    public int getHelperTextCurrentTextColor() {
        AppCompatTextView appCompatTextView = this.k.y;
        if (appCompatTextView != null) {
            return appCompatTextView.getCurrentTextColor();
        }
        return -1;
    }

    public CharSequence getHint() {
        if (this.D) {
            return this.E;
        }
        return null;
    }

    public final float getHintCollapsedTextHeight() {
        return this.v0.e();
    }

    public final int getHintCurrentCollapsedTextColor() {
        yd ydVar = this.v0;
        return ydVar.f(ydVar.o);
    }

    public ColorStateList getHintTextColor() {
        return this.k0;
    }

    public kn0 getLengthCounter() {
        return this.o;
    }

    public int getMaxEms() {
        return this.h;
    }

    public int getMaxWidth() {
        return this.j;
    }

    public int getMinEms() {
        return this.g;
    }

    public int getMinWidth() {
        return this.i;
    }

    @Deprecated
    public CharSequence getPasswordVisibilityToggleContentDescription() {
        return this.d.h.getContentDescription();
    }

    @Deprecated
    public Drawable getPasswordVisibilityToggleDrawable() {
        return this.d.h.getDrawable();
    }

    public CharSequence getPlaceholderText() {
        if (this.t) {
            return this.s;
        }
        return null;
    }

    public int getPlaceholderTextAppearance() {
        return this.w;
    }

    public ColorStateList getPlaceholderTextColor() {
        return this.v;
    }

    public CharSequence getPrefixText() {
        return this.c.d;
    }

    public ColorStateList getPrefixTextColor() {
        return this.c.c.getTextColors();
    }

    public TextView getPrefixTextView() {
        return this.c.c;
    }

    public ki0 getShapeAppearanceModel() {
        return this.M;
    }

    public CharSequence getStartIconContentDescription() {
        return this.c.e.getContentDescription();
    }

    public Drawable getStartIconDrawable() {
        return this.c.e.getDrawable();
    }

    public int getStartIconMinSize() {
        return this.c.h;
    }

    public ImageView.ScaleType getStartIconScaleType() {
        return this.c.i;
    }

    public CharSequence getSuffixText() {
        return this.d.q;
    }

    public ColorStateList getSuffixTextColor() {
        return this.d.r.getTextColors();
    }

    public TextView getSuffixTextView() {
        return this.d.r;
    }

    public Typeface getTypeface() {
        return this.c0;
    }

    public final int h(int i, boolean z) {
        return i - ((z || getSuffixText() == null) ? (!z || getPrefixText() == null) ? this.e.getCompoundPaddingRight() : this.c.a() : this.d.c());
    }

    public final void i() {
        int i = this.P;
        if (i == 0) {
            this.G = null;
            this.K = null;
            this.L = null;
        } else if (i == 1) {
            this.G = new u50(this.M);
            this.K = new u50();
            this.L = new u50();
        } else {
            if (i != 2) {
                throw new IllegalArgumentException(this.P + " is illegal; only @BoxBackgroundMode constants are supported.");
            }
            if (!this.D || (this.G instanceof ki)) {
                this.G = new u50(this.M);
            } else {
                ki0 ki0Var = this.M;
                int i2 = ki.z;
                if (ki0Var == null) {
                    ki0Var = new ki0();
                }
                ji jiVar = new ji(ki0Var, new RectF());
                ki kiVar = new ki(jiVar);
                kiVar.y = jiVar;
                this.G = kiVar;
            }
            this.K = null;
            this.L = null;
        }
        s();
        x();
        if (this.P == 1) {
            if (getContext().getResources().getConfiguration().fontScale >= 2.0f) {
                this.Q = getResources().getDimensionPixelSize(R$dimen.material_font_2_0_box_collapsed_padding_top);
            } else if (xi.F(getContext())) {
                this.Q = getResources().getDimensionPixelSize(R$dimen.material_font_1_3_box_collapsed_padding_top);
            }
        }
        if (this.e != null && this.P == 1) {
            if (getContext().getResources().getConfiguration().fontScale >= 2.0f) {
                EditText editText = this.e;
                WeakHashMap weakHashMap = xr0.a;
                editText.setPaddingRelative(editText.getPaddingStart(), getResources().getDimensionPixelSize(R$dimen.material_filled_edittext_font_2_0_padding_top), this.e.getPaddingEnd(), getResources().getDimensionPixelSize(R$dimen.material_filled_edittext_font_2_0_padding_bottom));
            } else if (xi.F(getContext())) {
                EditText editText2 = this.e;
                WeakHashMap weakHashMap2 = xr0.a;
                editText2.setPaddingRelative(editText2.getPaddingStart(), getResources().getDimensionPixelSize(R$dimen.material_filled_edittext_font_1_3_padding_top), this.e.getPaddingEnd(), getResources().getDimensionPixelSize(R$dimen.material_filled_edittext_font_1_3_padding_bottom));
            }
        }
        if (this.P != 0) {
            t();
        }
        EditText editText3 = this.e;
        if (editText3 instanceof AutoCompleteTextView) {
            AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) editText3;
            if (autoCompleteTextView.getDropDownBackground() == null) {
                int i3 = this.P;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void j() {
        /*
            Method dump skipped, instruction units count: 241
            To view this dump change 'Code comments level' option to 'DEBUG'
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
        appCompatTextView.setTextColor(bh.a(getContext(), R$color.design_error));
    }

    public final boolean m() {
        zx zxVar = this.k;
        return (zxVar.o != 1 || zxVar.r == null || TextUtils.isEmpty(zxVar.p)) ? false : true;
    }

    public final void n(Editable editable) {
        ((vk) this.o).getClass();
        int length = editable != null ? editable.length() : 0;
        boolean z = this.n;
        int i = this.m;
        String string = null;
        if (i == -1) {
            this.p.setText(String.valueOf(length));
            this.p.setContentDescription(null);
            this.n = false;
        } else {
            this.n = length > i;
            Context context = getContext();
            this.p.setContentDescription(context.getString(this.n ? R$string.character_counter_overflowed_content_description : R$string.character_counter_content_description, Integer.valueOf(length), Integer.valueOf(this.m)));
            if (z != this.n) {
                o();
            }
            String str = p8.b;
            p8 p8Var = TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 1 ? p8.e : p8.d;
            AppCompatTextView appCompatTextView = this.p;
            String string2 = getContext().getString(R$string.character_counter_pattern, Integer.valueOf(length), Integer.valueOf(this.m));
            if (string2 == null) {
                p8Var.getClass();
            } else {
                p8Var.getClass();
                e9 e9Var = fn0.a;
                string = p8Var.c(string2).toString();
            }
            appCompatTextView.setText(string);
        }
        if (this.e == null || z == this.n) {
            return;
        }
        u(false, false);
        x();
        r();
    }

    public final void o() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        AppCompatTextView appCompatTextView = this.p;
        if (appCompatTextView != null) {
            l(appCompatTextView, this.n ? this.q : this.r);
            if (!this.n && (colorStateList2 = this.z) != null) {
                this.p.setTextColor(colorStateList2);
            }
            if (!this.n || (colorStateList = this.A) == null) {
                return;
            }
            this.p.setTextColor(colorStateList);
        }
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.v0.h(configuration);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        EditText editText = this.e;
        if (editText != null) {
            Rect rect = this.W;
            jk.a(this, editText, rect);
            u50 u50Var = this.K;
            if (u50Var != null) {
                int i5 = rect.bottom;
                u50Var.setBounds(rect.left, i5 - this.S, rect.right, i5);
            }
            u50 u50Var2 = this.L;
            if (u50Var2 != null) {
                int i6 = rect.bottom;
                u50Var2.setBounds(rect.left, i6 - this.T, rect.right, i6);
            }
            if (this.D) {
                float textSize = this.e.getTextSize();
                yd ydVar = this.v0;
                if (ydVar.l != textSize) {
                    ydVar.l = textSize;
                    ydVar.i(false);
                }
                int gravity = this.e.getGravity();
                ydVar.l((gravity & (-113)) | 48);
                if (ydVar.j != gravity) {
                    ydVar.j = gravity;
                    ydVar.i(false);
                }
                if (this.e == null) {
                    throw new IllegalStateException();
                }
                boolean zI = rp0.I(this);
                int i7 = rect.bottom;
                Rect rect2 = this.a0;
                rect2.bottom = i7;
                int i8 = this.P;
                if (i8 == 1) {
                    rect2.left = g(rect.left, zI);
                    rect2.top = rect.top + this.Q;
                    rect2.right = h(rect.right, zI);
                } else if (i8 != 2) {
                    rect2.left = g(rect.left, zI);
                    rect2.top = getPaddingTop();
                    rect2.right = h(rect.right, zI);
                } else {
                    rect2.left = this.e.getPaddingLeft() + rect.left;
                    rect2.top = rect.top - c();
                    rect2.right = rect.right - this.e.getPaddingRight();
                }
                int i9 = rect2.left;
                int i10 = rect2.top;
                int i11 = rect2.right;
                int i12 = rect2.bottom;
                Rect rect3 = ydVar.h;
                if (rect3.left != i9 || rect3.top != i10 || rect3.right != i11 || rect3.bottom != i12) {
                    rect3.set(i9, i10, i11, i12);
                    ydVar.S = true;
                }
                if (this.e == null) {
                    throw new IllegalStateException();
                }
                TextPaint textPaint = ydVar.U;
                textPaint.setTextSize(ydVar.l);
                textPaint.setTypeface(ydVar.z);
                textPaint.setLetterSpacing(ydVar.g0);
                float f = -textPaint.ascent();
                rect2.left = this.e.getCompoundPaddingLeft() + rect.left;
                rect2.top = (this.P != 1 || this.e.getMinLines() > 1) ? rect.top + this.e.getCompoundPaddingTop() : (int) (rect.centerY() - (f / 2.0f));
                rect2.right = rect.right - this.e.getCompoundPaddingRight();
                int compoundPaddingBottom = (this.P != 1 || this.e.getMinLines() > 1) ? rect.bottom - this.e.getCompoundPaddingBottom() : (int) (rect2.top + f);
                rect2.bottom = compoundPaddingBottom;
                int i13 = rect2.left;
                int i14 = rect2.top;
                int i15 = rect2.right;
                Rect rect4 = ydVar.g;
                if (rect4.left != i13 || rect4.top != i14 || rect4.right != i15 || rect4.bottom != compoundPaddingBottom) {
                    rect4.set(i13, i14, i15, compoundPaddingBottom);
                    ydVar.S = true;
                }
                ydVar.i(false);
                if (!e() || this.u0) {
                    return;
                }
                j();
            }
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        EditText editText;
        int iMax;
        super.onMeasure(i, i2);
        EditText editText2 = this.e;
        fo foVar = this.d;
        boolean z = false;
        if (editText2 != null && this.e.getMeasuredHeight() < (iMax = Math.max(foVar.getMeasuredHeight(), this.c.getMeasuredHeight()))) {
            this.e.setMinimumHeight(iMax);
            z = true;
        }
        boolean zQ = q();
        if (z || zQ) {
            this.e.post(new in0(this, 1));
        }
        if (this.u != null && (editText = this.e) != null) {
            this.u.setGravity(editText.getGravity());
            this.u.setPadding(this.e.getCompoundPaddingLeft(), this.e.getCompoundPaddingTop(), this.e.getCompoundPaddingRight(), this.e.getCompoundPaddingBottom());
        }
        foVar.m();
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.b);
        setError(savedState.d);
        if (savedState.e) {
            post(new in0(this, 0));
        }
        requestLayout();
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onRtlPropertiesChanged(int i) {
        super.onRtlPropertiesChanged(i);
        boolean z = i == 1;
        if (z != this.N) {
            kh khVar = this.M.e;
            RectF rectF = this.b0;
            float fA = khVar.a(rectF);
            float fA2 = this.M.f.a(rectF);
            float fA3 = this.M.h.a(rectF);
            float fA4 = this.M.g.a(rectF);
            ki0 ki0Var = this.M;
            nv0 nv0Var = ki0Var.a;
            nv0 nv0Var2 = ki0Var.b;
            nv0 nv0Var3 = ki0Var.d;
            nv0 nv0Var4 = ki0Var.c;
            qm qmVar = new qm();
            qm qmVar2 = new qm();
            qm qmVar3 = new qm();
            qm qmVar4 = new qm();
            ii0.b(nv0Var2);
            ii0.b(nv0Var);
            ii0.b(nv0Var4);
            ii0.b(nv0Var3);
            e eVar = new e(fA2);
            e eVar2 = new e(fA);
            e eVar3 = new e(fA4);
            e eVar4 = new e(fA3);
            ki0 ki0Var2 = new ki0();
            ki0Var2.a = nv0Var2;
            ki0Var2.b = nv0Var;
            ki0Var2.c = nv0Var3;
            ki0Var2.d = nv0Var4;
            ki0Var2.e = eVar;
            ki0Var2.f = eVar2;
            ki0Var2.g = eVar4;
            ki0Var2.h = eVar3;
            ki0Var2.i = qmVar;
            ki0Var2.j = qmVar2;
            ki0Var2.k = qmVar3;
            ki0Var2.l = qmVar4;
            this.N = z;
            setShapeAppearanceModel(ki0Var2);
        }
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        if (m()) {
            savedState.d = getError();
        }
        fo foVar = this.d;
        savedState.e = foVar.j != 0 && foVar.h.e;
        return savedState;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0012  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void p() {
        /*
            r4 = this;
            android.content.res.ColorStateList r0 = r4.B
            if (r0 == 0) goto L5
            goto L25
        L5:
            android.content.Context r0 = r4.getContext()
            int r1 = com.google.android.material.R$attr.colorControlActivated
            android.util.TypedValue r1 = defpackage.nv0.J(r0, r1)
            r2 = 0
            if (r1 != 0) goto L14
        L12:
            r0 = r2
            goto L25
        L14:
            int r3 = r1.resourceId
            if (r3 == 0) goto L1d
            android.content.res.ColorStateList r0 = defpackage.rp0.w(r0, r3)
            goto L25
        L1d:
            int r0 = r1.data
            if (r0 == 0) goto L12
            android.content.res.ColorStateList r0 = android.content.res.ColorStateList.valueOf(r0)
        L25:
            android.widget.EditText r1 = r4.e
            if (r1 == 0) goto L4c
            android.graphics.drawable.Drawable r1 = defpackage.f0.d(r1)
            if (r1 != 0) goto L30
            goto L4c
        L30:
            android.widget.EditText r1 = r4.e
            android.graphics.drawable.Drawable r1 = defpackage.f0.d(r1)
            boolean r2 = r4.m()
            if (r2 != 0) goto L44
            androidx.appcompat.widget.AppCompatTextView r2 = r4.p
            if (r2 == 0) goto L49
            boolean r2 = r4.n
            if (r2 == 0) goto L49
        L44:
            android.content.res.ColorStateList r2 = r4.C
            if (r2 == 0) goto L49
            r0 = r2
        L49:
            defpackage.hl.h(r1, r0)
        L4c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.p():void");
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x005f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean q() {
        /*
            Method dump skipped, instruction units count: 304
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.q():boolean");
    }

    public final void r() {
        Drawable background;
        AppCompatTextView appCompatTextView;
        EditText editText = this.e;
        if (editText == null || this.P != 0 || (background = editText.getBackground()) == null) {
            return;
        }
        int[] iArr = ll.a;
        Drawable drawableMutate = background.mutate();
        if (m()) {
            drawableMutate.setColorFilter(a5.c(getErrorCurrentTextColors(), PorterDuff.Mode.SRC_IN));
        } else if (this.n && (appCompatTextView = this.p) != null) {
            drawableMutate.setColorFilter(a5.c(appCompatTextView.getCurrentTextColor(), PorterDuff.Mode.SRC_IN));
        } else {
            drawableMutate.clearColorFilter();
            this.e.refreshDrawableState();
        }
    }

    public void removeOnEditTextAttachedListener(ln0 ln0Var) {
        this.f0.remove(ln0Var);
    }

    public void removeOnEndIconChangedListener(mn0 mn0Var) {
        this.d.removeOnEndIconChangedListener(mn0Var);
    }

    public final void s() {
        EditText editText = this.e;
        if (editText == null || this.G == null) {
            return;
        }
        if ((this.J || editText.getBackground() == null) && this.P != 0) {
            EditText editText2 = this.e;
            Drawable editTextBoxBackground = getEditTextBoxBackground();
            WeakHashMap weakHashMap = xr0.a;
            editText2.setBackground(editTextBoxBackground);
            this.J = true;
        }
    }

    public void setBoxBackgroundColor(int i) {
        if (this.V != i) {
            this.V = i;
            this.p0 = i;
            this.r0 = i;
            this.s0 = i;
            b();
        }
    }

    public void setBoxBackgroundColorResource(int i) {
        setBoxBackgroundColor(bh.a(getContext(), i));
    }

    public void setBoxBackgroundColorStateList(ColorStateList colorStateList) {
        int defaultColor = colorStateList.getDefaultColor();
        this.p0 = defaultColor;
        this.V = defaultColor;
        this.q0 = colorStateList.getColorForState(new int[]{-16842910}, -1);
        this.r0 = colorStateList.getColorForState(new int[]{R.attr.state_focused, R.attr.state_enabled}, -1);
        this.s0 = colorStateList.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, -1);
        b();
    }

    public void setBoxBackgroundMode(int i) {
        if (i == this.P) {
            return;
        }
        this.P = i;
        if (this.e != null) {
            i();
        }
    }

    public void setBoxCollapsedPaddingTop(int i) {
        this.Q = i;
    }

    public void setBoxCornerFamily(int i) {
        ii0 ii0VarG = this.M.g();
        kh khVar = this.M.e;
        nv0 nv0VarP = oy.p(i);
        ii0VarG.a = nv0VarP;
        ii0.b(nv0VarP);
        ii0VarG.e = khVar;
        kh khVar2 = this.M.f;
        nv0 nv0VarP2 = oy.p(i);
        ii0VarG.b = nv0VarP2;
        ii0.b(nv0VarP2);
        ii0VarG.f = khVar2;
        kh khVar3 = this.M.h;
        nv0 nv0VarP3 = oy.p(i);
        ii0VarG.d = nv0VarP3;
        ii0.b(nv0VarP3);
        ii0VarG.h = khVar3;
        kh khVar4 = this.M.g;
        nv0 nv0VarP4 = oy.p(i);
        ii0VarG.c = nv0VarP4;
        ii0.b(nv0VarP4);
        ii0VarG.g = khVar4;
        this.M = ii0VarG.a();
        b();
    }

    public void setBoxStrokeColor(int i) {
        if (this.n0 != i) {
            this.n0 = i;
            x();
        }
    }

    public void setBoxStrokeColorStateList(ColorStateList colorStateList) {
        if (colorStateList.isStateful()) {
            this.l0 = colorStateList.getDefaultColor();
            this.t0 = colorStateList.getColorForState(new int[]{-16842910}, -1);
            this.m0 = colorStateList.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, -1);
            this.n0 = colorStateList.getColorForState(new int[]{R.attr.state_focused, R.attr.state_enabled}, -1);
        } else if (this.n0 != colorStateList.getDefaultColor()) {
            this.n0 = colorStateList.getDefaultColor();
        }
        x();
    }

    public void setBoxStrokeErrorColor(ColorStateList colorStateList) {
        if (this.o0 != colorStateList) {
            this.o0 = colorStateList;
            x();
        }
    }

    public void setBoxStrokeWidth(int i) {
        this.S = i;
        x();
    }

    public void setBoxStrokeWidthFocused(int i) {
        this.T = i;
        x();
    }

    public void setBoxStrokeWidthFocusedResource(int i) {
        setBoxStrokeWidthFocused(getResources().getDimensionPixelSize(i));
    }

    public void setBoxStrokeWidthResource(int i) {
        setBoxStrokeWidth(getResources().getDimensionPixelSize(i));
    }

    public void setCounterEnabled(boolean z) {
        if (this.l != z) {
            zx zxVar = this.k;
            if (z) {
                AppCompatTextView appCompatTextView = new AppCompatTextView(getContext());
                this.p = appCompatTextView;
                appCompatTextView.setId(R$id.textinput_counter);
                Typeface typeface = this.c0;
                if (typeface != null) {
                    this.p.setTypeface(typeface);
                }
                this.p.setMaxLines(1);
                zxVar.a(this.p, 2);
                ((ViewGroup.MarginLayoutParams) this.p.getLayoutParams()).setMarginStart(getResources().getDimensionPixelOffset(R$dimen.mtrl_textinput_counter_margin_start));
                o();
                if (this.p != null) {
                    EditText editText = this.e;
                    n(editText != null ? editText.getText() : null);
                }
            } else {
                zxVar.g(this.p, 2);
                this.p = null;
            }
            this.l = z;
        }
    }

    public void setCounterMaxLength(int i) {
        if (this.m != i) {
            if (i > 0) {
                this.m = i;
            } else {
                this.m = -1;
            }
            if (!this.l || this.p == null) {
                return;
            }
            EditText editText = this.e;
            n(editText == null ? null : editText.getText());
        }
    }

    public void setCounterOverflowTextAppearance(int i) {
        if (this.q != i) {
            this.q = i;
            o();
        }
    }

    public void setCounterOverflowTextColor(ColorStateList colorStateList) {
        if (this.A != colorStateList) {
            this.A = colorStateList;
            o();
        }
    }

    public void setCounterTextAppearance(int i) {
        if (this.r != i) {
            this.r = i;
            o();
        }
    }

    public void setCounterTextColor(ColorStateList colorStateList) {
        if (this.z != colorStateList) {
            this.z = colorStateList;
            o();
        }
    }

    public void setCursorColor(ColorStateList colorStateList) {
        if (this.B != colorStateList) {
            this.B = colorStateList;
            p();
        }
    }

    public void setCursorErrorColor(ColorStateList colorStateList) {
        if (this.C != colorStateList) {
            this.C = colorStateList;
            if (m() || (this.p != null && this.n)) {
                p();
            }
        }
    }

    public void setDefaultHintTextColor(ColorStateList colorStateList) {
        this.j0 = colorStateList;
        this.k0 = colorStateList;
        if (this.e != null) {
            u(false, false);
        }
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        k(this, z);
        super.setEnabled(z);
    }

    public void setEndIconActivated(boolean z) {
        this.d.h.setActivated(z);
    }

    public void setEndIconCheckable(boolean z) {
        this.d.h.setCheckable(z);
    }

    public void setEndIconContentDescription(int i) {
        fo foVar = this.d;
        CharSequence text = i != 0 ? foVar.getResources().getText(i) : null;
        CheckableImageButton checkableImageButton = foVar.h;
        if (checkableImageButton.getContentDescription() != text) {
            checkableImageButton.setContentDescription(text);
        }
    }

    public void setEndIconDrawable(int i) {
        fo foVar = this.d;
        Drawable drawableY = i != 0 ? rp0.y(foVar.getContext(), i) : null;
        CheckableImageButton checkableImageButton = foVar.h;
        checkableImageButton.setImageDrawable(drawableY);
        if (drawableY != null) {
            ColorStateList colorStateList = foVar.l;
            PorterDuff.Mode mode = foVar.m;
            TextInputLayout textInputLayout = foVar.b;
            nv0.e(textInputLayout, checkableImageButton, colorStateList, mode);
            nv0.I(textInputLayout, checkableImageButton, foVar.l);
        }
    }

    public void setEndIconMinSize(int i) {
        fo foVar = this.d;
        if (i < 0) {
            foVar.getClass();
            throw new IllegalArgumentException("endIconSize cannot be less than 0");
        }
        if (i != foVar.n) {
            foVar.n = i;
            CheckableImageButton checkableImageButton = foVar.h;
            checkableImageButton.setMinimumWidth(i);
            checkableImageButton.setMinimumHeight(i);
            CheckableImageButton checkableImageButton2 = foVar.d;
            checkableImageButton2.setMinimumWidth(i);
            checkableImageButton2.setMinimumHeight(i);
        }
    }

    public void setEndIconMode(int i) {
        this.d.g(i);
    }

    public void setEndIconOnClickListener(View.OnClickListener onClickListener) {
        this.d.setEndIconOnClickListener(onClickListener);
    }

    public void setEndIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.d.setEndIconOnLongClickListener(onLongClickListener);
    }

    public void setEndIconScaleType(ImageView.ScaleType scaleType) {
        fo foVar = this.d;
        foVar.o = scaleType;
        foVar.h.setScaleType(scaleType);
        foVar.d.setScaleType(scaleType);
    }

    public void setEndIconTintList(ColorStateList colorStateList) {
        fo foVar = this.d;
        if (foVar.l != colorStateList) {
            foVar.l = colorStateList;
            nv0.e(foVar.b, foVar.h, colorStateList, foVar.m);
        }
    }

    public void setEndIconTintMode(PorterDuff.Mode mode) {
        fo foVar = this.d;
        if (foVar.m != mode) {
            foVar.m = mode;
            nv0.e(foVar.b, foVar.h, foVar.l, mode);
        }
    }

    public void setEndIconVisible(boolean z) {
        this.d.h(z);
    }

    public void setError(CharSequence charSequence) {
        zx zxVar = this.k;
        if (!zxVar.q) {
            if (TextUtils.isEmpty(charSequence)) {
                return;
            } else {
                setErrorEnabled(true);
            }
        }
        if (TextUtils.isEmpty(charSequence)) {
            zxVar.f();
            return;
        }
        zxVar.c();
        zxVar.p = charSequence;
        zxVar.r.setText(charSequence);
        int i = zxVar.n;
        if (i != 1) {
            zxVar.o = 1;
        }
        zxVar.i(i, zxVar.o, zxVar.h(zxVar.r, charSequence));
    }

    public void setErrorAccessibilityLiveRegion(int i) {
        zx zxVar = this.k;
        zxVar.t = i;
        AppCompatTextView appCompatTextView = zxVar.r;
        if (appCompatTextView != null) {
            WeakHashMap weakHashMap = xr0.a;
            appCompatTextView.setAccessibilityLiveRegion(i);
        }
    }

    public void setErrorContentDescription(CharSequence charSequence) {
        zx zxVar = this.k;
        zxVar.s = charSequence;
        AppCompatTextView appCompatTextView = zxVar.r;
        if (appCompatTextView != null) {
            appCompatTextView.setContentDescription(charSequence);
        }
    }

    public void setErrorEnabled(boolean z) {
        zx zxVar = this.k;
        if (zxVar.q == z) {
            return;
        }
        zxVar.c();
        TextInputLayout textInputLayout = zxVar.h;
        if (z) {
            AppCompatTextView appCompatTextView = new AppCompatTextView(zxVar.g);
            zxVar.r = appCompatTextView;
            appCompatTextView.setId(R$id.textinput_error);
            zxVar.r.setTextAlignment(5);
            Typeface typeface = zxVar.B;
            if (typeface != null) {
                zxVar.r.setTypeface(typeface);
            }
            int i = zxVar.u;
            zxVar.u = i;
            AppCompatTextView appCompatTextView2 = zxVar.r;
            if (appCompatTextView2 != null) {
                textInputLayout.l(appCompatTextView2, i);
            }
            ColorStateList colorStateList = zxVar.v;
            zxVar.v = colorStateList;
            AppCompatTextView appCompatTextView3 = zxVar.r;
            if (appCompatTextView3 != null && colorStateList != null) {
                appCompatTextView3.setTextColor(colorStateList);
            }
            CharSequence charSequence = zxVar.s;
            zxVar.s = charSequence;
            AppCompatTextView appCompatTextView4 = zxVar.r;
            if (appCompatTextView4 != null) {
                appCompatTextView4.setContentDescription(charSequence);
            }
            int i2 = zxVar.t;
            zxVar.t = i2;
            AppCompatTextView appCompatTextView5 = zxVar.r;
            if (appCompatTextView5 != null) {
                WeakHashMap weakHashMap = xr0.a;
                appCompatTextView5.setAccessibilityLiveRegion(i2);
            }
            zxVar.r.setVisibility(4);
            zxVar.a(zxVar.r, 0);
        } else {
            zxVar.f();
            zxVar.g(zxVar.r, 0);
            zxVar.r = null;
            textInputLayout.r();
            textInputLayout.x();
        }
        zxVar.q = z;
    }

    public void setErrorIconDrawable(int i) {
        fo foVar = this.d;
        foVar.i(i != 0 ? rp0.y(foVar.getContext(), i) : null);
        nv0.I(foVar.b, foVar.d, foVar.e);
    }

    public void setErrorIconOnClickListener(View.OnClickListener onClickListener) {
        this.d.setErrorIconOnClickListener(onClickListener);
    }

    public void setErrorIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.d.setErrorIconOnLongClickListener(onLongClickListener);
    }

    public void setErrorIconTintList(ColorStateList colorStateList) {
        fo foVar = this.d;
        if (foVar.e != colorStateList) {
            foVar.e = colorStateList;
            nv0.e(foVar.b, foVar.d, colorStateList, foVar.f);
        }
    }

    public void setErrorIconTintMode(PorterDuff.Mode mode) {
        fo foVar = this.d;
        if (foVar.f != mode) {
            foVar.f = mode;
            nv0.e(foVar.b, foVar.d, foVar.e, mode);
        }
    }

    public void setErrorTextAppearance(int i) {
        zx zxVar = this.k;
        zxVar.u = i;
        AppCompatTextView appCompatTextView = zxVar.r;
        if (appCompatTextView != null) {
            zxVar.h.l(appCompatTextView, i);
        }
    }

    public void setErrorTextColor(ColorStateList colorStateList) {
        zx zxVar = this.k;
        zxVar.v = colorStateList;
        AppCompatTextView appCompatTextView = zxVar.r;
        if (appCompatTextView == null || colorStateList == null) {
            return;
        }
        appCompatTextView.setTextColor(colorStateList);
    }

    public void setExpandedHintEnabled(boolean z) {
        if (this.w0 != z) {
            this.w0 = z;
            u(false, false);
        }
    }

    public void setHelperText(CharSequence charSequence) {
        boolean zIsEmpty = TextUtils.isEmpty(charSequence);
        zx zxVar = this.k;
        if (zIsEmpty) {
            if (zxVar.x) {
                setHelperTextEnabled(false);
                return;
            }
            return;
        }
        if (!zxVar.x) {
            setHelperTextEnabled(true);
        }
        zxVar.c();
        zxVar.w = charSequence;
        zxVar.y.setText(charSequence);
        int i = zxVar.n;
        if (i != 2) {
            zxVar.o = 2;
        }
        zxVar.i(i, zxVar.o, zxVar.h(zxVar.y, charSequence));
    }

    public void setHelperTextColor(ColorStateList colorStateList) {
        zx zxVar = this.k;
        zxVar.A = colorStateList;
        AppCompatTextView appCompatTextView = zxVar.y;
        if (appCompatTextView == null || colorStateList == null) {
            return;
        }
        appCompatTextView.setTextColor(colorStateList);
    }

    public void setHelperTextEnabled(boolean z) {
        zx zxVar = this.k;
        if (zxVar.x == z) {
            return;
        }
        zxVar.c();
        if (z) {
            AppCompatTextView appCompatTextView = new AppCompatTextView(zxVar.g);
            zxVar.y = appCompatTextView;
            appCompatTextView.setId(R$id.textinput_helper_text);
            zxVar.y.setTextAlignment(5);
            Typeface typeface = zxVar.B;
            if (typeface != null) {
                zxVar.y.setTypeface(typeface);
            }
            zxVar.y.setVisibility(4);
            AppCompatTextView appCompatTextView2 = zxVar.y;
            WeakHashMap weakHashMap = xr0.a;
            appCompatTextView2.setAccessibilityLiveRegion(1);
            int i = zxVar.z;
            zxVar.z = i;
            AppCompatTextView appCompatTextView3 = zxVar.y;
            if (appCompatTextView3 != null) {
                appCompatTextView3.setTextAppearance(i);
            }
            ColorStateList colorStateList = zxVar.A;
            zxVar.A = colorStateList;
            AppCompatTextView appCompatTextView4 = zxVar.y;
            if (appCompatTextView4 != null && colorStateList != null) {
                appCompatTextView4.setTextColor(colorStateList);
            }
            zxVar.a(zxVar.y, 1);
            zxVar.y.setAccessibilityDelegate(new yx(zxVar));
        } else {
            zxVar.c();
            int i2 = zxVar.n;
            if (i2 == 2) {
                zxVar.o = 0;
            }
            zxVar.i(i2, zxVar.o, zxVar.h(zxVar.y, ExtensionRequestData.EMPTY_VALUE));
            zxVar.g(zxVar.y, 1);
            zxVar.y = null;
            TextInputLayout textInputLayout = zxVar.h;
            textInputLayout.r();
            textInputLayout.x();
        }
        zxVar.x = z;
    }

    public void setHelperTextTextAppearance(int i) {
        zx zxVar = this.k;
        zxVar.z = i;
        AppCompatTextView appCompatTextView = zxVar.y;
        if (appCompatTextView != null) {
            appCompatTextView.setTextAppearance(i);
        }
    }

    public void setHint(CharSequence charSequence) {
        if (this.D) {
            setHintInternal(charSequence);
            sendAccessibilityEvent(2048);
        }
    }

    public void setHintAnimationEnabled(boolean z) {
        this.x0 = z;
    }

    public void setHintEnabled(boolean z) {
        if (z != this.D) {
            this.D = z;
            if (z) {
                CharSequence hint = this.e.getHint();
                if (!TextUtils.isEmpty(hint)) {
                    if (TextUtils.isEmpty(this.E)) {
                        setHint(hint);
                    }
                    this.e.setHint((CharSequence) null);
                }
                this.F = true;
            } else {
                this.F = false;
                if (!TextUtils.isEmpty(this.E) && TextUtils.isEmpty(this.e.getHint())) {
                    this.e.setHint(this.E);
                }
                setHintInternal(null);
            }
            if (this.e != null) {
                t();
            }
        }
    }

    public void setHintTextAppearance(int i) {
        yd ydVar = this.v0;
        ydVar.k(i);
        this.k0 = ydVar.o;
        if (this.e != null) {
            u(false, false);
            t();
        }
    }

    public void setHintTextColor(ColorStateList colorStateList) {
        if (this.k0 != colorStateList) {
            if (this.j0 == null) {
                yd ydVar = this.v0;
                if (ydVar.o != colorStateList) {
                    ydVar.o = colorStateList;
                    ydVar.i(false);
                }
            }
            this.k0 = colorStateList;
            if (this.e != null) {
                u(false, false);
            }
        }
    }

    public void setLengthCounter(kn0 kn0Var) {
        this.o = kn0Var;
    }

    public void setMaxEms(int i) {
        this.h = i;
        EditText editText = this.e;
        if (editText == null || i == -1) {
            return;
        }
        editText.setMaxEms(i);
    }

    public void setMaxWidth(int i) {
        this.j = i;
        EditText editText = this.e;
        if (editText == null || i == -1) {
            return;
        }
        editText.setMaxWidth(i);
    }

    public void setMaxWidthResource(int i) {
        setMaxWidth(getContext().getResources().getDimensionPixelSize(i));
    }

    public void setMinEms(int i) {
        this.g = i;
        EditText editText = this.e;
        if (editText == null || i == -1) {
            return;
        }
        editText.setMinEms(i);
    }

    public void setMinWidth(int i) {
        this.i = i;
        EditText editText = this.e;
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
        fo foVar = this.d;
        foVar.h.setContentDescription(i != 0 ? foVar.getResources().getText(i) : null);
    }

    @Deprecated
    public void setPasswordVisibilityToggleDrawable(int i) {
        fo foVar = this.d;
        foVar.h.setImageDrawable(i != 0 ? rp0.y(foVar.getContext(), i) : null);
    }

    @Deprecated
    public void setPasswordVisibilityToggleEnabled(boolean z) {
        fo foVar = this.d;
        if (z && foVar.j != 1) {
            foVar.g(1);
        } else if (z) {
            foVar.getClass();
        } else {
            foVar.g(0);
        }
    }

    @Deprecated
    public void setPasswordVisibilityToggleTintList(ColorStateList colorStateList) {
        fo foVar = this.d;
        foVar.l = colorStateList;
        nv0.e(foVar.b, foVar.h, colorStateList, foVar.m);
    }

    @Deprecated
    public void setPasswordVisibilityToggleTintMode(PorterDuff.Mode mode) {
        fo foVar = this.d;
        foVar.m = mode;
        nv0.e(foVar.b, foVar.h, foVar.l, mode);
    }

    public void setPlaceholderText(CharSequence charSequence) {
        if (this.u == null) {
            AppCompatTextView appCompatTextView = new AppCompatTextView(getContext());
            this.u = appCompatTextView;
            appCompatTextView.setId(R$id.textinput_placeholder);
            AppCompatTextView appCompatTextView2 = this.u;
            WeakHashMap weakHashMap = xr0.a;
            appCompatTextView2.setImportantForAccessibility(2);
            Fade fadeD = d();
            this.x = fadeD;
            fadeD.c = 67L;
            this.y = d();
            setPlaceholderTextAppearance(this.w);
            setPlaceholderTextColor(this.v);
        }
        if (TextUtils.isEmpty(charSequence)) {
            setPlaceholderTextEnabled(false);
        } else {
            if (!this.t) {
                setPlaceholderTextEnabled(true);
            }
            this.s = charSequence;
        }
        EditText editText = this.e;
        v(editText == null ? null : editText.getText());
    }

    public void setPlaceholderTextAppearance(int i) {
        this.w = i;
        AppCompatTextView appCompatTextView = this.u;
        if (appCompatTextView != null) {
            appCompatTextView.setTextAppearance(i);
        }
    }

    public void setPlaceholderTextColor(ColorStateList colorStateList) {
        if (this.v != colorStateList) {
            this.v = colorStateList;
            AppCompatTextView appCompatTextView = this.u;
            if (appCompatTextView == null || colorStateList == null) {
                return;
            }
            appCompatTextView.setTextColor(colorStateList);
        }
    }

    public void setPrefixText(CharSequence charSequence) {
        mk0 mk0Var = this.c;
        mk0Var.getClass();
        mk0Var.d = TextUtils.isEmpty(charSequence) ? null : charSequence;
        mk0Var.c.setText(charSequence);
        mk0Var.e();
    }

    public void setPrefixTextAppearance(int i) {
        this.c.c.setTextAppearance(i);
    }

    public void setPrefixTextColor(ColorStateList colorStateList) {
        this.c.c.setTextColor(colorStateList);
    }

    public void setShapeAppearanceModel(ki0 ki0Var) {
        u50 u50Var = this.G;
        if (u50Var == null || u50Var.b.a == ki0Var) {
            return;
        }
        this.M = ki0Var;
        b();
    }

    public void setStartIconCheckable(boolean z) {
        this.c.e.setCheckable(z);
    }

    public void setStartIconContentDescription(CharSequence charSequence) {
        CheckableImageButton checkableImageButton = this.c.e;
        if (checkableImageButton.getContentDescription() != charSequence) {
            checkableImageButton.setContentDescription(charSequence);
        }
    }

    public void setStartIconDrawable(int i) {
        setStartIconDrawable(i != 0 ? rp0.y(getContext(), i) : null);
    }

    public void setStartIconMinSize(int i) {
        mk0 mk0Var = this.c;
        if (i < 0) {
            mk0Var.getClass();
            throw new IllegalArgumentException("startIconSize cannot be less than 0");
        }
        if (i != mk0Var.h) {
            mk0Var.h = i;
            CheckableImageButton checkableImageButton = mk0Var.e;
            checkableImageButton.setMinimumWidth(i);
            checkableImageButton.setMinimumHeight(i);
        }
    }

    public void setStartIconOnClickListener(View.OnClickListener onClickListener) {
        this.c.setStartIconOnClickListener(onClickListener);
    }

    public void setStartIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.c.setStartIconOnLongClickListener(onLongClickListener);
    }

    public void setStartIconScaleType(ImageView.ScaleType scaleType) {
        mk0 mk0Var = this.c;
        mk0Var.i = scaleType;
        mk0Var.e.setScaleType(scaleType);
    }

    public void setStartIconTintList(ColorStateList colorStateList) {
        mk0 mk0Var = this.c;
        if (mk0Var.f != colorStateList) {
            mk0Var.f = colorStateList;
            nv0.e(mk0Var.b, mk0Var.e, colorStateList, mk0Var.g);
        }
    }

    public void setStartIconTintMode(PorterDuff.Mode mode) {
        mk0 mk0Var = this.c;
        if (mk0Var.g != mode) {
            mk0Var.g = mode;
            nv0.e(mk0Var.b, mk0Var.e, mk0Var.f, mode);
        }
    }

    public void setStartIconVisible(boolean z) {
        this.c.c(z);
    }

    public void setSuffixText(CharSequence charSequence) {
        fo foVar = this.d;
        foVar.getClass();
        foVar.q = TextUtils.isEmpty(charSequence) ? null : charSequence;
        foVar.r.setText(charSequence);
        foVar.n();
    }

    public void setSuffixTextAppearance(int i) {
        this.d.r.setTextAppearance(i);
    }

    public void setSuffixTextColor(ColorStateList colorStateList) {
        this.d.r.setTextColor(colorStateList);
    }

    public void setTextInputAccessibilityDelegate(jn0 jn0Var) {
        EditText editText = this.e;
        if (editText != null) {
            xr0.p(editText, jn0Var);
        }
    }

    public void setTypeface(Typeface typeface) {
        if (typeface != this.c0) {
            this.c0 = typeface;
            yd ydVar = this.v0;
            boolean zM = ydVar.m(typeface);
            boolean zO = ydVar.o(typeface);
            if (zM || zO) {
                ydVar.i(false);
            }
            zx zxVar = this.k;
            if (typeface != zxVar.B) {
                zxVar.B = typeface;
                AppCompatTextView appCompatTextView = zxVar.r;
                if (appCompatTextView != null) {
                    appCompatTextView.setTypeface(typeface);
                }
                AppCompatTextView appCompatTextView2 = zxVar.y;
                if (appCompatTextView2 != null) {
                    appCompatTextView2.setTypeface(typeface);
                }
            }
            AppCompatTextView appCompatTextView3 = this.p;
            if (appCompatTextView3 != null) {
                appCompatTextView3.setTypeface(typeface);
            }
        }
    }

    public final void t() {
        if (this.P != 1) {
            FrameLayout frameLayout = this.b;
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
        EditText editText = this.e;
        boolean z3 = (editText == null || TextUtils.isEmpty(editText.getText())) ? false : true;
        EditText editText2 = this.e;
        boolean z4 = editText2 != null && editText2.hasFocus();
        ColorStateList colorStateList2 = this.j0;
        yd ydVar = this.v0;
        if (colorStateList2 != null) {
            ydVar.j(colorStateList2);
        }
        if (!zIsEnabled) {
            ColorStateList colorStateList3 = this.j0;
            ydVar.j(ColorStateList.valueOf(colorStateList3 != null ? colorStateList3.getColorForState(new int[]{-16842910}, this.t0) : this.t0));
        } else if (m()) {
            AppCompatTextView appCompatTextView2 = this.k.r;
            ydVar.j(appCompatTextView2 != null ? appCompatTextView2.getTextColors() : null);
        } else if (this.n && (appCompatTextView = this.p) != null) {
            ydVar.j(appCompatTextView.getTextColors());
        } else if (z4 && (colorStateList = this.k0) != null && ydVar.o != colorStateList) {
            ydVar.o = colorStateList;
            ydVar.i(false);
        }
        fo foVar = this.d;
        mk0 mk0Var = this.c;
        if (z3 || !this.w0 || (isEnabled() && z4)) {
            if (z2 || this.u0) {
                ValueAnimator valueAnimator = this.y0;
                if (valueAnimator != null && valueAnimator.isRunning()) {
                    this.y0.cancel();
                }
                if (z && this.x0) {
                    a(1.0f);
                } else {
                    ydVar.p(1.0f);
                }
                this.u0 = false;
                if (e()) {
                    j();
                }
                EditText editText3 = this.e;
                v(editText3 != null ? editText3.getText() : null);
                mk0Var.k = false;
                mk0Var.e();
                foVar.s = false;
                foVar.n();
                return;
            }
            return;
        }
        if (z2 || !this.u0) {
            ValueAnimator valueAnimator2 = this.y0;
            if (valueAnimator2 != null && valueAnimator2.isRunning()) {
                this.y0.cancel();
            }
            if (z && this.x0) {
                a(0.0f);
            } else {
                ydVar.p(0.0f);
            }
            if (e() && !((ki) this.G).y.s.isEmpty() && e()) {
                ((ki) this.G).w(0.0f, 0.0f, 0.0f, 0.0f);
            }
            this.u0 = true;
            AppCompatTextView appCompatTextView3 = this.u;
            if (appCompatTextView3 != null && this.t) {
                appCompatTextView3.setText((CharSequence) null);
                jp0.a(this.b, this.y);
                this.u.setVisibility(4);
            }
            mk0Var.k = true;
            mk0Var.e();
            foVar.s = true;
            foVar.n();
        }
    }

    public final void v(Editable editable) {
        ((vk) this.o).getClass();
        int length = editable != null ? editable.length() : 0;
        FrameLayout frameLayout = this.b;
        if (length != 0 || this.u0) {
            AppCompatTextView appCompatTextView = this.u;
            if (appCompatTextView == null || !this.t) {
                return;
            }
            appCompatTextView.setText((CharSequence) null);
            jp0.a(frameLayout, this.y);
            this.u.setVisibility(4);
            return;
        }
        if (this.u == null || !this.t || TextUtils.isEmpty(this.s)) {
            return;
        }
        this.u.setText(this.s);
        jp0.a(frameLayout, this.x);
        this.u.setVisibility(0);
        this.u.bringToFront();
        announceForAccessibility(this.s);
    }

    public final void w(boolean z, boolean z2) {
        int defaultColor = this.o0.getDefaultColor();
        int colorForState = this.o0.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, defaultColor);
        int colorForState2 = this.o0.getColorForState(new int[]{R.attr.state_activated, R.attr.state_enabled}, defaultColor);
        if (z) {
            this.U = colorForState2;
        } else if (z2) {
            this.U = colorForState;
        } else {
            this.U = defaultColor;
        }
    }

    public final void x() {
        AppCompatTextView appCompatTextView;
        EditText editText;
        EditText editText2;
        if (this.G == null || this.P == 0) {
            return;
        }
        boolean z = false;
        boolean z2 = isFocused() || ((editText2 = this.e) != null && editText2.hasFocus());
        if (isHovered() || ((editText = this.e) != null && editText.isHovered())) {
            z = true;
        }
        if (!isEnabled()) {
            this.U = this.t0;
        } else if (m()) {
            if (this.o0 != null) {
                w(z2, z);
            } else {
                this.U = getErrorCurrentTextColors();
            }
        } else if (!this.n || (appCompatTextView = this.p) == null) {
            if (z2) {
                this.U = this.n0;
            } else if (z) {
                this.U = this.m0;
            } else {
                this.U = this.l0;
            }
        } else if (this.o0 != null) {
            w(z2, z);
        } else {
            this.U = appCompatTextView.getCurrentTextColor();
        }
        if (Build.VERSION.SDK_INT >= 29) {
            p();
        }
        fo foVar = this.d;
        foVar.l();
        CheckableImageButton checkableImageButton = foVar.d;
        ColorStateList colorStateList = foVar.e;
        TextInputLayout textInputLayout = foVar.b;
        nv0.I(textInputLayout, checkableImageButton, colorStateList);
        ColorStateList colorStateList2 = foVar.l;
        CheckableImageButton checkableImageButton2 = foVar.h;
        nv0.I(textInputLayout, checkableImageButton2, colorStateList2);
        if (foVar.b() instanceof gm) {
            if (!textInputLayout.m() || checkableImageButton2.getDrawable() == null) {
                nv0.e(textInputLayout, checkableImageButton2, foVar.l, foVar.m);
            } else {
                Drawable drawableMutate = checkableImageButton2.getDrawable().mutate();
                hl.g(drawableMutate, textInputLayout.getErrorCurrentTextColors());
                checkableImageButton2.setImageDrawable(drawableMutate);
            }
        }
        mk0 mk0Var = this.c;
        nv0.I(mk0Var.b, mk0Var.e, mk0Var.f);
        if (this.P == 2) {
            int i = this.R;
            if (z2 && isEnabled()) {
                this.R = this.T;
            } else {
                this.R = this.S;
            }
            if (this.R != i && e() && !this.u0) {
                if (e()) {
                    ((ki) this.G).w(0.0f, 0.0f, 0.0f, 0.0f);
                }
                j();
            }
        }
        if (this.P == 1) {
            if (!isEnabled()) {
                this.V = this.q0;
            } else if (z && !z2) {
                this.V = this.s0;
            } else if (z2) {
                this.V = this.r0;
            } else {
                this.V = this.p0;
            }
        }
        b();
    }

    public TextInputLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.textInputStyle);
    }

    public void setStartIconDrawable(Drawable drawable) {
        this.c.b(drawable);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public TextInputLayout(Context context, AttributeSet attributeSet, int i) {
        int i2 = B0;
        super(y50.a(context, attributeSet, i, i2), attributeSet, i);
        this.g = -1;
        this.h = -1;
        this.i = -1;
        this.j = -1;
        this.k = new zx(this);
        this.o = new vk(10);
        this.W = new Rect();
        this.a0 = new Rect();
        this.b0 = new RectF();
        this.f0 = new LinkedHashSet();
        yd ydVar = new yd(this);
        this.v0 = ydVar;
        Context context2 = getContext();
        setOrientation(1);
        setWillNotDraw(false);
        setAddStatesFromChildren(true);
        FrameLayout frameLayout = new FrameLayout(context2);
        this.b = frameLayout;
        frameLayout.setAddStatesFromChildren(true);
        LinearInterpolator linearInterpolator = t3.a;
        ydVar.W = linearInterpolator;
        ydVar.i(false);
        ydVar.V = linearInterpolator;
        ydVar.i(false);
        ydVar.l(8388659);
        po poVarE = un0.e(context2, attributeSet, R$styleable.TextInputLayout, i, i2, R$styleable.TextInputLayout_counterTextAppearance, R$styleable.TextInputLayout_counterOverflowTextAppearance, R$styleable.TextInputLayout_errorTextAppearance, R$styleable.TextInputLayout_helperTextTextAppearance, R$styleable.TextInputLayout_hintTextAppearance);
        mk0 mk0Var = new mk0(this, poVarE);
        this.c = mk0Var;
        int i3 = R$styleable.TextInputLayout_hintEnabled;
        TypedArray typedArray = (TypedArray) poVarE.d;
        this.D = typedArray.getBoolean(i3, true);
        setHint(typedArray.getText(R$styleable.TextInputLayout_android_hint));
        this.x0 = typedArray.getBoolean(R$styleable.TextInputLayout_hintAnimationEnabled, true);
        this.w0 = typedArray.getBoolean(R$styleable.TextInputLayout_expandedHintEnabled, true);
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
        this.M = ki0.c(context2, attributeSet, i, i2).a();
        this.O = context2.getResources().getDimensionPixelOffset(R$dimen.mtrl_textinput_box_label_cutout_padding);
        this.Q = typedArray.getDimensionPixelOffset(R$styleable.TextInputLayout_boxCollapsedPaddingTop, 0);
        this.S = typedArray.getDimensionPixelSize(R$styleable.TextInputLayout_boxStrokeWidth, context2.getResources().getDimensionPixelSize(R$dimen.mtrl_textinput_box_stroke_width_default));
        this.T = typedArray.getDimensionPixelSize(R$styleable.TextInputLayout_boxStrokeWidthFocused, context2.getResources().getDimensionPixelSize(R$dimen.mtrl_textinput_box_stroke_width_focused));
        this.R = this.S;
        float dimension = typedArray.getDimension(R$styleable.TextInputLayout_boxCornerRadiusTopStart, -1.0f);
        float dimension2 = typedArray.getDimension(R$styleable.TextInputLayout_boxCornerRadiusTopEnd, -1.0f);
        float dimension3 = typedArray.getDimension(R$styleable.TextInputLayout_boxCornerRadiusBottomEnd, -1.0f);
        float dimension4 = typedArray.getDimension(R$styleable.TextInputLayout_boxCornerRadiusBottomStart, -1.0f);
        ii0 ii0VarG = this.M.g();
        if (dimension >= 0.0f) {
            ii0VarG.e = new e(dimension);
        }
        if (dimension2 >= 0.0f) {
            ii0VarG.f = new e(dimension2);
        }
        if (dimension3 >= 0.0f) {
            ii0VarG.g = new e(dimension3);
        }
        if (dimension4 >= 0.0f) {
            ii0VarG.h = new e(dimension4);
        }
        this.M = ii0VarG.a();
        ColorStateList colorStateListR = xi.r(context2, poVarE, R$styleable.TextInputLayout_boxBackgroundColor);
        if (colorStateListR != null) {
            int defaultColor = colorStateListR.getDefaultColor();
            this.p0 = defaultColor;
            this.V = defaultColor;
            if (colorStateListR.isStateful()) {
                this.q0 = colorStateListR.getColorForState(new int[]{-16842910}, -1);
                this.r0 = colorStateListR.getColorForState(new int[]{R.attr.state_focused, R.attr.state_enabled}, -1);
                this.s0 = colorStateListR.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, -1);
            } else {
                this.r0 = this.p0;
                ColorStateList colorStateListW = rp0.w(context2, R$color.mtrl_filled_background_color);
                this.q0 = colorStateListW.getColorForState(new int[]{-16842910}, -1);
                this.s0 = colorStateListW.getColorForState(new int[]{R.attr.state_hovered}, -1);
            }
        } else {
            this.V = 0;
            this.p0 = 0;
            this.q0 = 0;
            this.r0 = 0;
            this.s0 = 0;
        }
        if (typedArray.hasValue(R$styleable.TextInputLayout_android_textColorHint)) {
            ColorStateList colorStateListW2 = poVarE.w(R$styleable.TextInputLayout_android_textColorHint);
            this.k0 = colorStateListW2;
            this.j0 = colorStateListW2;
        }
        ColorStateList colorStateListR2 = xi.r(context2, poVarE, R$styleable.TextInputLayout_boxStrokeColor);
        this.n0 = typedArray.getColor(R$styleable.TextInputLayout_boxStrokeColor, 0);
        this.l0 = bh.a(context2, R$color.mtrl_textinput_default_box_stroke_color);
        this.t0 = bh.a(context2, R$color.mtrl_textinput_disabled_color);
        this.m0 = bh.a(context2, R$color.mtrl_textinput_hovered_box_stroke_color);
        if (colorStateListR2 != null) {
            setBoxStrokeColorStateList(colorStateListR2);
        }
        if (typedArray.hasValue(R$styleable.TextInputLayout_boxStrokeErrorColor)) {
            setBoxStrokeErrorColor(xi.r(context2, poVarE, R$styleable.TextInputLayout_boxStrokeErrorColor));
        }
        if (typedArray.getResourceId(R$styleable.TextInputLayout_hintTextAppearance, -1) != -1) {
            setHintTextAppearance(typedArray.getResourceId(R$styleable.TextInputLayout_hintTextAppearance, 0));
        }
        this.B = poVarE.w(R$styleable.TextInputLayout_cursorColor);
        this.C = poVarE.w(R$styleable.TextInputLayout_cursorErrorColor);
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
        this.r = typedArray.getResourceId(R$styleable.TextInputLayout_counterTextAppearance, 0);
        this.q = typedArray.getResourceId(R$styleable.TextInputLayout_counterOverflowTextAppearance, 0);
        setBoxBackgroundMode(typedArray.getInt(R$styleable.TextInputLayout_boxBackgroundMode, 0));
        setErrorContentDescription(text);
        setErrorAccessibilityLiveRegion(i4);
        setCounterOverflowTextAppearance(this.q);
        setHelperTextTextAppearance(resourceId2);
        setErrorTextAppearance(resourceId);
        setCounterTextAppearance(this.r);
        setPlaceholderText(text3);
        setPlaceholderTextAppearance(resourceId3);
        if (typedArray.hasValue(R$styleable.TextInputLayout_errorTextColor)) {
            setErrorTextColor(poVarE.w(R$styleable.TextInputLayout_errorTextColor));
        }
        if (typedArray.hasValue(R$styleable.TextInputLayout_helperTextTextColor)) {
            setHelperTextColor(poVarE.w(R$styleable.TextInputLayout_helperTextTextColor));
        }
        if (typedArray.hasValue(R$styleable.TextInputLayout_hintTextColor)) {
            setHintTextColor(poVarE.w(R$styleable.TextInputLayout_hintTextColor));
        }
        if (typedArray.hasValue(R$styleable.TextInputLayout_counterTextColor)) {
            setCounterTextColor(poVarE.w(R$styleable.TextInputLayout_counterTextColor));
        }
        if (typedArray.hasValue(R$styleable.TextInputLayout_counterOverflowTextColor)) {
            setCounterOverflowTextColor(poVarE.w(R$styleable.TextInputLayout_counterOverflowTextColor));
        }
        if (typedArray.hasValue(R$styleable.TextInputLayout_placeholderTextColor)) {
            setPlaceholderTextColor(poVarE.w(R$styleable.TextInputLayout_placeholderTextColor));
        }
        fo foVar = new fo(this, poVarE);
        this.d = foVar;
        boolean z4 = typedArray.getBoolean(R$styleable.TextInputLayout_android_enabled, true);
        poVarE.P();
        WeakHashMap weakHashMap = xr0.a;
        setImportantForAccessibility(2);
        int i5 = Build.VERSION.SDK_INT;
        if (i5 >= 26 && i5 >= 26) {
            or0.m(this, 1);
        }
        frameLayout.addView(mk0Var);
        frameLayout.addView(foVar);
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
        this.d.h.setContentDescription(charSequence);
    }

    @Deprecated
    public void setPasswordVisibilityToggleDrawable(Drawable drawable) {
        this.d.h.setImageDrawable(drawable);
    }

    public void setErrorIconDrawable(Drawable drawable) {
        this.d.i(drawable);
    }

    public void setEndIconContentDescription(CharSequence charSequence) {
        CheckableImageButton checkableImageButton = this.d.h;
        if (checkableImageButton.getContentDescription() != charSequence) {
            checkableImageButton.setContentDescription(charSequence);
        }
    }

    public void setEndIconDrawable(Drawable drawable) {
        fo foVar = this.d;
        CheckableImageButton checkableImageButton = foVar.h;
        checkableImageButton.setImageDrawable(drawable);
        if (drawable != null) {
            ColorStateList colorStateList = foVar.l;
            PorterDuff.Mode mode = foVar.m;
            TextInputLayout textInputLayout = foVar.b;
            nv0.e(textInputLayout, checkableImageButton, colorStateList, mode);
            nv0.I(textInputLayout, checkableImageButton, foVar.l);
        }
    }
}
