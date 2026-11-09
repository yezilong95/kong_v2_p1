package defpackage;

import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class b30 extends yh {
    public yh[] g0 = new yh[4];
    public int h0 = 0;

    public final void C(yh yhVar) {
        if (yhVar == this || yhVar == null) {
            return;
        }
        int i = this.h0 + 1;
        yh[] yhVarArr = this.g0;
        if (i > yhVarArr.length) {
            this.g0 = (yh[]) Arrays.copyOf(yhVarArr, yhVarArr.length * 2);
        }
        yh[] yhVarArr2 = this.g0;
        int i2 = this.h0;
        yhVarArr2[i2] = yhVar;
        this.h0 = i2 + 1;
    }

    @Override // defpackage.yh
    public void f(yh yhVar, HashMap map) {
        super.f(yhVar, map);
        b30 b30Var = (b30) yhVar;
        this.h0 = 0;
        int i = b30Var.h0;
        for (int i2 = 0; i2 < i; i2++) {
            C((yh) map.get(b30Var.g0[i2]));
        }
    }

    public void D() {
    }
}
