package defpackage;

import android.content.res.Resources;
import android.os.Build;
import android.util.Log;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public abstract class sp0 {
    public static final rp0 a;
    public static final v30 b;

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 29) {
            a = new xp0();
        } else if (i >= 28) {
            a = new wp0();
        } else if (i >= 26) {
            a = new vp0();
        } else {
            Method method = up0.g;
            if (method == null) {
                Log.w("TypefaceCompatApi24Impl", "Unable to collect necessary private methods.Fallback to legacy implementation.");
            }
            if (method != null) {
                a = new up0();
            } else {
                a = new tp0();
            }
        }
        b = new v30(16);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.graphics.Typeface a(android.content.Context r14, defpackage.ws r15, android.content.res.Resources r16, int r17, java.lang.String r18, int r19, int r20, defpackage.rp0 r21, boolean r22) {
        /*
            Method dump skipped, instruction units count: 433
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.sp0.a(android.content.Context, ws, android.content.res.Resources, int, java.lang.String, int, int, rp0, boolean):android.graphics.Typeface");
    }

    public static String b(Resources resources, int i, String str, int i2, int i3) {
        return resources.getResourcePackageName(i) + '-' + str + '-' + i2 + '-' + i + '-' + i3;
    }
}
