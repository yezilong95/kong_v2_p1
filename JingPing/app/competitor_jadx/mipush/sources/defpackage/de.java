package defpackage;

import android.graphics.Typeface;
import com.google.android.material.chip.Chip;

/* JADX INFO: loaded from: classes.dex */
public final class de extends s90 {
    public final /* synthetic */ int w;
    public final /* synthetic */ Object x;

    public /* synthetic */ de(int i, Object obj) {
        this.w = i;
        this.x = obj;
    }

    @Override // defpackage.s90
    public final void O(int i) {
        switch (this.w) {
            case 0:
                break;
            default:
                vz0 vz0Var = (vz0) this.x;
                vz0Var.e = true;
                uz0 uz0Var = (uz0) vz0Var.f.get();
                if (uz0Var != null) {
                    uz0Var.a();
                }
                break;
        }
    }

    @Override // defpackage.s90
    public final void P(Typeface typeface, boolean z) {
        switch (this.w) {
            case 0:
                Chip chip = (Chip) this.x;
                he heVar = chip.I;
                chip.setText(heVar.h1 ? heVar.j0 : chip.getText());
                chip.requestLayout();
                chip.invalidate();
                break;
            default:
                if (!z) {
                    vz0 vz0Var = (vz0) this.x;
                    vz0Var.e = true;
                    uz0 uz0Var = (uz0) vz0Var.f.get();
                    if (uz0Var != null) {
                        uz0Var.a();
                    }
                    break;
                }
                break;
        }
    }

    private final void d0(int i) {
    }
}
