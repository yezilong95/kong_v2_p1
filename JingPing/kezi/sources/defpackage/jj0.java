package defpackage;

import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public final class jj0 extends xi {
    public final /* synthetic */ int r;

    @Override // defpackage.kj0
    public final float b(ViewGroup viewGroup, View view) {
        switch (this.r) {
            case 0:
                return view.getTranslationY() - viewGroup.getHeight();
            default:
                return view.getTranslationY() + viewGroup.getHeight();
        }
    }
}
