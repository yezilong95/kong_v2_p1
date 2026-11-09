package defpackage;

import android.content.Context;
import android.content.ContextWrapper;

/* JADX INFO: loaded from: classes.dex */
public abstract class w01 extends ContextWrapper {
    public static final Object a = null;

    public static void a(Context context) {
        if (context.getResources() instanceof y01) {
            return;
        }
        context.getResources();
        int i = b41.a;
    }
}
