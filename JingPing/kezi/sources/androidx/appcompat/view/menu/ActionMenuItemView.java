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
import defpackage.a70;
import defpackage.h60;
import defpackage.i1;
import defpackage.i60;
import defpackage.j1;
import defpackage.jq0;
import defpackage.m60;
import defpackage.o1;

/* JADX INFO: loaded from: classes.dex */
public class ActionMenuItemView extends AppCompatTextView implements a70, View.OnClickListener, o1 {
    public m60 i;
    public CharSequence j;
    public Drawable k;
    public h60 l;
    public i1 m;
    public j1 n;
    public boolean o;
    public boolean p;
    public final int q;
    public int r;
    public final int s;

    public ActionMenuItemView(Context context) {
        this(context, null);
    }

    @Override // defpackage.o1
    public final boolean a() {
        return !TextUtils.isEmpty(getText());
    }

    @Override // defpackage.o1
    public final boolean b() {
        return !TextUtils.isEmpty(getText()) && this.i.getIcon() == null;
    }

    @Override // defpackage.a70
    public final void d(m60 m60Var) {
        this.i = m60Var;
        setIcon(m60Var.getIcon());
        setTitle(m60Var.getTitleCondensed());
        setId(m60Var.a);
        setVisibility(m60Var.isVisible() ? 0 : 8);
        setEnabled(m60Var.isEnabled());
        if (m60Var.hasSubMenu() && this.m == null) {
            this.m = new i1(this);
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

    @Override // defpackage.a70
    public m60 getItemData() {
        return this.i;
    }

    public final void h() {
        boolean z = true;
        boolean z2 = !TextUtils.isEmpty(this.j);
        if (this.k != null && ((this.i.y & 4) != 4 || (!this.o && !this.p))) {
            z = false;
        }
        boolean z3 = z2 & z;
        setText(z3 ? this.j : null);
        CharSequence charSequence = this.i.q;
        if (TextUtils.isEmpty(charSequence)) {
            setContentDescription(z3 ? null : this.i.e);
        } else {
            setContentDescription(charSequence);
        }
        CharSequence charSequence2 = this.i.r;
        if (TextUtils.isEmpty(charSequence2)) {
            jq0.d0(this, z3 ? null : this.i.e);
        } else {
            jq0.d0(this, charSequence2);
        }
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        h60 h60Var = this.l;
        if (h60Var != null) {
            h60Var.a(this.i);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.o = g();
        h();
    }

    @Override // androidx.appcompat.widget.AppCompatTextView, android.widget.TextView, android.view.View
    public final void onMeasure(int i, int i2) {
        int i3;
        boolean zIsEmpty = TextUtils.isEmpty(getText());
        if (!zIsEmpty && (i3 = this.r) >= 0) {
            super.setPadding(i3, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int measuredWidth = getMeasuredWidth();
        int i4 = this.q;
        int iMin = mode == Integer.MIN_VALUE ? Math.min(size, i4) : i4;
        if (mode != 1073741824 && i4 > 0 && measuredWidth < iMin) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(iMin, 1073741824), i2);
        }
        if (!zIsEmpty || this.k == null) {
            return;
        }
        super.setPadding((getMeasuredWidth() - this.k.getBounds().width()) / 2, getPaddingTop(), getPaddingRight(), getPaddingBottom());
    }

    @Override // android.widget.TextView, android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(null);
    }

    @Override // android.widget.TextView, android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        i1 i1Var;
        if (this.i.hasSubMenu() && (i1Var = this.m) != null && i1Var.onTouch(this, motionEvent)) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setCheckable(boolean z) {
    }

    public void setChecked(boolean z) {
    }

    public void setExpandedFormat(boolean z) {
        if (this.p != z) {
            this.p = z;
            m60 m60Var = this.i;
            if (m60Var != null) {
                i60 i60Var = m60Var.n;
                i60Var.k = true;
                i60Var.p(true);
            }
        }
    }

    public void setIcon(Drawable drawable) {
        this.k = drawable;
        if (drawable != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            int i = this.s;
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

    public void setItemInvoker(h60 h60Var) {
        this.l = h60Var;
    }

    @Override // android.widget.TextView, android.view.View
    public final void setPadding(int i, int i2, int i3, int i4) {
        this.r = i;
        super.setPadding(i, i2, i3, i4);
    }

    public void setPopupCallback(j1 j1Var) {
        this.n = j1Var;
    }

    public void setTitle(CharSequence charSequence) {
        this.j = charSequence;
        h();
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Resources resources = context.getResources();
        this.o = g();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ActionMenuItemView, i, 0);
        this.q = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ActionMenuItemView_android_minWidth, 0);
        typedArrayObtainStyledAttributes.recycle();
        this.s = (int) ((resources.getDisplayMetrics().density * 32.0f) + 0.5f);
        setOnClickListener(this);
        this.r = -1;
        setSaveEnabled(false);
    }
}
