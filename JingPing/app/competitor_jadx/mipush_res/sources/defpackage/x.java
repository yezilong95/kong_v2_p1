package defpackage;

import androidx.work.Configuration;
import androidx.work.impl.WorkDatabaseVersions;
import androidx.work.impl.WorkManagerImpl;
import io.nats.client.BaseConsumeOptions;
import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.uuid.Uuid;
import org.bouncycastle.asn1.ASN1Exception;

/* JADX INFO: loaded from: classes.dex */
public final class x extends FilterInputStream {
    public final int E;
    public final boolean F;
    public final byte[][] G;

    public x(ByteArrayInputStream byteArrayInputStream, int i, boolean z) {
        this(byteArrayInputStream, i, z, new byte[11][]);
    }

    public static int A(InputStream inputStream, int i) {
        int i2 = i & 31;
        if (i2 != 31) {
            return i2;
        }
        int i3 = inputStream.read();
        if (i3 < 31) {
            if (i3 < 0) {
                throw new EOFException("EOF found inside tag value.");
            }
            throw new IOException("corrupted stream - high tag number < 31 found");
        }
        int i4 = i3 & 127;
        if (i4 == 0) {
            throw new IOException("corrupted stream - invalid high tag number found");
        }
        while ((i3 & Uuid.SIZE_BITS) != 0) {
            if ((i4 >>> 24) != 0) {
                throw new IOException("Tag number more than 31 bits");
            }
            int i5 = i4 << 7;
            int i6 = inputStream.read();
            if (i6 < 0) {
                throw new EOFException("EOF found inside tag value.");
            }
            i4 = i5 | (i6 & 127);
            i3 = i6;
        }
        return i4;
    }

    public static g0 l(int i, sn snVar, byte[][] bArr) throws IOException {
        try {
            switch (i) {
                case 1:
                    return r.o(x(snVar, bArr));
                case 2:
                    return new y(snVar.l());
                case 3:
                    return p.o(snVar.l());
                case 4:
                    return new tk(snVar.l());
                case 5:
                    if (snVar.l().length == 0) {
                        return rk.E;
                    }
                    throw new IllegalStateException("malformed NULL encoding encountered");
                case 6:
                    d0.p(snVar.H);
                    return d0.q(x(snVar, bArr), true);
                case 7:
                    return new b0(new pk(snVar.l()));
                case 8:
                case WorkDatabaseVersions.VERSION_9 /* 9 */:
                case WorkDatabaseVersions.VERSION_11 /* 11 */:
                case WorkDatabaseVersions.VERSION_15 /* 15 */:
                case 16:
                case 17:
                case 29:
                default:
                    throw new IOException("unknown tag " + i + " encountered");
                case 10:
                    return v.o(x(snVar, bArr), true);
                case WorkDatabaseVersions.VERSION_12 /* 12 */:
                    return new zk(snVar.l());
                case WorkDatabaseVersions.VERSION_13 /* 13 */:
                    int i2 = snVar.H;
                    ConcurrentHashMap concurrentHashMap = h0.G;
                    if (i2 <= 4096) {
                        return h0.o(x(snVar, bArr), true);
                    }
                    throw new IllegalArgumentException("exceeded relative OID contents length limit");
                case WorkDatabaseVersions.VERSION_14 /* 14 */:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                    throw new IOException("unsupported tag " + i + " encountered");
                case 18:
                    return new sk(snVar.l());
                case 19:
                    return new vk(snVar.l());
                case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                    return new yk(snVar.l());
                case 21:
                    return new bl(snVar.l());
                case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                    return new qk(snVar.l());
                case WorkManagerImpl.MIN_JOB_SCHEDULER_API_LEVEL /* 23 */:
                    return new m0(snVar.l());
                case 24:
                    return new w(snVar.l());
                case BaseConsumeOptions.DEFAULT_THRESHOLD_PERCENT /* 25 */:
                    return new pk(snVar.l());
                case 26:
                    return new cl(snVar.l());
                case 27:
                    return new nk(snVar.l());
                case 28:
                    return new al(snVar.l());
                case 30:
                    return new kk(w(snVar));
            }
        } catch (IllegalArgumentException e) {
            throw new ASN1Exception(e, e.getMessage());
        } catch (IllegalStateException e2) {
            throw new ASN1Exception(e2, e2.getMessage());
        }
    }

