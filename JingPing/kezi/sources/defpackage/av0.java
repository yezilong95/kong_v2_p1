package defpackage;

import android.view.Window;

/* JADX INFO: loaded from: classes.dex */
public final class av0 extends zu0 {
    @Override // defpackage.cv0
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
