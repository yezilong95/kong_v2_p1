package androidx.fragment.app;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.activity.ComponentActivity;
import androidx.lifecycle.Lifecycle$Event;
import androidx.lifecycle.Lifecycle$State;
import defpackage.b2;
import defpackage.bu;
import defpackage.c30;
import defpackage.c4;
import defpackage.d4;
import defpackage.e5;
import defpackage.jt;
import defpackage.kt;
import defpackage.o70;
import defpackage.vt;
import defpackage.w10;
import defpackage.xj0;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes.dex */
public class FragmentActivity extends ComponentActivity implements b2 {
    public boolean x;
    public boolean y;
    public final o70 v = new o70(21, new kt(this));
    public final w10 w = new w10(this);
    public boolean z = true;

    public FragmentActivity() {
        ((e5) this.f.d).M("android:support:fragments", new c4(this, 1));
        addOnContextAvailableListener(new d4(this, 1));
    }

    public static boolean i(c cVar, Lifecycle$State lifecycle$State) {
        boolean zI = false;
        for (jt jtVar : cVar.c.B()) {
            if (jtVar != null) {
                kt ktVar = jtVar.t;
                if ((ktVar == null ? null : ktVar.x) != null) {
                    zI |= i(jtVar.f(), lifecycle$State);
                }
                bu buVar = jtVar.N;
                if (buVar != null) {
                    buVar.e();
                    if (buVar.c.c.isAtLeast(Lifecycle$State.STARTED)) {
                        jtVar.N.c.g(lifecycle$State);
                        zI = true;
                    }
                }
                if (jtVar.M.c.isAtLeast(Lifecycle$State.STARTED)) {
                    jtVar.M.g(lifecycle$State);
                    zI = true;
                }
            }
        }
        return zI;
    }

