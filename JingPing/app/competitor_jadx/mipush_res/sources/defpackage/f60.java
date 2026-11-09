package defpackage;

import io.nats.client.support.JsonUtils;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;
import kotlin.uuid.Uuid;

/* JADX INFO: loaded from: classes.dex */
public class f60 implements Closeable, Flushable {
    public static final String[] M = new String[Uuid.SIZE_BITS];
    public static final String[] N;
    public final Writer E;
    public int[] F = new int[32];
    public int G = 0;
    public final String H;
    public boolean I;
    public boolean J;
    public String K;
    public boolean L;

    static {
        for (int i = 0; i <= 31; i++) {
            M[i] = String.format("\\u%04x", Integer.valueOf(i));
        }
        String[] strArr = M;
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
        String[] strArr2 = (String[]) strArr.clone();
        N = strArr2;
        strArr2[60] = "\\u003c";
        strArr2[62] = "\\u003e";
        strArr2[38] = "\\u0026";
        strArr2[61] = "\\u003d";
        strArr2[39] = "\\u0027";
    }

    public f60(Writer writer) {
        E(6);
        this.H = ":";
        this.L = true;
        if (writer == null) {
            throw new NullPointerException("out == null");
        }
        this.E = writer;
    }

    public void A(String str) {
        if (str == null) {
            throw new NullPointerException("name == null");
        }
        if (this.K != null) {
            throw new IllegalStateException();
        }
        if (this.G == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.K = str;
    }

    public f60 C() throws IOException {
        if (this.K != null) {
            if (!this.L) {
                this.K = null;
                return this;
            }
            K();
        }
        j();
        this.E.write("null");
        return this;
    }

    public final int D() {
        int i = this.G;
        if (i != 0) {
            return this.F[i - 1];
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    public final void E(int i) {
        int i2 = this.G;
        int[] iArr = this.F;
        if (i2 == iArr.length) {
            int[] iArr2 = new int[i2 * 2];
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            this.F = iArr2;
        }
        int[] iArr3 = this.F;
        int i3 = this.G;
        this.G = i3 + 1;
        iArr3[i3] = i;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void F(java.lang.String r9) throws java.io.IOException {
        /*
            r8 = this;
            boolean r0 = r8.J
            if (r0 == 0) goto L7
            java.lang.String[] r0 = defpackage.f60.N
            goto L9
        L7:
            java.lang.String[] r0 = defpackage.f60.M
        L9:
            java.io.Writer r1 = r8.E
            java.lang.String r2 = "\""
            r1.write(r2)
            int r3 = r9.length()
            r4 = 0
            r5 = r4
        L16:
            if (r4 >= r3) goto L41
            char r6 = r9.charAt(r4)
            r7 = 128(0x80, float:1.8E-43)
            if (r6 >= r7) goto L25
            r6 = r0[r6]
            if (r6 != 0) goto L32
            goto L3e
        L25:
            r7 = 8232(0x2028, float:1.1535E-41)
            if (r6 != r7) goto L2c
            java.lang.String r6 = "\\u2028"
            goto L32
        L2c:
            r7 = 8233(0x2029, float:1.1537E-41)
            if (r6 != r7) goto L3e
            java.lang.String r6 = "\\u2029"
        L32:
            if (r5 >= r4) goto L39
            int r7 = r4 - r5
            r1.write(r9, r5, r7)
        L39:
            r1.write(r6)
            int r5 = r4 + 1
        L3e:
            int r4 = r4 + 1
            goto L16
        L41:
            if (r5 >= r3) goto L47
            int r3 = r3 - r5
            r1.write(r9, r5, r3)
        L47:
            r1.write(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.f60.F(java.lang.String):void");
    }

    public void G(long j) throws IOException {
        K();
        j();
        this.E.write(Long.toString(j));
    }

    public void H(Number number) throws IOException {
        if (number == null) {
            C();
            return;
        }
        K();
        String string = number.toString();
        if (this.I || !(string.equals("-Infinity") || string.equals("Infinity") || string.equals("NaN"))) {
            j();
            this.E.append((CharSequence) string);
        } else {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + number);
        }
    }

    public void I(String str) throws IOException {
        if (str == null) {
            C();
            return;
        }
        K();
        j();
        F(str);
    }

    public void J(boolean z) throws IOException {
        K();
        j();
        this.E.write(z ? "true" : "false");
    }

    public final void K() throws IOException {
        if (this.K != null) {
            int iD = D();
            if (iD == 5) {
                this.E.write(44);
            } else if (iD != 3) {
                throw new IllegalStateException("Nesting problem.");
            }
            B();
            this.F[this.G - 1] = 4;
            F(this.K);
            this.K = null;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.E.close();
        int i = this.G;
        if (i > 1 || (i == 1 && this.F[i - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.G = 0;
    }

    @Override // java.io.Flushable
    public void flush() throws IOException {
        if (this.G == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.E.flush();
    }

    public final void j() throws IOException {
        int iD = D();
        if (iD == 1) {
            this.F[this.G - 1] = 2;
            B();
            return;
        }
        Writer writer = this.E;
        if (iD == 2) {
            writer.append(',');
            B();
        } else {
            if (iD == 4) {
                writer.append((CharSequence) this.H);
                this.F[this.G - 1] = 5;
                return;
            }
            if (iD != 6) {
                if (iD != 7) {
                    throw new IllegalStateException("Nesting problem.");
                }
                if (!this.I) {
                    throw new IllegalStateException("JSON must have only one top-level value.");
                }
            }
            this.F[this.G - 1] = 7;
        }
    }

    public void l() throws IOException {
        K();
        j();
        E(1);
        this.E.write("[");
    }

    public void w() throws IOException {
        K();
        j();
        E(3);
        this.E.write("{");
    }

    public final void x(int i, int i2, String str) throws IOException {
        int iD = D();
        if (iD != i2 && iD != i) {
            throw new IllegalStateException("Nesting problem.");
        }
        if (this.K != null) {
            throw new IllegalStateException("Dangling name: " + this.K);
        }
        this.G--;
        if (iD == i2) {
            B();
        }
        this.E.write(str);
    }

    public void y() throws IOException {
        x(1, 2, "]");
    }

    public void z() throws IOException {
        x(3, 5, JsonUtils.CLOSE);
    }

    public final void B() {
    }
}
