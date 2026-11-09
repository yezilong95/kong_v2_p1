package defpackage;

import android.os.Handler;
import android.widget.AbsListView;
import androidx.appcompat.widget.ListPopupWindow;

/* JADX INFO: loaded from: classes.dex */
public final class z90 implements AbsListView.OnScrollListener {
    public final /* synthetic */ ListPopupWindow a;

    public z90(ListPopupWindow listPopupWindow) {
        this.a = listPopupWindow;
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public final void onScrollStateChanged(AbsListView absListView, int i) {
        if (i == 1) {
            ListPopupWindow listPopupWindow = this.a;
            if (listPopupWindow.d0.getInputMethodMode() == 2 || listPopupWindow.d0.getContentView() == null) {
                return;
            }
            Handler handler = listPopupWindow.Z;
            y90 y90Var = listPopupWindow.V;
            handler.removeCallbacks(y90Var);
            y90Var.run();
        }
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public final void onScroll(AbsListView absListView, int i, int i2, int i3) {
    }
}
