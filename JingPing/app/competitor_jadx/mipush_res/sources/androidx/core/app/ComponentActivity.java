package androidx.core.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import androidx.lifecycle.Lifecycle$State;
import defpackage.m70;
import defpackage.no0;
import defpackage.po0;
import defpackage.s80;
import defpackage.t40;
import defpackage.x80;
import defpackage.z80;

/* JADX INFO: loaded from: classes.dex */
public class ComponentActivity extends Activity implements x80, m70 {
    public final z80 E = new z80(this);

    @Override // defpackage.m70
    public final boolean b(KeyEvent keyEvent) {
        t40.n(keyEvent, "event");
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        t40.n(keyEvent, "event");
        View decorView = getWindow().getDecorView();
        t40.m(decorView, "window.decorView");
        if (t40.K(decorView, keyEvent)) {
            return true;
        }
        return t40.L(this, decorView, this, keyEvent);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        t40.n(keyEvent, "event");
        View decorView = getWindow().getDecorView();
        t40.m(decorView, "window.decorView");
        if (t40.K(decorView, keyEvent)) {
            return true;
        }
        return super.dispatchKeyShortcutEvent(keyEvent);
    }

    public void e() {
        invalidateOptionsMenu();
    }

    public s80 getLifecycle() {
        return this.E;
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        int i = po0.F;
        no0.b(this);
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        t40.n(bundle, "outState");
        this.E.g(Lifecycle$State.CREATED);
        super.onSaveInstanceState(bundle);
    }
}
