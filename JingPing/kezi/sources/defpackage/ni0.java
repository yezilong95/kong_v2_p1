package defpackage;

import android.graphics.Canvas;
import android.graphics.Matrix;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class ni0 extends ti0 {
    public final /* synthetic */ ArrayList c;
    public final /* synthetic */ Matrix d;

    public ni0(ArrayList arrayList, Matrix matrix) {
        this.c = arrayList;
        this.d = matrix;
    }

    @Override // defpackage.ti0
    public final void a(Matrix matrix, hi0 hi0Var, int i, Canvas canvas) {
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            ((ti0) it.next()).a(this.d, hi0Var, i, canvas);
        }
    }
}
