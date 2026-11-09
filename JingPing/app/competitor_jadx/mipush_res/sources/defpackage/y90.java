package defpackage;

import androidx.appcompat.widget.ListPopupWindow;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class y90 implements Runnable {
    public final /* synthetic */ int E;
    public final /* synthetic */ ListPopupWindow F;

    public /* synthetic */ y90(ListPopupWindow listPopupWindow, int i) {
        this.E = i;
        this.F = listPopupWindow;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ListPopupWindow listPopupWindow = this.F;
        switch (this.E) {
            case 0:
                gq gqVar = listPopupWindow.G;
                if (gqVar != null) {
                    gqVar.setListSelectionHidden(true);
                    gqVar.requestLayout();
                }
                break;
            default:
                gq gqVar2 = listPopupWindow.G;
                if (gqVar2 != null) {
                    WeakHashMap weakHashMap = f51.a;
                    if (gqVar2.isAttachedToWindow() && listPopupWindow.G.getCount() > listPopupWindow.G.getChildCount() && listPopupWindow.G.getChildCount() <= listPopupWindow.Q) {
                        listPopupWindow.d0.setInputMethodMode(2);
                        listPopupWindow.d();
                        break;
                    }
                }
                break;
        }
    }
}
