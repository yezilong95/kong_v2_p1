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
import java.io.IOException;
import java.util.ArrayDeque;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public final class vq0 extends mq0 {
    public static final PorterDuff.Mode k = PorterDuff.Mode.SRC_IN;
    public tq0 c;
    public PorterDuffColorFilter d;
    public ColorFilter e;
    public boolean f;
    public boolean g;
    public final float[] h;
    public final Matrix i;
    public final Rect j;

    public vq0() {
        this.g = true;
        this.h = new float[9];
        this.i = new Matrix();
        this.j = new Rect();
        tq0 tq0Var = new tq0();
        tq0Var.c = null;
        tq0Var.d = k;
        tq0Var.b = new sq0();
        this.c = tq0Var;
    }

    public final PorterDuffColorFilter a(ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean canApplyTheme() {
        Drawable drawable = this.b;
        if (drawable == null) {
            return false;
        }
        hl.b(drawable);
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        Paint paint;
        Drawable drawable = this.b;
        if (drawable != null) {
            drawable.draw(canvas);
            return;
        }
        Rect rect = this.j;
        copyBounds(rect);
        if (rect.width() <= 0 || rect.height() <= 0) {
            return;
        }
        ColorFilter colorFilter = this.e;
        if (colorFilter == null) {
            colorFilter = this.d;
        }
        Matrix matrix = this.i;
        canvas.getMatrix(matrix);
        float[] fArr = this.h;
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
        if (isAutoMirrored() && il.a(this) == 1) {
            canvas.translate(rect.width(), 0.0f);
            canvas.scale(-1.0f, 1.0f);
        }
        rect.offsetTo(0, 0);
        tq0 tq0Var = this.c;
        Bitmap bitmap = tq0Var.f;
        if (bitmap == null || iMin != bitmap.getWidth() || iMin2 != tq0Var.f.getHeight()) {
            tq0Var.f = Bitmap.createBitmap(iMin, iMin2, Bitmap.Config.ARGB_8888);
            tq0Var.k = true;
        }
        if (this.g) {
            tq0 tq0Var2 = this.c;
            if (tq0Var2.k || tq0Var2.g != tq0Var2.c || tq0Var2.h != tq0Var2.d || tq0Var2.j != tq0Var2.e || tq0Var2.i != tq0Var2.b.getRootAlpha()) {
                tq0 tq0Var3 = this.c;
                tq0Var3.f.eraseColor(0);
                Canvas canvas2 = new Canvas(tq0Var3.f);
                sq0 sq0Var = tq0Var3.b;
                sq0Var.a(sq0Var.g, sq0.p, canvas2, iMin, iMin2);
                tq0 tq0Var4 = this.c;
                tq0Var4.g = tq0Var4.c;
                tq0Var4.h = tq0Var4.d;
                tq0Var4.i = tq0Var4.b.getRootAlpha();
                tq0Var4.j = tq0Var4.e;
                tq0Var4.k = false;
            }
        } else {
            tq0 tq0Var5 = this.c;
            tq0Var5.f.eraseColor(0);
            Canvas canvas3 = new Canvas(tq0Var5.f);
            sq0 sq0Var2 = tq0Var5.b;
            sq0Var2.a(sq0Var2.g, sq0.p, canvas3, iMin, iMin2);
        }
        tq0 tq0Var6 = this.c;
        if (tq0Var6.b.getRootAlpha() >= 255 && colorFilter == null) {
            paint = null;
        } else {
            if (tq0Var6.l == null) {
                Paint paint2 = new Paint();
                tq0Var6.l = paint2;
                paint2.setFilterBitmap(true);
            }
            tq0Var6.l.setAlpha(tq0Var6.b.getRootAlpha());
            tq0Var6.l.setColorFilter(colorFilter);
            paint = tq0Var6.l;
        }
        canvas.drawBitmap(tq0Var6.f, (Rect) null, rect, paint);
        canvas.restoreToCount(iSave);
    }

    @Override // android.graphics.drawable.Drawable
    public final int getAlpha() {
        Drawable drawable = this.b;
        return drawable != null ? drawable.getAlpha() : this.c.b.getRootAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getChangingConfigurations() {
        Drawable drawable = this.b;
        return drawable != null ? drawable.getChangingConfigurations() : super.getChangingConfigurations() | this.c.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public final ColorFilter getColorFilter() {
        Drawable drawable = this.b;
        return drawable != null ? hl.c(drawable) : this.e;
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        if (this.b != null) {
            return new uq0(this.b.getConstantState());
        }
        this.c.a = getChangingConfigurations();
        return this.c;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        Drawable drawable = this.b;
        return drawable != null ? drawable.getIntrinsicHeight() : (int) this.c.b.i;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        Drawable drawable = this.b;
        return drawable != null ? drawable.getIntrinsicWidth() : (int) this.c.b.h;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        Drawable drawable = this.b;
        if (drawable != null) {
            return drawable.getOpacity();
        }
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public final void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        Drawable drawable = this.b;
        if (drawable != null) {
            drawable.inflate(resources, xmlPullParser, attributeSet);
        } else {
            inflate(resources, xmlPullParser, attributeSet, null);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void invalidateSelf() {
        Drawable drawable = this.b;
        if (drawable != null) {
            drawable.invalidateSelf();
        } else {
            super.invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean isAutoMirrored() {
        Drawable drawable = this.b;
        return drawable != null ? drawable.isAutoMirrored() : this.c.e;
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean isStateful() {
        Drawable drawable = this.b;
        if (drawable != null) {
            return drawable.isStateful();
        }
        if (super.isStateful()) {
            return true;
        }
        tq0 tq0Var = this.c;
        if (tq0Var == null) {
            return false;
        }
        sq0 sq0Var = tq0Var.b;
        if (sq0Var.n == null) {
            sq0Var.n = Boolean.valueOf(sq0Var.g.a());
        }
        if (sq0Var.n.booleanValue()) {
            return true;
        }
        ColorStateList colorStateList = this.c.c;
        return colorStateList != null && colorStateList.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable mutate() {
        Drawable drawable = this.b;
        if (drawable != null) {
            drawable.mutate();
            return this;
        }
        if (!this.f && super.mutate() == this) {
            tq0 tq0Var = this.c;
            tq0 tq0Var2 = new tq0();
            tq0Var2.c = null;
            tq0Var2.d = k;
            if (tq0Var != null) {
                tq0Var2.a = tq0Var.a;
                sq0 sq0Var = new sq0(tq0Var.b);
                tq0Var2.b = sq0Var;
                if (tq0Var.b.e != null) {
                    sq0Var.e = new Paint(tq0Var.b.e);
                }
                if (tq0Var.b.d != null) {
                    tq0Var2.b.d = new Paint(tq0Var.b.d);
                }
                tq0Var2.c = tq0Var.c;
                tq0Var2.d = tq0Var.d;
                tq0Var2.e = tq0Var.e;
            }
            this.c = tq0Var2;
            this.f = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public final void onBoundsChange(Rect rect) {
        Drawable drawable = this.b;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean onStateChange(int[] iArr) {
        boolean z;
        PorterDuff.Mode mode;
        Drawable drawable = this.b;
        if (drawable != null) {
            return drawable.setState(iArr);
        }
        tq0 tq0Var = this.c;
        ColorStateList colorStateList = tq0Var.c;
        if (colorStateList == null || (mode = tq0Var.d) == null) {
            z = false;
        } else {
            this.d = a(colorStateList, mode);
            invalidateSelf();
            z = true;
        }
        sq0 sq0Var = tq0Var.b;
        if (sq0Var.n == null) {
            sq0Var.n = Boolean.valueOf(sq0Var.g.a());
        }
        if (sq0Var.n.booleanValue()) {
            boolean zB = tq0Var.b.g.b(iArr);
            tq0Var.k |= zB;
            if (zB) {
                invalidateSelf();
                return true;
            }
        }
        return z;
    }

    @Override // android.graphics.drawable.Drawable
    public final void scheduleSelf(Runnable runnable, long j) {
        Drawable drawable = this.b;
        if (drawable != null) {
            drawable.scheduleSelf(runnable, j);
        } else {
            super.scheduleSelf(runnable, j);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        Drawable drawable = this.b;
        if (drawable != null) {
            drawable.setAlpha(i);
        } else if (this.c.b.getRootAlpha() != i) {
            this.c.b.setRootAlpha(i);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAutoMirrored(boolean z) {
        Drawable drawable = this.b;
        if (drawable != null) {
            drawable.setAutoMirrored(z);
        } else {
            this.c.e = z;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.b;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        } else {
            this.e = colorFilter;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTint(int i) {
        Drawable drawable = this.b;
        if (drawable != null) {
            nv0.N(drawable, i);
        } else {
            setTintList(ColorStateList.valueOf(i));
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.b;
        if (drawable != null) {
            hl.h(drawable, colorStateList);
            return;
        }
        tq0 tq0Var = this.c;
        if (tq0Var.c != colorStateList) {
            tq0Var.c = colorStateList;
            this.d = a(colorStateList, tq0Var.d);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.b;
        if (drawable != null) {
            hl.i(drawable, mode);
            return;
        }
        tq0 tq0Var = this.c;
        if (tq0Var.d != mode) {
            tq0Var.d = mode;
            this.d = a(tq0Var.c, mode);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean setVisible(boolean z, boolean z2) {
        Drawable drawable = this.b;
        return drawable != null ? drawable.setVisible(z, z2) : super.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Drawable
    public final void unscheduleSelf(Runnable runnable) {
        Drawable drawable = this.b;
        if (drawable != null) {
            drawable.unscheduleSelf(runnable);
        } else {
            super.unscheduleSelf(runnable);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        sq0 sq0Var;
        int i;
        boolean z;
        char c;
        int i2;
        Paint.Cap cap;
        Paint.Join join;
        Drawable drawable = this.b;
        if (drawable != null) {
            hl.d(drawable, resources, xmlPullParser, attributeSet, theme);
            return;
        }
        tq0 tq0Var = this.c;
        tq0Var.b = new sq0();
        TypedArray typedArrayD = za0.D(resources, theme, attributeSet, xi.a);
        tq0 tq0Var2 = this.c;
        sq0 sq0Var2 = tq0Var2.b;
        int iQ = za0.q(typedArrayD, xmlPullParser, "tintMode", 6, -1);
        PorterDuff.Mode mode = PorterDuff.Mode.SRC_IN;
        if (iQ == 3) {
            mode = PorterDuff.Mode.SRC_OVER;
        } else if (iQ != 5) {
            if (iQ != 9) {
                switch (iQ) {
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
        tq0Var2.d = mode;
        int i3 = 1;
        ColorStateList colorStateListA = null;
        if (xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "tint") != null) {
            TypedValue typedValue = new TypedValue();
            typedArrayD.getValue(1, typedValue);
            int i4 = typedValue.type;
            if (i4 == 2) {
                throw new UnsupportedOperationException("Failed to resolve attribute at index 1: " + typedValue);
            }
            if (i4 >= 28 && i4 <= 31) {
                colorStateListA = ColorStateList.valueOf(typedValue.data);
            } else {
                Resources resources2 = typedArrayD.getResources();
                int resourceId = typedArrayD.getResourceId(1, 0);
                ThreadLocal threadLocal = ie.a;
                try {
                    colorStateListA = ie.a(resources2, resources2.getXml(resourceId), theme);
                } catch (Exception e) {
                    Log.e("CSLCompat", "Failed to inflate ColorStateList.", e);
                }
            }
        }
        ColorStateList colorStateList = colorStateListA;
        if (colorStateList != null) {
            tq0Var2.c = colorStateList;
        }
        boolean z2 = tq0Var2.e;
        if (xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "autoMirrored") != null) {
            z2 = typedArrayD.getBoolean(5, z2);
        }
        tq0Var2.e = z2;
        float f = sq0Var2.j;
        if (xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "viewportWidth") != null) {
            f = typedArrayD.getFloat(7, f);
        }
        sq0Var2.j = f;
        float f2 = sq0Var2.k;
        if (xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "viewportHeight") != null) {
            f2 = typedArrayD.getFloat(8, f2);
        }
        sq0Var2.k = f2;
        boolean z3 = false;
        if (sq0Var2.j <= 0.0f) {
            throw new XmlPullParserException(typedArrayD.getPositionDescription() + "<vector> tag requires viewportWidth > 0");
        }
        if (f2 > 0.0f) {
            sq0Var2.h = typedArrayD.getDimension(3, sq0Var2.h);
            float dimension = typedArrayD.getDimension(2, sq0Var2.i);
            sq0Var2.i = dimension;
            if (sq0Var2.h <= 0.0f) {
                throw new XmlPullParserException(typedArrayD.getPositionDescription() + "<vector> tag requires width > 0");
            }
            if (dimension > 0.0f) {
                float alpha = sq0Var2.getAlpha();
                if (xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "alpha") != null) {
                    alpha = typedArrayD.getFloat(4, alpha);
                }
                sq0Var2.setAlpha(alpha);
                String string = typedArrayD.getString(0);
                if (string != null) {
                    sq0Var2.m = string;
                    sq0Var2.o.put(string, sq0Var2);
                }
                typedArrayD.recycle();
                tq0Var.a = getChangingConfigurations();
                tq0Var.k = true;
                tq0 tq0Var3 = this.c;
                sq0 sq0Var3 = tq0Var3.b;
                ArrayDeque arrayDeque = new ArrayDeque();
                arrayDeque.push(sq0Var3.g);
                int eventType = xmlPullParser.getEventType();
                int depth = xmlPullParser.getDepth() + 1;
                boolean z4 = true;
                while (eventType != i3 && (xmlPullParser.getDepth() >= depth || eventType != 3)) {
                    if (eventType == 2) {
                        String name = xmlPullParser.getName();
                        pq0 pq0Var = (pq0) arrayDeque.peek();
                        boolean zEquals = "path".equals(name);
                        i = depth;
                        c7 c7Var = sq0Var3.o;
                        if (zEquals) {
                            oq0 oq0Var = new oq0();
                            oq0Var.e = 0.0f;
                            oq0Var.g = 1.0f;
                            oq0Var.h = 1.0f;
                            sq0Var = sq0Var3;
                            oq0Var.i = 0.0f;
                            oq0Var.j = 1.0f;
                            oq0Var.k = 0.0f;
                            Paint.Cap cap2 = Paint.Cap.BUTT;
                            oq0Var.l = cap2;
                            Paint.Join join2 = Paint.Join.MITER;
                            oq0Var.m = join2;
                            oq0Var.n = 4.0f;
                            TypedArray typedArrayD2 = za0.D(resources, theme, attributeSet, xi.c);
                            if (xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "pathData") != null) {
                                String string2 = typedArrayD2.getString(0);
                                if (string2 != null) {
                                    oq0Var.b = string2;
                                }
                                String string3 = typedArrayD2.getString(2);
                                if (string3 != null) {
                                    oq0Var.a = jq0.k(string3);
                                }
                                oq0Var.f = za0.p(typedArrayD2, xmlPullParser, theme, "fillColor", 1);
                                float f3 = oq0Var.h;
                                if (xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "fillAlpha") != null) {
                                    f3 = typedArrayD2.getFloat(12, f3);
                                }
                                oq0Var.h = f3;
                                int i5 = xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "strokeLineCap") != null ? typedArrayD2.getInt(8, -1) : -1;
                                Paint.Cap cap3 = oq0Var.l;
                                if (i5 == 0) {
                                    cap = cap2;
                                } else if (i5 != 1) {
                                    cap = i5 != 2 ? cap3 : Paint.Cap.SQUARE;
                                } else {
                                    cap = Paint.Cap.ROUND;
                                }
                                oq0Var.l = cap;
                                int i6 = xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "strokeLineJoin") != null ? typedArrayD2.getInt(9, -1) : -1;
                                Paint.Join join3 = oq0Var.m;
                                if (i6 == 0) {
                                    join = join2;
                                } else if (i6 != 1) {
                                    join = i6 != 2 ? join3 : Paint.Join.BEVEL;
                                } else {
                                    join = Paint.Join.ROUND;
                                }
                                oq0Var.m = join;
                                float f4 = oq0Var.n;
                                if (xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "strokeMiterLimit") != null) {
                                    f4 = typedArrayD2.getFloat(10, f4);
                                }
                                oq0Var.n = f4;
                                oq0Var.d = za0.p(typedArrayD2, xmlPullParser, theme, "strokeColor", 3);
                                float f5 = oq0Var.g;
                                if (xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "strokeAlpha") != null) {
                                    f5 = typedArrayD2.getFloat(11, f5);
                                }
                                oq0Var.g = f5;
                                float f6 = oq0Var.e;
                                if (xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "strokeWidth") != null) {
                                    f6 = typedArrayD2.getFloat(4, f6);
                                }
                                oq0Var.e = f6;
                                float f7 = oq0Var.j;
                                if (xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "trimPathEnd") != null) {
                                    f7 = typedArrayD2.getFloat(6, f7);
                                }
                                oq0Var.j = f7;
                                float f8 = oq0Var.k;
                                if (xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "trimPathOffset") != null) {
                                    f8 = typedArrayD2.getFloat(7, f8);
                                }
                                oq0Var.k = f8;
                                float f9 = oq0Var.i;
                                if (xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "trimPathStart") != null) {
                                    f9 = typedArrayD2.getFloat(5, f9);
                                }
                                oq0Var.i = f9;
                                int i7 = oq0Var.c;
                                if (xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "fillType") != null) {
                                    i7 = typedArrayD2.getInt(13, i7);
                                }
                                oq0Var.c = i7;
                            }
                            typedArrayD2.recycle();
                            pq0Var.b.add(oq0Var);
                            if (oq0Var.getPathName() != null) {
                                c7Var.put(oq0Var.getPathName(), oq0Var);
                            }
                            tq0Var3.a = tq0Var3.a;
                            z4 = false;
                            c = '\b';
                            z = false;
                        } else {
                            sq0Var = sq0Var3;
                            c = '\b';
                            z = false;
                            if ("clip-path".equals(name)) {
                                nq0 nq0Var = new nq0();
                                if (xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "pathData") != null) {
                                    TypedArray typedArrayD3 = za0.D(resources, theme, attributeSet, xi.d);
                                    String string4 = typedArrayD3.getString(0);
                                    if (string4 != null) {
                                        nq0Var.b = string4;
                                    }
                                    String string5 = typedArrayD3.getString(1);
                                    if (string5 != null) {
                                        nq0Var.a = jq0.k(string5);
                                    }
                                    nq0Var.c = !za0.u(xmlPullParser, "fillType") ? 0 : typedArrayD3.getInt(2, 0);
                                    typedArrayD3.recycle();
                                }
                                pq0Var.b.add(nq0Var);
                                if (nq0Var.getPathName() != null) {
                                    c7Var.put(nq0Var.getPathName(), nq0Var);
                                }
                                tq0Var3.a = tq0Var3.a;
                            } else if ("group".equals(name)) {
                                pq0 pq0Var2 = new pq0();
                                TypedArray typedArrayD4 = za0.D(resources, theme, attributeSet, xi.b);
                                float f10 = pq0Var2.c;
                                if (za0.u(xmlPullParser, "rotation")) {
                                    f10 = typedArrayD4.getFloat(5, f10);
                                }
                                pq0Var2.c = f10;
                                pq0Var2.d = typedArrayD4.getFloat(1, pq0Var2.d);
                                pq0Var2.e = typedArrayD4.getFloat(2, pq0Var2.e);
                                float f11 = pq0Var2.f;
                                if (xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "scaleX") != null) {
                                    f11 = typedArrayD4.getFloat(3, f11);
                                }
                                pq0Var2.f = f11;
                                float f12 = pq0Var2.g;
                                if (xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "scaleY") != null) {
                                    f12 = typedArrayD4.getFloat(4, f12);
                                }
                                pq0Var2.g = f12;
                                float f13 = pq0Var2.h;
                                if (xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "translateX") != null) {
                                    f13 = typedArrayD4.getFloat(6, f13);
                                }
                                pq0Var2.h = f13;
                                float f14 = pq0Var2.i;
                                if (xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "translateY") != null) {
                                    f14 = typedArrayD4.getFloat(7, f14);
                                }
                                pq0Var2.i = f14;
                                String string6 = typedArrayD4.getString(0);
                                if (string6 != null) {
                                    pq0Var2.k = string6;
                                }
                                pq0Var2.c();
                                typedArrayD4.recycle();
                                pq0Var.b.add(pq0Var2);
                                arrayDeque.push(pq0Var2);
                                if (pq0Var2.getGroupName() != null) {
                                    c7Var.put(pq0Var2.getGroupName(), pq0Var2);
                                }
                                tq0Var3.a = tq0Var3.a;
                            }
                        }
                        i2 = 1;
                    } else {
                        sq0Var = sq0Var3;
                        i = depth;
                        z = z3;
                        c = '\b';
                        i2 = 1;
                        if (eventType == 3 && "group".equals(xmlPullParser.getName())) {
                            arrayDeque.pop();
                        }
                    }
                    eventType = xmlPullParser.next();
                    i3 = i2;
                    z3 = z;
                    sq0Var3 = sq0Var;
                    depth = i;
                }
                if (!z4) {
                    this.d = a(tq0Var.c, tq0Var.d);
                    return;
                }
                throw new XmlPullParserException("no path defined");
            }
            throw new XmlPullParserException(typedArrayD.getPositionDescription() + "<vector> tag requires height > 0");
        }
        throw new XmlPullParserException(typedArrayD.getPositionDescription() + "<vector> tag requires viewportHeight > 0");
    }

    public vq0(tq0 tq0Var) {
        this.g = true;
        this.h = new float[9];
        this.i = new Matrix();
        this.j = new Rect();
        this.c = tq0Var;
        this.d = a(tq0Var.c, tq0Var.d);
    }
}
