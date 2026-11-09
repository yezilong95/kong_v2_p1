package com.google.android.material.appbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.R$attr;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import defpackage.hl;
import defpackage.i60;
import defpackage.l3;
import defpackage.lr0;
import defpackage.oy;
import defpackage.rp0;
import defpackage.u50;
import defpackage.un0;
import defpackage.xr0;
import defpackage.y50;
import java.util.ArrayList;
import java.util.Collections;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class MaterialToolbar extends Toolbar {
    public static final int a0 = R$style.Widget_MaterialComponents_Toolbar;
    public static final ImageView.ScaleType[] b0 = {ImageView.ScaleType.MATRIX, ImageView.ScaleType.FIT_XY, ImageView.ScaleType.FIT_START, ImageView.ScaleType.FIT_CENTER, ImageView.ScaleType.FIT_END, ImageView.ScaleType.CENTER, ImageView.ScaleType.CENTER_CROP, ImageView.ScaleType.CENTER_INSIDE};
    public Integer S;
    public boolean T;
    public boolean U;
    public ImageView.ScaleType V;
    public Boolean W;

    public MaterialToolbar(Context context) {
        this(context, null);
    }

    public ImageView.ScaleType getLogoScaleType() {
        return this.V;
    }

    public Integer getNavigationIconTint() {
        return this.S;
    }

    @Override // androidx.appcompat.widget.Toolbar
    public final void m(int i) {
        Menu menu = getMenu();
        boolean z = menu instanceof i60;
        if (z) {
            ((i60) menu).y();
        }
        super.m(i);
        if (z) {
            ((i60) menu).x();
        }
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        oy.P(this);
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        ImageView imageView;
        Drawable drawable;
        super.onLayout(z, i, i2, i3, i4);
        int i5 = 0;
        ImageView imageView2 = null;
        if (this.T || this.U) {
            ArrayList arrayListD = rp0.D(this, getTitle());
            boolean zIsEmpty = arrayListD.isEmpty();
            l3 l3Var = rp0.d;
            TextView textView = zIsEmpty ? null : (TextView) Collections.min(arrayListD, l3Var);
            ArrayList arrayListD2 = rp0.D(this, getSubtitle());
            TextView textView2 = arrayListD2.isEmpty() ? null : (TextView) Collections.max(arrayListD2, l3Var);
            if (textView != null || textView2 != null) {
                int measuredWidth = getMeasuredWidth();
                int i6 = measuredWidth / 2;
                int paddingLeft = getPaddingLeft();
                int paddingRight = measuredWidth - getPaddingRight();
                for (int i7 = 0; i7 < getChildCount(); i7++) {
                    View childAt = getChildAt(i7);
                    if (childAt.getVisibility() != 8 && childAt != textView && childAt != textView2) {
                        if (childAt.getRight() < i6 && childAt.getRight() > paddingLeft) {
                            paddingLeft = childAt.getRight();
                        }
                        if (childAt.getLeft() > i6 && childAt.getLeft() < paddingRight) {
                            paddingRight = childAt.getLeft();
                        }
                    }
                }
                Pair pair = new Pair(Integer.valueOf(paddingLeft), Integer.valueOf(paddingRight));
                if (this.T && textView != null) {
                    v(textView, pair);
                }
                if (this.U && textView2 != null) {
                    v(textView2, pair);
                }
            }
        }
        Drawable logo = getLogo();
        if (logo != null) {
            while (true) {
                if (i5 >= getChildCount()) {
                    break;
                }
                View childAt2 = getChildAt(i5);
                if ((childAt2 instanceof ImageView) && (drawable = (imageView = (ImageView) childAt2).getDrawable()) != null && drawable.getConstantState() != null && drawable.getConstantState().equals(logo.getConstantState())) {
                    imageView2 = imageView;
                    break;
                }
                i5++;
            }
        }
        if (imageView2 != null) {
            Boolean bool = this.W;
            if (bool != null) {
                imageView2.setAdjustViewBounds(bool.booleanValue());
            }
            ImageView.ScaleType scaleType = this.V;
            if (scaleType != null) {
                imageView2.setScaleType(scaleType);
            }
        }
    }

    @Override // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        oy.N(this, f);
    }

    public void setLogoAdjustViewBounds(boolean z) {
        Boolean bool = this.W;
        if (bool == null || bool.booleanValue() != z) {
            this.W = Boolean.valueOf(z);
            requestLayout();
        }
    }

    public void setLogoScaleType(ImageView.ScaleType scaleType) {
        if (this.V != scaleType) {
            this.V = scaleType;
            requestLayout();
        }
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setNavigationIcon(Drawable drawable) {
        if (drawable != null && this.S != null) {
            drawable = drawable.mutate();
            hl.g(drawable, this.S.intValue());
        }
        super.setNavigationIcon(drawable);
    }

    public void setNavigationIconTint(int i) {
        this.S = Integer.valueOf(i);
        Drawable navigationIcon = getNavigationIcon();
        if (navigationIcon != null) {
            setNavigationIcon(navigationIcon);
        }
    }

    public void setSubtitleCentered(boolean z) {
        if (this.U != z) {
            this.U = z;
            requestLayout();
        }
    }

    public void setTitleCentered(boolean z) {
        if (this.T != z) {
            this.T = z;
            requestLayout();
        }
    }

    public final void v(TextView textView, Pair pair) {
        int measuredWidth = getMeasuredWidth();
        int measuredWidth2 = textView.getMeasuredWidth();
        int i = (measuredWidth / 2) - (measuredWidth2 / 2);
        int i2 = measuredWidth2 + i;
        int iMax = Math.max(Math.max(((Integer) pair.first).intValue() - i, 0), Math.max(i2 - ((Integer) pair.second).intValue(), 0));
        if (iMax > 0) {
            i += iMax;
            i2 -= iMax;
            textView.measure(View.MeasureSpec.makeMeasureSpec(i2 - i, 1073741824), textView.getMeasuredHeightAndState());
        }
        textView.layout(i, textView.getTop(), i2, textView.getBottom());
    }

    public MaterialToolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.toolbarStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialToolbar(Context context, AttributeSet attributeSet, int i) {
        int i2 = a0;
        super(y50.a(context, attributeSet, i, i2), attributeSet, i);
        Context context2 = getContext();
        TypedArray typedArrayD = un0.d(context2, attributeSet, R$styleable.MaterialToolbar, i, i2, new int[0]);
        if (typedArrayD.hasValue(R$styleable.MaterialToolbar_navigationIconTint)) {
            setNavigationIconTint(typedArrayD.getColor(R$styleable.MaterialToolbar_navigationIconTint, -1));
        }
        this.T = typedArrayD.getBoolean(R$styleable.MaterialToolbar_titleCentered, false);
        this.U = typedArrayD.getBoolean(R$styleable.MaterialToolbar_subtitleCentered, false);
        int i3 = typedArrayD.getInt(R$styleable.MaterialToolbar_logoScaleType, -1);
        if (i3 >= 0) {
            ImageView.ScaleType[] scaleTypeArr = b0;
            if (i3 < scaleTypeArr.length) {
                this.V = scaleTypeArr[i3];
            }
        }
        if (typedArrayD.hasValue(R$styleable.MaterialToolbar_logoAdjustViewBounds)) {
            this.W = Boolean.valueOf(typedArrayD.getBoolean(R$styleable.MaterialToolbar_logoAdjustViewBounds, false));
        }
        typedArrayD.recycle();
        Drawable background = getBackground();
        if (background == null || (background instanceof ColorDrawable)) {
            u50 u50Var = new u50();
            u50Var.m(ColorStateList.valueOf(background != null ? ((ColorDrawable) background).getColor() : 0));
            u50Var.k(context2);
            WeakHashMap weakHashMap = xr0.a;
            u50Var.l(lr0.i(this));
            setBackground(u50Var);
        }
    }
}
