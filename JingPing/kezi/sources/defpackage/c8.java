package defpackage;

import android.os.SystemClock;
import com.google.android.material.progressindicator.BaseProgressIndicator;

/* JADX INFO: loaded from: classes.dex */
public final class c8 implements Runnable {
    public final /* synthetic */ int b;
    public final /* synthetic */ BaseProgressIndicator c;

    public /* synthetic */ c8(BaseProgressIndicator baseProgressIndicator, int i) {
        this.b = i;
        this.c = baseProgressIndicator;
    }

    @Override // java.lang.Runnable
    public final void run() {
        BaseProgressIndicator baseProgressIndicator = this.c;
        switch (this.b) {
            case 0:
                int i = BaseProgressIndicator.m;
                if (baseProgressIndicator.e > 0) {
                    SystemClock.uptimeMillis();
                }
                baseProgressIndicator.setVisibility(0);
                break;
            default:
                int i2 = BaseProgressIndicator.m;
                ((nl) baseProgressIndicator.getCurrentDrawable()).e(false, false, true);
                if ((baseProgressIndicator.getProgressDrawable() == null || !baseProgressIndicator.getProgressDrawable().isVisible()) && (baseProgressIndicator.getIndeterminateDrawable() == null || !baseProgressIndicator.getIndeterminateDrawable().isVisible())) {
                    baseProgressIndicator.setVisibility(4);
                }
                baseProgressIndicator.getClass();
                break;
        }
    }
}
