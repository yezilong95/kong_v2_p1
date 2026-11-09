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
import defpackage.a10;
import defpackage.di;
import defpackage.e;
import defpackage.ei;
import defpackage.ej0;
import defpackage.f;
import defpackage.fj0;
import defpackage.fr0;
import defpackage.i6;
import defpackage.ir0;
import defpackage.jr0;
import defpackage.kr0;
import defpackage.l21;
import defpackage.ma0;
import defpackage.mi;
import defpackage.ni;
import defpackage.no0;
import defpackage.oj;
import defpackage.pj;
import defpackage.po0;
import defpackage.qg;
import defpackage.r51;
import defpackage.s51;
import defpackage.s80;
import defpackage.t40;
import defpackage.tg;
import defpackage.ug;
import defpackage.vf0;
import defpackage.w20;
import defpackage.wg;
import defpackage.xg;
import defpackage.ym0;
import defpackage.z00;
import defpackage.z80;
import defpackage.zn;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public class ComponentActivity extends androidx.core.app.ComponentActivity implements mi, s51, w20, kr0 {
    public static final /* synthetic */ int V = 0;
    public final ni F = new ni();
    public final i6 G = new i6(new e(8, this));
    public final z80 H;
    public final i6 I;
    public r51 J;
    public ej0 K;
    public final xg L;
    public final z00 M;
    public final tg N;
    public final CopyOnWriteArrayList O;
    public final CopyOnWriteArrayList P;
    public final CopyOnWriteArrayList Q;
    public final CopyOnWriteArrayList R;
    public final CopyOnWriteArrayList S;
    public boolean T;
    public boolean U;

    public ComponentActivity() {
        ir0 ir0Var;
        z80 z80Var = new z80(this);
        this.H = z80Var;
        jr0 jr0Var = new jr0(this, new qg(this, 2));
        i6 i6Var = new i6(jr0Var);
        this.I = i6Var;
        this.K = null;
        xg xgVar = new xg(this);
        this.L = xgVar;
        this.M = new z00(xgVar, new qg(this, 0));
        new AtomicInteger();
        this.N = new tg();
        this.O = new CopyOnWriteArrayList();
        this.P = new CopyOnWriteArrayList();
        this.Q = new CopyOnWriteArrayList();
        this.R = new CopyOnWriteArrayList();
        this.S = new CopyOnWriteArrayList();
        this.T = false;
        this.U = false;
        z80Var.a(new ug(this, 0));
        z80Var.a(new ug(this, 1));
        z80Var.a(new ug(this, 2));
        jr0Var.a();
        Lifecycle$State lifecycle$State = z80Var.c;
        if (lifecycle$State != Lifecycle$State.INITIALIZED && lifecycle$State != Lifecycle$State.CREATED) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        i6 i6Var2 = (i6) i6Var.G;
        jr0 jr0Var2 = (jr0) i6Var2.F;
        synchronized (jr0Var2.c) {
            Iterator it = jr0Var2.d.entrySet().iterator();
            do {
                ir0Var = null;
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry entry = (Map.Entry) it.next();
                String str = (String) entry.getKey();
                ir0 ir0Var2 = (ir0) entry.getValue();
                if (t40.f(str, "androidx.lifecycle.internal.SavedStateHandlesProvider")) {
                    ir0Var = ir0Var2;
                }
            } while (ir0Var == null);
        }
        if (ir0Var == null) {
            fr0 fr0Var = new fr0(i6Var2, this);
            i6Var2.L("androidx.lifecycle.internal.SavedStateHandlesProvider", fr0Var);
            z80Var.a(new ym0(3, fr0Var));
        }
        i6Var2.L("android:support:activity-result", new ir0() { // from class: rg
            @Override // defpackage.ir0
            public final Bundle a() {
                int i = ComponentActivity.V;
                ComponentActivity componentActivity = this.a;
                Bundle bundle = new Bundle();
                tg tgVar = componentActivity.N;
                tgVar.getClass();
                HashMap map = tgVar.b;
                bundle.putIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS", new ArrayList<>(map.values()));
                bundle.putStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS", new ArrayList<>(map.keySet()));
                bundle.putStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS", new ArrayList<>(tgVar.d));
                bundle.putBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT", (Bundle) tgVar.g.clone());
                return bundle;
            }
        });
        addOnContextAvailableListener(new fj0() { // from class: sg
            @Override // defpackage.fj0
            public final void a() {
                ComponentActivity componentActivity = this.a;
                Bundle bundleD = ((i6) componentActivity.I.G).d("android:support:activity-result");
                if (bundleD != null) {
                    tg tgVar = componentActivity.N;
                    tgVar.getClass();
                    ArrayList<Integer> integerArrayList = bundleD.getIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS");
                    ArrayList<String> stringArrayList = bundleD.getStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS");
                    if (stringArrayList == null || integerArrayList == null) {
                        return;
                    }
                    tgVar.d = bundleD.getStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS");
                    Bundle bundle = bundleD.getBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT");
                    Bundle bundle2 = tgVar.g;
                    bundle2.putAll(bundle);
                    for (int i = 0; i < stringArrayList.size(); i++) {
                        String str2 = stringArrayList.get(i);
                        HashMap map = tgVar.b;
                        boolean zContainsKey = map.containsKey(str2);
                        HashMap map2 = tgVar.a;
                        if (zContainsKey) {
                            Integer num = (Integer) map.remove(str2);
                            if (!bundle2.containsKey(str2)) {
                                map2.remove(num);
                            }
                        }
                        Integer num2 = integerArrayList.get(i);
                        num2.intValue();
                        String str3 = stringArrayList.get(i);
                        map2.put(num2, str3);
                        map.put(str3, num2);
                    }
                }
            }
        });
    }

    @Override // defpackage.kr0
    public final i6 a() {
        return (i6) this.I.G;
    }

    @Override // android.app.Activity
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        h();
        this.L.a(getWindow().getDecorView());
        super.addContentView(view, layoutParams);
    }

    @Override // defpackage.mi
    public final void addOnContextAvailableListener(fj0 fj0Var) {
        this.F.addOnContextAvailableListener(fj0Var);
    }

    @Override // defpackage.w20
    public final pj c() {
        vf0 vf0Var = new vf0(oj.b);
        Application application = getApplication();
        LinkedHashMap linkedHashMap = vf0Var.a;
        if (application != null) {
            linkedHashMap.put(l21.S, getApplication());
        }
        linkedHashMap.put(t40.i, this);
        linkedHashMap.put(t40.j, this);
        if (getIntent() != null && getIntent().getExtras() != null) {
            linkedHashMap.put(t40.k, getIntent().getExtras());
        }
        return vf0Var;
    }

    @Override // defpackage.s51
    public final r51 d() {
        if (getApplication() == null) {
            throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
        }
        if (this.J == null) {
            wg wgVar = (wg) getLastNonConfigurationInstance();
            if (wgVar != null) {
                this.J = wgVar.a;
            }
            if (this.J == null) {
                this.J = new r51();
            }
        }
        return this.J;
    }

    public final ej0 g() {
        if (this.K == null) {
            this.K = new ej0(new f(6, this));
            this.H.a(new ug(this, 3));
        }
        return this.K;
    }

    @Override // androidx.core.app.ComponentActivity, defpackage.x80
    public final s80 getLifecycle() {
        return this.H;
    }

    public final void h() {
        View decorView = getWindow().getDecorView();
        t40.n(decorView, "<this>");
        decorView.setTag(androidx.lifecycle.runtime.R$id.view_tree_lifecycle_owner, this);
        View decorView2 = getWindow().getDecorView();
        t40.n(decorView2, "<this>");
        decorView2.setTag(androidx.lifecycle.viewmodel.R$id.view_tree_view_model_store_owner, this);
        View decorView3 = getWindow().getDecorView();
        t40.n(decorView3, "<this>");
        decorView3.setTag(androidx.savedstate.R$id.view_tree_saved_state_registry_owner, this);
        View decorView4 = getWindow().getDecorView();
        t40.n(decorView4, "<this>");
        decorView4.setTag(R$id.view_tree_on_back_pressed_dispatcher_owner, this);
        View decorView5 = getWindow().getDecorView();
        t40.n(decorView5, "<this>");
        decorView5.setTag(R$id.report_drawn, this);
    }

    @Override // android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        if (this.N.a(i, i2, intent)) {
            return;
        }
        super.onActivityResult(i, i2, intent);
    }

    @Override // android.app.Activity
    public final void onBackPressed() {
        g().a();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Iterator it = this.O.iterator();
        while (it.hasNext()) {
            ((ei) it.next()).accept(configuration);
        }
    }

    @Override // androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        this.I.I(bundle);
        ni niVar = this.F;
        niVar.getClass();
        niVar.b = this;
        Iterator it = niVar.a.iterator();
        while (it.hasNext()) {
            ((fj0) it.next()).a();
        }
        super.onCreate(bundle);
        int i = po0.F;
        no0.b(this);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onCreatePanelMenu(int i, Menu menu) {
        if (i != 0) {
            return true;
        }
        super.onCreatePanelMenu(i, menu);
        getMenuInflater();
        Iterator it = ((CopyOnWriteArrayList) this.G.G).iterator();
        if (it.hasNext()) {
            throw zn.i(it);
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
        Iterator it = ((CopyOnWriteArrayList) this.G.G).iterator();
        if (it.hasNext()) {
            throw zn.i(it);
        }
        return false;
    }

    @Override // android.app.Activity
    public void onMultiWindowModeChanged(boolean z) {
        if (this.T) {
            return;
        }
        Iterator it = this.R.iterator();
        while (it.hasNext()) {
            ((ei) it.next()).accept(new di(20));
        }
    }

    @Override // android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Iterator it = this.Q.iterator();
        while (it.hasNext()) {
            ((ei) it.next()).accept(intent);
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i, Menu menu) {
        Iterator it = ((CopyOnWriteArrayList) this.G.G).iterator();
        if (it.hasNext()) {
            throw zn.i(it);
        }
        super.onPanelClosed(i, menu);
    }

    @Override // android.app.Activity
    public void onPictureInPictureModeChanged(boolean z) {
        if (this.U) {
            return;
        }
        Iterator it = this.S.iterator();
        while (it.hasNext()) {
            ((ei) it.next()).accept(new di(24));
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onPreparePanel(int i, View view, Menu menu) {
        if (i != 0) {
            return true;
        }
        super.onPreparePanel(i, view, menu);
        Iterator it = ((CopyOnWriteArrayList) this.G.G).iterator();
        if (it.hasNext()) {
            throw zn.i(it);
        }
        return true;
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (this.N.a(i, -1, new Intent().putExtra("androidx.activity.result.contract.extra.PERMISSIONS", strArr).putExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS", iArr))) {
            return;
        }
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    @Override // android.app.Activity
    public final Object onRetainNonConfigurationInstance() {
        wg wgVar;
        r51 r51Var = this.J;
        if (r51Var == null && (wgVar = (wg) getLastNonConfigurationInstance()) != null) {
            r51Var = wgVar.a;
        }
        if (r51Var == null) {
            return null;
        }
        wg wgVar2 = new wg();
        wgVar2.a = r51Var;
        return wgVar2;
    }

    @Override // androidx.core.app.ComponentActivity, android.app.Activity
    public final void onSaveInstanceState(Bundle bundle) {
        z80 z80Var = this.H;
        if (z80Var != null) {
            z80Var.g(Lifecycle$State.CREATED);
        }
        super.onSaveInstanceState(bundle);
        this.I.J(bundle);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks2
    public final void onTrimMemory(int i) {
        super.onTrimMemory(i);
        Iterator it = this.P.iterator();
        while (it.hasNext()) {
            ((ei) it.next()).accept(Integer.valueOf(i));
        }
    }

    @Override // defpackage.mi
    public final void removeOnContextAvailableListener(fj0 fj0Var) {
        this.F.removeOnContextAvailableListener(fj0Var);
    }

    @Override // android.app.Activity
    public final void reportFullyDrawn() {
        try {
            if (ma0.M()) {
                Trace.beginSection("reportFullyDrawn() for ComponentActivity");
            }
            super.reportFullyDrawn();
            z00 z00Var = this.M;
            synchronized (z00Var.a) {
                try {
                    z00Var.b = true;
                    Iterator it = z00Var.c.iterator();
                    while (it.hasNext()) {
                        ((a10) it.next()).invoke();
                    }
                    z00Var.c.clear();
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
        this.L.a(getWindow().getDecorView());
        super.setContentView(i);
    }

    @Override // android.app.Activity
    public void setContentView(@SuppressLint({"UnknownNullness", "MissingNullability"}) View view) {
        h();
        this.L.a(getWindow().getDecorView());
        super.setContentView(view);
    }

    @Override // android.app.Activity
    public final void onMultiWindowModeChanged(boolean z, Configuration configuration) {
        this.T = true;
        try {
            super.onMultiWindowModeChanged(z, configuration);
            this.T = false;
            for (ei eiVar : this.R) {
                t40.n(configuration, "newConfig");
                eiVar.accept(new di(20));
            }
        } catch (Throwable th) {
            this.T = false;
            throw th;
        }
    }

    @Override // android.app.Activity
    public final void onPictureInPictureModeChanged(boolean z, Configuration configuration) {
        this.U = true;
        try {
            super.onPictureInPictureModeChanged(z, configuration);
            this.U = false;
            for (ei eiVar : this.S) {
                t40.n(configuration, "newConfig");
                eiVar.accept(new di(24));
            }
        } catch (Throwable th) {
            this.U = false;
            throw th;
        }
    }

    @Override // android.app.Activity
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        h();
        this.L.a(getWindow().getDecorView());
        super.setContentView(view, layoutParams);
    }
}
