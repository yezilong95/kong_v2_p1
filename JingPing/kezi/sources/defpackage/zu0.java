package defpackage;

import android.view.Window;

/* JADX INFO: loaded from: classes.dex */
public class zu0 extends yu0 {
    @Override // defpackage.cv0
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
