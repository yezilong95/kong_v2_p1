package androidx.activity;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Trace;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.ComponentActivity;
import androidx.lifecycle.Lifecycle$State;
import defpackage.af;
import defpackage.ai;
import defpackage.b80;
import defpackage.bi;
import defpackage.cf;
import defpackage.dd0;
import defpackage.df;
import defpackage.e5;
import defpackage.hk;
import defpackage.is0;
import defpackage.ks0;
import defpackage.ls0;
import defpackage.ne0;
import defpackage.nu;
import defpackage.nv0;
import defpackage.ou;
import defpackage.oy;
import defpackage.p10;
import defpackage.pe0;
import defpackage.pg;
import defpackage.pg0;
import defpackage.qa0;
import defpackage.qv;
import defpackage.ra0;
import defpackage.ug0;
import defpackage.v6;
import defpackage.vg0;
import defpackage.w10;
import defpackage.we;
import defpackage.wg0;
import defpackage.xe;
import defpackage.xg;
import defpackage.xi;
import defpackage.yg;
import defpackage.yo;
import defpackage.z1;
import defpackage.ze;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public class ComponentActivity extends androidx.core.app.ComponentActivity implements xg, ls0, qv, vg0 {
    public static final /* synthetic */ int u = 0;
    public final yg c = new yg();
    public final e5 d = new e5(new z1(2, this));
    public final w10 e;
    public final e5 f;
    public ks0 g;
    public wg0 h;
    public qa0 i;
    public final df j;
    public final nu k;
    public final AtomicInteger l;
    public final ze m;
    public final CopyOnWriteArrayList n;
    public final CopyOnWriteArrayList o;
    public final CopyOnWriteArrayList p;
    public final CopyOnWriteArrayList q;
    public final CopyOnWriteArrayList r;
    public boolean s;
    public boolean t;

    public ComponentActivity() {
        w10 w10Var = new w10(this);
        this.e = w10Var;
        ug0 ug0Var = new ug0(this, new we(this, 2));
        e5 e5Var = new e5(ug0Var);
        this.f = e5Var;
        this.i = null;
        df dfVar = new df(this);
        this.j = dfVar;
        this.k = new nu(dfVar, new we(this, 0));
        this.l = new AtomicInteger();
        this.m = new ze(this);
        this.n = new CopyOnWriteArrayList();
        this.o = new CopyOnWriteArrayList();
        this.p = new CopyOnWriteArrayList();
        this.q = new CopyOnWriteArrayList();
        this.r = new CopyOnWriteArrayList();
        this.s = false;
        this.t = false;
        w10Var.a(new af(this, 0));
        w10Var.a(new af(this, 1));
        w10Var.a(new af(this, 2));
        ug0Var.a();
        Lifecycle$State lifecycle$State = w10Var.c;
        if (lifecycle$State != Lifecycle$State.INITIALIZED && lifecycle$State != Lifecycle$State.CREATED) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        e5 e5Var2 = (e5) e5Var.d;
        if (e5Var2.z() == null) {
            pg0 pg0Var = new pg0(e5Var2, this);
            e5Var2.M("androidx.lifecycle.internal.SavedStateHandlesProvider", pg0Var);
            w10Var.a(new dd0(3, pg0Var));
        }
        e5Var2.M("android:support:activity-result", new xe(0, this));
        addOnContextAvailableListener(new ra0() { // from class: ye
            @Override // defpackage.ra0
            public final void a() {
                ComponentActivity componentActivity = this.a;
                Bundle bundleD = ((e5) componentActivity.f.d).d("android:support:activity-result");
                if (bundleD != null) {
                    ze zeVar = componentActivity.m;
                    zeVar.getClass();
                    ArrayList<Integer> integerArrayList = bundleD.getIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS");
                    ArrayList<String> stringArrayList = bundleD.getStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS");
                    if (stringArrayList == null || integerArrayList == null) {
                        return;
                    }
                    zeVar.d = bundleD.getStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS");
                    Bundle bundle = bundleD.getBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT");
                    Bundle bundle2 = zeVar.g;
                    bundle2.putAll(bundle);
                    for (int i = 0; i < stringArrayList.size(); i++) {
                        String str = stringArrayList.get(i);
                        HashMap map = zeVar.b;
                        boolean zContainsKey = map.containsKey(str);
                        HashMap map2 = zeVar.a;
                        if (zContainsKey) {
                            Integer num = (Integer) map.remove(str);
                            if (!bundle2.containsKey(str)) {
                                map2.remove(num);
                            }
                        }
                        Integer num2 = integerArrayList.get(i);
                        num2.intValue();
                        String str2 = stringArrayList.get(i);
                        map2.put(num2, str2);
                        map.put(str2, num2);
                    }
                }
            }
        });
    }

    @Override // defpackage.qv
    public final bi a() {
        b80 b80Var = new b80(ai.b);
        Application application = getApplication();
        LinkedHashMap linkedHashMap = b80Var.a;
        if (application != null) {
            linkedHashMap.put(is0.f, getApplication());
        }
        linkedHashMap.put(xi.k, this);
        linkedHashMap.put(xi.l, this);
        if (getIntent() != null && getIntent().getExtras() != null) {
            linkedHashMap.put(xi.m, getIntent().getExtras());
        }
        return b80Var;
    }

    @Override // android.app.Activity
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        h();
        this.j.a(getWindow().getDecorView());
        super.addContentView(view, layoutParams);
    }

    @Override // defpackage.xg
    public final void addOnContextAvailableListener(ra0 ra0Var) {
        this.c.addOnContextAvailableListener(ra0Var);
    }

    @Override // defpackage.vg0
    public final e5 b() {
        return (e5) this.f.d;
    }

    @Override // defpackage.ls0
    public final ks0 d() {
        if (getApplication() == null) {
            throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
        }
        if (this.g == null) {
            cf cfVar = (cf) getLastNonConfigurationInstance();
            if (cfVar != null) {
                this.g = cfVar.a;
            }
            if (this.g == null) {
                this.g = new ks0();
            }
        }
        return this.g;
    }

    public final qa0 g() {
        if (this.i == null) {
            this.i = new qa0(new v6(3, this));
            this.e.a(new af(this, 3));
        }
        return this.i;
    }

    @Override // androidx.core.app.ComponentActivity, defpackage.u10
    public final p10 getLifecycle() {
        return this.e;
    }

    public final void h() {
        View decorView = getWindow().getDecorView();
        oy.j(decorView, "<this>");
        decorView.setTag(androidx.lifecycle.runtime.R$id.view_tree_lifecycle_owner, this);
        View decorView2 = getWindow().getDecorView();
        oy.j(decorView2, "<this>");
        decorView2.setTag(androidx.lifecycle.viewmodel.R$id.view_tree_view_model_store_owner, this);
        View decorView3 = getWindow().getDecorView();
        oy.j(decorView3, "<this>");
        decorView3.setTag(androidx.savedstate.R$id.view_tree_saved_state_registry_owner, this);
        View decorView4 = getWindow().getDecorView();
        oy.j(decorView4, "<this>");
        decorView4.setTag(R$id.view_tree_on_back_pressed_dispatcher_owner, this);
        View decorView5 = getWindow().getDecorView();
        oy.j(decorView5, "<this>");
        decorView5.setTag(R$id.report_drawn, this);
    }

    @Override // android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        if (this.m.a(i, i2, intent)) {
            return;
        }
        super.onActivityResult(i, i2, intent);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        g().a();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Iterator it = this.n.iterator();
        while (it.hasNext()) {
            ((pg) it.next()).a(configuration);
        }
    }

    @Override // androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        this.f.J(bundle);
        yg ygVar = this.c;
        ygVar.getClass();
        ygVar.b = this;
        Iterator it = ygVar.a.iterator();
        while (it.hasNext()) {
            ((ra0) it.next()).a();
        }
        super.onCreate(bundle);
        int i = pe0.c;
        ne0.b(this);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onCreatePanelMenu(int i, Menu menu) {
        if (i != 0) {
            return true;
        }
        super.onCreatePanelMenu(i, menu);
        getMenuInflater();
        Iterator it = ((CopyOnWriteArrayList) this.d.d).iterator();
        if (it.hasNext()) {
            throw hk.e(it);
        }
        return true;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        if (i != 0) {
            return false;
        }
        Iterator it = ((CopyOnWriteArrayList) this.d.d).iterator();
        if (it.hasNext()) {
            throw hk.e(it);
        }
        return false;
    }

    @Override // android.app.Activity
    public void onMultiWindowModeChanged(boolean z) {
        if (this.s) {
            return;
        }
        Iterator it = this.q.iterator();
        while (it.hasNext()) {
            ((pg) it.next()).a(new yo(6));
        }
    }

    @Override // android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Iterator it = this.p.iterator();
        while (it.hasNext()) {
            ((pg) it.next()).a(intent);
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i, Menu menu) {
        Iterator it = ((CopyOnWriteArrayList) this.d.d).iterator();
        if (it.hasNext()) {
            throw hk.e(it);
        }
        super.onPanelClosed(i, menu);
    }

    @Override // android.app.Activity
    public void onPictureInPictureModeChanged(boolean z) {
        if (this.t) {
            return;
        }
        Iterator it = this.r.iterator();
        while (it.hasNext()) {
            ((pg) it.next()).a(new yo(9));
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onPreparePanel(int i, View view, Menu menu) {
        if (i != 0) {
            return true;
        }
        super.onPreparePanel(i, view, menu);
        Iterator it = ((CopyOnWriteArrayList) this.d.d).iterator();
        if (it.hasNext()) {
            throw hk.e(it);
        }
        return true;
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (this.m.a(i, -1, new Intent().putExtra("androidx.activity.result.contract.extra.PERMISSIONS", strArr).putExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS", iArr))) {
            return;
        }
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    @Override // android.app.Activity
    public final Object onRetainNonConfigurationInstance() {
        cf cfVar;
        ks0 ks0Var = this.g;
        if (ks0Var == null && (cfVar = (cf) getLastNonConfigurationInstance()) != null) {
            ks0Var = cfVar.a;
        }
        if (ks0Var == null) {
            return null;
        }
        cf cfVar2 = new cf();
        cfVar2.a = ks0Var;
        return cfVar2;
    }

    @Override // androidx.core.app.ComponentActivity, android.app.Activity
    public final void onSaveInstanceState(Bundle bundle) {
        w10 w10Var = this.e;
        if (w10Var != null) {
            w10Var.g(Lifecycle$State.CREATED);
        }
        super.onSaveInstanceState(bundle);
        this.f.K(bundle);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks2
    public final void onTrimMemory(int i) {
        super.onTrimMemory(i);
        Iterator it = this.o.iterator();
        while (it.hasNext()) {
            ((pg) it.next()).a(Integer.valueOf(i));
        }
    }

    @Override // defpackage.xg
    public final void removeOnContextAvailableListener(ra0 ra0Var) {
        this.c.removeOnContextAvailableListener(ra0Var);
    }

    @Override // android.app.Activity
    public final void reportFullyDrawn() {
        try {
            if (nv0.C()) {
                Trace.beginSection("reportFullyDrawn() for ComponentActivity");
            }
            super.reportFullyDrawn();
            nu nuVar = this.k;
            synchronized (nuVar.a) {
                try {
                    nuVar.b = true;
                    Iterator it = nuVar.c.iterator();
                    while (it.hasNext()) {
                        ((ou) it.next()).invoke();
                    }
                    nuVar.c.clear();
                } finally {
                }
            }
            Trace.endSection();
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    @Override // android.app.Activity
    public void setContentView(int i) {
        h();
        this.j.a(getWindow().getDecorView());
        super.setContentView(i);
    }

    @Override // android.app.Activity
    public void setContentView(@SuppressLint({"UnknownNullness", "MissingNullability"}) View view) {
        h();
        this.j.a(getWindow().getDecorView());
        super.setContentView(view);
    }

    @Override // android.app.Activity
    public final void onMultiWindowModeChanged(boolean z, Configuration configuration) {
        this.s = true;
        try {
            super.onMultiWindowModeChanged(z, configuration);
            this.s = false;
            for (pg pgVar : this.q) {
                oy.j(configuration, "newConfig");
                pgVar.a(new yo(6));
            }
        } catch (Throwable th) {
            this.s = false;
            throw th;
        }
    }

    @Override // android.app.Activity
    public final void onPictureInPictureModeChanged(boolean z, Configuration configuration) {
        this.t = true;
        try {
            super.onPictureInPictureModeChanged(z, configuration);
            this.t = false;
            for (pg pgVar : this.r) {
                oy.j(configuration, "newConfig");
                pgVar.a(new yo(9));
            }
        } catch (Throwable th) {
            this.t = false;
            throw th;
        }
    }

    @Override // android.app.Activity
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        h();
        this.j.a(getWindow().getDecorView());
        super.setContentView(view, layoutParams);
    }
}
