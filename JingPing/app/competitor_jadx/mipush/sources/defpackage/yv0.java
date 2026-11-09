package defpackage;

import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public final class yv0 extends s90 {
    public final /* synthetic */ int w;

    @Override // defpackage.zv0
    public final float c(ViewGroup viewGroup, View view) {
        switch (this.w) {
            case 0:
                return view.getTranslationY() - viewGroup.getHeight();
            default:
                return view.getTranslationY() + viewGroup.getHeight();
        }
    }
}
