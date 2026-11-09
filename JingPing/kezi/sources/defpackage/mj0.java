package defpackage;

import android.os.Handler;
import android.os.Message;

/* JADX INFO: loaded from: classes.dex */
public final class mj0 implements Handler.Callback {
    public final /* synthetic */ g2 a;

    public mj0(g2 g2Var) {
        this.a = g2Var;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        if (message.what != 0) {
            return false;
        }
        g2 g2Var = this.a;
        if (message.obj != null) {
            throw new ClassCastException();
        }
        synchronized (g2Var.a) {
            throw null;
        }
    }
}
