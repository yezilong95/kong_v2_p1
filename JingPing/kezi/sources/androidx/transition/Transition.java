package androidx.transition;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.InflateException;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowId;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import defpackage.ba;
import defpackage.c7;
import defpackage.d1;
import defpackage.ep0;
import defpackage.fp0;
import defpackage.gp0;
import defpackage.hk;
import defpackage.jt0;
import defpackage.k9;
import defpackage.lr0;
import defpackage.lt0;
import defpackage.mp0;
import defpackage.nv0;
import defpackage.t30;
import defpackage.ul;
import defpackage.xi;
import defpackage.xr0;
import defpackage.za0;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.WeakHashMap;
import org.java_websocket.extensions.ExtensionRequestData;

/* JADX INFO: loaded from: classes.dex */
public abstract class Transition implements Cloneable {
    public static final int[] v = {2, 1, 3, 4};
    public static final ep0 w = new ep0();
    public static final ThreadLocal x = new ThreadLocal();
    public final String b;
    public long c;
    public long d;
    public TimeInterpolator e;
    public final ArrayList f;
    public final ArrayList g;
    public ba h;
    public ba i;
    public TransitionSet j;
    public final int[] k;
    public ArrayList l;
    public ArrayList m;
    public final ArrayList n;
    public int o;
    public boolean p;
    public boolean q;
    public ArrayList r;
    public ArrayList s;
    public nv0 t;
    public ep0 u;

    public Transition() {
        this.b = getClass().getName();
        this.c = -1L;
        this.d = -1L;
        this.e = null;
        this.f = new ArrayList();
        this.g = new ArrayList();
        this.h = new ba(5);
        this.i = new ba(5);
        this.j = null;
        this.k = v;
        this.n = new ArrayList();
        this.o = 0;
        this.p = false;
        this.q = false;
        this.r = null;
        this.s = new ArrayList();
        this.u = w;
    }

    public static void b(ba baVar, View view, mp0 mp0Var) {
        ((c7) baVar.a).put(view, mp0Var);
        int id = view.getId();
        if (id >= 0) {
            SparseArray sparseArray = (SparseArray) baVar.b;
            if (sparseArray.indexOfKey(id) >= 0) {
                sparseArray.put(id, null);
            } else {
                sparseArray.put(id, view);
            }
        }
        WeakHashMap weakHashMap = xr0.a;
        String strK = lr0.k(view);
        if (strK != null) {
            c7 c7Var = (c7) baVar.d;
            if (c7Var.containsKey(strK)) {
                c7Var.put(strK, null);
            } else {
                c7Var.put(strK, view);
            }
        }
        if (view.getParent() instanceof ListView) {
            ListView listView = (ListView) view.getParent();
            if (listView.getAdapter().hasStableIds()) {
                long itemIdAtPosition = listView.getItemIdAtPosition(listView.getPositionForView(view));
                t30 t30Var = (t30) baVar.c;
                if (t30Var.b) {
                    t30Var.b();
                }
                if (xi.e(t30Var.c, t30Var.e, itemIdAtPosition) < 0) {
                    view.setHasTransientState(true);
                    t30Var.d(itemIdAtPosition, view);
                    return;
                }
                View view2 = (View) t30Var.c(itemIdAtPosition, null);
                if (view2 != null) {
                    view2.setHasTransientState(false);
                    t30Var.d(itemIdAtPosition, null);
                }
            }
        }
    }

    public static c7 n() {
        ThreadLocal threadLocal = x;
        c7 c7Var = (c7) threadLocal.get();
        if (c7Var != null) {
            return c7Var;
        }
        c7 c7Var2 = new c7();
        threadLocal.set(c7Var2);
        return c7Var2;
    }

    public static boolean s(mp0 mp0Var, mp0 mp0Var2, String str) {
        Object obj = mp0Var.a.get(str);
        Object obj2 = mp0Var2.a.get(str);
        if (obj == null && obj2 == null) {
            return false;
        }
        if (obj == null || obj2 == null) {
            return true;
        }
        return !obj.equals(obj2);
    }

    public void A(ep0 ep0Var) {
        if (ep0Var == null) {
            this.u = w;
        } else {
            this.u = ep0Var;
        }
    }

    public void B(nv0 nv0Var) {
        this.t = nv0Var;
    }

    public void C(long j) {
        this.c = j;
    }

