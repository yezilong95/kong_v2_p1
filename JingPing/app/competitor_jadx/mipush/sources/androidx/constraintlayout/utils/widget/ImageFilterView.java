package androidx.constraintlayout.utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.view.ViewOutlineProvider;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.R$styleable;
import defpackage.p30;
import defpackage.q30;

/* JADX INFO: loaded from: classes.dex */
public class ImageFilterView extends AppCompatImageView {
    public final q30 H;
    public boolean I;
    public float J;
    public float K;
    public float L;
    public Path M;
    public ViewOutlineProvider N;
    public RectF O;
    public Drawable[] P;
    public LayerDrawable Q;

    public ImageFilterView(Context context) {
        super(context);
        this.H = new q30();
        this.I = true;
        this.J = 0.0f;
        this.K = 0.0f;
        this.L = Float.NaN;
    }

    private void setOverlay(boolean z) {
        this.I = z;
    }

    public final void c(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ImageFilterView);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(R$styleable.ImageFilterView_altSrc);
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.ImageFilterView_crossfade) {
                    this.J = typedArrayObtainStyledAttributes.getFloat(index, 0.0f);
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
                    setOverlay(typedArrayObtainStyledAttributes.getBoolean(index, this.I));
                }
            }
            typedArrayObtainStyledAttributes.recycle();
            if (drawable != null) {
                Drawable[] drawableArr = new Drawable[2];
                this.P = drawableArr;
                drawableArr[0] = getDrawable();
                this.P[1] = drawable;
                LayerDrawable layerDrawable = new LayerDrawable(this.P);
                this.Q = layerDrawable;
                layerDrawable.getDrawable(1).setAlpha((int) (this.J * 255.0f));
                super.setImageDrawable(this.Q);
            }
        }
    }

    public float getBrightness() {
        return this.H.d;
    }

    public float getContrast() {
        return this.H.f;
    }

    public float getCrossfade() {
        return this.J;
    }

    public float getRound() {
        return this.L;
    }

    public float getRoundPercent() {
        return this.K;
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
        this.J = f;
        if (this.P != null) {
            if (!this.I) {
                this.Q.getDrawable(0).setAlpha((int) ((1.0f - this.J) * 255.0f));
            }
            this.Q.getDrawable(1).setAlpha((int) (this.J * 255.0f));
            super.setImageDrawable(this.Q);
        }
    }

    public void setRound(float f) {
        if (Float.isNaN(f)) {
            this.L = f;
            float f2 = this.K;
            this.K = -1.0f;
            setRoundPercent(f2);
            return;
        }
        boolean z = this.L != f;
        this.L = f;
        if (f != 0.0f) {
            if (this.M == null) {
                this.M = new Path();
            }
            if (this.O == null) {
                this.O = new RectF();
            }
            if (this.N == null) {
                p30 p30Var = new p30(this, 1);
                this.N = p30Var;
                setOutlineProvider(p30Var);
            }
            setClipToOutline(true);
            this.O.set(0.0f, 0.0f, getWidth(), getHeight());
            this.M.reset();
            Path path = this.M;
            RectF rectF = this.O;
            float f3 = this.L;
            path.addRoundRect(rectF, f3, f3, Path.Direction.CW);
        } else {
            setClipToOutline(false);
        }
        if (z) {
            invalidateOutline();
        }
    }

    public void setRoundPercent(float f) {
        boolean z = this.K != f;
        this.K = f;
        if (f != 0.0f) {
            if (this.M == null) {
                this.M = new Path();
            }
            if (this.O == null) {
                this.O = new RectF();
            }
            if (this.N == null) {
                p30 p30Var = new p30(this, 0);
                this.N = p30Var;
                setOutlineProvider(p30Var);
            }
            setClipToOutline(true);
            int width = getWidth();
            int height = getHeight();
            float fMin = (Math.min(width, height) * this.K) / 2.0f;
            this.O.set(0.0f, 0.0f, width, height);
            this.M.reset();
            this.M.addRoundRect(this.O, fMin, fMin, Path.Direction.CW);
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

    public ImageFilterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.H = new q30();
        this.I = true;
        this.J = 0.0f;
        this.K = 0.0f;
        this.L = Float.NaN;
        c(attributeSet);
    }

    public ImageFilterView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.H = new q30();
        this.I = true;
        this.J = 0.0f;
        this.K = 0.0f;
        this.L = Float.NaN;
        c(attributeSet);
    }
}
