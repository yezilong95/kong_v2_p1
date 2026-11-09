package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.HandlerThread;

/* JADX INFO: loaded from: classes.dex */
public final class w7 {
    public final HandlerThread a;
    public final Handler b;
    public final t7 e;
    public volatile boolean c = false;
    public volatile boolean d = false;
    public final f g = new f(2, this);
    public final m2 f = new m2();

    public w7(Context context) {
        this.e = new t7(context);
        HandlerThread handlerThread = new HandlerThread("capture-thread");
        this.a = handlerThread;
        handlerThread.start();
        this.b = new Handler(handlerThread.getLooper());
    }

    public final void a() {
        try {
            if (this.c) {
                this.c = false;
                this.b.removeCallbacksAndMessages(null);
            }
            m2 m2Var = this.f;
            if (m2Var != null) {
                m2Var.e();
                m2Var.e();
                m2Var.a = null;
                m2Var.b = null;
                m2Var.e = null;
                m2Var.f = null;
            }
            t7 t7Var = this.e;
            if (t7Var != null) {
                Bitmap bitmap = t7Var.a;
                if (bitmap != null) {
                    bitmap.recycle();
                    t7Var.a = null;
                }
                t7Var.m.clear();
                t7Var.b = null;
                t7Var.c = null;
                t7Var.d = null;
                t7Var.e = null;
                t7Var.k = null;
                t7Var.f = null;
                t7Var.g = null;
                t7Var.h = null;
                t7Var.i = null;
            }
            HandlerThread handlerThread = this.a;
            if (handlerThread != null) {
                handlerThread.quitSafely();
            }
        } catch (Exception unused) {
        }
    }

    public final synchronized void b() {
        if (this.d) {
            try {
                this.f.e();
            } catch (Exception unused) {
            }
            this.d = false;
            if (this.c) {
                this.c = false;
                this.b.removeCallbacksAndMessages(null);
            }
        }
    }
}
