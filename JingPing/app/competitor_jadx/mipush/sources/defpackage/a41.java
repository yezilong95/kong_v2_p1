package defpackage;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import androidx.work.impl.WorkDatabaseVersions;
import io.nats.client.support.ApiConstants;
import java.io.IOException;
import java.util.ArrayDeque;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public final class a41 extends r31 {
    public static final PorterDuff.Mode N = PorterDuff.Mode.SRC_IN;
    public y31 F;
    public PorterDuffColorFilter G;
    public ColorFilter H;
    public boolean I;
    public boolean J;
    public final float[] K;
    public final Matrix L;
    public final Rect M;

    public a41() {
        this.J = true;
        this.K = new float[9];
        this.L = new Matrix();
        this.M = new Rect();
        y31 y31Var = new y31();
        y31Var.c = null;
        y31Var.d = N;
        y31Var.b = new x31();
        this.F = y31Var;
    }

    public final PorterDuffColorFilter a(ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean canApplyTheme() {
        Drawable drawable = this.E;
        if (drawable == null) {
            return false;
        }
        ip.b(drawable);
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        Paint paint;
        Drawable drawable = this.E;
        if (drawable != null) {
            drawable.draw(canvas);
            return;
        }
        Rect rect = this.M;
        copyBounds(rect);
        if (rect.width() <= 0 || rect.height() <= 0) {
            return;
        }
        ColorFilter colorFilter = this.H;
        if (colorFilter == null) {
            colorFilter = this.G;
        }
        Matrix matrix = this.L;
        canvas.getMatrix(matrix);
        float[] fArr = this.K;
        matrix.getValues(fArr);
        float fAbs = Math.abs(fArr[0]);
        float fAbs2 = Math.abs(fArr[4]);
        float fAbs3 = Math.abs(fArr[1]);
        float fAbs4 = Math.abs(fArr[3]);
        if (fAbs3 != 0.0f || fAbs4 != 0.0f) {
            fAbs = 1.0f;
            fAbs2 = 1.0f;
        }
        int iWidth = (int) (rect.width() * fAbs);
        int iMin = Math.min(2048, iWidth);
        int iMin2 = Math.min(2048, (int) (rect.height() * fAbs2));
        if (iMin <= 0 || iMin2 <= 0) {
            return;
        }
        int iSave = canvas.save();
        canvas.translate(rect.left, rect.top);
        if (isAutoMirrored() && jp.a(this) == 1) {
            canvas.translate(rect.width(), 0.0f);
            canvas.scale(-1.0f, 1.0f);
        }
        rect.offsetTo(0, 0);
        y31 y31Var = this.F;
        Bitmap bitmap = y31Var.f;
        if (bitmap == null || iMin != bitmap.getWidth() || iMin2 != y31Var.f.getHeight()) {
            y31Var.f = Bitmap.createBitmap(iMin, iMin2, Bitmap.Config.ARGB_8888);
            y31Var.k = true;
        }
        if (this.J) {
            y31 y31Var2 = this.F;
            if (y31Var2.k || y31Var2.g != y31Var2.c || y31Var2.h != y31Var2.d || y31Var2.j != y31Var2.e || y31Var2.i != y31Var2.b.getRootAlpha()) {
                y31 y31Var3 = this.F;
                y31Var3.f.eraseColor(0);
                Canvas canvas2 = new Canvas(y31Var3.f);
                x31 x31Var = y31Var3.b;
                x31Var.a(x31Var.g, x31.p, canvas2, iMin, iMin2);
                y31 y31Var4 = this.F;
                y31Var4.g = y31Var4.c;
                y31Var4.h = y31Var4.d;
                y31Var4.i = y31Var4.b.getRootAlpha();
                y31Var4.j = y31Var4.e;
                y31Var4.k = false;
            }
        } else {
            y31 y31Var5 = this.F;
            y31Var5.f.eraseColor(0);
            Canvas canvas3 = new Canvas(y31Var5.f);
            x31 x31Var2 = y31Var5.b;
            x31Var2.a(x31Var2.g, x31.p, canvas3, iMin, iMin2);
        }
        y31 y31Var6 = this.F;
        if (y31Var6.b.getRootAlpha() >= 255 && colorFilter == null) {
            paint = null;
        } else {
            if (y31Var6.l == null) {
                Paint paint2 = new Paint();
                y31Var6.l = paint2;
                paint2.setFilterBitmap(true);
            }
            y31Var6.l.setAlpha(y31Var6.b.getRootAlpha());
            y31Var6.l.setColorFilter(colorFilter);
            paint = y31Var6.l;
        }
        canvas.drawBitmap(y31Var6.f, (Rect) null, rect, paint);
        canvas.restoreToCount(iSave);
    }

    @Override // android.graphics.drawable.Drawable
    public final int getAlpha() {
        Drawable drawable = this.E;
        return drawable != null ? drawable.getAlpha() : this.F.b.getRootAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getChangingConfigurations() {
        Drawable drawable = this.E;
        return drawable != null ? drawable.getChangingConfigurations() : super.getChangingConfigurations() | this.F.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public final ColorFilter getColorFilter() {
        Drawable drawable = this.E;
        return drawable != null ? ip.c(drawable) : this.H;
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        if (this.E != null) {
            return new z31(this.E.getConstantState());
        }
        this.F.a = getChangingConfigurations();
        return this.F;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        Drawable drawable = this.E;
        return drawable != null ? drawable.getIntrinsicHeight() : (int) this.F.b.i;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        Drawable drawable = this.E;
        return drawable != null ? drawable.getIntrinsicWidth() : (int) this.F.b.h;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        Drawable drawable = this.E;
        if (drawable != null) {
            return drawable.getOpacity();
        }
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public final void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        Drawable drawable = this.E;
        if (drawable != null) {
            drawable.inflate(resources, xmlPullParser, attributeSet);
        } else {
            inflate(resources, xmlPullParser, attributeSet, null);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void invalidateSelf() {
        Drawable drawable = this.E;
        if (drawable != null) {
            drawable.invalidateSelf();
        } else {
            super.invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean isAutoMirrored() {
        Drawable drawable = this.E;
        return drawable != null ? drawable.isAutoMirrored() : this.F.e;
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean isStateful() {
        Drawable drawable = this.E;
        if (drawable != null) {
            return drawable.isStateful();
        }
        if (super.isStateful()) {
            return true;
        }
        y31 y31Var = this.F;
        if (y31Var == null) {
            return false;
        }
        x31 x31Var = y31Var.b;
        if (x31Var.n == null) {
            x31Var.n = Boolean.valueOf(x31Var.g.a());
        }
        if (x31Var.n.booleanValue()) {
            return true;
        }
        ColorStateList colorStateList = this.F.c;
        return colorStateList != null && colorStateList.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable mutate() {
        Drawable drawable = this.E;
        if (drawable != null) {
            drawable.mutate();
            return this;
        }
        if (!this.I && super.mutate() == this) {
            y31 y31Var = this.F;
            y31 y31Var2 = new y31();
            y31Var2.c = null;
            y31Var2.d = N;
            if (y31Var != null) {
                y31Var2.a = y31Var.a;
                x31 x31Var = new x31(y31Var.b);
                y31Var2.b = x31Var;
                if (y31Var.b.e != null) {
                    x31Var.e = new Paint(y31Var.b.e);
                }
                if (y31Var.b.d != null) {
                    y31Var2.b.d = new Paint(y31Var.b.d);
                }
                y31Var2.c = y31Var.c;
                y31Var2.d = y31Var.d;
                y31Var2.e = y31Var.e;
            }
            this.F = y31Var2;
            this.I = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public final void onBoundsChange(Rect rect) {
        Drawable drawable = this.E;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean onStateChange(int[] iArr) {
        boolean z;
        PorterDuff.Mode mode;
        Drawable drawable = this.E;
        if (drawable != null) {
            return drawable.setState(iArr);
        }
        y31 y31Var = this.F;
        ColorStateList colorStateList = y31Var.c;
        if (colorStateList == null || (mode = y31Var.d) == null) {
            z = false;
        } else {
            this.G = a(colorStateList, mode);
            invalidateSelf();
            z = true;
        }
        x31 x31Var = y31Var.b;
        if (x31Var.n == null) {
            x31Var.n = Boolean.valueOf(x31Var.g.a());
        }
        if (x31Var.n.booleanValue()) {
            boolean zB = y31Var.b.g.b(iArr);
            y31Var.k |= zB;
            if (zB) {
                invalidateSelf();
                return true;
            }
        }
        return z;
    }

    @Override // android.graphics.drawable.Drawable
    public final void scheduleSelf(Runnable runnable, long j) {
        Drawable drawable = this.E;
        if (drawable != null) {
            drawable.scheduleSelf(runnable, j);
        } else {
            super.scheduleSelf(runnable, j);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        Drawable drawable = this.E;
        if (drawable != null) {
            drawable.setAlpha(i);
        } else if (this.F.b.getRootAlpha() != i) {
            this.F.b.setRootAlpha(i);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAutoMirrored(boolean z) {
        Drawable drawable = this.E;
        if (drawable != null) {
            drawable.setAutoMirrored(z);
        } else {
            this.F.e = z;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.E;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        } else {
            this.H = colorFilter;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTint(int i) {
        Drawable drawable = this.E;
        if (drawable != null) {
            z91.k0(drawable, i);
        } else {
            setTintList(ColorStateList.valueOf(i));
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.E;
        if (drawable != null) {
            ip.h(drawable, colorStateList);
            return;
        }
        y31 y31Var = this.F;
        if (y31Var.c != colorStateList) {
            y31Var.c = colorStateList;
            this.G = a(colorStateList, y31Var.d);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.E;
        if (drawable != null) {
            ip.i(drawable, mode);
            return;
        }
        y31 y31Var = this.F;
        if (y31Var.d != mode) {
            y31Var.d = mode;
            this.G = a(y31Var.c, mode);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean setVisible(boolean z, boolean z2) {
        Drawable drawable = this.E;
        return drawable != null ? drawable.setVisible(z, z2) : super.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Drawable
    public final void unscheduleSelf(Runnable runnable) {
        Drawable drawable = this.E;
        if (drawable != null) {
            drawable.unscheduleSelf(runnable);
        } else {
            super.unscheduleSelf(runnable);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        x31 x31Var;
        int i;
        boolean z;
        char c;
        int i2;
        Paint.Cap cap;
        Paint.Join join;
        Drawable drawable = this.E;
        if (drawable != null) {
            ip.d(drawable, resources, xmlPullParser, attributeSet, theme);
            return;
        }
        y31 y31Var = this.F;
        y31Var.b = new x31();
        TypedArray typedArrayX = la0.X(resources, theme, attributeSet, l21.g);
        y31 y31Var2 = this.F;
        x31 x31Var2 = y31Var2.b;
        int iY = la0.y(typedArrayX, xmlPullParser, "tintMode", 6, -1);
        PorterDuff.Mode mode = PorterDuff.Mode.SRC_IN;
        if (iY == 3) {
            mode = PorterDuff.Mode.SRC_OVER;
        } else if (iY != 5) {
            if (iY != 9) {
                switch (iY) {
                    case WorkDatabaseVersions.VERSION_14 /* 14 */:
                        mode = PorterDuff.Mode.MULTIPLY;
                        break;
                    case WorkDatabaseVersions.VERSION_15 /* 15 */:
                        mode = PorterDuff.Mode.SCREEN;
                        break;
                    case 16:
                        mode = PorterDuff.Mode.ADD;
                        break;
                }
            } else {
                mode = PorterDuff.Mode.SRC_ATOP;
            }
        }
        y31Var2.d = mode;
        int i3 = 1;
        ColorStateList colorStateListA = null;
        if (xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "tint") != null) {
            TypedValue typedValue = new TypedValue();
            typedArrayX.getValue(1, typedValue);
            int i4 = typedValue.type;
            if (i4 == 2) {
                throw new UnsupportedOperationException("Failed to resolve attribute at index 1: " + typedValue);
            }
            if (i4 >= 28 && i4 <= 31) {
                colorStateListA = ColorStateList.valueOf(typedValue.data);
            } else {
                Resources resources2 = typedArrayX.getResources();
                int resourceId = typedArrayX.getResourceId(1, 0);
                ThreadLocal threadLocal = cg.a;
                try {
                    colorStateListA = cg.a(resources2, resources2.getXml(resourceId), theme);
                } catch (Exception e) {
                    Log.e("CSLCompat", "Failed to inflate ColorStateList.", e);
                }
            }
        }
        ColorStateList colorStateList = colorStateListA;
        if (colorStateList != null) {
            y31Var2.c = colorStateList;
        }
        boolean z2 = y31Var2.e;
        if (xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "autoMirrored") != null) {
            z2 = typedArrayX.getBoolean(5, z2);
        }
        y31Var2.e = z2;
        float f = x31Var2.j;
        if (xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "viewportWidth") != null) {
            f = typedArrayX.getFloat(7, f);
        }
        x31Var2.j = f;
        float f2 = x31Var2.k;
        if (xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "viewportHeight") != null) {
            f2 = typedArrayX.getFloat(8, f2);
        }
        x31Var2.k = f2;
        boolean z3 = false;
        if (x31Var2.j <= 0.0f) {
            throw new XmlPullParserException(typedArrayX.getPositionDescription() + "<vector> tag requires viewportWidth > 0");
        }
        if (f2 > 0.0f) {
            x31Var2.h = typedArrayX.getDimension(3, x31Var2.h);
            float dimension = typedArrayX.getDimension(2, x31Var2.i);
            x31Var2.i = dimension;
            if (x31Var2.h <= 0.0f) {
                throw new XmlPullParserException(typedArrayX.getPositionDescription() + "<vector> tag requires width > 0");
            }
            if (dimension > 0.0f) {
                float alpha = x31Var2.getAlpha();
                if (xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "alpha") != null) {
                    alpha = typedArrayX.getFloat(4, alpha);
                }
                x31Var2.setAlpha(alpha);
                String string = typedArrayX.getString(0);
                if (string != null) {
                    x31Var2.m = string;
                    x31Var2.o.put(string, x31Var2);
                }
                typedArrayX.recycle();
                y31Var.a = getChangingConfigurations();
                y31Var.k = true;
                y31 y31Var3 = this.F;
                x31 x31Var3 = y31Var3.b;
                ArrayDeque arrayDeque = new ArrayDeque();
                arrayDeque.push(x31Var3.g);
                int eventType = xmlPullParser.getEventType();
                int depth = xmlPullParser.getDepth() + 1;
                boolean z4 = true;
                while (eventType != i3 && (xmlPullParser.getDepth() >= depth || eventType != 3)) {
                    if (eventType == 2) {
                        String name = xmlPullParser.getName();
                        u31 u31Var = (u31) arrayDeque.peek();
                        boolean zEquals = "path".equals(name);
                        i = depth;
                        k8 k8Var = x31Var3.o;
                        if (zEquals) {
                            t31 t31Var = new t31();
                            t31Var.e = 0.0f;
                            t31Var.g = 1.0f;
                            t31Var.h = 1.0f;
                            x31Var = x31Var3;
                            t31Var.i = 0.0f;
                            t31Var.j = 1.0f;
                            t31Var.k = 0.0f;
                            Paint.Cap cap2 = Paint.Cap.BUTT;
                            t31Var.l = cap2;
                            Paint.Join join2 = Paint.Join.MITER;
                            t31Var.m = join2;
                            t31Var.n = 4.0f;
                            TypedArray typedArrayX2 = la0.X(resources, theme, attributeSet, l21.i);
                            if (xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "pathData") != null) {
                                String string2 = typedArrayX2.getString(0);
                                if (string2 != null) {
                                    t31Var.b = string2;
                                }
                                String string3 = typedArrayX2.getString(2);
                                if (string3 != null) {
                                    t31Var.a = o80.q(string3);
                                }
                                t31Var.f = la0.x(typedArrayX2, xmlPullParser, theme, "fillColor", 1);
                                float f3 = t31Var.h;
                                if (xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "fillAlpha") != null) {
                                    f3 = typedArrayX2.getFloat(12, f3);
                                }
                                t31Var.h = f3;
                                int i5 = xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "strokeLineCap") != null ? typedArrayX2.getInt(8, -1) : -1;
                                Paint.Cap cap3 = t31Var.l;
                                if (i5 == 0) {
                                    cap = cap2;
                                } else if (i5 != 1) {
                                    cap = i5 != 2 ? cap3 : Paint.Cap.SQUARE;
                                } else {
                                    cap = Paint.Cap.ROUND;
                                }
                                t31Var.l = cap;
                                int i6 = xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "strokeLineJoin") != null ? typedArrayX2.getInt(9, -1) : -1;
                                Paint.Join join3 = t31Var.m;
                                if (i6 == 0) {
                                    join = join2;
                                } else if (i6 != 1) {
                                    join = i6 != 2 ? join3 : Paint.Join.BEVEL;
                                } else {
                                    join = Paint.Join.ROUND;
                                }
                                t31Var.m = join;
                                float f4 = t31Var.n;
                                if (xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "strokeMiterLimit") != null) {
                                    f4 = typedArrayX2.getFloat(10, f4);
                                }
                                t31Var.n = f4;
                                t31Var.d = la0.x(typedArrayX2, xmlPullParser, theme, "strokeColor", 3);
                                float f5 = t31Var.g;
                                if (xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "strokeAlpha") != null) {
                                    f5 = typedArrayX2.getFloat(11, f5);
                                }
                                t31Var.g = f5;
                                float f6 = t31Var.e;
                                if (xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "strokeWidth") != null) {
                                    f6 = typedArrayX2.getFloat(4, f6);
                                }
                                t31Var.e = f6;
                                float f7 = t31Var.j;
                                if (xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "trimPathEnd") != null) {
                                    f7 = typedArrayX2.getFloat(6, f7);
                                }
                                t31Var.j = f7;
                                float f8 = t31Var.k;
                                if (xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "trimPathOffset") != null) {
                                    f8 = typedArrayX2.getFloat(7, f8);
                                }
                                t31Var.k = f8;
                                float f9 = t31Var.i;
                                if (xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "trimPathStart") != null) {
                                    f9 = typedArrayX2.getFloat(5, f9);
                                }
                                t31Var.i = f9;
                                int i7 = t31Var.c;
                                if (xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "fillType") != null) {
                                    i7 = typedArrayX2.getInt(13, i7);
                                }
                                t31Var.c = i7;
                            }
                            typedArrayX2.recycle();
                            u31Var.b.add(t31Var);
                            if (t31Var.getPathName() != null) {
                                k8Var.put(t31Var.getPathName(), t31Var);
                            }
                            y31Var3.a = y31Var3.a;
                            z4 = false;
                            c = '\b';
                            z = false;
                        } else {
                            x31Var = x31Var3;
                            c = '\b';
                            z = false;
                            if ("clip-path".equals(name)) {
                                s31 s31Var = new s31();
                                if (xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "pathData") != null) {
                                    TypedArray typedArrayX3 = la0.X(resources, theme, attributeSet, l21.j);
                                    String string4 = typedArrayX3.getString(0);
                                    if (string4 != null) {
                                        s31Var.b = string4;
                                    }
                                    String string5 = typedArrayX3.getString(1);
                                    if (string5 != null) {
                                        s31Var.a = o80.q(string5);
                                    }
                                    s31Var.c = !la0.J(xmlPullParser, "fillType") ? 0 : typedArrayX3.getInt(2, 0);
                                    typedArrayX3.recycle();
                                }
                                u31Var.b.add(s31Var);
                                if (s31Var.getPathName() != null) {
                                    k8Var.put(s31Var.getPathName(), s31Var);
                                }
                                y31Var3.a = y31Var3.a;
                            } else if (ApiConstants.GROUP.equals(name)) {
                                u31 u31Var2 = new u31();
                                TypedArray typedArrayX4 = la0.X(resources, theme, attributeSet, l21.h);
                                float f10 = u31Var2.c;
                                if (la0.J(xmlPullParser, "rotation")) {
                                    f10 = typedArrayX4.getFloat(5, f10);
                                }
                                u31Var2.c = f10;
                                u31Var2.d = typedArrayX4.getFloat(1, u31Var2.d);
                                u31Var2.e = typedArrayX4.getFloat(2, u31Var2.e);
                                float f11 = u31Var2.f;
                                if (xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "scaleX") != null) {
                                    f11 = typedArrayX4.getFloat(3, f11);
                                }
                                u31Var2.f = f11;
                                float f12 = u31Var2.g;
                                if (xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "scaleY") != null) {
                                    f12 = typedArrayX4.getFloat(4, f12);
                                }
                                u31Var2.g = f12;
                                float f13 = u31Var2.h;
                                if (xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "translateX") != null) {
                                    f13 = typedArrayX4.getFloat(6, f13);
                                }
                                u31Var2.h = f13;
                                float f14 = u31Var2.i;
                                if (xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "translateY") != null) {
                                    f14 = typedArrayX4.getFloat(7, f14);
                                }
                                u31Var2.i = f14;
                                String string6 = typedArrayX4.getString(0);
                                if (string6 != null) {
                                    u31Var2.k = string6;
                                }
                                u31Var2.c();
                                typedArrayX4.recycle();
                                u31Var.b.add(u31Var2);
                                arrayDeque.push(u31Var2);
                                if (u31Var2.getGroupName() != null) {
                                    k8Var.put(u31Var2.getGroupName(), u31Var2);
                                }
                                y31Var3.a = y31Var3.a;
                            }
                        }
                        i2 = 1;
                    } else {
                        x31Var = x31Var3;
                        i = depth;
                        z = z3;
                        c = '\b';
                        i2 = 1;
                        if (eventType == 3 && ApiConstants.GROUP.equals(xmlPullParser.getName())) {
                            arrayDeque.pop();
                        }
                    }
                    eventType = xmlPullParser.next();
                    i3 = i2;
                    z3 = z;
                    x31Var3 = x31Var;
                    depth = i;
                }
                if (!z4) {
                    this.G = a(y31Var.c, y31Var.d);
                    return;
                }
                throw new XmlPullParserException("no path defined");
            }
            throw new XmlPullParserException(typedArrayX.getPositionDescription() + "<vector> tag requires height > 0");
        }
        throw new XmlPullParserException(typedArrayX.getPositionDescription() + "<vector> tag requires viewportHeight > 0");
    }

    public a41(y31 y31Var) {
        this.J = true;
        this.K = new float[9];
        this.L = new Matrix();
        this.M = new Rect();
        this.F = y31Var;
        this.G = a(y31Var.c, y31Var.d);
    }
}
