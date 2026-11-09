package defpackage;

import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public final class xv0 extends o80 {
    public final /* synthetic */ int j;

    @Override // defpackage.zv0
    public final float d(ViewGroup viewGroup, View view) {
        switch (this.j) {
            case 0:
                break;
            case 1:
                if (viewGroup.getLayoutDirection() != 1) {
                }
                break;
            case 2:
                break;
            default:
                if (viewGroup.getLayoutDirection() != 1) {
                }
                break;
        }
        return view.getTranslationX() + viewGroup.getWidth();
    }
}
