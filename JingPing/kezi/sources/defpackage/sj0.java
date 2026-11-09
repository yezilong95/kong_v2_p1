package defpackage;

import androidx.constraintlayout.solver.SolverVariable$Type;
import java.util.Arrays;
import org.java_websocket.extensions.ExtensionRequestData;

/* JADX INFO: loaded from: classes.dex */
public final class sj0 {
    public boolean a;
    public float e;
    public SolverVariable$Type i;
    public int b = -1;
    public int c = -1;
    public int d = 0;
    public boolean f = false;
    public final float[] g = new float[9];
    public final float[] h = new float[9];
    public e7[] j = new e7[16];
    public int k = 0;
    public int l = 0;

    public sj0(SolverVariable$Type solverVariable$Type) {
        this.i = solverVariable$Type;
    }

    public final void a(e7 e7Var) {
        int i = 0;
        while (true) {
            int i2 = this.k;
            if (i >= i2) {
                e7[] e7VarArr = this.j;
                if (i2 >= e7VarArr.length) {
                    this.j = (e7[]) Arrays.copyOf(e7VarArr, e7VarArr.length * 2);
                }
                e7[] e7VarArr2 = this.j;
                int i3 = this.k;
                e7VarArr2[i3] = e7Var;
                this.k = i3 + 1;
                return;
            }
            if (this.j[i] == e7Var) {
                return;
            } else {
                i++;
            }
        }
    }

    public final void b(e7 e7Var) {
        int i = this.k;
        int i2 = 0;
        while (i2 < i) {
            if (this.j[i2] == e7Var) {
                while (i2 < i - 1) {
                    e7[] e7VarArr = this.j;
                    int i3 = i2 + 1;
                    e7VarArr[i2] = e7VarArr[i3];
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

    public final void d(e7 e7Var) {
        int i = this.k;
        for (int i2 = 0; i2 < i; i2++) {
            this.j[i2].h(e7Var, false);
        }
        this.k = 0;
    }

    public final String toString() {
        return ExtensionRequestData.EMPTY_VALUE + this.b;
    }
}
