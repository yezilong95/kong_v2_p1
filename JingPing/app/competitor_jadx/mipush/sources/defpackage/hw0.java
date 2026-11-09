package defpackage;

import androidx.constraintlayout.solver.SolverVariable$Type;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class hw0 {
    public boolean a;
    public float e;
    public SolverVariable$Type i;
    public int b = -1;
    public int c = -1;
    public int d = 0;
    public boolean f = false;
    public final float[] g = new float[9];
    public final float[] h = new float[9];
    public m8[] j = new m8[16];
    public int k = 0;
    public int l = 0;

    public hw0(SolverVariable$Type solverVariable$Type) {
        this.i = solverVariable$Type;
    }

    public final void a(m8 m8Var) {
        int i = 0;
        while (true) {
            int i2 = this.k;
            if (i >= i2) {
                m8[] m8VarArr = this.j;
                if (i2 >= m8VarArr.length) {
                    this.j = (m8[]) Arrays.copyOf(m8VarArr, m8VarArr.length * 2);
                }
                m8[] m8VarArr2 = this.j;
                int i3 = this.k;
                m8VarArr2[i3] = m8Var;
                this.k = i3 + 1;
                return;
            }
            if (this.j[i] == m8Var) {
                return;
            } else {
                i++;
            }
        }
    }

    public final void b(m8 m8Var) {
        int i = this.k;
        int i2 = 0;
        while (i2 < i) {
            if (this.j[i2] == m8Var) {
                while (i2 < i - 1) {
                    m8[] m8VarArr = this.j;
                    int i3 = i2 + 1;
                    m8VarArr[i2] = m8VarArr[i3];
                    i2 = i3;
                }
                this.k--;
                return;
            }
            i2++;
        }
    }

    public final void c() {
        this.i = SolverVariable$Type.UNKNOWN;
        this.d = 0;
        this.b = -1;
        this.c = -1;
        this.e = 0.0f;
        this.f = false;
        int i = this.k;
        for (int i2 = 0; i2 < i; i2++) {
            this.j[i2] = null;
        }
        this.k = 0;
        this.l = 0;
        this.a = false;
        Arrays.fill(this.h, 0.0f);
    }

    public final void d(m8 m8Var) {
        int i = this.k;
        for (int i2 = 0; i2 < i; i2++) {
            this.j[i2].h(m8Var, false);
        }
        this.k = 0;
    }

    public final String toString() {
        return "" + this.b;
    }
}
