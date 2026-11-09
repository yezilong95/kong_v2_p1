package defpackage;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import androidx.appcompat.R$styleable;
import io.nats.client.support.NatsJetStreamConstants;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.WeakHashMap;
import org.bouncycastle.asn1.ASN1Exception;

/* JADX INFO: loaded from: classes.dex */
public final class l0 {
    public final /* synthetic */ int a;
    public int b;
    public Object c;
    public Object d;

    public l0() {
        this.a = 2;
        this.c = new WeakHashMap();
        this.d = new BigInteger[8];
        this.b = 0;
    }

    public static void c(l0 l0Var, l0 l0Var2, l0 l0Var3) {
        for (int i = 0; i < 64; i++) {
            int i2 = i * 4;
            short[] sArr = (short[]) l0Var2.c;
            short s = sArr[i2];
            int i3 = i2 + 1;
            short s2 = sArr[i3];
            short[] sArr2 = (short[]) l0Var3.c;
            short s3 = sArr2[i2];
            short s4 = sArr2[i3];
            short[] sArr3 = t40.f;
            int i4 = i + 64;
            t40.g(l0Var, i2, s, s2, s3, s4, sArr3[i4]);
            int i5 = i2 + 2;
            short[] sArr4 = (short[]) l0Var2.c;
            short s5 = sArr4[i5];
            int i6 = i2 + 3;
            short s6 = sArr4[i6];
            short[] sArr5 = (short[]) l0Var3.c;
            t40.g(l0Var, i5, s5, s6, sArr5[i5], sArr5[i6], (short) (sArr3[i4] * (-1)));
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x01f6, code lost:
    
        if (r3.size() <= 0) goto L102;
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x01f8, code lost:
    
        r0 = new defpackage.i6(r3, r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x01fe, code lost:
    
        r0 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x01ff, code lost:
    
        if (r0 == null) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x0203, code lost:
    
        if (r19 == false) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x0205, code lost:
    
        r0 = new defpackage.i6(r6, r7, r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x020b, code lost:
    
        r0 = new defpackage.i6(r6, r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x0211, code lost:
    
        if (r8 == 1) goto L120;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x0214, code lost:
    
        if (r8 == 2) goto L119;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x0218, code lost:
    
        if (r11 == 1) goto L117;
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x021a, code lost:
    
        if (r11 == 2) goto L116;
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x021c, code lost:
    
        r1 = android.graphics.Shader.TileMode.CLAMP;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x0221, code lost:
    
        r1 = android.graphics.Shader.TileMode.MIRROR;
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x0224, code lost:
    
        r1 = android.graphics.Shader.TileMode.REPEAT;
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x0227, code lost:
    
        r13 = new android.graphics.LinearGradient(r22, r25, r16, r17, (int[]) r0.F, (float[]) r0.G, r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x023b, code lost:
    
        r13 = new android.graphics.SweepGradient(r5, r10, (int[]) r0.F, (float[]) r0.G);
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x024d, code lost:
    
        if (r24 <= 0.0f) goto L131;
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x0253, code lost:
    
        if (r11 == 1) goto L127;
     */
    /* JADX WARN: Code restructure failed: missing block: B:124:0x0255, code lost:
    
        if (r11 == 2) goto L126;
     */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x0257, code lost:
    
        r1 = android.graphics.Shader.TileMode.CLAMP;
     */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x025a, code lost:
    
        r1 = android.graphics.Shader.TileMode.MIRROR;
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x025d, code lost:
    
        r1 = android.graphics.Shader.TileMode.REPEAT;
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x025f, code lost:
    
        r13 = new android.graphics.RadialGradient(r5, r10, r24, (int[]) r0.F, (float[]) r0.G, r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x027f, code lost:
    
        return new defpackage.l0(r13, (android.content.res.ColorStateList) null, 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x0287, code lost:
    
        throw new org.xmlpull.v1.XmlPullParserException("<gradient> tag requires 'gradientRadius' attribute with radial type");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static defpackage.l0 d(android.content.res.Resources r26, int r27, android.content.res.Resources.Theme r28) {
        /*
            Method dump skipped, instruction units count: 688
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.l0.d(android.content.res.Resources, int, android.content.res.Resources$Theme):l0");
    }

    public static void e(String str) {
        if (str.equalsIgnoreCase(":memory:")) {
            return;
        }
        int length = str.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            char cCharAt = str.charAt(!z ? i : length);
            boolean z2 = cCharAt < ' ' || cCharAt == ' ';
            if (z) {
                if (!z2) {
                    break;
                } else {
                    length--;
                }
            } else if (z2) {
                i++;
            } else {
                z = true;
            }
        }
        if (str.subSequence(i, length + 1).toString().length() == 0) {
            return;
        }
        Log.w("SupportSQLite", "deleting the database file: ".concat(str));
        try {
            SQLiteDatabase.deleteDatabase(new File(str));
        } catch (Exception e) {
            Log.w("SupportSQLite", "delete failed: ", e);
        }
    }

    public synchronized void a(BigInteger bigInteger) {
        ((WeakHashMap) this.c).put(bigInteger, Boolean.TRUE);
        BigInteger[] bigIntegerArr = (BigInteger[]) this.d;
        int i = this.b;
        bigIntegerArr[i] = bigInteger;
        this.b = (i + 1) % bigIntegerArr.length;
    }

    public void b() {
        x01 x01Var;
        ImageView imageView = (ImageView) this.c;
        Drawable drawable = imageView.getDrawable();
        if (drawable != null) {
            op.a(drawable);
        }
        if (drawable == null || (x01Var = (x01) this.d) == null) {
            return;
        }
        f6.e(drawable, x01Var, imageView.getDrawableState());
    }

    public void f(byte[] bArr, byte b) {
        int i = this.b;
        int i2 = (i * NatsJetStreamConstants.MAX_PULL_SIZE) / 4;
        byte[] bArr2 = new byte[i2];
        tb tbVar = (tb) this.d;
        tbVar.getClass();
        int length = bArr.length + 1;
        byte[] bArr3 = new byte[length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        bArr3[bArr.length] = b;
        gq0 gq0Var = (gq0) tbVar.d;
        gq0Var.e(bArr3, 0, length);
        gq0Var.h(bArr2, 0, i2);
        if (i != 3) {
            for (int i3 = 0; i3 < 32; i3++) {
                int i4 = i3 * 4;
                long j = ((long) (bArr2[i4] & 255)) | (((long) (bArr2[i4 + 1] & 255)) << 8) | (((long) (bArr2[i4 + 2] & 255)) << 16) | (((long) (bArr2[i4 + 3] & 255)) << 24);
                long j2 = (j & 1431655765) + ((j >> 1) & 1431655765);
                for (int i5 = 0; i5 < 8; i5++) {
                    int i6 = i5 * 4;
                    ((short[]) this.c)[(i3 * 8) + i5] = (short) (((short) ((j2 >> i6) & 3)) - ((short) ((j2 >> (i6 + i)) & 3)));
                }
            }
            return;
        }
        for (int i7 = 0; i7 < 64; i7++) {
            int i8 = i7 * 3;
            long j3 = ((long) (bArr2[i8] & 255)) | (((long) (bArr2[i8 + 1] & 255)) << 8) | (((long) (bArr2[i8 + 2] & 255)) << 16);
            long j4 = (j3 & 2396745) + ((j3 >> 1) & 2396745) + ((j3 >> 2) & 2396745);
            for (int i9 = 0; i9 < 4; i9++) {
                int i10 = i9 * 6;
                ((short[]) this.c)[(i7 * 4) + i9] = (short) (((short) ((j4 >> i10) & 7)) - ((short) ((j4 >> (i10 + 3)) & 7)));
            }
        }
    }

    public t g(int i) throws IOException {
        c90 c90Var = (c90) this.c;
        if (c90Var instanceof z30) {
            z30 z30Var = (z30) c90Var;
            z30Var.J = false;
            z30Var.l();
        }
        int iA = x.A(c90Var, i);
        boolean z = iA == 3 || iA == 4 || iA == 16 || iA == 17 || iA == 8;
        int i2 = this.b;
        int iY = x.y(c90Var, i2, z);
        byte[][] bArr = (byte[][]) this.d;
        if (iY < 0) {
            if ((i & 32) == 0) {
                throw new IOException("indefinite-length primitive encoding encountered");
            }
            l0 l0Var = new l0(new z30(c90Var, i2), i2, bArr);
            int i3 = i & 192;
            if (i3 != 0) {
                return new k9(i3, iA, l0Var);
            }
            if (iA == 3) {
                return new c9(l0Var);
            }
            if (iA == 4) {
                return new f9(l0Var);
            }
            if (iA == 8) {
                return new h9(l0Var);
            }
            if (iA == 16) {
                h9 h9Var = new h9(0);
                h9Var.F = l0Var;
                return h9Var;
            }
            if (iA == 17) {
                h9 h9Var2 = new h9(1);
                h9Var2.F = l0Var;
                return h9Var2;
            }
            throw new ASN1Exception("unknown BER object encountered: 0x" + Integer.toHexString(iA));
        }
        sn snVar = new sn(c90Var, iY, i2);
        if ((i & 224) == 0) {
            if (iA == 3) {
                return new kl(snVar);
            }
            if (iA == 4) {
                f9 f9Var = new f9();
                f9Var.F = snVar;
                return f9Var;
            }
            if (iA == 8) {
                throw new ASN1Exception("externals must use constructed encoding (see X.690 8.18)");
            }
            if (iA == 16) {
                throw new ASN1Exception("sets must use constructed encoding (see X.690 8.11.1/8.12.1)");
            }
            if (iA == 17) {
                throw new ASN1Exception("sequences must use constructed encoding (see X.690 8.9.1/8.10.1)");
            }
            try {
                return x.l(iA, snVar, bArr);
            } catch (IllegalArgumentException e) {
                throw new ASN1Exception(e, "corrupted stream detected");
            }
        }
        l0 l0Var2 = new l0(snVar, snVar.F, bArr);
        int i4 = i & 192;
        if (i4 != 0) {
            return new ol(i4, iA, (i & 32) != 0, l0Var2);
        }
        if (iA == 3) {
            return new c9(l0Var2);
        }
        if (iA == 4) {
            return new f9(l0Var2);
        }
        if (iA == 8) {
            return new h9(l0Var2);
        }
        if (iA == 16) {
            h9 h9Var3 = new h9(3);
            h9Var3.F = l0Var2;
            return h9Var3;
        }
        if (iA == 17) {
            h9 h9Var4 = new h9(4);
            h9Var4.F = l0Var2;
            return h9Var4;
        }
        throw new ASN1Exception("unknown DL object encountered: 0x" + Integer.toHexString(iA));
    }

    public boolean h() {
        ColorStateList colorStateList;
        return ((Shader) this.c) == null && (colorStateList = (ColorStateList) this.d) != null && colorStateList.isStateful();
    }

    public void i(AttributeSet attributeSet, int i) {
        int resourceId;
        ImageView imageView = (ImageView) this.c;
        ou ouVarP = ou.P(imageView.getContext(), attributeSet, R$styleable.AppCompatImageView, i);
        f51.n(imageView, imageView.getContext(), R$styleable.AppCompatImageView, attributeSet, (TypedArray) ouVarP.G, i);
        try {
            Drawable drawable = imageView.getDrawable();
            TypedArray typedArray = (TypedArray) ouVarP.G;
            if (drawable == null && (resourceId = typedArray.getResourceId(R$styleable.AppCompatImageView_srcCompat, -1)) != -1 && (drawable = qm.z(imageView.getContext(), resourceId)) != null) {
                imageView.setImageDrawable(drawable);
            }
            if (drawable != null) {
                op.a(drawable);
            }
            if (typedArray.hasValue(R$styleable.AppCompatImageView_tint)) {
                s30.c(imageView, ouVarP.y(R$styleable.AppCompatImageView_tint));
            }
            if (typedArray.hasValue(R$styleable.AppCompatImageView_tintMode)) {
                s30.d(imageView, op.c(typedArray.getInt(R$styleable.AppCompatImageView_tintMode, -1), null));
            }
            ouVarP.S();
        } catch (Throwable th) {
            ouVarP.S();
            throw th;
        }
    }

    public j9 j(int i, int i2) throws IOException {
        u uVarN = n();
        int i3 = uVarN.b;
        if (i3 == 1) {
            return new j9(3, i, i2, uVarN.d(0), 0);
        }
        g9 g9Var = d9.a;
        return new j9(4, i, i2, i3 < 1 ? d9.a : new g9(uVarN), 0);
    }

    public void k(ey0 ey0Var) throws IOException {
        q00 q00Var = (q00) ey0Var;
        Cursor cursorV = q00Var.v("SELECT count(*) FROM sqlite_master WHERE name != 'android_metadata'");
        try {
            boolean z = false;
            if (cursorV.moveToFirst()) {
                if (cursorV.getInt(0) == 0) {
                    z = true;
                }
            }
            cursorV.close();
            lp0 lp0Var = (lp0) this.d;
            lp0Var.createAllTables(ey0Var);
            if (!z) {
                mp0 mp0VarOnValidateSchema = lp0Var.onValidateSchema(ey0Var);
                if (!mp0VarOnValidateSchema.a) {
                    throw new IllegalStateException("Pre-packaged database has an invalid schema: " + mp0VarOnValidateSchema.b);
                }
            }
            q00Var.d("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
            q00Var.d("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '5181942b9ebc31ce68dacb56c16fd79f')");
            lp0Var.onCreate(ey0Var);
        } finally {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void l(defpackage.ey0 r7) throws java.io.IOException {
        /*
            r6 = this;
            r0 = r7
            q00 r0 = (defpackage.q00) r0
            java.lang.String r1 = "SELECT 1 FROM sqlite_master WHERE type = 'table' AND name='room_master_table'"
            android.database.Cursor r1 = r0.v(r1)
            boolean r2 = r1.moveToFirst()     // Catch: java.lang.Throwable -> L18
            r3 = 0
            if (r2 == 0) goto L1b
            int r2 = r1.getInt(r3)     // Catch: java.lang.Throwable -> L18
            if (r2 == 0) goto L1b
            r2 = 1
            goto L1c
        L18:
            r7 = move-exception
            goto L96
        L1b:
            r2 = r3
        L1c:
            r1.close()
            java.lang.Object r1 = r6.d
            lp0 r1 = (defpackage.lp0) r1
            r4 = 0
            if (r2 == 0) goto L65
            i6 r2 = new i6
            java.lang.String r5 = "SELECT identity_hash FROM room_master_table WHERE id = 42 LIMIT 1"
            r2.<init>(r5)
            android.database.Cursor r0 = r0.c(r2)
            boolean r2 = r0.moveToFirst()     // Catch: java.lang.Throwable -> L3c
            if (r2 == 0) goto L3e
            java.lang.String r2 = r0.getString(r3)     // Catch: java.lang.Throwable -> L3c
            goto L3f
        L3c:
            r7 = move-exception
            goto L5f
        L3e:
            r2 = r4
        L3f:
            r0.close()
            java.lang.String r0 = "5181942b9ebc31ce68dacb56c16fd79f"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L7a
            java.lang.String r0 = "ae2044fb577e65ee8bb576ca48a2f06e"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L53
            goto L7a
        L53:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "Room cannot verify the data integrity. Looks like you've changed schema but forgot to update the version number. You can simply fix this by increasing the version number. Expected identity hash: 5181942b9ebc31ce68dacb56c16fd79f, found: "
            java.lang.String r0 = defpackage.zn.v(r0, r2)
            r7.<init>(r0)
            throw r7
        L5f:
            throw r7     // Catch: java.lang.Throwable -> L60
        L60:
            r1 = move-exception
            defpackage.qm.h(r0, r7)
            throw r1
        L65:
            mp0 r2 = r1.onValidateSchema(r7)
            boolean r3 = r2.a
            if (r3 == 0) goto L80
            r1.onPostMigrate(r7)
            java.lang.String r2 = "CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)"
            r0.d(r2)
            java.lang.String r2 = "INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '5181942b9ebc31ce68dacb56c16fd79f')"
            r0.d(r2)
        L7a:
            r1.onOpen(r7)
            r6.c = r4
            return
        L80:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Pre-packaged database has an invalid schema: "
            r0.<init>(r1)
            java.lang.String r1 = r2.b
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r7.<init>(r0)
            throw r7
        L96:
            throw r7     // Catch: java.lang.Throwable -> L97
        L97:
            r0 = move-exception
            defpackage.qm.h(r1, r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.l0.l(ey0):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0085 A[EDGE_INSN: B:70:0x0085->B:37:0x0085 BREAK  A[LOOP:1: B:12:0x0022->B:73:?], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void m(defpackage.ey0 r13, int r14, int r15) {
        /*
            Method dump skipped, instruction units count: 278
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.l0.m(ey0, int, int):void");
    }

    public u n() throws IOException {
        c90 c90Var = (c90) this.c;
        int i = c90Var.read();
        if (i < 0) {
            return new u(0);
        }
        u uVar = new u();
        do {
            t tVarG = g(i);
            uVar.c(tVarG instanceof u30 ? ((u30) tVarG).d() : tVarG.b());
            i = c90Var.read();
        } while (i >= 0);
        return uVar;
    }

    public void o() {
        for (int i = 0; i < 256; i++) {
            short[] sArr = (short[]) this.c;
            short s = sArr[i];
            sArr[i] = (short) (s - ((short) (((short) ((((short) 20159) * s) >> 26)) * 3329)));
        }
    }

    public void p(int i) {
        ImageView imageView = (ImageView) this.c;
        if (i != 0) {
            Drawable drawableZ = qm.z(imageView.getContext(), i);
            if (drawableZ != null) {
                op.a(drawableZ);
            }
            imageView.setImageDrawable(drawableZ);
        } else {
            imageView.setImageDrawable(null);
        }
        b();
    }

    public String toString() {
        switch (this.a) {
            case 4:
                StringBuffer stringBuffer = new StringBuffer("[");
                int i = 0;
                while (true) {
                    short[] sArr = (short[]) this.c;
                    if (i >= sArr.length) {
                        stringBuffer.append("]");
                        return stringBuffer.toString();
                    }
                    stringBuffer.append((int) sArr[i]);
                    if (i != ((short[]) this.c).length - 1) {
                        stringBuffer.append(", ");
                    }
                    i++;
                }
                break;
            default:
                return super.toString();
        }
    }

    public l0(c90 c90Var, int i, byte[][] bArr) {
        this.a = 0;
        this.c = c90Var;
        this.b = i;
        this.d = bArr;
    }

    public l0(fb0 fb0Var) {
        this.a = 4;
        this.c = new short[NatsJetStreamConstants.MAX_PULL_SIZE];
        fb0Var.getClass();
        this.b = fb0Var.c;
        this.d = (tb) fb0Var.h;
    }

    public l0(hm hmVar, lp0 lp0Var) {
        this.a = 5;
        t40.n(hmVar, "configuration");
        int i = lp0Var.version;
        this.a = 5;
        this.b = i;
        this.c = hmVar;
        this.d = lp0Var;
    }

    public l0(ImageView imageView) {
        this.a = 1;
        this.b = 0;
        this.c = imageView;
    }

    public l0(Shader shader, ColorStateList colorStateList, int i) {
        this.a = 3;
        this.c = shader;
        this.d = colorStateList;
        this.b = i;
    }
}
