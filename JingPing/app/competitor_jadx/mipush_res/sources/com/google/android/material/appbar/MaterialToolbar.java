package com.google.android.material.appbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
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
import defpackage.f51;
import defpackage.i01;
import defpackage.ip;
import defpackage.ld0;
import defpackage.ma0;
import defpackage.pd0;
import defpackage.q4;
import defpackage.qm;
import defpackage.t40;
import defpackage.t41;
import defpackage.zd0;
import java.util.ArrayList;
import java.util.Collections;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class MaterialToolbar extends Toolbar {
    public static final int D0 = R$style.Widget_MaterialComponents_Toolbar;
    public static final ImageView.ScaleType[] E0 = {ImageView.ScaleType.MATRIX, ImageView.ScaleType.FIT_XY, ImageView.ScaleType.FIT_START, ImageView.ScaleType.FIT_CENTER, ImageView.ScaleType.FIT_END, ImageView.ScaleType.CENTER, ImageView.ScaleType.CENTER_CROP, ImageView.ScaleType.CENTER_INSIDE};
    public boolean A0;
    public ImageView.ScaleType B0;
    public Boolean C0;
    public Integer y0;
    public boolean z0;

    public MaterialToolbar(Context context) {
        this(context, null);
    }

    public ImageView.ScaleType getLogoScaleType() {
        return this.B0;
    }

    public Integer getNavigationIconTint() {
        return this.y0;
    }

    @Override // androidx.appcompat.widget.Toolbar
    public final void m(int i) {
        Menu menu = getMenu();
        boolean z = menu instanceof zd0;
        if (z) {
            ((zd0) menu).y();
        }
        super.m(i);
        if (z) {
            ((zd0) menu).x();
        }
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        ma0.p0(this);
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        ImageView imageView;
        Drawable drawable;
        super.onLayout(z, i, i2, i3, i4);
        int i5 = 0;
        ImageView imageView2 = null;
        if (this.z0 || this.A0) {
            ArrayList arrayListQ0 = t40.q0(this, getTitle());
            boolean zIsEmpty = arrayListQ0.isEmpty();
            q4 q4Var = t40.s;
            TextView textView = zIsEmpty ? null : (TextView) Collections.min(arrayListQ0, q4Var);
            ArrayList arrayListQ02 = t40.q0(this, getSubtitle());
            TextView textView2 = arrayListQ02.isEmpty() ? null : (TextView) Collections.max(arrayListQ02, q4Var);
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
                if (this.z0 && textView != null) {
                    v(textView, pair);
                }
                if (this.A0 && textView2 != null) {
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
            Boolean bool = this.C0;
            if (bool != null) {
                imageView2.setAdjustViewBounds(bool.booleanValue());
            }
            ImageView.ScaleType scaleType = this.B0;
            if (scaleType != null) {
                imageView2.setScaleType(scaleType);
            }
        }
    }

    @Override // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        ma0.n0(this, f);
    }

    public void setLogoAdjustViewBounds(boolean z) {
        Boolean bool = this.C0;
        if (bool == null || bool.booleanValue() != z) {
            this.C0 = Boolean.valueOf(z);
            requestLayout();
        }
    }

    public void setLogoScaleType(ImageView.ScaleType scaleType) {
        if (this.B0 != scaleType) {
            this.B0 = scaleType;
            requestLayout();
        }
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setNavigationIcon(Drawable drawable) {
        if (drawable != null && this.y0 != null) {
            drawable = drawable.mutate();
            ip.g(drawable, this.y0.intValue());
        }
        super.setNavigationIcon(drawable);
    }

    public void setNavigationIconTint(int i) {
        this.y0 = Integer.valueOf(i);
        Drawable navigationIcon = getNavigationIcon();
        if (navigationIcon != null) {
            setNavigationIcon(navigationIcon);
        }
    }

    public void setSubtitleCentered(boolean z) {
        if (this.A0 != z) {
            this.A0 = z;
            requestLayout();
        }
    }

    public void setTitleCentered(boolean z) {
        if (this.z0 != z) {
            this.z0 = z;
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
        ColorStateList colorStateListS;
        int i2 = D0;
        super(pd0.a(context, attributeSet, i, i2), attributeSet, i);
        Context context2 = getContext();
        TypedArray typedArrayD = i01.d(context2, attributeSet, R$styleable.MaterialToolbar, i, i2, new int[0]);
        if (typedArrayD.hasValue(R$styleable.MaterialToolbar_navigationIconTint)) {
            setNavigationIconTint(typedArrayD.getColor(R$styleable.MaterialToolbar_navigationIconTint, -1));
        }
        this.z0 = typedArrayD.getBoolean(R$styleable.MaterialToolbar_titleCentered, false);
        this.A0 = typedArrayD.getBoolean(R$styleable.MaterialToolbar_subtitleCentered, false);
        int i3 = typedArrayD.getInt(R$styleable.MaterialToolbar_logoScaleType, -1);
        if (i3 >= 0) {
            ImageView.ScaleType[] scaleTypeArr = E0;
            if (i3 < scaleTypeArr.length) {
                this.B0 = scaleTypeArr[i3];
            }
        }
        if (typedArrayD.hasValue(R$styleable.MaterialToolbar_logoAdjustViewBounds)) {
            this.C0 = Boolean.valueOf(typedArrayD.getBoolean(R$styleable.MaterialToolbar_logoAdjustViewBounds, false));
        }
        typedArrayD.recycle();
        Drawable background = getBackground();
        if (background == null) {
            colorStateListS = ColorStateList.valueOf(0);
        } else {
            colorStateListS = qm.s(background);
        }
        if (colorStateListS != null) {
            ld0 ld0Var = new ld0();
            ld0Var.m(colorStateListS);
            ld0Var.k(context2);
            WeakHashMap weakHashMap = f51.a;
            ld0Var.l(t41.i(this));
            setBackground(ld0Var);
        }
    }
}
