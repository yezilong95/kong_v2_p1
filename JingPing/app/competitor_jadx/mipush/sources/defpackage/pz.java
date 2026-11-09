package defpackage;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;

/* JADX INFO: loaded from: classes.dex */
public final class pz implements Runnable {
    public final /* synthetic */ int E;
    public final /* synthetic */ qz F;

    public /* synthetic */ pz(qz qzVar, int i) {
        this.E = i;
        this.F = qzVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.E) {
            case 0:
                ViewParent parent = this.F.H.getParent();
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(true);
                }
                break;
            default:
                qz qzVar = this.F;
                qzVar.a();
                View view = qzVar.H;
                if (view.isEnabled() && !view.isLongClickable() && qzVar.c()) {
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                    long jUptimeMillis = SystemClock.uptimeMillis();
                    MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
                    view.onTouchEvent(motionEventObtain);
                    motionEventObtain.recycle();
                    qzVar.K = true;
                    break;
                }
                break;
        }
    }
}
