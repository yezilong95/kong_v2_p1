package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;
import defpackage.c6;
import defpackage.f3;
import defpackage.gi;
import defpackage.h61;
import defpackage.r5;
import defpackage.rm;
import defpackage.zd0;

/* JADX INFO: loaded from: classes.dex */
public class ContentFrameLayout extends FrameLayout {
    public TypedValue E;
    public TypedValue F;
    public TypedValue G;
    public TypedValue H;
    public TypedValue I;
    public TypedValue J;
    public final Rect K;
    public gi L;

    public ContentFrameLayout(Context context) {
        this(context, null);
    }

    public TypedValue getFixedHeightMajor() {
        if (this.I == null) {
            this.I = new TypedValue();
        }
        return this.I;
    }

    public TypedValue getFixedHeightMinor() {
        if (this.J == null) {
            this.J = new TypedValue();
        }
        return this.J;
    }

    public TypedValue getFixedWidthMajor() {
        if (this.G == null) {
            this.G = new TypedValue();
        }
        return this.G;
    }

    public TypedValue getFixedWidthMinor() {
        if (this.H == null) {
            this.H = new TypedValue();
        }
        return this.H;
    }

    public TypedValue getMinWidthMajor() {
        if (this.E == null) {
            this.E = new TypedValue();
        }
        return this.E;
    }

    public TypedValue getMinWidthMinor() {
        if (this.F == null) {
            this.F = new TypedValue();
        }
        return this.F;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        gi giVar = this.L;
        if (giVar != null) {
            giVar.getClass();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        b bVar;
        super.onDetachedFromWindow();
        gi giVar = this.L;
        if (giVar != null) {
            c6 c6Var = ((r5) giVar).E;
            rm rmVar = c6Var.V;
            if (rmVar != null) {
                ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) rmVar;
                actionBarOverlayLayout.k();
                ActionMenuView actionMenuView = ((l) actionBarOverlayLayout.I).a.E;
                if (actionMenuView != null && (bVar = actionMenuView.a0) != null) {
                    bVar.d();
                    f3 f3Var = bVar.Y;
                    if (f3Var != null && f3Var.b()) {
                        f3Var.i.dismiss();
                    }
                }
            }
            if (c6Var.a0 != null) {
                c6Var.P.getDecorView().removeCallbacks(c6Var.b0);
                if (c6Var.a0.isShowing()) {
                    try {
                        c6Var.a0.dismiss();
                    } catch (IllegalArgumentException unused) {
                    }
                }
                c6Var.a0 = null;
            }
            h61 h61Var = c6Var.c0;
            if (h61Var != null) {
                h61Var.b();
            }
            zd0 zd0Var = c6Var.z(0).h;
            if (zd0Var != null) {
                zd0Var.c(true);
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
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onMeasure(int r17, int r18) {
        /*
            Method dump skipped, instruction units count: 229
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ContentFrameLayout.onMeasure(int, int):void");
    }

    public void setAttachListener(gi giVar) {
        this.L = giVar;
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.K = new Rect();
    }
}
