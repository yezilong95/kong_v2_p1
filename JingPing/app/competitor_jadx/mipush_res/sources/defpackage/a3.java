package defpackage;

import androidx.appcompat.widget.ActionBarOverlayLayout;

/* JADX INFO: loaded from: classes.dex */
public final class a3 implements Runnable {
    public final /* synthetic */ int E;
    public final /* synthetic */ ActionBarOverlayLayout F;

    public /* synthetic */ a3(ActionBarOverlayLayout actionBarOverlayLayout, int i) {
        this.E = i;
        this.F = actionBarOverlayLayout;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.E) {
            case 0:
                ActionBarOverlayLayout actionBarOverlayLayout = this.F;
                actionBarOverlayLayout.e();
                actionBarOverlayLayout.d0 = actionBarOverlayLayout.H.animate().translationY(0.0f).setListener(actionBarOverlayLayout.e0);
                break;
            default:
                ActionBarOverlayLayout actionBarOverlayLayout2 = this.F;
                actionBarOverlayLayout2.e();
                actionBarOverlayLayout2.d0 = actionBarOverlayLayout2.H.animate().translationY(-actionBarOverlayLayout2.H.getHeight()).setListener(actionBarOverlayLayout2.e0);
                break;
        }
    }
}
