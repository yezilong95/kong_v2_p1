package defpackage;

import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class vv extends mg {
    public mg[] g0 = new mg[4];
    public int h0 = 0;

    public final void C(mg mgVar) {
        if (mgVar == this || mgVar == null) {
            return;
        }
        int i = this.h0 + 1;
        mg[] mgVarArr = this.g0;
        if (i > mgVarArr.length) {
            this.g0 = (mg[]) Arrays.copyOf(mgVarArr, mgVarArr.length * 2);
        }
        mg[] mgVarArr2 = this.g0;
        int i2 = this.h0;
        mgVarArr2[i2] = mgVar;
        this.h0 = i2 + 1;
    }

    @Override // defpackage.mg
    public void f(mg mgVar, HashMap map) {
        super.f(mgVar, map);
        vv vvVar = (vv) mgVar;
        this.h0 = 0;
        int i = vvVar.h0;
        for (int i2 = 0; i2 < i; i2++) {
            C((mg) map.get(vvVar.g0[i2]));
        }
    }

    public void D() {
    }
}
