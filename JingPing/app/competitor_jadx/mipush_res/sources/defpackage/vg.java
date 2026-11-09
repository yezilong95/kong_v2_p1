package defpackage;

import android.app.Activity;
import android.window.OnBackInvokedDispatcher;

/* JADX INFO: loaded from: classes.dex */
public abstract class vg {
    public static OnBackInvokedDispatcher a(Activity activity) {
        return activity.getOnBackInvokedDispatcher();
    }
}
