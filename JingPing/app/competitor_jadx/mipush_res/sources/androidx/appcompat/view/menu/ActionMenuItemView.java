package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.R$styleable;
import androidx.appcompat.widget.AppCompatTextView;
import defpackage.d3;
import defpackage.de0;
import defpackage.e3;
import defpackage.j3;
import defpackage.la0;
import defpackage.re0;
import defpackage.yd0;
import defpackage.zd0;

/* JADX INFO: loaded from: classes.dex */
public class ActionMenuItemView extends AppCompatTextView implements re0, View.OnClickListener, j3 {
    public de0 L;
    public CharSequence M;
    public Drawable N;
    public yd0 O;
    public d3 P;
    public e3 Q;
    public boolean R;
    public boolean S;
    public final int T;
    public int U;
    public final int V;

    public ActionMenuItemView(Context context) {
        this(context, null);
    }

    @Override // defpackage.j3
    public final boolean a() {
        return !TextUtils.isEmpty(getText());
    }

    @Override // defpackage.j3
    public final boolean b() {
        return !TextUtils.isEmpty(getText()) && this.L.getIcon() == null;
    }

    @Override // defpackage.re0
    public final void d(de0 de0Var) {
        this.L = de0Var;
        setIcon(de0Var.getIcon());
        setTitle(de0Var.getTitleCondensed());
        setId(de0Var.a);
        setVisibility(de0Var.isVisible() ? 0 : 8);
        setEnabled(de0Var.isEnabled());
        if (de0Var.hasSubMenu() && this.P == null) {
            this.P = new d3(this);
        }
    }

    public final boolean g() {
        Configuration configuration = getContext().getResources().getConfiguration();
        int i = configuration.screenWidthDp;
        int i2 = configuration.screenHeightDp;
        if (i < 480) {
            return (i >= 640 && i2 >= 480) || configuration.orientation == 2;
        }
        return true;
    }

    @Override // android.widget.TextView, android.view.View
    public CharSequence getAccessibilityClassName() {
        return Button.class.getName();
    }

    @Override // defpackage.re0
    public de0 getItemData() {
        return this.L;
    }

    public final void h() {
        boolean z = true;
        boolean z2 = !TextUtils.isEmpty(this.M);
        if (this.N != null && ((this.L.y & 4) != 4 || (!this.R && !this.S))) {
            z = false;
        }
        boolean z3 = z2 & z;
        setText(z3 ? this.M : null);
        CharSequence charSequence = this.L.q;
        if (TextUtils.isEmpty(charSequence)) {
            setContentDescription(z3 ? null : this.L.e);
        } else {
            setContentDescription(charSequence);
        }
        CharSequence charSequence2 = this.L.r;
        if (TextUtils.isEmpty(charSequence2)) {
            la0.e0(this, z3 ? null : this.L.e);
        } else {
            la0.e0(this, charSequence2);
        }
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        yd0 yd0Var = this.O;
        if (yd0Var != null) {
            yd0Var.a(this.L);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.R = g();
        h();
    }

    @Override // androidx.appcompat.widget.AppCompatTextView, android.widget.TextView, android.view.View
    public final void onMeasure(int i, int i2) {
        int i3;
        boolean zIsEmpty = TextUtils.isEmpty(getText());
        if (!zIsEmpty && (i3 = this.U) >= 0) {
            super.setPadding(i3, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int measuredWidth = getMeasuredWidth();
        int i4 = this.T;
        int iMin = mode == Integer.MIN_VALUE ? Math.min(size, i4) : i4;
        if (mode != 1073741824 && i4 > 0 && measuredWidth < iMin) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(iMin, 1073741824), i2);
        }
        if (!zIsEmpty || this.N == null) {
            return;
        }
        super.setPadding((getMeasuredWidth() - this.N.getBounds().width()) / 2, getPaddingTop(), getPaddingRight(), getPaddingBottom());
    }

    @Override // android.widget.TextView, android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(null);
    }

    @Override // android.widget.TextView, android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        d3 d3Var;
        if (this.L.hasSubMenu() && (d3Var = this.P) != null && d3Var.onTouch(this, motionEvent)) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setCheckable(boolean z) {
    }

    public void setChecked(boolean z) {
    }

    public void setExpandedFormat(boolean z) {
        if (this.S != z) {
            this.S = z;
            de0 de0Var = this.L;
            if (de0Var != null) {
                zd0 zd0Var = de0Var.n;
                zd0Var.k = true;
                zd0Var.p(true);
            }
        }
    }

    public void setIcon(Drawable drawable) {
        this.N = drawable;
        if (drawable != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            int i = this.V;
            if (intrinsicWidth > i) {
                intrinsicHeight = (int) (intrinsicHeight * (i / intrinsicWidth));
                intrinsicWidth = i;
            }
            if (intrinsicHeight > i) {
                intrinsicWidth = (int) (intrinsicWidth * (i / intrinsicHeight));
            } else {
                i = intrinsicHeight;
            }
            drawable.setBounds(0, 0, intrinsicWidth, i);
        }
        setCompoundDrawables(drawable, null, null, null);
        h();
    }

    public void setItemInvoker(yd0 yd0Var) {
        this.O = yd0Var;
    }

    @Override // android.widget.TextView, android.view.View
    public final void setPadding(int i, int i2, int i3, int i4) {
        this.U = i;
        super.setPadding(i, i2, i3, i4);
    }

    public void setPopupCallback(e3 e3Var) {
        this.Q = e3Var;
    }

    public void setTitle(CharSequence charSequence) {
        this.M = charSequence;
        h();
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Resources resources = context.getResources();
        this.R = g();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ActionMenuItemView, i, 0);
        this.T = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ActionMenuItemView_android_minWidth, 0);
        typedArrayObtainStyledAttributes.recycle();
        this.V = (int) ((resources.getDisplayMetrics().density * 32.0f) + 0.5f);
        setOnClickListener(this);
        this.U = -1;
        setSaveEnabled(false);
    }
}
