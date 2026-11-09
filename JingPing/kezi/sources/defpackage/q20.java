package defpackage;

import android.view.View;
import android.widget.AdapterView;
import androidx.appcompat.widget.ListPopupWindow;
import androidx.appcompat.widget.SearchView;

/* JADX INFO: loaded from: classes.dex */
public final class q20 implements AdapterView.OnItemSelectedListener {
    public final /* synthetic */ int b;
    public final /* synthetic */ Object c;

    public /* synthetic */ q20(int i, Object obj) {
        this.b = i;
        this.c = obj;
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onItemSelected(AdapterView adapterView, View view, int i, long j) {
        cm cmVar;
        switch (this.b) {
            case 0:
                if (i != -1 && (cmVar = ((ListPopupWindow) this.c).d) != null) {
                    cmVar.setListSelectionHidden(false);
                    break;
                }
                break;
            default:
                ((SearchView) this.c).q(i);
                break;
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onNothingSelected(AdapterView adapterView) {
        int i = this.b;
    }

    private final void a(AdapterView adapterView) {
    }

    private final void b(AdapterView adapterView) {
    }
}
