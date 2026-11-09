package defpackage;

import android.window.BackEvent;

/* JADX INFO: loaded from: classes.dex */
public final class a5 {
    public static final a5 a = new a5();

    public final BackEvent a(float f, float f2, float f3, int i) {
        return new BackEvent(f, f2, f3, i);
    }

    public final float b(BackEvent backEvent) {
        t40.n(backEvent, "backEvent");
        return backEvent.getProgress();
    }

    public final int c(BackEvent backEvent) {
        t40.n(backEvent, "backEvent");
        return backEvent.getSwipeEdge();
    }

    public final float d(BackEvent backEvent) {
        t40.n(backEvent, "backEvent");
        return backEvent.getTouchX();
    }

    public final float e(BackEvent backEvent) {
        t40.n(backEvent, "backEvent");
        return backEvent.getTouchY();
    }
}
