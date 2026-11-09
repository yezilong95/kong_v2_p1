package com.google.android.material.chip;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatCheckBox;
import com.google.android.material.R$attr;
import com.google.android.material.R$id;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.chip.Chip;
import defpackage.cb;
import defpackage.dd0;
import defpackage.de;
import defpackage.ed0;
import defpackage.ee;
import defpackage.f51;
import defpackage.fa0;
import defpackage.fe;
import defpackage.ge;
import defpackage.gp0;
import defpackage.he;
import defpackage.i01;
import defpackage.i2;
import defpackage.jp;
import defpackage.kv0;
import defpackage.la0;
import defpackage.ma0;
import defpackage.na;
import defpackage.pd0;
import defpackage.qm;
import defpackage.rf0;
import defpackage.sz0;
import defpackage.t40;
import defpackage.t41;
import defpackage.tz0;
import defpackage.vz0;
import defpackage.z91;
import defpackage.zu0;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Locale;
import java.util.WeakHashMap;
import org.java_websocket.framing.CloseFrame;

/* JADX INFO: loaded from: classes.dex */
public class Chip extends AppCompatCheckBox implements ge, kv0, ed0 {
    public static final int e0 = R$style.Widget_MaterialComponents_Chip_Action;
    public static final Rect f0 = new Rect();
    public static final int[] g0 = {R.attr.state_selected};
    public static final int[] h0 = {R.attr.state_checkable};
    public he I;
    public InsetDrawable J;
    public RippleDrawable K;
    public View.OnClickListener L;
    public CompoundButton.OnCheckedChangeListener M;
    public dd0 N;
    public boolean O;
    public boolean P;
    public boolean Q;
    public boolean R;
    public boolean S;
    public int T;
    public int U;
    public CharSequence V;
    public final fe W;
    public boolean a0;
    public final Rect b0;
    public final RectF c0;
    public final de d0;

