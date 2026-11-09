package defpackage;

import android.database.DataSetObserver;
import androidx.appcompat.widget.ListPopupWindow;

/* JADX INFO: loaded from: classes.dex */
public final class di extends DataSetObserver {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ di(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // android.database.DataSetObserver
    public final void onChanged() {
        switch (this.a) {
            case 0:
                jl0 jl0Var = (jl0) this.b;
                jl0Var.b = true;
                jl0Var.notifyDataSetChanged();
                break;
            default:
                ListPopupWindow listPopupWindow = (ListPopupWindow) this.b;
                if (listPopupWindow.A.isShowing()) {
                    listPopupWindow.f();
                }
                break;
        }
    }

    @Override // android.database.DataSetObserver
    public final void onInvalidated() {
        switch (this.a) {
            case 0:
                jl0 jl0Var = (jl0) this.b;
                jl0Var.b = false;
                jl0Var.notifyDataSetInvalidated();
                break;
            default:
                ((ListPopupWindow) this.b).dismiss();
                break;
        }
    }
}
