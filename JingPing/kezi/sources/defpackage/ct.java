package defpackage;

import android.view.View;
import android.view.ViewConfiguration;

/* JADX INFO: loaded from: classes.dex */
public abstract class ct implements View.OnTouchListener, View.OnAttachStateChangeListener {
    public final float b;
    public final int c;
    public final int d;
    public final View e;
    public bt f;
    public bt g;
    public boolean h;
    public int i;
    public final int[] j = new int[2];

    public ct(View view) {
        this.e = view;
        view.setLongClickable(true);
        view.addOnAttachStateChangeListener(this);
        this.b = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
        int tapTimeout = ViewConfiguration.getTapTimeout();
        this.c = tapTimeout;
        this.d = (ViewConfiguration.getLongPressTimeout() + tapTimeout) / 2;
    }

    public final void a() {
        bt btVar = this.g;
        View view = this.e;
        if (btVar != null) {
            view.removeCallbacks(btVar);
        }
        bt btVar2 = this.f;
        if (btVar2 != null) {
            view.removeCallbacks(btVar2);
        }
    }

    public abstract bj0 b();

    public abstract boolean c();

    public boolean d() {
        bj0 bj0VarB = b();
        if (bj0VarB == null || !bj0VarB.b()) {
            return true;
        }
        bj0VarB.dismiss();
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0100  */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onTouch(android.view.View r13, android.view.MotionEvent r14) {
        /*
            Method dump skipped, instruction units count: 284
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ct.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        this.h = false;
        this.i = -1;
        bt btVar = this.f;
        if (btVar != null) {
            this.e.removeCallbacks(btVar);
        }
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
    }
}
