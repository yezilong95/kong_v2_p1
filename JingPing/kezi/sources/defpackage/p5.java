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
public final class p5 extends ListPopupWindow implements q5 {
    public CharSequence D;
    public m5 E;
    public final Rect F;
    public int G;
    public final /* synthetic */ AppCompatSpinner H;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p5(AppCompatSpinner appCompatSpinner, Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.H = appCompatSpinner;
        this.F = new Rect();
        this.p = appCompatSpinner;
        this.z = true;
        this.A.setFocusable(true);
        setOnItemClickListener(new n5(0, this));
    }

    @Override // defpackage.q5
    public final void e(int i, int i2) {
        ViewTreeObserver viewTreeObserver;
        PopupWindow popupWindow = this.A;
        boolean zIsShowing = popupWindow.isShowing();
        s();
        this.A.setInputMethodMode(2);
        f();
        cm cmVar = this.d;
        cmVar.setChoiceMode(1);
        j5.d(cmVar, i);
        j5.c(cmVar, i2);
        AppCompatSpinner appCompatSpinner = this.H;
        int selectedItemPosition = appCompatSpinner.getSelectedItemPosition();
        cm cmVar2 = this.d;
        if (popupWindow.isShowing() && cmVar2 != null) {
            cmVar2.setListSelectionHidden(false);
            cmVar2.setSelection(selectedItemPosition);
            if (cmVar2.getChoiceMode() != 0) {
                cmVar2.setItemChecked(selectedItemPosition, true);
            }
        }
        if (zIsShowing || (viewTreeObserver = appCompatSpinner.getViewTreeObserver()) == null) {
            return;
        }
        v1 v1Var = new v1(2, this);
        viewTreeObserver.addOnGlobalLayoutListener(v1Var);
        setOnDismissListener(new o5(this, v1Var));
    }

    @Override // defpackage.q5
    public final CharSequence i() {
        return this.D;
    }

    @Override // defpackage.q5
    public final void k(CharSequence charSequence) {
        this.D = charSequence;
    }

    @Override // androidx.appcompat.widget.ListPopupWindow, defpackage.q5
    public final void n(ListAdapter listAdapter) {
        super.n(listAdapter);
        this.E = (m5) listAdapter;
    }

    @Override // defpackage.q5
    public final void p(int i) {
        this.G = i;
    }

    public final void s() {
        int i;
        PopupWindow popupWindow = this.A;
        Drawable background = popupWindow.getBackground();
        AppCompatSpinner appCompatSpinner = this.H;
        Rect rect = appCompatSpinner.i;
        if (background != null) {
            background.getPadding(rect);
            i = kt0.a(appCompatSpinner) ? rect.right : -rect.left;
        } else {
            i = 0;
            rect.right = 0;
            rect.left = 0;
        }
        int paddingLeft = appCompatSpinner.getPaddingLeft();
        int paddingRight = appCompatSpinner.getPaddingRight();
        int width = appCompatSpinner.getWidth();
        int i2 = appCompatSpinner.h;
        if (i2 == -2) {
            int iA = appCompatSpinner.a(this.E, popupWindow.getBackground());
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
        this.g = kt0.a(appCompatSpinner) ? (((width - paddingRight) - this.f) - this.G) + i : paddingLeft + this.G + i;
    }
}
