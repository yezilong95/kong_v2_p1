package androidx.cardview.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import androidx.cardview.R$attr;
import androidx.cardview.R$color;
import androidx.cardview.R$style;
import androidx.cardview.R$styleable;
import defpackage.e5;
import defpackage.qm;
import defpackage.wf0;

/* JADX INFO: loaded from: classes.dex */
public class CardView extends FrameLayout {
    public static final int[] g = {R.attr.colorBackground};
    public static final qm h = new qm();
    public boolean b;
    public boolean c;
    public final Rect d;
    public final Rect e;
    public final e5 f;

    public CardView(Context context) {
        this(context, null);
    }

    public ColorStateList getCardBackgroundColor() {
        return ((wf0) ((Drawable) this.f.c)).h;
    }

    public float getCardElevation() {
        return ((CardView) this.f.d).getElevation();
    }

    public int getContentPaddingBottom() {
        return this.d.bottom;
    }

    public int getContentPaddingLeft() {
        return this.d.left;
    }

    public int getContentPaddingRight() {
        return this.d.right;
    }

    public int getContentPaddingTop() {
        return this.d.top;
    }

    public float getMaxCardElevation() {
        return ((wf0) ((Drawable) this.f.c)).e;
    }

    public boolean getPreventCornerOverlap() {
        return this.c;
    }

    public float getRadius() {
        return ((wf0) ((Drawable) this.f.c)).a;
    }

    public boolean getUseCompatPadding() {
        return this.b;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    public void setCardBackgroundColor(int i) {
        ColorStateList colorStateListValueOf = ColorStateList.valueOf(i);
        wf0 wf0Var = (wf0) ((Drawable) this.f.c);
        if (colorStateListValueOf == null) {
            wf0Var.getClass();
            colorStateListValueOf = ColorStateList.valueOf(0);
        }
        wf0Var.h = colorStateListValueOf;
        wf0Var.b.setColor(colorStateListValueOf.getColorForState(wf0Var.getState(), wf0Var.h.getDefaultColor()));
        wf0Var.invalidateSelf();
    }

    public void setCardElevation(float f) {
        ((CardView) this.f.d).setElevation(f);
    }

    public void setMaxCardElevation(float f) {
        h.n(f, this.f);
    }

    @Override // android.view.View
    public void setMinimumHeight(int i) {
        super.setMinimumHeight(i);
    }

    @Override // android.view.View
    public void setMinimumWidth(int i) {
        super.setMinimumWidth(i);
    }

    @Override // android.view.View
    public final void setPadding(int i, int i2, int i3, int i4) {
    }

    @Override // android.view.View
    public final void setPaddingRelative(int i, int i2, int i3, int i4) {
    }

    public void setPreventCornerOverlap(boolean z) {
        if (z != this.c) {
            this.c = z;
            qm qmVar = h;
            e5 e5Var = this.f;
            qmVar.n(((wf0) ((Drawable) e5Var.c)).e, e5Var);
        }
    }

    public void setRadius(float f) {
        wf0 wf0Var = (wf0) ((Drawable) this.f.c);
        if (f == wf0Var.a) {
            return;
        }
        wf0Var.a = f;
        wf0Var.b(null);
        wf0Var.invalidateSelf();
    }

    public void setUseCompatPadding(boolean z) {
        if (this.b != z) {
            this.b = z;
            qm qmVar = h;
            e5 e5Var = this.f;
            qmVar.n(((wf0) ((Drawable) e5Var.c)).e, e5Var);
        }
    }

    public CardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.cardViewStyle);
    }

    public CardView(Context context, AttributeSet attributeSet, int i) {
        int color;
        ColorStateList colorStateListValueOf;
        super(context, attributeSet, i);
        Rect rect = new Rect();
        this.d = rect;
        this.e = new Rect();
        e5 e5Var = new e5(this);
        this.f = e5Var;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CardView, i, R$style.CardView);
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.CardView_cardBackgroundColor)) {
            colorStateListValueOf = typedArrayObtainStyledAttributes.getColorStateList(R$styleable.CardView_cardBackgroundColor);
        } else {
            TypedArray typedArrayObtainStyledAttributes2 = getContext().obtainStyledAttributes(g);
            int color2 = typedArrayObtainStyledAttributes2.getColor(0, 0);
            typedArrayObtainStyledAttributes2.recycle();
            float[] fArr = new float[3];
            Color.colorToHSV(color2, fArr);
            if (fArr[2] > 0.5f) {
                color = getResources().getColor(R$color.cardview_light_background);
            } else {
                color = getResources().getColor(R$color.cardview_dark_background);
            }
            colorStateListValueOf = ColorStateList.valueOf(color);
        }
        float dimension = typedArrayObtainStyledAttributes.getDimension(R$styleable.CardView_cardCornerRadius, 0.0f);
        float dimension2 = typedArrayObtainStyledAttributes.getDimension(R$styleable.CardView_cardElevation, 0.0f);
        float dimension3 = typedArrayObtainStyledAttributes.getDimension(R$styleable.CardView_cardMaxElevation, 0.0f);
        this.b = typedArrayObtainStyledAttributes.getBoolean(R$styleable.CardView_cardUseCompatPadding, false);
        this.c = typedArrayObtainStyledAttributes.getBoolean(R$styleable.CardView_cardPreventCornerOverlap, true);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.CardView_contentPadding, 0);
        rect.left = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.CardView_contentPaddingLeft, dimensionPixelSize);
        rect.top = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.CardView_contentPaddingTop, dimensionPixelSize);
        rect.right = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.CardView_contentPaddingRight, dimensionPixelSize);
        rect.bottom = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.CardView_contentPaddingBottom, dimensionPixelSize);
        dimension3 = dimension2 > dimension3 ? dimension2 : dimension3;
        typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.CardView_android_minWidth, 0);
        typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.CardView_android_minHeight, 0);
        typedArrayObtainStyledAttributes.recycle();
        qm qmVar = h;
        wf0 wf0Var = new wf0(colorStateListValueOf, dimension);
        e5Var.c = wf0Var;
        setBackgroundDrawable(wf0Var);
        setClipToOutline(true);
        setElevation(dimension2);
        qmVar.n(dimension3, e5Var);
    }

    public void setCardBackgroundColor(ColorStateList colorStateList) {
        wf0 wf0Var = (wf0) ((Drawable) this.f.c);
        if (colorStateList == null) {
            wf0Var.getClass();
            colorStateList = ColorStateList.valueOf(0);
        }
        wf0Var.h = colorStateList;
        wf0Var.b.setColor(colorStateList.getColorForState(wf0Var.getState(), wf0Var.h.getDefaultColor()));
        wf0Var.invalidateSelf();
    }
}
