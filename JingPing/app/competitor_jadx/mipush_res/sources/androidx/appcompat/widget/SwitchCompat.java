package androidx.appcompat.widget;

import android.R;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.InputFilter;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CompoundButton;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$string;
import androidx.appcompat.R$styleable;
import androidx.core.R$id;
import defpackage.c7;
import defpackage.f51;
import defpackage.fa0;
import defpackage.h6;
import defpackage.ip;
import defpackage.j01;
import defpackage.m4;
import defpackage.mt;
import defpackage.op;
import defpackage.ou;
import defpackage.p41;
import defpackage.q61;
import defpackage.qm;
import defpackage.t40;
import defpackage.vy0;
import defpackage.yc;
import defpackage.zs;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class SwitchCompat extends CompoundButton {
    public static final yc y0 = new yc(Float.class, "thumbPos", 17);
    public static final int[] z0 = {R.attr.state_checked};
    public Drawable E;
    public ColorStateList F;
    public PorterDuff.Mode G;
    public boolean H;
    public boolean I;
    public Drawable J;
    public ColorStateList K;
    public PorterDuff.Mode L;
    public boolean M;
    public boolean N;
    public int O;
    public int P;
    public int Q;
    public boolean R;
    public CharSequence S;
    public CharSequence T;
    public CharSequence U;
    public CharSequence V;
    public boolean W;
    public int a0;
    public final int b0;
    public float c0;
    public float d0;
    public final VelocityTracker e0;
    public final int f0;
    public float g0;
    public int h0;
    public int i0;
    public int j0;
    public int k0;
    public int l0;
    public int m0;
    public int n0;
    public boolean o0;
    public final TextPaint p0;
    public final ColorStateList q0;
    public StaticLayout r0;
    public StaticLayout s0;
    public final m4 t0;
    public ObjectAnimator u0;
    public h6 v0;
    public mt w0;
    public final Rect x0;

    public SwitchCompat(Context context) {
        this(context, null);
    }

    private h6 getEmojiTextViewHelper() {
        if (this.v0 == null) {
            this.v0 = new h6(this);
        }
        return this.v0;
    }

    private boolean getTargetCheckedState() {
        return this.g0 > 0.5f;
    }

    private int getThumbOffset() {
        return (int) (((q61.a(this) ? 1.0f - this.g0 : this.g0) * getThumbScrollRange()) + 0.5f);
    }

    private int getThumbScrollRange() {
        Drawable drawable = this.J;
        if (drawable == null) {
            return 0;
        }
        Rect rect = this.x0;
        drawable.getPadding(rect);
        Drawable drawable2 = this.E;
        Rect rectB = drawable2 != null ? op.b(drawable2) : op.c;
        return ((((this.h0 - this.j0) - rect.left) - rect.right) - rectB.left) - rectB.right;
    }

    private void setTextOffInternal(CharSequence charSequence) {
        this.U = charSequence;
        h6 emojiTextViewHelper = getEmojiTextViewHelper();
        TransformationMethod transformationMethodM0 = ((qm) emojiTextViewHelper.b.F).M0(this.t0);
        if (transformationMethodM0 != null) {
            charSequence = transformationMethodM0.getTransformation(charSequence, this);
        }
        this.V = charSequence;
        this.s0 = null;
        if (this.W) {
            d();
        }
    }

    private void setTextOnInternal(CharSequence charSequence) {
        this.S = charSequence;
        h6 emojiTextViewHelper = getEmojiTextViewHelper();
        TransformationMethod transformationMethodM0 = ((qm) emojiTextViewHelper.b.F).M0(this.t0);
        if (transformationMethodM0 != null) {
            charSequence = transformationMethodM0.getTransformation(charSequence, this);
        }
        this.T = charSequence;
        this.r0 = null;
        if (this.W) {
            d();
        }
    }

    public final void a() {
        Drawable drawable = this.E;
        if (drawable != null) {
            if (this.H || this.I) {
                Drawable drawableMutate = drawable.mutate();
                this.E = drawableMutate;
                if (this.H) {
                    ip.h(drawableMutate, this.F);
                }
                if (this.I) {
                    ip.i(this.E, this.G);
                }
                if (this.E.isStateful()) {
                    this.E.setState(getDrawableState());
                }
            }
        }
    }

    public final void b() {
        Drawable drawable = this.J;
        if (drawable != null) {
            if (this.M || this.N) {
                Drawable drawableMutate = drawable.mutate();
                this.J = drawableMutate;
                if (this.M) {
                    ip.h(drawableMutate, this.K);
                }
                if (this.N) {
                    ip.i(this.J, this.L);
                }
                if (this.J.isStateful()) {
                    this.J.setState(getDrawableState());
                }
            }
        }
    }

    public final void c() {
        setTextOnInternal(this.S);
        setTextOffInternal(this.U);
        requestLayout();
    }

    public final void d() {
        if (this.w0 == null && ((qm) this.v0.b.F).O() && zs.j != null) {
            zs zsVarA = zs.a();
            int iB = zsVarA.b();
            if (iB == 3 || iB == 0) {
                mt mtVar = new mt(this);
                this.w0 = mtVar;
                zsVarA.f(mtVar);
            }
        }
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        int i;
        int i2;
        int i3 = this.k0;
        int i4 = this.l0;
        int i5 = this.m0;
        int i6 = this.n0;
        int thumbOffset = getThumbOffset() + i3;
        Drawable drawable = this.E;
        Rect rectB = drawable != null ? op.b(drawable) : op.c;
        Drawable drawable2 = this.J;
        Rect rect = this.x0;
        if (drawable2 != null) {
            drawable2.getPadding(rect);
            int i7 = rect.left;
            thumbOffset += i7;
            if (rectB != null) {
                int i8 = rectB.left;
                if (i8 > i7) {
                    i3 += i8 - i7;
                }
                int i9 = rectB.top;
                int i10 = rect.top;
                i = i9 > i10 ? (i9 - i10) + i4 : i4;
                int i11 = rectB.right;
                int i12 = rect.right;
                if (i11 > i12) {
                    i5 -= i11 - i12;
                }
                int i13 = rectB.bottom;
                int i14 = rect.bottom;
                if (i13 > i14) {
                    i2 = i6 - (i13 - i14);
                }
                this.J.setBounds(i3, i, i5, i2);
            } else {
                i = i4;
            }
            i2 = i6;
            this.J.setBounds(i3, i, i5, i2);
        }
        Drawable drawable3 = this.E;
        if (drawable3 != null) {
            drawable3.getPadding(rect);
            int i15 = thumbOffset - rect.left;
            int i16 = thumbOffset + this.j0 + rect.right;
            this.E.setBounds(i15, i4, i16, i6);
            Drawable background = getBackground();
            if (background != null) {
                ip.f(background, i15, i4, i16, i6);
            }
        }
        super.draw(canvas);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final void drawableHotspotChanged(float f, float f2) {
        super.drawableHotspotChanged(f, f2);
        Drawable drawable = this.E;
        if (drawable != null) {
            ip.e(drawable, f, f2);
        }
        Drawable drawable2 = this.J;
        if (drawable2 != null) {
            ip.e(drawable2, f, f2);
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.E;
        boolean state = (drawable == null || !drawable.isStateful()) ? false : drawable.setState(drawableState);
        Drawable drawable2 = this.J;
        if (drawable2 != null && drawable2.isStateful()) {
            state |= drawable2.setState(drawableState);
        }
        if (state) {
            invalidate();
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingLeft() {
        if (!q61.a(this)) {
            return super.getCompoundPaddingLeft();
        }
        int compoundPaddingLeft = super.getCompoundPaddingLeft() + this.h0;
        return !TextUtils.isEmpty(getText()) ? compoundPaddingLeft + this.Q : compoundPaddingLeft;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingRight() {
        if (q61.a(this)) {
            return super.getCompoundPaddingRight();
        }
        int compoundPaddingRight = super.getCompoundPaddingRight() + this.h0;
        return !TextUtils.isEmpty(getText()) ? compoundPaddingRight + this.Q : compoundPaddingRight;
    }

    @Override // android.widget.TextView
    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        return fa0.O0(super.getCustomSelectionActionModeCallback());
    }

    public boolean getShowText() {
        return this.W;
    }

    public boolean getSplitTrack() {
        return this.R;
    }

    public int getSwitchMinWidth() {
        return this.P;
    }

    public int getSwitchPadding() {
        return this.Q;
    }

    public CharSequence getTextOff() {
        return this.U;
    }

    public CharSequence getTextOn() {
        return this.S;
    }

    public Drawable getThumbDrawable() {
        return this.E;
    }

    public final float getThumbPosition() {
        return this.g0;
    }

    public int getThumbTextPadding() {
        return this.O;
    }

    public ColorStateList getThumbTintList() {
        return this.F;
    }

    public PorterDuff.Mode getThumbTintMode() {
        return this.G;
    }

    public Drawable getTrackDrawable() {
        return this.J;
    }

    public ColorStateList getTrackTintList() {
        return this.K;
    }

    public PorterDuff.Mode getTrackTintMode() {
        return this.L;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.E;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        Drawable drawable2 = this.J;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
        ObjectAnimator objectAnimator = this.u0;
        if (objectAnimator == null || !objectAnimator.isStarted()) {
            return;
        }
        this.u0.end();
        this.u0 = null;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public int[] onCreateDrawableState(int i) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + 1);
        if (isChecked()) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, z0);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final void onDraw(Canvas canvas) {
        int width;
        super.onDraw(canvas);
        Drawable drawable = this.J;
        Rect rect = this.x0;
        if (drawable != null) {
            drawable.getPadding(rect);
        } else {
            rect.setEmpty();
        }
        int i = this.l0;
        int i2 = this.n0;
        int i3 = i + rect.top;
        int i4 = i2 - rect.bottom;
        Drawable drawable2 = this.E;
        if (drawable != null) {
            if (!this.R || drawable2 == null) {
                drawable.draw(canvas);
            } else {
                Rect rectB = op.b(drawable2);
                drawable2.copyBounds(rect);
                rect.left += rectB.left;
                rect.right -= rectB.right;
                int iSave = canvas.save();
                canvas.clipRect(rect, Region.Op.DIFFERENCE);
                drawable.draw(canvas);
                canvas.restoreToCount(iSave);
            }
        }
        int iSave2 = canvas.save();
        if (drawable2 != null) {
            drawable2.draw(canvas);
        }
        StaticLayout staticLayout = getTargetCheckedState() ? this.r0 : this.s0;
        if (staticLayout != null) {
            int[] drawableState = getDrawableState();
            ColorStateList colorStateList = this.q0;
            TextPaint textPaint = this.p0;
            if (colorStateList != null) {
                textPaint.setColor(colorStateList.getColorForState(drawableState, 0));
            }
            textPaint.drawableState = drawableState;
            if (drawable2 != null) {
                Rect bounds = drawable2.getBounds();
                width = bounds.left + bounds.right;
            } else {
                width = getWidth();
            }
            canvas.translate((width / 2) - (staticLayout.getWidth() / 2), ((i3 + i4) / 2) - (staticLayout.getHeight() / 2));
            staticLayout.draw(canvas);
        }
        canvas.restoreToCount(iSave2);
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName("android.widget.Switch");
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName("android.widget.Switch");
        if (Build.VERSION.SDK_INT < 30) {
            CharSequence charSequence = isChecked() ? this.S : this.U;
            if (TextUtils.isEmpty(charSequence)) {
                return;
            }
            CharSequence text = accessibilityNodeInfo.getText();
            if (TextUtils.isEmpty(text)) {
                accessibilityNodeInfo.setText(charSequence);
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(text);
            sb.append(' ');
            sb.append(charSequence);
            accessibilityNodeInfo.setText(sb);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int iMax;
        int width;
        int paddingLeft;
        int height;
        int paddingTop;
        super.onLayout(z, i, i2, i3, i4);
        int iMax2 = 0;
        if (this.E != null) {
            Drawable drawable = this.J;
            Rect rect = this.x0;
            if (drawable != null) {
                drawable.getPadding(rect);
            } else {
                rect.setEmpty();
            }
            Rect rectB = op.b(this.E);
            iMax = Math.max(0, rectB.left - rect.left);
            iMax2 = Math.max(0, rectB.right - rect.right);
        } else {
            iMax = 0;
        }
        if (q61.a(this)) {
            paddingLeft = getPaddingLeft() + iMax;
            width = ((this.h0 + paddingLeft) - iMax) - iMax2;
        } else {
            width = (getWidth() - getPaddingRight()) - iMax2;
            paddingLeft = (width - this.h0) + iMax + iMax2;
        }
        int gravity = getGravity() & 112;
        if (gravity == 16) {
            int height2 = ((getHeight() + getPaddingTop()) - getPaddingBottom()) / 2;
            int i5 = this.i0;
            int i6 = height2 - (i5 / 2);
            height = i5 + i6;
            paddingTop = i6;
        } else if (gravity != 80) {
            paddingTop = getPaddingTop();
            height = this.i0 + paddingTop;
        } else {
            height = getHeight() - getPaddingBottom();
            paddingTop = height - this.i0;
        }
        this.k0 = paddingLeft;
        this.l0 = paddingTop;
        this.n0 = height;
        this.m0 = width;
    }

    @Override // android.widget.TextView, android.view.View
    public final void onMeasure(int i, int i2) {
        int intrinsicWidth;
        int intrinsicHeight;
        int intrinsicHeight2 = 0;
        if (this.W) {
            StaticLayout staticLayout = this.r0;
            TextPaint textPaint = this.p0;
            if (staticLayout == null) {
                CharSequence charSequence = this.T;
                this.r0 = new StaticLayout(charSequence, textPaint, charSequence != null ? (int) Math.ceil(Layout.getDesiredWidth(charSequence, textPaint)) : 0, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
            }
            if (this.s0 == null) {
                CharSequence charSequence2 = this.V;
                this.s0 = new StaticLayout(charSequence2, textPaint, charSequence2 != null ? (int) Math.ceil(Layout.getDesiredWidth(charSequence2, textPaint)) : 0, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
            }
        }
        Drawable drawable = this.E;
        Rect rect = this.x0;
        if (drawable != null) {
            drawable.getPadding(rect);
            intrinsicWidth = (this.E.getIntrinsicWidth() - rect.left) - rect.right;
            intrinsicHeight = this.E.getIntrinsicHeight();
        } else {
            intrinsicWidth = 0;
            intrinsicHeight = 0;
        }
        this.j0 = Math.max(this.W ? (this.O * 2) + Math.max(this.r0.getWidth(), this.s0.getWidth()) : 0, intrinsicWidth);
        Drawable drawable2 = this.J;
        if (drawable2 != null) {
            drawable2.getPadding(rect);
            intrinsicHeight2 = this.J.getIntrinsicHeight();
        } else {
            rect.setEmpty();
        }
        int iMax = rect.left;
        int iMax2 = rect.right;
        Drawable drawable3 = this.E;
        if (drawable3 != null) {
            Rect rectB = op.b(drawable3);
            iMax = Math.max(iMax, rectB.left);
            iMax2 = Math.max(iMax2, rectB.right);
        }
        int iMax3 = this.o0 ? Math.max(this.P, (this.j0 * 2) + iMax + iMax2) : this.P;
        int iMax4 = Math.max(intrinsicHeight2, intrinsicHeight);
        this.h0 = iMax3;
        this.i0 = iMax4;
        super.onMeasure(i, i2);
        if (getMeasuredHeight() < iMax4) {
            setMeasuredDimension(getMeasuredWidthAndState(), iMax4);
        }
    }

    @Override // android.view.View
    public final void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        CharSequence charSequence = isChecked() ? this.S : this.U;
        if (charSequence != null) {
            accessibilityEvent.getText().add(charSequence);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x008c  */
    @Override // android.widget.TextView, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onTouchEvent(android.view.MotionEvent r10) {
        /*
            Method dump skipped, instruction units count: 325
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.SwitchCompat.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.widget.TextView
    public void setAllCaps(boolean z) {
        super.setAllCaps(z);
        getEmojiTextViewHelper().c(z);
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z) {
        super.setChecked(z);
        boolean zIsChecked = isChecked();
        if (zIsChecked) {
            if (Build.VERSION.SDK_INT >= 30) {
                Object string = this.S;
                if (string == null) {
                    string = getResources().getString(R$string.abc_capital_on);
                }
                Object obj = string;
                WeakHashMap weakHashMap = f51.a;
                new p41(R$id.tag_state_description, CharSequence.class, 64, 30, 2).f(this, obj);
            }
        } else if (Build.VERSION.SDK_INT >= 30) {
            Object string2 = this.U;
            if (string2 == null) {
                string2 = getResources().getString(R$string.abc_capital_off);
            }
            Object obj2 = string2;
            WeakHashMap weakHashMap2 = f51.a;
            new p41(R$id.tag_state_description, CharSequence.class, 64, 30, 2).f(this, obj2);
        }
        if (getWindowToken() != null) {
            WeakHashMap weakHashMap3 = f51.a;
            if (isLaidOut()) {
                ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, y0, zIsChecked ? 1.0f : 0.0f);
                this.u0 = objectAnimatorOfFloat;
                objectAnimatorOfFloat.setDuration(250L);
                vy0.a(this.u0, true);
                this.u0.start();
                return;
            }
        }
        ObjectAnimator objectAnimator = this.u0;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
        setThumbPosition(zIsChecked ? 1.0f : 0.0f);
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(fa0.P0(callback, this));
    }

    public void setEmojiCompatEnabled(boolean z) {
        getEmojiTextViewHelper().d(z);
        setTextOnInternal(this.S);
        setTextOffInternal(this.U);
        requestLayout();
    }

    public final void setEnforceSwitchWidth(boolean z) {
        this.o0 = z;
        invalidate();
    }

    @Override // android.widget.TextView
    public void setFilters(InputFilter[] inputFilterArr) {
        super.setFilters(getEmojiTextViewHelper().a(inputFilterArr));
    }

    public void setShowText(boolean z) {
        if (this.W != z) {
            this.W = z;
            requestLayout();
            if (z) {
                d();
            }
        }
    }

    public void setSplitTrack(boolean z) {
        this.R = z;
        invalidate();
    }

    public void setSwitchMinWidth(int i) {
        this.P = i;
        requestLayout();
    }

    public void setSwitchPadding(int i) {
        this.Q = i;
        requestLayout();
    }

    public void setSwitchTypeface(Typeface typeface) {
        TextPaint textPaint = this.p0;
        if ((textPaint.getTypeface() == null || textPaint.getTypeface().equals(typeface)) && (textPaint.getTypeface() != null || typeface == null)) {
            return;
        }
        textPaint.setTypeface(typeface);
        requestLayout();
        invalidate();
    }

    public void setTextOff(CharSequence charSequence) {
        setTextOffInternal(charSequence);
        requestLayout();
        if (isChecked() || Build.VERSION.SDK_INT < 30) {
            return;
        }
        Object string = this.U;
        if (string == null) {
            string = getResources().getString(R$string.abc_capital_off);
        }
        WeakHashMap weakHashMap = f51.a;
        new p41(R$id.tag_state_description, CharSequence.class, 64, 30, 2).f(this, string);
    }

    public void setTextOn(CharSequence charSequence) {
        setTextOnInternal(charSequence);
        requestLayout();
        if (!isChecked() || Build.VERSION.SDK_INT < 30) {
            return;
        }
        Object string = this.S;
        if (string == null) {
            string = getResources().getString(R$string.abc_capital_on);
        }
        WeakHashMap weakHashMap = f51.a;
        new p41(R$id.tag_state_description, CharSequence.class, 64, 30, 2).f(this, string);
    }

    public void setThumbDrawable(Drawable drawable) {
        Drawable drawable2 = this.E;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.E = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        requestLayout();
    }

    public void setThumbPosition(float f) {
        this.g0 = f;
        invalidate();
    }

    public void setThumbResource(int i) {
        setThumbDrawable(qm.z(getContext(), i));
    }

    public void setThumbTextPadding(int i) {
        this.O = i;
        requestLayout();
    }

    public void setThumbTintList(ColorStateList colorStateList) {
        this.F = colorStateList;
        this.H = true;
        a();
    }

    public void setThumbTintMode(PorterDuff.Mode mode) {
        this.G = mode;
        this.I = true;
        a();
    }

    public void setTrackDrawable(Drawable drawable) {
        Drawable drawable2 = this.J;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.J = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        requestLayout();
    }

    public void setTrackResource(int i) {
        setTrackDrawable(qm.z(getContext(), i));
    }

    public void setTrackTintList(ColorStateList colorStateList) {
        this.K = colorStateList;
        this.M = true;
        b();
    }

    public void setTrackTintMode(PorterDuff.Mode mode) {
        this.L = mode;
        this.N = true;
        b();
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public final void toggle() {
        setChecked(!isChecked());
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.E || drawable == this.J;
    }

    public SwitchCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.switchStyle);
    }

    public SwitchCompat(Context context, AttributeSet attributeSet, int i) {
        Typeface typeface;
        Typeface typefaceCreate;
        int resourceId;
        super(context, attributeSet, i);
        this.F = null;
        this.G = null;
        this.H = false;
        this.I = false;
        this.K = null;
        this.L = null;
        this.M = false;
        this.N = false;
        this.e0 = VelocityTracker.obtain();
        this.o0 = true;
        this.x0 = new Rect();
        j01.a(this, getContext());
        TextPaint textPaint = new TextPaint(1);
        this.p0 = textPaint;
        textPaint.density = getResources().getDisplayMetrics().density;
        ou ouVarP = ou.P(context, attributeSet, R$styleable.SwitchCompat, i);
        f51.n(this, context, R$styleable.SwitchCompat, attributeSet, (TypedArray) ouVarP.G, i);
        Drawable drawableA = ouVarP.A(R$styleable.SwitchCompat_android_thumb);
        this.E = drawableA;
        if (drawableA != null) {
            drawableA.setCallback(this);
        }
        Drawable drawableA2 = ouVarP.A(R$styleable.SwitchCompat_track);
        this.J = drawableA2;
        if (drawableA2 != null) {
            drawableA2.setCallback(this);
        }
        int i2 = R$styleable.SwitchCompat_android_textOn;
        TypedArray typedArray = (TypedArray) ouVarP.G;
        setTextOnInternal(typedArray.getText(i2));
        setTextOffInternal(typedArray.getText(R$styleable.SwitchCompat_android_textOff));
        this.W = typedArray.getBoolean(R$styleable.SwitchCompat_showText, true);
        this.O = typedArray.getDimensionPixelSize(R$styleable.SwitchCompat_thumbTextPadding, 0);
        this.P = typedArray.getDimensionPixelSize(R$styleable.SwitchCompat_switchMinWidth, 0);
        this.Q = typedArray.getDimensionPixelSize(R$styleable.SwitchCompat_switchPadding, 0);
        this.R = typedArray.getBoolean(R$styleable.SwitchCompat_splitTrack, false);
        ColorStateList colorStateListY = ouVarP.y(R$styleable.SwitchCompat_thumbTint);
        if (colorStateListY != null) {
            this.F = colorStateListY;
            this.H = true;
        }
        PorterDuff.Mode modeC = op.c(typedArray.getInt(R$styleable.SwitchCompat_thumbTintMode, -1), null);
        if (this.G != modeC) {
            this.G = modeC;
            this.I = true;
        }
        if (this.H || this.I) {
            a();
        }
        ColorStateList colorStateListY2 = ouVarP.y(R$styleable.SwitchCompat_trackTint);
        if (colorStateListY2 != null) {
            this.K = colorStateListY2;
            this.M = true;
        }
        PorterDuff.Mode modeC2 = op.c(typedArray.getInt(R$styleable.SwitchCompat_trackTintMode, -1), null);
        if (this.L != modeC2) {
            this.L = modeC2;
            this.N = true;
        }
        if (this.M || this.N) {
            b();
        }
        int resourceId2 = typedArray.getResourceId(R$styleable.SwitchCompat_switchTextAppearance, 0);
        if (resourceId2 != 0) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(resourceId2, R$styleable.TextAppearance);
            int i3 = R$styleable.TextAppearance_android_textColor;
            ColorStateList colorStateList = (!typedArrayObtainStyledAttributes.hasValue(i3) || (resourceId = typedArrayObtainStyledAttributes.getResourceId(i3, 0)) == 0 || (colorStateList = t40.Y(context, resourceId)) == null) ? typedArrayObtainStyledAttributes.getColorStateList(i3) : colorStateList;
            if (colorStateList != null) {
                this.q0 = colorStateList;
            } else {
                this.q0 = getTextColors();
            }
            int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.TextAppearance_android_textSize, 0);
            if (dimensionPixelSize != 0) {
                float f = dimensionPixelSize;
                if (f != textPaint.getTextSize()) {
                    textPaint.setTextSize(f);
                    requestLayout();
                }
            }
            int i4 = typedArrayObtainStyledAttributes.getInt(R$styleable.TextAppearance_android_typeface, -1);
            int i5 = typedArrayObtainStyledAttributes.getInt(R$styleable.TextAppearance_android_textStyle, -1);
            if (i4 == 1) {
                typeface = Typeface.SANS_SERIF;
            } else if (i4 != 2) {
                typeface = i4 != 3 ? null : Typeface.MONOSPACE;
            } else {
                typeface = Typeface.SERIF;
            }
            if (i5 > 0) {
                if (typeface == null) {
                    typefaceCreate = Typeface.defaultFromStyle(i5);
                } else {
                    typefaceCreate = Typeface.create(typeface, i5);
                }
                setSwitchTypeface(typefaceCreate);
                int i6 = (~(typefaceCreate != null ? typefaceCreate.getStyle() : 0)) & i5;
                textPaint.setFakeBoldText((i6 & 1) != 0);
                textPaint.setTextSkewX((i6 & 2) != 0 ? -0.25f : 0.0f);
            } else {
                textPaint.setFakeBoldText(false);
                textPaint.setTextSkewX(0.0f);
                setSwitchTypeface(typeface);
            }
            if (typedArrayObtainStyledAttributes.getBoolean(R$styleable.TextAppearance_textAllCaps, false)) {
                Context context2 = getContext();
                m4 m4Var = new m4();
                m4Var.a = context2.getResources().getConfiguration().locale;
                this.t0 = m4Var;
            } else {
                this.t0 = null;
            }
            setTextOnInternal(this.S);
            setTextOffInternal(this.U);
            typedArrayObtainStyledAttributes.recycle();
        }
        new c7(this).f(attributeSet, i);
        ouVarP.S();
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.b0 = viewConfiguration.getScaledTouchSlop();
        this.f0 = viewConfiguration.getScaledMinimumFlingVelocity();
        getEmojiTextViewHelper().b(attributeSet, i);
        refreshDrawableState();
        setChecked(isChecked());
    }
}
