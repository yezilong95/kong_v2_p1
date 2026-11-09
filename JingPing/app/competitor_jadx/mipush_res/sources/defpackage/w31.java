package defpackage;

/* JADX INFO: loaded from: classes.dex */
public abstract class w31 extends v31 {
    public mk0[] a;
    public String b;
    public int c;

    public w31() {
        this.a = null;
        this.c = 0;
    }

    public mk0[] getPathData() {
        return this.a;
    }

    public String getPathName() {
        return this.b;
    }

    public void setPathData(mk0[] mk0VarArr) {
        mk0[] mk0VarArr2 = this.a;
        boolean z = false;
        if (mk0VarArr2 != null && mk0VarArr != null && mk0VarArr2.length == mk0VarArr.length) {
            int i = 0;
            while (true) {
                if (i >= mk0VarArr2.length) {
                    z = true;
                    break;
                }
                mk0 mk0Var = mk0VarArr2[i];
                char c = mk0Var.a;
                mk0 mk0Var2 = mk0VarArr[i];
                if (c != mk0Var2.a || mk0Var.b.length != mk0Var2.b.length) {
                    break;
                } else {
                    i++;
                }
            }
        }
        if (!z) {
            this.a = o80.t(mk0VarArr);
            return;
        }
        mk0[] mk0VarArr3 = this.a;
        for (int i2 = 0; i2 < mk0VarArr.length; i2++) {
            mk0VarArr3[i2].a = mk0VarArr[i2].a;
            int i3 = 0;
            while (true) {
                float[] fArr = mk0VarArr[i2].b;
                if (i3 < fArr.length) {
                    mk0VarArr3[i2].b[i3] = fArr[i3];
                    i3++;
                }
            }
        }
    }

    public w31(w31 w31Var) {
        this.a = null;
        this.c = 0;
        this.b = w31Var.b;
        this.a = o80.t(w31Var.a);
    }
}
