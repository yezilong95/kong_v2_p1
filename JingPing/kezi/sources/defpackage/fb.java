package defpackage;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Property;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.work.impl.WorkDatabaseVersions;
import com.google.android.material.progressindicator.CircularProgressIndicatorSpec;
import java.util.Arrays;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class fb extends Property {
    public final /* synthetic */ int a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ fb(Class cls, String str, int i) {
        super(cls, str);
        this.a = i;
    }

    @Override // android.util.Property
    public final Object get(Object obj) {
        switch (this.a) {
            case 0:
                return null;
            case 1:
                return null;
            case 2:
                return null;
            case 3:
                return null;
            case 4:
                return null;
            case 5:
                return null;
            case 6:
                return null;
            case 7:
                return null;
            case 8:
                return Float.valueOf(((vc) obj).i);
            case WorkDatabaseVersions.VERSION_9 /* 9 */:
                return Float.valueOf(((vc) obj).j);
            case WorkDatabaseVersions.VERSION_10 /* 10 */:
                return Float.valueOf(((nl) obj).b());
            case WorkDatabaseVersions.VERSION_11 /* 11 */:
                return Float.valueOf(((View) obj).getLayoutParams().width);
            case WorkDatabaseVersions.VERSION_12 /* 12 */:
                return Float.valueOf(((View) obj).getLayoutParams().height);
            case WorkDatabaseVersions.VERSION_13 /* 13 */:
                WeakHashMap weakHashMap = xr0.a;
                return Float.valueOf(((View) obj).getPaddingStart());
            case WorkDatabaseVersions.VERSION_14 /* 14 */:
                WeakHashMap weakHashMap2 = xr0.a;
                return Float.valueOf(((View) obj).getPaddingEnd());
            case WorkDatabaseVersions.VERSION_15 /* 15 */:
                return Float.valueOf(((a20) obj).i);
            case 16:
                return Float.valueOf(((c20) obj).j);
            case 17:
                return Float.valueOf(((SwitchCompat) obj).A);
            case 18:
                return Float.valueOf(jt0.a.F((View) obj));
            default:
                WeakHashMap weakHashMap3 = xr0.a;
                return ((View) obj).getClipBounds();
        }
    }

    @Override // android.util.Property
    public final void set(Object obj, Object obj2) {
        switch (this.a) {
            case 0:
                jb jbVar = (jb) obj;
                PointF pointF = (PointF) obj2;
                jbVar.getClass();
                jbVar.a = Math.round(pointF.x);
                int iRound = Math.round(pointF.y);
                jbVar.b = iRound;
                int i = jbVar.f + 1;
                jbVar.f = i;
                if (i == jbVar.g) {
                    jt0.a(jbVar.e, jbVar.a, iRound, jbVar.c, jbVar.d);
                    jbVar.f = 0;
                    jbVar.g = 0;
                }
                break;
            case 1:
                jb jbVar2 = (jb) obj;
                PointF pointF2 = (PointF) obj2;
                jbVar2.getClass();
                jbVar2.c = Math.round(pointF2.x);
                int iRound2 = Math.round(pointF2.y);
                jbVar2.d = iRound2;
                int i2 = jbVar2.g + 1;
                jbVar2.g = i2;
                if (jbVar2.f == i2) {
                    jt0.a(jbVar2.e, jbVar2.a, jbVar2.b, jbVar2.c, iRound2);
                    jbVar2.f = 0;
                    jbVar2.g = 0;
                }
                break;
            case 2:
                View view = (View) obj;
                PointF pointF3 = (PointF) obj2;
                jt0.a(view, view.getLeft(), view.getTop(), Math.round(pointF3.x), Math.round(pointF3.y));
                break;
            case 3:
                View view2 = (View) obj;
                PointF pointF4 = (PointF) obj2;
                jt0.a(view2, Math.round(pointF4.x), Math.round(pointF4.y), view2.getRight(), view2.getBottom());
                break;
            case 4:
                View view3 = (View) obj;
                PointF pointF5 = (PointF) obj2;
                int iRound3 = Math.round(pointF5.x);
                int iRound4 = Math.round(pointF5.y);
                jt0.a(view3, iRound3, iRound4, view3.getWidth() + iRound3, view3.getHeight() + iRound4);
                break;
            case 5:
                ImageView imageView = (ImageView) obj;
                Matrix matrix = (Matrix) obj2;
                if (Build.VERSION.SDK_INT >= 29) {
                    imageView.animateTransform(matrix);
                } else if (matrix == null) {
                    Drawable drawable = imageView.getDrawable();
                    if (drawable != null) {
                        drawable.setBounds(0, 0, (imageView.getWidth() - imageView.getPaddingLeft()) - imageView.getPaddingRight(), (imageView.getHeight() - imageView.getPaddingTop()) - imageView.getPaddingBottom());
                        imageView.invalidate();
                    }
                } else if (k9.l) {
                    try {
                        imageView.animateTransform(matrix);
                    } catch (NoSuchMethodError unused) {
                        k9.l = false;
                        return;
                    }
                }
                break;
            case 6:
                ob obVar = (ob) obj;
                float[] fArr = (float[]) obj2;
                obVar.getClass();
                System.arraycopy(fArr, 0, obVar.c, 0, fArr.length);
                obVar.a();
                break;
            case 7:
                ob obVar2 = (ob) obj;
                PointF pointF6 = (PointF) obj2;
                obVar2.getClass();
                obVar2.d = pointF6.x;
                obVar2.e = pointF6.y;
                obVar2.a();
                break;
            case 8:
                vc vcVar = (vc) obj;
                float fFloatValue = ((Float) obj2).floatValue();
                vcVar.i = fFloatValue;
                int i3 = (int) (5400.0f * fFloatValue);
                float f = fFloatValue * 1520.0f;
                float[] fArr2 = (float[]) vcVar.b;
                fArr2[0] = (-20.0f) + f;
                fArr2[1] = f;
                int i4 = 0;
                while (true) {
                    mp mpVar = vcVar.f;
                    if (i4 >= 4) {
                        float f2 = fArr2[0];
                        float f3 = fArr2[1];
                        float f4 = ((f3 - f2) * vcVar.j) + f2;
                        fArr2[0] = f4;
                        fArr2[0] = f4 / 360.0f;
                        fArr2[1] = f3 / 360.0f;
                        int i5 = 0;
                        while (true) {
                            if (i5 < 4) {
                                float f5 = (i3 - vc.n[i5]) / 333;
                                if (f5 < 0.0f || f5 > 1.0f) {
                                    i5++;
                                } else {
                                    int i6 = i5 + vcVar.h;
                                    CircularProgressIndicatorSpec circularProgressIndicatorSpec = vcVar.g;
                                    int[] iArr = circularProgressIndicatorSpec.c;
                                    int length = i6 % iArr.length;
                                    ((int[]) vcVar.c)[0] = u6.a(mpVar.getInterpolation(f5), Integer.valueOf(k9.j(iArr[length], ((vx) vcVar.a).k)), Integer.valueOf(k9.j(circularProgressIndicatorSpec.c[(1 + length) % iArr.length], ((vx) vcVar.a).k))).intValue();
                                }
                            }
                        }
                        ((vx) vcVar.a).invalidateSelf();
                    } else {
                        float f6 = 667;
                        fArr2[1] = (mpVar.getInterpolation((i3 - vc.l[i4]) / f6) * 250.0f) + fArr2[1];
                        fArr2[0] = (mpVar.getInterpolation((i3 - vc.m[i4]) / f6) * 250.0f) + fArr2[0];
                        i4++;
                    }
                    break;
                }
                break;
            case WorkDatabaseVersions.VERSION_9 /* 9 */:
                ((vc) obj).j = ((Float) obj2).floatValue();
                break;
            case WorkDatabaseVersions.VERSION_10 /* 10 */:
                nl nlVar = (nl) obj;
                float fFloatValue2 = ((Float) obj2).floatValue();
                if (nlVar.i != fFloatValue2) {
                    nlVar.i = fFloatValue2;
                    nlVar.invalidateSelf();
                }
                break;
            case WorkDatabaseVersions.VERSION_11 /* 11 */:
                View view4 = (View) obj;
                view4.getLayoutParams().width = ((Float) obj2).intValue();
                view4.requestLayout();
                break;
            case WorkDatabaseVersions.VERSION_12 /* 12 */:
                View view5 = (View) obj;
                view5.getLayoutParams().height = ((Float) obj2).intValue();
                view5.requestLayout();
                break;
            case WorkDatabaseVersions.VERSION_13 /* 13 */:
                View view6 = (View) obj;
                int iIntValue = ((Float) obj2).intValue();
                int paddingTop = view6.getPaddingTop();
                WeakHashMap weakHashMap = xr0.a;
                view6.setPaddingRelative(iIntValue, paddingTop, view6.getPaddingEnd(), view6.getPaddingBottom());
                break;
            case WorkDatabaseVersions.VERSION_14 /* 14 */:
                View view7 = (View) obj;
                WeakHashMap weakHashMap2 = xr0.a;
                view7.setPaddingRelative(view7.getPaddingStart(), view7.getPaddingTop(), ((Float) obj2).intValue(), view7.getPaddingBottom());
                break;
            case WorkDatabaseVersions.VERSION_15 /* 15 */:
                a20 a20Var = (a20) obj;
                a20Var.i = ((Float) obj2).floatValue();
                float[] fArr3 = (float[]) a20Var.b;
                fArr3[0] = 0.0f;
                float f7 = ((int) (r15 * 333.0f)) / 667;
                mp mpVar2 = a20Var.e;
                float interpolation = mpVar2.getInterpolation(f7);
                fArr3[2] = interpolation;
                fArr3[1] = interpolation;
                float interpolation2 = mpVar2.getInterpolation(f7 + 0.49925038f);
                fArr3[4] = interpolation2;
                fArr3[3] = interpolation2;
                fArr3[5] = 1.0f;
                if (a20Var.h && interpolation2 < 1.0f) {
                    int[] iArr2 = (int[]) a20Var.c;
                    iArr2[2] = iArr2[1];
                    iArr2[1] = iArr2[0];
                    iArr2[0] = k9.j(a20Var.f.c[a20Var.g], ((vx) a20Var.a).k);
                    a20Var.h = false;
                }
                ((vx) a20Var.a).invalidateSelf();
                break;
            case 16:
                c20 c20Var = (c20) obj;
                float fFloatValue3 = ((Float) obj2).floatValue();
                c20Var.j = fFloatValue3;
                int i7 = (int) (fFloatValue3 * 1800.0f);
                for (int i8 = 0; i8 < 4; i8++) {
                    ((float[]) c20Var.b)[i8] = Math.max(0.0f, Math.min(1.0f, c20Var.f[i8].getInterpolation((i7 - c20.m[i8]) / c20.l[i8])));
                }
                if (c20Var.i) {
                    Arrays.fill((int[]) c20Var.c, k9.j(c20Var.g.c[c20Var.h], ((vx) c20Var.a).k));
                    c20Var.i = false;
                }
                ((vx) c20Var.a).invalidateSelf();
                break;
            case 17:
                ((SwitchCompat) obj).setThumbPosition(((Float) obj2).floatValue());
                break;
            case 18:
                jt0.b((View) obj, ((Float) obj2).floatValue());
                break;
            default:
                WeakHashMap weakHashMap3 = xr0.a;
                ((View) obj).setClipBounds((Rect) obj2);
                break;
        }
    }
}
