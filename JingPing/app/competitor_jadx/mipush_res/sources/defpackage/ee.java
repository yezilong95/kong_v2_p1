package defpackage;

import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewOutlineProvider;
import com.google.android.material.chip.Chip;

/* JADX INFO: loaded from: classes.dex */
public final class ee extends ViewOutlineProvider {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ ee(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // android.view.ViewOutlineProvider
    public final void getOutline(View view, Outline outline) {
        switch (this.a) {
            case 0:
                he heVar = ((Chip) this.b).I;
                if (heVar == null) {
                    outline.setAlpha(0.0f);
                } else {
                    heVar.getOutline(outline);
                }
                break;
            case 1:
                mv0 mv0Var = (mv0) this.b;
                if (mv0Var.c != null && !mv0Var.d.isEmpty()) {
                    RectF rectF = mv0Var.d;
                    outline.setRoundRect((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom, mv0Var.g);
                    break;
                }
                break;
            default:
                Path path = ((nv0) this.b).e;
                if (!path.isEmpty()) {
                    outline.setPath(path);
                }
                break;
        }
    }
}
