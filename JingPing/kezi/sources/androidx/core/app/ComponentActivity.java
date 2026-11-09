package androidx.core.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import androidx.lifecycle.Lifecycle$State;
import defpackage.ne0;
import defpackage.oy;
import defpackage.p10;
import defpackage.pe0;
import defpackage.u10;
import defpackage.w10;
import defpackage.x00;
import defpackage.xi;

/* JADX INFO: loaded from: classes.dex */
public class ComponentActivity extends Activity implements u10, x00 {
    public final w10 b = new w10(this);

    @Override // defpackage.x00
    public final boolean c(KeyEvent keyEvent) {
        oy.j(keyEvent, "event");
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        oy.j(keyEvent, "event");
        View decorView = getWindow().getDecorView();
        oy.i(decorView, "window.decorView");
        if (xi.o(decorView, keyEvent)) {
            return true;
        }
        return xi.p(this, decorView, this, keyEvent);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        oy.j(keyEvent, "event");
        View decorView = getWindow().getDecorView();
        oy.i(decorView, "window.decorView");
        if (xi.o(decorView, keyEvent)) {
            return true;
        }
        return super.dispatchKeyShortcutEvent(keyEvent);
    }

    public void e() {
        invalidateOptionsMenu();
    }

    public p10 getLifecycle() {
        return this.b;
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        int i = pe0.c;
        ne0.b(this);
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        oy.j(bundle, "outState");
        this.b.g(Lifecycle$State.CREATED);
        super.onSaveInstanceState(bundle);
    }
}
