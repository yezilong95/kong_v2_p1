package defpackage;

import android.window.BackEvent;

/* JADX INFO: loaded from: classes.dex */
public final class w3 {
    public static final w3 a = new w3();

    public final BackEvent a(float f, float f2, float f3, int i) {
        return new BackEvent(f, f2, f3, i);
    }

    public final float b(BackEvent backEvent) {
        oy.j(backEvent, "backEvent");
        return backEvent.getProgress();
    }

    public final int c(BackEvent backEvent) {
        oy.j(backEvent, "backEvent");
        return backEvent.getSwipeEdge();
    }

    public final float d(BackEvent backEvent) {
        oy.j(backEvent, "backEvent");
        return backEvent.getTouchX();
    }

    public final float e(BackEvent backEvent) {
        oy.j(backEvent, "backEvent");
        return backEvent.getTouchY();
    }
}
