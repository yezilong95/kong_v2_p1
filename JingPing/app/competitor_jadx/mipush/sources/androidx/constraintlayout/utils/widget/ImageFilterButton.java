package androidx.constraintlayout.utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.view.ViewOutlineProvider;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.constraintlayout.widget.R$styleable;
import defpackage.o30;
import defpackage.q30;

/* JADX INFO: loaded from: classes.dex */
public class ImageFilterButton extends AppCompatImageButton {
    public final q30 H;
    public float I;
    public float J;
    public float K;
    public Path L;
    public ViewOutlineProvider M;
    public RectF N;
    public Drawable[] O;
    public LayerDrawable P;
    public boolean Q;

    public ImageFilterButton(Context context) {
        super(context);
        this.H = new q30();
        this.I = 0.0f;
        this.J = 0.0f;
        this.K = Float.NaN;
        this.Q = true;
        setPadding(0, 0, 0, 0);
    }

    private void setOverlay(boolean z) {
        this.Q = z;
    }

    public final void a(AttributeSet attributeSet) {
        setPadding(0, 0, 0, 0);
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ImageFilterView);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(R$styleable.ImageFilterView_altSrc);
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.ImageFilterView_crossfade) {
                    this.I = typedArrayObtainStyledAttributes.getFloat(index, 0.0f);
                } else if (index == R$styleable.ImageFilterView_warmth) {
                    setWarmth(typedArrayObtainStyledAttributes.getFloat(index, 0.0f));
                } else if (index == R$styleable.ImageFilterView_saturation) {
                    setSaturation(typedArrayObtainStyledAttributes.getFloat(index, 0.0f));
                } else if (index == R$styleable.ImageFilterView_contrast) {
                    setContrast(typedArrayObtainStyledAttributes.getFloat(index, 0.0f));
                } else if (index == R$styleable.ImageFilterView_round) {
                    setRound(typedArrayObtainStyledAttributes.getDimension(index, 0.0f));
                } else if (index == R$styleable.ImageFilterView_roundPercent) {
                    setRoundPercent(typedArrayObtainStyledAttributes.getFloat(index, 0.0f));
                } else if (index == R$styleable.ImageFilterView_overlay) {
                    setOverlay(typedArrayObtainStyledAttributes.getBoolean(index, this.Q));
                }
            }
            typedArrayObtainStyledAttributes.recycle();
            if (drawable != null) {
                Drawable[] drawableArr = new Drawable[2];
                this.O = drawableArr;
                drawableArr[0] = getDrawable();
                this.O[1] = drawable;
                LayerDrawable layerDrawable = new LayerDrawable(this.O);
                this.P = layerDrawable;
                layerDrawable.getDrawable(1).setAlpha((int) (this.I * 255.0f));
                super.setImageDrawable(this.P);
            }
        }
    }

    public float getContrast() {
        return this.H.f;
    }

    public float getCrossfade() {
        return this.I;
    }

    public float getRound() {
        return this.K;
    }

    public float getRoundPercent() {
        return this.J;
    }

    public float getSaturation() {
        return this.H.e;
    }

    public float getWarmth() {
        return this.H.g;
    }

    public void setBrightness(float f) {
        q30 q30Var = this.H;
        q30Var.d = f;
        q30Var.a(this);
    }

    public void setContrast(float f) {
        q30 q30Var = this.H;
        q30Var.f = f;
        q30Var.a(this);
    }

    public void setCrossfade(float f) {
        this.I = f;
        if (this.O != null) {
            if (!this.Q) {
                this.P.getDrawable(0).setAlpha((int) ((1.0f - this.I) * 255.0f));
            }
            this.P.getDrawable(1).setAlpha((int) (this.I * 255.0f));
            super.setImageDrawable(this.P);
        }
    }

    public void setRound(float f) {
        if (Float.isNaN(f)) {
            this.K = f;
            float f2 = this.J;
            this.J = -1.0f;
            setRoundPercent(f2);
            return;
        }
        boolean z = this.K != f;
        this.K = f;
        if (f != 0.0f) {
            if (this.L == null) {
                this.L = new Path();
            }
            if (this.N == null) {
                this.N = new RectF();
            }
            if (this.M == null) {
                o30 o30Var = new o30(this, 1);
                this.M = o30Var;
                setOutlineProvider(o30Var);
            }
            setClipToOutline(true);
            this.N.set(0.0f, 0.0f, getWidth(), getHeight());
            this.L.reset();
            Path path = this.L;
            RectF rectF = this.N;
            float f3 = this.K;
            path.addRoundRect(rectF, f3, f3, Path.Direction.CW);
        } else {
            setClipToOutline(false);
        }
        if (z) {
            invalidateOutline();
        }
    }

    public void setRoundPercent(float f) {
        boolean z = this.J != f;
        this.J = f;
        if (f != 0.0f) {
            if (this.L == null) {
                this.L = new Path();
            }
            if (this.N == null) {
                this.N = new RectF();
            }
            if (this.M == null) {
                o30 o30Var = new o30(this, 0);
                this.M = o30Var;
                setOutlineProvider(o30Var);
            }
            setClipToOutline(true);
            int width = getWidth();
            int height = getHeight();
            float fMin = (Math.min(width, height) * this.J) / 2.0f;
            this.N.set(0.0f, 0.0f, width, height);
            this.L.reset();
            this.L.addRoundRect(this.N, fMin, fMin, Path.Direction.CW);
        } else {
            setClipToOutline(false);
        }
        if (z) {
            invalidateOutline();
        }
    }

    public void setSaturation(float f) {
        q30 q30Var = this.H;
        q30Var.e = f;
        q30Var.a(this);
    }

    public void setWarmth(float f) {
        q30 q30Var = this.H;
        q30Var.g = f;
        q30Var.a(this);
    }

    public ImageFilterButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.H = new q30();
        this.I = 0.0f;
        this.J = 0.0f;
        this.K = Float.NaN;
        this.Q = true;
        a(attributeSet);
    }

    public ImageFilterButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.H = new q30();
        this.I = 0.0f;
        this.J = 0.0f;
        this.K = Float.NaN;
        this.Q = true;
        a(attributeSet);
    }
}
