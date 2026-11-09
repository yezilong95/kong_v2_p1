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
import defpackage.e9;
import defpackage.en0;
import defpackage.fn0;
import defpackage.hn0;
import defpackage.ic;
import defpackage.il;
import defpackage.jc;
import defpackage.kc;
import defpackage.ki0;
import defpackage.lc;
import defpackage.lf0;
import defpackage.lr0;
import defpackage.mc;
import defpackage.n0;
import defpackage.n50;
import defpackage.nv0;
import defpackage.o50;
import defpackage.oy;
import defpackage.p8;
import defpackage.rp0;
import defpackage.un0;
import defpackage.vi0;
import defpackage.x70;
import defpackage.xi;
import defpackage.xr0;
import defpackage.y50;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Locale;
import java.util.WeakHashMap;
import org.java_websocket.extensions.ExtensionRequestData;
import org.java_websocket.framing.CloseFrame;

/* JADX INFO: loaded from: classes.dex */
public class Chip extends AppCompatCheckBox implements lc, vi0, o50 {
    public mc f;
    public InsetDrawable g;
    public RippleDrawable h;
    public View.OnClickListener i;
    public CompoundButton.OnCheckedChangeListener j;
    public n50 k;
    public boolean l;
    public boolean m;
    public boolean n;
    public boolean o;
    public boolean p;
    public int q;
    public int r;
    public CharSequence s;
    public final kc t;
    public boolean u;
    public final Rect v;
    public final RectF w;
    public final ic x;
    public static final int y = R$style.Widget_MaterialComponents_Chip_Action;
    public static final Rect z = new Rect();
    public static final int[] A = {R.attr.state_selected};
    public static final int[] B = {R.attr.state_checkable};

