package defpackage;

import android.os.Handler;
import android.widget.AbsListView;
import androidx.appcompat.widget.ListPopupWindow;

/* JADX INFO: loaded from: classes.dex */
public final class u20 implements AbsListView.OnScrollListener {
    public final /* synthetic */ ListPopupWindow a;

    public u20(ListPopupWindow listPopupWindow) {
        this.a = listPopupWindow;
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public final void onScrollStateChanged(AbsListView absListView, int i) {
        if (i == 1) {
            ListPopupWindow listPopupWindow = this.a;
            if (listPopupWindow.A.getInputMethodMode() == 2 || listPopupWindow.A.getContentView() == null) {
                return;
            }
            Handler handler = listPopupWindow.w;
            t20 t20Var = listPopupWindow.s;
            handler.removeCallbacks(t20Var);
            t20Var.run();
        }
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public final void onScroll(AbsListView absListView, int i, int i2, int i3) {
    }
}
