package defpackage;

import android.os.Handler;
import android.os.Looper;

/* JADX INFO: loaded from: classes.dex */
public abstract class hf {
    public static Handler a(Looper looper) {
        return Handler.createAsync(looper);
    }
}
