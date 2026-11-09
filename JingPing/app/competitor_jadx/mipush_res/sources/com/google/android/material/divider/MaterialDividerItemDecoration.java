package com.google.android.material.divider;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.g;
import androidx.recyclerview.widget.h;
import androidx.recyclerview.widget.n;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import defpackage.fa0;
import defpackage.i01;
import defpackage.ip;
import defpackage.la0;
import defpackage.zn;

/* JADX INFO: loaded from: classes.dex */
public class MaterialDividerItemDecoration extends h {
    public static final int i = R$style.Widget_MaterialComponents_MaterialDivider;
    public final ShapeDrawable a;
    public final int b;
    public final int c;
    public final int d;
    public final int e;
    public final int f;
    public final boolean g;
    public final Rect h;

    public MaterialDividerItemDecoration(Context context, AttributeSet attributeSet, int i2) {
        int i3 = R$attr.materialDividerStyle;
        this.h = new Rect();
        TypedArray typedArrayD = i01.d(context, attributeSet, R$styleable.MaterialDivider, i3, i, new int[0]);
        this.c = la0.p(context, typedArrayD, R$styleable.MaterialDivider_dividerColor).getDefaultColor();
        this.b = typedArrayD.getDimensionPixelSize(R$styleable.MaterialDivider_dividerThickness, context.getResources().getDimensionPixelSize(R$dimen.material_divider_thickness));
        this.e = typedArrayD.getDimensionPixelOffset(R$styleable.MaterialDivider_dividerInsetStart, 0);
        this.f = typedArrayD.getDimensionPixelOffset(R$styleable.MaterialDivider_dividerInsetEnd, 0);
        this.g = typedArrayD.getBoolean(R$styleable.MaterialDivider_lastItemDecorated, true);
        typedArrayD.recycle();
        ShapeDrawable shapeDrawable = new ShapeDrawable();
        int i4 = this.c;
        this.c = i4;
        this.a = shapeDrawable;
        ip.g(shapeDrawable, i4);
        if (i2 != 0 && i2 != 1) {
            throw new IllegalArgumentException(zn.k(i2, "Invalid orientation: ", ". It should be either HORIZONTAL or VERTICAL"));
        }
        this.d = i2;
    }

    @Override // androidx.recyclerview.widget.h
    public final void a(Rect rect, View view, RecyclerView recyclerView) {
        rect.set(0, 0, 0, 0);
        if (d(recyclerView, view)) {
            int i2 = this.d;
            int i3 = this.b;
            if (i2 == 1) {
                rect.bottom = i3;
            } else if (fa0.d0(recyclerView)) {
                rect.left = i3;
            } else {
                rect.right = i3;
            }
        }
    }

    @Override // androidx.recyclerview.widget.h
    public final void b(Canvas canvas, RecyclerView recyclerView) {
        int height;
        int paddingTop;
        int i2;
        int i3;
        int width;
        int paddingLeft;
        if (recyclerView.getLayoutManager() == null) {
            return;
        }
        int i4 = this.d;
        int i5 = this.b;
        int i6 = this.f;
        int i7 = this.e;
        Rect rect = this.h;
        int i8 = 0;
        if (i4 == 1) {
            canvas.save();
            if (recyclerView.getClipToPadding()) {
                paddingLeft = recyclerView.getPaddingLeft();
                width = recyclerView.getWidth() - recyclerView.getPaddingRight();
                canvas.clipRect(paddingLeft, recyclerView.getPaddingTop(), width, recyclerView.getHeight() - recyclerView.getPaddingBottom());
            } else {
                width = recyclerView.getWidth();
                paddingLeft = 0;
            }
            boolean zD0 = fa0.d0(recyclerView);
            int i9 = paddingLeft + (zD0 ? i6 : i7);
            if (zD0) {
                i6 = i7;
            }
            int i10 = width - i6;
            int childCount = recyclerView.getChildCount();
            while (i8 < childCount) {
                View childAt = recyclerView.getChildAt(i8);
                if (d(recyclerView, childAt)) {
                    recyclerView.getLayoutManager().y(rect, childAt);
                    int iRound = Math.round(childAt.getTranslationY()) + rect.bottom;
                    this.a.setBounds(i9, iRound - i5, i10, iRound);
                    this.a.setAlpha(Math.round(childAt.getAlpha() * 255.0f));
                    this.a.draw(canvas);
                }
                i8++;
            }
            canvas.restore();
            return;
        }
        canvas.save();
        if (recyclerView.getClipToPadding()) {
            paddingTop = recyclerView.getPaddingTop();
            height = recyclerView.getHeight() - recyclerView.getPaddingBottom();
            canvas.clipRect(recyclerView.getPaddingLeft(), paddingTop, recyclerView.getWidth() - recyclerView.getPaddingRight(), height);
        } else {
            height = recyclerView.getHeight();
            paddingTop = 0;
        }
        int i11 = paddingTop + i7;
        int i12 = height - i6;
        boolean zD02 = fa0.d0(recyclerView);
        int childCount2 = recyclerView.getChildCount();
        while (i8 < childCount2) {
            View childAt2 = recyclerView.getChildAt(i8);
            if (d(recyclerView, childAt2)) {
                recyclerView.getLayoutManager().y(rect, childAt2);
                int iRound2 = Math.round(childAt2.getTranslationX());
                if (zD02) {
                    i3 = rect.left + iRound2;
                    i2 = i3 + i5;
                } else {
                    i2 = iRound2 + rect.right;
                    i3 = i2 - i5;
                }
                this.a.setBounds(i3, i11, i2, i12);
                this.a.setAlpha(Math.round(childAt2.getAlpha() * 255.0f));
                this.a.draw(canvas);
            }
            i8++;
        }
        canvas.restore();
    }

    public final boolean d(RecyclerView recyclerView, View view) {
        RecyclerView recyclerView2;
        recyclerView.getClass();
        n nVarI = RecyclerView.I(view);
        int iF = (nVarI == null || (recyclerView2 = nVarI.r) == null) ? -1 : recyclerView2.F(nVarI);
        g adapter = recyclerView.getAdapter();
        return iF != -1 && (!(adapter != null && iF == adapter.a() - 1) || this.g);
    }
}