    public static char[] w(sn snVar) throws IOException {
        int i = snVar.H;
        if ((i & 1) != 0) {
            throw new IOException("malformed BMPString encoding encountered");
        }
        int i2 = i / 2;
        char[] cArr = new char[i2];
        byte[] bArr = new byte[8];
        int i3 = 0;
        int i4 = 0;
        while (i >= 8) {
            if (la0.a0(snVar, bArr, 8) != 8) {
                throw new EOFException("EOF encountered in middle of BMPString");
            }
            cArr[i4] = (char) ((bArr[0] << 8) | (bArr[1] & 255));
            cArr[i4 + 1] = (char) ((bArr[2] << 8) | (bArr[3] & 255));
            cArr[i4 + 2] = (char) ((bArr[4] << 8) | (bArr[5] & 255));
            cArr[i4 + 3] = (char) ((bArr[6] << 8) | (bArr[7] & 255));
            i4 += 4;
            i -= 8;
        }
        if (i > 0) {
            if (la0.a0(snVar, bArr, i) != i) {
                throw new EOFException("EOF encountered in middle of BMPString");
            }
            do {
                int i5 = i3 + 1;
                int i6 = bArr[i3] << 8;
                i3 += 2;
                cArr[i4] = (char) ((bArr[i5] & 255) | i6);
                i4++;
            } while (i3 < i);
        }
        if (snVar.H == 0 && i2 == i4) {
            return cArr;
        }
        throw new IllegalStateException();
    }

    public static byte[] x(sn snVar, byte[][] bArr) throws IOException {
        int i = snVar.H;
        if (i >= bArr.length) {
            return snVar.l();
        }
        byte[] bArr2 = bArr[i];
        if (bArr2 == null) {
            bArr2 = new byte[i];
            bArr[i] = bArr2;
        }
        if (i != bArr2.length) {
            throw new IllegalArgumentException("buffer length not right for data");
        }
        if (i == 0) {
            return bArr2;
        }
        int i2 = snVar.F;
        if (i >= i2) {
            throw new IOException("corrupted stream - out of bounds length found: " + snVar.H + " >= " + i2);
        }
        int iA0 = i - la0.a0(snVar.E, bArr2, bArr2.length);
        snVar.H = iA0;
        if (iA0 == 0) {
            snVar.j();
            return bArr2;
        }
        throw new EOFException("DEF length " + snVar.G + " object truncated by " + snVar.H);
    }

    public static int y(InputStream inputStream, int i, boolean z) {
        int i2 = inputStream.read();
        if ((i2 >>> 7) == 0) {
            return i2;
        }
        if (128 == i2) {
            return -1;
        }
        if (i2 < 0) {
            throw new EOFException("EOF found when length expected");
        }
        if (255 == i2) {
            throw new IOException("invalid long form definite-length 0xFF");
        }
        int i3 = i2 & 127;
        int i4 = 0;
        int i5 = 0;
        do {
            int i6 = inputStream.read();
            if (i6 < 0) {
                throw new EOFException("EOF found reading length");
            }
            if ((i4 >>> 23) != 0) {
                throw new IOException("long form definite-length more than 31 bits");
            }
            i4 = (i4 << 8) + i6;
            i5++;
        } while (i5 < i3);
        if (i4 < i || z) {
            return i4;
        }
        throw new IOException(zn.l("corrupted stream - out of bounds length found: ", i4, i, " >= "));
    }

    public final u B(sn snVar) {
        int i = snVar.H;
        if (i < 1) {
            return new u(0);
        }
        x xVar = new x(snVar, i, this.F, this.G);
        g0 g0VarZ = xVar.z();
        if (g0VarZ == null) {
            return new u(0);
        }
        u uVar = new u();
        do {
            uVar.c(g0VarZ);
            g0VarZ = xVar.z();
        } while (g0VarZ != null);
        return uVar;
    }

