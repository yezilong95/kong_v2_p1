package defpackage;

import android.window.OnBackInvokedCallback;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class u5 implements OnBackInvokedCallback {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ u5(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    public final void onBackInvoked() {
        switch (this.a) {
            case 0:
                ((c6) this.b).D();
                break;
            case 1:
                ((mc0) this.b).a();
                break;
            case 2:
                ((a10) this.b).invoke();
                break;
            default:
                ((Runnable) this.b).run();
                break;
        }
    }
}
