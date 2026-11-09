package defpackage;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class r00 extends w00 {
    public final /* synthetic */ int d;

    @Override // defpackage.w00
    public final void c(View view, float f) {
        switch (this.d) {
            case 0:
                view.setAlpha(a(f));
                break;
            case 1:
                view.setElevation(a(f));
                break;
            case 2:
                view.setRotation(a(f));
                break;
            case 3:
                view.setRotationX(a(f));
                break;
            case 4:
                view.setRotationY(a(f));
                break;
            case 5:
                view.setScaleX(a(f));
                break;
            case 6:
                view.setScaleY(a(f));
                break;
            case 7:
                view.setTranslationX(a(f));
                break;
            case 8:
                view.setTranslationY(a(f));
                break;
            default:
                view.setTranslationZ(a(f));
                break;
        }
    }
}
