package defpackage;

import android.graphics.Canvas;
import android.graphics.Matrix;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class cv0 extends iv0 {
    public final /* synthetic */ ArrayList c;
    public final /* synthetic */ Matrix d;

    public cv0(ArrayList arrayList, Matrix matrix) {
        this.c = arrayList;
        this.d = matrix;
    }

    @Override // defpackage.iv0
    public final void a(Matrix matrix, wu0 wu0Var, int i, Canvas canvas) {
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            ((iv0) it.next()).a(this.d, wu0Var, i, canvas);
        }
    }
}
