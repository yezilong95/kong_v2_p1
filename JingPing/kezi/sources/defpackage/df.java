package defpackage;

import android.os.Looper;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.activity.ComponentActivity;
import androidx.work.WorkRequest;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class df implements Executor, ViewTreeObserver.OnDrawListener, Runnable {
    public Runnable c;
    public final /* synthetic */ ComponentActivity e;
    public final long b = SystemClock.uptimeMillis() + WorkRequest.MIN_BACKOFF_MILLIS;
    public boolean d = false;

    public df(ComponentActivity componentActivity) {
        this.e = componentActivity;
    }

    public final void a(View view) {
        if (this.d) {
            return;
        }
        this.d = true;
        view.getViewTreeObserver().addOnDrawListener(this);
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        this.c = runnable;
        View decorView = this.e.getWindow().getDecorView();
        if (!this.d) {
            decorView.postOnAnimation(new z1(3, this));
        } else if (Looper.myLooper() == Looper.getMainLooper()) {
            decorView.invalidate();
        } else {
            decorView.postInvalidate();
        }
    }

    @Override // android.view.ViewTreeObserver.OnDrawListener
    public final void onDraw() {
        boolean z;
        Runnable runnable = this.c;
        if (runnable == null) {
            if (SystemClock.uptimeMillis() > this.b) {
                this.d = false;
                this.e.getWindow().getDecorView().post(this);
                return;
            }
            return;
        }
        runnable.run();
        this.c = null;
        nu nuVar = this.e.k;
        synchronized (nuVar.a) {
            z = nuVar.b;
        }
        if (z) {
            this.d = false;
            this.e.getWindow().getDecorView().post(this);
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.e.getWindow().getDecorView().getViewTreeObserver().removeOnDrawListener(this);
    }
}
