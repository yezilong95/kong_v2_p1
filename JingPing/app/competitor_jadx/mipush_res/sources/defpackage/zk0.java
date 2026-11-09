package defpackage;

import io.nats.client.support.NatsConstants;

/* JADX INFO: loaded from: classes.dex */
public final class zk0 {
    public final /* synthetic */ int a;
    public final ou[] b;
    public final int c;

    public zk0(fb0 fb0Var, int i) {
        this.a = i;
        switch (i) {
            case 1:
                int i2 = fb0Var.c;
                this.c = i2;
                this.b = new ou[i2];
                for (int i3 = 0; i3 < this.c; i3++) {
                    this.b[i3] = new ou(fb0Var);
                }
                break;
            default:
                int i4 = fb0Var.b;
                this.c = i4;
                this.b = new ou[i4];
                for (int i5 = 0; i5 < this.c; i5++) {
                    this.b[i5] = new ou(fb0Var);
                }
                break;
        }
    }

    public final String toString() {
        switch (this.a) {
            case 0:
                String strM = "[";
                int i = 0;
                while (true) {
                    int i2 = this.c;
                    if (i >= i2) {
                        return zn.m(strM, "]");
                    }
                    strM = strM + i + NatsConstants.SPACE + this.b[i].toString();
                    if (i != i2 - 1) {
                        strM = zn.m(strM, ",\n");
                    }
                    i++;
                }
                break;
            default:
                String strM2 = "\n[";
                int i3 = 0;
                while (true) {
                    int i4 = this.c;
                    if (i3 >= i4) {
                        return zn.m(strM2, "]");
                    }
                    strM2 = strM2 + "Inner Matrix " + i3 + NatsConstants.SPACE + this.b[i3].toString();
                    if (i3 != i4 - 1) {
                        strM2 = zn.m(strM2, ",\n");
                    }
                    i3++;
                }
                break;
        }
    }
}
