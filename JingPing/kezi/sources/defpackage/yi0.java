package defpackage;

import android.view.View;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
public final class yi0 extends wi0 {
    public yi0(FrameLayout frameLayout) {
        c(frameLayout);
    }

    private void c(View view) {
        view.setOutlineProvider(new jc(2, this));
    }

    @Override // defpackage.wi0
    public final void a(FrameLayout frameLayout) {
        frameLayout.setClipToOutline(!this.a);
        if (this.a) {
            frameLayout.invalidate();
        } else {
            frameLayout.invalidateOutline();
        }
    }

    @Override // defpackage.wi0
    public final boolean b() {
        return this.a;
    }
}
