package defpackage;

import android.view.View;
import android.widget.AdapterView;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.ListPopupWindow;
import androidx.appcompat.widget.SearchView;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;

/* JADX INFO: loaded from: classes.dex */
public final class n5 implements AdapterView.OnItemClickListener {
    public final /* synthetic */ int b;
    public final /* synthetic */ Object c;

    public /* synthetic */ n5(int i, Object obj) {
        this.b = i;
        this.c = obj;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
        switch (this.b) {
            case 0:
                p5 p5Var = (p5) this.c;
                p5Var.H.setSelection(i);
                AppCompatSpinner appCompatSpinner = p5Var.H;
                if (appCompatSpinner.getOnItemClickListener() != null) {
                    appCompatSpinner.performItemClick(view, i, p5Var.E.getItemId(i));
                }
                p5Var.dismiss();
                break;
            case 1:
                MaterialAutoCompleteTextView materialAutoCompleteTextView = (MaterialAutoCompleteTextView) this.c;
                ListPopupWindow listPopupWindow = materialAutoCompleteTextView.f;
                MaterialAutoCompleteTextView.a(materialAutoCompleteTextView, i < 0 ? !listPopupWindow.A.isShowing() ? null : listPopupWindow.d.getSelectedItem() : materialAutoCompleteTextView.getAdapter().getItem(i));
                AdapterView.OnItemClickListener onItemClickListener = materialAutoCompleteTextView.getOnItemClickListener();
                if (onItemClickListener != null) {
                    if (view == null || i < 0) {
                        view = !listPopupWindow.A.isShowing() ? null : listPopupWindow.d.getSelectedView();
                        i = !listPopupWindow.A.isShowing() ? -1 : listPopupWindow.d.getSelectedItemPosition();
                        j = !listPopupWindow.A.isShowing() ? Long.MIN_VALUE : listPopupWindow.d.getSelectedItemId();
                    }
                    onItemClickListener.onItemClick(listPopupWindow.d, view, i, j);
                }
                listPopupWindow.dismiss();
                break;
            default:
                ((SearchView) this.c).p(i);
                break;
        }
    }
}
