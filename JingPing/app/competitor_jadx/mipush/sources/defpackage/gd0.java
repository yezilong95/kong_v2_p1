package defpackage;

import android.view.View;
import io.nats.client.support.NatsJetStreamConstants;
import kotlin.uuid.Uuid;

/* JADX INFO: loaded from: classes.dex */
public final class gd0 implements vi0 {
    public final /* synthetic */ int E = 0;
    public final int F;
    public final int G;
    public final Object H;

    public gd0(fb0 fb0Var) {
        int i = fb0Var.b;
        this.F = i;
        this.G = fb0Var.c;
        this.H = new zk0[i];
        for (int i2 = 0; i2 < this.F; i2++) {
            ((zk0[]) this.H)[i2] = new zk0(fb0Var, 1);
        }
    }

    public void a() {
        for (int i = 0; i < this.F; i++) {
            l0 l0Var = ((l0[]) this.H)[i];
            short[] sArr = (short[]) l0Var.c;
            short[] sArr2 = new short[NatsJetStreamConstants.MAX_PULL_SIZE];
            System.arraycopy(sArr, 0, sArr2, 0, NatsJetStreamConstants.MAX_PULL_SIZE);
            int i2 = 1;
            for (int i3 = Uuid.SIZE_BITS; i3 >= 2; i3 >>= 1) {
                int i4 = 0;
                while (i4 < 256) {
                    int i5 = i2 + 1;
                    short s = t40.f[i2];
                    int i6 = i4;
                    while (i6 < i4 + i3) {
                        int i7 = i6 + i3;
                        short sU = la0.U(sArr2[i7] * s);
                        sArr2[i7] = (short) (sArr2[i6] - sU);
                        sArr2[i6] = (short) (sArr2[i6] + sU);
                        i6++;
                    }
                    i4 = i6 + i3;
                    i2 = i5;
                }
            }
            l0Var.c = sArr2;
            l0Var.o();
        }
    }

    public byte[] b() {
        byte[] bArr = new byte[this.G];
        for (int i = 0; i < this.F; i++) {
            l0 l0Var = ((l0[]) this.H)[i];
            byte[] bArr2 = new byte[384];
            for (int i2 = 0; i2 < 256; i2++) {
                short s = (short) (r7[i2] - 3329);
                ((short[]) l0Var.c)[i2] = (short) (s + ((s >> 15) & 3329));
            }
            for (int i3 = 0; i3 < 128; i3++) {
                int i4 = i3 * 2;
                short[] sArr = (short[]) l0Var.c;
                short s2 = sArr[i4];
                short s3 = sArr[i4 + 1];
                int i5 = i3 * 3;
                bArr2[i5] = (byte) s2;
                bArr2[i5 + 1] = (byte) ((s2 >> 8) | (s3 << 4));
                bArr2[i5 + 2] = (byte) (s3 >> 4);
            }
            l0Var.getClass();
            System.arraycopy(bArr2, 0, bArr, i * 384, 384);
        }
        return bArr;
    }

    @Override // defpackage.vi0
    public s81 h(View view, s81 s81Var) {
        int i = s81Var.a.f(7).b;
        View view2 = (View) this.H;
        int i2 = this.F;
        if (i2 >= 0) {
            view2.getLayoutParams().height = i2 + i;
            view2.setLayoutParams(view2.getLayoutParams());
        }
        view2.setPadding(view2.getPaddingLeft(), this.G + i, view2.getPaddingRight(), view2.getPaddingBottom());
        return s81Var;
    }

    public String toString() {
        switch (this.E) {
            case 1:
                StringBuffer stringBuffer = new StringBuffer("[");
                int i = 0;
                while (true) {
                    int i2 = this.F;
                    if (i >= i2) {
                        stringBuffer.append("]");
                        return stringBuffer.toString();
                    }
                    stringBuffer.append(((l0[]) this.H)[i].toString());
                    if (i != i2 - 1) {
                        stringBuffer.append(", ");
                    }
                    i++;
                }
                break;
            default:
                return super.toString();
        }
    }

    public gd0(fb0 fb0Var, byte b) {
        int i = fb0Var.a;
        this.F = i;
        this.G = fb0Var.b;
        this.H = new l0[i];
        for (int i2 = 0; i2 < this.F; i2++) {
            ((l0[]) this.H)[i2] = new l0(fb0Var);
        }
    }

    public gd0(View view, int i, int i2) {
        this.F = i;
        this.H = view;
        this.G = i2;
    }
}
