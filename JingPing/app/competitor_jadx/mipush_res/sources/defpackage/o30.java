package defpackage;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;
import androidx.constraintlayout.utils.widget.ImageFilterButton;

/* JADX INFO: loaded from: classes.dex */
public final class o30 extends ViewOutlineProvider {
    public final /* synthetic */ int a;
    public final /* synthetic */ ImageFilterButton b;

    public /* synthetic */ o30(ImageFilterButton imageFilterButton, int i) {
        this.a = i;
        this.b = imageFilterButton;
    }

    @Override // android.view.ViewOutlineProvider
    public final void getOutline(View view, Outline outline) {
        switch (this.a) {
            case 0:
                ImageFilterButton imageFilterButton = this.b;
                outline.setRoundRect(0, 0, imageFilterButton.getWidth(), imageFilterButton.getHeight(), (Math.min(r9, r10) * imageFilterButton.J) / 2.0f);
                break;
            default:
                ImageFilterButton imageFilterButton2 = this.b;
                outline.setRoundRect(0, 0, imageFilterButton2.getWidth(), imageFilterButton2.getHeight(), imageFilterButton2.K);
                break;
        }
    }
}
