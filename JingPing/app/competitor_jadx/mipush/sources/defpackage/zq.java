package defpackage;

/* JADX INFO: loaded from: classes.dex */
public abstract class zq extends l21 {
    @Override // defpackage.l21
    public l21 d1(l21 l21Var) {
        return e(l21Var);
    }

    public l21 i1() {
        int iN = N();
        if ((iN & 1) == 0) {
            throw new IllegalStateException("Half-trace only defined for odd m");
        }
        int i = (iN + 1) >>> 1;
        int iNumberOfLeadingZeros = 31 - Integer.numberOfLeadingZeros(i);
        l21 l21VarE = this;
        int i2 = 1;
        while (iNumberOfLeadingZeros > 0) {
            l21VarE = l21VarE.b1(i2 << 1).e(l21VarE);
            iNumberOfLeadingZeros--;
            i2 = i >>> iNumberOfLeadingZeros;
            if ((i2 & 1) != 0) {
                l21VarE = l21VarE.b1(2).e(this);
            }
        }
        return l21VarE;
    }

    public boolean j1() {
        return this instanceof ht0;
    }

    public int k1() {
        int iN = N();
        int iNumberOfLeadingZeros = 31 - Integer.numberOfLeadingZeros(iN);
        l21 l21VarE = this;
        int i = 1;
        while (iNumberOfLeadingZeros > 0) {
            l21VarE = l21VarE.b1(i).e(l21VarE);
            iNumberOfLeadingZeros--;
            i = iN >>> iNumberOfLeadingZeros;
            if ((i & 1) != 0) {
                l21VarE = l21VarE.U0().e(this);
            }
        }
        if (l21VarE.d0()) {
            return 0;
        }
        if (l21VarE.b0()) {
            return 1;
        }
        throw new IllegalStateException("Internal error in trace calculation");
    }
}
