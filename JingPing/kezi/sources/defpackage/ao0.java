package defpackage;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class ao0 extends eo0 {
    public final /* synthetic */ int g;

    public /* synthetic */ ao0(int i) {
        this.g = i;
    }

    @Override // defpackage.eo0
    public final boolean b(float f, long j, o70 o70Var, View view) {
        switch (this.g) {
            case 0:
                view.setAlpha(a(f, j, o70Var, view));
                break;
            case 1:
                view.setElevation(a(f, j, o70Var, view));
                break;
            case 2:
                view.setRotation(a(f, j, o70Var, view));
                break;
            case 3:
                view.setRotationX(a(f, j, o70Var, view));
                break;
            case 4:
                view.setRotationY(a(f, j, o70Var, view));
                break;
            case 5:
                view.setScaleX(a(f, j, o70Var, view));
                break;
            case 6:
                view.setScaleY(a(f, j, o70Var, view));
                break;
            case 7:
                view.setTranslationX(a(f, j, o70Var, view));
                break;
            case 8:
                view.setTranslationY(a(f, j, o70Var, view));
                break;
            default:
                view.setTranslationZ(a(f, j, o70Var, view));
                break;
        }
        return this.d;
    }
}
