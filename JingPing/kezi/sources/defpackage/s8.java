package defpackage;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.google.android.material.bottomappbar.BottomAppBar;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class s8 implements Runnable {
    public final /* synthetic */ int b;
    public final /* synthetic */ View c;

    public /* synthetic */ s8(View view, int i) {
        this.b = i;
        this.c = view;
    }

    @Override // java.lang.Runnable
    public final void run() {
        View view = this.c;
        switch (this.b) {
            case 0:
                int i = BottomAppBar.s0;
                view.requestLayout();
                break;
            case 1:
                ((InputMethodManager) view.getContext().getSystemService("input_method")).showSoftInput(view, 0);
                break;
            default:
                ((InputMethodManager) bh.b(view.getContext(), InputMethodManager.class)).showSoftInput(view, 1);
                break;
        }
    }
}
