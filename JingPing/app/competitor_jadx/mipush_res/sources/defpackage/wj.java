package defpackage;

import android.database.DataSetObserver;
import androidx.appcompat.widget.ListPopupWindow;

/* JADX INFO: loaded from: classes.dex */
public final class wj extends DataSetObserver {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ wj(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // android.database.DataSetObserver
    public final void onChanged() {
        switch (this.a) {
            case 0:
                xx0 xx0Var = (xx0) this.b;
                xx0Var.E = true;
                xx0Var.notifyDataSetChanged();
                break;
            default:
                ListPopupWindow listPopupWindow = (ListPopupWindow) this.b;
                if (listPopupWindow.d0.isShowing()) {
                    listPopupWindow.d();
                }
                break;
        }
    }

    @Override // android.database.DataSetObserver
    public final void onInvalidated() {
        switch (this.a) {
            case 0:
                xx0 xx0Var = (xx0) this.b;
                xx0Var.E = false;
                xx0Var.notifyDataSetInvalidated();
                break;
            default:
                ((ListPopupWindow) this.b).dismiss();
                break;
        }
    }
}
