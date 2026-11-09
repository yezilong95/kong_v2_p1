package defpackage;

import android.view.View;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
public final class nv0 extends lv0 {
    public nv0(FrameLayout frameLayout) {
        d(frameLayout);
    }

    private void d(View view) {
        view.setOutlineProvider(new ee(2, this));
    }

    @Override // defpackage.lv0
    public final void a(FrameLayout frameLayout) {
        frameLayout.setClipToOutline(!this.a);
        if (this.a) {
            frameLayout.invalidate();
        } else {
            frameLayout.invalidateOutline();
        }
    }

    @Override // defpackage.lv0
    public final boolean b() {
        return this.a;
    }
}
