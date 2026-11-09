package defpackage;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public abstract class us {
    public static final v30 a = new v30(16);
    public static final ThreadPoolExecutor b;
    public static final Object c;
    public static final fj0 d;

    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 10000, TimeUnit.MILLISECONDS, new LinkedBlockingDeque(), new re0());
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        b = threadPoolExecutor;
        c = new Object();
        d = new fj0();
    }

    public static ts a(String str, Context context, o2 o2Var, int i) {
        v30 v30Var = a;
        Typeface typeface = (Typeface) v30Var.a(str);
        if (typeface != null) {
            return new ts(typeface);
        }
        try {
            w2 w2VarV = jq0.v(context, o2Var);
            int i2 = 1;
            at[] atVarArr = (at[]) w2VarV.c;
            int i3 = w2VarV.b;
            if (i3 != 0) {
                i2 = i3 != 1 ? -3 : -2;
            } else if (atVarArr != null && atVarArr.length != 0) {
                int length = atVarArr.length;
                i2 = 0;
                int i4 = 0;
                while (true) {
                    if (i4 >= length) {
                        break;
                    }
                    int i5 = atVarArr[i4].e;
                    if (i5 == 0) {
                        i4++;
                    } else if (i5 >= 0) {
                        i2 = i5;
                    }
                }
            }
            if (i2 != 0) {
                return new ts(i2);
            }
            Typeface typefaceP = sp0.a.p(context, atVarArr, i);
            if (typefaceP == null) {
                return new ts(-3);
            }
            v30Var.b(str, typefaceP);
            return new ts(typefaceP);
        } catch (PackageManager.NameNotFoundException unused) {
            return new ts(-1);
        }
    }
}
