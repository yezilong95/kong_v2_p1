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
import defpackage.d5;
import defpackage.e4;
import defpackage.ko0;
import defpackage.qf;
import defpackage.vn0;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatImageView extends ImageView {
    public final e4 b;
    public final d5 c;
    public boolean d;

    public AppCompatImageView(Context context) {
        this(context, null);
    }

    @Override // android.widget.ImageView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        e4 e4Var = this.b;
        if (e4Var != null) {
            e4Var.a();
        }
        d5 d5Var = this.c;
        if (d5Var != null) {
            d5Var.a();
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        e4 e4Var = this.b;
        if (e4Var != null) {
            return e4Var.c();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        e4 e4Var = this.b;
        if (e4Var != null) {
            return e4Var.d();
        }
        return null;
    }

    public ColorStateList getSupportImageTintList() {
        qf qfVar;
        d5 d5Var = this.c;
        if (d5Var == null || (qfVar = (qf) d5Var.d) == null) {
            return null;
        }
        return (ColorStateList) qfVar.c;
    }

    public PorterDuff.Mode getSupportImageTintMode() {
        qf qfVar;
        d5 d5Var = this.c;
        if (d5Var == null || (qfVar = (qf) d5Var.d) == null) {
            return null;
        }
        return (PorterDuff.Mode) qfVar.d;
    }

    @Override // android.widget.ImageView, android.view.View
    public final boolean hasOverlappingRendering() {
        return !(((ImageView) this.c.c).getBackground() instanceof RippleDrawable) && super.hasOverlappingRendering();
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        e4 e4Var = this.b;
        if (e4Var != null) {
            e4Var.f();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        e4 e4Var = this.b;
        if (e4Var != null) {
            e4Var.g(i);
        }
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        d5 d5Var = this.c;
        if (d5Var != null) {
            d5Var.a();
        }
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        d5 d5Var = this.c;
        if (d5Var != null && drawable != null && !this.d) {
            d5Var.b = drawable.getLevel();
        }
        super.setImageDrawable(drawable);
        if (d5Var != null) {
            d5Var.a();
            if (this.d) {
                return;
            }
            ImageView imageView = (ImageView) d5Var.c;
            if (imageView.getDrawable() != null) {
                imageView.getDrawable().setLevel(d5Var.b);
            }
        }
    }

    @Override // android.widget.ImageView
    public void setImageLevel(int i) {
        super.setImageLevel(i);
        this.d = true;
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i) {
        d5 d5Var = this.c;
        if (d5Var != null) {
            d5Var.j(i);
        }
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        d5 d5Var = this.c;
        if (d5Var != null) {
            d5Var.a();
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        e4 e4Var = this.b;
        if (e4Var != null) {
            e4Var.i(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        e4 e4Var = this.b;
        if (e4Var != null) {
            e4Var.j(mode);
        }
    }

    public void setSupportImageTintList(ColorStateList colorStateList) {
        d5 d5Var = this.c;
        if (d5Var != null) {
            if (((qf) d5Var.d) == null) {
                d5Var.d = new qf();
            }
            qf qfVar = (qf) d5Var.d;
            qfVar.c = colorStateList;
            qfVar.b = true;
            d5Var.a();
        }
    }

    public void setSupportImageTintMode(PorterDuff.Mode mode) {
        d5 d5Var = this.c;
        if (d5Var != null) {
            if (((qf) d5Var.d) == null) {
                d5Var.d = new qf();
            }
            qf qfVar = (qf) d5Var.d;
            qfVar.d = mode;
            qfVar.a = true;
            d5Var.a();
        }
    }

    public AppCompatImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppCompatImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        ko0.a(context);
        this.d = false;
        vn0.a(this, getContext());
        e4 e4Var = new e4(this);
        this.b = e4Var;
        e4Var.e(attributeSet, i);
        d5 d5Var = new d5(this);
        this.c = d5Var;
        d5Var.f(attributeSet, i);
    }
}
