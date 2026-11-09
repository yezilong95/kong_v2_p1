package defpackage;

import android.view.Window;

/* JADX INFO: loaded from: classes.dex */
public class u81 extends t81 {
    @Override // defpackage.x81
    public final void c(boolean z) {
        if (!z) {
            f(8192);
            return;
        }
        Window window = this.a;
        window.clearFlags(67108864);
        window.addFlags(Integer.MIN_VALUE);
        e(8192);
    }
}
