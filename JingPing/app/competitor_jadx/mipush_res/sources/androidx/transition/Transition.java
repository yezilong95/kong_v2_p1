package androidx.transition;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.InflateException;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import defpackage.a21;
import defpackage.cb0;
import defpackage.f51;
import defpackage.k8;
import defpackage.la0;
import defpackage.no;
import defpackage.o80;
import defpackage.q11;
import defpackage.qm;
import defpackage.s11;
import defpackage.t41;
import defpackage.tb;
import defpackage.xp;
import defpackage.z2;
import defpackage.z91;
import defpackage.zn;
import io.nats.client.BaseConsumeOptions;
import io.nats.client.support.ApiConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class Transition implements Cloneable {
    public static final Animator[] b0 = new Animator[0];
    public static final int[] c0 = {2, 1, 3, 4};
    public static final q11 d0 = new q11();
    public static final ThreadLocal e0 = new ThreadLocal();
    public final String E;
    public long F;
    public long G;
    public TimeInterpolator H;
    public final ArrayList I;
    public final ArrayList J;
    public tb K;
    public tb L;
    public TransitionSet M;
    public final int[] N;
    public ArrayList O;
    public ArrayList P;
    public s11[] Q;
    public final ArrayList R;
    public Animator[] S;
    public int T;
    public boolean U;
    public boolean V;
    public Transition W;
    public ArrayList X;
    public ArrayList Y;
    public z91 Z;
    public q11 a0;

    public Transition() {
        this.E = getClass().getName();
        this.F = -1L;
        this.G = -1L;
        this.H = null;
        this.I = new ArrayList();
        this.J = new ArrayList();
        this.K = new tb(6);
        this.L = new tb(6);
        this.M = null;
        this.N = c0;
        this.R = new ArrayList();
        this.S = b0;
        this.T = 0;
        this.U = false;
        this.V = false;
        this.W = null;
        this.X = null;
        this.Y = new ArrayList();
        this.a0 = d0;
    }

    public static void b(tb tbVar, View view, a21 a21Var) {
        ((k8) tbVar.a).put(view, a21Var);
        int id = view.getId();
        if (id >= 0) {
            SparseArray sparseArray = (SparseArray) tbVar.b;
            if (sparseArray.indexOfKey(id) >= 0) {
                sparseArray.put(id, null);
            } else {
                sparseArray.put(id, view);
            }
        }
        WeakHashMap weakHashMap = f51.a;
        String strK = t41.k(view);
        if (strK != null) {
            k8 k8Var = (k8) tbVar.d;
            if (k8Var.containsKey(strK)) {
                k8Var.put(strK, null);
            } else {
                k8Var.put(strK, view);
            }
        }
        if (view.getParent() instanceof ListView) {
            ListView listView = (ListView) view.getParent();
            if (listView.getAdapter().hasStableIds()) {
                long itemIdAtPosition = listView.getItemIdAtPosition(listView.getPositionForView(view));
                cb0 cb0Var = (cb0) tbVar.c;
                if (cb0Var.E) {
                    cb0Var.b();
                }
                if (z91.h(cb0Var.F, cb0Var.H, itemIdAtPosition) < 0) {
                    view.setHasTransientState(true);
                    cb0Var.d(itemIdAtPosition, view);
                    return;
                }
                View view2 = (View) cb0Var.c(itemIdAtPosition, null);
                if (view2 != null) {
                    view2.setHasTransientState(false);
                    cb0Var.d(itemIdAtPosition, null);
                }
            }
        }
    }

    public static k8 p() {
        ThreadLocal threadLocal = e0;
        k8 k8Var = (k8) threadLocal.get();
        if (k8Var != null) {
            return k8Var;
        }
        k8 k8Var2 = new k8();
        threadLocal.set(k8Var2);
        return k8Var2;
    }

    public static boolean u(a21 a21Var, a21 a21Var2, String str) {
        Object obj = a21Var.a.get(str);
        Object obj2 = a21Var2.a.get(str);
        if (obj == null && obj2 == null) {
            return false;
        }
        if (obj == null || obj2 == null) {
            return true;
        }
        return !obj.equals(obj2);
    }

    public void A(long j) {
        this.G = j;
    }

    public void C(TimeInterpolator timeInterpolator) {
        this.H = timeInterpolator;
    }

    public void D(q11 q11Var) {
        if (q11Var == null) {
            this.a0 = d0;
        } else {
            this.a0 = q11Var;
        }
    }

    public void E(z91 z91Var) {
        this.Z = z91Var;
    }

    public void F(long j) {
        this.F = j;
    }

    public final void G() {
        if (this.T == 0) {
            v(this, no.F);
            this.V = false;
        }
        this.T++;
    }

    public String H(String str) {
        StringBuilder sb = new StringBuilder(str);
        sb.append(getClass().getSimpleName());
        sb.append("@");
        sb.append(Integer.toHexString(hashCode()));
        sb.append(": ");
        if (this.G != -1) {
            sb.append("dur(");
            sb.append(this.G);
            sb.append(") ");
        }
        if (this.F != -1) {
            sb.append("dly(");
            sb.append(this.F);
            sb.append(") ");
        }
        if (this.H != null) {
            sb.append("interp(");
            sb.append(this.H);
            sb.append(") ");
        }
        ArrayList arrayList = this.I;
        int size = arrayList.size();
        ArrayList arrayList2 = this.J;
        if (size > 0 || arrayList2.size() > 0) {
            sb.append("tgts(");
            if (arrayList.size() > 0) {
                for (int i = 0; i < arrayList.size(); i++) {
                    if (i > 0) {
                        sb.append(", ");
                    }
                    sb.append(arrayList.get(i));
                }
            }
            if (arrayList2.size() > 0) {
                for (int i2 = 0; i2 < arrayList2.size(); i2++) {
                    if (i2 > 0) {
                        sb.append(", ");
                    }
                    sb.append(arrayList2.get(i2));
                }
            }
            sb.append(")");
        }
        return sb.toString();
    }

    public void a(s11 s11Var) {
        if (this.X == null) {
            this.X = new ArrayList();
        }
        this.X.add(s11Var);
    }

    public void c() {
        ArrayList arrayList = this.R;
        int size = arrayList.size();
        Animator[] animatorArr = (Animator[]) arrayList.toArray(this.S);
        this.S = b0;
        for (int i = size - 1; i >= 0; i--) {
            Animator animator = animatorArr[i];
            animatorArr[i] = null;
            animator.cancel();
        }
        this.S = animatorArr;
        v(this, no.H);
    }

    public abstract void d(a21 a21Var);

    public final void e(View view, boolean z) {
        if (view == null) {
            return;
        }
        view.getId();
        if (view.getParent() instanceof ViewGroup) {
            a21 a21Var = new a21(view);
            if (z) {
                g(a21Var);
            } else {
                d(a21Var);
            }
            a21Var.c.add(this);
            f(a21Var);
            if (z) {
                b(this.K, view, a21Var);
            } else {
                b(this.L, view, a21Var);
            }
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                e(viewGroup.getChildAt(i), z);
            }
        }
    }

    public void f(a21 a21Var) {
        if (this.Z != null) {
            HashMap map = a21Var.a;
            if (map.isEmpty()) {
                return;
            }
            this.Z.getClass();
            String[] strArr = z91.Z;
            for (int i = 0; i < 2; i++) {
                if (!map.containsKey(strArr[i])) {
                    this.Z.getClass();
                    Integer numValueOf = (Integer) map.get("android:visibility:visibility");
                    View view = a21Var.b;
                    if (numValueOf == null) {
                        numValueOf = Integer.valueOf(view.getVisibility());
                    }
                    map.put("android:visibilityPropagation:visibility", numValueOf);
                    int[] iArr = {iRound, 0};
                    view.getLocationOnScreen(iArr);
                    int iRound = Math.round(view.getTranslationX()) + iArr[0];
                    iArr[0] = (view.getWidth() / 2) + iRound;
                    int iRound2 = Math.round(view.getTranslationY()) + iArr[1];
                    iArr[1] = iRound2;
                    iArr[1] = (view.getHeight() / 2) + iRound2;
                    map.put("android:visibilityPropagation:center", iArr);
                    return;
                }
            }
        }
    }

    public abstract void g(a21 a21Var);

    public final void h(ViewGroup viewGroup, boolean z) {
        i(z);
        ArrayList arrayList = this.I;
        int size = arrayList.size();
        ArrayList arrayList2 = this.J;
        if (size <= 0 && arrayList2.size() <= 0) {
            e(viewGroup, z);
            return;
        }
        for (int i = 0; i < arrayList.size(); i++) {
            View viewFindViewById = viewGroup.findViewById(((Integer) arrayList.get(i)).intValue());
            if (viewFindViewById != null) {
                a21 a21Var = new a21(viewFindViewById);
                if (z) {
                    g(a21Var);
                } else {
                    d(a21Var);
                }
                a21Var.c.add(this);
                f(a21Var);
                if (z) {
                    b(this.K, viewFindViewById, a21Var);
                } else {
                    b(this.L, viewFindViewById, a21Var);
                }
            }
        }
        for (int i2 = 0; i2 < arrayList2.size(); i2++) {
            View view = (View) arrayList2.get(i2);
            a21 a21Var2 = new a21(view);
            if (z) {
                g(a21Var2);
            } else {
                d(a21Var2);
            }
            a21Var2.c.add(this);
            f(a21Var2);
            if (z) {
                b(this.K, view, a21Var2);
            } else {
                b(this.L, view, a21Var2);
            }
        }
    }

    public final void i(boolean z) {
        if (z) {
            ((k8) this.K.a).clear();
            ((SparseArray) this.K.b).clear();
            ((cb0) this.K.c).a();
        } else {
            ((k8) this.L.a).clear();
            ((SparseArray) this.L.b).clear();
            ((cb0) this.L.c).a();
        }
    }

    @Override // 
    /* JADX INFO: renamed from: j */
    public Transition clone() {
        try {
            Transition transition = (Transition) super.clone();
            transition.Y = new ArrayList();
            transition.K = new tb(6);
            transition.L = new tb(6);
            transition.O = null;
            transition.P = null;
            transition.W = this;
            transition.X = null;
            return transition;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public Animator k(ViewGroup viewGroup, a21 a21Var, a21 a21Var2) {
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x010f A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void l(android.view.ViewGroup r21, defpackage.tb r22, defpackage.tb r23, java.util.ArrayList r24, java.util.ArrayList r25) {
        /*
            Method dump skipped, instruction units count: 331
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.transition.Transition.l(android.view.ViewGroup, tb, tb, java.util.ArrayList, java.util.ArrayList):void");
    }

    public final void m() {
        int i = this.T - 1;
        this.T = i;
        if (i == 0) {
            v(this, no.G);
            for (int i2 = 0; i2 < ((cb0) this.K.c).e(); i2++) {
                View view = (View) ((cb0) this.K.c).f(i2);
                if (view != null) {
                    view.setHasTransientState(false);
                }
            }
            for (int i3 = 0; i3 < ((cb0) this.L.c).e(); i3++) {
                View view2 = (View) ((cb0) this.L.c).f(i3);
                if (view2 != null) {
                    view2.setHasTransientState(false);
                }
            }
            this.V = true;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x002c, code lost:
    
        if (r2 < 0) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x002e, code lost:
    
        if (r6 == false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0030, code lost:
    
        r5 = r4.P;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0033, code lost:
    
        r5 = r4.O;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x003b, code lost:
    
        return (defpackage.a21) r5.get(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x003c, code lost:
    
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final defpackage.a21 n(android.view.View r5, boolean r6) {
        /*
            r4 = this;
            androidx.transition.TransitionSet r0 = r4.M
            if (r0 == 0) goto L9
            a21 r5 = r0.n(r5, r6)
            return r5
        L9:
            if (r6 == 0) goto Le
            java.util.ArrayList r0 = r4.O
            goto L10
        Le:
            java.util.ArrayList r0 = r4.P
        L10:
            if (r0 != 0) goto L13
            goto L3c
        L13:
            int r1 = r0.size()
            r2 = 0
        L18:
            if (r2 >= r1) goto L2b
            java.lang.Object r3 = r0.get(r2)
            a21 r3 = (defpackage.a21) r3
            if (r3 != 0) goto L23
            goto L3c
        L23:
            android.view.View r3 = r3.b
            if (r3 != r5) goto L28
            goto L2c
        L28:
            int r2 = r2 + 1
            goto L18
        L2b:
            r2 = -1
        L2c:
            if (r2 < 0) goto L3c
            if (r6 == 0) goto L33
            java.util.ArrayList r5 = r4.P
            goto L35
        L33:
            java.util.ArrayList r5 = r4.O
        L35:
            java.lang.Object r5 = r5.get(r2)
            a21 r5 = (defpackage.a21) r5
            return r5
        L3c:
            r5 = 0
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.transition.Transition.n(android.view.View, boolean):a21");
    }

    public final Transition o() {
        TransitionSet transitionSet = this.M;
        return transitionSet != null ? transitionSet.o() : this;
    }

    public String[] q() {
        return null;
    }

    public final a21 r(View view, boolean z) {
        TransitionSet transitionSet = this.M;
        if (transitionSet != null) {
            return transitionSet.r(view, z);
        }
        return (a21) ((k8) (z ? this.K : this.L).a).getOrDefault(view, null);
    }

    public boolean s(a21 a21Var, a21 a21Var2) {
        if (a21Var != null && a21Var2 != null) {
            String[] strArrQ = q();
            if (strArrQ != null) {
                for (String str : strArrQ) {
                    if (u(a21Var, a21Var2, str)) {
                        return true;
                    }
                }
            } else {
                Iterator it = a21Var.a.keySet().iterator();
                while (it.hasNext()) {
                    if (u(a21Var, a21Var2, (String) it.next())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public final boolean t(View view) {
        int id = view.getId();
        ArrayList arrayList = this.I;
        int size = arrayList.size();
        ArrayList arrayList2 = this.J;
        return (size == 0 && arrayList2.size() == 0) || arrayList.contains(Integer.valueOf(id)) || arrayList2.contains(view);
    }

    public final String toString() {
        return H("");
    }

    public final void v(Transition transition, no noVar) {
        Transition transition2 = this.W;
        if (transition2 != null) {
            transition2.v(transition, noVar);
        }
        ArrayList arrayList = this.X;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        int size = this.X.size();
        s11[] s11VarArr = this.Q;
        if (s11VarArr == null) {
            s11VarArr = new s11[size];
        }
        this.Q = null;
        s11[] s11VarArr2 = (s11[]) this.X.toArray(s11VarArr);
        for (int i = 0; i < size; i++) {
            s11 s11Var = s11VarArr2[i];
            switch (noVar.E) {
                case 24:
                    s11Var.f(transition);
                    break;
                case BaseConsumeOptions.DEFAULT_THRESHOLD_PERCENT /* 25 */:
                    s11Var.a(transition);
                    break;
                case 26:
                    s11Var.d(transition);
                    break;
                case 27:
                    s11Var.b();
                    break;
                default:
                    s11Var.e();
                    break;
            }
            s11VarArr2[i] = null;
        }
        this.Q = s11VarArr2;
    }

    public void w(ViewGroup viewGroup) {
        if (this.V) {
            return;
        }
        ArrayList arrayList = this.R;
        int size = arrayList.size();
        Animator[] animatorArr = (Animator[]) arrayList.toArray(this.S);
        this.S = b0;
        for (int i = size - 1; i >= 0; i--) {
            Animator animator = animatorArr[i];
            animatorArr[i] = null;
            animator.pause();
        }
        this.S = animatorArr;
        v(this, no.I);
        this.U = true;
    }

    public Transition x(s11 s11Var) {
        Transition transition;
        ArrayList arrayList = this.X;
        if (arrayList != null) {
            if (!arrayList.remove(s11Var) && (transition = this.W) != null) {
                transition.x(s11Var);
            }
            if (this.X.size() == 0) {
                this.X = null;
            }
        }
        return this;
    }

    public void y(View view) {
        if (this.U) {
            if (!this.V) {
                ArrayList arrayList = this.R;
                int size = arrayList.size();
                Animator[] animatorArr = (Animator[]) arrayList.toArray(this.S);
                this.S = b0;
                for (int i = size - 1; i >= 0; i--) {
                    Animator animator = animatorArr[i];
                    animatorArr[i] = null;
                    animator.resume();
                }
                this.S = animatorArr;
                v(this, no.J);
            }
            this.U = false;
        }
    }

    public void z() {
        G();
        k8 k8VarP = p();
        for (Animator animator : this.Y) {
            if (k8VarP.containsKey(animator)) {
                G();
                if (animator != null) {
                    animator.addListener(new xp(3, this, k8VarP, false));
                    long j = this.G;
                    if (j >= 0) {
                        animator.setDuration(j);
                    }
                    long j2 = this.F;
                    if (j2 >= 0) {
                        animator.setStartDelay(animator.getStartDelay() + j2);
                    }
                    TimeInterpolator timeInterpolator = this.H;
                    if (timeInterpolator != null) {
                        animator.setInterpolator(timeInterpolator);
                    }
                    animator.addListener(new z2(12, this));
                    animator.start();
                }
            }
        }
        this.Y.clear();
        m();
    }

    public Transition(Context context, AttributeSet attributeSet) {
        this.E = getClass().getName();
        this.F = -1L;
        this.G = -1L;
        this.H = null;
        this.I = new ArrayList();
        this.J = new ArrayList();
        this.K = new tb(6);
        this.L = new tb(6);
        this.M = null;
        int[] iArr = c0;
        this.N = iArr;
        this.R = new ArrayList();
        this.S = b0;
        this.T = 0;
        this.U = false;
        this.V = false;
        this.W = null;
        this.X = null;
        this.Y = new ArrayList();
        this.a0 = d0;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, qm.r);
        XmlResourceParser xmlResourceParser = (XmlResourceParser) attributeSet;
        long jY = la0.y(typedArrayObtainStyledAttributes, xmlResourceParser, "duration", 1, -1);
        if (jY >= 0) {
            A(jY);
        }
        long j = xmlResourceParser.getAttributeValue("http://schemas.android.com/apk/res/android", "startDelay") != null ? typedArrayObtainStyledAttributes.getInt(2, -1) : -1;
        if (j > 0) {
            F(j);
        }
        int resourceId = xmlResourceParser.getAttributeValue("http://schemas.android.com/apk/res/android", "interpolator") != null ? typedArrayObtainStyledAttributes.getResourceId(0, 0) : 0;
        if (resourceId > 0) {
            C(AnimationUtils.loadInterpolator(context, resourceId));
        }
        String strZ = la0.z(typedArrayObtainStyledAttributes, xmlResourceParser, "matchOrder", 3);
        if (strZ != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(strZ, ",");
            int[] iArr2 = new int[stringTokenizer.countTokens()];
            int i = 0;
            while (stringTokenizer.hasMoreTokens()) {
                String strTrim = stringTokenizer.nextToken().trim();
                if (ApiConstants.ID.equalsIgnoreCase(strTrim)) {
                    iArr2[i] = 3;
                } else if ("instance".equalsIgnoreCase(strTrim)) {
                    iArr2[i] = 1;
                } else if (ApiConstants.NAME.equalsIgnoreCase(strTrim)) {
                    iArr2[i] = 2;
                } else if ("itemId".equalsIgnoreCase(strTrim)) {
                    iArr2[i] = 4;
                } else if (strTrim.isEmpty()) {
                    int[] iArr3 = new int[iArr2.length - 1];
                    System.arraycopy(iArr2, 0, iArr3, 0, i);
                    i--;
                    iArr2 = iArr3;
                } else {
                    throw new InflateException(zn.n("Unknown match type in matchOrder: '", strTrim, "'"));
                }
                i++;
            }
            if (iArr2.length == 0) {
                this.N = iArr;
            } else {
                for (int i2 = 0; i2 < iArr2.length; i2++) {
                    int i3 = iArr2[i2];
                    if (i3 < 1 || i3 > 4) {
                        throw new IllegalArgumentException("matches contains invalid value");
                    }
                    for (int i4 = 0; i4 < i2; i4++) {
                        if (iArr2[i4] == i3) {
                            throw new IllegalArgumentException("matches contains a duplicate value");
                        }
                    }
                }
                this.N = (int[]) iArr2.clone();
            }
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public void B(o80 o80Var) {
    }
}
