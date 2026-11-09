package defpackage;

import android.content.ClipData;
import android.content.ClipDescription;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.ContentInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.b;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.coordinatorlayout.widget.c;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.work.impl.WorkDatabaseVersions;
import androidx.work.impl.WorkManagerImpl;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.navigation.NavigationBarView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;
import java.util.WeakHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class o70 implements y60, g60, ha0, z5, l60, n50, sg, ug, y0, c50 {
    public static final o70 d = new o70(0);
    public static final gq0 e = new gq0();
    public final /* synthetic */ int b;
    public Object c;

    public /* synthetic */ o70(int i, Object obj) {
        this.b = i;
        this.c = obj;
    }

    public static o70 y(int i, int i2, int i3) {
        return new o70(2, AccessibilityNodeInfo.CollectionInfo.obtain(i, i2, false, i3));
    }

    public boolean A(int i, int i2, Bundle bundle) {
        return false;
    }

    public void B(String str) {
        int i = 0;
        while (true) {
            ArrayList arrayList = (ArrayList) this.c;
            if (i >= arrayList.size()) {
                return;
            }
            if (str.equalsIgnoreCase((String) arrayList.get(i))) {
                arrayList.remove(i);
                arrayList.remove(i);
                i -= 2;
            }
            i += 2;
        }
    }

    @Override // defpackage.y60
    public void a(i60 i60Var, boolean z) {
        if (i60Var instanceof el0) {
            ((el0) i60Var).z.k().c(false);
        }
        y60 y60Var = ((b) this.c).f;
        if (y60Var != null) {
            y60Var.a(i60Var, z);
        }
    }

    @Override // defpackage.ug
    public ClipData b() {
        return ((ContentInfo) this.c).getClip();
    }

    @Override // defpackage.y0
    public boolean c(View view) {
        DrawerLayout drawerLayout = (DrawerLayout) this.c;
        if (!DrawerLayout.k(view) || drawerLayout.g(view) == 2) {
            return false;
        }
        drawerLayout.b(view, true);
        return true;
    }

    @Override // defpackage.sg
    public vg d() {
        return new vg(new o70(((ContentInfo.Builder) this.c).build()));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // defpackage.ha0
    public xu0 e(View view, xu0 xu0Var) {
        xu0 xu0Var2;
        int i = 0;
        switch (this.b) {
            case 7:
                AppBarLayout appBarLayout = (AppBarLayout) this.c;
                WeakHashMap weakHashMap = xr0.a;
                xu0Var2 = appBarLayout.getFitsSystemWindows() ? xu0Var : null;
                if (!Objects.equals(appBarLayout.h, xu0Var2)) {
                    appBarLayout.h = xu0Var2;
                    if (appBarLayout.w != null && appBarLayout.getTopInset() > 0) {
                        i = 1;
                    }
                    appBarLayout.setWillNotDraw(i ^ 1);
                    appBarLayout.requestLayout();
                }
                return xu0Var;
            case WorkDatabaseVersions.VERSION_11 /* 11 */:
                CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) this.c;
                WeakHashMap weakHashMap2 = xr0.a;
                xu0Var2 = collapsingToolbarLayout.getFitsSystemWindows() ? xu0Var : null;
                if (!Objects.equals(collapsingToolbarLayout.B, xu0Var2)) {
                    collapsingToolbarLayout.B = xu0Var2;
                    collapsingToolbarLayout.requestLayout();
                }
                return xu0Var.a.c();
            default:
                CoordinatorLayout coordinatorLayout = (CoordinatorLayout) this.c;
                if (!Objects.equals(coordinatorLayout.o, xu0Var)) {
                    coordinatorLayout.o = xu0Var;
                    boolean z = xu0Var.d() > 0;
                    coordinatorLayout.p = z;
                    coordinatorLayout.setWillNotDraw(!z && coordinatorLayout.getBackground() == null);
                    uu0 uu0Var = xu0Var.a;
                    if (!uu0Var.m()) {
                        int childCount = coordinatorLayout.getChildCount();
                        while (i < childCount) {
                            View childAt = coordinatorLayout.getChildAt(i);
                            WeakHashMap weakHashMap3 = xr0.a;
                            if (!childAt.getFitsSystemWindows() || ((c) childAt.getLayoutParams()).a == null || !uu0Var.m()) {
                                i++;
                            }
                        }
                    }
                    coordinatorLayout.requestLayout();
                }
                return xu0Var;
        }
    }

    @Override // defpackage.ug
    public int f() {
        return ((ContentInfo) this.c).getFlags();
    }

    @Override // defpackage.ug
    public ContentInfo g() {
        return (ContentInfo) this.c;
    }

    @Override // defpackage.g60
    public void i(i60 i60Var) {
        switch (this.b) {
            case 5:
                po0 po0Var = ((ActionMenuView) this.c).v;
                if (po0Var != null) {
                    po0Var.i(i60Var);
                }
                break;
        }
    }

    @Override // defpackage.sg
    public void j(Bundle bundle) {
        ((ContentInfo.Builder) this.c).setExtras(bundle);
    }

    @Override // defpackage.sg
    public void l(Uri uri) {
        ((ContentInfo.Builder) this.c).setLinkUri(uri);
    }

    @Override // defpackage.g60
    public boolean m(i60 i60Var, MenuItem menuItem) {
        switch (this.b) {
            case 5:
                p1 p1Var = ((ActionMenuView) this.c).A;
                if (p1Var != null) {
                    Iterator it = ((CopyOnWriteArrayList) ((po0) p1Var).b.H.d).iterator();
                    if (it.hasNext()) {
                        throw hk.e(it);
                    }
                }
                return false;
            default:
                int i = NavigationBarView.f;
                NavigationBarView navigationBarView = (NavigationBarView) this.c;
                navigationBarView.getClass();
                navigationBarView.getClass();
                return false;
        }
    }

    @Override // defpackage.ug
    public int n() {
        return ((ContentInfo) this.c).getSource();
    }

    @Override // defpackage.l60
    public void o(i60 i60Var, m60 m60Var) {
        ((za) this.c).g.removeCallbacksAndMessages(i60Var);
    }

    @Override // defpackage.y60
    public boolean p(i60 i60Var) {
        b bVar = (b) this.c;
        if (i60Var == bVar.d) {
            return false;
        }
        bVar.z = ((el0) i60Var).A.a;
        y60 y60Var = bVar.f;
        if (y60Var != null) {
            return y60Var.p(i60Var);
        }
        return false;
    }

    @Override // defpackage.l60
    public void q(i60 i60Var, m60 m60Var) {
        za zaVar = (za) this.c;
        zaVar.g.removeCallbacksAndMessages(null);
        ArrayList arrayList = zaVar.i;
        int size = arrayList.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                i = -1;
                break;
            } else if (i60Var == ((ya) arrayList.get(i)).b) {
                break;
            } else {
                i++;
            }
        }
        if (i == -1) {
            return;
        }
        int i2 = i + 1;
        zaVar.g.postAtTime(new xa(this, i2 < arrayList.size() ? (ya) arrayList.get(i2) : null, m60Var, i60Var), i60Var, SystemClock.uptimeMillis() + 200);
    }

    @Override // defpackage.sg
    public void r(int i) {
        ((ContentInfo.Builder) this.c).setFlags(i);
    }

    public void s(String str, String str2) {
        oy.j(str, "name");
        oy.j(str2, "value");
        ArrayList arrayList = (ArrayList) this.c;
        arrayList.add(str);
        arrayList.add(vk0.B0(str2).toString());
    }

    public tv t() {
        return new tv((String[]) ((ArrayList) this.c).toArray(new String[0]));
    }

    public String toString() {
        switch (this.b) {
            case WorkDatabaseVersions.VERSION_14 /* 14 */:
                return "ContentInfoCompat{" + ((ContentInfo) this.c) + "}";
            default:
                return super.toString();
        }
    }

    public o0 u(int i) {
        return null;
    }

    public o0 v(int i) {
        return null;
    }

    public gs0 w(Class cls) {
        String str;
        jd jdVarA = ce0.a(cls);
        Class cls2 = jdVarA.b;
        oy.j(cls2, "jClass");
        String canonicalName = null;
        if (!cls2.isAnonymousClass() && !cls2.isLocalClass()) {
            boolean zIsArray = cls2.isArray();
            HashMap map = jd.d;
            if (zIsArray) {
                Class<?> componentType = cls2.getComponentType();
                if (componentType.isPrimitive() && (str = (String) map.get(componentType.getName())) != null) {
                    canonicalName = str.concat("Array");
                }
                if (canonicalName == null) {
                    canonicalName = "kotlin.Array";
                }
            } else {
                canonicalName = (String) map.get(cls2.getName());
                if (canonicalName == null) {
                    canonicalName = cls2.getCanonicalName();
                }
            }
        }
        if (canonicalName != null) {
            return ((ba) this.c).d(jdVarA, "androidx.lifecycle.ViewModelProvider.DefaultKey:".concat(canonicalName));
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }

    public void x() {
        ((kt) this.c).w.H();
    }

    public o70(int i) {
        this.b = i;
        switch (i) {
            case 3:
                if (Build.VERSION.SDK_INT < 26) {
                    this.c = new p0(this);
                } else {
                    this.c = new q0(this);
                }
                break;
            case WorkDatabaseVersions.VERSION_12 /* 12 */:
                oy.j(TimeUnit.MINUTES, "timeUnit");
                this.c = new zc0(an0.h);
                break;
            case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                this.c = null;
                break;
            case WorkManagerImpl.MIN_JOB_SCHEDULER_API_LEVEL /* 23 */:
                this.c = new ArrayList(20);
                break;
            case 25:
                this.c = new HashMap();
                break;
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public o70(ks0 ks0Var, js0 js0Var) {
        this(ks0Var, js0Var, ai.b);
        this.b = 1;
        oy.j(ks0Var, "store");
    }

    public o70(ks0 ks0Var, js0 js0Var, bi biVar) {
        this.b = 1;
        oy.j(ks0Var, "store");
        oy.j(js0Var, "factory");
        oy.j(biVar, "defaultCreationExtras");
        this.c = new ba(ks0Var, js0Var, biVar);
    }

    public o70(TextView textView) {
        this.b = 18;
        this.c = new tn(textView);
    }

    public o70(EditText editText) {
        this.b = 17;
        this.c = new e5(editText, 9);
    }

    public o70(Uri uri, ClipDescription clipDescription, Uri uri2) {
        this.b = 24;
        if (Build.VERSION.SDK_INT >= 25) {
            this.c = new fy(uri, clipDescription, uri2);
        } else {
            this.c = new po(uri, clipDescription, uri2, 8);
        }
    }

    public o70(ContentInfo contentInfo) {
        this.b = 14;
        contentInfo.getClass();
        this.c = c9.g(contentInfo);
    }

    public o70(ClipData clipData, int i) {
        this.b = 13;
        this.c = c9.e(clipData, i);
    }

    private final void z(i60 i60Var) {
    }

    public void h(int i) {
    }

    public void k(int i) {
    }
}
