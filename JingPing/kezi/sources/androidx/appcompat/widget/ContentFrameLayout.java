package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;
import defpackage.bt0;
import defpackage.i60;
import defpackage.k1;
import defpackage.m4;
import defpackage.rg;
import defpackage.x4;
import defpackage.yi;

/* JADX INFO: loaded from: classes.dex */
public class ContentFrameLayout extends FrameLayout {
    public TypedValue b;
    public TypedValue c;
    public TypedValue d;
    public TypedValue e;
    public TypedValue f;
    public TypedValue g;
    public final Rect h;
    public rg i;

    public ContentFrameLayout(Context context) {
        this(context, null);
    }

    public TypedValue getFixedHeightMajor() {
        if (this.f == null) {
            this.f = new TypedValue();
        }
        return this.f;
    }

    public TypedValue getFixedHeightMinor() {
        if (this.g == null) {
            this.g = new TypedValue();
        }
        return this.g;
    }

    public TypedValue getFixedWidthMajor() {
        if (this.d == null) {
            this.d = new TypedValue();
        }
        return this.d;
    }

    public TypedValue getFixedWidthMinor() {
        if (this.e == null) {
            this.e = new TypedValue();
        }
        return this.e;
    }

    public TypedValue getMinWidthMajor() {
        if (this.b == null) {
            this.b = new TypedValue();
        }
        return this.b;
    }

    public TypedValue getMinWidthMinor() {
        if (this.c == null) {
            this.c = new TypedValue();
        }
        return this.c;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        rg rgVar = this.i;
        if (rgVar != null) {
            rgVar.getClass();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        b bVar;
        super.onDetachedFromWindow();
        rg rgVar = this.i;
        if (rgVar != null) {
            x4 x4Var = ((m4) rgVar).c;
            yi yiVar = x4Var.s;
            if (yiVar != null) {
                ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) yiVar;
                actionBarOverlayLayout.k();
                ActionMenuView actionMenuView = ((l) actionBarOverlayLayout.f).a.b;
                if (actionMenuView != null && (bVar = actionMenuView.u) != null) {
                    bVar.f();
                    k1 k1Var = bVar.v;
                    if (k1Var != null && k1Var.b()) {
                        k1Var.i.dismiss();
                    }
                }
            }
            if (x4Var.x != null) {
                x4Var.m.getDecorView().removeCallbacks(x4Var.y);
                if (x4Var.x.isShowing()) {
                    try {
                        x4Var.x.dismiss();
                    } catch (IllegalArgumentException unused) {
                    }
                }
                x4Var.x = null;
            }
            bt0 bt0Var = x4Var.z;
            if (bt0Var != null) {
                bt0Var.b();
            }
            i60 i60Var = x4Var.z(0).h;
            if (i60Var != null) {
                i60Var.c(true);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00de  */
    @Override // android.widget.FrameLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onMeasure(int r17, int r18) {
        /*
            Method dump skipped, instruction units count: 229
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ContentFrameLayout.onMeasure(int, int):void");
    }

    public void setAttachListener(rg rgVar) {
        this.i = rgVar;
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.h = new Rect();
    }
}
