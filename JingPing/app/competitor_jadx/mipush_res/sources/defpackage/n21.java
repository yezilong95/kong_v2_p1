package defpackage;

import android.content.res.Resources;
import android.os.Build;
import android.util.Log;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public abstract class n21 {
    public static final ma0 a;
    public static final eb0 b;

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 29) {
            a = new s21();
        } else if (i >= 28) {
            a = new r21();
        } else if (i >= 26) {
            a = new q21();
        } else {
            Method method = p21.s;
            if (method == null) {
                Log.w("TypefaceCompatApi24Impl", "Unable to collect necessary private methods.Fallback to legacy implementation.");
            }
            if (method != null) {
                a = new p21();
            } else {
                a = new o21();
            }
        }
        b = new eb0(16);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.graphics.Typeface a(android.content.Context r14, defpackage.kz r15, android.content.res.Resources r16, int r17, java.lang.String r18, int r19, int r20, defpackage.o80 r21, boolean r22) {
        /*
            Method dump skipped, instruction units count: 433
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.n21.a(android.content.Context, kz, android.content.res.Resources, int, java.lang.String, int, int, o80, boolean):android.graphics.Typeface");
    }

    public static String b(Resources resources, int i, String str, int i2, int i3) {
        return resources.getResourcePackageName(i) + '-' + str + '-' + i2 + '-' + i + '-' + i3;
    }
}
