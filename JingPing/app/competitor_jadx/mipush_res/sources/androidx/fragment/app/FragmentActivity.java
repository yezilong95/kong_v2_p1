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
import defpackage.g5;
import defpackage.ga0;
import defpackage.h00;
import defpackage.h5;
import defpackage.i6;
import defpackage.lw0;
import defpackage.n00;
import defpackage.v3;
import defpackage.vz;
import defpackage.wz;
import defpackage.z8;
import defpackage.z80;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes.dex */
public class FragmentActivity extends ComponentActivity implements v3 {
    public boolean Y;
    public boolean Z;
    public final z8 W = new z8(21, new wz(this));
    public final z80 X = new z80(this);
    public boolean a0 = true;

    public FragmentActivity() {
        ((i6) this.I.G).L("android:support:fragments", new g5(this, 1));
        addOnContextAvailableListener(new h5(this, 1));
    }

    public static boolean i(c cVar, Lifecycle$State lifecycle$State) {
        boolean zI = false;
        for (vz vzVar : cVar.c.D()) {
            if (vzVar != null) {
                wz wzVar = vzVar.W;
                if ((wzVar == null ? null : wzVar.f0) != null) {
                    zI |= i(vzVar.f(), lifecycle$State);
                }
                n00 n00Var = vzVar.q0;
                if (n00Var != null) {
                    n00Var.e();
                    if (n00Var.F.c.isAtLeast(Lifecycle$State.STARTED)) {
                        vzVar.q0.F.g(lifecycle$State);
                        zI = true;
                    }
                }
                if (vzVar.p0.c.isAtLeast(Lifecycle$State.STARTED)) {
                    vzVar.p0.g(lifecycle$State);
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
        printWriter.print(this.Y);
        printWriter.print(" mResumed=");
        printWriter.print(this.Z);
        printWriter.print(" mStopped=");
        printWriter.print(this.a0);
        if (getApplication() != null) {
            lw0 lw0Var = ((ga0) new z8(d(), ga0.c).y(ga0.class)).b;
            if (lw0Var.G > 0) {
                printWriter.print(str2);
                printWriter.println("Loaders:");
                if (lw0Var.G > 0) {
                    if (lw0Var.F[0] != null) {
                        throw new ClassCastException();
                    }
                    printWriter.print(str2);
                    printWriter.print("  #");
                    printWriter.print(lw0Var.E[0]);
                    printWriter.print(": ");
                    throw null;
                }
            }
        }
        ((wz) this.W.F).e0.q(str, fileDescriptor, printWriter, strArr);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void onActivityResult(int i, int i2, Intent intent) {
        this.W.z();
        super.onActivityResult(i, i2, intent);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        z8 z8Var = this.W;
        z8Var.z();
        super.onConfigurationChanged(configuration);
        ((wz) z8Var.F).e0.h();
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.X.e(Lifecycle$Event.ON_CREATE);
        h00 h00Var = ((wz) this.W.F).e0;
        h00Var.z = false;
        h00Var.A = false;
        h00Var.G.g = false;
        h00Var.p(1);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public final boolean onCreatePanelMenu(int i, Menu menu) {
        if (i != 0) {
            super.onCreatePanelMenu(i, menu);
            return true;
        }
        super.onCreatePanelMenu(i, menu);
        getMenuInflater();
        ((wz) this.W.F).e0.j();
        return true;
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        View viewOnCreateView = ((wz) this.W.F).e0.f.onCreateView(view, str, context, attributeSet);
        return viewOnCreateView == null ? super.onCreateView(view, str, context, attributeSet) : viewOnCreateView;
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        ((wz) this.W.F).e0.k();
        this.X.e(Lifecycle$Event.ON_DESTROY);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public final void onLowMemory() {
        super.onLowMemory();
        for (vz vzVar : ((wz) this.W.F).e0.c.D()) {
            if (vzVar != null) {
                vzVar.v();
            }
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        z8 z8Var = this.W;
        if (i == 0) {
            return ((wz) z8Var.F).e0.l();
        }
        if (i != 6) {
            return false;
        }
        return ((wz) z8Var.F).e0.i();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void onMultiWindowModeChanged(boolean z) {
        for (vz vzVar : ((wz) this.W.F).e0.c.D()) {
            if (vzVar != null) {
                vzVar.w();
            }
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void onNewIntent(Intent intent) {
        this.W.z();
        super.onNewIntent(intent);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i, Menu menu) {
        if (i == 0) {
            ((wz) this.W.F).e0.m();
        }
        super.onPanelClosed(i, menu);
    }

    @Override // android.app.Activity
    public final void onPause() {
        super.onPause();
        this.Z = false;
        ((wz) this.W.F).e0.p(5);
        this.X.e(Lifecycle$Event.ON_PAUSE);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void onPictureInPictureModeChanged(boolean z) {
        for (vz vzVar : ((wz) this.W.F).e0.c.D()) {
            if (vzVar != null) {
                vzVar.x();
            }
        }
    }

    @Override // android.app.Activity
    public void onPostResume() {
        super.onPostResume();
        this.X.e(Lifecycle$Event.ON_RESUME);
        h00 h00Var = ((wz) this.W.F).e0;
        h00Var.z = false;
        h00Var.A = false;
        h00Var.G.g = false;
        h00Var.p(7);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public final boolean onPreparePanel(int i, View view, Menu menu) {
        if (i != 0) {
            super.onPreparePanel(i, view, menu);
            return true;
        }
        super.onPreparePanel(0, view, menu);
        ((wz) this.W.F).e0.o();
        return true;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        this.W.z();
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    @Override // android.app.Activity
    public final void onResume() {
        z8 z8Var = this.W;
        z8Var.z();
        super.onResume();
        this.Z = true;
        ((wz) z8Var.F).e0.t(true);
    }

    @Override // android.app.Activity
    public void onStart() {
        z8 z8Var = this.W;
        z8Var.z();
        super.onStart();
        this.a0 = false;
        boolean z = this.Y;
        wz wzVar = (wz) z8Var.F;
        if (!z) {
            this.Y = true;
            h00 h00Var = wzVar.e0;
            h00Var.z = false;
            h00Var.A = false;
            h00Var.G.g = false;
            h00Var.p(4);
        }
        wzVar.e0.t(true);
        this.X.e(Lifecycle$Event.ON_START);
        h00 h00Var2 = wzVar.e0;
        h00Var2.z = false;
        h00Var2.A = false;
        h00Var2.G.g = false;
        h00Var2.p(5);
    }

    @Override // android.app.Activity
    public final void onStateNotSaved() {
        this.W.z();
    }

    @Override // android.app.Activity
    public void onStop() {
        z8 z8Var;
        wz wzVar;
        super.onStop();
        this.a0 = true;
        do {
            z8Var = this.W;
            wzVar = (wz) z8Var.F;
        } while (i(wzVar.e0, Lifecycle$State.CREATED));
        h00 h00Var = ((wz) z8Var.F).e0;
        h00Var.A = true;
        h00Var.G.g = true;
        h00Var.p(4);
        this.X.e(Lifecycle$Event.ON_STOP);
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory
    public final View onCreateView(String str, Context context, AttributeSet attributeSet) {
        View viewOnCreateView = ((wz) this.W.F).e0.f.onCreateView(null, str, context, attributeSet);
        return viewOnCreateView == null ? super.onCreateView(str, context, attributeSet) : viewOnCreateView;
    }
}
