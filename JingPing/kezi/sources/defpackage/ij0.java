package defpackage;

import android.view.View;
import android.view.ViewGroup;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class ij0 extends k9 {
    public final /* synthetic */ int n;

    @Override // defpackage.kj0
    public final float a(ViewGroup viewGroup, View view) {
        switch (this.n) {
            case 0:
                break;
            case 1:
                WeakHashMap weakHashMap = xr0.a;
                if (viewGroup.getLayoutDirection() != 1) {
                }
                break;
            case 2:
                break;
            default:
                WeakHashMap weakHashMap2 = xr0.a;
                if (viewGroup.getLayoutDirection() != 1) {
                }
                break;
        }
        return view.getTranslationX() + viewGroup.getWidth();
    }
}
