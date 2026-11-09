package defpackage;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;
import androidx.constraintlayout.utils.widget.ImageFilterView;

/* JADX INFO: loaded from: classes.dex */
public final class p30 extends ViewOutlineProvider {
    public final /* synthetic */ int a;
    public final /* synthetic */ ImageFilterView b;

    public /* synthetic */ p30(ImageFilterView imageFilterView, int i) {
        this.a = i;
        this.b = imageFilterView;
    }

    @Override // android.view.ViewOutlineProvider
    public final void getOutline(View view, Outline outline) {
        switch (this.a) {
            case 0:
                ImageFilterView imageFilterView = this.b;
                outline.setRoundRect(0, 0, imageFilterView.getWidth(), imageFilterView.getHeight(), (Math.min(r9, r10) * imageFilterView.K) / 2.0f);
                break;
            default:
                ImageFilterView imageFilterView2 = this.b;
                outline.setRoundRect(0, 0, imageFilterView2.getWidth(), imageFilterView2.getHeight(), imageFilterView2.L);
                break;
        }
    }
}
