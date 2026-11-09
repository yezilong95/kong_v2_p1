package defpackage;

import android.graphics.Typeface;
import com.google.android.material.chip.Chip;

/* JADX INFO: loaded from: classes.dex */
public final class ic extends za0 {
    public final /* synthetic */ int p;
    public final /* synthetic */ Object q;

    public /* synthetic */ ic(int i, Object obj) {
        this.p = i;
        this.q = obj;
    }

    @Override // defpackage.za0
    public final void G(int i) {
        switch (this.p) {
            case 0:
                break;
            default:
                hn0 hn0Var = (hn0) this.q;
                hn0Var.e = true;
                gn0 gn0Var = (gn0) hn0Var.f.get();
                if (gn0Var != null) {
                    gn0Var.a();
                }
                break;
        }
    }

    @Override // defpackage.za0
    public final void H(Typeface typeface, boolean z) {
        switch (this.p) {
            case 0:
                Chip chip = (Chip) this.q;
                mc mcVar = chip.f;
                chip.setText(mcVar.D0 ? mcVar.F : chip.getText());
                chip.requestLayout();
                chip.invalidate();
                break;
            default:
                if (!z) {
                    hn0 hn0Var = (hn0) this.q;
                    hn0Var.e = true;
                    gn0 gn0Var = (gn0) hn0Var.f.get();
                    if (gn0Var != null) {
                        gn0Var.a();
                    }
                    break;
                }
                break;
        }
    }

    private final void S(int i) {
    }
}
