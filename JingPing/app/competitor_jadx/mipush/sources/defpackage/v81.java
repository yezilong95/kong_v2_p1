package defpackage;

import android.view.Window;

/* JADX INFO: loaded from: classes.dex */
public final class v81 extends u81 {
    @Override // defpackage.x81
    public final void b(boolean z) {
        if (!z) {
            f(16);
            return;
        }
        Window window = this.a;
        window.clearFlags(134217728);
        window.addFlags(Integer.MIN_VALUE);
        e(16);
    }
}
