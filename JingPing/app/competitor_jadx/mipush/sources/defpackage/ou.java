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
import io.nats.client.support.NatsJetStreamConstants;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class ou implements qi0, fc, av, j40 {
    public static ou I;
    public static volatile ou J;
    public static final Object K = new Object();
    public static ou L;
    public final /* synthetic */ int E;
    public Object F;
    public Object G;
    public Object H;

    public /* synthetic */ ou(int i, boolean z) {
        this.E = i;
    }

    public static ou E(Context context) {
        if (J == null) {
            synchronized (K) {
                try {
                    if (J == null) {
                        J = new ou(context);
                    }
                } finally {
                }
            }
        }
        return J;
    }

    public static ou O(Context context, AttributeSet attributeSet, int[] iArr) {
        return new ou(context, context.obtainStyledAttributes(attributeSet, iArr));
    }

    public static ou P(Context context, AttributeSet attributeSet, int[] iArr, int i) {
        return new ou(context, context.obtainStyledAttributes(attributeSet, iArr, i, 0));
    }

    public static int T(ou ouVar, int i, int i2, byte[] bArr, int i3, int i4) {
        int i5 = 0;
        int i6 = 0;
        while (i5 < i2 && i6 < i3) {
            byte b = bArr[i6];
            int i7 = b & 15;
            i6++;
            int i8 = (b & 255) >> 4;
            if (i4 == 2) {
                if (i7 < 15) {
                    ((int[]) ouVar.F)[i + i5] = 2 - (i7 - (((i7 * 205) >> 10) * 5));
                    i5++;
                }
                if (i8 < 15 && i5 < i2) {
                    ((int[]) ouVar.F)[i + i5] = 2 - (i8 - (((i8 * 205) >> 10) * 5));
                    i5++;
                }
            } else if (i4 == 4) {
                if (i7 < 9) {
                    ((int[]) ouVar.F)[i + i5] = 4 - i7;
                    i5++;
                }
                if (i8 < 9 && i5 < i2) {
                    ((int[]) ouVar.F)[i + i5] = 4 - i8;
                    i5++;
                }
            }
        }
        return i5;
    }

    public static int U(ou ouVar, int i, int i2, byte[] bArr, int i3) {
        int i4 = 0;
        int i5 = 0;
        while (i4 < i2) {
            int i6 = i5 + 3;
            if (i6 > i3) {
                break;
            }
            int i7 = (((bArr[i5 + 2] & 255) << 16) | ((bArr[i5 + 1] & 255) << 8) | (bArr[i5] & 255)) & 8388607;
            if (i7 < 8380417) {
                ((int[]) ouVar.F)[i + i4] = i7;
                i4++;
            }
            i5 = i6;
        }
        return i4;
    }

    public static ou z() {
        if (I == null) {
            synchronized (ou.class) {
                try {
                    if (I == null) {
                        ou ouVar = new ou(0, false);
                        ouVar.F = new ConcurrentHashMap();
                        ouVar.G = new ArrayBlockingQueue(10);
                        ouVar.H = Executors.newFixedThreadPool(5);
                        Executors.newSingleThreadExecutor().execute(new f(12, ouVar));
                        I = ouVar;
                    }
                } finally {
                }
            }
        }
        return I;
    }

    public Drawable A(int i) {
        int resourceId;
        TypedArray typedArray = (TypedArray) this.G;
        return (!typedArray.hasValue(i) || (resourceId = typedArray.getResourceId(i, 0)) == 0) ? typedArray.getDrawable(i) : qm.z((Context) this.F, resourceId);
    }

    public Drawable B(int i) {
        int resourceId;
        Drawable drawableD;
        if (!((TypedArray) this.G).hasValue(i) || (resourceId = ((TypedArray) this.G).getResourceId(i, 0)) == 0) {
            return null;
        }
        f6 f6VarA = f6.a();
        Context context = (Context) this.F;
        synchronized (f6VarA) {
            drawableD = f6VarA.a.d(context, resourceId, true);
        }
        return drawableD;
    }

    public Typeface C(int i, int i2, w6 w6Var) {
        int resourceId = ((TypedArray) this.G).getResourceId(i, 0);
        if (resourceId == 0) {
            return null;
        }
        if (((TypedValue) this.H) == null) {
            this.H = new TypedValue();
        }
        TypedValue typedValue = (TypedValue) this.H;
        ThreadLocal threadLocal = bp0.a;
        Context context = (Context) this.F;
        if (context.isRestricted()) {
            return null;
        }
        return bp0.b(context, resourceId, typedValue, i2, w6Var, true, false);
    }

    public List D() {
        ArrayList arrayList;
        if (((ArrayList) this.F).isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        synchronized (((ArrayList) this.F)) {
            arrayList = new ArrayList((ArrayList) this.F);
        }
        return arrayList;
    }

    public int F(int i) {
        if (i < 0) {
            return -1;
        }
        int childCount = ((cn0) this.F).a.getChildCount();
        int i2 = i;
        while (i2 < childCount) {
            ae aeVar = (ae) this.G;
            int iB = i - (i2 - aeVar.b(i2));
            if (iB == 0) {
                while (aeVar.d(i2)) {
                    i2++;
                }
                return i2;
            }
            i2 += iB;
        }
        return -1;
    }

    public double G(double d) {
        if (d < 0.0d) {
            d = 0.0d;
        } else if (d > 1.0d) {
            d = 1.0d;
        }
        int iBinarySearch = Arrays.binarySearch((double[]) this.G, d);
        if (iBinarySearch > 0) {
            return 1.0d;
        }
        if (iBinarySearch == 0) {
            return 0.0d;
        }
        int i = -iBinarySearch;
        int i2 = i - 1;
        float[] fArr = (float[]) this.F;
        float f = fArr[i2];
        int i3 = i - 2;
        float f2 = fArr[i3];
        double[] dArr = (double[]) this.G;
        double d2 = dArr[i2];
        double d3 = dArr[i3];
        double d4 = ((double) (f - f2)) / (d2 - d3);
        return ((((d * d) - (d3 * d3)) * d4) / 2.0d) + ((d - d3) * (((double) f2) - (d4 * d3))) + ((double[]) this.H)[i3];
    }

    public View H(int i) {
        return ((cn0) this.F).a.getChildAt(i);
    }

    public int I() {
        return ((cn0) this.F).a.getChildCount();
    }

    public double J(double d) {
        return Math.sin(G(d) * 6.283185307179586d);
    }

    public void K(View view) {
        ((ArrayList) this.H).add(view);
        cn0 cn0Var = (cn0) this.F;
        n nVarI = RecyclerView.I(view);
        if (nVarI != null) {
            int i = nVarI.q;
            View view2 = nVarI.a;
            if (i != -1) {
                nVarI.p = i;
            } else {
                WeakHashMap weakHashMap = f51.a;
                nVarI.p = view2.getImportantForAccessibility();
            }
            RecyclerView recyclerView = cn0Var.a;
            if (recyclerView.L()) {
                nVarI.q = 4;
                recyclerView.U0.add(nVarI);
            } else {
                WeakHashMap weakHashMap2 = f51.a;
                view2.setImportantForAccessibility(4);
            }
        }
    }

    public void L(d dVar) {
        vz vzVar = dVar.c;
        String str = vzVar.I;
        HashMap map = (HashMap) this.G;
        if (map.get(str) != null) {
            return;
        }
        map.put(vzVar.I, dVar);
        if (c.B(2)) {
            Log.v("FragmentManager", "Added fragment to active set " + vzVar);
        }
    }

    public void M(d dVar) {
        vz vzVar = dVar.c;
        if (vzVar.e0) {
            ((j00) this.H).c(vzVar);
        }
        if (((d) ((HashMap) this.G).put(vzVar.I, null)) != null && c.B(2)) {
            Log.v("FragmentManager", "Removed fragment from active set " + vzVar);
        }
    }

    public boolean N(ha haVar, yh yhVar, boolean z) {
        ConstraintWidget$DimensionBehaviour[] constraintWidget$DimensionBehaviourArr = yhVar.J;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour = constraintWidget$DimensionBehaviourArr[0];
        ga gaVar = (ga) this.G;
        gaVar.a = constraintWidget$DimensionBehaviour;
        gaVar.b = constraintWidget$DimensionBehaviourArr[1];
        gaVar.c = yhVar.m();
        gaVar.d = yhVar.j();
        gaVar.i = false;
        gaVar.j = z;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour2 = gaVar.a;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour3 = ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT;
        boolean z2 = constraintWidget$DimensionBehaviour2 == constraintWidget$DimensionBehaviour3;
        boolean z3 = gaVar.b == constraintWidget$DimensionBehaviour3;
        boolean z4 = z2 && yhVar.N > 0.0f;
        boolean z5 = z3 && yhVar.N > 0.0f;
        int[] iArr = yhVar.l;
        if (z4 && iArr[0] == 4) {
            gaVar.a = ConstraintWidget$DimensionBehaviour.FIXED;
        }
        if (z5 && iArr[1] == 4) {
            gaVar.b = ConstraintWidget$DimensionBehaviour.FIXED;
        }
        ((b) haVar).a(yhVar, gaVar);
        yhVar.z(gaVar.e);
        yhVar.w(gaVar.f);
        yhVar.w = gaVar.h;
        int i = gaVar.g;
        yhVar.R = i;
        yhVar.w = i > 0;
        gaVar.j = false;
        return gaVar.i;
    }

    public void Q(int i, byte[] bArr) {
        byte[] bArr2 = new byte[8];
        int i2 = ((fb0) this.G).d;
        if (i2 != 2) {
            if (i2 != 4) {
                throw new RuntimeException("Eta needs to be 2 or 4!");
            }
            for (int i3 = 0; i3 < 128; i3++) {
                int i4 = i3 * 2;
                int[] iArr = (int[]) this.F;
                byte b = (byte) (i2 - iArr[i4]);
                bArr2[0] = b;
                byte b2 = (byte) (i2 - iArr[i4 + 1]);
                bArr2[1] = b2;
                bArr[i + i3] = (byte) ((b2 << 4) | b);
            }
            return;
        }
        for (int i5 = 0; i5 < 32; i5++) {
            int i6 = i5 * 8;
            int[] iArr2 = (int[]) this.F;
            byte b3 = (byte) (i2 - iArr2[i6]);
            bArr2[0] = b3;
            byte b4 = (byte) (i2 - iArr2[i6 + 1]);
            bArr2[1] = b4;
            byte b5 = (byte) (i2 - iArr2[i6 + 2]);
            bArr2[2] = b5;
            bArr2[3] = (byte) (i2 - iArr2[i6 + 3]);
            bArr2[4] = (byte) (i2 - iArr2[i6 + 4]);
            bArr2[5] = (byte) (i2 - iArr2[i6 + 5]);
            bArr2[6] = (byte) (i2 - iArr2[i6 + 6]);
            bArr2[7] = (byte) (i2 - iArr2[i6 + 7]);
            int i7 = (i5 * 3) + i;
            bArr[i7] = (byte) (b3 | (b4 << 3) | (b5 << 6));
            bArr[i7 + 1] = (byte) ((bArr2[2] >> 2) | (bArr2[3] << 1) | (bArr2[4] << 4) | (bArr2[5] << 7));
            bArr[i7 + 2] = (byte) ((bArr2[7] << 5) | (bArr2[5] >> 1) | (bArr2[6] << 2));
        }
    }

    public void R(Object obj) {
        synchronized (ou.class) {
            try {
                ((ArrayBlockingQueue) this.G).offer(obj, 2L, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void S() {
        ((TypedArray) this.G).recycle();
    }

    public void V(zh zhVar, int i, int i2) {
        int i3 = zhVar.S;
        int i4 = zhVar.T;
        zhVar.S = 0;
        zhVar.T = 0;
        zhVar.z(i);
        zhVar.w(i2);
        if (i3 < 0) {
            zhVar.S = 0;
        } else {
            zhVar.S = i3;
        }
        if (i4 < 0) {
            zhVar.T = 0;
        } else {
            zhVar.T = i4;
        }
        ((zh) this.H).F();
    }

    public void W(View view) {
        if (((ArrayList) this.H).remove(view)) {
            cn0 cn0Var = (cn0) this.F;
            n nVarI = RecyclerView.I(view);
            if (nVarI != null) {
                int i = nVarI.p;
                RecyclerView recyclerView = cn0Var.a;
                if (recyclerView.L()) {
                    nVarI.q = i;
                    recyclerView.U0.add(nVarI);
                } else {
                    WeakHashMap weakHashMap = f51.a;
                    nVarI.a.setImportantForAccessibility(i);
                }
                nVarI.p = 0;
            }
        }
    }

    public void X(byte[] bArr, short s) {
        int i;
        int i2 = ((fb0) this.G).d;
        i6 i6Var = (i6) this.H;
        if (i2 == 2) {
            i6Var.getClass();
            i = 1;
        } else {
            if (i2 != 4) {
                throw new RuntimeException("Wrong Dilithium Eta!");
            }
            i6Var.getClass();
            i = 2;
        }
        i6Var.getClass();
        int i3 = i * 136;
        byte[] bArr2 = new byte[i3];
        gq0 gq0Var = (gq0) i6Var.G;
        gq0Var.n();
        gq0Var.e(bArr, 0, bArr.length);
        gq0Var.e(new byte[]{(byte) s, (byte) (s >> 8)}, 0, 2);
        gq0Var.p(bArr2, 0, i3);
        int iT = T(this, 0, NatsJetStreamConstants.MAX_PULL_SIZE, bArr2, i3, i2);
        while (iT < 256) {
            gq0Var.p(bArr2, 0, 136);
            iT += T(this, iT, 256 - iT, bArr2, 136, i2);
        }
    }

    @Override // defpackage.j40
    public ClipDescription a() {
        return (ClipDescription) this.G;
    }

    @Override // defpackage.j40
    public Object b() {
        return null;
    }

    @Override // defpackage.av
    public int c() {
        return ((ExtendedFloatingActionButton) this.H).i0;
    }

    @Override // defpackage.fc
    public void d() {
        View view = (View) this.F;
        view.clearAnimation();
        ((ViewGroup) this.G).endViewTransition(view);
        ((mn) this.H).e();
    }

    @Override // defpackage.j40
    public Uri e() {
        return (Uri) this.F;
    }

    @Override // defpackage.av
    public int g() {
        return ((ExtendedFloatingActionButton) this.H).h0;
    }

    @Override // defpackage.av
    public int getHeight() {
        int i = ((ExtendedFloatingActionButton) this.H).p0;
        return i == -1 ? ((i6) this.F).getHeight() : (i == 0 || i == -2) ? ((ExtendedFloatingActionButton) ((z8) this.G).F).getMeasuredHeight() : i;
    }

    @Override // defpackage.av
    public int getWidth() {
        int i = ((ExtendedFloatingActionButton) this.H).o0;
        return i == -1 ? ((i6) this.F).getWidth() : (i == 0 || i == -2) ? ((z8) this.G).getWidth() : i;
    }

    @Override // defpackage.j40
    public Uri h() {
        return (Uri) this.H;
    }

    public void i(vz vzVar) {
        if (((ArrayList) this.F).contains(vzVar)) {
            throw new IllegalStateException("Fragment already added: " + vzVar);
        }
        synchronized (((ArrayList) this.F)) {
            ((ArrayList) this.F).add(vzVar);
        }
        vzVar.O = true;
    }

    public void j(double d, float f) {
        int length = ((float[]) this.F).length + 1;
        int iBinarySearch = Arrays.binarySearch((double[]) this.G, d);
        if (iBinarySearch < 0) {
            iBinarySearch = (-iBinarySearch) - 1;
        }
        this.G = Arrays.copyOf((double[]) this.G, length);
        this.F = Arrays.copyOf((float[]) this.F, length);
        this.H = new double[length];
        double[] dArr = (double[]) this.G;
        System.arraycopy(dArr, iBinarySearch, dArr, iBinarySearch + 1, (length - iBinarySearch) - 1);
        ((double[]) this.G)[iBinarySearch] = d;
        ((float[]) this.F)[iBinarySearch] = f;
    }

    public void k(int[] iArr, ValueAnimator valueAnimator) {
        er0 er0Var = new er0();
        valueAnimator.addListener((z2) this.H);
        ((ArrayList) this.F).add(er0Var);
    }

    public void l(View view, int i, boolean z) {
        RecyclerView recyclerView = ((cn0) this.F).a;
        int childCount = i < 0 ? recyclerView.getChildCount() : F(i);
        ((ae) this.G).e(childCount, z);
        if (z) {
            K(view);
        }
        recyclerView.addView(view, childCount);
        RecyclerView.I(view);
        ArrayList arrayList = recyclerView.f0;
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ((a61) ((jn0) recyclerView.f0.get(size))).getClass();
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
                if (((ViewGroup.MarginLayoutParams) layoutParams).width != -1 || ((ViewGroup.MarginLayoutParams) layoutParams).height != -1) {
                    throw new IllegalStateException("Pages must fill the whole ViewPager2 (use match_parent)");
                }
            }
        }
    }

    public void m(View view, int i, ViewGroup.LayoutParams layoutParams, boolean z) {
        RecyclerView recyclerView = ((cn0) this.F).a;
        int childCount = i < 0 ? recyclerView.getChildCount() : F(i);
        ((ae) this.G).e(childCount, z);
        if (z) {
            K(view);
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

    public void n(int i) {
        n nVarI;
        int iF = F(i);
        ((ae) this.G).f(iF);
        RecyclerView recyclerView = ((cn0) this.F).a;
        View childAt = recyclerView.getChildAt(iF);
        if (childAt != null && (nVarI = RecyclerView.I(childAt)) != null) {
            if (nVarI.j() && !nVarI.o()) {
                throw new IllegalArgumentException("called detach on an already detached child " + nVarI + recyclerView.y());
            }
            nVarI.a(NatsJetStreamConstants.MAX_PULL_SIZE);
        }
        recyclerView.detachViewFromParent(iF);
    }

    public void o(Bundle bundle) {
        HashSet hashSet;
        String string = ((Context) this.H).getString(R$string.androidx_startup);
        if (bundle != null) {
            try {
                HashSet hashSet2 = new HashSet();
                Iterator<String> it = bundle.keySet().iterator();
                while (true) {
                    boolean zHasNext = it.hasNext();
                    hashSet = (HashSet) this.G;
                    if (!zHasNext) {
                        break;
                    }
                    String next = it.next();
                    if (string.equals(bundle.getString(next, null))) {
                        Class<?> cls = Class.forName(next);
                        if (f40.class.isAssignableFrom(cls)) {
                            hashSet.add(cls);
                        }
                    }
                }
                Iterator it2 = hashSet.iterator();
                while (it2.hasNext()) {
                    r((Class) it2.next(), hashSet2);
                }
            } catch (ClassNotFoundException e) {
                throw new StartupException(e);
            }
        }
    }

    @Override // defpackage.av
    public ViewGroup.LayoutParams p() {
        ExtendedFloatingActionButton extendedFloatingActionButton = (ExtendedFloatingActionButton) this.H;
        int i = extendedFloatingActionButton.o0;
        if (i == 0) {
            i = -2;
        }
        int i2 = extendedFloatingActionButton.p0;
        return new ViewGroup.LayoutParams(i, i2 != 0 ? i2 : -2);
    }

    @Override // defpackage.qi0
    public Object q() {
        try {
            return ((ma0) this.F).b0((Class) this.G);
        } catch (Exception e) {
            throw new RuntimeException("Unable to invoke no-args constructor for " + ((Type) this.H) + ". Registering an InstanceCreator with Gson for this type may fix this problem.", e);
        }
    }

    public Object r(Class cls, HashSet hashSet) {
        Object objCreate;
        if (ma0.M()) {
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
        HashMap map = (HashMap) this.F;
        if (map.containsKey(cls)) {
            objCreate = map.get(cls);
        } else {
            hashSet.add(cls);
            try {
                f40 f40Var = (f40) cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                List<Class> listDependencies = f40Var.dependencies();
                if (!listDependencies.isEmpty()) {
                    for (Class cls2 : listDependencies) {
                        if (!map.containsKey(cls2)) {
                            r(cls2, hashSet);
                        }
                    }
                }
                objCreate = f40Var.create((Context) this.H);
                hashSet.remove(cls);
                map.put(cls, objCreate);
            } catch (Throwable th2) {
                throw new StartupException(th2);
            }
        }
        Trace.endSection();
        return objCreate;
    }

    public vz s(String str) {
        d dVar = (d) ((HashMap) this.G).get(str);
        if (dVar != null) {
            return dVar.c;
        }
        return null;
    }

    public vz t(String str) {
        for (d dVar : ((HashMap) this.G).values()) {
            if (dVar != null) {
                vz vzVarT = dVar.c;
                if (!str.equals(vzVarT.I)) {
                    vzVarT = vzVarT.X.c.t(str);
                }
                if (vzVarT != null) {
                    return vzVarT;
                }
            }
        }
        return null;
    }

    public String toString() {
        switch (this.E) {
            case 4:
                return ((ae) this.G).toString() + ", hidden list:" + ((ArrayList) this.H).size();
            case WorkDatabaseVersions.VERSION_11 /* 11 */:
                return "pos =" + Arrays.toString((double[]) this.G) + " period=" + Arrays.toString((float[]) this.F);
            case WorkDatabaseVersions.VERSION_12 /* 12 */:
                StringBuffer stringBuffer = new StringBuffer("[");
                int i = 0;
                while (true) {
                    int[] iArr = (int[]) this.F;
                    if (i >= iArr.length) {
                        stringBuffer.append("]");
                        return stringBuffer.toString();
                    }
                    stringBuffer.append(iArr[i]);
                    if (i != ((int[]) this.F).length - 1) {
                        stringBuffer.append(", ");
                    }
                    i++;
                }
                break;
            default:
                return super.toString();
        }
    }

    public oq0 u(fu fuVar) {
        switch (this.E) {
            case WorkDatabaseVersions.VERSION_13 /* 13 */:
                r20 r20Var = new r20();
                Hashtable hashtable = k31.a;
                q20 q20Var = (q20) this.F;
                q20Var.a.getClass();
                if (256 > ((Integer) k31.a.get("SHA-512")).intValue()) {
                    throw new IllegalArgumentException("Requested security strength is not supported by the derivation function");
                }
                if (fuVar.c() < 256) {
                    throw new IllegalArgumentException("Not enough entropy for security strength required");
                }
                r20Var.f = NatsJetStreamConstants.MAX_PULL_SIZE;
                r20Var.d = fuVar;
                r20Var.e = q20Var;
                byte[] bArrB = fuVar.b();
                if (bArrB.length < (r20Var.f + 7) / 8) {
                    throw new IllegalStateException("Insufficient entropy provided by entropy source");
                }
                byte[] bArrN = z91.n(bArrB, (byte[]) this.G, (byte[]) this.H);
                int i = q20Var.b;
                r20Var.a = new byte[i];
                byte[] bArr = new byte[i];
                r20Var.b = bArr;
                Arrays.fill(bArr, (byte) 1);
                r20Var.c(bArrN, (byte) 0);
                if (bArrN != null) {
                    r20Var.c(bArrN, (byte) 1);
                }
                r20Var.c = 1L;
                return r20Var;
            default:
                x20 x20Var = new x20();
                Hashtable hashtable2 = k31.a;
                fq0 fq0Var = (fq0) this.F;
                if (256 > ((Integer) hashtable2.get("SHA-512")).intValue()) {
                    throw new IllegalArgumentException("Requested security strength is not supported by the derivation function");
                }
                if (fuVar.c() < 256) {
                    throw new IllegalArgumentException("Not enough entropy for security strength required");
                }
                x20Var.a = fq0Var;
                x20Var.e = fuVar;
                x20Var.f = NatsJetStreamConstants.MAX_PULL_SIZE;
                int iIntValue = ((Integer) x20.i.get("SHA-512")).intValue();
                x20Var.g = iIntValue;
                byte[] bArrB2 = x20Var.e.b();
                if (bArrB2.length < (x20Var.f + 7) / 8) {
                    throw new IllegalStateException("Insufficient entropy provided by entropy source");
                }
                byte[] bArrA = k31.a(fq0Var, z91.n(bArrB2, (byte[]) this.G, (byte[]) this.H), iIntValue);
                x20Var.b = bArrA;
                byte[] bArr2 = new byte[bArrA.length + 1];
                System.arraycopy(bArrA, 0, bArr2, 1, bArrA.length);
                x20Var.c = k31.a(fq0Var, bArr2, iIntValue);
                x20Var.d = 1L;
                return x20Var;
        }
    }

    public ArrayList v() {
        ArrayList arrayList = new ArrayList();
        for (d dVar : ((HashMap) this.G).values()) {
            if (dVar != null) {
                arrayList.add(dVar);
            }
        }
        return arrayList;
    }

    public View w(int i) {
        return ((cn0) this.F).a.getChildAt(F(i));
    }

    public int x() {
        return ((cn0) this.F).a.getChildCount() - ((ArrayList) this.H).size();
    }

    public ColorStateList y(int i) {
        int resourceId;
        ColorStateList colorStateListY;
        TypedArray typedArray = (TypedArray) this.G;
        return (!typedArray.hasValue(i) || (resourceId = typedArray.getResourceId(i, 0)) == 0 || (colorStateListY = t40.Y((Context) this.F, resourceId)) == null) ? typedArray.getColorStateList(i) : colorStateListY;
    }

    public ou(fb0 fb0Var) {
        this.E = 12;
        this.F = new int[NatsJetStreamConstants.MAX_PULL_SIZE];
        this.G = fb0Var;
        i6 i6Var = (i6) fb0Var.h;
        this.H = i6Var;
        i6Var.getClass();
    }

    public /* synthetic */ ou(Object obj, Object obj2, Object obj3, int i) {
        this.E = i;
        this.F = obj;
        this.G = obj2;
        this.H = obj3;
    }

    public ou(int i) {
        this.E = i;
        switch (i) {
            case WorkDatabaseVersions.VERSION_15 /* 15 */:
                this.F = new ArrayList();
                this.G = null;
                this.H = new z2(11, this);
                break;
            default:
                this.F = new ArrayList();
                this.G = new HashMap();
                break;
        }
    }

    public ou(cn0 cn0Var) {
        this.E = 4;
        this.F = cn0Var;
        this.G = new ae();
        this.H = new ArrayList();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ou(FrameLayout frameLayout) {
        Object nc0Var;
        this.E = 10;
        mc0 mc0Var = (mc0) frameLayout;
        int i = Build.VERSION.SDK_INT;
        if (i >= 34) {
            nc0Var = new pc0();
        } else {
            nc0Var = i >= 33 ? new nc0() : null;
        }
        this.F = nc0Var;
        this.G = mc0Var;
        this.H = frameLayout;
    }

    public ou(Context context, TypedArray typedArray) {
        this.E = 16;
        this.F = context;
        this.G = typedArray;
    }

    public ou(Context context, LocationManager locationManager) {
        this.E = 17;
        this.H = new d21();
        this.F = context;
        this.G = locationManager;
    }

    public ou(Context context) {
        this.E = 1;
        this.H = context.getApplicationContext();
        this.G = new HashSet();
        this.F = new HashMap();
    }

    @Override // defpackage.j40
    public void f() {
    }

    public ou(zh zhVar) {
        this.E = 3;
        this.F = new ArrayList();
        this.G = new ga();
        this.H = zhVar;
    }

    public ou(z8 z8Var) {
        this.E = 2;
        this.E = 2;
        this.F = z8Var;
        this.G = Choreographer.getInstance();
        this.H = new w4(this);
    }

    public ou(Class cls, Type type) {
        Object f31Var;
        this.E = 5;
        this.G = cls;
        this.H = type;
        try {
            try {
                try {
                    Class<?> cls2 = Class.forName("sun.misc.Unsafe");
                    Field declaredField = cls2.getDeclaredField("theUnsafe");
                    declaredField.setAccessible(true);
                    f31Var = new c31(cls2.getMethod("allocateInstance", Class.class), declaredField.get(null));
                } catch (Exception unused) {
                    f31Var = new f31();
                }
            } catch (Exception unused2) {
                Method declaredMethod = ObjectInputStream.class.getDeclaredMethod("newInstance", Class.class, Class.class);
                declaredMethod.setAccessible(true);
                f31Var = new e31(declaredMethod);
            }
        } catch (Exception unused3) {
            Method declaredMethod2 = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", Class.class);
            declaredMethod2.setAccessible(true);
            int iIntValue = ((Integer) declaredMethod2.invoke(null, Object.class)).intValue();
            Method declaredMethod3 = ObjectStreamClass.class.getDeclaredMethod("newInstance", Class.class, Integer.TYPE);
            declaredMethod3.setAccessible(true);
            f31Var = new d31(iIntValue, declaredMethod3);
        }
        this.F = f31Var;
    }

    public ou(ExtendedFloatingActionButton extendedFloatingActionButton, i6 i6Var, z8 z8Var) {
        this.E = 7;
        this.H = extendedFloatingActionButton;
        this.F = i6Var;
        this.G = z8Var;
    }
}
