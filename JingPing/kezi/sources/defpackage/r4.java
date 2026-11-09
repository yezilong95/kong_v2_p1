package defpackage;

import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class r4 implements Window.Callback {
    public final Window.Callback b;
    public boolean c;
    public boolean d;
    public boolean e;
    public final /* synthetic */ x4 f;

    public r4(x4 x4Var, Window.Callback callback) {
        this.f = x4Var;
        if (callback == null) {
            throw new IllegalArgumentException("Window callback may not be null");
        }
        this.b = callback;
    }

    public final void a(Window.Callback callback) {
        try {
            this.c = true;
            callback.onContentChanged();
        } finally {
            this.c = false;
        }
    }

    public final boolean b(int i, Menu menu) {
        return this.b.onMenuOpened(i, menu);
    }

    public final void c(int i, Menu menu) {
        this.b.onPanelClosed(i, menu);
    }

    public final void d(List list, Menu menu, int i) {
        wt0.a(this.b, list, menu, i);
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        return this.b.dispatchGenericMotionEvent(motionEvent);
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        boolean z = this.d;
        Window.Callback callback = this.b;
        return z ? callback.dispatchKeyEvent(keyEvent) : this.f.u(keyEvent) || callback.dispatchKeyEvent(keyEvent);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x006e A[RETURN] */
    @Override // android.view.Window.Callback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean dispatchKeyShortcutEvent(android.view.KeyEvent r7) {
        /*
            r6 = this;
            android.view.Window$Callback r0 = r6.b
            boolean r0 = r0.dispatchKeyShortcutEvent(r7)
            r1 = 1
            if (r0 != 0) goto L6f
            int r0 = r7.getKeyCode()
            x4 r2 = r6.f
            r2.A()
            bu0 r3 = r2.p
            r4 = 0
            if (r3 == 0) goto L3d
            au0 r3 = r3.i
            if (r3 != 0) goto L1d
        L1b:
            r0 = r4
            goto L39
        L1d:
            i60 r3 = r3.e
            if (r3 == 0) goto L1b
            int r5 = r7.getDeviceId()
            android.view.KeyCharacterMap r5 = android.view.KeyCharacterMap.load(r5)
            int r5 = r5.getKeyboardType()
            if (r5 == r1) goto L31
            r5 = r1
            goto L32
        L31:
            r5 = r4
        L32:
            r3.setQwertyMode(r5)
            boolean r0 = r3.performShortcut(r0, r7, r4)
        L39:
            if (r0 == 0) goto L3d
        L3b:
            r7 = r1
            goto L6b
        L3d:
            w4 r0 = r2.N
            if (r0 == 0) goto L52
            int r3 = r7.getKeyCode()
            boolean r0 = r2.F(r0, r3, r7)
            if (r0 == 0) goto L52
            w4 r7 = r2.N
            if (r7 == 0) goto L3b
            r7.l = r1
            goto L3b
        L52:
            w4 r0 = r2.N
            if (r0 != 0) goto L6a
            w4 r0 = r2.z(r4)
            r2.G(r0, r7)
            int r3 = r7.getKeyCode()
            boolean r7 = r2.F(r0, r3, r7)
            r0.k = r4
            if (r7 == 0) goto L6a
            goto L3b
        L6a:
            r7 = r4
        L6b:
            if (r7 == 0) goto L6e
            goto L6f
        L6e:
            return r4
        L6f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.r4.dispatchKeyShortcutEvent(android.view.KeyEvent):boolean");
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return this.b.dispatchPopulateAccessibilityEvent(accessibilityEvent);
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return this.b.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        return this.b.dispatchTrackballEvent(motionEvent);
    }

    @Override // android.view.Window.Callback
    public final void onActionModeFinished(ActionMode actionMode) {
        this.b.onActionModeFinished(actionMode);
    }

    @Override // android.view.Window.Callback
    public final void onActionModeStarted(ActionMode actionMode) {
        this.b.onActionModeStarted(actionMode);
    }

    @Override // android.view.Window.Callback
    public final void onAttachedToWindow() {
        this.b.onAttachedToWindow();
    }

    @Override // android.view.Window.Callback
    public final void onContentChanged() {
        if (this.c) {
            this.b.onContentChanged();
        }
    }

    @Override // android.view.Window.Callback
    public final boolean onCreatePanelMenu(int i, Menu menu) {
        if (i != 0 || (menu instanceof i60)) {
            return this.b.onCreatePanelMenu(i, menu);
        }
        return false;
    }

    @Override // android.view.Window.Callback
    public final View onCreatePanelView(int i) {
        return this.b.onCreatePanelView(i);
    }

    @Override // android.view.Window.Callback
    public final void onDetachedFromWindow() {
        this.b.onDetachedFromWindow();
    }

    @Override // android.view.Window.Callback
    public final boolean onMenuItemSelected(int i, MenuItem menuItem) {
        return this.b.onMenuItemSelected(i, menuItem);
    }

    @Override // android.view.Window.Callback
    public final boolean onMenuOpened(int i, Menu menu) {
        b(i, menu);
        x4 x4Var = this.f;
        if (i != 108) {
            x4Var.getClass();
            return true;
        }
        x4Var.A();
        bu0 bu0Var = x4Var.p;
        if (bu0Var != null && true != bu0Var.l) {
            bu0Var.l = true;
            ArrayList arrayList = bu0Var.m;
            if (arrayList.size() > 0) {
                arrayList.get(0).getClass();
                throw new ClassCastException();
            }
        }
        return true;
    }

    @Override // android.view.Window.Callback
    public final void onPanelClosed(int i, Menu menu) {
        if (this.e) {
            this.b.onPanelClosed(i, menu);
            return;
        }
        c(i, menu);
        x4 x4Var = this.f;
        if (i != 108) {
            if (i != 0) {
                x4Var.getClass();
                return;
            }
            w4 w4VarZ = x4Var.z(i);
            if (w4VarZ.m) {
                x4Var.s(w4VarZ, false);
                return;
            }
            return;
        }
        x4Var.A();
        bu0 bu0Var = x4Var.p;
        if (bu0Var == null || !bu0Var.l) {
            return;
        }
        bu0Var.l = false;
        ArrayList arrayList = bu0Var.m;
        if (arrayList.size() <= 0) {
            return;
        }
        arrayList.get(0).getClass();
        throw new ClassCastException();
    }

    @Override // android.view.Window.Callback
    public final void onPointerCaptureChanged(boolean z) {
        xt0.a(this.b, z);
    }

    @Override // android.view.Window.Callback
    public final boolean onPreparePanel(int i, View view, Menu menu) {
        i60 i60Var = menu instanceof i60 ? (i60) menu : null;
        if (i == 0 && i60Var == null) {
            return false;
        }
        if (i60Var != null) {
            i60Var.x = true;
        }
        boolean zOnPreparePanel = this.b.onPreparePanel(i, view, menu);
        if (i60Var != null) {
            i60Var.x = false;
        }
        return zOnPreparePanel;
    }

    @Override // android.view.Window.Callback
    public final void onProvideKeyboardShortcuts(List list, Menu menu, int i) {
        i60 i60Var = this.f.z(0).h;
        if (i60Var != null) {
            d(list, i60Var, i);
        } else {
            d(list, menu, i);
        }
    }

    @Override // android.view.Window.Callback
    public final boolean onSearchRequested(SearchEvent searchEvent) {
        return vt0.a(this.b, searchEvent);
    }

    @Override // android.view.Window.Callback
    public final void onWindowAttributesChanged(WindowManager.LayoutParams layoutParams) {
        this.b.onWindowAttributesChanged(layoutParams);
    }

    @Override // android.view.Window.Callback
    public final void onWindowFocusChanged(boolean z) {
        this.b.onWindowFocusChanged(z);
    }

    /* JADX WARN: Removed duplicated region for block: B:64:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01a3  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x01e2  */
    @Override // android.view.Window.Callback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.view.ActionMode onWindowStartingActionMode(android.view.ActionMode.Callback r10, int r11) {
        /*
            Method dump skipped, instruction units count: 516
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.r4.onWindowStartingActionMode(android.view.ActionMode$Callback, int):android.view.ActionMode");
    }

    @Override // android.view.Window.Callback
    public final boolean onSearchRequested() {
        return this.b.onSearchRequested();
    }

    @Override // android.view.Window.Callback
    public final ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        return null;
    }
}
