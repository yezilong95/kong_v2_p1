package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class a20 extends a0 {
    public final z10[] E;

    public a20(z10 z10Var) {
        this.E = new z10[]{z10Var};
    }

    @Override // defpackage.a0, defpackage.t
    public final g0 b() {
        return new wk(this.E, 0);
    }

    public final String toString() {
        StringBuffer stringBuffer = new StringBuffer("GeneralNames:");
        String str = jx0.a;
        stringBuffer.append(str);
        int i = 0;
        while (true) {
            z10[] z10VarArr = this.E;
            if (i == z10VarArr.length) {
                return stringBuffer.toString();
            }
            stringBuffer.append("    ");
            stringBuffer.append(z10VarArr[i]);
            stringBuffer.append(str);
            i++;
        }
    }
}