    public Chip(Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public RectF getCloseIconTouchBounds() {
        RectF rectF = this.c0;
        rectF.setEmpty();
        if (d() && this.L != null) {
            he heVar = this.I;
            Rect bounds = heVar.getBounds();
            rectF.setEmpty();
            if (heVar.b0()) {
                float f = heVar.I0 + heVar.H0 + heVar.t0 + heVar.G0 + heVar.F0;
                if (jp.a(heVar) == 0) {
                    float f2 = bounds.right;
                    rectF.right = f2;
                    rectF.left = f2 - f;
                } else {
                    float f3 = bounds.left;
                    rectF.left = f3;
                    rectF.right = f3 + f;
                }
                rectF.top = bounds.top;
                rectF.bottom = bounds.bottom;
            }
        }
        return rectF;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Rect getCloseIconTouchBoundsInt() {
        RectF closeIconTouchBounds = getCloseIconTouchBounds();
        int i = (int) closeIconTouchBounds.left;
        int i2 = (int) closeIconTouchBounds.top;
        int i3 = (int) closeIconTouchBounds.right;
        int i4 = (int) closeIconTouchBounds.bottom;
        Rect rect = this.b0;
        rect.set(i, i2, i3, i4);
        return rect;
    }

    private sz0 getTextAppearance() {
        he heVar = this.I;
        if (heVar != null) {
            return heVar.P0.g;
        }
        return null;
    }

    private void setCloseIconHovered(boolean z) {
        if (this.Q != z) {
            this.Q = z;
            refreshDrawableState();
        }
    }

    private void setCloseIconPressed(boolean z) {
        if (this.P != z) {
            this.P = z;
            refreshDrawableState();
        }
    }

    public final void c(int i) {
        this.U = i;
        if (!this.S) {
            InsetDrawable insetDrawable = this.J;
            if (insetDrawable == null) {
                int[] iArr = gp0.a;
                f();
                return;
            } else {
                if (insetDrawable != null) {
                    this.J = null;
                    setMinWidth(0);
                    setMinHeight((int) getChipMinHeight());
                    int[] iArr2 = gp0.a;
                    f();
                    return;
                }
                return;
            }
        }
        int iMax = Math.max(0, i - ((int) this.I.e0));
        int iMax2 = Math.max(0, i - this.I.getIntrinsicWidth());
        if (iMax2 <= 0 && iMax <= 0) {
            InsetDrawable insetDrawable2 = this.J;
            if (insetDrawable2 == null) {
                int[] iArr3 = gp0.a;
                f();
                return;
            } else {
                if (insetDrawable2 != null) {
                    this.J = null;
                    setMinWidth(0);
                    setMinHeight((int) getChipMinHeight());
                    int[] iArr4 = gp0.a;
                    f();
                    return;
                }
                return;
            }
        }
        int i2 = iMax2 > 0 ? iMax2 / 2 : 0;
        int i3 = iMax > 0 ? iMax / 2 : 0;
        if (this.J != null) {
            Rect rect = new Rect();
            this.J.getPadding(rect);
            if (rect.top == i3 && rect.bottom == i3 && rect.left == i2 && rect.right == i2) {
                int[] iArr5 = gp0.a;
                f();
                return;
            }
        }
        if (getMinHeight() != i) {
            setMinHeight(i);
        }
        if (getMinWidth() != i) {
            setMinWidth(i);
        }
        this.J = new InsetDrawable((Drawable) this.I, i2, i3, i2, i3);
        int[] iArr6 = gp0.a;
        f();
    }

    public final boolean d() {
        he heVar = this.I;
        if (heVar == null) {
            return false;
        }
        Drawable drawable = heVar.q0;
        return (drawable != null ? z91.v0(drawable) : null) != null;
    }

    @Override // android.view.View
    public final boolean dispatchHoverEvent(MotionEvent motionEvent) {
        return !this.a0 ? super.dispatchHoverEvent(motionEvent) : this.W.m(motionEvent) || super.dispatchHoverEvent(motionEvent);
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0057  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean dispatchKeyEvent(android.view.KeyEvent r10) {
        /*
            r9 = this;
            boolean r0 = r9.a0
            if (r0 != 0) goto L9
            boolean r10 = super.dispatchKeyEvent(r10)
            return r10
        L9:
            fe r0 = r9.W
            r0.getClass()
            int r1 = r10.getAction()
            r2 = 1
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = 0
            if (r1 == r2) goto L84
            int r1 = r10.getKeyCode()
            r5 = 61
            r6 = 0
            if (r1 == r5) goto L6e
            r5 = 66
            if (r1 == r5) goto L57
            switch(r1) {
                case 19: goto L29;
                case 20: goto L29;
                case 21: goto L29;
                case 22: goto L29;
                case 23: goto L57;
                default: goto L28;
            }
        L28:
            goto L84
        L29:
            boolean r7 = r10.hasNoModifiers()
            if (r7 == 0) goto L84
            r7 = 19
            if (r1 == r7) goto L41
            r7 = 21
            if (r1 == r7) goto L3e
            r7 = 22
            if (r1 == r7) goto L43
            r5 = 130(0x82, float:1.82E-43)
            goto L43
        L3e:
            r5 = 17
            goto L43
        L41:
            r5 = 33
        L43:
            int r1 = r10.getRepeatCount()
            int r1 = r1 + r2
            r7 = r4
        L49:
            if (r4 >= r1) goto L55
            boolean r8 = r0.q(r5, r6)
            if (r8 == 0) goto L55
            int r4 = r4 + 1
            r7 = r2
            goto L49
        L55:
            r4 = r7
            goto L84
        L57:
            boolean r1 = r10.hasNoModifiers()
            if (r1 == 0) goto L84
            int r1 = r10.getRepeatCount()
            if (r1 != 0) goto L84
            int r1 = r0.l
            if (r1 == r3) goto L6c
            r4 = 16
            r0.s(r1, r4, r6)
        L6c:
            r4 = r2
            goto L84
        L6e:
            boolean r1 = r10.hasNoModifiers()
            if (r1 == 0) goto L7a
            r1 = 2
            boolean r4 = r0.q(r1, r6)
            goto L84
        L7a:
            boolean r1 = r10.hasModifiers(r2)
            if (r1 == 0) goto L84
            boolean r4 = r0.q(r2, r6)
        L84:
            if (r4 == 0) goto L8b
            int r0 = r0.l
            if (r0 == r3) goto L8b
            return r2
        L8b:
            boolean r10 = super.dispatchKeyEvent(r10)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.chip.Chip.dispatchKeyEvent(android.view.KeyEvent):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [boolean, int] */
    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        int i;
        super.drawableStateChanged();
        he heVar = this.I;
        boolean zE = false;
        if (heVar != null && he.C(heVar.q0)) {
            he heVar2 = this.I;
            ?? IsEnabled = isEnabled();
            int i2 = IsEnabled;
            if (this.R) {
                i2 = IsEnabled + 1;
            }
            int i3 = i2;
            if (this.Q) {
                i3 = i2 + 1;
            }
            int i4 = i3;
            if (this.P) {
                i4 = i3 + 1;
            }
            int i5 = i4;
            if (isChecked()) {
                i5 = i4 + 1;
            }
            int[] iArr = new int[i5];
            if (isEnabled()) {
                iArr[0] = 16842910;
                i = 1;
            } else {
                i = 0;
            }
            if (this.R) {
                iArr[i] = 16842908;
                i++;
            }
            if (this.Q) {
                iArr[i] = 16843623;
                i++;
            }
            if (this.P) {
                iArr[i] = 16842919;
                i++;
            }
            if (isChecked()) {
                iArr[i] = 16842913;
            }
            if (!Arrays.equals(heVar2.d1, iArr)) {
                heVar2.d1 = iArr;
                if (heVar2.b0()) {
                    zE = heVar2.E(heVar2.getState(), iArr);
                }
            }
        }
        if (zE) {
            invalidate();
        }
    }

    public final void e() {
        he heVar;
        if (!d() || (heVar = this.I) == null || !heVar.p0 || this.L == null) {
            f51.o(this, null);
            this.a0 = false;
        } else {
            f51.o(this, this.W);
            this.a0 = true;
        }
    }

    public final void f() {
        this.K = new RippleDrawable(gp0.c(this.I.i0), getBackgroundDrawable(), null);
        this.I.getClass();
        RippleDrawable rippleDrawable = this.K;
        WeakHashMap weakHashMap = f51.a;
        setBackground(rippleDrawable);
        g();
    }

    public final void g() {
        he heVar;
        if (TextUtils.isEmpty(getText()) || (heVar = this.I) == null) {
            return;
        }
        int iZ = (int) (heVar.z() + heVar.I0 + heVar.F0);
        he heVar2 = this.I;
        int iY = (int) (heVar2.y() + heVar2.B0 + heVar2.E0);
        if (this.J != null) {
            Rect rect = new Rect();
            this.J.getPadding(rect);
            iY += rect.left;
            iZ += rect.right;
        }
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        WeakHashMap weakHashMap = f51.a;
        setPaddingRelative(iY, paddingTop, iZ, paddingBottom);
    }

    @Override // android.widget.CheckBox, android.widget.CompoundButton, android.widget.Button, android.widget.TextView, android.view.View
    public CharSequence getAccessibilityClassName() {
        if (!TextUtils.isEmpty(this.V)) {
            return this.V;
        }
        he heVar = this.I;
        if (!(heVar != null && heVar.v0)) {
            return isClickable() ? "android.widget.Button" : "android.view.View";
        }
        ViewParent parent = getParent();
        return ((parent instanceof ChipGroup) && ((ChipGroup) parent).L.d) ? "android.widget.RadioButton" : "android.widget.Button";
    }

    public Drawable getBackgroundDrawable() {
        InsetDrawable insetDrawable = this.J;
        return insetDrawable == null ? this.I : insetDrawable;
    }

    public Drawable getCheckedIcon() {
        he heVar = this.I;
        if (heVar != null) {
            return heVar.x0;
        }
        return null;
    }

    public ColorStateList getCheckedIconTint() {
        he heVar = this.I;
        if (heVar != null) {
            return heVar.y0;
        }
        return null;
    }

    public ColorStateList getChipBackgroundColor() {
        he heVar = this.I;
        if (heVar != null) {
            return heVar.d0;
        }
        return null;
    }

    public float getChipCornerRadius() {
        he heVar = this.I;
        if (heVar != null) {
            return Math.max(0.0f, heVar.A());
        }
        return 0.0f;
    }

    public Drawable getChipDrawable() {
        return this.I;
    }

    public float getChipEndPadding() {
        he heVar = this.I;
        if (heVar != null) {
            return heVar.I0;
        }
        return 0.0f;
    }

    public Drawable getChipIcon() {
        Drawable drawable;
        he heVar = this.I;
        if (heVar == null || (drawable = heVar.l0) == null) {
            return null;
        }
        return z91.v0(drawable);
    }

    public float getChipIconSize() {
        he heVar = this.I;
        if (heVar != null) {
            return heVar.n0;
        }
        return 0.0f;
    }

    public ColorStateList getChipIconTint() {
        he heVar = this.I;
        if (heVar != null) {
            return heVar.m0;
        }
        return null;
    }

    public float getChipMinHeight() {
        he heVar = this.I;
        if (heVar != null) {
            return heVar.e0;
        }
        return 0.0f;
    }

    public float getChipStartPadding() {
        he heVar = this.I;
        if (heVar != null) {
            return heVar.B0;
        }
        return 0.0f;
    }

    public ColorStateList getChipStrokeColor() {
        he heVar = this.I;
        if (heVar != null) {
            return heVar.g0;
        }
        return null;
    }

    public float getChipStrokeWidth() {
        he heVar = this.I;
        if (heVar != null) {
            return heVar.h0;
        }
        return 0.0f;
    }

    @Deprecated
    public CharSequence getChipText() {
        return getText();
    }

    public Drawable getCloseIcon() {
        Drawable drawable;
        he heVar = this.I;
        if (heVar == null || (drawable = heVar.q0) == null) {
            return null;
        }
        return z91.v0(drawable);
    }

    public CharSequence getCloseIconContentDescription() {
        he heVar = this.I;
        if (heVar != null) {
            return heVar.u0;
        }
        return null;
    }

    public float getCloseIconEndPadding() {
        he heVar = this.I;
        if (heVar != null) {
            return heVar.H0;
        }
        return 0.0f;
    }

    public float getCloseIconSize() {
        he heVar = this.I;
        if (heVar != null) {
            return heVar.t0;
        }
        return 0.0f;
    }

    public float getCloseIconStartPadding() {
        he heVar = this.I;
        if (heVar != null) {
            return heVar.G0;
        }
        return 0.0f;
    }

    public ColorStateList getCloseIconTint() {
        he heVar = this.I;
        if (heVar != null) {
            return heVar.s0;
        }
        return null;
    }

    @Override // android.widget.TextView
    public TextUtils.TruncateAt getEllipsize() {
        he heVar = this.I;
        if (heVar != null) {
            return heVar.g1;
        }
        return null;
    }

    @Override // android.widget.TextView, android.view.View
    public final void getFocusedRect(Rect rect) {
        if (this.a0) {
            fe feVar = this.W;
            if (feVar.l == 1 || feVar.k == 1) {
                rect.set(getCloseIconTouchBoundsInt());
                return;
            }
        }
        super.getFocusedRect(rect);
    }

    public rf0 getHideMotionSpec() {
        he heVar = this.I;
        if (heVar != null) {
            return heVar.A0;
        }
        return null;
    }

    public float getIconEndPadding() {
        he heVar = this.I;
        if (heVar != null) {
            return heVar.D0;
        }
        return 0.0f;
    }

    public float getIconStartPadding() {
        he heVar = this.I;
        if (heVar != null) {
            return heVar.C0;
        }
        return 0.0f;
    }

    public ColorStateList getRippleColor() {
        he heVar = this.I;
        if (heVar != null) {
            return heVar.i0;
        }
        return null;
    }

    public zu0 getShapeAppearanceModel() {
        return this.I.E.a;
    }

    public rf0 getShowMotionSpec() {
        he heVar = this.I;
        if (heVar != null) {
            return heVar.z0;
        }
        return null;
    }

    public float getTextEndPadding() {
        he heVar = this.I;
        if (heVar != null) {
            return heVar.F0;
        }
        return 0.0f;
    }

    public float getTextStartPadding() {
        he heVar = this.I;
        if (heVar != null) {
            return heVar.E0;
        }
        return 0.0f;
    }

    public final void h() {
        TextPaint paint = getPaint();
        he heVar = this.I;
        if (heVar != null) {
            paint.drawableState = heVar.getState();
        }
        sz0 textAppearance = getTextAppearance();
        if (textAppearance != null) {
            textAppearance.e(getContext(), paint, this.d0);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        ma0.o0(this, this.I);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final int[] onCreateDrawableState(int i) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + 2);
        if (isChecked()) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, g0);
        }
        he heVar = this.I;
        if (heVar != null && heVar.v0) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, h0);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // android.widget.TextView, android.view.View
    public final void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
        if (this.a0) {
            fe feVar = this.W;
            int i2 = feVar.l;
            if (i2 != Integer.MIN_VALUE) {
                feVar.j(i2);
            }
            if (z) {
                feVar.q(i, rect);
            }
        }
    }

