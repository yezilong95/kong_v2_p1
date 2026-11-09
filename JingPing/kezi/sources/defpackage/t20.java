package defpackage;

import androidx.appcompat.widget.ListPopupWindow;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class t20 implements Runnable {
    public final /* synthetic */ int b;
    public final /* synthetic */ ListPopupWindow c;

    public /* synthetic */ t20(ListPopupWindow listPopupWindow, int i) {
        this.b = i;
        this.c = listPopupWindow;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ListPopupWindow listPopupWindow = this.c;
        switch (this.b) {
            case 0:
                cm cmVar = listPopupWindow.d;
                if (cmVar != null) {
                    cmVar.setListSelectionHidden(true);
                    cmVar.requestLayout();
                }
                break;
            default:
                cm cmVar2 = listPopupWindow.d;
                if (cmVar2 != null) {
                    WeakHashMap weakHashMap = xr0.a;
                    if (cmVar2.isAttachedToWindow() && listPopupWindow.d.getCount() > listPopupWindow.d.getChildCount() && listPopupWindow.d.getChildCount() <= listPopupWindow.n) {
                        listPopupWindow.A.setInputMethodMode(2);
                        listPopupWindow.f();
                        break;
                    }
                }
                break;
        }
    }
}
