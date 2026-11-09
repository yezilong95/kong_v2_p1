package defpackage;

import androidx.work.impl.WorkDatabaseVersions;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.MalformedJsonException;
import java.io.Closeable;
import java.io.IOException;
import java.io.StringReader;

/* JADX INFO: loaded from: classes.dex */
public class a60 implements Closeable {
    public static final char[] T = ")]}'\n".toCharArray();
    public final StringReader E;
    public long M;
    public int N;
    public String O;
    public int[] P;
    public String[] R;
    public int[] S;
    public boolean F = false;
    public final char[] G = new char[1024];
    public int H = 0;
    public int I = 0;
    public int J = 0;
    public int K = 0;
    public int L = 0;
    public int Q = 1;

    static {
        di.G = new di(19);
    }

    public a60(StringReader stringReader) {
        int[] iArr = new int[32];
        this.P = iArr;
        iArr[0] = 6;
        this.R = new String[32];
        this.S = new int[32];
        this.E = stringReader;
    }

    public final boolean A(int i) throws IOException {
        int i2;
        int i3;
        int i4 = this.K;
        int i5 = this.H;
        this.K = i4 - i5;
        int i6 = this.I;
        char[] cArr = this.G;
        if (i6 != i5) {
            int i7 = i6 - i5;
            this.I = i7;
            System.arraycopy(cArr, i5, cArr, 0, i7);
        } else {
            this.I = 0;
        }
        this.H = 0;
        do {
            int i8 = this.I;
            int i9 = this.E.read(cArr, i8, cArr.length - i8);
            if (i9 == -1) {
                return false;
            }
            i2 = this.I + i9;
            this.I = i2;
            if (this.J == 0 && (i3 = this.K) == 0 && i2 > 0 && cArr[0] == 65279) {
                this.H++;
                this.K = i3 + 1;
                i++;
            }
        } while (i2 < i);
        return true;
    }

    public final boolean B() throws IOException {
        int iX = this.L;
        if (iX == 0) {
            iX = x();
        }
        return (iX == 2 || iX == 4) ? false : true;
    }

    public final boolean C(char c) throws MalformedJsonException {
        if (c == '\t' || c == '\n' || c == '\f' || c == '\r' || c == ' ') {
            return false;
        }
        if (c != '#') {
            if (c == ',') {
                return false;
            }
            if (c != '/' && c != '=') {
                if (c == '{' || c == '}' || c == ':') {
                    return false;
                }
                if (c != ';') {
                    switch (c) {
                        case '[':
                        case ']':
                            return false;
                        case '\\':
                            break;
                        default:
                            return true;
                    }
                }
            }
        }
        w();
        return false;
    }