    public final g0 j(int i, int i2, int i3) throws IOException {
        sn snVar = new sn(this, i3, this.E);
        if ((i & 224) == 0) {
            return l(i2, snVar, this.G);
        }
        int i4 = i & 192;
        int i5 = 3;
        int i6 = 0;
        int i7 = 4;
        if (i4 != 0) {
            if ((i & 32) == 0) {
                return new j9(i7, i4, i2, new tk(snVar.l()), 2);
            }
            u uVarB = B(snVar);
            return uVarB.b == 1 ? new j9(i5, i4, i2, uVarB.d(0), 2) : new j9(i7, i4, i2, ll.a(uVarB), 2);
        }
        if (i2 == 3) {
            u uVarB2 = B(snVar);
            int i8 = uVarB2.b;
            p[] pVarArr = new p[i8];
            while (i6 != i8) {
                t tVarD = uVarB2.d(i6);
                if (!(tVarD instanceof p)) {
                    throw new ASN1Exception("unknown object encountered in constructed BIT STRING: " + tVarD.getClass());
                }
                pVarArr[i6] = (p) tVarD;
                i6++;
            }
            return new b9(pVarArr);
        }
        if (i2 == 4) {
            u uVarB3 = B(snVar);
            int i9 = uVarB3.b;
            e0[] e0VarArr = new e0[i9];
            while (i6 != i9) {
                t tVarD2 = uVarB3.d(i6);
                if (!(tVarD2 instanceof e0)) {
                    throw new ASN1Exception("unknown object encountered in constructed OCTET STRING: " + tVarD2.getClass());
                }
                e0VarArr[i6] = (e0) tVarD2;
                i6++;
            }
            return new e9(e9.p(e0VarArr), e0VarArr);
        }
        if (i2 == 8) {
            wk wkVarA = ll.a(B(snVar));
            wkVarA.getClass();
            return new mk(wkVarA, 1);
        }
        if (i2 != 16) {
            if (i2 == 17) {
                return ll.b(B(snVar));
            }
            throw new IOException(zn.k(i2, "unknown tag ", " encountered"));
        }
        if (snVar.H < 1) {
            return ll.a;
        }
        if (!this.F) {
            return ll.a(B(snVar));
        }
        byte[] bArrL = snVar.l();
        k80 k80Var = new k80();
        k80Var.G = bArrL;
        return k80Var;
    }

    public final g0 z() {
        int i = read();
        if (i <= 0) {
            if (i != 0) {
                return null;
            }
            throw new IOException("unexpected end-of-contents marker");
        }
        int iA = A(this, i);
        int i2 = this.E;
        int iY = y(this, i2, false);
        if (iY >= 0) {
            try {
                return j(i, iA, iY);
            } catch (IllegalArgumentException e) {
                throw new ASN1Exception(e, "corrupted stream detected");
            }
        }
        if ((i & 32) == 0) {
            throw new IOException("indefinite-length primitive encoding encountered");
        }
        l0 l0Var = new l0(new z30(this, i2), i2, this.G);
        int i3 = i & 192;
        if (i3 != 0) {
            return l0Var.j(i3, iA);
        }
        if (iA == 3) {
            return c9.f(l0Var);
        }
        if (iA == 4) {
            return f9.f(l0Var);
        }
        if (iA == 8) {
            return h9.f(l0Var);
        }
        if (iA == 16) {
            return new g9(l0Var.n());
        }
        if (iA == 17) {
            return new i9(l0Var.n(), false);
        }
        throw new IOException("unknown BER object encountered");
    }

    public x(InputStream inputStream, int i, boolean z, byte[][] bArr) {
        super(inputStream);
        this.E = i;
        this.F = z;
        this.G = bArr;
    }

    public x(byte[] bArr) {
        this(new ByteArrayInputStream(bArr), bArr.length, false);
    }

    public x(byte[] bArr, int i) {
        this(new ByteArrayInputStream(bArr), bArr.length, true);
    }
}
