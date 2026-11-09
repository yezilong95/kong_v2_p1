package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.ThemedSpinnerAdapter;
import androidx.appcompat.R$attr;
import defpackage.i5;
import defpackage.l6;
import defpackage.n6;
import defpackage.o6;
import defpackage.q3;
import defpackage.q6;
import defpackage.qm;
import defpackage.u6;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatSpinner extends Spinner {
    public static final int[] M = {R.attr.spinnerMode};
    public final i5 E;
    public final Context F;
    public final l6 G;
    public SpinnerAdapter H;
    public final boolean I;
    public final u6 J;
    public int K;
    public final Rect L;

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new c();
        public boolean E;

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeByte(this.E ? (byte) 1 : (byte) 0);
        }
    }

    public AppCompatSpinner(Context context) {
        this(context, null);
    }

    public final int a(SpinnerAdapter spinnerAdapter, Drawable drawable) {
        int i = 0;
        if (spinnerAdapter == null) {
            return 0;
        }
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
        int iMax = Math.max(0, getSelectedItemPosition());
        int iMin = Math.min(spinnerAdapter.getCount(), iMax + 15);
        View view = null;
        int iMax2 = 0;
        for (int iMax3 = Math.max(0, iMax - (15 - (iMin - iMax))); iMax3 < iMin; iMax3++) {
            int itemViewType = spinnerAdapter.getItemViewType(iMax3);
            if (itemViewType != i) {
                view = null;
                i = itemViewType;
            }
            view = spinnerAdapter.getView(iMax3, view, this);
            if (view.getLayoutParams() == null) {
                view.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            }
            view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
            iMax2 = Math.max(iMax2, view.getMeasuredWidth());
        }
        if (drawable == null) {
            return iMax2;
        }
        Rect rect = this.L;
        drawable.getPadding(rect);
        return rect.left + rect.right + iMax2;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        i5 i5Var = this.E;
        if (i5Var != null) {
            i5Var.d();
        }
    }

    @Override // android.widget.Spinner
    public int getDropDownHorizontalOffset() {
        u6 u6Var = this.J;
        return u6Var != null ? u6Var.c() : super.getDropDownHorizontalOffset();
    }

    @Override // android.widget.Spinner
    public int getDropDownVerticalOffset() {
        u6 u6Var = this.J;
        return u6Var != null ? u6Var.o() : super.getDropDownVerticalOffset();
    }

    @Override // android.widget.Spinner
    public int getDropDownWidth() {
        return this.J != null ? this.K : super.getDropDownWidth();
    }

    public final u6 getInternalPopup() {
        return this.J;
    }

    @Override // android.widget.Spinner
    public Drawable getPopupBackground() {
        u6 u6Var = this.J;
        return u6Var != null ? u6Var.e() : super.getPopupBackground();
    }

    @Override // android.widget.Spinner
    public Context getPopupContext() {
        return this.F;
    }

    @Override // android.widget.Spinner
    public CharSequence getPrompt() {
        u6 u6Var = this.J;
        return u6Var != null ? u6Var.p() : super.getPrompt();
    }

    public ColorStateList getSupportBackgroundTintList() {
        i5 i5Var = this.E;
        if (i5Var != null) {
            return i5Var.f();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        i5 i5Var = this.E;
        if (i5Var != null) {
            return i5Var.g();
        }
        return null;
    }

    @Override // android.widget.Spinner, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        u6 u6Var = this.J;
        if (u6Var == null || !u6Var.b()) {
            return;
        }
        u6Var.dismiss();
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.J == null || View.MeasureSpec.getMode(i) != Integer.MIN_VALUE) {
            return;
        }
        setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), a(getAdapter(), getBackground())), View.MeasureSpec.getSize(i)), getMeasuredHeight());
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        ViewTreeObserver viewTreeObserver;
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        if (!savedState.E || (viewTreeObserver = getViewTreeObserver()) == null) {
            return;
        }
        viewTreeObserver.addOnGlobalLayoutListener(new q3(1, this));
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        u6 u6Var = this.J;
        savedState.E = u6Var != null && u6Var.b();
        return savedState;
    }

    @Override // android.widget.Spinner, android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        l6 l6Var = this.G;
        if (l6Var == null || !l6Var.onTouch(this, motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    @Override // android.widget.Spinner, android.view.View
    public final boolean performClick() {
        u6 u6Var = this.J;
        if (u6Var == null) {
            return super.performClick();
        }
        if (u6Var.b()) {
            return true;
        }
        this.J.n(n6.b(this), n6.a(this));
        return true;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        i5 i5Var = this.E;
        if (i5Var != null) {
            i5Var.i();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        i5 i5Var = this.E;
        if (i5Var != null) {
            i5Var.j(i);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownHorizontalOffset(int i) {
        u6 u6Var = this.J;
        if (u6Var == null) {
            super.setDropDownHorizontalOffset(i);
        } else {
            u6Var.l(i);
            u6Var.m(i);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownVerticalOffset(int i) {
        u6 u6Var = this.J;
        if (u6Var != null) {
            u6Var.k(i);
        } else {
            super.setDropDownVerticalOffset(i);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownWidth(int i) {
        if (this.J != null) {
            this.K = i;
        } else {
            super.setDropDownWidth(i);
        }
    }

    @Override // android.widget.Spinner
    public void setPopupBackgroundDrawable(Drawable drawable) {
        u6 u6Var = this.J;
        if (u6Var != null) {
            u6Var.g(drawable);
        } else {
            super.setPopupBackgroundDrawable(drawable);
        }
    }

    @Override // android.widget.Spinner
    public void setPopupBackgroundResource(int i) {
        setPopupBackgroundDrawable(qm.z(getPopupContext(), i));
    }

    @Override // android.widget.Spinner
    public void setPrompt(CharSequence charSequence) {
        u6 u6Var = this.J;
        if (u6Var != null) {
            u6Var.f(charSequence);
        } else {
            super.setPrompt(charSequence);
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        i5 i5Var = this.E;
        if (i5Var != null) {
            i5Var.l(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        i5 i5Var = this.E;
        if (i5Var != null) {
            i5Var.m(mode);
        }
    }

    public AppCompatSpinner(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.spinnerStyle);
    }

    @Override // android.widget.AdapterView
    public void setAdapter(SpinnerAdapter spinnerAdapter) {
        if (!this.I) {
            this.H = spinnerAdapter;
            return;
        }
        super.setAdapter(spinnerAdapter);
        u6 u6Var = this.J;
        if (u6Var != null) {
            Context context = this.F;
            if (context == null) {
                context = getContext();
            }
            Resources.Theme theme = context.getTheme();
            q6 q6Var = new q6();
            q6Var.E = spinnerAdapter;
            if (spinnerAdapter instanceof ListAdapter) {
                q6Var.F = (ListAdapter) spinnerAdapter;
            }
            if (theme != null && (spinnerAdapter instanceof ThemedSpinnerAdapter)) {
                o6.a((ThemedSpinnerAdapter) spinnerAdapter, theme);
            }
            u6Var.q(q6Var);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00de  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public AppCompatSpinner(android.content.Context r10, android.util.AttributeSet r11, int r12) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 226
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.AppCompatSpinner.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }
}
