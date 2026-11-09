package defpackage;

import android.os.Handler;
import android.os.Message;

/* JADX INFO: loaded from: classes.dex */
public final class bw0 implements Handler.Callback {
    public final /* synthetic */ cw0 a;

    public bw0(cw0 cw0Var) {
        this.a = cw0Var;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        if (message.what != 0) {
            return false;
        }
        cw0 cw0Var = this.a;
        if (message.obj != null) {
            throw new ClassCastException();
        }
        synchronized (cw0Var.F) {
            throw null;
        }
    }
}
