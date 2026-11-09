package defpackage;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;

/* JADX INFO: loaded from: classes.dex */
public final class bt implements Runnable {
    public final /* synthetic */ int b;
    public final /* synthetic */ ct c;

    public /* synthetic */ bt(ct ctVar, int i) {
        this.b = i;
        this.c = ctVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.b) {
            case 0:
                ViewParent parent = this.c.e.getParent();
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(true);
                }
                break;
            default:
                ct ctVar = this.c;
                ctVar.a();
                View view = ctVar.e;
                if (view.isEnabled() && !view.isLongClickable() && ctVar.c()) {
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                    long jUptimeMillis = SystemClock.uptimeMillis();
                    MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
                    view.onTouchEvent(motionEventObtain);
                    motionEventObtain.recycle();
                    ctVar.h = true;
                    break;
                }
                break;
        }
    }
}