    public Chip(Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public RectF getCloseIconTouchBounds() {
        RectF rectF = this.w;
        rectF.setEmpty();
        if (d() && this.i != null) {
            mc mcVar = this.f;
            Rect bounds = mcVar.getBounds();
            rectF.setEmpty();
            if (mcVar.b0()) {
                float f = mcVar.e0 + mcVar.d0 + mcVar.P + mcVar.c0 + mcVar.b0;
                if (il.a(mcVar) == 0) {
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
        Rect rect = this.v;
        rect.set(i, i2, i3, i4);
        return rect;
    }

    private en0 getTextAppearance() {
        mc mcVar = this.f;
        if (mcVar != null) {
            return mcVar.l0.g;
        }
        return null;
    }

    private void setCloseIconHovered(boolean z2) {
        if (this.n != z2) {
            this.n = z2;
            refreshDrawableState();
        }
    }

    private void setCloseIconPressed(boolean z2) {
        if (this.m != z2) {
            this.m = z2;
            refreshDrawableState();
        }
    }

    public final void c(int i) {
        this.r = i;
        if (!this.p) {
            InsetDrawable insetDrawable = this.g;
            if (insetDrawable == null) {
                int[] iArr = lf0.a;
                f();
                return;
            } else {
                if (insetDrawable != null) {
                    this.g = null;
                    setMinWidth(0);
                    setMinHeight((int) getChipMinHeight());
                    int[] iArr2 = lf0.a;
                    f();
                    return;
                }
                return;
            }
        }
        int iMax = Math.max(0, i - ((int) this.f.A));
        int iMax2 = Math.max(0, i - this.f.getIntrinsicWidth());
        if (iMax2 <= 0 && iMax <= 0) {
            InsetDrawable insetDrawable2 = this.g;
            if (insetDrawable2 == null) {
                int[] iArr3 = lf0.a;
                f();
                return;
            } else {
                if (insetDrawable2 != null) {
                    this.g = null;
                    setMinWidth(0);
                    setMinHeight((int) getChipMinHeight());
                    int[] iArr4 = lf0.a;
                    f();
                    return;
                }
                return;
            }
        }
        int i2 = iMax2 > 0 ? iMax2 / 2 : 0;
        int i3 = iMax > 0 ? iMax / 2 : 0;
        if (this.g != null) {
            Rect rect = new Rect();
            this.g.getPadding(rect);
            if (rect.top == i3 && rect.bottom == i3 && rect.left == i2 && rect.right == i2) {
                int[] iArr5 = lf0.a;
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
        this.g = new InsetDrawable((Drawable) this.f, i2, i3, i2, i3);
        int[] iArr6 = lf0.a;
        f();
    }

    public final boolean d() {
        mc mcVar = this.f;
        if (mcVar == null) {
            return false;
        }
        Drawable drawable = mcVar.M;
        return (drawable != null ? nv0.Q(drawable) : null) != null;
    }

    @Override // android.view.View
    public final boolean dispatchHoverEvent(MotionEvent motionEvent) {
        return !this.u ? super.dispatchHoverEvent(motionEvent) : this.t.m(motionEvent) || super.dispatchHoverEvent(motionEvent);
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0057  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean dispatchKeyEvent(android.view.KeyEvent r10) {
        /*
            r9 = this;
            boolean r0 = r9.u
            if (r0 != 0) goto L9
            boolean r10 = super.dispatchKeyEvent(r10)
            return r10
        L9:
            kc r0 = r9.t
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
        mc mcVar = this.f;
        boolean zE = false;
        if (mcVar != null && mc.C(mcVar.M)) {
            mc mcVar2 = this.f;
            ?? IsEnabled = isEnabled();
            int i2 = IsEnabled;
            if (this.o) {
                i2 = IsEnabled + 1;
            }
            int i3 = i2;
            if (this.n) {
                i3 = i2 + 1;
            }
            int i4 = i3;
            if (this.m) {
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
            if (this.o) {
                iArr[i] = 16842908;
                i++;
            }
            if (this.n) {
                iArr[i] = 16843623;
                i++;
            }
            if (this.m) {
                iArr[i] = 16842919;
                i++;
            }
            if (isChecked()) {
                iArr[i] = 16842913;
            }
            if (!Arrays.equals(mcVar2.z0, iArr)) {
                mcVar2.z0 = iArr;
                if (mcVar2.b0()) {
                    zE = mcVar2.E(mcVar2.getState(), iArr);
                }
            }
        }
        if (zE) {
            invalidate();
        }
    }

    public final void e() {
        mc mcVar;
        if (!d() || (mcVar = this.f) == null || !mcVar.L || this.i == null) {
            xr0.p(this, null);
            this.u = false;
        } else {
            xr0.p(this, this.t);
            this.u = true;
        }
    }

    public final void f() {
        this.h = new RippleDrawable(lf0.c(this.f.E), getBackgroundDrawable(), null);
        this.f.getClass();
        RippleDrawable rippleDrawable = this.h;
        WeakHashMap weakHashMap = xr0.a;
        setBackground(rippleDrawable);
        g();
    }

    public final void g() {
        mc mcVar;
        if (TextUtils.isEmpty(getText()) || (mcVar = this.f) == null) {
            return;
        }
        int iZ = (int) (mcVar.z() + mcVar.e0 + mcVar.b0);
        mc mcVar2 = this.f;
        int iY = (int) (mcVar2.y() + mcVar2.X + mcVar2.a0);
        if (this.g != null) {
            Rect rect = new Rect();
            this.g.getPadding(rect);
            iY += rect.left;
            iZ += rect.right;
        }
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        WeakHashMap weakHashMap = xr0.a;
        setPaddingRelative(iY, paddingTop, iZ, paddingBottom);
    }

    @Override // android.widget.CheckBox, android.widget.CompoundButton, android.widget.Button, android.widget.TextView, android.view.View
    public CharSequence getAccessibilityClassName() {
        if (!TextUtils.isEmpty(this.s)) {
            return this.s;
        }
        mc mcVar = this.f;
        if (!(mcVar != null && mcVar.R)) {
            return isClickable() ? "android.widget.Button" : "android.view.View";
        }
        ViewParent parent = getParent();
        return ((parent instanceof ChipGroup) && ((ChipGroup) parent).i.d) ? "android.widget.RadioButton" : "android.widget.Button";
    }

    public Drawable getBackgroundDrawable() {
        InsetDrawable insetDrawable = this.g;
        return insetDrawable == null ? this.f : insetDrawable;
    }

    public Drawable getCheckedIcon() {
        mc mcVar = this.f;
        if (mcVar != null) {
            return mcVar.T;
        }
        return null;
    }

    public ColorStateList getCheckedIconTint() {
        mc mcVar = this.f;
        if (mcVar != null) {
            return mcVar.U;
        }
        return null;
    }

    public ColorStateList getChipBackgroundColor() {
        mc mcVar = this.f;
        if (mcVar != null) {
            return mcVar.z;
        }
        return null;
    }

    public float getChipCornerRadius() {
        mc mcVar = this.f;
        if (mcVar != null) {
            return Math.max(0.0f, mcVar.A());
        }
        return 0.0f;
    }

    public Drawable getChipDrawable() {
        return this.f;
    }

    public float getChipEndPadding() {
        mc mcVar = this.f;
        if (mcVar != null) {
            return mcVar.e0;
        }
        return 0.0f;
    }

    public Drawable getChipIcon() {
        Drawable drawable;
        mc mcVar = this.f;
        if (mcVar == null || (drawable = mcVar.H) == null) {
            return null;
        }
        return nv0.Q(drawable);
    }

    public float getChipIconSize() {
        mc mcVar = this.f;
        if (mcVar != null) {
            return mcVar.J;
        }
        return 0.0f;
    }

    public ColorStateList getChipIconTint() {
        mc mcVar = this.f;
        if (mcVar != null) {
            return mcVar.I;
        }
        return null;
    }

    public float getChipMinHeight() {
        mc mcVar = this.f;
        if (mcVar != null) {
            return mcVar.A;
        }
        return 0.0f;
    }

    public float getChipStartPadding() {
        mc mcVar = this.f;
        if (mcVar != null) {
            return mcVar.X;
        }
        return 0.0f;
    }

    public ColorStateList getChipStrokeColor() {
        mc mcVar = this.f;
        if (mcVar != null) {
            return mcVar.C;
        }
        return null;
    }

    public float getChipStrokeWidth() {
        mc mcVar = this.f;
        if (mcVar != null) {
            return mcVar.D;
        }
        return 0.0f;
    }

    @Deprecated
    public CharSequence getChipText() {
        return getText();
    }

    public Drawable getCloseIcon() {
        Drawable drawable;
        mc mcVar = this.f;
        if (mcVar == null || (drawable = mcVar.M) == null) {
            return null;
        }
        return nv0.Q(drawable);
    }

    public CharSequence getCloseIconContentDescription() {
        mc mcVar = this.f;
        if (mcVar != null) {
            return mcVar.Q;
        }
        return null;
    }

    public float getCloseIconEndPadding() {
        mc mcVar = this.f;
        if (mcVar != null) {
            return mcVar.d0;
        }
        return 0.0f;
    }

    public float getCloseIconSize() {
        mc mcVar = this.f;
        if (mcVar != null) {
            return mcVar.P;
        }
        return 0.0f;
    }

    public float getCloseIconStartPadding() {
        mc mcVar = this.f;
        if (mcVar != null) {
            return mcVar.c0;
        }
        return 0.0f;
    }

    public ColorStateList getCloseIconTint() {
        mc mcVar = this.f;
        if (mcVar != null) {
            return mcVar.O;
        }
        return null;
    }

    @Override // android.widget.TextView
    public TextUtils.TruncateAt getEllipsize() {
        mc mcVar = this.f;
        if (mcVar != null) {
            return mcVar.C0;
        }
        return null;
    }

    @Override // android.widget.TextView, android.view.View
    public final void getFocusedRect(Rect rect) {
        if (this.u) {
            kc kcVar = this.t;
            if (kcVar.l == 1 || kcVar.k == 1) {
                rect.set(getCloseIconTouchBoundsInt());
                return;
            }
        }
        super.getFocusedRect(rect);
    }

    public x70 getHideMotionSpec() {
        mc mcVar = this.f;
        if (mcVar != null) {
            return mcVar.W;
        }
        return null;
    }

    public float getIconEndPadding() {
        mc mcVar = this.f;
        if (mcVar != null) {
            return mcVar.Z;
        }
        return 0.0f;
    }

    public float getIconStartPadding() {
        mc mcVar = this.f;
        if (mcVar != null) {
            return mcVar.Y;
        }
        return 0.0f;
    }

    public ColorStateList getRippleColor() {
        mc mcVar = this.f;
        if (mcVar != null) {
            return mcVar.E;
        }
        return null;
    }

    public ki0 getShapeAppearanceModel() {
        return this.f.b.a;
    }

    public x70 getShowMotionSpec() {
        mc mcVar = this.f;
        if (mcVar != null) {
            return mcVar.V;
        }
        return null;
    }

    public float getTextEndPadding() {
        mc mcVar = this.f;
        if (mcVar != null) {
            return mcVar.b0;
        }
        return 0.0f;
    }

    public float getTextStartPadding() {
        mc mcVar = this.f;
        if (mcVar != null) {
            return mcVar.a0;
        }
        return 0.0f;
    }

    public final void h() {
        TextPaint paint = getPaint();
        mc mcVar = this.f;
        if (mcVar != null) {
            paint.drawableState = mcVar.getState();
        }
        en0 textAppearance = getTextAppearance();
        if (textAppearance != null) {
            textAppearance.e(getContext(), paint, this.x);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        oy.O(this, this.f);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final int[] onCreateDrawableState(int i) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + 2);
        if (isChecked()) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, A);
        }
        mc mcVar = this.f;
        if (mcVar != null && mcVar.R) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, B);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // android.widget.TextView, android.view.View
    public final void onFocusChanged(boolean z2, int i, Rect rect) {
        super.onFocusChanged(z2, i, rect);
        if (this.u) {
            kc kcVar = this.t;
            int i2 = kcVar.l;
            if (i2 != Integer.MIN_VALUE) {
                kcVar.j(i2);
            }
            if (z2) {
                kcVar.q(i, rect);
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
        mc mcVar = this.f;
        int i2 = 0;
        accessibilityNodeInfo.setCheckable(mcVar != null && mcVar.R);
        accessibilityNodeInfo.setClickable(isClickable());
        if (getParent() instanceof ChipGroup) {
            ChipGroup chipGroup = (ChipGroup) getParent();
            if (chipGroup.d) {
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
            accessibilityNodeInfo.setCollectionItemInfo(n0.a(tag instanceof Integer ? ((Integer) tag).intValue() : -1, 1, i, 1, false, isChecked()).a);
        }
    }

    @Override // android.widget.Button, android.widget.TextView, android.view.View
    public final PointerIcon onResolvePointerIcon(MotionEvent motionEvent, int i) {
        return (getCloseIconTouchBounds().contains(motionEvent.getX(), motionEvent.getY()) && isEnabled()) ? PointerIcon.getSystemIcon(getContext(), CloseFrame.PROTOCOL_ERROR) : super.onResolvePointerIcon(motionEvent, i);
    }

    @Override // android.widget.TextView, android.view.View
    public final void onRtlPropertiesChanged(int i) {
        super.onRtlPropertiesChanged(i);
        if (this.q != i) {
            this.q = i;
            g();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
    
        if (r0 != 3) goto L28;
     */
    @Override // android.widget.TextView, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
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
            boolean r0 = r5.m
            if (r0 == 0) goto L50
            if (r1 != 0) goto L2a
            r5.setCloseIconPressed(r2)
        L2a:
            r0 = r3
            goto L51
        L2c:
            boolean r0 = r5.m
            if (r0 == 0) goto L45
            r5.playSoundEffect(r2)
            android.view.View$OnClickListener r0 = r5.i
            if (r0 == 0) goto L3a
            r0.onClick(r5)
        L3a:
            boolean r0 = r5.u
            if (r0 == 0) goto L43
            kc r0 = r5.t
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
        this.s = charSequence;
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        if (drawable == getBackgroundDrawable() || drawable == this.h) {
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
        if (drawable == getBackgroundDrawable() || drawable == this.h) {
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

    public void setCheckable(boolean z2) {
        mc mcVar = this.f;
        if (mcVar != null) {
            mcVar.F(z2);
        }
    }

    public void setCheckableResource(int i) {
        mc mcVar = this.f;
        if (mcVar != null) {
            mcVar.F(mcVar.f0.getResources().getBoolean(i));
        }
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z2) {
        mc mcVar = this.f;
        if (mcVar == null) {
            this.l = z2;
        } else if (mcVar.R) {
            super.setChecked(z2);
        }
    }

    public void setCheckedIcon(Drawable drawable) {
        mc mcVar = this.f;
        if (mcVar != null) {
            mcVar.G(drawable);
        }
    }

    @Deprecated
    public void setCheckedIconEnabled(boolean z2) {
        setCheckedIconVisible(z2);
    }

    @Deprecated
    public void setCheckedIconEnabledResource(int i) {
        setCheckedIconVisible(i);
    }

    public void setCheckedIconResource(int i) {
        mc mcVar = this.f;
        if (mcVar != null) {
            mcVar.G(rp0.y(mcVar.f0, i));
        }
    }

    public void setCheckedIconTint(ColorStateList colorStateList) {
        mc mcVar = this.f;
        if (mcVar != null) {
            mcVar.H(colorStateList);
        }
    }

    public void setCheckedIconTintResource(int i) {
        mc mcVar = this.f;
        if (mcVar != null) {
            mcVar.H(rp0.w(mcVar.f0, i));
        }
    }

    public void setCheckedIconVisible(int i) {
        mc mcVar = this.f;
        if (mcVar != null) {
            mcVar.I(mcVar.f0.getResources().getBoolean(i));
        }
    }

    public void setChipBackgroundColor(ColorStateList colorStateList) {
        mc mcVar = this.f;
        if (mcVar == null || mcVar.z == colorStateList) {
            return;
        }
        mcVar.z = colorStateList;
        mcVar.onStateChange(mcVar.getState());
    }

    public void setChipBackgroundColorResource(int i) {
        ColorStateList colorStateListW;
        mc mcVar = this.f;
        if (mcVar == null || mcVar.z == (colorStateListW = rp0.w(mcVar.f0, i))) {
            return;
        }
        mcVar.z = colorStateListW;
        mcVar.onStateChange(mcVar.getState());
    }

    @Deprecated
    public void setChipCornerRadius(float f) {
        mc mcVar = this.f;
        if (mcVar != null) {
            mcVar.J(f);
        }
    }

    @Deprecated
    public void setChipCornerRadiusResource(int i) {
        mc mcVar = this.f;
        if (mcVar != null) {
            mcVar.J(mcVar.f0.getResources().getDimension(i));
        }
    }

    public void setChipDrawable(mc mcVar) {
        mc mcVar2 = this.f;
        if (mcVar2 != mcVar) {
            if (mcVar2 != null) {
                mcVar2.B0 = new WeakReference(null);
            }
            this.f = mcVar;
            mcVar.D0 = false;
            mcVar.B0 = new WeakReference(this);
            c(this.r);
        }
    }

    public void setChipEndPadding(float f) {
        mc mcVar = this.f;
        if (mcVar == null || mcVar.e0 == f) {
            return;
        }
        mcVar.e0 = f;
        mcVar.invalidateSelf();
        mcVar.D();
    }

    public void setChipEndPaddingResource(int i) {
        mc mcVar = this.f;
        if (mcVar != null) {
            float dimension = mcVar.f0.getResources().getDimension(i);
            if (mcVar.e0 != dimension) {
                mcVar.e0 = dimension;
                mcVar.invalidateSelf();
                mcVar.D();
            }
        }
    }

    public void setChipIcon(Drawable drawable) {
        mc mcVar = this.f;
        if (mcVar != null) {
            mcVar.K(drawable);
        }
    }

    @Deprecated
    public void setChipIconEnabled(boolean z2) {
        setChipIconVisible(z2);
    }

    @Deprecated
    public void setChipIconEnabledResource(int i) {
        setChipIconVisible(i);
    }

    public void setChipIconResource(int i) {
        mc mcVar = this.f;
        if (mcVar != null) {
            mcVar.K(rp0.y(mcVar.f0, i));
        }
    }

    public void setChipIconSize(float f) {
        mc mcVar = this.f;
        if (mcVar != null) {
            mcVar.L(f);
        }
    }

    public void setChipIconSizeResource(int i) {
        mc mcVar = this.f;
        if (mcVar != null) {
            mcVar.L(mcVar.f0.getResources().getDimension(i));
        }
    }

    public void setChipIconTint(ColorStateList colorStateList) {
        mc mcVar = this.f;
        if (mcVar != null) {
            mcVar.M(colorStateList);
        }
    }

    public void setChipIconTintResource(int i) {
        mc mcVar = this.f;
        if (mcVar != null) {
            mcVar.M(rp0.w(mcVar.f0, i));
        }
    }

    public void setChipIconVisible(int i) {
        mc mcVar = this.f;
        if (mcVar != null) {
            mcVar.N(mcVar.f0.getResources().getBoolean(i));
        }
    }

    public void setChipMinHeight(float f) {
        mc mcVar = this.f;
        if (mcVar == null || mcVar.A == f) {
            return;
        }
        mcVar.A = f;
        mcVar.invalidateSelf();
        mcVar.D();
    }

    public void setChipMinHeightResource(int i) {
        mc mcVar = this.f;
        if (mcVar != null) {
            float dimension = mcVar.f0.getResources().getDimension(i);
            if (mcVar.A != dimension) {
                mcVar.A = dimension;
                mcVar.invalidateSelf();
                mcVar.D();
            }
        }
    }

    public void setChipStartPadding(float f) {
        mc mcVar = this.f;
        if (mcVar == null || mcVar.X == f) {
            return;
        }
        mcVar.X = f;
        mcVar.invalidateSelf();
        mcVar.D();
    }

    public void setChipStartPaddingResource(int i) {
        mc mcVar = this.f;
        if (mcVar != null) {
            float dimension = mcVar.f0.getResources().getDimension(i);
            if (mcVar.X != dimension) {
                mcVar.X = dimension;
                mcVar.invalidateSelf();
                mcVar.D();
            }
        }
    }

    public void setChipStrokeColor(ColorStateList colorStateList) {
        mc mcVar = this.f;
        if (mcVar != null) {
            mcVar.O(colorStateList);
        }
    }

    public void setChipStrokeColorResource(int i) {
        mc mcVar = this.f;
        if (mcVar != null) {
            mcVar.O(rp0.w(mcVar.f0, i));
        }
    }

    public void setChipStrokeWidth(float f) {
        mc mcVar = this.f;
        if (mcVar != null) {
            mcVar.P(f);
        }
    }

    public void setChipStrokeWidthResource(int i) {
        mc mcVar = this.f;
        if (mcVar != null) {
            mcVar.P(mcVar.f0.getResources().getDimension(i));
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
        mc mcVar = this.f;
        if (mcVar != null) {
            mcVar.Q(drawable);
        }
        e();
    }

    public void setCloseIconContentDescription(CharSequence charSequence) {
        mc mcVar = this.f;
        if (mcVar == null || mcVar.Q == charSequence) {
            return;
        }
        String str = p8.b;
        p8 p8Var = TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 1 ? p8.e : p8.d;
        p8Var.getClass();
        e9 e9Var = fn0.a;
        mcVar.Q = p8Var.c(charSequence);
        mcVar.invalidateSelf();
    }

    @Deprecated
    public void setCloseIconEnabled(boolean z2) {
        setCloseIconVisible(z2);
    }

    @Deprecated
    public void setCloseIconEnabledResource(int i) {
        setCloseIconVisible(i);
    }

    public void setCloseIconEndPadding(float f) {
        mc mcVar = this.f;
        if (mcVar != null) {
            mcVar.R(f);
        }
    }

    public void setCloseIconEndPaddingResource(int i) {
        mc mcVar = this.f;
        if (mcVar != null) {
            mcVar.R(mcVar.f0.getResources().getDimension(i));
        }
    }

    public void setCloseIconResource(int i) {
        mc mcVar = this.f;
        if (mcVar != null) {
            mcVar.Q(rp0.y(mcVar.f0, i));
        }
        e();
    }

    public void setCloseIconSize(float f) {
        mc mcVar = this.f;
        if (mcVar != null) {
            mcVar.S(f);
        }
    }

    public void setCloseIconSizeResource(int i) {
        mc mcVar = this.f;
        if (mcVar != null) {
            mcVar.S(mcVar.f0.getResources().getDimension(i));
        }
    }

    public void setCloseIconStartPadding(float f) {
        mc mcVar = this.f;
        if (mcVar != null) {
            mcVar.T(f);
        }
    }

    public void setCloseIconStartPaddingResource(int i) {
        mc mcVar = this.f;
        if (mcVar != null) {
            mcVar.T(mcVar.f0.getResources().getDimension(i));
        }
    }

    public void setCloseIconTint(ColorStateList colorStateList) {
        mc mcVar = this.f;
        if (mcVar != null) {
            mcVar.U(colorStateList);
        }
    }

    public void setCloseIconTintResource(int i) {
        mc mcVar = this.f;
        if (mcVar != null) {
            mcVar.U(rp0.w(mcVar.f0, i));
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
        mc mcVar = this.f;
        if (mcVar != null) {
            mcVar.l(f);
        }
    }

    @Override // android.widget.TextView
    public void setEllipsize(TextUtils.TruncateAt truncateAt) {
        if (this.f == null) {
            return;
        }
        if (truncateAt == TextUtils.TruncateAt.MARQUEE) {
            throw new UnsupportedOperationException("Text within a chip are not allowed to scroll.");
        }
        super.setEllipsize(truncateAt);
        mc mcVar = this.f;
        if (mcVar != null) {
            mcVar.C0 = truncateAt;
        }
    }

    public void setEnsureMinTouchTargetSize(boolean z2) {
        this.p = z2;
        c(this.r);
    }

    @Override // android.widget.TextView
    public void setGravity(int i) {
        if (i != 8388627) {
            Log.w("Chip", "Chip text must be vertically center and start aligned");
        } else {
            super.setGravity(i);
        }
    }

    public void setHideMotionSpec(x70 x70Var) {
        mc mcVar = this.f;
        if (mcVar != null) {
            mcVar.W = x70Var;
        }
    }

    public void setHideMotionSpecResource(int i) {
        mc mcVar = this.f;
        if (mcVar != null) {
            mcVar.W = x70.b(mcVar.f0, i);
        }
    }

    public void setIconEndPadding(float f) {
        mc mcVar = this.f;
        if (mcVar != null) {
            mcVar.W(f);
        }
    }

    public void setIconEndPaddingResource(int i) {
        mc mcVar = this.f;
        if (mcVar != null) {
            mcVar.W(mcVar.f0.getResources().getDimension(i));
        }
    }

    public void setIconStartPadding(float f) {
        mc mcVar = this.f;
        if (mcVar != null) {
            mcVar.X(f);
        }
    }

    public void setIconStartPaddingResource(int i) {
        mc mcVar = this.f;
        if (mcVar != null) {
            mcVar.X(mcVar.f0.getResources().getDimension(i));
        }
    }

    @Override // defpackage.o50
    public void setInternalOnCheckedChangeListener(n50 n50Var) {
        this.k = n50Var;
    }

    @Override // android.view.View
    public void setLayoutDirection(int i) {
        if (this.f == null) {
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
        mc mcVar = this.f;
        if (mcVar != null) {
            mcVar.E0 = i;
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
        this.j = onCheckedChangeListener;
    }

    public void setOnCloseIconClickListener(View.OnClickListener onClickListener) {
        this.i = onClickListener;
        e();
    }

    public void setRippleColor(ColorStateList colorStateList) {
        mc mcVar = this.f;
        if (mcVar != null) {
            mcVar.Y(colorStateList);
        }
        this.f.getClass();
        f();
    }

    public void setRippleColorResource(int i) {
        mc mcVar = this.f;
        if (mcVar != null) {
            mcVar.Y(rp0.w(mcVar.f0, i));
            this.f.getClass();
            f();
        }
    }

    @Override // defpackage.vi0
    public void setShapeAppearanceModel(ki0 ki0Var) {
        this.f.setShapeAppearanceModel(ki0Var);
    }

    public void setShowMotionSpec(x70 x70Var) {
        mc mcVar = this.f;
        if (mcVar != null) {
            mcVar.V = x70Var;
        }
    }

    public void setShowMotionSpecResource(int i) {
        mc mcVar = this.f;
        if (mcVar != null) {
            mcVar.V = x70.b(mcVar.f0, i);
        }
    }

    @Override // android.widget.TextView
    public void setSingleLine(boolean z2) {
        if (!z2) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setSingleLine(z2);
    }

    @Override // android.widget.TextView
    public final void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        mc mcVar = this.f;
        if (mcVar == null) {
            return;
        }
        if (charSequence == null) {
            charSequence = ExtensionRequestData.EMPTY_VALUE;
        }
        super.setText(mcVar.D0 ? null : charSequence, bufferType);
        mc mcVar2 = this.f;
        if (mcVar2 == null || TextUtils.equals(mcVar2.F, charSequence)) {
            return;
        }
        mcVar2.F = charSequence;
        mcVar2.l0.e = true;
        mcVar2.invalidateSelf();
        mcVar2.D();
    }

    public void setTextAppearance(en0 en0Var) {
        mc mcVar = this.f;
        if (mcVar != null) {
            mcVar.l0.c(en0Var, mcVar.f0);
        }
        h();
    }

    public void setTextAppearanceResource(int i) {
        setTextAppearance(getContext(), i);
    }

    public void setTextEndPadding(float f) {
        mc mcVar = this.f;
        if (mcVar == null || mcVar.b0 == f) {
            return;
        }
        mcVar.b0 = f;
        mcVar.invalidateSelf();
        mcVar.D();
    }

    public void setTextEndPaddingResource(int i) {
        mc mcVar = this.f;
        if (mcVar != null) {
            float dimension = mcVar.f0.getResources().getDimension(i);
            if (mcVar.b0 != dimension) {
                mcVar.b0 = dimension;
                mcVar.invalidateSelf();
                mcVar.D();
            }
        }
    }

    @Override // android.widget.TextView
    public final void setTextSize(int i, float f) {
        super.setTextSize(i, f);
        mc mcVar = this.f;
        if (mcVar != null) {
            float fApplyDimension = TypedValue.applyDimension(i, f, getResources().getDisplayMetrics());
            hn0 hn0Var = mcVar.l0;
            en0 en0Var = hn0Var.g;
            if (en0Var != null) {
                en0Var.k = fApplyDimension;
                hn0Var.a.setTextSize(fApplyDimension);
                mcVar.a();
            }
        }
        h();
    }

    public void setTextStartPadding(float f) {
        mc mcVar = this.f;
        if (mcVar == null || mcVar.a0 == f) {
            return;
        }
        mcVar.a0 = f;
        mcVar.invalidateSelf();
        mcVar.D();
    }

    public void setTextStartPaddingResource(int i) {
        mc mcVar = this.f;
        if (mcVar != null) {
            float dimension = mcVar.f0.getResources().getDimension(i);
            if (mcVar.a0 != dimension) {
                mcVar.a0 = dimension;
                mcVar.invalidateSelf();
                mcVar.D();
            }
        }
    }

    public Chip(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.chipStyle);
    }

    public void setCloseIconVisible(boolean z2) {
        mc mcVar = this.f;
        if (mcVar != null) {
            mcVar.V(z2);
        }
        e();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Chip(Context context, AttributeSet attributeSet, int i) {
        int resourceId;
        int i2 = y;
        super(y50.a(context, attributeSet, i, i2), attributeSet, i);
        this.v = new Rect();
        this.w = new RectF();
        this.x = new ic(0, this);
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
        mc mcVar = new mc(context2, attributeSet, i);
        TypedArray typedArrayD = un0.d(mcVar.f0, attributeSet, R$styleable.Chip, i, i2, new int[0]);
        mcVar.F0 = typedArrayD.hasValue(R$styleable.Chip_shapeAppearance);
        int i3 = R$styleable.Chip_chipSurfaceColor;
        Context context3 = mcVar.f0;
        ColorStateList colorStateListS = xi.s(context3, typedArrayD, i3);
        if (mcVar.y != colorStateListS) {
            mcVar.y = colorStateListS;
            mcVar.onStateChange(mcVar.getState());
        }
        ColorStateList colorStateListS2 = xi.s(context3, typedArrayD, R$styleable.Chip_chipBackgroundColor);
        if (mcVar.z != colorStateListS2) {
            mcVar.z = colorStateListS2;
            mcVar.onStateChange(mcVar.getState());
        }
        float dimension = typedArrayD.getDimension(R$styleable.Chip_chipMinHeight, 0.0f);
        if (mcVar.A != dimension) {
            mcVar.A = dimension;
            mcVar.invalidateSelf();
            mcVar.D();
        }
        if (typedArrayD.hasValue(R$styleable.Chip_chipCornerRadius)) {
            mcVar.J(typedArrayD.getDimension(R$styleable.Chip_chipCornerRadius, 0.0f));
        }
        mcVar.O(xi.s(context3, typedArrayD, R$styleable.Chip_chipStrokeColor));
        mcVar.P(typedArrayD.getDimension(R$styleable.Chip_chipStrokeWidth, 0.0f));
        mcVar.Y(xi.s(context3, typedArrayD, R$styleable.Chip_rippleColor));
        CharSequence text = typedArrayD.getText(R$styleable.Chip_android_text);
        text = text == null ? ExtensionRequestData.EMPTY_VALUE : text;
        boolean zEquals = TextUtils.equals(mcVar.F, text);
        hn0 hn0Var = mcVar.l0;
        if (!zEquals) {
            mcVar.F = text;
            hn0Var.e = true;
            mcVar.invalidateSelf();
            mcVar.D();
        }
        int i4 = R$styleable.Chip_android_textAppearance;
        en0 en0Var = (!typedArrayD.hasValue(i4) || (resourceId = typedArrayD.getResourceId(i4, 0)) == 0) ? null : new en0(context3, resourceId);
        en0Var.k = typedArrayD.getDimension(R$styleable.Chip_android_textSize, en0Var.k);
        hn0Var.c(en0Var, context3);
        int i5 = typedArrayD.getInt(R$styleable.Chip_android_ellipsize, 0);
        if (i5 == 1) {
            mcVar.C0 = TextUtils.TruncateAt.START;
        } else if (i5 == 2) {
            mcVar.C0 = TextUtils.TruncateAt.MIDDLE;
        } else if (i5 == 3) {
            mcVar.C0 = TextUtils.TruncateAt.END;
        }
        mcVar.N(typedArrayD.getBoolean(R$styleable.Chip_chipIconVisible, false));
        if (attributeSet != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "chipIconEnabled") != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "chipIconVisible") == null) {
            mcVar.N(typedArrayD.getBoolean(R$styleable.Chip_chipIconEnabled, false));
        }
        mcVar.K(xi.x(context3, typedArrayD, R$styleable.Chip_chipIcon));
        if (typedArrayD.hasValue(R$styleable.Chip_chipIconTint)) {
            mcVar.M(xi.s(context3, typedArrayD, R$styleable.Chip_chipIconTint));
        }
        mcVar.L(typedArrayD.getDimension(R$styleable.Chip_chipIconSize, -1.0f));
        mcVar.V(typedArrayD.getBoolean(R$styleable.Chip_closeIconVisible, false));
        if (attributeSet != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "closeIconEnabled") != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "closeIconVisible") == null) {
            mcVar.V(typedArrayD.getBoolean(R$styleable.Chip_closeIconEnabled, false));
        }
        mcVar.Q(xi.x(context3, typedArrayD, R$styleable.Chip_closeIcon));
        mcVar.U(xi.s(context3, typedArrayD, R$styleable.Chip_closeIconTint));
        mcVar.S(typedArrayD.getDimension(R$styleable.Chip_closeIconSize, 0.0f));
        mcVar.F(typedArrayD.getBoolean(R$styleable.Chip_android_checkable, false));
        mcVar.I(typedArrayD.getBoolean(R$styleable.Chip_checkedIconVisible, false));
        if (attributeSet != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "checkedIconEnabled") != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "checkedIconVisible") == null) {
            mcVar.I(typedArrayD.getBoolean(R$styleable.Chip_checkedIconEnabled, false));
        }
        mcVar.G(xi.x(context3, typedArrayD, R$styleable.Chip_checkedIcon));
        if (typedArrayD.hasValue(R$styleable.Chip_checkedIconTint)) {
            mcVar.H(xi.s(context3, typedArrayD, R$styleable.Chip_checkedIconTint));
        }
        mcVar.V = x70.a(context3, typedArrayD, R$styleable.Chip_showMotionSpec);
        mcVar.W = x70.a(context3, typedArrayD, R$styleable.Chip_hideMotionSpec);
        float dimension2 = typedArrayD.getDimension(R$styleable.Chip_chipStartPadding, 0.0f);
        if (mcVar.X != dimension2) {
            mcVar.X = dimension2;
            mcVar.invalidateSelf();
            mcVar.D();
        }
        mcVar.X(typedArrayD.getDimension(R$styleable.Chip_iconStartPadding, 0.0f));
        mcVar.W(typedArrayD.getDimension(R$styleable.Chip_iconEndPadding, 0.0f));
        float dimension3 = typedArrayD.getDimension(R$styleable.Chip_textStartPadding, 0.0f);
        if (mcVar.a0 != dimension3) {
            mcVar.a0 = dimension3;
            mcVar.invalidateSelf();
            mcVar.D();
        }
        float dimension4 = typedArrayD.getDimension(R$styleable.Chip_textEndPadding, 0.0f);
        if (mcVar.b0 != dimension4) {
            mcVar.b0 = dimension4;
            mcVar.invalidateSelf();
            mcVar.D();
        }
        mcVar.T(typedArrayD.getDimension(R$styleable.Chip_closeIconStartPadding, 0.0f));
        mcVar.R(typedArrayD.getDimension(R$styleable.Chip_closeIconEndPadding, 0.0f));
        float dimension5 = typedArrayD.getDimension(R$styleable.Chip_chipEndPadding, 0.0f);
        if (mcVar.e0 != dimension5) {
            mcVar.e0 = dimension5;
            mcVar.invalidateSelf();
            mcVar.D();
        }
        mcVar.E0 = typedArrayD.getDimensionPixelSize(R$styleable.Chip_android_maxWidth, Integer.MAX_VALUE);
        typedArrayD.recycle();
        int[] iArr = R$styleable.Chip;
        un0.a(context2, attributeSet, i, i2);
        un0.b(context2, attributeSet, iArr, i, i2, new int[0]);
        TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, iArr, i, i2);
        this.p = typedArrayObtainStyledAttributes.getBoolean(R$styleable.Chip_ensureMinTouchTargetSize, false);
        this.r = (int) Math.ceil(typedArrayObtainStyledAttributes.getDimension(R$styleable.Chip_chipMinTouchTargetSize, (float) Math.ceil(rp0.t(getContext(), 48))));
        typedArrayObtainStyledAttributes.recycle();
        setChipDrawable(mcVar);
        WeakHashMap weakHashMap = xr0.a;
        mcVar.l(lr0.i(this));
        int[] iArr2 = R$styleable.Chip;
        un0.a(context2, attributeSet, i, i2);
        un0.b(context2, attributeSet, iArr2, i, i2, new int[0]);
        TypedArray typedArrayObtainStyledAttributes2 = context2.obtainStyledAttributes(attributeSet, iArr2, i, i2);
        boolean zHasValue = typedArrayObtainStyledAttributes2.hasValue(R$styleable.Chip_shapeAppearance);
        typedArrayObtainStyledAttributes2.recycle();
        this.t = new kc(this, this);
        e();
        if (!zHasValue) {
            setOutlineProvider(new jc(0, this));
        }
        setChecked(this.l);
        setText(mcVar.F);
        setEllipsize(mcVar.C0);
        h();
        if (!this.f.D0) {
            setLines(1);
            setHorizontallyScrolling(true);
        }
        setGravity(8388627);
        g();
        if (this.p) {
            setMinHeight(this.r);
        }
        this.q = getLayoutDirection();
        super.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: hc
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
                Chip chip = this.a;
                n50 n50Var = chip.k;
                if (n50Var != null) {
                    bc bcVar = (bc) ((o70) n50Var).c;
                    if (!z2 ? bcVar.e(chip, bcVar.e) : bcVar.a(chip)) {
                        bcVar.d();
                    }
                }
                CompoundButton.OnCheckedChangeListener onCheckedChangeListener = chip.j;
                if (onCheckedChangeListener != null) {
                    onCheckedChangeListener.onCheckedChanged(compoundButton, z2);
                }
            }
        });
    }

    public void setCheckedIconVisible(boolean z2) {
        mc mcVar = this.f;
        if (mcVar != null) {
            mcVar.I(z2);
        }
    }

    public void setChipIconVisible(boolean z2) {
        mc mcVar = this.f;
        if (mcVar != null) {
            mcVar.N(z2);
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
        mc mcVar = this.f;
        if (mcVar != null) {
            Context context2 = mcVar.f0;
            mcVar.l0.c(new en0(context2, i), context2);
        }
        h();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(int i) {
        super.setTextAppearance(i);
        mc mcVar = this.f;
        if (mcVar != null) {
            Context context = mcVar.f0;
            mcVar.l0.c(new en0(context, i), context);
        }
        h();
    }
}
