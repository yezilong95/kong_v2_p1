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
import defpackage.i6;
import defpackage.is;
import defpackage.sp0;

/* JADX INFO: loaded from: classes.dex */
public class CardView extends FrameLayout {
    public static final int[] J = {R.attr.colorBackground};
    public static final is K = new is(20);
    public boolean E;
    public boolean F;
    public final Rect G;
    public final Rect H;
    public final i6 I;

    public CardView(Context context) {
        this(context, null);
    }

    public ColorStateList getCardBackgroundColor() {
        return ((sp0) ((Drawable) this.I.F)).h;
    }

    public float getCardElevation() {
        return ((CardView) this.I.G).getElevation();
    }

    public int getContentPaddingBottom() {
        return this.G.bottom;
    }

    public int getContentPaddingLeft() {
        return this.G.left;
    }

    public int getContentPaddingRight() {
        return this.G.right;
    }

    public int getContentPaddingTop() {
        return this.G.top;
    }

    public float getMaxCardElevation() {
        return ((sp0) ((Drawable) this.I.F)).e;
    }

    public boolean getPreventCornerOverlap() {
        return this.F;
    }

    public float getRadius() {
        return ((sp0) ((Drawable) this.I.F)).a;
    }

    public boolean getUseCompatPadding() {
        return this.E;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    public void setCardBackgroundColor(int i) {
        ColorStateList colorStateListValueOf = ColorStateList.valueOf(i);
        sp0 sp0Var = (sp0) ((Drawable) this.I.F);
        if (colorStateListValueOf == null) {
            sp0Var.getClass();
            colorStateListValueOf = ColorStateList.valueOf(0);
        }
        sp0Var.h = colorStateListValueOf;
        sp0Var.b.setColor(colorStateListValueOf.getColorForState(sp0Var.getState(), sp0Var.h.getDefaultColor()));
        sp0Var.invalidateSelf();
    }

    public void setCardElevation(float f) {
        ((CardView) this.I.G).setElevation(f);
    }

    public void setMaxCardElevation(float f) {
        K.m(f, this.I);
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
        if (z != this.F) {
            this.F = z;
            is isVar = K;
            i6 i6Var = this.I;
            isVar.m(((sp0) ((Drawable) i6Var.F)).e, i6Var);
        }
    }

    public void setRadius(float f) {
        sp0 sp0Var = (sp0) ((Drawable) this.I.F);
        if (f == sp0Var.a) {
            return;
        }
        sp0Var.a = f;
        sp0Var.b(null);
        sp0Var.invalidateSelf();
    }

    public void setUseCompatPadding(boolean z) {
        if (this.E != z) {
            this.E = z;
            is isVar = K;
            i6 i6Var = this.I;
            isVar.m(((sp0) ((Drawable) i6Var.F)).e, i6Var);
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
        this.G = rect;
        this.H = new Rect();
        i6 i6Var = new i6(this);
        this.I = i6Var;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CardView, i, R$style.CardView);
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.CardView_cardBackgroundColor)) {
            colorStateListValueOf = typedArrayObtainStyledAttributes.getColorStateList(R$styleable.CardView_cardBackgroundColor);
        } else {
            TypedArray typedArrayObtainStyledAttributes2 = getContext().obtainStyledAttributes(J);
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
        this.E = typedArrayObtainStyledAttributes.getBoolean(R$styleable.CardView_cardUseCompatPadding, false);
        this.F = typedArrayObtainStyledAttributes.getBoolean(R$styleable.CardView_cardPreventCornerOverlap, true);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.CardView_contentPadding, 0);
        rect.left = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.CardView_contentPaddingLeft, dimensionPixelSize);
        rect.top = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.CardView_contentPaddingTop, dimensionPixelSize);
        rect.right = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.CardView_contentPaddingRight, dimensionPixelSize);
        rect.bottom = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.CardView_contentPaddingBottom, dimensionPixelSize);
        dimension3 = dimension2 > dimension3 ? dimension2 : dimension3;
        typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.CardView_android_minWidth, 0);
        typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.CardView_android_minHeight, 0);
        typedArrayObtainStyledAttributes.recycle();
        is isVar = K;
        sp0 sp0Var = new sp0(colorStateListValueOf, dimension);
        i6Var.F = sp0Var;
        setBackgroundDrawable(sp0Var);
        setClipToOutline(true);
        setElevation(dimension2);
        isVar.m(dimension3, i6Var);
    }

    public void setCardBackgroundColor(ColorStateList colorStateList) {
        sp0 sp0Var = (sp0) ((Drawable) this.I.F);
        if (colorStateList == null) {
            sp0Var.getClass();
            colorStateList = ColorStateList.valueOf(0);
        }
        sp0Var.h = colorStateList;
        sp0Var.b.setColor(colorStateList.getColorForState(sp0Var.getState(), sp0Var.h.getDefaultColor()));
        sp0Var.invalidateSelf();
    }
}
