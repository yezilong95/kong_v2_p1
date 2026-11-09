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
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.util.regex.Pattern;
import okhttp3.Protocol;

/* JADX INFO: loaded from: classes.dex */
public final class d5 {
    public final /* synthetic */ int a;
    public int b;
    public Object c;
    public Object d;

    public d5(Protocol protocol, int i, String str) {
        this.a = 4;
        oy.j(protocol, "protocol");
        this.c = protocol;
        this.b = i;
        this.d = str;
    }

    public static final d5 b(String str, c60 c60Var) {
        oy.j(str, "<this>");
        Charset charset = zb.a;
        Pattern pattern = c60.c;
        Charset charsetA = c60Var.a(null);
        if (charsetA == null) {
            String str2 = c60Var + "; charset=utf-8";
            oy.j(str2, "<this>");
            try {
                c60Var = jq0.q(str2);
            } catch (IllegalArgumentException unused) {
                c60Var = null;
            }
        } else {
            charset = charsetA;
        }
        byte[] bytes = str.getBytes(charset);
        oy.i(bytes, "this as java.lang.String).getBytes(charset)");
        int length = bytes.length;
        kq0.c(bytes.length, 0, length);
        return new d5(c60Var, length, bytes);
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x01f6, code lost:
    
        if (r3.size() <= 0) goto L102;
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x01f8, code lost:
    
        r0 = new defpackage.e5(r3, r13);
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
    
        r0 = new defpackage.e5(r6, r7, r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x020b, code lost:
    
        r0 = new defpackage.e5(r6, r12);
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
    
        r13 = new android.graphics.LinearGradient(r22, r25, r16, r17, (int[]) r0.c, (float[]) r0.d, r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x023b, code lost:
    
        r13 = new android.graphics.SweepGradient(r5, r10, (int[]) r0.c, (float[]) r0.d);
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
    
        r13 = new android.graphics.RadialGradient(r5, r10, r24, (int[]) r0.c, (float[]) r0.d, r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x027f, code lost:
    
        return new defpackage.d5(r13, (android.content.res.ColorStateList) null, 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x0287, code lost:
    
        throw new org.xmlpull.v1.XmlPullParserException("<gradient> tag requires 'gradientRadius' attribute with radial type");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static defpackage.d5 c(android.content.res.Resources r26, int r27, android.content.res.Resources.Theme r28) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 688
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.d5.c(android.content.res.Resources, int, android.content.res.Resources$Theme):d5");
    }

    public static void d(String str) {
        if (str.equalsIgnoreCase(":memory:")) {
            return;
        }
        int length = str.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean z2 = oy.n(str.charAt(!z ? i : length), 32) <= 0;
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

    public void a() {
        qf qfVar;
        ImageView imageView = (ImageView) this.c;
        Drawable drawable = imageView.getDrawable();
        if (drawable != null) {
            ll.a(drawable);
        }
        if (drawable == null || (qfVar = (qf) this.d) == null) {
            return;
        }
        a5.e(drawable, qfVar, imageView.getDrawableState());
    }

    public boolean e() {
        ColorStateList colorStateList;
        return ((Shader) this.c) == null && (colorStateList = (ColorStateList) this.d) != null && colorStateList.isStateful();
    }

    public void f(AttributeSet attributeSet, int i) {
        int resourceId;
        ImageView imageView = (ImageView) this.c;
        po poVarN = po.N(imageView.getContext(), attributeSet, R$styleable.AppCompatImageView, i);
        xr0.o(imageView, imageView.getContext(), R$styleable.AppCompatImageView, attributeSet, (TypedArray) poVarN.d, i);
        try {
            Drawable drawable = imageView.getDrawable();
            TypedArray typedArray = (TypedArray) poVarN.d;
            if (drawable == null && (resourceId = typedArray.getResourceId(R$styleable.AppCompatImageView_srcCompat, -1)) != -1 && (drawable = rp0.y(imageView.getContext(), resourceId)) != null) {
                imageView.setImageDrawable(drawable);
            }
            if (drawable != null) {
                ll.a(drawable);
            }
            if (typedArray.hasValue(R$styleable.AppCompatImageView_tint)) {
                qx.c(imageView, poVarN.w(R$styleable.AppCompatImageView_tint));
            }
            if (typedArray.hasValue(R$styleable.AppCompatImageView_tintMode)) {
                qx.d(imageView, ll.c(typedArray.getInt(R$styleable.AppCompatImageView_tintMode, -1), null));
            }
            poVarN.P();
        } catch (Throwable th) {
            poVarN.P();
            throw th;
        }
    }

    public void g(ql0 ql0Var) throws IllegalAccessException, IOException, InvocationTargetException {
        eu euVar = (eu) ql0Var;
        Cursor cursorY = euVar.y("SELECT count(*) FROM sqlite_master WHERE name != 'android_metadata'");
        try {
            boolean z = false;
            if (cursorY.moveToFirst()) {
                if (cursorY.getInt(0) == 0) {
                    z = true;
                }
            }
            cursorY.close();
            qf0 qf0Var = (qf0) this.d;
            qf0Var.createAllTables(ql0Var);
            if (!z) {
                rf0 rf0VarOnValidateSchema = qf0Var.onValidateSchema(ql0Var);
                if (!rf0VarOnValidateSchema.a) {
                    throw new IllegalStateException("Pre-packaged database has an invalid schema: " + rf0VarOnValidateSchema.b);
                }
            }
            euVar.i("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
            euVar.i("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '5181942b9ebc31ce68dacb56c16fd79f')");
            qf0Var.onCreate(ql0Var);
        } finally {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void h(defpackage.ql0 r7) throws java.lang.IllegalAccessException, java.io.IOException, java.lang.reflect.InvocationTargetException {
        /*
            r6 = this;
            r0 = r7
            eu r0 = (defpackage.eu) r0
            java.lang.String r1 = "SELECT 1 FROM sqlite_master WHERE type = 'table' AND name='room_master_table'"
            android.database.Cursor r1 = r0.y(r1)
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
            qf0 r1 = (defpackage.qf0) r1
            r4 = 0
            if (r2 == 0) goto L65
            e5 r2 = new e5
            java.lang.String r5 = "SELECT identity_hash FROM room_master_table WHERE id = 42 LIMIT 1"
            r2.<init>(r5)
            android.database.Cursor r0 = r0.g(r2)
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
            java.lang.String r0 = defpackage.hk.n(r0, r2)
            r7.<init>(r0)
            throw r7
        L5f:
            throw r7     // Catch: java.lang.Throwable -> L60
        L60:
            r1 = move-exception
            defpackage.k9.i(r0, r7)
            throw r1
        L65:
            rf0 r2 = r1.onValidateSchema(r7)
            boolean r3 = r2.a
            if (r3 == 0) goto L80
            r1.onPostMigrate(r7)
            java.lang.String r2 = "CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)"
            r0.i(r2)
            java.lang.String r2 = "INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '5181942b9ebc31ce68dacb56c16fd79f')"
            r0.i(r2)
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
            defpackage.k9.i(r1, r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.d5.h(ql0):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0085 A[EDGE_INSN: B:70:0x0085->B:37:0x0085 BREAK  A[LOOP:1: B:12:0x0022->B:73:?], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void i(defpackage.ql0 r13, int r14, int r15) {
        /*
            Method dump skipped, instruction units count: 278
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.d5.i(ql0, int, int):void");
    }

    public void j(int i) {
        ImageView imageView = (ImageView) this.c;
        if (i != 0) {
            Drawable drawableY = rp0.y(imageView.getContext(), i);
            if (drawableY != null) {
                ll.a(drawableY);
            }
            imageView.setImageDrawable(drawableY);
        } else {
            imageView.setImageDrawable(null);
        }
        a();
    }

    public String toString() {
        switch (this.a) {
            case 4:
                StringBuilder sb = new StringBuilder();
                if (((Protocol) this.c) == Protocol.HTTP_1_0) {
                    sb.append("HTTP/1.0");
                } else {
                    sb.append("HTTP/1.1");
                }
                sb.append(' ');
                sb.append(this.b);
                sb.append(' ');
                sb.append((String) this.d);
                String string = sb.toString();
                oy.i(string, "StringBuilder().apply(builderAction).toString()");
                return string;
            default:
                return super.toString();
        }
    }

    public d5(c60 c60Var, int i, byte[] bArr) {
        this.a = 2;
        this.c = c60Var;
        this.b = i;
        this.d = bArr;
    }

    public d5(li liVar, qf0 qf0Var) {
        this.a = 3;
        oy.j(liVar, "configuration");
        int i = qf0Var.version;
        this.a = 3;
        this.b = i;
        this.c = liVar;
        this.d = qf0Var;
    }

    public d5(ImageView imageView) {
        this.a = 0;
        this.b = 0;
        this.c = imageView;
    }

    public d5(Shader shader, ColorStateList colorStateList, int i) {
        this.a = 1;
        this.c = shader;
        this.d = colorStateList;
        this.b = i;
    }
}
