package defpackage;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ka implements ru {
    public final /* synthetic */ int[] E;
    public final /* synthetic */ int F;
    public final /* synthetic */ List G;
    public final /* synthetic */ nf H;

    public ka(int[] iArr, int i, List list, nf nfVar) {
        this.E = iArr;
        this.F = i;
        this.G = list;
        this.H = nfVar;
    }

    @Override // defpackage.ru
    public final void b() {
        int[] iArr = this.E;
        int i = iArr[0];
        if (i < this.F) {
            int i2 = i + 1;
            iArr[0] = i2;
            la.j(i2, this.G, new z8(7, this));
        }
    }
}
