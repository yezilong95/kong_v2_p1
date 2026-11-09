package defpackage;

import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

/* JADX INFO: loaded from: classes.dex */
public final class x6 implements Runnable {
    public final /* synthetic */ int E = 0;
    public final /* synthetic */ int F;
    public final /* synthetic */ View G;
    public final /* synthetic */ Object H;

    public x6(TextView textView, Typeface typeface, int i) {
        this.G = textView;
        this.H = typeface;
        this.F = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.F;
        View view = this.G;
        Object obj = this.H;
        switch (this.E) {
            case 0:
                ((TextView) view).setTypeface((Typeface) obj, i);
                break;
            default:
                int i2 = BottomSheetBehavior.K0;
                ((BottomSheetBehavior) obj).L(view, i, false);
                break;
        }
    }

    public x6(BottomSheetBehavior bottomSheetBehavior, View view, int i) {
        this.H = bottomSheetBehavior;
        this.G = view;
        this.F = i;
    }
}
