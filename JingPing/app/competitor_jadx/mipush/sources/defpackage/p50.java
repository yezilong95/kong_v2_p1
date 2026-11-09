package defpackage;

import android.animation.AnimatorSet;
import android.app.job.JobScheduler;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.ContentInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.EditText;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$bool;
import androidx.appcompat.R$dimen;
import androidx.appcompat.R$styleable;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.coordinatorlayout.widget.c;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.work.impl.WorkDatabaseVersions;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.navigationrail.NavigationRailView;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.WeakHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.uuid.Uuid;

/* JADX INFO: loaded from: classes.dex */
public class p50 implements xd0, d7, nf, n61, ce0, vd, ac, vi0, qi0, hi, u2, av, sc0 {
    public static volatile p50 G;
    public static final p50 H = new p50(1, false);
    public final /* synthetic */ int E;
    public Object F;

    public /* synthetic */ p50(int i, Object obj) {
        this.E = i;
        this.F = obj;
    }

    public static p50 B(int i, int i2, int i3) {
        return new p50(3, AccessibilityNodeInfo.CollectionInfo.obtain(i, i2, false, i3));
    }

    public static p50 t(Context context) {
        p50 p50Var = new p50(4, false);
        p50Var.F = context;
        return p50Var;
    }

    public static int w(int i) {
        if (i < 128) {
            return 1;
        }
        int i2 = 2;
        while (true) {
            i >>>= 8;
            if (i == 0) {
                return i2;
            }
            i2++;
        }
    }

    public static int x(int i, boolean z) {
        return w(i) + (z ? 1 : 0) + i;
    }

    public static int y(int i) {
        if (i < 31) {
            return 1;
        }
        int i2 = 2;
        while (true) {
            i >>>= 7;
            if (i == 0) {
                return i2;
            }
            i2++;
        }
    }

    public int A() {
        int[] iArr = R$styleable.ActionBar;
        int i = R$attr.actionBarStyle;
        Context context = (Context) this.F;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(null, iArr, i, 0);
        int layoutDimension = typedArrayObtainStyledAttributes.getLayoutDimension(R$styleable.ActionBar_height, 0);
        Resources resources = context.getResources();
        if (!((Context) this.F).getResources().getBoolean(R$bool.abc_action_bar_embed_tabs)) {
            layoutDimension = Math.min(layoutDimension, resources.getDimensionPixelSize(R$dimen.abc_action_bar_stacked_max_height));
        }
        typedArrayObtainStyledAttributes.recycle();
        return layoutDimension;
    }

    public void C(int i) throws IOException {
        ((ByteArrayOutputStream) this.F).write(i);
    }

    public void D(byte[] bArr, int i, int i2) throws IOException {
        ((ByteArrayOutputStream) this.F).write(bArr, i, i2);
    }

    public void E(int i) throws IOException {
        if (i < 128) {
            C(i);
            return;
        }
        int i2 = 5;
        byte[] bArr = new byte[5];
        while (true) {
            int i3 = i2 - 1;
            bArr[i3] = (byte) i;
            i >>>= 8;
            if (i == 0) {
                int i4 = i2 - 2;
                bArr[i4] = (byte) ((5 - i3) | Uuid.SIZE_BITS);
                D(bArr, i4, 6 - i3);
                return;
            }
            i2 = i3;
        }
    }

    public void F(t[] tVarArr) {
        for (t tVar : tVarArr) {
            tVar.b().h(this, true);
        }
    }

    public void G(int i, boolean z, byte[] bArr) throws IOException {
        J(i, z);
        E(bArr.length);
        D(bArr, 0, bArr.length);
    }

    public void H(boolean z, int i, t[] tVarArr) throws IOException {
        J(i, z);
        C(Uuid.SIZE_BITS);
        F(tVarArr);
        C(0);
        C(0);
    }

    public void I(int i, int i2) throws IOException {
        if (i2 < 31) {
            C(i | i2);
            return;
        }
        byte[] bArr = new byte[6];
        int i3 = 5;
        bArr[5] = (byte) (i2 & 127);
        while (i2 > 127) {
            i2 >>>= 7;
            i3--;
            bArr[i3] = (byte) ((i2 & 127) | Uuid.SIZE_BITS);
        }
        int i4 = i3 - 1;
        bArr[i4] = (byte) (i | 31);
        D(bArr, i4, 6 - i4);
    }

    public void J(int i, boolean z) throws IOException {
        if (z) {
            C(i);
        }
    }

    public void K(g0 g0Var) {
        g0Var.h(this, true);
    }

    public void L(g0[] g0VarArr) {
        for (g0 g0Var : g0VarArr) {
            g0Var.h(this, true);
        }
    }

    @Override // defpackage.hi
    public ki a() {
        return new ki(new z8(((ContentInfo.Builder) this.F).build()));
    }

    @Override // defpackage.u2
    public boolean b(View view) {
        DrawerLayout drawerLayout = (DrawerLayout) this.F;
        if (!DrawerLayout.k(view) || drawerLayout.g(view) == 2) {
            return false;
        }
        drawerLayout.b(view, true);
        return true;
    }

