package defpackage;

import android.os.Looper;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.activity.ComponentActivity;
import androidx.work.WorkRequest;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class xg implements Executor, ViewTreeObserver.OnDrawListener, Runnable {
    public Runnable F;
    public final /* synthetic */ ComponentActivity H;
    public final long E = SystemClock.uptimeMillis() + WorkRequest.MIN_BACKOFF_MILLIS;
    public boolean G = false;

    public xg(ComponentActivity componentActivity) {
        this.H = componentActivity;
    }

    public final void a(View view) {
        if (this.G) {
            return;
        }
        this.G = true;
        view.getViewTreeObserver().addOnDrawListener(this);
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        this.F = runnable;
        View decorView = this.H.getWindow().getDecorView();
        if (!this.G) {
            decorView.postOnAnimation(new e(9, this));
        } else if (Looper.myLooper() == Looper.getMainLooper()) {
            decorView.invalidate();
        } else {
            decorView.postInvalidate();
        }
    }

    @Override // android.view.ViewTreeObserver.OnDrawListener
    public final void onDraw() {
        boolean z;
        Runnable runnable = this.F;
        if (runnable == null) {
            if (SystemClock.uptimeMillis() > this.E) {
                this.G = false;
                this.H.getWindow().getDecorView().post(this);
                return;
            }
            return;
        }
        runnable.run();
        this.F = null;
        z00 z00Var = this.H.M;
        synchronized (z00Var.a) {
            z = z00Var.b;
        }
        if (z) {
            this.G = false;
            this.H.getWindow().getDecorView().post(this);
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.H.getWindow().getDecorView().getViewTreeObserver().removeOnDrawListener(this);
    }
}