    public final String D() {
        int i = this.J + 1;
        int i2 = (this.H - this.K) + 1;
        StringBuilder sb = new StringBuilder(" at line ");
        sb.append(i);
        sb.append(" column ");
        sb.append(i2);
        sb.append(" path ");
        StringBuilder sb2 = new StringBuilder("$");
        int i3 = this.Q;
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = this.P[i4];
            if (i5 == 1 || i5 == 2) {
                sb2.append('[');
                sb2.append(this.S[i4]);
                sb2.append(']');
            } else if (i5 == 3 || i5 == 4 || i5 == 5) {
                sb2.append('.');
                String str = this.R[i4];
                if (str != null) {
                    sb2.append(str);
                }
            }
        }
        sb.append(sb2.toString());
        return sb.toString();
    }

    public final boolean E() {
        int iX = this.L;
        if (iX == 0) {
            iX = x();
        }
        if (iX == 5) {
            this.L = 0;
            int[] iArr = this.S;
            int i = this.Q - 1;
            iArr[i] = iArr[i] + 1;
            return true;
        }
        if (iX != 6) {
            throw new IllegalStateException("Expected a boolean but was " + O() + D());
        }
        this.L = 0;
        int[] iArr2 = this.S;
        int i2 = this.Q - 1;
        iArr2[i2] = iArr2[i2] + 1;
        return false;
    }

    public final double F() throws IOException {
        int iX = this.L;
        if (iX == 0) {
            iX = x();
        }
        if (iX == 15) {
            this.L = 0;
            int[] iArr = this.S;
            int i = this.Q - 1;
            iArr[i] = iArr[i] + 1;
            return this.M;
        }
        if (iX == 16) {
            this.O = new String(this.G, this.H, this.N);
            this.H += this.N;
        } else if (iX == 8 || iX == 9) {
            this.O = L(iX == 8 ? '\'' : '\"');
        } else if (iX == 10) {
            this.O = N();
        } else if (iX != 11) {
            throw new IllegalStateException("Expected a double but was " + O() + D());
        }
        this.L = 11;
        double d = Double.parseDouble(this.O);
        if (!this.F && (Double.isNaN(d) || Double.isInfinite(d))) {
            throw new MalformedJsonException("JSON forbids NaN and infinities: " + d + D());
        }
        this.O = null;
        this.L = 0;
        int[] iArr2 = this.S;
        int i2 = this.Q - 1;
        iArr2[i2] = iArr2[i2] + 1;
        return d;
    }

    public final int G() {
        int iX = this.L;
        if (iX == 0) {
            iX = x();
        }
        if (iX == 15) {
            long j = this.M;
            int i = (int) j;
            if (j != i) {
                throw new NumberFormatException("Expected an int but was " + this.M + D());
            }
            this.L = 0;
            int[] iArr = this.S;
            int i2 = this.Q - 1;
            iArr[i2] = iArr[i2] + 1;
            return i;
        }
        if (iX == 16) {
            this.O = new String(this.G, this.H, this.N);
            this.H += this.N;
        } else {
            if (iX != 8 && iX != 9 && iX != 10) {
                throw new IllegalStateException("Expected an int but was " + O() + D());
            }
            if (iX == 10) {
                this.O = N();
            } else {
                this.O = L(iX == 8 ? '\'' : '\"');
            }
            try {
                int i3 = Integer.parseInt(this.O);
                this.L = 0;
                int[] iArr2 = this.S;
                int i4 = this.Q - 1;
                iArr2[i4] = iArr2[i4] + 1;
                return i3;
            } catch (NumberFormatException unused) {
            }
        }
        this.L = 11;
        double d = Double.parseDouble(this.O);
        int i5 = (int) d;
        if (i5 != d) {
            throw new NumberFormatException("Expected an int but was " + this.O + D());
        }
        this.O = null;
        this.L = 0;
        int[] iArr3 = this.S;
        int i6 = this.Q - 1;
        iArr3[i6] = iArr3[i6] + 1;
        return i5;
    }

    public final long H() throws IOException {
        int iX = this.L;
        if (iX == 0) {
            iX = x();
        }
        if (iX == 15) {
            this.L = 0;
            int[] iArr = this.S;
            int i = this.Q - 1;
            iArr[i] = iArr[i] + 1;
            return this.M;
        }
        if (iX == 16) {
            this.O = new String(this.G, this.H, this.N);
            this.H += this.N;
        } else {
            if (iX != 8 && iX != 9 && iX != 10) {
                throw new IllegalStateException("Expected a long but was " + O() + D());
            }
            if (iX == 10) {
                this.O = N();
            } else {
                this.O = L(iX == 8 ? '\'' : '\"');
            }
            try {
                long j = Long.parseLong(this.O);
                this.L = 0;
                int[] iArr2 = this.S;
                int i2 = this.Q - 1;
                iArr2[i2] = iArr2[i2] + 1;
                return j;
            } catch (NumberFormatException unused) {
            }
        }
        this.L = 11;
        double d = Double.parseDouble(this.O);
        long j2 = (long) d;
        if (j2 != d) {
            throw new NumberFormatException("Expected a long but was " + this.O + D());
        }
        this.O = null;
        this.L = 0;
        int[] iArr3 = this.S;
        int i3 = this.Q - 1;
        iArr3[i3] = iArr3[i3] + 1;
        return j2;
    }

    public final String I() {
        String strL;
        int iX = this.L;
        if (iX == 0) {
            iX = x();
        }
        if (iX == 14) {
            strL = N();
        } else if (iX == 12) {
            strL = L('\'');
        } else {
            if (iX != 13) {
                throw new IllegalStateException("Expected a name but was " + O() + D());
            }
            strL = L('\"');
        }
        this.L = 0;
        this.R[this.Q - 1] = strL;
        return strL;
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0074, code lost:
    
        return r5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int J(boolean r10) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 226
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.a60.J(boolean):int");
    }

    public final void K() {
        int iX = this.L;
        if (iX == 0) {
            iX = x();
        }
        if (iX != 7) {
            throw new IllegalStateException("Expected null but was " + O() + D());
        }
        this.L = 0;
        int[] iArr = this.S;
        int i = this.Q - 1;
        iArr[i] = iArr[i] + 1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x002d, code lost:
    
        r10.H = r8;
        r8 = r8 - r3;
        r2 = r8 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0032, code lost:
    
        if (r1 != null) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0034, code lost:
    
        r1 = new java.lang.StringBuilder(java.lang.Math.max(r8 * 2, 16));
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x005b, code lost:
    
        if (r1 != null) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x005d, code lost:
    
        r1 = new java.lang.StringBuilder(java.lang.Math.max((r2 - r3) * 2, 16));
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x006b, code lost:
    
        r1.append(r5, r3, r2 - r3);
        r10.H = r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String L(char r11) throws com.google.gson.stream.MalformedJsonException {
        /*
            r10 = this;
            r0 = 0
            r1 = r0
        L2:
            int r2 = r10.H
            int r3 = r10.I
        L6:
            r4 = r3
            r3 = r2
        L8:
            char[] r5 = r10.G
            r6 = 1
            r7 = 16
            if (r2 >= r4) goto L5b
            int r8 = r2 + 1
            char r2 = r5[r2]
            if (r2 != r11) goto L29
            r10.H = r8
            int r8 = r8 - r3
            int r8 = r8 - r6
            if (r1 != 0) goto L21
            java.lang.String r11 = new java.lang.String
            r11.<init>(r5, r3, r8)
            return r11
        L21:
            r1.append(r5, r3, r8)
            java.lang.String r11 = r1.toString()
            return r11
        L29:
            r9 = 92
            if (r2 != r9) goto L4e
            r10.H = r8
            int r8 = r8 - r3
            int r2 = r8 + (-1)
            if (r1 != 0) goto L3f
            int r8 = r8 * 2
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            int r4 = java.lang.Math.max(r8, r7)
            r1.<init>(r4)
        L3f:
            r1.append(r5, r3, r2)
            char r2 = r10.Q()
            r1.append(r2)
            int r2 = r10.H
            int r3 = r10.I
            goto L6
        L4e:
            r5 = 10
            if (r2 != r5) goto L59
            int r2 = r10.J
            int r2 = r2 + r6
            r10.J = r2
            r10.K = r8
        L59:
            r2 = r8
            goto L8
        L5b:
            if (r1 != 0) goto L6b
            int r1 = r2 - r3
            int r1 = r1 * 2
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            int r1 = java.lang.Math.max(r1, r7)
            r4.<init>(r1)
            r1 = r4
        L6b:
            int r4 = r2 - r3
            r1.append(r5, r3, r4)
            r10.H = r2
            boolean r2 = r10.A(r6)
            if (r2 == 0) goto L79
            goto L2
        L79:
            java.lang.String r11 = "Unterminated string"
            r10.U(r11)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.a60.L(char):java.lang.String");
    }

    public final String M() {
        String str;
        int iX = this.L;
        if (iX == 0) {
            iX = x();
        }
        if (iX == 10) {
            str = N();
        } else if (iX == 8) {
            str = L('\'');
        } else if (iX == 9) {
            str = L('\"');
        } else if (iX == 11) {
            str = this.O;
            this.O = null;
        } else if (iX == 15) {
            str = Long.toString(this.M);
        } else {
            if (iX != 16) {
                throw new IllegalStateException("Expected a string but was " + O() + D());
            }
            str = new String(this.G, this.H, this.N);
            this.H += this.N;
        }
        this.L = 0;
        int[] iArr = this.S;
        int i = this.Q - 1;
        iArr[i] = iArr[i] + 1;
        return str;
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x004a, code lost:
    
        w();
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:32:0x0044. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:46:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0084  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String N() throws com.google.gson.stream.MalformedJsonException {
        /*
            r7 = this;
            r0 = 0
            r1 = 0
        L2:
            r2 = r1
        L3:
            int r3 = r7.H
            int r4 = r3 + r2
            int r5 = r7.I
            char[] r6 = r7.G
            if (r4 >= r5) goto L4e
            int r3 = r3 + r2
            char r3 = r6[r3]
            r4 = 9
            if (r3 == r4) goto L5a
            r4 = 10
            if (r3 == r4) goto L5a
            r4 = 12
            if (r3 == r4) goto L5a
            r4 = 13
            if (r3 == r4) goto L5a
            r4 = 32
            if (r3 == r4) goto L5a
            r4 = 35
            if (r3 == r4) goto L4a
            r4 = 44
            if (r3 == r4) goto L5a
            r4 = 47
            if (r3 == r4) goto L4a
            r4 = 61
            if (r3 == r4) goto L4a
            r4 = 123(0x7b, float:1.72E-43)
            if (r3 == r4) goto L5a
            r4 = 125(0x7d, float:1.75E-43)
            if (r3 == r4) goto L5a
            r4 = 58
            if (r3 == r4) goto L5a
            r4 = 59
            if (r3 == r4) goto L4a
            switch(r3) {
                case 91: goto L5a;
                case 92: goto L4a;
                case 93: goto L5a;
                default: goto L47;
            }
        L47:
            int r2 = r2 + 1
            goto L3
        L4a:
            r7.w()
            goto L5a
        L4e:
            int r3 = r6.length
            if (r2 >= r3) goto L5c
            int r3 = r2 + 1
            boolean r3 = r7.A(r3)
            if (r3 == 0) goto L5a
            goto L3
        L5a:
            r1 = r2
            goto L7a
        L5c:
            if (r0 != 0) goto L69
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r3 = 16
            int r3 = java.lang.Math.max(r2, r3)
            r0.<init>(r3)
        L69:
            int r3 = r7.H
            r0.append(r6, r3, r2)
            int r3 = r7.H
            int r3 = r3 + r2
            r7.H = r3
            r2 = 1
            boolean r2 = r7.A(r2)
            if (r2 != 0) goto L2
        L7a:
            if (r0 != 0) goto L84
            java.lang.String r0 = new java.lang.String
            int r2 = r7.H
            r0.<init>(r6, r2, r1)
            goto L8d
        L84:
            int r2 = r7.H
            r0.append(r6, r2, r1)
            java.lang.String r0 = r0.toString()
        L8d:
            int r2 = r7.H
            int r2 = r2 + r1
            r7.H = r2
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.a60.N():java.lang.String");
    }

    public final JsonToken O() {
        int iX = this.L;
        if (iX == 0) {
            iX = x();
        }
        switch (iX) {
            case 1:
                return JsonToken.BEGIN_OBJECT;
            case 2:
                return JsonToken.END_OBJECT;
            case 3:
                return JsonToken.BEGIN_ARRAY;
            case 4:
                return JsonToken.END_ARRAY;
            case 5:
            case 6:
                return JsonToken.BOOLEAN;
            case 7:
                return JsonToken.NULL;
            case 8:
            case WorkDatabaseVersions.VERSION_9 /* 9 */:
            case 10:
            case WorkDatabaseVersions.VERSION_11 /* 11 */:
                return JsonToken.STRING;
            case WorkDatabaseVersions.VERSION_12 /* 12 */:
            case WorkDatabaseVersions.VERSION_13 /* 13 */:
            case WorkDatabaseVersions.VERSION_14 /* 14 */:
                return JsonToken.NAME;
            case WorkDatabaseVersions.VERSION_15 /* 15 */:
            case 16:
                return JsonToken.NUMBER;
            case 17:
                return JsonToken.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    public final void P(int i) {
        int i2 = this.Q;
        int[] iArr = this.P;
        if (i2 == iArr.length) {
            int i3 = i2 * 2;
            int[] iArr2 = new int[i3];
            int[] iArr3 = new int[i3];
            String[] strArr = new String[i3];
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            System.arraycopy(this.S, 0, iArr3, 0, this.Q);
            System.arraycopy(this.R, 0, strArr, 0, this.Q);
            this.P = iArr2;
            this.S = iArr3;
            this.R = strArr;
        }
        int[] iArr4 = this.P;
        int i4 = this.Q;
        this.Q = i4 + 1;
        iArr4[i4] = i;
    }

    public final char Q() throws MalformedJsonException {
        int i;
        if (this.H == this.I && !A(1)) {
            U("Unterminated escape sequence");
            throw null;
        }
        int i2 = this.H;
        int i3 = i2 + 1;
        this.H = i3;
        char[] cArr = this.G;
        char c = cArr[i2];
        if (c == '\n') {
            this.J++;
            this.K = i3;
            return c;
        }
        if (c == '\"' || c == '\'' || c == '/' || c == '\\') {
            return c;
        }
        if (c == 'b') {
            return '\b';
        }
        if (c == 'f') {
            return '\f';
        }
        if (c == 'n') {
            return '\n';
        }
        if (c == 'r') {
            return '\r';
        }
        if (c == 't') {
            return '\t';
        }
        if (c != 'u') {
            U("Invalid escape sequence");
            throw null;
        }
        if (i2 + 5 > this.I && !A(4)) {
            U("Unterminated escape sequence");
            throw null;
        }
        int i4 = this.H;
        int i5 = i4 + 4;
        char c2 = 0;
        while (i4 < i5) {
            char c3 = cArr[i4];
            char c4 = (char) (c2 << 4);
            if (c3 >= '0' && c3 <= '9') {
                i = c3 - '0';
            } else if (c3 >= 'a' && c3 <= 'f') {
                i = c3 - 'W';
            } else {
                if (c3 < 'A' || c3 > 'F') {
                    throw new NumberFormatException("\\u".concat(new String(cArr, this.H, 4)));
                }
                i = c3 - '7';
            }
            c2 = (char) (i + c4);
            i4++;
        }
        this.H += 4;
        return c2;
    }

    public final void R(char c) throws MalformedJsonException {
        do {
            int i = this.H;
            int i2 = this.I;
            while (i < i2) {
                int i3 = i + 1;
                char c2 = this.G[i];
                if (c2 == c) {
                    this.H = i3;
                    return;
                }
                if (c2 == '\\') {
                    this.H = i3;
                    Q();
                    i = this.H;
                    i2 = this.I;
                } else {
                    if (c2 == '\n') {
                        this.J++;
                        this.K = i3;
                    }
                    i = i3;
                }
            }
            this.H = i;
        } while (A(1));
        U("Unterminated string");
        throw null;
    }

    public final void S() {
        char c;
        do {
            if (this.H >= this.I && !A(1)) {
                return;
            }
            int i = this.H;
            int i2 = i + 1;
            this.H = i2;
            c = this.G[i];
            if (c == '\n') {
                this.J++;
                this.K = i2;
                return;
            }
        } while (c != '\r');
    }

    /* JADX WARN: Code restructure failed: missing block: B:63:0x00a1, code lost:
    
        w();
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:61:0x009b. Please report as an issue. */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void T() throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 212
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.a60.T():void");
    }

    public final void U(String str) throws MalformedJsonException {
        StringBuilder sbP = zn.p(str);
        sbP.append(D());
        throw new MalformedJsonException(sbP.toString());
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.L = 0;
        this.P[0] = 8;
        this.Q = 1;
        this.E.close();
    }

    public final void j() {
        int iX = this.L;
        if (iX == 0) {
            iX = x();
        }
        if (iX == 3) {
            P(1);
            this.S[this.Q - 1] = 0;
            this.L = 0;
        } else {
            throw new IllegalStateException("Expected BEGIN_ARRAY but was " + O() + D());
        }
    }

    public final void l() {
        int iX = this.L;
        if (iX == 0) {
            iX = x();
        }
        if (iX == 1) {
            P(3);
            this.L = 0;
        } else {
            throw new IllegalStateException("Expected BEGIN_OBJECT but was " + O() + D());
        }
    }

    public final String toString() {
        return a60.class.getSimpleName() + D();
    }

    public final void w() throws MalformedJsonException {
        if (this.F) {
            return;
        }
        U("Use JsonReader.setLenient(true) to accept malformed JSON");
        throw null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:134:0x01b4, code lost:
    
        r22 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:163:0x020a, code lost:
    
        if (C(r15) != false) goto L115;
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x020c, code lost:
    
        r6 = 2;
     */
    /* JADX WARN: Removed duplicated region for block: B:109:0x016c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:110:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x025d  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x0269 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:205:0x026a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int x() throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 791
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.a60.x():int");
    }

    public final void y() {
        int iX = this.L;
        if (iX == 0) {
            iX = x();
        }
        if (iX != 4) {
            throw new IllegalStateException("Expected END_ARRAY but was " + O() + D());
        }
        int i = this.Q;
        this.Q = i - 1;
        int[] iArr = this.S;
        int i2 = i - 2;
        iArr[i2] = iArr[i2] + 1;
        this.L = 0;
    }

    public final void z() {
        int iX = this.L;
        if (iX == 0) {
            iX = x();
        }
        if (iX != 2) {
            throw new IllegalStateException("Expected END_OBJECT but was " + O() + D());
        }
        int i = this.Q;
        int i2 = i - 1;
        this.Q = i2;
        this.R[i2] = null;
        int[] iArr = this.S;
        int i3 = i - 2;
        iArr[i3] = iArr[i3] + 1;
        this.L = 0;
    }
}
