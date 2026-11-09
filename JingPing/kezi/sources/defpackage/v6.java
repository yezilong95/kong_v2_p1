package defpackage;

import android.animation.ValueAnimator;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.b;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.c;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.recyclerview.widget.n;
import androidx.viewpager.widget.ViewPager;
import androidx.work.impl.WorkDatabaseVersions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Lock;
import java.util.logging.Level;
import kotlin.collections.EmptySet;
import kotlin.collections.builders.SetBuilder;

/* JADX INFO: loaded from: classes.dex */
public final class v6 implements Runnable {
    public final /* synthetic */ int b;
    public final /* synthetic */ Object c;

    public /* synthetic */ v6(int i, Object obj) {
        this.b = i;
        this.c = obj;
    }

    public Set a() throws IllegalAccessException, IOException, InvocationTargetException {
        ty tyVar = (ty) this.c;
        SetBuilder setBuilder = new SetBuilder();
        Cursor cursorQuery$default = pf0.query$default(tyVar.a, new e5("SELECT * FROM room_table_modification_log WHERE invalidated = 1;"), null, 2, null);
        while (cursorQuery$default.moveToNext()) {
            try {
                setBuilder.add(Integer.valueOf(cursorQuery$default.getInt(0)));
            } finally {
            }
        }
        cursorQuery$default.close();
        Set setBuild = setBuilder.build();
        if (setBuild.isEmpty()) {
            return setBuild;
        }
        if (((ty) this.c).h == null) {
            throw new IllegalStateException("Required value was null.");
        }
        wl0 wl0Var = ((ty) this.c).h;
        if (wl0Var == null) {
            throw new IllegalArgumentException("Required value was null.");
        }
        wl0Var.n();
        return setBuild;
    }

