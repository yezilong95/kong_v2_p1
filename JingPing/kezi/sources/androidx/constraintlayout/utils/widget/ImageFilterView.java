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
import defpackage.ox;
import defpackage.px;

/* JADX INFO: loaded from: classes.dex */
public class ImageFilterView extends AppCompatImageView {
    public final px e;
    public boolean f;
    public float g;
    public float h;
    public float i;
    public Path j;
    public ViewOutlineProvider k;
    public RectF l;
    public Drawable[] m;
    public LayerDrawable n;

    public ImageFilterView(Context context) {
        super(context);
        this.e = new px();
        this.f = true;
        this.g = 0.0f;
        this.h = 0.0f;
        this.i = Float.NaN;
    }

    private void setOverlay(boolean z) {
        this.f = z;
    }

    public final void c(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ImageFilterView);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(R$styleable.ImageFilterView_altSrc);
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.ImageFilterView_crossfade) {
                    this.g = typedArrayObtainStyledAttributes.getFloat(index, 0.0f);
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
                    setOverlay(typedArrayObtainStyledAttributes.getBoolean(index, this.f));
                }
            }
            typedArrayObtainStyledAttributes.recycle();
            if (drawable != null) {
                Drawable[] drawableArr = new Drawable[2];
                this.m = drawableArr;
                drawableArr[0] = getDrawable();
                this.m[1] = drawable;
                LayerDrawable layerDrawable = new LayerDrawable(this.m);
                this.n = layerDrawable;
                layerDrawable.getDrawable(1).setAlpha((int) (this.g * 255.0f));
                super.setImageDrawable(this.n);
            }
        }
    }

    public float getBrightness() {
        return this.e.d;
    }

    public float getContrast() {
        return this.e.f;
    }

    public float getCrossfade() {
        return this.g;
    }

    public float getRound() {
        return this.i;
    }

    public float getRoundPercent() {
        return this.h;
    }

    public float getSaturation() {
        return this.e.e;
    }

    public float getWarmth() {
        return this.e.g;
    }

    public void setBrightness(float f) {
        px pxVar = this.e;
        pxVar.d = f;
        pxVar.a(this);
    }

    public void setContrast(float f) {
        px pxVar = this.e;
        pxVar.f = f;
        pxVar.a(this);
    }

    public void setCrossfade(float f) {
        this.g = f;
        if (this.m != null) {
            if (!this.f) {
                this.n.getDrawable(0).setAlpha((int) ((1.0f - this.g) * 255.0f));
            }
            this.n.getDrawable(1).setAlpha((int) (this.g * 255.0f));
            super.setImageDrawable(this.n);
        }
    }

    public void setRound(float f) {
        if (Float.isNaN(f)) {
            this.i = f;
            float f2 = this.h;
            this.h = -1.0f;
            setRoundPercent(f2);
            return;
        }
        boolean z = this.i != f;
        this.i = f;
        if (f != 0.0f) {
            if (this.j == null) {
                this.j = new Path();
            }
            if (this.l == null) {
                this.l = new RectF();
            }
            if (this.k == null) {
                ox oxVar = new ox(this, 1);
                this.k = oxVar;
                setOutlineProvider(oxVar);
            }
            setClipToOutline(true);
            this.l.set(0.0f, 0.0f, getWidth(), getHeight());
            this.j.reset();
            Path path = this.j;
            RectF rectF = this.l;
            float f3 = this.i;
            path.addRoundRect(rectF, f3, f3, Path.Direction.CW);
        } else {
            setClipToOutline(false);
        }
        if (z) {
            invalidateOutline();
        }
    }

    public void setRoundPercent(float f) {
        boolean z = this.h != f;
        this.h = f;
        if (f != 0.0f) {
            if (this.j == null) {
                this.j = new Path();
            }
            if (this.l == null) {
                this.l = new RectF();
            }
            if (this.k == null) {
                ox oxVar = new ox(this, 0);
                this.k = oxVar;
                setOutlineProvider(oxVar);
            }
            setClipToOutline(true);
            int width = getWidth();
            int height = getHeight();
            float fMin = (Math.min(width, height) * this.h) / 2.0f;
            this.l.set(0.0f, 0.0f, width, height);
            this.j.reset();
            this.j.addRoundRect(this.l, fMin, fMin, Path.Direction.CW);
        } else {
            setClipToOutline(false);
        }
        if (z) {
            invalidateOutline();
        }
    }

    public void setSaturation(float f) {
        px pxVar = this.e;
        pxVar.e = f;
        pxVar.a(this);
    }

    public void setWarmth(float f) {
        px pxVar = this.e;
        pxVar.g = f;
        pxVar.a(this);
    }

    public ImageFilterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = new px();
        this.f = true;
        this.g = 0.0f;
        this.h = 0.0f;
        this.i = Float.NaN;
        c(attributeSet);
    }

    public ImageFilterView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = new px();
        this.f = true;
        this.g = 0.0f;
        this.h = 0.0f;
        this.i = Float.NaN;
        c(attributeSet);
    }
}
