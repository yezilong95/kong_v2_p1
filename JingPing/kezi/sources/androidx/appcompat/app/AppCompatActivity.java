package androidx.appcompat.app;

import android.R;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.appcompat.R$bool;
import androidx.appcompat.widget.l;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.runtime.R$id;
import defpackage.a5;
import defpackage.bu0;
import defpackage.c4;
import defpackage.ct0;
import defpackage.d4;
import defpackage.e5;
import defpackage.f4;
import defpackage.g1;
import defpackage.k4;
import defpackage.m6;
import defpackage.ol0;
import defpackage.oy;
import defpackage.t30;
import defpackage.ve0;
import defpackage.wq0;
import defpackage.x4;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatActivity extends FragmentActivity implements f4 {
    public x4 A;

    public AppCompatActivity() {
        ((e5) this.f.d).M("androidx:appcompat", new c4(this, 0));
        addOnContextAvailableListener(new d4(this, 0));
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        l();
        x4 x4Var = (x4) j();
        x4Var.w();
        ((ViewGroup) x4Var.B.findViewById(R.id.content)).addView(view, layoutParams);
        x4Var.n.a(x4Var.m.getCallback());
    }

    /* JADX WARN: Removed duplicated region for block: B:169:0x0234 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00ae  */
    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void attachBaseContext(android.content.Context r12) {
        /*
            Method dump skipped, instruction units count: 591
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatActivity.attachBaseContext(android.content.Context):void");
    }

    @Override // android.app.Activity
    public final void closeOptionsMenu() {
        k();
        if (getWindow().hasFeature(0)) {
            super.closeOptionsMenu();
        }
    }

    @Override // androidx.core.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        keyEvent.getKeyCode();
        k();
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // androidx.core.app.ComponentActivity
    public final void e() {
        j().b();
    }

    @Override // android.app.Activity
    public final View findViewById(int i) {
        x4 x4Var = (x4) j();
        x4Var.w();
        return x4Var.m.findViewById(i);
    }

    @Override // android.app.Activity
    public final MenuInflater getMenuInflater() {
        x4 x4Var = (x4) j();
        if (x4Var.q == null) {
            x4Var.A();
            bu0 bu0Var = x4Var.p;
            x4Var.q = new ol0(bu0Var != null ? bu0Var.b() : x4Var.l);
        }
        return x4Var.q;
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public final Resources getResources() {
        int i = wq0.a;
        return super.getResources();
    }

    @Override // android.app.Activity
    public final void invalidateOptionsMenu() {
        j().b();
    }

    public final k4 j() {
        if (this.A == null) {
            m6 m6Var = k4.b;
            this.A = new x4(this, null, this, this);
        }
        return this.A;
    }

    public final bu0 k() {
        x4 x4Var = (x4) j();
        x4Var.A();
        return x4Var.p;
    }

    public final void l() {
        View decorView = getWindow().getDecorView();
        oy.j(decorView, "<this>");
        decorView.setTag(R$id.view_tree_lifecycle_owner, this);
        View decorView2 = getWindow().getDecorView();
        oy.j(decorView2, "<this>");
        decorView2.setTag(androidx.lifecycle.viewmodel.R$id.view_tree_view_model_store_owner, this);
        View decorView3 = getWindow().getDecorView();
        oy.j(decorView3, "<this>");
        decorView3.setTag(androidx.savedstate.R$id.view_tree_saved_state_registry_owner, this);
        View decorView4 = getWindow().getDecorView();
        oy.j(decorView4, "<this>");
        decorView4.setTag(androidx.activity.R$id.view_tree_on_back_pressed_dispatcher_owner, this);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        x4 x4Var = (x4) j();
        if (x4Var.G && x4Var.A) {
            x4Var.A();
            bu0 bu0Var = x4Var.p;
            if (bu0Var != null) {
                bu0Var.e(g1.b(bu0Var.a).a.getResources().getBoolean(R$bool.abc_action_bar_embed_tabs));
            }
        }
        a5 a5VarA = a5.a();
        Context context = x4Var.l;
        synchronized (a5VarA) {
            ve0 ve0Var = a5VarA.a;
            synchronized (ve0Var) {
                t30 t30Var = (t30) ve0Var.b.get(context);
                if (t30Var != null) {
                    t30Var.a();
                }
            }
        }
        x4Var.S = new Configuration(x4Var.l.getResources().getConfiguration());
        x4Var.n(false, false);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final void onContentChanged() {
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        j().e();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        Window window;
        if (Build.VERSION.SDK_INT >= 26 || keyEvent.isCtrlPressed() || KeyEvent.metaStateHasNoModifiers(keyEvent.getMetaState()) || keyEvent.getRepeatCount() != 0 || KeyEvent.isModifierKey(keyEvent.getKeyCode()) || (window = getWindow()) == null || window.getDecorView() == null || !window.getDecorView().dispatchKeyShortcutEvent(keyEvent)) {
            return super.onKeyDown(i, keyEvent);
        }
        return true;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public final boolean onMenuItemSelected(int i, MenuItem menuItem) {
        Intent intentY;
        if (!super.onMenuItemSelected(i, menuItem)) {
            bu0 bu0VarK = k();
            if (menuItem.getItemId() != 16908332 || bu0VarK == null || (((l) bu0VarK.e).b & 4) == 0 || (intentY = oy.y(this)) == null) {
                return false;
            }
            if (!shouldUpRecreateTask(intentY)) {
                navigateUpTo(intentY);
                return true;
            }
            ArrayList arrayList = new ArrayList();
            Intent intentY2 = oy.y(this);
            if (intentY2 == null) {
                intentY2 = oy.y(this);
            }
            if (intentY2 != null) {
                ComponentName component = intentY2.getComponent();
                if (component == null) {
                    component = intentY2.resolveActivity(getPackageManager());
                }
                int size = arrayList.size();
                try {
                    Intent intentZ = oy.z(this, component);
                    while (intentZ != null) {
                        arrayList.add(size, intentZ);
                        intentZ = oy.z(this, intentZ.getComponent());
                    }
                    arrayList.add(intentY2);
                } catch (PackageManager.NameNotFoundException e) {
                    Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
                    throw new IllegalArgumentException(e);
                }
            }
            if (arrayList.isEmpty()) {
                throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
            }
            Intent[] intentArr = (Intent[]) arrayList.toArray(new Intent[0]);
            intentArr[0] = new Intent(intentArr[0]).addFlags(268484608);
            startActivities(intentArr, null);
            try {
                finishAffinity();
            } catch (IllegalStateException unused) {
                finish();
            }
        }
        return true;
    }

    @Override // android.app.Activity
    public final void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        ((x4) j()).w();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onPostResume() {
        super.onPostResume();
        x4 x4Var = (x4) j();
        x4Var.A();
        bu0 bu0Var = x4Var.p;
        if (bu0Var != null) {
            bu0Var.u = true;
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onStart() {
        super.onStart();
        ((x4) j()).n(true, false);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onStop() {
        super.onStop();
        x4 x4Var = (x4) j();
        x4Var.A();
        bu0 bu0Var = x4Var.p;
        if (bu0Var != null) {
            bu0Var.u = false;
            ct0 ct0Var = bu0Var.t;
            if (ct0Var != null) {
                ct0Var.a();
            }
        }
    }

    @Override // android.app.Activity
    public final void onTitleChanged(CharSequence charSequence, int i) {
        super.onTitleChanged(charSequence, i);
        j().l(charSequence);
    }

    @Override // android.app.Activity
    public final void openOptionsMenu() {
        k();
        if (getWindow().hasFeature(0)) {
            super.openOptionsMenu();
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void setContentView(int i) {
        l();
        j().h(i);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public final void setTheme(int i) {
        super.setTheme(i);
        ((x4) j()).U = i;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(View view) {
        l();
        j().j(view);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        l();
        j().k(view, layoutParams);
    }
}