    /* JADX INFO: Infinite loop detected, blocks: 9, insns: 0 */
    @Override // java.lang.Runnable
    public final void run() {
        View viewD;
        int width;
        int i;
        Set setA;
        Object obj;
        boolean z;
        vm0 vm0VarC;
        long jNanoTime;
        b bVar;
        switch (this.b) {
            case 0:
                Throwable th = (Throwable) this.c;
                th.toString();
                for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                    stackTraceElement.toString();
                }
                Throwable cause = th.getCause();
                if (cause != null) {
                    cause.toString();
                    for (StackTraceElement stackTraceElement2 : cause.getStackTrace()) {
                        stackTraceElement2.toString();
                    }
                }
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
                try {
                    nv0.q();
                    throw null;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            case 1:
                w20 w20Var = (w20) this.c;
                if (w20Var.p) {
                    boolean z2 = w20Var.n;
                    p7 p7Var = w20Var.b;
                    if (z2) {
                        w20Var.n = false;
                        long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
                        p7Var.e = jCurrentAnimationTimeMillis;
                        p7Var.g = -1L;
                        p7Var.f = jCurrentAnimationTimeMillis;
                        p7Var.h = 0.5f;
                    }
                    if ((p7Var.g > 0 && AnimationUtils.currentAnimationTimeMillis() > p7Var.g + ((long) p7Var.i)) || !w20Var.e()) {
                        w20Var.p = false;
                        return;
                    }
                    boolean z3 = w20Var.o;
                    ListView listView = w20Var.d;
                    if (z3) {
                        w20Var.o = false;
                        long jUptimeMillis = SystemClock.uptimeMillis();
                        MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
                        listView.onTouchEvent(motionEventObtain);
                        motionEventObtain.recycle();
                    }
                    if (p7Var.f == 0) {
                        throw new RuntimeException("Cannot compute scroll delta before calling start()");
                    }
                    long jCurrentAnimationTimeMillis2 = AnimationUtils.currentAnimationTimeMillis();
                    float fA = p7Var.a(jCurrentAnimationTimeMillis2);
                    long j = jCurrentAnimationTimeMillis2 - p7Var.f;
                    p7Var.f = jCurrentAnimationTimeMillis2;
                    w20Var.r.scrollListBy((int) (j * ((fA * 4.0f) + ((-4.0f) * fA * fA)) * p7Var.d));
                    WeakHashMap weakHashMap = xr0.a;
                    listView.postOnAnimation(this);
                    return;
                }
                return;
            case 2:
                g9 g9Var = (g9) this.c;
                g9Var.c = false;
                BottomSheetBehavior bottomSheetBehavior = (BottomSheetBehavior) g9Var.e;
                cs0 cs0Var = bottomSheetBehavior.N;
                if (cs0Var != null && cs0Var.g()) {
                    g9Var.a(g9Var.b);
                    return;
                } else {
                    if (bottomSheetBehavior.M == 2) {
                        bottomSheetBehavior.J(g9Var.b);
                        return;
                    }
                    return;
                }
            case 3:
                try {
                    super/*android.app.Activity*/.onBackPressed();
                    return;
                } catch (IllegalStateException e2) {
                    if (!TextUtils.equals(e2.getMessage(), "Can not perform this action after onSaveInstanceState")) {
                        throw e2;
                    }
                    return;
                } catch (NullPointerException e3) {
                    if (!TextUtils.equals(e3.getMessage(), "Attempt to invoke virtual method 'android.os.Handler android.app.FragmentHostCallback.getHandler()' on a null object reference")) {
                        throw e3;
                    }
                    return;
                }
            case 4:
                sj sjVar = (sj) this.c;
                sjVar.a.endViewTransition(sjVar.b);
                sjVar.c.d();
                return;
            case 5:
                uk ukVar = (uk) this.c;
                ukVar.V.onDismiss(ukVar.d0);
                return;
            case 6:
                androidx.drawerlayout.widget.b bVar2 = (androidx.drawerlayout.widget.b) this.c;
                int i2 = bVar2.s.o;
                int i3 = bVar2.r;
                boolean z4 = i3 == 3;
                DrawerLayout drawerLayout = bVar2.u;
                if (z4) {
                    viewD = drawerLayout.d(3);
                    width = (viewD != null ? -viewD.getWidth() : 0) + i2;
                } else {
                    viewD = drawerLayout.d(5);
                    width = drawerLayout.getWidth() - i2;
                }
                if (viewD != null) {
                    if (((!z4 || viewD.getLeft() >= width) && (z4 || viewD.getLeft() <= width)) || drawerLayout.g(viewD) != 0) {
                        return;
                    }
                    DrawerLayout.LayoutParams layoutParams = (DrawerLayout.LayoutParams) viewD.getLayoutParams();
                    bVar2.s.s(viewD, width, viewD.getTop());
                    layoutParams.c = true;
                    drawerLayout.invalidate();
                    View viewD2 = drawerLayout.d(i3 != 3 ? 3 : 5);
                    if (viewD2 != null) {
                        drawerLayout.b(viewD2, true);
                    }
                    if (drawerLayout.s) {
                        return;
                    }
                    long jUptimeMillis2 = SystemClock.uptimeMillis();
                    MotionEvent motionEventObtain2 = MotionEvent.obtain(jUptimeMillis2, jUptimeMillis2, 3, 0.0f, 0.0f, 0);
                    int childCount = drawerLayout.getChildCount();
                    for (int i4 = 0; i4 < childCount; i4++) {
                        drawerLayout.getChildAt(i4).dispatchTouchEvent(motionEventObtain2);
                    }
                    motionEventObtain2.recycle();
                    drawerLayout.s = true;
                    return;
                }
                return;
            case 7:
                cm cmVar = (cm) this.c;
                cmVar.m = null;
                cmVar.drawableStateChanged();
                return;
            case 8:
                po poVar = (po) this.c;
                while (true) {
                    try {
                        ((ExecutorService) poVar.e).submit(new m1(poVar, i, ((ArrayBlockingQueue) poVar.d).take()));
                    } catch (Exception e4) {
                        e4.printStackTrace();
                    }
                }
                break;
            case WorkDatabaseVersions.VERSION_9 /* 9 */:
                qp qpVar = (qp) this.c;
                int i5 = qpVar.A;
                ValueAnimator valueAnimator = qpVar.z;
                if (i5 != 1) {
                    i = 2;
                    if (i5 != 2) {
                        return;
                    }
                } else {
                    i = 2;
                    valueAnimator.cancel();
                }
                qpVar.A = 3;
                float[] fArr = new float[i];
                fArr[0] = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                fArr[1] = 0.0f;
                valueAnimator.setFloatValues(fArr);
                valueAnimator.setDuration(500);
                valueAnimator.start();
                return;
            case WorkDatabaseVersions.VERSION_10 /* 10 */:
                ((c) this.c).u(true);
                return;
            case WorkDatabaseVersions.VERSION_11 /* 11 */:
                Lock closeLock$room_runtime_release = ((ty) this.c).a.getCloseLock$room_runtime_release();
                closeLock$room_runtime_release.lock();
                try {
                    try {
                    } catch (SQLiteException e5) {
                        Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", e5);
                        setA = EmptySet.INSTANCE;
                    } catch (IllegalStateException e6) {
                        Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", e6);
                        setA = EmptySet.INSTANCE;
                    }
                    if (((ty) this.c).b() && ((ty) this.c).f.compareAndSet(true, false) && !((ty) this.c).a.inTransaction()) {
                        ql0 ql0VarA = ((ku) ((ty) this.c).a.getOpenHelper()).a();
                        ql0VarA.q();
                        try {
                            setA = a();
                            ql0VarA.l();
                            if (setA.isEmpty()) {
                                return;
                            }
                            ty tyVar = (ty) this.c;
                            synchronized (tyVar.k) {
                                Iterator it = tyVar.k.iterator();
                                while (true) {
                                    hg0 hg0Var = (hg0) it;
                                    if (hg0Var.hasNext()) {
                                        ((ry) ((Map.Entry) hg0Var.next()).getValue()).a(setA);
                                    }
                                }
                            }
                            return;
                        } finally {
                            ql0VarA.e();
                        }
                    }
                    return;
                } finally {
                    closeLock$room_runtime_release.unlock();
                    ((ty) this.c).getClass();
                }
            case WorkDatabaseVersions.VERSION_12 /* 12 */:
                synchronized (((b30) this.c).a) {
                    obj = ((b30) this.c).f;
                    ((b30) this.c).f = b30.k;
                    break;
                }
                ((b30) this.c).i(obj);
                return;
            case WorkDatabaseVersions.VERSION_13 /* 13 */:
                ((ViewGroup) this.c).setNestedScrollingEnabled(true);
                return;
            case WorkDatabaseVersions.VERSION_14 /* 14 */:
                RecyclerView recyclerView = (RecyclerView) this.c;
                ld0 ld0Var = recyclerView.J;
                if (ld0Var != null) {
                    mj mjVar = (mj) ld0Var;
                    ArrayList arrayList = mjVar.h;
                    boolean zIsEmpty = arrayList.isEmpty();
                    ArrayList arrayList2 = mjVar.j;
                    boolean zIsEmpty2 = arrayList2.isEmpty();
                    ArrayList arrayList3 = mjVar.k;
                    boolean zIsEmpty3 = arrayList3.isEmpty();
                    ArrayList arrayList4 = mjVar.i;
                    boolean zIsEmpty4 = arrayList4.isEmpty();
                    if (!zIsEmpty || !zIsEmpty2 || !zIsEmpty4 || !zIsEmpty3) {
                        Iterator it2 = arrayList.iterator();
                        while (true) {
                            boolean zHasNext = it2.hasNext();
                            long j2 = mjVar.d;
                            if (zHasNext) {
                                n nVar = (n) it2.next();
                                View view = nVar.a;
                                ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
                                mjVar.q.add(nVar);
                                viewPropertyAnimatorAnimate.setDuration(j2).alpha(0.0f).setListener(new hj(mjVar, nVar, viewPropertyAnimatorAnimate, view)).start();
                            } else {
                                arrayList.clear();
                                if (!zIsEmpty2) {
                                    ArrayList arrayList5 = new ArrayList();
                                    arrayList5.addAll(arrayList2);
                                    mjVar.m.add(arrayList5);
                                    arrayList2.clear();
                                    gj gjVar = new gj(mjVar, arrayList5, 0);
                                    if (zIsEmpty) {
                                        gjVar.run();
                                    } else {
                                        View view2 = ((lj) arrayList5.get(0)).a.a;
                                        WeakHashMap weakHashMap2 = xr0.a;
                                        view2.postOnAnimationDelayed(gjVar, j2);
                                    }
                                }
                                if (!zIsEmpty3) {
                                    ArrayList arrayList6 = new ArrayList();
                                    arrayList6.addAll(arrayList3);
                                    mjVar.n.add(arrayList6);
                                    arrayList3.clear();
                                    gj gjVar2 = new gj(mjVar, arrayList6, 1);
                                    if (zIsEmpty) {
                                        gjVar2.run();
                                    } else {
                                        View view3 = ((kj) arrayList6.get(0)).a.a;
                                        WeakHashMap weakHashMap3 = xr0.a;
                                        view3.postOnAnimationDelayed(gjVar2, j2);
                                    }
                                }
                                if (!zIsEmpty4) {
                                    ArrayList arrayList7 = new ArrayList();
                                    arrayList7.addAll(arrayList4);
                                    mjVar.l.add(arrayList7);
                                    arrayList4.clear();
                                    gj gjVar3 = new gj(mjVar, arrayList7, 2);
                                    if (zIsEmpty && zIsEmpty2 && zIsEmpty3) {
                                        gjVar3.run();
                                    } else {
                                        if (zIsEmpty) {
                                            j2 = 0;
                                        }
                                        long jMax = Math.max(!zIsEmpty2 ? mjVar.e : 0L, !zIsEmpty3 ? mjVar.f : 0L) + j2;
                                        View view4 = ((n) arrayList7.get(0)).a;
                                        WeakHashMap weakHashMap4 = xr0.a;
                                        view4.postOnAnimationDelayed(gjVar3, jMax);
                                    }
                                }
                            }
                        }
                    }
                    z = false;
                } else {
                    z = false;
                }
                recyclerView.k0 = z;
                return;
            case WorkDatabaseVersions.VERSION_15 /* 15 */:
                ((StaggeredGridLayoutManager) this.c).z0();
                return;
            case 16:
                break;
            case 17:
                ActionMenuView actionMenuView = ((Toolbar) this.c).b;
                if (actionMenuView == null || (bVar = actionMenuView.u) == null) {
                    return;
                }
                bVar.n();
                return;
            case 18:
                ((cs0) this.c).p(0);
                return;
            default:
                ((ViewPager) this.c).setScrollState(0);
                return;
        }
        while (true) {
            an0 an0Var = (an0) this.c;
            synchronized (an0Var) {
                vm0VarC = an0Var.c();
            }
            if (vm0VarC == null) {
                return;
            }
            zm0 zm0Var = vm0VarC.c;
            oy.g(zm0Var);
            an0 an0Var2 = (an0) this.c;
            boolean zIsLoggable = an0.i.isLoggable(Level.FINE);
            if (zIsLoggable) {
                d90 d90Var = zm0Var.a.a;
                jNanoTime = System.nanoTime();
                oy.b(vm0VarC, zm0Var, "starting");
            } else {
                jNanoTime = -1;
            }
            try {
                an0.a(an0Var2, vm0VarC);
                if (zIsLoggable) {
                    d90 d90Var2 = zm0Var.a.a;
                    oy.b(vm0VarC, zm0Var, "finished run in ".concat(oy.t(System.nanoTime() - jNanoTime)));
                }
            } catch (Throwable th2) {
                try {
                    ((ThreadPoolExecutor) an0Var2.a.b).execute(this);
                    throw th2;
                } catch (Throwable th3) {
                    if (zIsLoggable) {
                        d90 d90Var3 = zm0Var.a.a;
                        oy.b(vm0VarC, zm0Var, "failed a run in ".concat(oy.t(System.nanoTime() - jNanoTime)));
                    }
                    throw th3;
                }
            }
        }
    }
}
