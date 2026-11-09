package defpackage;

import android.animation.ValueAnimator;
import android.content.ClipDescription;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Trace;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Choreographer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour;
import androidx.constraintlayout.widget.b;
import androidx.fragment.app.c;
import androidx.fragment.app.d;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.n;
import androidx.startup.R$string;
import androidx.startup.StartupException;
import androidx.work.impl.WorkDatabaseVersions;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class po implements na, fp, gy {
    public static po f;
    public static volatile po g;
    public static final Object h = new Object();
    public static po i;
    public final /* synthetic */ int b;
    public Object c;
    public Object d;
    public Object e;

    public /* synthetic */ po(int i2, boolean z) {
        this.b = i2;
    }

    public static po C(Context context) {
        if (g == null) {
            synchronized (h) {
                try {
                    if (g == null) {
                        g = new po(context);
                    }
                } finally {
                }
            }
        }
        return g;
    }

    public static po M(Context context, AttributeSet attributeSet, int[] iArr) {
        return new po(context, context.obtainStyledAttributes(attributeSet, iArr));
    }

    public static po N(Context context, AttributeSet attributeSet, int[] iArr, int i2) {
        return new po(context, context.obtainStyledAttributes(attributeSet, iArr, i2, 0));
    }

    public static po x() {
        if (f == null) {
            synchronized (po.class) {
                try {
                    if (f == null) {
                        po poVar = new po(0, false);
                        poVar.c = new ConcurrentHashMap();
                        poVar.d = new ArrayBlockingQueue(10);
                        poVar.e = Executors.newFixedThreadPool(5);
                        Executors.newSingleThreadExecutor().execute(new v6(8, poVar));
                        f = poVar;
                    }
                } finally {
                }
            }
        }
        return f;
    }

    public Typeface A(int i2, int i3, s5 s5Var) {
        int resourceId = ((TypedArray) this.d).getResourceId(i2, 0);
        if (resourceId == 0) {
            return null;
        }
        if (((TypedValue) this.e) == null) {
            this.e = new TypedValue();
        }
        TypedValue typedValue = (TypedValue) this.e;
        ThreadLocal threadLocal = cf0.a;
        Context context = (Context) this.c;
        if (context.isRestricted()) {
            return null;
        }
        return cf0.b(context, resourceId, typedValue, i3, s5Var, true, false);
    }

    public List B() {
        ArrayList arrayList;
        if (((ArrayList) this.c).isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        synchronized (((ArrayList) this.c)) {
            arrayList = new ArrayList((ArrayList) this.c);
        }
        return arrayList;
    }

    public int D(int i2) {
        if (i2 < 0) {
            return -1;
        }
        int childCount = ((gd0) this.c).a.getChildCount();
        int i3 = i2;
        while (i3 < childCount) {
            fc fcVar = (fc) this.d;
            int iB = i2 - (i3 - fcVar.b(i3));
            if (iB == 0) {
                while (fcVar.d(i3)) {
                    i3++;
                }
                return i3;
            }
            i3 += iB;
        }
        return -1;
    }

    public double E(double d) {
        if (d < 0.0d) {
            d = 0.0d;
        } else if (d > 1.0d) {
            d = 1.0d;
        }
        int iBinarySearch = Arrays.binarySearch((double[]) this.d, d);
        if (iBinarySearch > 0) {
            return 1.0d;
        }
        if (iBinarySearch == 0) {
            return 0.0d;
        }
        int i2 = -iBinarySearch;
        int i3 = i2 - 1;
        float[] fArr = (float[]) this.c;
        float f2 = fArr[i3];
        int i4 = i2 - 2;
        float f3 = fArr[i4];
        double[] dArr = (double[]) this.d;
        double d2 = dArr[i3];
        double d3 = dArr[i4];
        double d4 = ((double) (f2 - f3)) / (d2 - d3);
        return ((((d * d) - (d3 * d3)) * d4) / 2.0d) + ((d - d3) * (((double) f3) - (d4 * d3))) + ((double[]) this.e)[i4];
    }

    public View F(int i2) {
        return ((gd0) this.c).a.getChildAt(i2);
    }

    public int G() {
        return ((gd0) this.c).a.getChildCount();
    }

    public double H(double d) {
        return Math.sin(E(d) * 6.283185307179586d);
    }

    public void I(View view) {
        ((ArrayList) this.e).add(view);
        gd0 gd0Var = (gd0) this.c;
        n nVarI = RecyclerView.I(view);
        if (nVarI != null) {
            int i2 = nVarI.q;
            View view2 = nVarI.a;
            if (i2 != -1) {
                nVarI.p = i2;
            } else {
                WeakHashMap weakHashMap = xr0.a;
                nVarI.p = view2.getImportantForAccessibility();
            }
            RecyclerView recyclerView = gd0Var.a;
            if (recyclerView.L()) {
                nVarI.q = 4;
                recyclerView.r0.add(nVarI);
            } else {
                WeakHashMap weakHashMap2 = xr0.a;
                view2.setImportantForAccessibility(4);
            }
        }
    }

    public void J(d dVar) {
        jt jtVar = dVar.c;
        String str = jtVar.f;
        HashMap map = (HashMap) this.d;
        if (map.get(str) != null) {
            return;
        }
        map.put(jtVar.f, dVar);
        if (c.C(2)) {
            Log.v("FragmentManager", "Added fragment to active set " + jtVar);
        }
    }

    public void K(d dVar) {
        jt jtVar = dVar.c;
        if (jtVar.B) {
            ((xt) this.e).c(jtVar);
        }
        if (((d) ((HashMap) this.d).put(jtVar.f, null)) != null && c.C(2)) {
            Log.v("FragmentManager", "Removed fragment from active set " + jtVar);
        }
    }

    public boolean L(m8 m8Var, mg mgVar, boolean z) {
        ConstraintWidget$DimensionBehaviour[] constraintWidget$DimensionBehaviourArr = mgVar.J;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour = constraintWidget$DimensionBehaviourArr[0];
        l8 l8Var = (l8) this.d;
        l8Var.a = constraintWidget$DimensionBehaviour;
        l8Var.b = constraintWidget$DimensionBehaviourArr[1];
        l8Var.c = mgVar.m();
        l8Var.d = mgVar.j();
        l8Var.i = false;
        l8Var.j = z;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour2 = l8Var.a;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour3 = ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT;
        boolean z2 = constraintWidget$DimensionBehaviour2 == constraintWidget$DimensionBehaviour3;
        boolean z3 = l8Var.b == constraintWidget$DimensionBehaviour3;
        boolean z4 = z2 && mgVar.N > 0.0f;
        boolean z5 = z3 && mgVar.N > 0.0f;
        int[] iArr = mgVar.l;
        if (z4 && iArr[0] == 4) {
            l8Var.a = ConstraintWidget$DimensionBehaviour.FIXED;
        }
        if (z5 && iArr[1] == 4) {
            l8Var.b = ConstraintWidget$DimensionBehaviour.FIXED;
        }
        ((b) m8Var).a(mgVar, l8Var);
        mgVar.z(l8Var.e);
        mgVar.w(l8Var.f);
        mgVar.w = l8Var.h;
        int i2 = l8Var.g;
        mgVar.R = i2;
        mgVar.w = i2 > 0;
        l8Var.j = false;
        return l8Var.i;
    }

    public void O(Object obj) {
        synchronized (po.class) {
            try {
                ((ArrayBlockingQueue) this.d).offer(obj, 2L, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void P() {
        ((TypedArray) this.d).recycle();
    }

    public void Q(ng ngVar, int i2, int i3) {
        int i4 = ngVar.S;
        int i5 = ngVar.T;
        ngVar.S = 0;
        ngVar.T = 0;
        ngVar.z(i2);
        ngVar.w(i3);
        if (i4 < 0) {
            ngVar.S = 0;
        } else {
            ngVar.S = i4;
        }
        if (i5 < 0) {
            ngVar.T = 0;
        } else {
            ngVar.T = i5;
        }
        ((ng) this.e).F();
    }

    public void R(View view) {
        if (((ArrayList) this.e).remove(view)) {
            gd0 gd0Var = (gd0) this.c;
            n nVarI = RecyclerView.I(view);
            if (nVarI != null) {
                int i2 = nVarI.p;
                RecyclerView recyclerView = gd0Var.a;
                if (recyclerView.L()) {
                    nVarI.q = i2;
                    recyclerView.r0.add(nVarI);
                } else {
                    WeakHashMap weakHashMap = xr0.a;
                    nVarI.a.setImportantForAccessibility(i2);
                }
                nVarI.p = 0;
            }
        }
    }

    @Override // defpackage.gy
    public Uri b() {
        return (Uri) this.e;
    }

    @Override // defpackage.fp
    public int c() {
        return ((ExtendedFloatingActionButton) this.e).C;
    }

    @Override // defpackage.gy
    public ClipDescription d() {
        return (ClipDescription) this.d;
    }

    @Override // defpackage.gy
    public Object e() {
        return null;
    }

    @Override // defpackage.fp
    public int f() {
        return ((ExtendedFloatingActionButton) this.e).B;
    }

    @Override // defpackage.fp
    public ViewGroup.LayoutParams g() {
        ExtendedFloatingActionButton extendedFloatingActionButton = (ExtendedFloatingActionButton) this.e;
        int i2 = extendedFloatingActionButton.I;
        if (i2 == 0) {
            i2 = -2;
        }
        int i3 = extendedFloatingActionButton.J;
        return new ViewGroup.LayoutParams(i2, i3 != 0 ? i3 : -2);
    }

    @Override // defpackage.fp
    public int getHeight() {
        int i2 = ((ExtendedFloatingActionButton) this.e).J;
        return i2 == -1 ? ((e5) this.c).getHeight() : (i2 == 0 || i2 == -2) ? ((bp) this.d).c.getMeasuredHeight() : i2;
    }

    @Override // defpackage.fp
    public int getWidth() {
        int i2 = ((ExtendedFloatingActionButton) this.e).I;
        return i2 == -1 ? ((e5) this.c).getWidth() : (i2 == 0 || i2 == -2) ? ((bp) this.d).getWidth() : i2;
    }

    @Override // defpackage.na
    public void h() {
        View view = (View) this.c;
        view.clearAnimation();
        ((ViewGroup) this.d).endViewTransition(view);
        ((tj) this.e).d();
    }

    @Override // defpackage.gy
    public Uri i() {
        return (Uri) this.c;
    }

    public void j(jt jtVar) {
        if (((ArrayList) this.c).contains(jtVar)) {
            throw new IllegalStateException("Fragment already added: " + jtVar);
        }
        synchronized (((ArrayList) this.c)) {
            ((ArrayList) this.c).add(jtVar);
        }
        jtVar.l = true;
    }

    public void k(double d, float f2) {
        int length = ((float[]) this.c).length + 1;
        int iBinarySearch = Arrays.binarySearch((double[]) this.d, d);
        if (iBinarySearch < 0) {
            iBinarySearch = (-iBinarySearch) - 1;
        }
        this.d = Arrays.copyOf((double[]) this.d, length);
        this.c = Arrays.copyOf((float[]) this.c, length);
        this.e = new double[length];
        double[] dArr = (double[]) this.d;
        System.arraycopy(dArr, iBinarySearch, dArr, iBinarySearch + 1, (length - iBinarySearch) - 1);
        ((double[]) this.d)[iBinarySearch] = d;
        ((float[]) this.c)[iBinarySearch] = f2;
    }

    public void l(int[] iArr, ValueAnimator valueAnimator) {
        yo yoVar = new yo(iArr, valueAnimator);
        valueAnimator.addListener((d1) this.e);
        ((ArrayList) this.c).add(yoVar);
    }

    public void m(View view, int i2, boolean z) {
        RecyclerView recyclerView = ((gd0) this.c).a;
        int childCount = i2 < 0 ? recyclerView.getChildCount() : D(i2);
        ((fc) this.d).e(childCount, z);
        if (z) {
            I(view);
        }
        recyclerView.addView(view, childCount);
        RecyclerView.I(view);
        ArrayList arrayList = recyclerView.z;
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ((ts0) ((nd0) recyclerView.z.get(size))).getClass();
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
                if (((ViewGroup.MarginLayoutParams) layoutParams).width != -1 || ((ViewGroup.MarginLayoutParams) layoutParams).height != -1) {
                    throw new IllegalStateException("Pages must fill the whole ViewPager2 (use match_parent)");
                }
            }
        }
    }

    public void n(View view, int i2, ViewGroup.LayoutParams layoutParams, boolean z) {
        RecyclerView recyclerView = ((gd0) this.c).a;
        int childCount = i2 < 0 ? recyclerView.getChildCount() : D(i2);
        ((fc) this.d).e(childCount, z);
        if (z) {
            I(view);
        }
        n nVarI = RecyclerView.I(view);
        if (nVarI != null) {
            if (!nVarI.j() && !nVarI.o()) {
                throw new IllegalArgumentException("Called attach on a child which is not detached: " + nVarI + recyclerView.y());
            }
            nVarI.j &= -257;
        }
        recyclerView.attachViewToParent(view, childCount, layoutParams);
    }

    public void o(int i2) {
        n nVarI;
        int iD = D(i2);
        ((fc) this.d).g(iD);
        RecyclerView recyclerView = ((gd0) this.c).a;
        View childAt = recyclerView.getChildAt(iD);
        if (childAt != null && (nVarI = RecyclerView.I(childAt)) != null) {
            if (nVarI.j() && !nVarI.o()) {
                throw new IllegalArgumentException("called detach on an already detached child " + nVarI + recyclerView.y());
            }
            nVarI.a(256);
        }
        recyclerView.detachViewFromParent(iD);
    }

    public void p(Bundle bundle) {
        HashSet hashSet;
        String string = ((Context) this.e).getString(R$string.androidx_startup);
        if (bundle != null) {
            try {
                HashSet hashSet2 = new HashSet();
                Iterator<String> it = bundle.keySet().iterator();
                while (true) {
                    boolean zHasNext = it.hasNext();
                    hashSet = (HashSet) this.d;
                    if (!zHasNext) {
                        break;
                    }
                    String next = it.next();
                    if (string.equals(bundle.getString(next, null))) {
                        Class<?> cls = Class.forName(next);
                        if (cy.class.isAssignableFrom(cls)) {
                            hashSet.add(cls);
                        }
                    }
                }
                Iterator it2 = hashSet.iterator();
                while (it2.hasNext()) {
                    q((Class) it2.next(), hashSet2);
                }
            } catch (ClassNotFoundException e) {
                throw new StartupException(e);
            }
        }
    }

    public Object q(Class cls, HashSet hashSet) {
        Object objCreate;
        if (nv0.C()) {
            try {
                Trace.beginSection(cls.getSimpleName());
            } catch (Throwable th) {
                Trace.endSection();
                throw th;
            }
        }
        if (hashSet.contains(cls)) {
            throw new IllegalStateException("Cannot initialize " + cls.getName() + ". Cycle detected.");
        }
        HashMap map = (HashMap) this.c;
        if (map.containsKey(cls)) {
            objCreate = map.get(cls);
        } else {
            hashSet.add(cls);
            try {
                cy cyVar = (cy) cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                List<Class> listDependencies = cyVar.dependencies();
                if (!listDependencies.isEmpty()) {
                    for (Class cls2 : listDependencies) {
                        if (!map.containsKey(cls2)) {
                            q(cls2, hashSet);
                        }
                    }
                }
                objCreate = cyVar.create((Context) this.e);
                hashSet.remove(cls);
                map.put(cls, objCreate);
            } catch (Throwable th2) {
                throw new StartupException(th2);
            }
        }
        Trace.endSection();
        return objCreate;
    }

    public jt r(String str) {
        d dVar = (d) ((HashMap) this.d).get(str);
        if (dVar != null) {
            return dVar.c;
        }
        return null;
    }

    public jt s(String str) {
        for (d dVar : ((HashMap) this.d).values()) {
            if (dVar != null) {
                jt jtVarS = dVar.c;
                if (!str.equals(jtVarS.f)) {
                    jtVarS = jtVarS.u.c.s(str);
                }
                if (jtVarS != null) {
                    return jtVarS;
                }
            }
        }
        return null;
    }

    public ArrayList t() {
        ArrayList arrayList = new ArrayList();
        for (d dVar : ((HashMap) this.d).values()) {
            if (dVar != null) {
                arrayList.add(dVar);
            }
        }
        return arrayList;
    }

    public String toString() {
        switch (this.b) {
            case 4:
                return ((fc) this.d).toString() + ", hidden list:" + ((ArrayList) this.e).size();
            case WorkDatabaseVersions.VERSION_10 /* 10 */:
                return "pos =" + Arrays.toString((double[]) this.d) + " period=" + Arrays.toString((float[]) this.c);
            default:
                return super.toString();
        }
    }

    public View u(int i2) {
        return ((gd0) this.c).a.getChildAt(D(i2));
    }

    public int v() {
        return ((gd0) this.c).a.getChildCount() - ((ArrayList) this.e).size();
    }

    public ColorStateList w(int i2) {
        int resourceId;
        ColorStateList colorStateListW;
        TypedArray typedArray = (TypedArray) this.d;
        return (!typedArray.hasValue(i2) || (resourceId = typedArray.getResourceId(i2, 0)) == 0 || (colorStateListW = rp0.w((Context) this.c, resourceId)) == null) ? typedArray.getColorStateList(i2) : colorStateListW;
    }

    public Drawable y(int i2) {
        int resourceId;
        TypedArray typedArray = (TypedArray) this.d;
        return (!typedArray.hasValue(i2) || (resourceId = typedArray.getResourceId(i2, 0)) == 0) ? typedArray.getDrawable(i2) : rp0.y((Context) this.c, resourceId);
    }

    public Drawable z(int i2) {
        int resourceId;
        Drawable drawableD;
        if (!((TypedArray) this.d).hasValue(i2) || (resourceId = ((TypedArray) this.d).getResourceId(i2, 0)) == 0) {
            return null;
        }
        a5 a5VarA = a5.a();
        Context context = (Context) this.c;
        synchronized (a5VarA) {
            drawableD = a5VarA.a.d(context, resourceId, true);
        }
        return drawableD;
    }

    public /* synthetic */ po(Object obj, Object obj2, Object obj3, int i2) {
        this.b = i2;
        this.c = obj;
        this.d = obj2;
        this.e = obj3;
    }

    public po(int i2) {
        this.b = i2;
        switch (i2) {
            case WorkDatabaseVersions.VERSION_11 /* 11 */:
                this.c = new ArrayList();
                this.d = null;
                this.e = new d1(10, this);
                break;
            default:
                this.c = new ArrayList();
                this.d = new HashMap();
                break;
        }
    }

    public po(gd0 gd0Var) {
        this.b = 4;
        this.c = gd0Var;
        this.d = new fc();
        this.e = new ArrayList();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public po(FrameLayout frameLayout) {
        Object x40Var;
        this.b = 9;
        w40 w40Var = (w40) frameLayout;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 34) {
            x40Var = new z40();
        } else {
            x40Var = i2 >= 33 ? new x40() : null;
        }
        this.c = x40Var;
        this.d = w40Var;
        this.e = frameLayout;
    }

    public po(Context context, TypedArray typedArray) {
        this.b = 12;
        this.c = context;
        this.d = typedArray;
    }

    public po(Context context, LocationManager locationManager) {
        this.b = 13;
        this.e = new qp0();
        this.c = context;
        this.d = locationManager;
    }

    public po(Context context) {
        this.b = 1;
        this.e = context.getApplicationContext();
        this.d = new HashSet();
        this.c = new HashMap();
    }

    public po(ng ngVar) {
        this.b = 3;
        this.c = new ArrayList();
        this.d = new l8();
        this.e = ngVar;
    }

    public po(o70 o70Var) {
        this.b = 2;
        this.b = 2;
        this.c = o70Var;
        this.d = Choreographer.getInstance();
        this.e = new r3(this);
    }

    @Override // defpackage.gy
    public void a() {
    }

    public po(ExtendedFloatingActionButton extendedFloatingActionButton, e5 e5Var, bp bpVar) {
        this.b = 6;
        this.e = extendedFloatingActionButton;
        this.c = e5Var;
        this.d = bpVar;
    }
}
