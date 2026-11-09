package androidx.transition;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import defpackage.a21;
import defpackage.dm0;
import defpackage.e20;
import defpackage.f20;
import defpackage.f51;
import defpackage.g20;
import defpackage.gd;
import defpackage.h20;
import defpackage.hd;
import defpackage.id;
import defpackage.jd;
import defpackage.l51;
import defpackage.la0;
import defpackage.o80;
import defpackage.ow;
import defpackage.p61;
import defpackage.qm;
import defpackage.rd0;
import defpackage.t41;
import defpackage.yc;
import defpackage.z8;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public class ChangeTransform extends Transition {
    public static final String[] i0 = {"android:changeTransform:matrix", "android:changeTransform:transforms", "android:changeTransform:parentMatrix"};
    public static final yc j0 = new yc(float[].class, "nonTranslations", 6);
    public static final yc k0 = new yc(PointF.class, "translations", 7);
    public static final boolean l0 = true;
    public final boolean f0;
    public final boolean g0;
    public final Matrix h0;

    public ChangeTransform(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f0 = true;
        this.g0 = true;
        this.h0 = new Matrix();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, qm.v);
        XmlPullParser xmlPullParser = (XmlPullParser) attributeSet;
        this.f0 = !la0.J(xmlPullParser, "reparentWithOverlay") ? true : typedArrayObtainStyledAttributes.getBoolean(1, true);
        this.g0 = la0.J(xmlPullParser, "reparent") ? typedArrayObtainStyledAttributes.getBoolean(0, true) : true;
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void I(a21 a21Var) {
        View view = a21Var.b;
        if (view.getVisibility() == 8) {
            return;
        }
        HashMap map = a21Var.a;
        map.put("android:changeTransform:parent", view.getParent());
        map.put("android:changeTransform:transforms", new jd(view));
        Matrix matrix = view.getMatrix();
        map.put("android:changeTransform:matrix", (matrix == null || matrix.isIdentity()) ? null : new Matrix(matrix));
        if (this.g0) {
            Matrix matrix2 = new Matrix();
            p61.a.p0((ViewGroup) view.getParent(), matrix2);
            matrix2.preTranslate(-r2.getScrollX(), -r2.getScrollY());
            map.put("android:changeTransform:parentMatrix", matrix2);
            map.put("android:changeTransform:intermediateMatrix", view.getTag(R$id.transition_transform));
            map.put("android:changeTransform:intermediateParentMatrix", view.getTag(R$id.parent_matrix));
        }
    }

    @Override // androidx.transition.Transition
    public final void d(a21 a21Var) {
        I(a21Var);
    }

    @Override // androidx.transition.Transition
    public final void g(a21 a21Var) {
        I(a21Var);
        if (l0) {
            return;
        }
        View view = a21Var.b;
        ((ViewGroup) view.getParent()).startViewTransition(view);
    }

    @Override // androidx.transition.Transition
    public final Animator k(ViewGroup viewGroup, a21 a21Var, a21 a21Var2) {
        ObjectAnimator objectAnimatorOfPropertyValuesHolder;
        Object obj;
        ObjectAnimator objectAnimator;
        int i;
        h20 h20Var;
        boolean z;
        h20 h20Var2;
        g20 g20Var;
        ArrayList arrayList;
        ObjectAnimator objectAnimator2;
        ArrayList arrayList2;
        int i2;
        boolean z2;
        boolean z3;
        int i3;
        int iIntValue;
        g20 g20Var2;
        e20 e20Var;
        z8 z8Var;
        a21 a21VarN;
        if (a21Var == null || a21Var2 == null) {
            return null;
        }
        HashMap map = a21Var.a;
        if (!map.containsKey("android:changeTransform:parent")) {
            return null;
        }
        HashMap map2 = a21Var2.a;
        if (!map2.containsKey("android:changeTransform:parent")) {
            return null;
        }
        ViewGroup viewGroup2 = (ViewGroup) map.get("android:changeTransform:parent");
        View view = (ViewGroup) map2.get("android:changeTransform:parent");
        boolean z4 = this.g0 && (!(t(viewGroup2) && t(view)) ? viewGroup2 == view : !((a21VarN = n(viewGroup2, true)) == null || view != a21VarN.b));
        Matrix matrix = (Matrix) map.get("android:changeTransform:intermediateMatrix");
        if (matrix != null) {
            map.put("android:changeTransform:matrix", matrix);
        }
        Matrix matrix2 = (Matrix) map.get("android:changeTransform:intermediateParentMatrix");
        if (matrix2 != null) {
            map.put("android:changeTransform:parentMatrix", matrix2);
        }
        View view2 = a21Var2.b;
        if (z4) {
            Matrix matrix3 = (Matrix) map2.get("android:changeTransform:parentMatrix");
            view2.setTag(R$id.parent_matrix, matrix3);
            Matrix matrix4 = this.h0;
            matrix4.reset();
            matrix3.invert(matrix4);
            Matrix matrix5 = (Matrix) map.get("android:changeTransform:matrix");
            if (matrix5 == null) {
                matrix5 = new Matrix();
                map.put("android:changeTransform:matrix", matrix5);
            }
            matrix5.postConcat((Matrix) map.get("android:changeTransform:parentMatrix"));
            matrix5.postConcat(matrix4);
        }
        Matrix matrix6 = (Matrix) map.get("android:changeTransform:matrix");
        Matrix matrix7 = (Matrix) map2.get("android:changeTransform:matrix");
        if (matrix6 == null) {
            matrix6 = rd0.a;
        }
        if (matrix7 == null) {
            matrix7 = rd0.a;
        }
        if (matrix6.equals(matrix7)) {
            obj = "android:changeTransform:parentMatrix";
            objectAnimatorOfPropertyValuesHolder = null;
        } else {
            jd jdVar = (jd) map2.get("android:changeTransform:transforms");
            view2.setTranslationX(0.0f);
            view2.setTranslationY(0.0f);
            WeakHashMap weakHashMap = f51.a;
            t41.w(view2, 0.0f);
            view2.setScaleX(1.0f);
            view2.setScaleY(1.0f);
            view2.setRotationX(0.0f);
            view2.setRotationY(0.0f);
            view2.setRotation(0.0f);
            float[] fArr = new float[9];
            matrix6.getValues(fArr);
            float[] fArr2 = new float[9];
            matrix7.getValues(fArr2);
            id idVar = new id(view2, fArr);
            ow owVar = new ow(0);
            owVar.b = new float[9];
            objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(idVar, PropertyValuesHolder.ofObject(j0, owVar, fArr, fArr2), dm0.a(k0, this.a0.a(fArr[2], fArr[5], fArr2[2], fArr2[5])));
            obj = "android:changeTransform:parentMatrix";
            hd hdVar = new hd(view2, jdVar, idVar, matrix7, z4, this.f0);
            objectAnimatorOfPropertyValuesHolder.addListener(hdVar);
            objectAnimatorOfPropertyValuesHolder.addPauseListener(hdVar);
        }
        View view3 = a21Var.b;
        boolean z5 = l0;
        if (z4 && objectAnimatorOfPropertyValuesHolder != null && this.f0) {
            Matrix matrix8 = new Matrix((Matrix) map2.get(obj));
            p61.a.q0(viewGroup, matrix8);
            if (Build.VERSION.SDK_INT == 28) {
                if (!z8.L) {
                    try {
                        z8.w();
                        Method declaredMethod = z8.I.getDeclaredMethod("addGhost", View.class, ViewGroup.class, Matrix.class);
                        z8.K = declaredMethod;
                        declaredMethod.setAccessible(true);
                    } catch (NoSuchMethodException e) {
                        Log.i("GhostViewApi21", "Failed to retrieve addGhost method", e);
                    }
                    z8.L = true;
                }
                Method method = z8.K;
                if (method != null) {
                    try {
                        z8Var = new z8(22, (View) method.invoke(null, view2, viewGroup, matrix8));
                    } catch (IllegalAccessException unused) {
                        z8Var = null;
                    } catch (InvocationTargetException e2) {
                        throw new RuntimeException(e2.getCause());
                    }
                    objectAnimator = objectAnimatorOfPropertyValuesHolder;
                    z = z5;
                    e20Var = z8Var;
                } else {
                    z8Var = null;
                    objectAnimator = objectAnimatorOfPropertyValuesHolder;
                    z = z5;
                    e20Var = z8Var;
                }
            } else {
                int i4 = h20.K;
                if (!(view2.getParent() instanceof ViewGroup)) {
                    throw new IllegalArgumentException("Ghosted views must be parented by a ViewGroup");
                }
                int i5 = g20.G;
                g20 g20Var3 = (g20) viewGroup.getTag(R$id.ghost_view_holder);
                h20 h20Var3 = (h20) view2.getTag(R$id.ghost_view);
                if (h20Var3 == null || (g20Var2 = (g20) h20Var3.getParent()) == g20Var3) {
                    i = 0;
                    h20Var = h20Var3;
                } else {
                    i = h20Var3.H;
                    g20Var2.removeView(h20Var3);
                    h20Var = null;
                }
                if (h20Var == null) {
                    h20 h20Var4 = new h20(view2);
                    h20Var4.I = matrix8;
                    if (g20Var3 == null) {
                        g20 g20Var4 = new g20(viewGroup.getContext());
                        g20Var4.setClipChildren(false);
                        g20Var4.E = viewGroup;
                        viewGroup.setTag(R$id.ghost_view_holder, g20Var4);
                        viewGroup.getOverlay().add(g20Var4);
                        g20Var4.F = true;
                        g20Var = g20Var4;
                    } else {
                        if (!g20Var3.F) {
                            throw new IllegalStateException("This GhostViewHolder is detached!");
                        }
                        ViewGroup viewGroup3 = g20Var3.E;
                        viewGroup3.getOverlay().remove(g20Var3);
                        viewGroup3.getOverlay().add(g20Var3);
                        g20Var = g20Var3;
                    }
                    p61.a(g20Var, g20Var.getLeft(), g20Var.getTop(), viewGroup.getWidth() + g20Var.getLeft(), viewGroup.getHeight() + g20Var.getTop());
                    p61.a(h20Var4, h20Var4.getLeft(), h20Var4.getTop(), viewGroup.getWidth() + h20Var4.getLeft(), viewGroup.getHeight() + h20Var4.getTop());
                    ArrayList arrayList3 = new ArrayList();
                    g20.a(h20Var4.G, arrayList3);
                    ArrayList arrayList4 = new ArrayList();
                    int childCount = g20Var.getChildCount() - 1;
                    int i6 = 0;
                    while (i6 <= childCount) {
                        int i7 = (i6 + childCount) / 2;
                        g20.a(((h20) g20Var.getChildAt(i7)).G, arrayList4);
                        if (arrayList3.isEmpty() || arrayList4.isEmpty()) {
                            arrayList = arrayList3;
                            objectAnimator2 = objectAnimatorOfPropertyValuesHolder;
                            arrayList2 = arrayList4;
                            i2 = childCount;
                        } else {
                            objectAnimator2 = objectAnimatorOfPropertyValuesHolder;
                            i2 = childCount;
                            if (arrayList3.get(0) != arrayList4.get(0)) {
                                arrayList = arrayList3;
                            } else {
                                int iMin = Math.min(arrayList3.size(), arrayList4.size());
                                int i8 = 1;
                                while (true) {
                                    if (i8 < iMin) {
                                        View view4 = (View) arrayList3.get(i8);
                                        arrayList = arrayList3;
                                        View view5 = (View) arrayList4.get(i8);
                                        if (view4 != view5) {
                                            ViewGroup viewGroup4 = (ViewGroup) view4.getParent();
                                            int childCount2 = viewGroup4.getChildCount();
                                            if (f20.a(view4) == f20.a(view5)) {
                                                arrayList2 = arrayList4;
                                                int i9 = 0;
                                                while (i9 < childCount2) {
                                                    int i10 = childCount2;
                                                    z2 = z5;
                                                    if (Build.VERSION.SDK_INT >= 29) {
                                                        iIntValue = l51.a(viewGroup4, i9);
                                                        i3 = i9;
                                                    } else {
                                                        if (!o80.h) {
                                                            try {
                                                                Class cls = Integer.TYPE;
                                                                Method declaredMethod2 = ViewGroup.class.getDeclaredMethod("getChildDrawingOrder", cls, cls);
                                                                o80.g = declaredMethod2;
                                                                z3 = true;
                                                                try {
                                                                    declaredMethod2.setAccessible(true);
                                                                } catch (NoSuchMethodException unused2) {
                                                                }
                                                            } catch (NoSuchMethodException unused3) {
                                                                z3 = true;
                                                            }
                                                            o80.h = z3;
                                                        }
                                                        Method method2 = o80.g;
                                                        if (method2 != null) {
                                                            try {
                                                                i3 = i9;
                                                            } catch (IllegalAccessException | InvocationTargetException unused4) {
                                                                i3 = i9;
                                                            }
                                                            try {
                                                                iIntValue = ((Integer) method2.invoke(viewGroup4, Integer.valueOf(viewGroup4.getChildCount()), Integer.valueOf(i3))).intValue();
                                                            } catch (IllegalAccessException | InvocationTargetException unused5) {
                                                                iIntValue = i3;
                                                            }
                                                        } else {
                                                            i3 = i9;
                                                            iIntValue = i3;
                                                        }
                                                    }
                                                    View childAt = viewGroup4.getChildAt(iIntValue);
                                                    if (childAt != view4) {
                                                        if (childAt == view5) {
                                                            break;
                                                        }
                                                        i9 = i3 + 1;
                                                        childCount2 = i10;
                                                        z5 = z2;
                                                    }
                                                }
                                            } else if (f20.a(view4) <= f20.a(view5)) {
                                                arrayList2 = arrayList4;
                                                z2 = z5;
                                            }
                                        } else {
                                            i8++;
                                            arrayList3 = arrayList;
                                        }
                                    } else {
                                        arrayList = arrayList3;
                                        arrayList2 = arrayList4;
                                        z2 = z5;
                                        if (arrayList2.size() == iMin) {
                                        }
                                        arrayList2.clear();
                                        arrayList3 = arrayList;
                                        arrayList4 = arrayList2;
                                        objectAnimatorOfPropertyValuesHolder = objectAnimator2;
                                        z5 = z2;
                                    }
                                }
                                childCount = i7 - 1;
                                arrayList2.clear();
                                arrayList3 = arrayList;
                                arrayList4 = arrayList2;
                                objectAnimatorOfPropertyValuesHolder = objectAnimator2;
                                z5 = z2;
                            }
                            arrayList2 = arrayList4;
                        }
                        z2 = z5;
                        i6 = i7 + 1;
                        childCount = i2;
                        arrayList2.clear();
                        arrayList3 = arrayList;
                        arrayList4 = arrayList2;
                        objectAnimatorOfPropertyValuesHolder = objectAnimator2;
                        z5 = z2;
                    }
                    objectAnimator = objectAnimatorOfPropertyValuesHolder;
                    z = z5;
                    if (i6 < 0 || i6 >= g20Var.getChildCount()) {
                        g20Var.addView(h20Var4);
                    } else {
                        g20Var.addView(h20Var4, i6);
                    }
                    h20Var4.H = i;
                    h20Var2 = h20Var4;
                } else {
                    objectAnimator = objectAnimatorOfPropertyValuesHolder;
                    z = z5;
                    h20Var.I = matrix8;
                    h20Var2 = h20Var;
                }
                h20Var2.H++;
                e20Var = h20Var2;
            }
            if (e20Var != null) {
                e20Var.e((ViewGroup) map.get("android:changeTransform:parent"), view3);
                Transition transition = this;
                while (true) {
                    Transition transition2 = transition.M;
                    if (transition2 == null) {
                        break;
                    }
                    transition = transition2;
                }
                gd gdVar = new gd();
                gdVar.b = view2;
                gdVar.c = e20Var;
                transition.a(gdVar);
                if (z) {
                    if (view3 != view2) {
                        p61.b(view3, 0.0f);
                    }
                    p61.b(view2, 1.0f);
                }
            }
        } else {
            objectAnimator = objectAnimatorOfPropertyValuesHolder;
            if (!z5) {
                viewGroup2.endViewTransition(view3);
            }
        }
        return objectAnimator;
    }

    @Override // androidx.transition.Transition
    public final String[] q() {
        return i0;
    }
}
