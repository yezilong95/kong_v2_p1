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
import defpackage.b41;
import defpackage.c6;
import defpackage.cb0;
import defpackage.cy0;
import defpackage.f6;
import defpackage.g5;
import defpackage.h5;
import defpackage.i6;
import defpackage.i61;
import defpackage.j5;
import defpackage.la0;
import defpackage.o5;
import defpackage.p50;
import defpackage.q7;
import defpackage.t40;
import defpackage.uo0;
import defpackage.x71;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatActivity extends FragmentActivity implements j5 {
    public c6 b0;

    public AppCompatActivity() {
        ((i6) this.I.G).L("androidx:appcompat", new g5(this, 0));
        addOnContextAvailableListener(new h5(this, 0));
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        k();
        c6 c6Var = (c6) j();
        c6Var.w();
        ((ViewGroup) c6Var.e0.findViewById(R.id.content)).addView(view, layoutParams);
        c6Var.Q.a(c6Var.P.getCallback());
    }

    /* JADX WARN: Removed duplicated region for block: B:169:0x0234 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00ae  */
    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void attachBaseContext(android.content.Context r12) {
        /*
            Method dump skipped, instruction units count: 591
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatActivity.attachBaseContext(android.content.Context):void");
    }

    @Override // android.app.Activity
    public final void closeOptionsMenu() {
        ((c6) j()).A();
        if (getWindow().hasFeature(0)) {
            super.closeOptionsMenu();
        }
    }

    @Override // androidx.core.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        keyEvent.getKeyCode();
        ((c6) j()).A();
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // androidx.core.app.ComponentActivity
    public final void e() {
        j().b();
    }

    @Override // android.app.Activity
    public final View findViewById(int i) {
        c6 c6Var = (c6) j();
        c6Var.w();
        return c6Var.P.findViewById(i);
    }

    @Override // android.app.Activity
    public final MenuInflater getMenuInflater() {
        c6 c6Var = (c6) j();
        if (c6Var.T == null) {
            c6Var.A();
            x71 x71Var = c6Var.S;
            c6Var.T = new cy0(x71Var != null ? x71Var.b() : c6Var.O);
        }
        return c6Var.T;
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public final Resources getResources() {
        int i = b41.a;
        return super.getResources();
    }

    @Override // android.app.Activity
    public final void invalidateOptionsMenu() {
        j().b();
    }

    public final o5 j() {
        if (this.b0 == null) {
            q7 q7Var = o5.E;
            this.b0 = new c6(this, null, this, this);
        }
        return this.b0;
    }

    public final void k() {
        View decorView = getWindow().getDecorView();
        t40.n(decorView, "<this>");
        decorView.setTag(R$id.view_tree_lifecycle_owner, this);
        View decorView2 = getWindow().getDecorView();
        t40.n(decorView2, "<this>");
        decorView2.setTag(androidx.lifecycle.viewmodel.R$id.view_tree_view_model_store_owner, this);
        View decorView3 = getWindow().getDecorView();
        t40.n(decorView3, "<this>");
        decorView3.setTag(androidx.savedstate.R$id.view_tree_saved_state_registry_owner, this);
        View decorView4 = getWindow().getDecorView();
        t40.n(decorView4, "<this>");
        decorView4.setTag(androidx.activity.R$id.view_tree_on_back_pressed_dispatcher_owner, this);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) throws IllegalAccessException {
        super.onConfigurationChanged(configuration);
        c6 c6Var = (c6) j();
        if (c6Var.j0 && c6Var.d0) {
            c6Var.A();
            x71 x71Var = c6Var.S;
            if (x71Var != null) {
                x71Var.e(((Context) p50.t(x71Var.a).F).getResources().getBoolean(R$bool.abc_action_bar_embed_tabs));
            }
        }
        f6 f6VarA = f6.a();
        Context context = c6Var.O;
        synchronized (f6VarA) {
            uo0 uo0Var = f6VarA.a;
            synchronized (uo0Var) {
                cb0 cb0Var = (cb0) uo0Var.b.get(context);
                if (cb0Var != null) {
                    cb0Var.a();
                }
            }
        }
        c6Var.v0 = new Configuration(c6Var.O.getResources().getConfiguration());
        c6Var.n(false, false);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final void onContentChanged() {
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onDestroy() {
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
        Intent intentB;
        if (!super.onMenuItemSelected(i, menuItem)) {
            c6 c6Var = (c6) j();
            c6Var.A();
            x71 x71Var = c6Var.S;
            if (menuItem.getItemId() != 16908332 || x71Var == null || (((l) x71Var.e).b & 4) == 0 || (intentB = la0.B(this)) == null) {
                return false;
            }
            if (!shouldUpRecreateTask(intentB)) {
                navigateUpTo(intentB);
                return true;
            }
            ArrayList arrayList = new ArrayList();
            Intent intentB2 = la0.B(this);
            if (intentB2 == null) {
                intentB2 = la0.B(this);
            }
            if (intentB2 != null) {
                ComponentName component = intentB2.getComponent();
                if (component == null) {
                    component = intentB2.resolveActivity(getPackageManager());
                }
                int size = arrayList.size();
                try {
                    Intent intentC = la0.C(this, component);
                    while (intentC != null) {
                        arrayList.add(size, intentC);
                        intentC = la0.C(this, intentC.getComponent());
                    }
                    arrayList.add(intentB2);
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
        ((c6) j()).w();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onPostResume() {
        super.onPostResume();
        c6 c6Var = (c6) j();
        c6Var.A();
        x71 x71Var = c6Var.S;
        if (x71Var != null) {
            x71Var.t = true;
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onStart() throws IllegalAccessException {
        super.onStart();
        ((c6) j()).n(true, false);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onStop() {
        super.onStop();
        c6 c6Var = (c6) j();
        c6Var.A();
        x71 x71Var = c6Var.S;
        if (x71Var != null) {
            x71Var.t = false;
            i61 i61Var = x71Var.s;
            if (i61Var != null) {
                i61Var.a();
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
        ((c6) j()).A();
        if (getWindow().hasFeature(0)) {
            super.openOptionsMenu();
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void setContentView(int i) {
        k();
        j().i(i);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public final void setTheme(int i) {
        super.setTheme(i);
        ((c6) j()).x0 = i;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(View view) {
        k();
        j().j(view);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        k();
        j().k(view, layoutParams);
    }
}
