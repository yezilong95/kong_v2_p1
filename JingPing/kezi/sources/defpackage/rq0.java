package defpackage;

/* JADX INFO: loaded from: classes.dex */
public abstract class rq0 extends qq0 {
    public pb0[] a;
    public String b;
    public int c;

    public rq0() {
        this.a = null;
        this.c = 0;
    }

    public pb0[] getPathData() {
        return this.a;
    }

    public String getPathName() {
        return this.b;
    }

    public void setPathData(pb0[] pb0VarArr) {
        if (!jq0.c(this.a, pb0VarArr)) {
            this.a = jq0.n(pb0VarArr);
            return;
        }
        pb0[] pb0VarArr2 = this.a;
        for (int i = 0; i < pb0VarArr.length; i++) {
            pb0VarArr2[i].a = pb0VarArr[i].a;
            int i2 = 0;
            while (true) {
                float[] fArr = pb0VarArr[i].b;
                if (i2 < fArr.length) {
                    pb0VarArr2[i].b[i2] = fArr[i2];
                    i2++;
                }
            }
        }
    }

    public rq0(rq0 rq0Var) {
        this.a = null;
        this.c = 0;
        this.b = rq0Var.b;
        this.a = jq0.n(rq0Var.a);
    }
}
