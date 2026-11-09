package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.PopupWindow;
import androidx.appcompat.R$styleable;
import defpackage.po;
import defpackage.xb0;

/* JADX INFO: loaded from: classes.dex */
class AppCompatPopupWindow extends PopupWindow {
    public AppCompatPopupWindow(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet, i);
    }

    public final void a(Context context, AttributeSet attributeSet, int i) {
        po poVarN = po.N(context, attributeSet, R$styleable.PopupWindow, i);
        int i2 = R$styleable.PopupWindow_overlapAnchor;
        TypedArray typedArray = (TypedArray) poVarN.d;
        if (typedArray.hasValue(i2)) {
            xb0.c(this, typedArray.getBoolean(R$styleable.PopupWindow_overlapAnchor, false));
        }
        setBackgroundDrawable(poVarN.y(R$styleable.PopupWindow_android_popupBackground));
        poVarN.P();
    }
}
