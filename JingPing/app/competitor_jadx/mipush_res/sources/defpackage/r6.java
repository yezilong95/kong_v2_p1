package defpackage;

import android.view.View;
import android.widget.AdapterView;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.ListPopupWindow;
import androidx.appcompat.widget.SearchView;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;

/* JADX INFO: loaded from: classes.dex */
public final class r6 implements AdapterView.OnItemClickListener {
    public final /* synthetic */ int E;
    public final /* synthetic */ Object F;

    public /* synthetic */ r6(int i, Object obj) {
        this.E = i;
        this.F = obj;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
        switch (this.E) {
            case 0:
                t6 t6Var = (t6) this.F;
                t6Var.k0.setSelection(i);
                AppCompatSpinner appCompatSpinner = t6Var.k0;
                if (appCompatSpinner.getOnItemClickListener() != null) {
                    appCompatSpinner.performItemClick(view, i, t6Var.h0.getItemId(i));
                }
                t6Var.dismiss();
                break;
            case 1:
                MaterialAutoCompleteTextView materialAutoCompleteTextView = (MaterialAutoCompleteTextView) this.F;
                ListPopupWindow listPopupWindow = materialAutoCompleteTextView.I;
                MaterialAutoCompleteTextView.a(materialAutoCompleteTextView, i < 0 ? !listPopupWindow.d0.isShowing() ? null : listPopupWindow.G.getSelectedItem() : materialAutoCompleteTextView.getAdapter().getItem(i));
                AdapterView.OnItemClickListener onItemClickListener = materialAutoCompleteTextView.getOnItemClickListener();
                if (onItemClickListener != null) {
                    if (view == null || i < 0) {
                        view = !listPopupWindow.d0.isShowing() ? null : listPopupWindow.G.getSelectedView();
                        i = !listPopupWindow.d0.isShowing() ? -1 : listPopupWindow.G.getSelectedItemPosition();
                        j = !listPopupWindow.d0.isShowing() ? Long.MIN_VALUE : listPopupWindow.G.getSelectedItemId();
                    }
                    onItemClickListener.onItemClick(listPopupWindow.G, view, i, j);
                }
                listPopupWindow.dismiss();
                break;
            default:
                ((SearchView) this.F).p(i);
                break;
        }
    }
}
