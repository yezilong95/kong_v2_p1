package defpackage;

import android.view.WindowInsetsController;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class fw0 implements WindowInsetsController.OnControllableInsetsChangedListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ fw0(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // android.view.WindowInsetsController.OnControllableInsetsChangedListener
    public final void onControllableInsetsChanged(WindowInsetsController windowInsetsController, int i) {
        switch (this.a) {
            case 0:
                ((AtomicBoolean) this.b).set((i & 8) != 0);
                return;
            default:
                if (((w81) this.b).a == windowInsetsController) {
                    throw null;
                }
                return;
        }
    }
}
