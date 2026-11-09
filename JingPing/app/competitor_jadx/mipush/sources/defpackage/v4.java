package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public final class v4 extends r31 implements Animatable {
    public final Context G;
    public z2 H = null;
    public ArrayList I = null;
    public final s4 J = new s4(this);
    public final t4 F = new t4();

    public v4(Context context) {
        this.G = context;
    }

    @Override // defpackage.r31, android.graphics.drawable.Drawable
    public final void applyTheme(Resources.Theme theme) {
        Drawable drawable = this.E;
        if (drawable != null) {
            ip.a(drawable, theme);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean canApplyTheme() {
        Drawable drawable = this.E;
        if (drawable != null) {
            return ip.b(drawable);
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        Drawable drawable = this.E;
        if (drawable != null) {
            drawable.draw(canvas);
            return;
        }
        t4 t4Var = this.F;
        t4Var.a.draw(canvas);
        if (t4Var.b.isStarted()) {
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final int getAlpha() {
        Drawable drawable = this.E;
        return drawable != null ? drawable.getAlpha() : this.F.a.getAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getChangingConfigurations() {
        Drawable drawable = this.E;
        if (drawable != null) {
            return drawable.getChangingConfigurations();
        }
        int changingConfigurations = super.getChangingConfigurations();
        this.F.getClass();
        return changingConfigurations;
    }

    @Override // android.graphics.drawable.Drawable
    public final ColorFilter getColorFilter() {
        Drawable drawable = this.E;
        return drawable != null ? ip.c(drawable) : this.F.a.getColorFilter();
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        if (this.E != null) {
            return new u4(this.E.getConstantState());
        }
        return null;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        Drawable drawable = this.E;
        return drawable != null ? drawable.getIntrinsicHeight() : this.F.a.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        Drawable drawable = this.E;
        return drawable != null ? drawable.getIntrinsicWidth() : this.F.a.getIntrinsicWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        Drawable drawable = this.E;
        return drawable != null ? drawable.getOpacity() : this.F.a.getOpacity();
    }

    /* JADX WARN: Code restructure failed: missing block: B:39:0x00ca, code lost:
    
        if (r3.b != null) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00cc, code lost:
    
        r3.b = new android.animation.AnimatorSet();
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00d3, code lost:
    
        r3.b.playTogether(r3.c);
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00da, code lost:
    
        return;
     */
    @Override // android.graphics.drawable.Drawable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void inflate(android.content.res.Resources r10, org.xmlpull.v1.XmlPullParser r11, android.util.AttributeSet r12, android.content.res.Resources.Theme r13) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 219
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.v4.inflate(android.content.res.Resources, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.content.res.Resources$Theme):void");
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean isAutoMirrored() {
        Drawable drawable = this.E;
        return drawable != null ? drawable.isAutoMirrored() : this.F.a.isAutoMirrored();
    }

    @Override // android.graphics.drawable.Animatable
    public final boolean isRunning() {
        Drawable drawable = this.E;
        return drawable != null ? ((AnimatedVectorDrawable) drawable).isRunning() : this.F.b.isRunning();
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean isStateful() {
        Drawable drawable = this.E;
        return drawable != null ? drawable.isStateful() : this.F.a.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable mutate() {
        Drawable drawable = this.E;
        if (drawable != null) {
            drawable.mutate();
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public final void onBoundsChange(Rect rect) {
        Drawable drawable = this.E;
        if (drawable != null) {
            drawable.setBounds(rect);
        } else {
            this.F.a.setBounds(rect);
        }
    }

    @Override // defpackage.r31, android.graphics.drawable.Drawable
    public final boolean onLevelChange(int i) {
        Drawable drawable = this.E;
        return drawable != null ? drawable.setLevel(i) : this.F.a.setLevel(i);
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean onStateChange(int[] iArr) {
        Drawable drawable = this.E;
        return drawable != null ? drawable.setState(iArr) : this.F.a.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        Drawable drawable = this.E;
        if (drawable != null) {
            drawable.setAlpha(i);
        } else {
            this.F.a.setAlpha(i);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAutoMirrored(boolean z) {
        Drawable drawable = this.E;
        if (drawable != null) {
            drawable.setAutoMirrored(z);
        } else {
            this.F.a.setAutoMirrored(z);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.E;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        } else {
            this.F.a.setColorFilter(colorFilter);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTint(int i) {
        Drawable drawable = this.E;
        if (drawable != null) {
            z91.k0(drawable, i);
        } else {
            this.F.a.setTint(i);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.E;
        if (drawable != null) {
            z91.l0(drawable, colorStateList);
        } else {
            this.F.a.setTintList(colorStateList);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.E;
        if (drawable != null) {
            z91.m0(drawable, mode);
        } else {
            this.F.a.setTintMode(mode);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean setVisible(boolean z, boolean z2) {
        Drawable drawable = this.E;
        if (drawable != null) {
            return drawable.setVisible(z, z2);
        }
        this.F.a.setVisible(z, z2);
        return super.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Animatable
    public final void start() {
        Drawable drawable = this.E;
        if (drawable != null) {
            ((AnimatedVectorDrawable) drawable).start();
            return;
        }
        t4 t4Var = this.F;
        if (t4Var.b.isStarted()) {
            return;
        }
        t4Var.b.start();
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Animatable
    public final void stop() {
        Drawable drawable = this.E;
        if (drawable != null) {
            ((AnimatedVectorDrawable) drawable).stop();
        } else {
            this.F.b.end();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        inflate(resources, xmlPullParser, attributeSet, null);
    }
}