    @Override // android.view.View
    public final boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 7) {
            setCloseIconHovered(getCloseIconTouchBounds().contains(motionEvent.getX(), motionEvent.getY()));
        } else if (actionMasked == 10) {
            setCloseIconHovered(false);
        }
        return super.onHoverEvent(motionEvent);
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        int i;
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(getAccessibilityClassName());
        he heVar = this.I;
        int i2 = 0;
        accessibilityNodeInfo.setCheckable(heVar != null && heVar.v0);
        accessibilityNodeInfo.setClickable(isClickable());
        if (getParent() instanceof ChipGroup) {
            ChipGroup chipGroup = (ChipGroup) getParent();
            if (chipGroup.G) {
                int i3 = 0;
                while (true) {
                    if (i2 >= chipGroup.getChildCount()) {
                        i3 = -1;
                        break;
                    }
                    View childAt = chipGroup.getChildAt(i2);
                    if ((childAt instanceof Chip) && chipGroup.getChildAt(i2).getVisibility() == 0) {
                        if (((Chip) childAt) == this) {
                            break;
                        } else {
                            i3++;
                        }
                    }
                    i2++;
                }
                i = i3;
            } else {
                i = -1;
            }
            Object tag = getTag(R$id.row_index_key);
            accessibilityNodeInfo.setCollectionItemInfo(i2.a(tag instanceof Integer ? ((Integer) tag).intValue() : -1, 1, i, 1, false, isChecked()).a);
        }
    }

    @Override // android.widget.Button, android.widget.TextView, android.view.View
    public final PointerIcon onResolvePointerIcon(MotionEvent motionEvent, int i) {
        return (getCloseIconTouchBounds().contains(motionEvent.getX(), motionEvent.getY()) && isEnabled()) ? PointerIcon.getSystemIcon(getContext(), CloseFrame.PROTOCOL_ERROR) : super.onResolvePointerIcon(motionEvent, i);
    }

    @Override // android.widget.TextView, android.view.View
    public final void onRtlPropertiesChanged(int i) {
        super.onRtlPropertiesChanged(i);
        if (this.T != i) {
            this.T = i;
            g();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
    
        if (r0 != 3) goto L28;
     */
    @Override // android.widget.TextView, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            int r0 = r6.getActionMasked()
            android.graphics.RectF r1 = r5.getCloseIconTouchBounds()
            float r2 = r6.getX()
            float r3 = r6.getY()
            boolean r1 = r1.contains(r2, r3)
            r2 = 0
            r3 = 1
            if (r0 == 0) goto L4a
            if (r0 == r3) goto L2c
            r4 = 2
            if (r0 == r4) goto L21
            r1 = 3
            if (r0 == r1) goto L45
            goto L50
        L21:
            boolean r0 = r5.P
            if (r0 == 0) goto L50
            if (r1 != 0) goto L2a
            r5.setCloseIconPressed(r2)
        L2a:
            r0 = r3
            goto L51
        L2c:
            boolean r0 = r5.P
            if (r0 == 0) goto L45
            r5.playSoundEffect(r2)
            android.view.View$OnClickListener r0 = r5.L
            if (r0 == 0) goto L3a
            r0.onClick(r5)
        L3a:
            boolean r0 = r5.a0
            if (r0 == 0) goto L43
            fe r0 = r5.W
            r0.x(r3, r3)
        L43:
            r0 = r3
            goto L46
        L45:
            r0 = r2
        L46:
            r5.setCloseIconPressed(r2)
            goto L51
        L4a:
            if (r1 == 0) goto L50
            r5.setCloseIconPressed(r3)
            goto L2a
        L50:
            r0 = r2
        L51:
            if (r0 != 0) goto L5b
            boolean r6 = super.onTouchEvent(r6)
            if (r6 == 0) goto L5a
            goto L5b
        L5a:
            return r2
        L5b:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.chip.Chip.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void setAccessibilityClassName(CharSequence charSequence) {
        this.V = charSequence;
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        if (drawable == getBackgroundDrawable() || drawable == this.K) {
            super.setBackground(drawable);
        } else {
            Log.w("Chip", "Do not set the background; Chip manages its own background drawable.");
        }
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        Log.w("Chip", "Do not set the background color; Chip manages its own background drawable.");
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (drawable == getBackgroundDrawable() || drawable == this.K) {
            super.setBackgroundDrawable(drawable);
        } else {
            Log.w("Chip", "Do not set the background drawable; Chip manages its own background drawable.");
        }
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.view.View
    public void setBackgroundResource(int i) {
        Log.w("Chip", "Do not set the background resource; Chip manages its own background drawable.");
    }

    @Override // android.view.View
    public void setBackgroundTintList(ColorStateList colorStateList) {
        Log.w("Chip", "Do not set the background tint list; Chip manages its own background drawable.");
    }

    @Override // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode mode) {
        Log.w("Chip", "Do not set the background tint mode; Chip manages its own background drawable.");
    }

    public void setCheckable(boolean z) {
        he heVar = this.I;
        if (heVar != null) {
            heVar.F(z);
        }
    }

    public void setCheckableResource(int i) {
        he heVar = this.I;
        if (heVar != null) {
            heVar.F(heVar.J0.getResources().getBoolean(i));
        }
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z) {
        he heVar = this.I;
        if (heVar == null) {
            this.O = z;
        } else if (heVar.v0) {
            super.setChecked(z);
        }
    }

    public void setCheckedIcon(Drawable drawable) {
        he heVar = this.I;
        if (heVar != null) {
            heVar.G(drawable);
        }
    }

    @Deprecated
    public void setCheckedIconEnabled(boolean z) {
        setCheckedIconVisible(z);
    }

    @Deprecated
    public void setCheckedIconEnabledResource(int i) {
        setCheckedIconVisible(i);
    }

    public void setCheckedIconResource(int i) {
        he heVar = this.I;
        if (heVar != null) {
            heVar.G(qm.z(heVar.J0, i));
        }
    }

    public void setCheckedIconTint(ColorStateList colorStateList) {
        he heVar = this.I;
        if (heVar != null) {
            heVar.H(colorStateList);
        }
    }

    public void setCheckedIconTintResource(int i) {
        he heVar = this.I;
        if (heVar != null) {
            heVar.H(t40.Y(heVar.J0, i));
        }
    }

    public void setCheckedIconVisible(int i) {
        he heVar = this.I;
        if (heVar != null) {
            heVar.I(heVar.J0.getResources().getBoolean(i));
        }
    }

    public void setChipBackgroundColor(ColorStateList colorStateList) {
        he heVar = this.I;
        if (heVar == null || heVar.d0 == colorStateList) {
            return;
        }
        heVar.d0 = colorStateList;
        heVar.onStateChange(heVar.getState());
    }

    public void setChipBackgroundColorResource(int i) {
        ColorStateList colorStateListY;
        he heVar = this.I;
        if (heVar == null || heVar.d0 == (colorStateListY = t40.Y(heVar.J0, i))) {
            return;
        }
        heVar.d0 = colorStateListY;
        heVar.onStateChange(heVar.getState());
    }

    @Deprecated
    public void setChipCornerRadius(float f) {
        he heVar = this.I;
        if (heVar != null) {
            heVar.J(f);
        }
    }

    @Deprecated
    public void setChipCornerRadiusResource(int i) {
        he heVar = this.I;
        if (heVar != null) {
            heVar.J(heVar.J0.getResources().getDimension(i));
        }
    }

    public void setChipDrawable(he heVar) {
        he heVar2 = this.I;
        if (heVar2 != heVar) {
            if (heVar2 != null) {
                heVar2.f1 = new WeakReference(null);
            }
            this.I = heVar;
            heVar.h1 = false;
            heVar.f1 = new WeakReference(this);
            c(this.U);
        }
    }

    public void setChipEndPadding(float f) {
        he heVar = this.I;
        if (heVar == null || heVar.I0 == f) {
            return;
        }
        heVar.I0 = f;
        heVar.invalidateSelf();
        heVar.D();
    }

    public void setChipEndPaddingResource(int i) {
        he heVar = this.I;
        if (heVar != null) {
            float dimension = heVar.J0.getResources().getDimension(i);
            if (heVar.I0 != dimension) {
                heVar.I0 = dimension;
                heVar.invalidateSelf();
                heVar.D();
            }
        }
    }

    public void setChipIcon(Drawable drawable) {
        he heVar = this.I;
        if (heVar != null) {
            heVar.K(drawable);
        }
    }

    @Deprecated
    public void setChipIconEnabled(boolean z) {
        setChipIconVisible(z);
    }

    @Deprecated
    public void setChipIconEnabledResource(int i) {
        setChipIconVisible(i);
    }

    public void setChipIconResource(int i) {
        he heVar = this.I;
        if (heVar != null) {
            heVar.K(qm.z(heVar.J0, i));
        }
    }

    public void setChipIconSize(float f) {
        he heVar = this.I;
        if (heVar != null) {
            heVar.L(f);
        }
    }

    public void setChipIconSizeResource(int i) {
        he heVar = this.I;
        if (heVar != null) {
            heVar.L(heVar.J0.getResources().getDimension(i));
        }
    }

    public void setChipIconTint(ColorStateList colorStateList) {
        he heVar = this.I;
        if (heVar != null) {
            heVar.M(colorStateList);
        }
    }

    public void setChipIconTintResource(int i) {
        he heVar = this.I;
        if (heVar != null) {
            heVar.M(t40.Y(heVar.J0, i));
        }
    }

    public void setChipIconVisible(int i) {
        he heVar = this.I;
        if (heVar != null) {
            heVar.N(heVar.J0.getResources().getBoolean(i));
        }
    }

    public void setChipMinHeight(float f) {
        he heVar = this.I;
        if (heVar == null || heVar.e0 == f) {
            return;
        }
        heVar.e0 = f;
        heVar.invalidateSelf();
        heVar.D();
    }

    public void setChipMinHeightResource(int i) {
        he heVar = this.I;
        if (heVar != null) {
            float dimension = heVar.J0.getResources().getDimension(i);
            if (heVar.e0 != dimension) {
                heVar.e0 = dimension;
                heVar.invalidateSelf();
                heVar.D();
            }
        }
    }

    public void setChipStartPadding(float f) {
        he heVar = this.I;
        if (heVar == null || heVar.B0 == f) {
            return;
        }
        heVar.B0 = f;
        heVar.invalidateSelf();
        heVar.D();
    }

    public void setChipStartPaddingResource(int i) {
        he heVar = this.I;
        if (heVar != null) {
            float dimension = heVar.J0.getResources().getDimension(i);
            if (heVar.B0 != dimension) {
                heVar.B0 = dimension;
                heVar.invalidateSelf();
                heVar.D();
            }
        }
    }

    public void setChipStrokeColor(ColorStateList colorStateList) {
        he heVar = this.I;
        if (heVar != null) {
            heVar.O(colorStateList);
        }
    }

    public void setChipStrokeColorResource(int i) {
        he heVar = this.I;
        if (heVar != null) {
            heVar.O(t40.Y(heVar.J0, i));
        }
    }

    public void setChipStrokeWidth(float f) {
        he heVar = this.I;
        if (heVar != null) {
            heVar.P(f);
        }
    }

    public void setChipStrokeWidthResource(int i) {
        he heVar = this.I;
        if (heVar != null) {
            heVar.P(heVar.J0.getResources().getDimension(i));
        }
    }

    @Deprecated
    public void setChipText(CharSequence charSequence) {
        setText(charSequence);
    }

    @Deprecated
    public void setChipTextResource(int i) {
        setText(getResources().getString(i));
    }

    public void setCloseIcon(Drawable drawable) {
        he heVar = this.I;
        if (heVar != null) {
            heVar.Q(drawable);
        }
        e();
    }

    public void setCloseIconContentDescription(CharSequence charSequence) {
        he heVar = this.I;
        if (heVar == null || heVar.u0 == charSequence) {
            return;
        }
        String str = na.b;
        na naVar = TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 1 ? na.e : na.d;
        naVar.getClass();
        cb cbVar = tz0.a;
        heVar.u0 = naVar.c(charSequence);
        heVar.invalidateSelf();
    }

    @Deprecated
    public void setCloseIconEnabled(boolean z) {
        setCloseIconVisible(z);
    }

    @Deprecated
    public void setCloseIconEnabledResource(int i) {
        setCloseIconVisible(i);
    }

    public void setCloseIconEndPadding(float f) {
        he heVar = this.I;
        if (heVar != null) {
            heVar.R(f);
        }
    }

    public void setCloseIconEndPaddingResource(int i) {
        he heVar = this.I;
        if (heVar != null) {
            heVar.R(heVar.J0.getResources().getDimension(i));
        }
    }

    public void setCloseIconResource(int i) {
        he heVar = this.I;
        if (heVar != null) {
            heVar.Q(qm.z(heVar.J0, i));
        }
        e();
    }

    public void setCloseIconSize(float f) {
        he heVar = this.I;
        if (heVar != null) {
            heVar.S(f);
        }
    }

    public void setCloseIconSizeResource(int i) {
        he heVar = this.I;
        if (heVar != null) {
            heVar.S(heVar.J0.getResources().getDimension(i));
        }
    }

    public void setCloseIconStartPadding(float f) {
        he heVar = this.I;
        if (heVar != null) {
            heVar.T(f);
        }
    }

    public void setCloseIconStartPaddingResource(int i) {
        he heVar = this.I;
        if (heVar != null) {
            heVar.T(heVar.J0.getResources().getDimension(i));
        }
    }

    public void setCloseIconTint(ColorStateList colorStateList) {
        he heVar = this.I;
        if (heVar != null) {
            heVar.U(colorStateList);
        }
    }

    public void setCloseIconTintResource(int i) {
        he heVar = this.I;
        if (heVar != null) {
            heVar.U(t40.Y(heVar.J0, i));
        }
    }

    public void setCloseIconVisible(int i) {
        setCloseIconVisible(getResources().getBoolean(i));
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.widget.TextView
    public final void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (drawable3 != null) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.widget.TextView
    public final void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (drawable3 != null) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelativeWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        if (i != 0) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (i3 != 0) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(i, i2, i3, i4);
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        if (i != 0) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (i3 != 0) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawablesWithIntrinsicBounds(i, i2, i3, i4);
    }

    @Override // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        he heVar = this.I;
        if (heVar != null) {
            heVar.l(f);
        }
    }

    @Override // android.widget.TextView
    public void setEllipsize(TextUtils.TruncateAt truncateAt) {
        if (this.I == null) {
            return;
        }
        if (truncateAt == TextUtils.TruncateAt.MARQUEE) {
            throw new UnsupportedOperationException("Text within a chip are not allowed to scroll.");
        }
        super.setEllipsize(truncateAt);
        he heVar = this.I;
        if (heVar != null) {
            heVar.g1 = truncateAt;
        }
    }

    public void setEnsureMinTouchTargetSize(boolean z) {
        this.S = z;
        c(this.U);
    }

    @Override // android.widget.TextView
    public void setGravity(int i) {
        if (i != 8388627) {
            Log.w("Chip", "Chip text must be vertically center and start aligned");
        } else {
            super.setGravity(i);
        }
    }

    public void setHideMotionSpec(rf0 rf0Var) {
        he heVar = this.I;
        if (heVar != null) {
            heVar.A0 = rf0Var;
        }
    }

    public void setHideMotionSpecResource(int i) {
        he heVar = this.I;
        if (heVar != null) {
            heVar.A0 = rf0.b(heVar.J0, i);
        }
    }

    public void setIconEndPadding(float f) {
        he heVar = this.I;
        if (heVar != null) {
            heVar.W(f);
        }
    }

    public void setIconEndPaddingResource(int i) {
        he heVar = this.I;
        if (heVar != null) {
            heVar.W(heVar.J0.getResources().getDimension(i));
        }
    }

    public void setIconStartPadding(float f) {
        he heVar = this.I;
        if (heVar != null) {
            heVar.X(f);
        }
    }

    public void setIconStartPaddingResource(int i) {
        he heVar = this.I;
        if (heVar != null) {
            heVar.X(heVar.J0.getResources().getDimension(i));
        }
    }

    @Override // defpackage.ed0
    public void setInternalOnCheckedChangeListener(dd0 dd0Var) {
        this.N = dd0Var;
    }

    @Override // android.view.View
    public void setLayoutDirection(int i) {
        if (this.I == null) {
            return;
        }
        super.setLayoutDirection(i);
    }

    @Override // android.widget.TextView
    public void setLines(int i) {
        if (i > 1) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setLines(i);
    }

    @Override // android.widget.TextView
    public void setMaxLines(int i) {
        if (i > 1) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setMaxLines(i);
    }

    @Override // android.widget.TextView
    public void setMaxWidth(int i) {
        super.setMaxWidth(i);
        he heVar = this.I;
        if (heVar != null) {
            heVar.i1 = i;
        }
    }

    @Override // android.widget.TextView
    public void setMinLines(int i) {
        if (i > 1) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setMinLines(i);
    }

    @Override // android.widget.CompoundButton
    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.M = onCheckedChangeListener;
    }

    public void setOnCloseIconClickListener(View.OnClickListener onClickListener) {
        this.L = onClickListener;
        e();
    }

    public void setRippleColor(ColorStateList colorStateList) {
        he heVar = this.I;
        if (heVar != null) {
            heVar.Y(colorStateList);
        }
        this.I.getClass();
        f();
    }

    public void setRippleColorResource(int i) {
        he heVar = this.I;
        if (heVar != null) {
            heVar.Y(t40.Y(heVar.J0, i));
            this.I.getClass();
            f();
        }
    }

    @Override // defpackage.kv0
    public void setShapeAppearanceModel(zu0 zu0Var) {
        this.I.setShapeAppearanceModel(zu0Var);
    }

    public void setShowMotionSpec(rf0 rf0Var) {
        he heVar = this.I;
        if (heVar != null) {
            heVar.z0 = rf0Var;
        }
    }

    public void setShowMotionSpecResource(int i) {
        he heVar = this.I;
        if (heVar != null) {
            heVar.z0 = rf0.b(heVar.J0, i);
        }
    }

    @Override // android.widget.TextView
    public void setSingleLine(boolean z) {
        if (!z) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setSingleLine(z);
    }

    @Override // android.widget.TextView
    public final void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        he heVar = this.I;
        if (heVar == null) {
            return;
        }
        if (charSequence == null) {
            charSequence = "";
        }
        super.setText(heVar.h1 ? null : charSequence, bufferType);
        he heVar2 = this.I;
        if (heVar2 == null || TextUtils.equals(heVar2.j0, charSequence)) {
            return;
        }
        heVar2.j0 = charSequence;
        heVar2.P0.e = true;
        heVar2.invalidateSelf();
        heVar2.D();
    }

    public void setTextAppearance(sz0 sz0Var) {
        he heVar = this.I;
        if (heVar != null) {
            heVar.P0.c(sz0Var, heVar.J0);
        }
        h();
    }

    public void setTextAppearanceResource(int i) {
        setTextAppearance(getContext(), i);
    }

    public void setTextEndPadding(float f) {
        he heVar = this.I;
        if (heVar == null || heVar.F0 == f) {
            return;
        }
        heVar.F0 = f;
        heVar.invalidateSelf();
        heVar.D();
    }

    public void setTextEndPaddingResource(int i) {
        he heVar = this.I;
        if (heVar != null) {
            float dimension = heVar.J0.getResources().getDimension(i);
            if (heVar.F0 != dimension) {
                heVar.F0 = dimension;
                heVar.invalidateSelf();
                heVar.D();
            }
        }
    }

    @Override // android.widget.TextView
    public final void setTextSize(int i, float f) {
        super.setTextSize(i, f);
        he heVar = this.I;
        if (heVar != null) {
            float fApplyDimension = TypedValue.applyDimension(i, f, getResources().getDisplayMetrics());
            vz0 vz0Var = heVar.P0;
            sz0 sz0Var = vz0Var.g;
            if (sz0Var != null) {
                sz0Var.k = fApplyDimension;
                vz0Var.a.setTextSize(fApplyDimension);
                heVar.a();
            }
        }
        h();
    }

    public void setTextStartPadding(float f) {
        he heVar = this.I;
        if (heVar == null || heVar.E0 == f) {
            return;
        }
        heVar.E0 = f;
        heVar.invalidateSelf();
        heVar.D();
    }

    public void setTextStartPaddingResource(int i) {
        he heVar = this.I;
        if (heVar != null) {
            float dimension = heVar.J0.getResources().getDimension(i);
            if (heVar.E0 != dimension) {
                heVar.E0 = dimension;
                heVar.invalidateSelf();
                heVar.D();
            }
        }
    }

    public Chip(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.chipStyle);
    }

    public void setCloseIconVisible(boolean z) {
        he heVar = this.I;
        if (heVar != null) {
            heVar.V(z);
        }
        e();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Chip(Context context, AttributeSet attributeSet, int i) {
        int resourceId;
        int i2 = e0;
        super(pd0.a(context, attributeSet, i, i2), attributeSet, i);
        this.b0 = new Rect();
        this.c0 = new RectF();
        this.d0 = new de(0, this);
        Context context2 = getContext();
        if (attributeSet != null) {
            if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "background") != null) {
                Log.w("Chip", "Do not set the background; Chip manages its own background drawable.");
            }
            if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableLeft") == null) {
                if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableStart") == null) {
                    if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableEnd") == null) {
                        if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableRight") == null) {
                            if (attributeSet.getAttributeBooleanValue("http://schemas.android.com/apk/res/android", "singleLine", true) && attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "lines", 1) == 1 && attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "minLines", 1) == 1 && attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "maxLines", 1) == 1) {
                                if (attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "gravity", 8388627) != 8388627) {
                                    Log.w("Chip", "Chip text must be vertically center and start aligned");
                                }
                            } else {
                                throw new UnsupportedOperationException("Chip does not support multi-line text");
                            }
                        } else {
                            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
                        }
                    } else {
                        throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
                    }
                } else {
                    throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
                }
            } else {
                throw new UnsupportedOperationException("Please set left drawable using R.attr#chipIcon.");
            }
        }
        he heVar = new he(context2, attributeSet, i);
        TypedArray typedArrayD = i01.d(heVar.J0, attributeSet, R$styleable.Chip, i, i2, new int[0]);
        heVar.j1 = typedArrayD.hasValue(R$styleable.Chip_shapeAppearance);
        int i3 = R$styleable.Chip_chipSurfaceColor;
        Context context3 = heVar.J0;
        ColorStateList colorStateListP = la0.p(context3, typedArrayD, i3);
        if (heVar.c0 != colorStateListP) {
            heVar.c0 = colorStateListP;
            heVar.onStateChange(heVar.getState());
        }
        ColorStateList colorStateListP2 = la0.p(context3, typedArrayD, R$styleable.Chip_chipBackgroundColor);
        if (heVar.d0 != colorStateListP2) {
            heVar.d0 = colorStateListP2;
            heVar.onStateChange(heVar.getState());
        }
        float dimension = typedArrayD.getDimension(R$styleable.Chip_chipMinHeight, 0.0f);
        if (heVar.e0 != dimension) {
            heVar.e0 = dimension;
            heVar.invalidateSelf();
            heVar.D();
        }
        if (typedArrayD.hasValue(R$styleable.Chip_chipCornerRadius)) {
            heVar.J(typedArrayD.getDimension(R$styleable.Chip_chipCornerRadius, 0.0f));
        }
        heVar.O(la0.p(context3, typedArrayD, R$styleable.Chip_chipStrokeColor));
        heVar.P(typedArrayD.getDimension(R$styleable.Chip_chipStrokeWidth, 0.0f));
        heVar.Y(la0.p(context3, typedArrayD, R$styleable.Chip_rippleColor));
        String text = typedArrayD.getText(R$styleable.Chip_android_text);
        text = text == null ? "" : text;
        boolean zEquals = TextUtils.equals(heVar.j0, text);
        vz0 vz0Var = heVar.P0;
        if (!zEquals) {
            heVar.j0 = text;
            vz0Var.e = true;
            heVar.invalidateSelf();
            heVar.D();
        }
        int i4 = R$styleable.Chip_android_textAppearance;
        sz0 sz0Var = (!typedArrayD.hasValue(i4) || (resourceId = typedArrayD.getResourceId(i4, 0)) == 0) ? null : new sz0(context3, resourceId);
        sz0Var.k = typedArrayD.getDimension(R$styleable.Chip_android_textSize, sz0Var.k);
        vz0Var.c(sz0Var, context3);
        int i5 = typedArrayD.getInt(R$styleable.Chip_android_ellipsize, 0);
        if (i5 == 1) {
            heVar.g1 = TextUtils.TruncateAt.START;
        } else if (i5 == 2) {
            heVar.g1 = TextUtils.TruncateAt.MIDDLE;
        } else if (i5 == 3) {
            heVar.g1 = TextUtils.TruncateAt.END;
        }
        heVar.N(typedArrayD.getBoolean(R$styleable.Chip_chipIconVisible, false));
        if (attributeSet != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "chipIconEnabled") != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "chipIconVisible") == null) {
            heVar.N(typedArrayD.getBoolean(R$styleable.Chip_chipIconEnabled, false));
        }
        heVar.K(la0.s(context3, typedArrayD, R$styleable.Chip_chipIcon));
        if (typedArrayD.hasValue(R$styleable.Chip_chipIconTint)) {
            heVar.M(la0.p(context3, typedArrayD, R$styleable.Chip_chipIconTint));
        }
        heVar.L(typedArrayD.getDimension(R$styleable.Chip_chipIconSize, -1.0f));
        heVar.V(typedArrayD.getBoolean(R$styleable.Chip_closeIconVisible, false));
        if (attributeSet != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "closeIconEnabled") != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "closeIconVisible") == null) {
            heVar.V(typedArrayD.getBoolean(R$styleable.Chip_closeIconEnabled, false));
        }
        heVar.Q(la0.s(context3, typedArrayD, R$styleable.Chip_closeIcon));
        heVar.U(la0.p(context3, typedArrayD, R$styleable.Chip_closeIconTint));
        heVar.S(typedArrayD.getDimension(R$styleable.Chip_closeIconSize, 0.0f));
        heVar.F(typedArrayD.getBoolean(R$styleable.Chip_android_checkable, false));
        heVar.I(typedArrayD.getBoolean(R$styleable.Chip_checkedIconVisible, false));
        if (attributeSet != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "checkedIconEnabled") != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "checkedIconVisible") == null) {
            heVar.I(typedArrayD.getBoolean(R$styleable.Chip_checkedIconEnabled, false));
        }
        heVar.G(la0.s(context3, typedArrayD, R$styleable.Chip_checkedIcon));
        if (typedArrayD.hasValue(R$styleable.Chip_checkedIconTint)) {
            heVar.H(la0.p(context3, typedArrayD, R$styleable.Chip_checkedIconTint));
        }
        heVar.z0 = rf0.a(context3, typedArrayD, R$styleable.Chip_showMotionSpec);
        heVar.A0 = rf0.a(context3, typedArrayD, R$styleable.Chip_hideMotionSpec);
        float dimension2 = typedArrayD.getDimension(R$styleable.Chip_chipStartPadding, 0.0f);
        if (heVar.B0 != dimension2) {
            heVar.B0 = dimension2;
            heVar.invalidateSelf();
            heVar.D();
        }
        heVar.X(typedArrayD.getDimension(R$styleable.Chip_iconStartPadding, 0.0f));
        heVar.W(typedArrayD.getDimension(R$styleable.Chip_iconEndPadding, 0.0f));
        float dimension3 = typedArrayD.getDimension(R$styleable.Chip_textStartPadding, 0.0f);
        if (heVar.E0 != dimension3) {
            heVar.E0 = dimension3;
            heVar.invalidateSelf();
            heVar.D();
        }
        float dimension4 = typedArrayD.getDimension(R$styleable.Chip_textEndPadding, 0.0f);
        if (heVar.F0 != dimension4) {
            heVar.F0 = dimension4;
            heVar.invalidateSelf();
            heVar.D();
        }
        heVar.T(typedArrayD.getDimension(R$styleable.Chip_closeIconStartPadding, 0.0f));
        heVar.R(typedArrayD.getDimension(R$styleable.Chip_closeIconEndPadding, 0.0f));
        float dimension5 = typedArrayD.getDimension(R$styleable.Chip_chipEndPadding, 0.0f);
        if (heVar.I0 != dimension5) {
            heVar.I0 = dimension5;
            heVar.invalidateSelf();
            heVar.D();
        }
        heVar.i1 = typedArrayD.getDimensionPixelSize(R$styleable.Chip_android_maxWidth, Integer.MAX_VALUE);
        typedArrayD.recycle();
        int[] iArr = R$styleable.Chip;
        i01.a(context2, attributeSet, i, i2);
        i01.b(context2, attributeSet, iArr, i, i2, new int[0]);
        TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, iArr, i, i2);
        this.S = typedArrayObtainStyledAttributes.getBoolean(R$styleable.Chip_ensureMinTouchTargetSize, false);
        this.U = (int) Math.ceil(typedArrayObtainStyledAttributes.getDimension(R$styleable.Chip_chipMinTouchTargetSize, (float) Math.ceil(fa0.C(getContext(), 48))));
        typedArrayObtainStyledAttributes.recycle();
        setChipDrawable(heVar);
        WeakHashMap weakHashMap = f51.a;
        heVar.l(t41.i(this));
        int[] iArr2 = R$styleable.Chip;
        i01.a(context2, attributeSet, i, i2);
        i01.b(context2, attributeSet, iArr2, i, i2, new int[0]);
        TypedArray typedArrayObtainStyledAttributes2 = context2.obtainStyledAttributes(attributeSet, iArr2, i, i2);
        boolean zHasValue = typedArrayObtainStyledAttributes2.hasValue(R$styleable.Chip_shapeAppearance);
        typedArrayObtainStyledAttributes2.recycle();
        this.W = new fe(this, this);
        e();
        if (!zHasValue) {
            setOutlineProvider(new ee(0, this));
        }
        setChecked(this.O);
        setText(heVar.j0);
        setEllipsize(heVar.g1);
        h();
        if (!this.I.h1) {
            setLines(1);
            setHorizontallyScrolling(true);
        }
        setGravity(8388627);
        g();
        if (this.S) {
            setMinHeight(this.U);
        }
        this.T = getLayoutDirection();
        super.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: ce
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Chip chip = this.a;
                dd0 dd0Var = chip.N;
                if (dd0Var != null) {
                    wd wdVar = (wd) ((z8) dd0Var).F;
                    if (!z ? wdVar.e(chip, wdVar.e) : wdVar.a(chip)) {
                        wdVar.d();
                    }
                }
                CompoundButton.OnCheckedChangeListener onCheckedChangeListener = chip.M;
                if (onCheckedChangeListener != null) {
                    onCheckedChangeListener.onCheckedChanged(compoundButton, z);
                }
            }
        });
    }

    public void setCheckedIconVisible(boolean z) {
        he heVar = this.I;
        if (heVar != null) {
            heVar.I(z);
        }
    }

    public void setChipIconVisible(boolean z) {
        he heVar = this.I;
        if (heVar != null) {
            heVar.N(z);
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (drawable3 == null) {
            super.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
            return;
        }
        throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set left drawable using R.attr#chipIcon.");
        }
        if (drawable3 == null) {
            super.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
            return;
        }
        throw new UnsupportedOperationException("Please set right drawable using R.attr#closeIcon.");
    }

    @Override // android.widget.TextView
    public final void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        he heVar = this.I;
        if (heVar != null) {
            Context context2 = heVar.J0;
            heVar.P0.c(new sz0(context2, i), context2);
        }
        h();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(int i) {
        super.setTextAppearance(i);
        he heVar = this.I;
        if (heVar != null) {
            Context context = heVar.J0;
            heVar.P0.c(new sz0(context, i), context);
        }
        h();
    }
}
