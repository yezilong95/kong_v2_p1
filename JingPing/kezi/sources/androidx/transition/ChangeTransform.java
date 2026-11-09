package androidx.transition;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeConverter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import defpackage.a60;
import defpackage.bq;
import defpackage.ev;
import defpackage.fb;
import defpackage.fv;
import defpackage.gv;
import defpackage.hv;
import defpackage.jt0;
import defpackage.lr0;
import defpackage.mb;
import defpackage.mp0;
import defpackage.nb;
import defpackage.ob;
import defpackage.oy;
import defpackage.pb;
import defpackage.xr0;
import defpackage.za0;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public class ChangeTransform extends Transition {
    public static final String[] B = {"android:changeTransform:matrix", "android:changeTransform:transforms", "android:changeTransform:parentMatrix"};
    public static final fb C = new fb(float[].class, "nonTranslations", 6);
    public static final fb D = new fb(PointF.class, "translations", 7);
    public static final boolean E = true;
    public final Matrix A;
    public final boolean y;
    public final boolean z;

    @SuppressLint({"RestrictedApi"})
    public ChangeTransform(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.y = true;
        this.z = true;
        this.A = new Matrix();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, za0.k);
        XmlPullParser xmlPullParser = (XmlPullParser) attributeSet;
        this.y = !za0.u(xmlPullParser, "reparentWithOverlay") ? true : typedArrayObtainStyledAttributes.getBoolean(1, true);
        this.z = za0.u(xmlPullParser, "reparent") ? typedArrayObtainStyledAttributes.getBoolean(0, true) : true;
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void F(mp0 mp0Var) {
        View view = mp0Var.b;
        if (view.getVisibility() == 8) {
            return;
        }
        HashMap map = mp0Var.a;
        map.put("android:changeTransform:parent", view.getParent());
        map.put("android:changeTransform:transforms", new pb(view));
        Matrix matrix = view.getMatrix();
        map.put("android:changeTransform:matrix", (matrix == null || matrix.isIdentity()) ? null : new Matrix(matrix));
        if (this.z) {
            Matrix matrix2 = new Matrix();
            jt0.a.w0((ViewGroup) view.getParent(), matrix2);
            matrix2.preTranslate(-r2.getScrollX(), -r2.getScrollY());
            map.put("android:changeTransform:parentMatrix", matrix2);
            map.put("android:changeTransform:intermediateMatrix", view.getTag(R$id.transition_transform));
            map.put("android:changeTransform:intermediateParentMatrix", view.getTag(R$id.parent_matrix));
        }
    }

    @Override // androidx.transition.Transition
    public final void c(mp0 mp0Var) {
        F(mp0Var);
    }

    @Override // androidx.transition.Transition
    public final void f(mp0 mp0Var) {
        F(mp0Var);
        if (E) {
            return;
        }
        View view = mp0Var.b;
        ((ViewGroup) view.getParent()).startViewTransition(view);
    }

    @Override // androidx.transition.Transition
    public final Animator j(ViewGroup viewGroup, mp0 mp0Var, mp0 mp0Var2) {
        ObjectAnimator objectAnimatorOfPropertyValuesHolder;
        View view;
        Object obj;
        ObjectAnimator objectAnimator;
        int i;
        boolean z;
        ArrayList arrayList;
        boolean z2;
        ArrayList arrayList2;
        ObjectAnimator objectAnimator2;
        int i2;
        boolean z3;
        int i3;
        int iIntValue;
        fv fvVar;
        ev evVar;
        gv gvVar;
        mp0 mp0VarM;
        ChangeTransform changeTransform = this;
        if (mp0Var == null || mp0Var2 == null) {
            return null;
        }
        HashMap map = mp0Var.a;
        if (!map.containsKey("android:changeTransform:parent")) {
            return null;
        }
        HashMap map2 = mp0Var2.a;
        if (!map2.containsKey("android:changeTransform:parent")) {
            return null;
        }
        ViewGroup viewGroup2 = (ViewGroup) map.get("android:changeTransform:parent");
        ViewGroup viewGroup3 = (ViewGroup) map2.get("android:changeTransform:parent");
        boolean z4 = changeTransform.z && (!(changeTransform.r(viewGroup2) && changeTransform.r(viewGroup3)) ? viewGroup2 == viewGroup3 : !((mp0VarM = changeTransform.m(viewGroup2, true)) == null || viewGroup3 != mp0VarM.b));
        Matrix matrix = (Matrix) map.get("android:changeTransform:intermediateMatrix");
        if (matrix != null) {
            map.put("android:changeTransform:matrix", matrix);
        }
        Matrix matrix2 = (Matrix) map.get("android:changeTransform:intermediateParentMatrix");
        if (matrix2 != null) {
            map.put("android:changeTransform:parentMatrix", matrix2);
        }
        View view2 = mp0Var2.b;
        if (z4) {
            Matrix matrix3 = (Matrix) map2.get("android:changeTransform:parentMatrix");
            view2.setTag(R$id.parent_matrix, matrix3);
            Matrix matrix4 = changeTransform.A;
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
            matrix6 = a60.a;
        }
        if (matrix7 == null) {
            matrix7 = a60.a;
        }
        if (matrix6.equals(matrix7)) {
            view = view2;
            obj = "android:changeTransform:parentMatrix";
            objectAnimatorOfPropertyValuesHolder = null;
        } else {
            pb pbVar = (pb) map2.get("android:changeTransform:transforms");
            view2.setTranslationX(0.0f);
            view2.setTranslationY(0.0f);
            WeakHashMap weakHashMap = xr0.a;
            lr0.w(view2, 0.0f);
            view2.setScaleX(1.0f);
            view2.setScaleY(1.0f);
            view2.setRotationX(0.0f);
            view2.setRotationY(0.0f);
            view2.setRotation(0.0f);
            float[] fArr = new float[9];
            matrix6.getValues(fArr);
            float[] fArr2 = new float[9];
            matrix7.getValues(fArr2);
            ob obVar = new ob(view2, fArr);
            bq bqVar = new bq(0);
            bqVar.b = new float[9];
            objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(obVar, PropertyValuesHolder.ofObject(C, bqVar, fArr, fArr2), PropertyValuesHolder.ofObject(D, (TypeConverter) null, changeTransform.u.a(fArr[2], fArr[5], fArr2[2], fArr2[5])));
            Matrix matrix8 = matrix7;
            view = view2;
            changeTransform = this;
            obj = "android:changeTransform:parentMatrix";
            z4 = z4;
            mb mbVar = new mb(changeTransform, z4, matrix8, view, pbVar, obVar);
            objectAnimatorOfPropertyValuesHolder.addListener(mbVar);
            objectAnimatorOfPropertyValuesHolder.addPauseListener(mbVar);
        }
        View view3 = mp0Var.b;
        boolean z5 = E;
        if (z4 && objectAnimatorOfPropertyValuesHolder != null && changeTransform.y) {
            Matrix matrix9 = new Matrix((Matrix) map2.get(obj));
            jt0.a.x0(viewGroup, matrix9);
            if (Build.VERSION.SDK_INT == 28) {
                if (!gv.f) {
                    try {
                        gv.b();
                        Method declaredMethod = gv.c.getDeclaredMethod("addGhost", View.class, ViewGroup.class, Matrix.class);
                        gv.e = declaredMethod;
                        declaredMethod.setAccessible(true);
                    } catch (NoSuchMethodException e) {
                        Log.i("GhostViewApi21", "Failed to retrieve addGhost method", e);
                    }
                    gv.f = true;
                }
                Method method = gv.e;
                if (method != null) {
                    try {
                        gvVar = new gv((View) method.invoke(null, view, viewGroup, matrix9));
                    } catch (IllegalAccessException unused) {
                        gvVar = null;
                    } catch (InvocationTargetException e2) {
                        throw new RuntimeException(e2.getCause());
                    }
                    z = z5;
                    objectAnimator = objectAnimatorOfPropertyValuesHolder;
                    evVar = gvVar;
                } else {
                    gvVar = null;
                    z = z5;
                    objectAnimator = objectAnimatorOfPropertyValuesHolder;
                    evVar = gvVar;
                }
            } else {
                int i4 = hv.h;
                if (!(view.getParent() instanceof ViewGroup)) {
                    throw new IllegalArgumentException("Ghosted views must be parented by a ViewGroup");
                }
                int i5 = fv.d;
                fv fvVar2 = (fv) viewGroup.getTag(R$id.ghost_view_holder);
                hv hvVar = (hv) view.getTag(R$id.ghost_view);
                if (hvVar == null || (fvVar = (fv) hvVar.getParent()) == fvVar2) {
                    i = 0;
                } else {
                    i = hvVar.e;
                    fvVar.removeView(hvVar);
                    hvVar = null;
                }
                if (hvVar == null) {
                    hvVar = new hv(view);
                    hvVar.f = matrix9;
                    if (fvVar2 == null) {
                        fvVar2 = new fv(viewGroup.getContext());
                        fvVar2.setClipChildren(false);
                        fvVar2.b = viewGroup;
                        viewGroup.setTag(R$id.ghost_view_holder, fvVar2);
                        viewGroup.getOverlay().add(fvVar2);
                        fvVar2.c = true;
                    } else {
                        if (!fvVar2.c) {
                            throw new IllegalStateException("This GhostViewHolder is detached!");
                        }
                        ViewGroup viewGroup4 = fvVar2.b;
                        viewGroup4.getOverlay().remove(fvVar2);
                        viewGroup4.getOverlay().add(fvVar2);
                    }
                    jt0.a(fvVar2, fvVar2.getLeft(), fvVar2.getTop(), viewGroup.getWidth() + fvVar2.getLeft(), viewGroup.getHeight() + fvVar2.getTop());
                    jt0.a(hvVar, hvVar.getLeft(), hvVar.getTop(), viewGroup.getWidth() + hvVar.getLeft(), viewGroup.getHeight() + hvVar.getTop());
                    ArrayList arrayList3 = new ArrayList();
                    fv.a(hvVar.d, arrayList3);
                    ArrayList arrayList4 = new ArrayList();
                    int childCount = fvVar2.getChildCount() - 1;
                    int i6 = 0;
                    while (i6 <= childCount) {
                        int i7 = (i6 + childCount) / 2;
                        fv.a(((hv) fvVar2.getChildAt(i7)).d, arrayList4);
                        if (arrayList3.isEmpty() || arrayList4.isEmpty()) {
                            arrayList = arrayList3;
                            z2 = z5;
                            arrayList2 = arrayList4;
                            objectAnimator2 = objectAnimatorOfPropertyValuesHolder;
                        } else {
                            z2 = z5;
                            objectAnimator2 = objectAnimatorOfPropertyValuesHolder;
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
                                            ViewGroup viewGroup5 = (ViewGroup) view4.getParent();
                                            int childCount2 = viewGroup5.getChildCount();
                                            if (view4.getZ() == view5.getZ()) {
                                                arrayList2 = arrayList4;
                                                int i9 = 0;
                                                while (i9 < childCount2) {
                                                    int i10 = childCount2;
                                                    i2 = childCount;
                                                    if (Build.VERSION.SDK_INT >= 29) {
                                                        iIntValue = viewGroup5.getChildDrawingOrder(i9);
                                                        i3 = i9;
                                                    } else {
                                                        if (!oy.k) {
                                                            try {
                                                                Class cls = Integer.TYPE;
                                                                Method declaredMethod2 = ViewGroup.class.getDeclaredMethod("getChildDrawingOrder", cls, cls);
                                                                oy.j = declaredMethod2;
                                                                z3 = true;
                                                                try {
                                                                    declaredMethod2.setAccessible(true);
                                                                } catch (NoSuchMethodException unused2) {
                                                                }
                                                            } catch (NoSuchMethodException unused3) {
                                                                z3 = true;
                                                            }
                                                            oy.k = z3;
                                                        }
                                                        Method method2 = oy.j;
                                                        if (method2 != null) {
                                                            try {
                                                                i3 = i9;
                                                                try {
                                                                    iIntValue = ((Integer) method2.invoke(viewGroup5, Integer.valueOf(viewGroup5.getChildCount()), Integer.valueOf(i3))).intValue();
                                                                } catch (IllegalAccessException | InvocationTargetException unused4) {
                                                                    iIntValue = i3;
                                                                }
                                                            } catch (IllegalAccessException | InvocationTargetException unused5) {
                                                                i3 = i9;
                                                            }
                                                        } else {
                                                            i3 = i9;
                                                            iIntValue = i3;
                                                        }
                                                    }
                                                    View childAt = viewGroup5.getChildAt(iIntValue);
                                                    if (childAt != view4) {
                                                        if (childAt == view5) {
                                                            break;
                                                        }
                                                        i9 = i3 + 1;
                                                        childCount2 = i10;
                                                        childCount = i2;
                                                    }
                                                }
                                            } else if (view4.getZ() <= view5.getZ()) {
                                                arrayList2 = arrayList4;
                                            }
                                        } else {
                                            i8++;
                                            arrayList3 = arrayList;
                                        }
                                    } else {
                                        arrayList = arrayList3;
                                        arrayList2 = arrayList4;
                                        i2 = childCount;
                                        if (arrayList2.size() == iMin) {
                                        }
                                        arrayList2.clear();
                                        arrayList3 = arrayList;
                                        z5 = z2;
                                        arrayList4 = arrayList2;
                                        objectAnimatorOfPropertyValuesHolder = objectAnimator2;
                                    }
                                }
                                childCount = i7 - 1;
                                arrayList2.clear();
                                arrayList3 = arrayList;
                                z5 = z2;
                                arrayList4 = arrayList2;
                                objectAnimatorOfPropertyValuesHolder = objectAnimator2;
                            }
                            arrayList2 = arrayList4;
                        }
                        i2 = childCount;
                        i6 = i7 + 1;
                        childCount = i2;
                        arrayList2.clear();
                        arrayList3 = arrayList;
                        z5 = z2;
                        arrayList4 = arrayList2;
                        objectAnimatorOfPropertyValuesHolder = objectAnimator2;
                    }
                    z = z5;
                    objectAnimator = objectAnimatorOfPropertyValuesHolder;
                    if (i6 < 0 || i6 >= fvVar2.getChildCount()) {
                        fvVar2.addView(hvVar);
                    } else {
                        fvVar2.addView(hvVar, i6);
                    }
                    hvVar.e = i;
                } else {
                    z = z5;
                    objectAnimator = objectAnimatorOfPropertyValuesHolder;
                    hvVar.f = matrix9;
                }
                hv hvVar2 = hvVar;
                hvVar2.e++;
                evVar = hvVar2;
            }
            if (evVar != null) {
                evVar.a((ViewGroup) map.get("android:changeTransform:parent"), view3);
                Transition transition = this;
                while (true) {
                    TransitionSet transitionSet = transition.j;
                    if (transitionSet == null) {
                        break;
                    }
                    transition = transitionSet;
                }
                nb nbVar = new nb();
                nbVar.b = view;
                nbVar.c = evVar;
                transition.a(nbVar);
                if (z) {
                    if (view3 != view) {
                        jt0.b(view3, 0.0f);
                    }
                    jt0.b(view, 1.0f);
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
    public final String[] o() {
        return B;
    }
}
