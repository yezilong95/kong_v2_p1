package defpackage;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.ListPopupWindow;

/* JADX INFO: loaded from: classes.dex */
public final class t6 extends ListPopupWindow implements u6 {
    public CharSequence g0;
    public q6 h0;
    public final Rect i0;
    public int j0;
    public final /* synthetic */ AppCompatSpinner k0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public t6(AppCompatSpinner appCompatSpinner, Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.k0 = appCompatSpinner;
        this.i0 = new Rect();
        this.S = appCompatSpinner;
        this.c0 = true;
        this.d0.setFocusable(true);
        setOnItemClickListener(new r6(0, this));
    }

    @Override // defpackage.u6
    public final void f(CharSequence charSequence) {
        this.g0 = charSequence;
    }

    @Override // defpackage.u6
    public final void l(int i) {
        this.j0 = i;
    }

    @Override // defpackage.u6
    public final void n(int i, int i2) {
        ViewTreeObserver viewTreeObserver;
        PopupWindow popupWindow = this.d0;
        boolean zIsShowing = popupWindow.isShowing();
        s();
        this.d0.setInputMethodMode(2);
        d();
        gq gqVar = this.G;
        gqVar.setChoiceMode(1);
        n6.d(gqVar, i);
        n6.c(gqVar, i2);
        AppCompatSpinner appCompatSpinner = this.k0;
        int selectedItemPosition = appCompatSpinner.getSelectedItemPosition();
        gq gqVar2 = this.G;
        if (popupWindow.isShowing() && gqVar2 != null) {
            gqVar2.setListSelectionHidden(false);
            gqVar2.setSelection(selectedItemPosition);
            if (gqVar2.getChoiceMode() != 0) {
                gqVar2.setItemChecked(selectedItemPosition, true);
            }
        }
        if (zIsShowing || (viewTreeObserver = appCompatSpinner.getViewTreeObserver()) == null) {
            return;
        }
        q3 q3Var = new q3(2, this);
        viewTreeObserver.addOnGlobalLayoutListener(q3Var);
        setOnDismissListener(new s6(this, q3Var));
    }

    @Override // defpackage.u6
    public final CharSequence p() {
        return this.g0;
    }

    @Override // androidx.appcompat.widget.ListPopupWindow, defpackage.u6
    public final void q(ListAdapter listAdapter) {
        super.q(listAdapter);
        this.h0 = (q6) listAdapter;
    }

    public final void s() {
        int i;
        PopupWindow popupWindow = this.d0;
        Drawable background = popupWindow.getBackground();
        AppCompatSpinner appCompatSpinner = this.k0;
        Rect rect = appCompatSpinner.L;
        if (background != null) {
            background.getPadding(rect);
            i = q61.a(appCompatSpinner) ? rect.right : -rect.left;
        } else {
            i = 0;
            rect.right = 0;
            rect.left = 0;
        }
        int paddingLeft = appCompatSpinner.getPaddingLeft();
        int paddingRight = appCompatSpinner.getPaddingRight();
        int width = appCompatSpinner.getWidth();
        int i2 = appCompatSpinner.K;
        if (i2 == -2) {
            int iA = appCompatSpinner.a(this.h0, popupWindow.getBackground());
            int i3 = (appCompatSpinner.getContext().getResources().getDisplayMetrics().widthPixels - rect.left) - rect.right;
            if (iA > i3) {
                iA = i3;
            }
            r(Math.max(iA, (width - paddingLeft) - paddingRight));
        } else if (i2 == -1) {
            r((width - paddingLeft) - paddingRight);
        } else {
            r(i2);
        }
        this.J = q61.a(appCompatSpinner) ? (((width - paddingRight) - this.I) - this.j0) + i : paddingLeft + this.j0 + i;
    }
}
