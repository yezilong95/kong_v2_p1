package defpackage;

import android.view.View;
import android.view.ViewConfiguration;

/* JADX INFO: loaded from: classes.dex */
public abstract class qz implements View.OnTouchListener, View.OnAttachStateChangeListener {
    public final float E;
    public final int F;
    public final int G;
    public final View H;
    public pz I;
    public pz J;
    public boolean K;
    public int L;
    public final int[] M = new int[2];

    public qz(View view) {
        this.H = view;
        view.setLongClickable(true);
        view.addOnAttachStateChangeListener(this);
        this.E = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
        int tapTimeout = ViewConfiguration.getTapTimeout();
        this.F = tapTimeout;
        this.G = (ViewConfiguration.getLongPressTimeout() + tapTimeout) / 2;
    }

    public final void a() {
        pz pzVar = this.J;
        View view = this.H;
        if (pzVar != null) {
            view.removeCallbacks(pzVar);
        }
        pz pzVar2 = this.I;
        if (pzVar2 != null) {
            view.removeCallbacks(pzVar2);
        }
    }

    public abstract qv0 b();

    public abstract boolean c();

    public boolean d() {
        qv0 qv0VarB = b();
        if (qv0VarB == null || !qv0VarB.b()) {
            return true;
        }
        qv0VarB.dismiss();
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0100  */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onTouch(android.view.View r13, android.view.MotionEvent r14) {
        /*
            Method dump skipped, instruction units count: 284
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.qz.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        this.K = false;
        this.L = -1;
        pz pzVar = this.I;
        if (pzVar != null) {
            this.H.removeCallbacks(pzVar);
        }
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
    }
}
