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
public final class w5 implements Window.Callback {
    public final Window.Callback E;
    public boolean F;
    public boolean G;
    public boolean H;
    public final /* synthetic */ c6 I;

    public w5(c6 c6Var, Window.Callback callback) {
        this.I = c6Var;
        if (callback == null) {
            throw new IllegalArgumentException("Window callback may not be null");
        }
        this.E = callback;
    }

    public final void a(Window.Callback callback) {
        try {
            this.F = true;
            callback.onContentChanged();
        } finally {
            this.F = false;
        }
    }

    public final boolean b(int i, Menu menu) {
        return this.E.onMenuOpened(i, menu);
    }

    public final void c(int i, Menu menu) {
        this.E.onPanelClosed(i, menu);
    }

    public final void d(List list, Menu menu, int i) {
        s71.a(this.E, list, menu, i);
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        return this.E.dispatchGenericMotionEvent(motionEvent);
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        boolean z = this.G;
        Window.Callback callback = this.E;
        return z ? callback.dispatchKeyEvent(keyEvent) : this.I.u(keyEvent) || callback.dispatchKeyEvent(keyEvent);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x006e A[RETURN] */
    @Override // android.view.Window.Callback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean dispatchKeyShortcutEvent(android.view.KeyEvent r7) {
        /*
            r6 = this;
            android.view.Window$Callback r0 = r6.E
            boolean r0 = r0.dispatchKeyShortcutEvent(r7)
            r1 = 1
            if (r0 != 0) goto L6f
            int r0 = r7.getKeyCode()
            c6 r2 = r6.I
            r2.A()
            x71 r3 = r2.S
            r4 = 0
            if (r3 == 0) goto L3d
            w71 r3 = r3.i
            if (r3 != 0) goto L1d
        L1b:
            r0 = r4
            goto L39
        L1d:
            zd0 r3 = r3.H
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
            b6 r0 = r2.q0
            if (r0 == 0) goto L52
            int r3 = r7.getKeyCode()
            boolean r0 = r2.F(r0, r3, r7)
            if (r0 == 0) goto L52
            b6 r7 = r2.q0
            if (r7 == 0) goto L3b
            r7.l = r1
            goto L3b
        L52:
            b6 r0 = r2.q0
            if (r0 != 0) goto L6a
            b6 r0 = r2.z(r4)
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
        throw new UnsupportedOperationException("Method not decompiled: defpackage.w5.dispatchKeyShortcutEvent(android.view.KeyEvent):boolean");
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return this.E.dispatchPopulateAccessibilityEvent(accessibilityEvent);
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return this.E.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        return this.E.dispatchTrackballEvent(motionEvent);
    }

    @Override // android.view.Window.Callback
    public final void onActionModeFinished(ActionMode actionMode) {
        this.E.onActionModeFinished(actionMode);
    }

    @Override // android.view.Window.Callback
    public final void onActionModeStarted(ActionMode actionMode) {
        this.E.onActionModeStarted(actionMode);
    }

    @Override // android.view.Window.Callback
    public final void onAttachedToWindow() {
        this.E.onAttachedToWindow();
    }

    @Override // android.view.Window.Callback
    public final void onContentChanged() {
        if (this.F) {
            this.E.onContentChanged();
        }
    }

    @Override // android.view.Window.Callback
    public final boolean onCreatePanelMenu(int i, Menu menu) {
        if (i != 0 || (menu instanceof zd0)) {
            return this.E.onCreatePanelMenu(i, menu);
        }
        return false;
    }

    @Override // android.view.Window.Callback
    public final View onCreatePanelView(int i) {
        return this.E.onCreatePanelView(i);
    }

    @Override // android.view.Window.Callback
    public final void onDetachedFromWindow() {
        this.E.onDetachedFromWindow();
    }

    @Override // android.view.Window.Callback
    public final boolean onMenuItemSelected(int i, MenuItem menuItem) {
        return this.E.onMenuItemSelected(i, menuItem);
    }

    @Override // android.view.Window.Callback
    public final boolean onMenuOpened(int i, Menu menu) {
        b(i, menu);
        c6 c6Var = this.I;
        if (i != 108) {
            c6Var.getClass();
            return true;
        }
        c6Var.A();
        x71 x71Var = c6Var.S;
        if (x71Var != null && true != x71Var.l) {
            x71Var.l = true;
            ArrayList arrayList = x71Var.m;
            if (arrayList.size() > 0) {
                arrayList.get(0).getClass();
                throw new ClassCastException();
            }
        }
        return true;
    }

    @Override // android.view.Window.Callback
    public final void onPanelClosed(int i, Menu menu) {
        if (this.H) {
            this.E.onPanelClosed(i, menu);
            return;
        }
        c(i, menu);
        c6 c6Var = this.I;
        if (i != 108) {
            if (i != 0) {
                c6Var.getClass();
                return;
            }
            b6 b6VarZ = c6Var.z(i);
            if (b6VarZ.m) {
                c6Var.s(b6VarZ, false);
                return;
            }
            return;
        }
        c6Var.A();
        x71 x71Var = c6Var.S;
        if (x71Var == null || !x71Var.l) {
            return;
        }
        x71Var.l = false;
        ArrayList arrayList = x71Var.m;
        if (arrayList.size() <= 0) {
            return;
        }
        arrayList.get(0).getClass();
        throw new ClassCastException();
    }

    @Override // android.view.Window.Callback
    public final void onPointerCaptureChanged(boolean z) {
        t71.a(this.E, z);
    }

    @Override // android.view.Window.Callback
    public final boolean onPreparePanel(int i, View view, Menu menu) {
        zd0 zd0Var = menu instanceof zd0 ? (zd0) menu : null;
        if (i == 0 && zd0Var == null) {
            return false;
        }
        if (zd0Var != null) {
            zd0Var.x = true;
        }
        boolean zOnPreparePanel = this.E.onPreparePanel(i, view, menu);
        if (zd0Var != null) {
            zd0Var.x = false;
        }
        return zOnPreparePanel;
    }

    @Override // android.view.Window.Callback
    public final void onProvideKeyboardShortcuts(List list, Menu menu, int i) {
        zd0 zd0Var = this.I.z(0).h;
        if (zd0Var != null) {
            d(list, zd0Var, i);
        } else {
            d(list, menu, i);
        }
    }

    @Override // android.view.Window.Callback
    public final boolean onSearchRequested(SearchEvent searchEvent) {
        return r71.a(this.E, searchEvent);
    }

    @Override // android.view.Window.Callback
    public final void onWindowAttributesChanged(WindowManager.LayoutParams layoutParams) {
        this.E.onWindowAttributesChanged(layoutParams);
    }

    @Override // android.view.Window.Callback
    public final void onWindowFocusChanged(boolean z) {
        this.E.onWindowFocusChanged(z);
    }

    /* JADX WARN: Removed duplicated region for block: B:64:0x019f  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01a4  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x01e3  */
    @Override // android.view.Window.Callback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.view.ActionMode onWindowStartingActionMode(android.view.ActionMode.Callback r9, int r10) {
        /*
            Method dump skipped, instruction units count: 517
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.w5.onWindowStartingActionMode(android.view.ActionMode$Callback, int):android.view.ActionMode");
    }

    @Override // android.view.Window.Callback
    public final boolean onSearchRequested() {
        return this.E.onSearchRequested();
    }

    @Override // android.view.Window.Callback
    public final ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        return null;
    }
}
