package defpackage;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class dz implements ys {
    public final Context E;
    public final c4 F;
    public final di G;
    public final Object H;
    public Handler I;
    public ThreadPoolExecutor J;
    public ThreadPoolExecutor K;
    public z91 L;

    public dz(Context context, c4 c4Var) {
        di diVar = ez.d;
        this.H = new Object();
        la0.i(context, "Context cannot be null");
        this.E = context.getApplicationContext();
        this.F = c4Var;
        this.G = diVar;
    }

    public final void a() {
        synchronized (this.H) {
            try {
                this.L = null;
                Handler handler = this.I;
                if (handler != null) {
                    handler.removeCallbacks(null);
                }
                this.I = null;
                ThreadPoolExecutor threadPoolExecutor = this.K;
                if (threadPoolExecutor != null) {
                    threadPoolExecutor.shutdown();
                }
                this.J = null;
                this.K = null;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final oz b() {
        try {
            di diVar = this.G;
            Context context = this.E;
            c4 c4Var = this.F;
            diVar.getClass();
            j4 j4VarO = l21.O(context, c4Var);
            int i = j4VarO.E;
            if (i != 0) {
                throw new RuntimeException(zn.k(i, "fetchFonts failed (", ")"));
            }
            oz[] ozVarArr = (oz[]) j4VarO.F;
            if (ozVarArr == null || ozVarArr.length == 0) {
                throw new RuntimeException("fetchFonts failed (empty result)");
            }
            return ozVarArr[0];
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("provider not found", e);
        }
    }

    @Override // defpackage.ys
    public final void k(z91 z91Var) {
        synchronized (this.H) {
            this.L = z91Var;
        }
        synchronized (this.H) {
            try {
                if (this.L == null) {
                    return;
                }
                if (this.J == null) {
                    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 15L, TimeUnit.SECONDS, new LinkedBlockingDeque(), new z7("emojiCompat", 1));
                    threadPoolExecutor.allowCoreThreadTimeOut(true);
                    this.K = threadPoolExecutor;
                    this.J = threadPoolExecutor;
                }
                this.J.execute(new e(18, this));
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
