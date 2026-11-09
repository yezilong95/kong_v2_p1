package com.google.android.material.snackbar;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$id;
import defpackage.f51;
import defpackage.s90;
import defpackage.y4;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class SnackbarContentLayout extends LinearLayout {
    public TextView E;
    public Button F;
    public int G;

    public SnackbarContentLayout(Context context) {
        this(context, null);
    }

    public final boolean a(int i, int i2, int i3) {
        boolean z;
        if (i != getOrientation()) {
            setOrientation(i);
            z = true;
        } else {
            z = false;
        }
        if (this.E.getPaddingTop() == i2 && this.E.getPaddingBottom() == i3) {
            return z;
        }
        TextView textView = this.E;
        WeakHashMap weakHashMap = f51.a;
        if (textView.isPaddingRelative()) {
            textView.setPaddingRelative(textView.getPaddingStart(), i2, textView.getPaddingEnd(), i3);
            return true;
        }
        textView.setPadding(textView.getPaddingLeft(), i2, textView.getPaddingRight(), i3);
        return true;
    }

    public Button getActionView() {
        return this.F;
    }

    public TextView getMessageView() {
        return this.E;
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.E = (TextView) findViewById(R$id.snackbar_text);
        this.F = (Button) findViewById(R$id.snackbar_action);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (getOrientation() == 1) {
            return;
        }
        int dimensionPixelSize = getResources().getDimensionPixelSize(R$dimen.design_snackbar_padding_vertical_2lines);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R$dimen.design_snackbar_padding_vertical);
        Layout layout = this.E.getLayout();
        boolean z = layout != null && layout.getLineCount() > 1;
        if (!z || this.G <= 0 || this.F.getMeasuredWidth() <= this.G) {
            if (!z) {
                dimensionPixelSize = dimensionPixelSize2;
            }
            if (!a(0, dimensionPixelSize, dimensionPixelSize)) {
                return;
            }
        } else if (!a(1, dimensionPixelSize, dimensionPixelSize - dimensionPixelSize2)) {
            return;
        }
        super.onMeasure(i, i2);
    }

    public void setMaxInlineActionWidth(int i) {
        this.G = i;
    }

    public SnackbarContentLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        s90.V(context, R$attr.motionEasingEmphasizedInterpolator, y4.b);
    }
}
