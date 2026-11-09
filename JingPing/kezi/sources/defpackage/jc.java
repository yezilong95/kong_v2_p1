package defpackage;

import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewOutlineProvider;
import com.google.android.material.chip.Chip;

/* JADX INFO: loaded from: classes.dex */
public final class jc extends ViewOutlineProvider {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ jc(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // android.view.ViewOutlineProvider
    public final void getOutline(View view, Outline outline) {
        switch (this.a) {
            case 0:
                mc mcVar = ((Chip) this.b).f;
                if (mcVar == null) {
                    outline.setAlpha(0.0f);
                } else {
                    mcVar.getOutline(outline);
                }
                break;
            case 1:
                xi0 xi0Var = (xi0) this.b;
                if (xi0Var.c != null && !xi0Var.d.isEmpty()) {
                    RectF rectF = xi0Var.d;
                    outline.setRoundRect((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom, xi0Var.g);
                    break;
                }
                break;
            default:
                Path path = ((yi0) this.b).e;
                if (!path.isEmpty()) {
                    outline.setPath(path);
                }
                break;
        }
    }
}
