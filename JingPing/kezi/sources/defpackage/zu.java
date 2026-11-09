package defpackage;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.k;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class zu {
    public int a;
    public int b;
    public int c;
    public Object d;

    public void a(j20 j20Var) {
        j20Var.d = null;
        j20Var.b = null;
        j20Var.c = null;
        j20Var.j = 1;
        int i = this.a;
        if (i > 0) {
            int i2 = this.c;
            if ((i2 & 1) == 0) {
                this.c = i2 + 1;
                this.a = i - 1;
                this.b++;
            }
        }
        j20Var.b = (j20) this.d;
        this.d = j20Var;
        int i3 = this.c;
        int i4 = i3 + 1;
        this.c = i4;
        int i5 = this.a;
        if (i5 > 0 && (i4 & 1) == 0) {
            this.c = i3 + 2;
            this.a = i5 - 1;
            this.b++;
        }
        int i6 = 4;
        while (true) {
            int i7 = i6 - 1;
            if ((this.c & i7) != i7) {
                return;
            }
            int i8 = this.b;
            if (i8 == 0) {
                j20 j20Var2 = (j20) this.d;
                j20 j20Var3 = j20Var2.b;
                j20 j20Var4 = j20Var3.b;
                j20Var3.b = j20Var4.b;
                this.d = j20Var3;
                j20Var3.c = j20Var4;
                j20Var3.d = j20Var2;
                j20Var3.j = j20Var2.j + 1;
                j20Var4.b = j20Var3;
                j20Var2.b = j20Var3;
            } else if (i8 == 1) {
                j20 j20Var5 = (j20) this.d;
                j20 j20Var6 = j20Var5.b;
                this.d = j20Var6;
                j20Var6.d = j20Var5;
                j20Var6.j = j20Var5.j + 1;
                j20Var5.b = j20Var6;
                this.b = 0;
            } else if (i8 == 2) {
                this.b = 0;
            }
            i6 *= 2;
        }
    }

    public void b(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("Layout positions must be non-negative");
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("Pixel distance must be non-negative");
        }
        int i3 = this.c;
        int i4 = i3 * 2;
        int[] iArr = (int[]) this.d;
        if (iArr == null) {
            int[] iArr2 = new int[4];
            this.d = iArr2;
            Arrays.fill(iArr2, -1);
        } else if (i4 >= iArr.length) {
            int[] iArr3 = new int[i3 * 4];
            this.d = iArr3;
            System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
        }
        int[] iArr4 = (int[]) this.d;
        iArr4[i4] = i;
        iArr4[i4 + 1] = i2;
        this.c++;
    }

    public void c(RecyclerView recyclerView, boolean z) {
        this.c = 0;
        int[] iArr = (int[]) this.d;
        if (iArr != null) {
            Arrays.fill(iArr, -1);
        }
        k kVar = recyclerView.m;
        if (recyclerView.l == null || kVar == null || !kVar.i) {
            return;
        }
        if (z) {
            if (!recyclerView.e.i()) {
                kVar.i(recyclerView.l.a(), this);
            }
        } else if (!recyclerView.K()) {
            kVar.h(this.a, this.b, recyclerView.e0, this);
        }
        int i = this.c;
        if (i > kVar.j) {
            kVar.j = i;
            kVar.k = z;
            recyclerView.c.k();
        }
    }
}
