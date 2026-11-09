package defpackage;

import androidx.appcompat.widget.ActionBarOverlayLayout;

/* JADX INFO: loaded from: classes.dex */
public final class e1 implements Runnable {
    public final /* synthetic */ int b;
    public final /* synthetic */ ActionBarOverlayLayout c;

    public /* synthetic */ e1(ActionBarOverlayLayout actionBarOverlayLayout, int i) {
        this.b = i;
        this.c = actionBarOverlayLayout;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.b) {
            case 0:
                ActionBarOverlayLayout actionBarOverlayLayout = this.c;
                actionBarOverlayLayout.d();
                actionBarOverlayLayout.x = actionBarOverlayLayout.e.animate().translationY(0.0f).setListener(actionBarOverlayLayout.y);
                break;
            default:
                ActionBarOverlayLayout actionBarOverlayLayout2 = this.c;
                actionBarOverlayLayout2.d();
                actionBarOverlayLayout2.x = actionBarOverlayLayout2.e.animate().translationY(-actionBarOverlayLayout2.e.getHeight()).setListener(actionBarOverlayLayout2.y);
                break;
        }
    }
}
