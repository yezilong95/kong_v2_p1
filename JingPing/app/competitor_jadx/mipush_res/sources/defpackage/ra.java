package defpackage;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.google.android.material.bottomappbar.BottomAppBar;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ra implements Runnable {
    public final /* synthetic */ int E;
    public final /* synthetic */ View F;

    public /* synthetic */ ra(View view, int i) {
        this.E = i;
        this.F = view;
    }

    @Override // java.lang.Runnable
    public final void run() {
        View view = this.F;
        switch (this.E) {
            case 0:
                int i = BottomAppBar.V0;
                view.requestLayout();
                break;
            case 1:
                ((InputMethodManager) view.getContext().getSystemService("input_method")).showSoftInput(view, 0);
                break;
            default:
                ((InputMethodManager) qi.b(view.getContext(), InputMethodManager.class)).showSoftInput(view, 1);
                break;
        }
    }
}