    @Override // android.app.Activity
    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        printWriter.print(str);
        printWriter.print("Local FragmentActivity ");
        printWriter.print(Integer.toHexString(System.identityHashCode(this)));
        printWriter.println(" State:");
        String str2 = str + "  ";
        printWriter.print(str2);
        printWriter.print("mCreated=");
        printWriter.print(this.x);
        printWriter.print(" mResumed=");
        printWriter.print(this.y);
        printWriter.print(" mStopped=");
        printWriter.print(this.z);
        if (getApplication() != null) {
            xj0 xj0Var = ((c30) new o70(d(), c30.c).w(c30.class)).b;
            if (xj0Var.d > 0) {
                printWriter.print(str2);
                printWriter.println("Loaders:");
                if (xj0Var.d > 0) {
                    if (xj0Var.c[0] != null) {
                        throw new ClassCastException();
                    }
                    printWriter.print(str2);
                    printWriter.print("  #");
                    printWriter.print(xj0Var.b[0]);
                    printWriter.print(": ");
                    throw null;
                }
            }
        }
        ((kt) this.v.c).w.r(str, fileDescriptor, printWriter, strArr);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void onActivityResult(int i, int i2, Intent intent) {
        this.v.x();
        super.onActivityResult(i, i2, intent);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        o70 o70Var = this.v;
        o70Var.x();
        super.onConfigurationChanged(configuration);
        ((kt) o70Var.c).w.h();
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.w.e(Lifecycle$Event.ON_CREATE);
        vt vtVar = ((kt) this.v.c).w;
        vtVar.z = false;
        vtVar.A = false;
        vtVar.G.g = false;
        vtVar.p(1);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public final boolean onCreatePanelMenu(int i, Menu menu) {
        if (i != 0) {
            super.onCreatePanelMenu(i, menu);
            return true;
        }
        super.onCreatePanelMenu(i, menu);
        getMenuInflater();
        ((kt) this.v.c).w.j();
        return true;
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        View viewOnCreateView = ((kt) this.v.c).w.f.onCreateView(view, str, context, attributeSet);
        return viewOnCreateView == null ? super.onCreateView(view, str, context, attributeSet) : viewOnCreateView;
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        ((kt) this.v.c).w.k();
        this.w.e(Lifecycle$Event.ON_DESTROY);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public final void onLowMemory() {
        super.onLowMemory();
        for (jt jtVar : ((kt) this.v.c).w.c.B()) {
            if (jtVar != null) {
                jtVar.v();
            }
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        o70 o70Var = this.v;
        if (i == 0) {
            return ((kt) o70Var.c).w.l();
        }
        if (i != 6) {
            return false;
        }
        return ((kt) o70Var.c).w.i();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void onMultiWindowModeChanged(boolean z) {
        for (jt jtVar : ((kt) this.v.c).w.c.B()) {
            if (jtVar != null) {
                jtVar.w();
            }
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void onNewIntent(Intent intent) {
        this.v.x();
        super.onNewIntent(intent);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i, Menu menu) {
        if (i == 0) {
            ((kt) this.v.c).w.m();
        }
        super.onPanelClosed(i, menu);
    }

    @Override // android.app.Activity
    public final void onPause() {
        super.onPause();
        this.y = false;
        ((kt) this.v.c).w.p(5);
        this.w.e(Lifecycle$Event.ON_PAUSE);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void onPictureInPictureModeChanged(boolean z) {
        for (jt jtVar : ((kt) this.v.c).w.c.B()) {
            if (jtVar != null) {
                jtVar.x();
            }
        }
    }

    @Override // android.app.Activity
    public void onPostResume() {
        super.onPostResume();
        this.w.e(Lifecycle$Event.ON_RESUME);
        vt vtVar = ((kt) this.v.c).w;
        vtVar.z = false;
        vtVar.A = false;
        vtVar.G.g = false;
        vtVar.p(7);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public final boolean onPreparePanel(int i, View view, Menu menu) {
        if (i != 0) {
            super.onPreparePanel(i, view, menu);
            return true;
        }
        super.onPreparePanel(0, view, menu);
        ((kt) this.v.c).w.o();
        return true;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        this.v.x();
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    @Override // android.app.Activity
    public final void onResume() {
        o70 o70Var = this.v;
        o70Var.x();
        super.onResume();
        this.y = true;
        ((kt) o70Var.c).w.u(true);
    }

    @Override // android.app.Activity
    public void onStart() {
        o70 o70Var = this.v;
        o70Var.x();
        super.onStart();
        this.z = false;
        boolean z = this.x;
        kt ktVar = (kt) o70Var.c;
        if (!z) {
            this.x = true;
            vt vtVar = ktVar.w;
            vtVar.z = false;
            vtVar.A = false;
            vtVar.G.g = false;
            vtVar.p(4);
        }
        ktVar.w.u(true);
        this.w.e(Lifecycle$Event.ON_START);
        vt vtVar2 = ktVar.w;
        vtVar2.z = false;
        vtVar2.A = false;
        vtVar2.G.g = false;
        vtVar2.p(5);
    }

    @Override // android.app.Activity
    public final void onStateNotSaved() {
        this.v.x();
    }

    @Override // android.app.Activity
    public void onStop() {
        o70 o70Var;
        kt ktVar;
        super.onStop();
        this.z = true;
        do {
            o70Var = this.v;
            ktVar = (kt) o70Var.c;
        } while (i(ktVar.w, Lifecycle$State.CREATED));
        vt vtVar = ((kt) o70Var.c).w;
        vtVar.A = true;
        vtVar.G.g = true;
        vtVar.p(4);
        this.w.e(Lifecycle$Event.ON_STOP);
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory
    public final View onCreateView(String str, Context context, AttributeSet attributeSet) {
        View viewOnCreateView = ((kt) this.v.c).w.f.onCreateView(null, str, context, attributeSet);
        return viewOnCreateView == null ? super.onCreateView(str, context, attributeSet) : viewOnCreateView;
    }
}