    public final void D() {
        if (this.o == 0) {
            ArrayList arrayList = this.r;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.r.clone();
                int size = arrayList2.size();
                for (int i = 0; i < size; i++) {
                    ((gp0) arrayList2.get(i)).b();
                }
            }
            this.q = false;
        }
        this.o++;
    }

    public String E(String str) {
        StringBuilder sbL = hk.l(str);
        sbL.append(getClass().getSimpleName());
        sbL.append("@");
        sbL.append(Integer.toHexString(hashCode()));
        sbL.append(": ");
        String string = sbL.toString();
        if (this.d != -1) {
            string = string + "dur(" + this.d + ") ";
        }
        if (this.c != -1) {
            string = string + "dly(" + this.c + ") ";
        }
        if (this.e != null) {
            string = string + "interp(" + this.e + ") ";
        }
        ArrayList arrayList = this.f;
        int size = arrayList.size();
        ArrayList arrayList2 = this.g;
        if (size <= 0 && arrayList2.size() <= 0) {
            return string;
        }
        String strI = hk.i(string, "tgts(");
        if (arrayList.size() > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                if (i > 0) {
                    strI = hk.i(strI, ", ");
                }
                StringBuilder sbL2 = hk.l(strI);
                sbL2.append(arrayList.get(i));
                strI = sbL2.toString();
            }
        }
        if (arrayList2.size() > 0) {
            for (int i2 = 0; i2 < arrayList2.size(); i2++) {
                if (i2 > 0) {
                    strI = hk.i(strI, ", ");
                }
                StringBuilder sbL3 = hk.l(strI);
                sbL3.append(arrayList2.get(i2));
                strI = sbL3.toString();
            }
        }
        return hk.i(strI, ")");
    }

    public void a(gp0 gp0Var) {
        if (this.r == null) {
            this.r = new ArrayList();
        }
        this.r.add(gp0Var);
    }

    public abstract void c(mp0 mp0Var);

    public final void d(View view, boolean z) {
        if (view == null) {
            return;
        }
        view.getId();
        if (view.getParent() instanceof ViewGroup) {
            mp0 mp0Var = new mp0(view);
            if (z) {
                f(mp0Var);
            } else {
                c(mp0Var);
            }
            mp0Var.c.add(this);
            e(mp0Var);
            if (z) {
                b(this.h, view, mp0Var);
            } else {
                b(this.i, view, mp0Var);
            }
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                d(viewGroup.getChildAt(i), z);
            }
        }
    }

    public void e(mp0 mp0Var) {
        if (this.t != null) {
            HashMap map = mp0Var.a;
            if (map.isEmpty()) {
                return;
            }
            this.t.getClass();
            String[] strArr = nv0.q;
            for (int i = 0; i < 2; i++) {
                if (!map.containsKey(strArr[i])) {
                    this.t.getClass();
                    Integer numValueOf = (Integer) map.get("android:visibility:visibility");
                    View view = mp0Var.b;
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

    public abstract void f(mp0 mp0Var);

    public final void g(ViewGroup viewGroup, boolean z) {
        h(z);
        ArrayList arrayList = this.f;
        int size = arrayList.size();
        ArrayList arrayList2 = this.g;
        if (size <= 0 && arrayList2.size() <= 0) {
            d(viewGroup, z);
            return;
        }
        for (int i = 0; i < arrayList.size(); i++) {
            View viewFindViewById = viewGroup.findViewById(((Integer) arrayList.get(i)).intValue());
            if (viewFindViewById != null) {
                mp0 mp0Var = new mp0(viewFindViewById);
                if (z) {
                    f(mp0Var);
                } else {
                    c(mp0Var);
                }
                mp0Var.c.add(this);
                e(mp0Var);
                if (z) {
                    b(this.h, viewFindViewById, mp0Var);
                } else {
                    b(this.i, viewFindViewById, mp0Var);
                }
            }
        }
        for (int i2 = 0; i2 < arrayList2.size(); i2++) {
            View view = (View) arrayList2.get(i2);
            mp0 mp0Var2 = new mp0(view);
            if (z) {
                f(mp0Var2);
            } else {
                c(mp0Var2);
            }
            mp0Var2.c.add(this);
            e(mp0Var2);
            if (z) {
                b(this.h, view, mp0Var2);
            } else {
                b(this.i, view, mp0Var2);
            }
        }
    }

    public final void h(boolean z) {
        if (z) {
            ((c7) this.h.a).clear();
            ((SparseArray) this.h.b).clear();
            ((t30) this.h.c).a();
        } else {
            ((c7) this.i.a).clear();
            ((SparseArray) this.i.b).clear();
            ((t30) this.i.c).a();
        }
    }

    @Override // 
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public Transition clone() {
        try {
            Transition transition = (Transition) super.clone();
            transition.s = new ArrayList();
            transition.h = new ba(5);
            transition.i = new ba(5);
            transition.l = null;
            transition.m = null;
            return transition;
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    public Animator j(ViewGroup viewGroup, mp0 mp0Var, mp0 mp0Var2) {
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0109 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void k(android.view.ViewGroup r21, defpackage.ba r22, defpackage.ba r23, java.util.ArrayList r24, java.util.ArrayList r25) {
        /*
            Method dump skipped, instruction units count: 314
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.transition.Transition.k(android.view.ViewGroup, ba, ba, java.util.ArrayList, java.util.ArrayList):void");
    }

    public final void l() {
        int i = this.o - 1;
        this.o = i;
        if (i == 0) {
            ArrayList arrayList = this.r;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.r.clone();
                int size = arrayList2.size();
                for (int i2 = 0; i2 < size; i2++) {
                    ((gp0) arrayList2.get(i2)).d(this);
                }
            }
            for (int i3 = 0; i3 < ((t30) this.h.c).e(); i3++) {
                View view = (View) ((t30) this.h.c).f(i3);
                if (view != null) {
                    WeakHashMap weakHashMap = xr0.a;
                    view.setHasTransientState(false);
                }
            }
            for (int i4 = 0; i4 < ((t30) this.i.c).e(); i4++) {
                View view2 = (View) ((t30) this.i.c).f(i4);
                if (view2 != null) {
                    WeakHashMap weakHashMap2 = xr0.a;
                    view2.setHasTransientState(false);
                }
            }
            this.q = true;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x002c, code lost:
    
        if (r2 < 0) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x002e, code lost:
    
        if (r6 == false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0030, code lost:
    
        r5 = r4.m;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0033, code lost:
    
        r5 = r4.l;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x003b, code lost:
    
        return (defpackage.mp0) r5.get(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x003c, code lost:
    
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final defpackage.mp0 m(android.view.View r5, boolean r6) {
        /*
            r4 = this;
            androidx.transition.TransitionSet r0 = r4.j
            if (r0 == 0) goto L9
            mp0 r5 = r0.m(r5, r6)
            return r5
        L9:
            if (r6 == 0) goto Le
            java.util.ArrayList r0 = r4.l
            goto L10
        Le:
            java.util.ArrayList r0 = r4.m
        L10:
            if (r0 != 0) goto L13
            goto L3c
        L13:
            int r1 = r0.size()
            r2 = 0
        L18:
            if (r2 >= r1) goto L2b
            java.lang.Object r3 = r0.get(r2)
            mp0 r3 = (defpackage.mp0) r3
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
            java.util.ArrayList r5 = r4.m
            goto L35
        L33:
            java.util.ArrayList r5 = r4.l
        L35:
            java.lang.Object r5 = r5.get(r2)
            mp0 r5 = (defpackage.mp0) r5
            return r5
        L3c:
            r5 = 0
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.transition.Transition.m(android.view.View, boolean):mp0");
    }

    public String[] o() {
        return null;
    }

    public final mp0 p(View view, boolean z) {
        TransitionSet transitionSet = this.j;
        if (transitionSet != null) {
            return transitionSet.p(view, z);
        }
        return (mp0) ((c7) (z ? this.h : this.i).a).getOrDefault(view, null);
    }

    public boolean q(mp0 mp0Var, mp0 mp0Var2) {
        if (mp0Var != null && mp0Var2 != null) {
            String[] strArrO = o();
            if (strArrO != null) {
                for (String str : strArrO) {
                    if (s(mp0Var, mp0Var2, str)) {
                        return true;
                    }
                }
            } else {
                Iterator it = mp0Var.a.keySet().iterator();
                while (it.hasNext()) {
                    if (s(mp0Var, mp0Var2, (String) it.next())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public final boolean r(View view) {
        int id = view.getId();
        ArrayList arrayList = this.f;
        int size = arrayList.size();
        ArrayList arrayList2 = this.g;
        return (size == 0 && arrayList2.size() == 0) || arrayList.contains(Integer.valueOf(id)) || arrayList2.contains(view);
    }

    public void t(ViewGroup viewGroup) {
        if (this.q) {
            return;
        }
        c7 c7VarN = n();
        int i = c7VarN.d;
        lt0 lt0Var = jt0.a;
        WindowId windowId = viewGroup.getWindowId();
        for (int i2 = i - 1; i2 >= 0; i2--) {
            fp0 fp0Var = (fp0) c7VarN.j(i2);
            if (fp0Var.a != null && fp0Var.d.a.equals(windowId)) {
                ((Animator) c7VarN.h(i2)).pause();
            }
        }
        ArrayList arrayList = this.r;
        if (arrayList != null && arrayList.size() > 0) {
            ArrayList arrayList2 = (ArrayList) this.r.clone();
            int size = arrayList2.size();
            for (int i3 = 0; i3 < size; i3++) {
                ((gp0) arrayList2.get(i3)).c();
            }
        }
        this.p = true;
    }

    public final String toString() {
        return E(ExtensionRequestData.EMPTY_VALUE);
    }

    public void u(gp0 gp0Var) {
        ArrayList arrayList = this.r;
        if (arrayList == null) {
            return;
        }
        arrayList.remove(gp0Var);
        if (this.r.size() == 0) {
            this.r = null;
        }
    }

    public void v(View view) {
        if (this.p) {
            if (!this.q) {
                c7 c7VarN = n();
                int i = c7VarN.d;
                lt0 lt0Var = jt0.a;
                WindowId windowId = view.getWindowId();
                for (int i2 = i - 1; i2 >= 0; i2--) {
                    fp0 fp0Var = (fp0) c7VarN.j(i2);
                    if (fp0Var.a != null && fp0Var.d.a.equals(windowId)) {
                        ((Animator) c7VarN.h(i2)).resume();
                    }
                }
                ArrayList arrayList = this.r;
                if (arrayList != null && arrayList.size() > 0) {
                    ArrayList arrayList2 = (ArrayList) this.r.clone();
                    int size = arrayList2.size();
                    for (int i3 = 0; i3 < size; i3++) {
                        ((gp0) arrayList2.get(i3)).e();
                    }
                }
            }
            this.p = false;
        }
    }

    public void w() {
        D();
        c7 c7VarN = n();
        for (Animator animator : this.s) {
            if (c7VarN.containsKey(animator)) {
                D();
                if (animator != null) {
                    animator.addListener(new ul(this, c7VarN));
                    long j = this.d;
                    if (j >= 0) {
                        animator.setDuration(j);
                    }
                    long j2 = this.c;
                    if (j2 >= 0) {
                        animator.setStartDelay(animator.getStartDelay() + j2);
                    }
                    TimeInterpolator timeInterpolator = this.e;
                    if (timeInterpolator != null) {
                        animator.setInterpolator(timeInterpolator);
                    }
                    animator.addListener(new d1(11, this));
                    animator.start();
                }
            }
        }
        this.s.clear();
        l();
    }

    public void x(long j) {
        this.d = j;
    }

    public void z(TimeInterpolator timeInterpolator) {
        this.e = timeInterpolator;
    }

    @SuppressLint({"RestrictedApi"})
    public Transition(Context context, AttributeSet attributeSet) {
        this.b = getClass().getName();
        this.c = -1L;
        this.d = -1L;
        this.e = null;
        this.f = new ArrayList();
        this.g = new ArrayList();
        this.h = new ba(5);
        this.i = new ba(5);
        this.j = null;
        int[] iArr = v;
        this.k = iArr;
        this.n = new ArrayList();
        this.o = 0;
        this.p = false;
        this.q = false;
        this.r = null;
        this.s = new ArrayList();
        this.u = w;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, za0.g);
        XmlResourceParser xmlResourceParser = (XmlResourceParser) attributeSet;
        long jQ = za0.q(typedArrayObtainStyledAttributes, xmlResourceParser, "duration", 1, -1);
        if (jQ >= 0) {
            x(jQ);
        }
        long j = xmlResourceParser.getAttributeValue("http://schemas.android.com/apk/res/android", "startDelay") != null ? typedArrayObtainStyledAttributes.getInt(2, -1) : -1;
        if (j > 0) {
            C(j);
        }
        int resourceId = xmlResourceParser.getAttributeValue("http://schemas.android.com/apk/res/android", "interpolator") != null ? typedArrayObtainStyledAttributes.getResourceId(0, 0) : 0;
        if (resourceId > 0) {
            z(AnimationUtils.loadInterpolator(context, resourceId));
        }
        String strR = za0.r(typedArrayObtainStyledAttributes, xmlResourceParser, "matchOrder", 3);
        if (strR != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(strR, ",");
            int[] iArr2 = new int[stringTokenizer.countTokens()];
            int i = 0;
            while (stringTokenizer.hasMoreTokens()) {
                String strTrim = stringTokenizer.nextToken().trim();
                if ("id".equalsIgnoreCase(strTrim)) {
                    iArr2[i] = 3;
                } else if ("instance".equalsIgnoreCase(strTrim)) {
                    iArr2[i] = 1;
                } else if ("name".equalsIgnoreCase(strTrim)) {
                    iArr2[i] = 2;
                } else if ("itemId".equalsIgnoreCase(strTrim)) {
                    iArr2[i] = 4;
                } else if (strTrim.isEmpty()) {
                    int[] iArr3 = new int[iArr2.length - 1];
                    System.arraycopy(iArr2, 0, iArr3, 0, i);
                    i--;
                    iArr2 = iArr3;
                } else {
                    throw new InflateException(hk.j("Unknown match type in matchOrder: '", strTrim, "'"));
                }
                i++;
            }
            if (iArr2.length == 0) {
                this.k = iArr;
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
                this.k = (int[]) iArr2.clone();
            }
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public void y(k9 k9Var) {
    }
}
