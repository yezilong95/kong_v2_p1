package defpackage;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.Property;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.work.impl.WorkDatabaseVersions;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class yc extends Property {
    public final /* synthetic */ int a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ yc(Class cls, String str, int i) {
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
                return Float.valueOf(((pe) obj).h);
            case WorkDatabaseVersions.VERSION_9 /* 9 */:
                return Float.valueOf(((pe) obj).i);
            case 10:
                return Float.valueOf(((qp) obj).b());
            case WorkDatabaseVersions.VERSION_11 /* 11 */:
                return Float.valueOf(((View) obj).getLayoutParams().width);
            case WorkDatabaseVersions.VERSION_12 /* 12 */:
                return Float.valueOf(((View) obj).getLayoutParams().height);
            case WorkDatabaseVersions.VERSION_13 /* 13 */:
                WeakHashMap weakHashMap = f51.a;
                return Float.valueOf(((View) obj).getPaddingStart());
            case WorkDatabaseVersions.VERSION_14 /* 14 */:
                WeakHashMap weakHashMap2 = f51.a;
                return Float.valueOf(((View) obj).getPaddingEnd());
            case WorkDatabaseVersions.VERSION_15 /* 15 */:
                return Float.valueOf(((e90) obj).h);
            case 16:
                return Float.valueOf(((g90) obj).i);
            case 17:
                return Float.valueOf(((SwitchCompat) obj).g0);
            case 18:
                return Float.valueOf(p61.a.H((View) obj));
            default:
                return ((View) obj).getClipBounds();
        }
    }

    @Override // android.util.Property
    public final void set(Object obj, Object obj2) {
        switch (this.a) {
            case 0:
                cd cdVar = (cd) obj;
                PointF pointF = (PointF) obj2;
                cdVar.getClass();
                cdVar.a = Math.round(pointF.x);
                int iRound = Math.round(pointF.y);
                cdVar.b = iRound;
                int i = cdVar.f + 1;
                cdVar.f = i;
                if (i == cdVar.g) {
                    p61.a(cdVar.e, cdVar.a, iRound, cdVar.c, cdVar.d);
                    cdVar.f = 0;
                    cdVar.g = 0;
                }
                break;
            case 1:
                cd cdVar2 = (cd) obj;
                PointF pointF2 = (PointF) obj2;
                cdVar2.getClass();
                cdVar2.c = Math.round(pointF2.x);
                int iRound2 = Math.round(pointF2.y);
                cdVar2.d = iRound2;
                int i2 = cdVar2.g + 1;
                cdVar2.g = i2;
                if (cdVar2.f == i2) {
                    p61.a(cdVar2.e, cdVar2.a, cdVar2.b, cdVar2.c, iRound2);
                    cdVar2.f = 0;
                    cdVar2.g = 0;
                }
                break;
            case 2:
                View view = (View) obj;
                PointF pointF3 = (PointF) obj2;
                p61.a(view, view.getLeft(), view.getTop(), Math.round(pointF3.x), Math.round(pointF3.y));
                break;
            case 3:
                View view2 = (View) obj;
                PointF pointF4 = (PointF) obj2;
                p61.a(view2, Math.round(pointF4.x), Math.round(pointF4.y), view2.getRight(), view2.getBottom());
                break;
            case 4:
                View view3 = (View) obj;
                PointF pointF5 = (PointF) obj2;
                int iRound3 = Math.round(pointF5.x);
                int iRound4 = Math.round(pointF5.y);
                p61.a(view3, iRound3, iRound4, view3.getWidth() + iRound3, view3.getHeight() + iRound4);
                break;
            case 5:
                j31.g((ImageView) obj, (Matrix) obj2);
                break;
            case 6:
                id idVar = (id) obj;
                float[] fArr = (float[]) obj2;
                idVar.getClass();
                System.arraycopy(fArr, 0, idVar.c, 0, fArr.length);
                idVar.a();
                break;
            case 7:
                id idVar2 = (id) obj;
                PointF pointF6 = (PointF) obj2;
                idVar2.getClass();
                idVar2.d = pointF6.x;
                idVar2.e = pointF6.y;
                idVar2.a();
                break;
            case 8:
                pe peVar = (pe) obj;
                float fFloatValue = ((Float) obj2).floatValue();
                peVar.h = fFloatValue;
                int i3 = (int) (fFloatValue * 5400.0f);
                ArrayList arrayList = (ArrayList) peVar.b;
                zp zpVar = (zp) arrayList.get(0);
                float f = peVar.h * 1520.0f;
                zpVar.a = (-20.0f) + f;
                zpVar.b = f;
                int i4 = 0;
                while (true) {
                    sv svVar = peVar.e;
                    if (i4 >= 4) {
                        float f2 = zpVar.a;
                        float f3 = zpVar.b;
                        zpVar.a = (((f3 - f2) * peVar.i) + f2) / 360.0f;
                        zpVar.b = f3 / 360.0f;
                        int i5 = 0;
                        while (true) {
                            if (i5 < 4) {
                                float fH = z5.h(i3, pe.m[i5], 333);
                                if (fH < 0.0f || fH > 1.0f) {
                                    i5++;
                                } else {
                                    int i6 = i5 + peVar.g;
                                    int[] iArr = peVar.f.c;
                                    int length = i6 % iArr.length;
                                    int length2 = (1 + length) % iArr.length;
                                    int i7 = iArr[length];
                                    int i8 = iArr[length2];
                                    ((zp) arrayList.get(0)).c = f8.a(svVar.getInterpolation(fH), Integer.valueOf(i7), Integer.valueOf(i8)).intValue();
                                }
                            }
                        }
                        ((a40) peVar.a).invalidateSelf();
                    } else {
                        zpVar.b = (svVar.getInterpolation(z5.h(i3, pe.k[i4], 667)) * 250.0f) + zpVar.b;
                        zpVar.a = (svVar.getInterpolation(z5.h(i3, pe.l[i4], 667)) * 250.0f) + zpVar.a;
                        i4++;
                    }
                    break;
                }
                break;
            case WorkDatabaseVersions.VERSION_9 /* 9 */:
                ((pe) obj).i = ((Float) obj2).floatValue();
                break;
            case 10:
                qp qpVar = (qp) obj;
                float fFloatValue2 = ((Float) obj2).floatValue();
                if (qpVar.L != fFloatValue2) {
                    qpVar.L = fFloatValue2;
                    qpVar.invalidateSelf();
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
                WeakHashMap weakHashMap = f51.a;
                view6.setPaddingRelative(iIntValue, paddingTop, view6.getPaddingEnd(), view6.getPaddingBottom());
                break;
            case WorkDatabaseVersions.VERSION_14 /* 14 */:
                View view7 = (View) obj;
                WeakHashMap weakHashMap2 = f51.a;
                view7.setPaddingRelative(view7.getPaddingStart(), view7.getPaddingTop(), ((Float) obj2).intValue(), view7.getPaddingBottom());
                break;
            case WorkDatabaseVersions.VERSION_15 /* 15 */:
                e90 e90Var = (e90) obj;
                float fFloatValue3 = ((Float) obj2).floatValue();
                e90Var.h = fFloatValue3;
                ArrayList arrayList2 = (ArrayList) e90Var.b;
                ((zp) arrayList2.get(0)).a = 0.0f;
                float fH2 = z5.h((int) (fFloatValue3 * 333.0f), 0, 667);
                zp zpVar2 = (zp) arrayList2.get(0);
                zp zpVar3 = (zp) arrayList2.get(1);
                sv svVar2 = e90Var.d;
                float interpolation = svVar2.getInterpolation(fH2);
                zpVar3.a = interpolation;
                zpVar2.b = interpolation;
                zp zpVar4 = (zp) arrayList2.get(1);
                zp zpVar5 = (zp) arrayList2.get(2);
                float interpolation2 = svVar2.getInterpolation(fH2 + 0.49925038f);
                zpVar5.a = interpolation2;
                zpVar4.b = interpolation2;
                ((zp) arrayList2.get(2)).b = 1.0f;
                if (e90Var.g && ((zp) arrayList2.get(1)).b < 1.0f) {
                    ((zp) arrayList2.get(2)).c = ((zp) arrayList2.get(1)).c;
                    ((zp) arrayList2.get(1)).c = ((zp) arrayList2.get(0)).c;
                    ((zp) arrayList2.get(0)).c = e90Var.e.c[e90Var.f];
                    e90Var.g = false;
                }
                ((a40) e90Var.a).invalidateSelf();
                break;
            case 16:
                g90 g90Var = (g90) obj;
                float fFloatValue4 = ((Float) obj2).floatValue();
                g90Var.i = fFloatValue4;
                int i9 = (int) (fFloatValue4 * 1800.0f);
                int i10 = 0;
                while (true) {
                    ArrayList arrayList3 = (ArrayList) g90Var.b;
                    if (i10 >= arrayList3.size()) {
                        if (g90Var.h) {
                            Iterator it = arrayList3.iterator();
                            while (it.hasNext()) {
                                ((zp) it.next()).c = g90Var.f.c[g90Var.g];
                            }
                            g90Var.h = false;
                        }
                        ((a40) g90Var.a).invalidateSelf();
                    } else {
                        zp zpVar6 = (zp) arrayList3.get(i10);
                        int[] iArr2 = g90.l;
                        int i11 = i10 * 2;
                        int i12 = iArr2[i11];
                        int[] iArr3 = g90.k;
                        float fH3 = z5.h(i9, i12, iArr3[i11]);
                        Interpolator[] interpolatorArr = g90Var.e;
                        zpVar6.a = s90.l(interpolatorArr[i11].getInterpolation(fH3), 0.0f, 1.0f);
                        int i13 = i11 + 1;
                        zpVar6.b = s90.l(interpolatorArr[i13].getInterpolation(z5.h(i9, iArr2[i13], iArr3[i13])), 0.0f, 1.0f);
                        i10++;
                    }
                    break;
                }
                break;
            case 17:
                ((SwitchCompat) obj).setThumbPosition(((Float) obj2).floatValue());
                break;
            case 18:
                p61.b((View) obj, ((Float) obj2).floatValue());
                break;
            default:
                ((View) obj).setClipBounds((Rect) obj2);
                break;
        }
    }
}
