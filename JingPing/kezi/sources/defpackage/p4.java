package defpackage;

import android.window.OnBackInvokedCallback;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class p4 implements OnBackInvokedCallback {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ p4(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    public final void onBackInvoked() {
        switch (this.a) {
            case 0:
                ((x4) this.b).D();
                break;
            case 1:
                ((w40) this.b).b();
                break;
            case 2:
                ((ou) this.b).invoke();
                break;
            default:
                ((Runnable) this.b).run();
                break;
        }
    }
}
