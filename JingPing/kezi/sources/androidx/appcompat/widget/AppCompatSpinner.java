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
import defpackage.e4;
import defpackage.h5;
import defpackage.j5;
import defpackage.k5;
import defpackage.m5;
import defpackage.q5;
import defpackage.rp0;
import defpackage.v1;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatSpinner extends Spinner {
    public static final int[] j = {R.attr.spinnerMode};
    public final e4 b;
    public final Context c;
    public final h5 d;
    public SpinnerAdapter e;
    public final boolean f;
    public final q5 g;
    public int h;
    public final Rect i;

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new c();
        public boolean b;

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeByte(this.b ? (byte) 1 : (byte) 0);
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
        Rect rect = this.i;
        drawable.getPadding(rect);
        return rect.left + rect.right + iMax2;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        e4 e4Var = this.b;
        if (e4Var != null) {
            e4Var.a();
        }
    }

    @Override // android.widget.Spinner
    public int getDropDownHorizontalOffset() {
        q5 q5Var = this.g;
        return q5Var != null ? q5Var.d() : super.getDropDownHorizontalOffset();
    }

    @Override // android.widget.Spinner
    public int getDropDownVerticalOffset() {
        q5 q5Var = this.g;
        return q5Var != null ? q5Var.g() : super.getDropDownVerticalOffset();
    }

    @Override // android.widget.Spinner
    public int getDropDownWidth() {
        return this.g != null ? this.h : super.getDropDownWidth();
    }

    public final q5 getInternalPopup() {
        return this.g;
    }

    @Override // android.widget.Spinner
    public Drawable getPopupBackground() {
        q5 q5Var = this.g;
        return q5Var != null ? q5Var.h() : super.getPopupBackground();
    }

    @Override // android.widget.Spinner
    public Context getPopupContext() {
        return this.c;
    }

    @Override // android.widget.Spinner
    public CharSequence getPrompt() {
        q5 q5Var = this.g;
        return q5Var != null ? q5Var.i() : super.getPrompt();
    }

    public ColorStateList getSupportBackgroundTintList() {
        e4 e4Var = this.b;
        if (e4Var != null) {
            return e4Var.c();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        e4 e4Var = this.b;
        if (e4Var != null) {
            return e4Var.d();
        }
        return null;
    }

    @Override // android.widget.Spinner, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        q5 q5Var = this.g;
        if (q5Var == null || !q5Var.b()) {
            return;
        }
        q5Var.dismiss();
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.g == null || View.MeasureSpec.getMode(i) != Integer.MIN_VALUE) {
            return;
        }
        setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), a(getAdapter(), getBackground())), View.MeasureSpec.getSize(i)), getMeasuredHeight());
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        ViewTreeObserver viewTreeObserver;
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        if (!savedState.b || (viewTreeObserver = getViewTreeObserver()) == null) {
            return;
        }
        viewTreeObserver.addOnGlobalLayoutListener(new v1(1, this));
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        q5 q5Var = this.g;
        savedState.b = q5Var != null && q5Var.b();
        return savedState;
    }

    @Override // android.widget.Spinner, android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        h5 h5Var = this.d;
        if (h5Var == null || !h5Var.onTouch(this, motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    @Override // android.widget.Spinner, android.view.View
    public final boolean performClick() {
        q5 q5Var = this.g;
        if (q5Var == null) {
            return super.performClick();
        }
        if (q5Var.b()) {
            return true;
        }
        this.g.e(j5.b(this), j5.a(this));
        return true;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        e4 e4Var = this.b;
        if (e4Var != null) {
            e4Var.f();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        e4 e4Var = this.b;
        if (e4Var != null) {
            e4Var.g(i);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownHorizontalOffset(int i) {
        q5 q5Var = this.g;
        if (q5Var == null) {
            super.setDropDownHorizontalOffset(i);
        } else {
            q5Var.p(i);
            q5Var.c(i);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownVerticalOffset(int i) {
        q5 q5Var = this.g;
        if (q5Var != null) {
            q5Var.m(i);
        } else {
            super.setDropDownVerticalOffset(i);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownWidth(int i) {
        if (this.g != null) {
            this.h = i;
        } else {
            super.setDropDownWidth(i);
        }
    }

    @Override // android.widget.Spinner
    public void setPopupBackgroundDrawable(Drawable drawable) {
        q5 q5Var = this.g;
        if (q5Var != null) {
            q5Var.l(drawable);
        } else {
            super.setPopupBackgroundDrawable(drawable);
        }
    }

    @Override // android.widget.Spinner
    public void setPopupBackgroundResource(int i) {
        setPopupBackgroundDrawable(rp0.y(getPopupContext(), i));
    }

    @Override // android.widget.Spinner
    public void setPrompt(CharSequence charSequence) {
        q5 q5Var = this.g;
        if (q5Var != null) {
            q5Var.k(charSequence);
        } else {
            super.setPrompt(charSequence);
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        e4 e4Var = this.b;
        if (e4Var != null) {
            e4Var.i(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        e4 e4Var = this.b;
        if (e4Var != null) {
            e4Var.j(mode);
        }
    }

    public AppCompatSpinner(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.spinnerStyle);
    }

    @Override // android.widget.AdapterView
    public void setAdapter(SpinnerAdapter spinnerAdapter) {
        if (!this.f) {
            this.e = spinnerAdapter;
            return;
        }
        super.setAdapter(spinnerAdapter);
        q5 q5Var = this.g;
        if (q5Var != null) {
            Context context = this.c;
            if (context == null) {
                context = getContext();
            }
            Resources.Theme theme = context.getTheme();
            m5 m5Var = new m5();
            m5Var.b = spinnerAdapter;
            if (spinnerAdapter instanceof ListAdapter) {
                m5Var.c = (ListAdapter) spinnerAdapter;
            }
            if (theme != null && (spinnerAdapter instanceof ThemedSpinnerAdapter)) {
                k5.a((ThemedSpinnerAdapter) spinnerAdapter, theme);
            }
            q5Var.n(m5Var);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00de  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public AppCompatSpinner(android.content.Context r10, android.util.AttributeSet r11, int r12) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 226
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.AppCompatSpinner.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }
}
