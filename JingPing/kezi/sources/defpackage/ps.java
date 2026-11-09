package defpackage;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class ps implements gn {
    public final Context a;
    public final o2 b;
    public final yo c;
    public final Object d;
    public Handler e;
    public ThreadPoolExecutor f;
    public ThreadPoolExecutor g;
    public za0 h;

    public ps(Context context, o2 o2Var) {
        yo yoVar = qs.d;
        this.d = new Object();
        k9.h(context, "Context cannot be null");
        this.a = context.getApplicationContext();
        this.b = o2Var;
        this.c = yoVar;
    }

    @Override // defpackage.gn
    public final void a(za0 za0Var) {
        synchronized (this.d) {
            this.h = za0Var;
        }
        synchronized (this.d) {
            try {
                if (this.h == null) {
                    return;
                }
                if (this.f == null) {
                    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 15L, TimeUnit.SECONDS, new LinkedBlockingDeque(), new gf("emojiCompat"));
                    threadPoolExecutor.allowCoreThreadTimeOut(true);
                    this.g = threadPoolExecutor;
                    this.f = threadPoolExecutor;
                }
                this.f.execute(new z1(12, this));
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void b() {
        synchronized (this.d) {
            try {
                this.h = null;
                Handler handler = this.e;
                if (handler != null) {
                    handler.removeCallbacks(null);
                }
                this.e = null;
                ThreadPoolExecutor threadPoolExecutor = this.g;
                if (threadPoolExecutor != null) {
                    threadPoolExecutor.shutdown();
                }
                this.f = null;
                this.g = null;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final at c() {
        try {
            yo yoVar = this.c;
            Context context = this.a;
            o2 o2Var = this.b;
            yoVar.getClass();
            w2 w2VarV = jq0.v(context, o2Var);
            int i = w2VarV.b;
            if (i != 0) {
                throw new RuntimeException(hk.h(i, "fetchFonts failed (", ")"));
            }
            at[] atVarArr = (at[]) w2VarV.c;
            if (atVarArr == null || atVarArr.length == 0) {
                throw new RuntimeException("fetchFonts failed (empty result)");
            }
            return atVarArr[0];
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("provider not found", e);
        }
    }
}
