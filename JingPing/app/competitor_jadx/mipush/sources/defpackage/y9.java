package defpackage;

import android.os.SystemClock;
import com.google.android.material.progressindicator.BaseProgressIndicator;

/* JADX INFO: loaded from: classes.dex */
public final class y9 implements Runnable {
    public final /* synthetic */ int E;
    public final /* synthetic */ BaseProgressIndicator F;

    public /* synthetic */ y9(BaseProgressIndicator baseProgressIndicator, int i) {
        this.E = i;
        this.F = baseProgressIndicator;
    }

    @Override // java.lang.Runnable
    public final void run() {
        BaseProgressIndicator baseProgressIndicator = this.F;
        switch (this.E) {
            case 0:
                int i = BaseProgressIndicator.P;
                if (baseProgressIndicator.H > 0) {
                    SystemClock.uptimeMillis();
                }
                baseProgressIndicator.setVisibility(0);
                break;
            default:
                int i2 = BaseProgressIndicator.P;
                ((qp) baseProgressIndicator.getCurrentDrawable()).c(false, false, true);
                if ((baseProgressIndicator.getProgressDrawable() == null || !baseProgressIndicator.getProgressDrawable().isVisible()) && (baseProgressIndicator.getIndeterminateDrawable() == null || !baseProgressIndicator.getIndeterminateDrawable().isVisible())) {
                    baseProgressIndicator.setVisibility(4);
                }
                baseProgressIndicator.getClass();
                break;
        }
    }
}
