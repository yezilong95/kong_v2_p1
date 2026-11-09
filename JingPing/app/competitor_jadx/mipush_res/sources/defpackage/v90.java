package defpackage;

import android.view.View;
import android.widget.AdapterView;
import androidx.appcompat.widget.ListPopupWindow;
import androidx.appcompat.widget.SearchView;

/* JADX INFO: loaded from: classes.dex */
public final class v90 implements AdapterView.OnItemSelectedListener {
    public final /* synthetic */ int E;
    public final /* synthetic */ Object F;

    public /* synthetic */ v90(int i, Object obj) {
        this.E = i;
        this.F = obj;
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onItemSelected(AdapterView adapterView, View view, int i, long j) {
        gq gqVar;
        switch (this.E) {
            case 0:
                if (i != -1 && (gqVar = ((ListPopupWindow) this.F).G) != null) {
                    gqVar.setListSelectionHidden(false);
                    break;
                }
                break;
            default:
                ((SearchView) this.F).q(i);
                break;
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onNothingSelected(AdapterView adapterView) {
        int i = this.E;
    }

    private final void a(AdapterView adapterView) {
    }

    private final void b(AdapterView adapterView) {
    }
}
