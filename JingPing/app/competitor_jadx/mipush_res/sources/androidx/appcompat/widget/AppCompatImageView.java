package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;
import defpackage.i5;
import defpackage.j01;
import defpackage.l0;
import defpackage.w01;
import defpackage.x01;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatImageView extends ImageView {
    public final i5 E;
    public final l0 F;
    public boolean G;

    public AppCompatImageView(Context context) {
        this(context, null);
    }

    @Override // android.widget.ImageView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        i5 i5Var = this.E;
        if (i5Var != null) {
            i5Var.d();
        }
        l0 l0Var = this.F;
        if (l0Var != null) {
            l0Var.b();
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        i5 i5Var = this.E;
        if (i5Var != null) {
            return i5Var.f();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        i5 i5Var = this.E;
        if (i5Var != null) {
            return i5Var.g();
        }
        return null;
    }

    public ColorStateList getSupportImageTintList() {
        x01 x01Var;
        l0 l0Var = this.F;
        if (l0Var == null || (x01Var = (x01) l0Var.d) == null) {
            return null;
        }
        return x01Var.a;
    }

    public PorterDuff.Mode getSupportImageTintMode() {
        x01 x01Var;
        l0 l0Var = this.F;
        if (l0Var == null || (x01Var = (x01) l0Var.d) == null) {
            return null;
        }
        return x01Var.b;
    }

    @Override // android.widget.ImageView, android.view.View
    public final boolean hasOverlappingRendering() {
        return !(((ImageView) this.F.c).getBackground() instanceof RippleDrawable) && super.hasOverlappingRendering();
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        i5 i5Var = this.E;
        if (i5Var != null) {
            i5Var.i();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        i5 i5Var = this.E;
        if (i5Var != null) {
            i5Var.j(i);
        }
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        l0 l0Var = this.F;
        if (l0Var != null) {
            l0Var.b();
        }
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        l0 l0Var = this.F;
        if (l0Var != null && drawable != null && !this.G) {
            l0Var.b = drawable.getLevel();
        }
        super.setImageDrawable(drawable);
        if (l0Var != null) {
            l0Var.b();
            if (this.G) {
                return;
            }
            ImageView imageView = (ImageView) l0Var.c;
            if (imageView.getDrawable() != null) {
                imageView.getDrawable().setLevel(l0Var.b);
            }
        }
    }

    @Override // android.widget.ImageView
    public void setImageLevel(int i) {
        super.setImageLevel(i);
        this.G = true;
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i) {
        l0 l0Var = this.F;
        if (l0Var != null) {
            l0Var.p(i);
        }
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        l0 l0Var = this.F;
        if (l0Var != null) {
            l0Var.b();
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        i5 i5Var = this.E;
        if (i5Var != null) {
            i5Var.l(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        i5 i5Var = this.E;
        if (i5Var != null) {
            i5Var.m(mode);
        }
    }

    public void setSupportImageTintList(ColorStateList colorStateList) {
        l0 l0Var = this.F;
        if (l0Var != null) {
            if (((x01) l0Var.d) == null) {
                l0Var.d = new x01();
            }
            x01 x01Var = (x01) l0Var.d;
            x01Var.a = colorStateList;
            x01Var.d = true;
            l0Var.b();
        }
    }

    public void setSupportImageTintMode(PorterDuff.Mode mode) {
        l0 l0Var = this.F;
        if (l0Var != null) {
            if (((x01) l0Var.d) == null) {
                l0Var.d = new x01();
            }
            x01 x01Var = (x01) l0Var.d;
            x01Var.b = mode;
            x01Var.c = true;
            l0Var.b();
        }
    }

    public AppCompatImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppCompatImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        w01.a(context);
        this.G = false;
        j01.a(this, getContext());
        i5 i5Var = new i5(this);
        this.E = i5Var;
        i5Var.h(attributeSet, i);
        l0 l0Var = new l0(this);
        this.F = l0Var;
        l0Var.i(attributeSet, i);
    }
}
