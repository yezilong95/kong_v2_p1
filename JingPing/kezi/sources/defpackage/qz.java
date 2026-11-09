package defpackage;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.StringWriter;
import kotlin.uuid.Uuid;

/* JADX INFO: loaded from: classes.dex */
public final class qz implements Closeable, Flushable {
    public static final String[] i = new String[Uuid.SIZE_BITS];
    public final StringWriter b;
    public int[] c;
    public int d;
    public final String e;
    public boolean f;
    public String g;
    public final boolean h;

    static {
        for (int i2 = 0; i2 <= 31; i2++) {
            i[i2] = String.format("\\u%04x", Integer.valueOf(i2));
        }
        String[] strArr = i;
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
        String[] strArr2 = (String[]) strArr.clone();
        strArr2[60] = "\\u003c";
        strArr2[62] = "\\u003e";
        strArr2[38] = "\\u0026";
        strArr2[61] = "\\u003d";
        strArr2[39] = "\\u0027";
    }

    public qz(StringWriter stringWriter) {
        this.c = new int[32];
        this.d = 0;
        int i2 = this.d;
        int[] iArr = this.c;
        if (i2 == iArr.length) {
            int[] iArr2 = new int[i2 * 2];
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            this.c = iArr2;
        }
        int[] iArr3 = this.c;
        int i3 = this.d;
        this.d = i3 + 1;
        iArr3[i3] = 6;
        this.e = ":";
        this.h = true;
        this.b = stringWriter;
    }

    public final int C() {
        int i2 = this.d;
        if (i2 != 0) {
            return this.c[i2 - 1];
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void D(java.lang.String r9) throws java.io.IOException {
        /*
            r8 = this;
            java.lang.String[] r0 = defpackage.qz.i
            java.io.StringWriter r1 = r8.b
            java.lang.String r2 = "\""
            r1.write(r2)
            int r3 = r9.length()
            r4 = 0
            r5 = r4
        Lf:
            if (r4 >= r3) goto L3a
            char r6 = r9.charAt(r4)
            r7 = 128(0x80, float:1.8E-43)
            if (r6 >= r7) goto L1e
            r6 = r0[r6]
            if (r6 != 0) goto L2b
            goto L37
        L1e:
            r7 = 8232(0x2028, float:1.1535E-41)
            if (r6 != r7) goto L25
            java.lang.String r6 = "\\u2028"
            goto L2b
        L25:
            r7 = 8233(0x2029, float:1.1537E-41)
            if (r6 != r7) goto L37
            java.lang.String r6 = "\\u2029"
        L2b:
            if (r5 >= r4) goto L32
            int r7 = r4 - r5
            r1.write(r9, r5, r7)
        L32:
            r1.write(r6)
            int r5 = r4 + 1
        L37:
            int r4 = r4 + 1
            goto Lf
        L3a:
            if (r5 >= r3) goto L40
            int r3 = r3 - r5
            r1.write(r9, r5, r3)
        L40:
            r1.write(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.qz.D(java.lang.String):void");
    }

    public final void E() throws IOException {
        if (this.g != null) {
            int iC = C();
            if (iC == 5) {
                this.b.write(44);
            } else if (iC != 3) {
                throw new IllegalStateException("Nesting problem.");
            }
            this.c[this.d - 1] = 4;
            D(this.g);
            this.g = null;
        }
    }

    public final void a() throws IOException {
        int iC = C();
        if (iC == 1) {
            this.c[this.d - 1] = 2;
            return;
        }
        StringWriter stringWriter = this.b;
        if (iC == 2) {
            stringWriter.append(',');
            return;
        }
        if (iC == 4) {
            stringWriter.append((CharSequence) this.e);
            this.c[this.d - 1] = 5;
            return;
        }
        if (iC != 6) {
            if (iC != 7) {
                throw new IllegalStateException("Nesting problem.");
            }
            if (!this.f) {
                throw new IllegalStateException("JSON must have only one top-level value.");
            }
        }
        this.c[this.d - 1] = 7;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.b.close();
        int i2 = this.d;
        if (i2 > 1 || (i2 == 1 && this.c[i2 - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.d = 0;
    }

    @Override // java.io.Flushable
    public final void flush() throws IOException {
        if (this.d == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.b.flush();
    }

    public final void t() throws IOException {
        if (this.g != null) {
            if (!this.h) {
                this.g = null;
                return;
            }
            E();
        }
        a();
        this.b.write("null");
    }
}