    @Override // defpackage.av
    public int c() {
        return ((ExtendedFloatingActionButton) this.F).getCollapsedPadding();
    }

    @Override // defpackage.hi
    public void e(Bundle bundle) {
        ((ContentInfo.Builder) this.F).setExtras(bundle);
    }

    @Override // defpackage.xd0
    public boolean f(zd0 zd0Var, MenuItem menuItem) {
        k3 k3Var = ((ActionMenuView) this.F).g0;
        if (k3Var == null) {
            return false;
        }
        Iterator it = ((CopyOnWriteArrayList) ((Toolbar) ((cw0) k3Var).F).n0.G).iterator();
        if (it.hasNext()) {
            throw zn.i(it);
        }
        return false;
    }

    @Override // defpackage.av
    public int g() {
        return ((ExtendedFloatingActionButton) this.F).getCollapsedPadding();
    }

    @Override // defpackage.av
    public int getHeight() {
        return ((ExtendedFloatingActionButton) this.F).getCollapsedSize();
    }

    @Override // defpackage.av
    public int getWidth() {
        return ((ExtendedFloatingActionButton) this.F).getCollapsedSize();
    }

    @Override // defpackage.vi0
    public s81 h(View view, s81 s81Var) {
        switch (this.E) {
            case WorkDatabaseVersions.VERSION_13 /* 13 */:
                CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) this.F;
                WeakHashMap weakHashMap = f51.a;
                s81 s81Var2 = collapsingToolbarLayout.getFitsSystemWindows() ? s81Var : null;
                if (!Objects.equals(collapsingToolbarLayout.h0, s81Var2)) {
                    collapsingToolbarLayout.h0 = s81Var2;
                    collapsingToolbarLayout.requestLayout();
                }
                return s81Var.a.c();
            default:
                CoordinatorLayout coordinatorLayout = (CoordinatorLayout) this.F;
                if (!Objects.equals(coordinatorLayout.R, s81Var)) {
                    coordinatorLayout.R = s81Var;
                    boolean z = s81Var.d() > 0;
                    coordinatorLayout.S = z;
                    coordinatorLayout.setWillNotDraw(!z && coordinatorLayout.getBackground() == null);
                    p81 p81Var = s81Var.a;
                    if (!p81Var.m()) {
                        int childCount = coordinatorLayout.getChildCount();
                        for (int i = 0; i < childCount; i++) {
                            View childAt = coordinatorLayout.getChildAt(i);
                            WeakHashMap weakHashMap2 = f51.a;
                            if (!childAt.getFitsSystemWindows() || ((c) childAt.getLayoutParams()).a == null || !p81Var.m()) {
                            }
                        }
                    }
                    coordinatorLayout.requestLayout();
                }
                return s81Var;
        }
    }

    @Override // defpackage.ce0
    public void i(zd0 zd0Var, de0 de0Var) {
        ((uc) this.F).J.removeCallbacksAndMessages(zd0Var);
    }

    @Override // defpackage.ce0
    public void j(zd0 zd0Var, de0 de0Var) {
        uc ucVar = (uc) this.F;
        ucVar.J.removeCallbacksAndMessages(null);
        ArrayList arrayList = ucVar.L;
        int size = arrayList.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                i = -1;
                break;
            } else if (zd0Var == ((tc) arrayList.get(i)).b) {
                break;
            } else {
                i++;
            }
        }
        if (i == -1) {
            return;
        }
        int i2 = i + 1;
        ucVar.J.postAtTime(new sc(this, i2 < arrayList.size() ? (tc) arrayList.get(i2) : null, de0Var, zd0Var), zd0Var, SystemClock.uptimeMillis() + 200);
    }

    @Override // defpackage.nf
    public void l() {
        ((nf) this.F).l();
    }

    @Override // defpackage.xd0
    public void m(zd0 zd0Var) {
        wl0 wl0Var = ((ActionMenuView) this.F).b0;
        if (wl0Var != null) {
            wl0Var.m(zd0Var);
        }
    }

    @Override // defpackage.hi
    public void n(Uri uri) {
        ((ContentInfo.Builder) this.F).setLinkUri(uri);
    }

    @Override // defpackage.n61
    public s81 o(View view, s81 s81Var, o61 o61Var) {
        boolean z;
        boolean fitsSystemWindows;
        boolean fitsSystemWindows2;
        boolean fitsSystemWindows3;
        switch (this.E) {
            case WorkDatabaseVersions.VERSION_9 /* 9 */:
                BottomAppBar bottomAppBar = (BottomAppBar) this.F;
                if (bottomAppBar.K0) {
                    bottomAppBar.Q0 = s81Var.a();
                }
                boolean z2 = false;
                if (bottomAppBar.L0) {
                    z = bottomAppBar.S0 != s81Var.b();
                    bottomAppBar.S0 = s81Var.b();
                } else {
                    z = false;
                }
                if (bottomAppBar.M0) {
                    boolean z3 = bottomAppBar.R0 != s81Var.c();
                    bottomAppBar.R0 = s81Var.c();
                    z2 = z3;
                }
                if (z || z2) {
                    AnimatorSet animatorSet = bottomAppBar.B0;
                    if (animatorSet != null) {
                        animatorSet.cancel();
                    }
                    AnimatorSet animatorSet2 = bottomAppBar.A0;
                    if (animatorSet2 != null) {
                        animatorSet2.cancel();
                    }
                    bottomAppBar.G();
                    bottomAppBar.F();
                }
                break;
            default:
                m40 m40VarF = s81Var.a.f(7);
                NavigationRailView navigationRailView = (NavigationRailView) this.F;
                Boolean bool = navigationRailView.L;
                if (bool != null) {
                    fitsSystemWindows = bool.booleanValue();
                } else {
                    WeakHashMap weakHashMap = f51.a;
                    fitsSystemWindows = navigationRailView.getFitsSystemWindows();
                }
                if (fitsSystemWindows) {
                    o61Var.b += m40VarF.b;
                }
                Boolean bool2 = navigationRailView.M;
                if (bool2 != null) {
                    fitsSystemWindows2 = bool2.booleanValue();
                } else {
                    WeakHashMap weakHashMap2 = f51.a;
                    fitsSystemWindows2 = navigationRailView.getFitsSystemWindows();
                }
                if (fitsSystemWindows2) {
                    o61Var.d += m40VarF.d;
                }
                Boolean bool3 = navigationRailView.N;
                if (bool3 != null) {
                    fitsSystemWindows3 = bool3.booleanValue();
                } else {
                    WeakHashMap weakHashMap3 = f51.a;
                    fitsSystemWindows3 = navigationRailView.getFitsSystemWindows();
                }
                if (fitsSystemWindows3) {
                    o61Var.a += fa0.d0(view) ? m40VarF.c : m40VarF.a;
                }
                int i = o61Var.a;
                int i2 = o61Var.b;
                int i3 = o61Var.c;
                int i4 = o61Var.d;
                WeakHashMap weakHashMap4 = f51.a;
                view.setPaddingRelative(i, i2, i3, i4);
                break;
        }
        return s81Var;
    }

    @Override // defpackage.av
    public ViewGroup.LayoutParams p() {
        ExtendedFloatingActionButton extendedFloatingActionButton = (ExtendedFloatingActionButton) this.F;
        return new ViewGroup.LayoutParams(extendedFloatingActionButton.getCollapsedSize(), extendedFloatingActionButton.getCollapsedSize());
    }

    @Override // defpackage.qi0
    public Object q() {
        Constructor constructor = (Constructor) this.F;
        try {
            return constructor.newInstance(null);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InstantiationException e2) {
            throw new RuntimeException("Failed to invoke " + constructor + " with no args", e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException("Failed to invoke " + constructor + " with no args", e3.getTargetException());
        }
    }

    @Override // defpackage.ac
    public void r(Typeface typeface) {
        sf sfVar = (sf) this.F;
        if (sfVar.m(typeface)) {
            sfVar.i(false);
        }
    }

    @Override // defpackage.hi
    public void s(int i) {
        ((ContentInfo.Builder) this.F).setFlags(i);
    }

    public uk u() {
        return new uk(2, (ByteArrayOutputStream) this.F);
    }

    public ml v() {
        return new ml(2, (ByteArrayOutputStream) this.F);
    }

    public int z() {
        Configuration configuration = ((Context) this.F).getResources().getConfiguration();
        int i = configuration.screenWidthDp;
        int i2 = configuration.screenHeightDp;
        if (configuration.smallestScreenWidthDp > 600 || i > 600) {
            return 5;
        }
        if (i > 960 && i2 > 720) {
            return 5;
        }
        if (i > 720 && i2 > 960) {
            return 5;
        }
        if (i >= 500) {
            return 4;
        }
        if (i > 640 && i2 > 480) {
            return 4;
        }
        if (i <= 480 || i2 <= 640) {
            return i >= 360 ? 3 : 2;
        }
        return 4;
    }

    public /* synthetic */ p50(int i, boolean z) {
        this.E = i;
    }

    public p50(Context context) {
        this.E = 0;
        this.F = (JobScheduler) context.getSystemService("jobscheduler");
    }

    public p50(String str, Object[] objArr, Throwable th) {
        this.E = 22;
        this.F = objArr;
    }

    public p50(EditText editText) {
        this.E = 19;
        this.F = new i6(editText, 11);
    }

    public p50(Uri uri, ClipDescription clipDescription, Uri uri2) {
        this.E = 24;
        if (Build.VERSION.SDK_INT >= 25) {
            this.F = new i40(uri, clipDescription, uri2);
        } else {
            this.F = new ou(uri, clipDescription, uri2, 9);
        }
    }

    public p50(int i) {
        this.E = i;
        switch (i) {
            case 29:
                this.F = new LinkedList();
                new AtomicReference();
                break;
            default:
                this.F = null;
                break;
        }
    }

    public p50(ClipData clipData, int i) {
        this.E = 15;
        this.F = ab.e(clipData, i);
    }

    public void d(int i) {
    }

    public void k(int i) {
    }
}
