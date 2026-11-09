package defpackage;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public abstract class iz {
    public static final eb0 a = new eb0(16);
    public static final ThreadPoolExecutor b;
    public static final Object c;
    public static final vv0 d;

    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 10000, TimeUnit.MILLISECONDS, new LinkedBlockingDeque(), new bp(1));
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        b = threadPoolExecutor;
        c = new Object();
        d = new vv0();
    }

    public static hz a(String str, Context context, c4 c4Var, int i) {
        eb0 eb0Var = a;
        Typeface typeface = (Typeface) eb0Var.a(str);
        if (typeface != null) {
            return new hz(typeface);
        }
        try {
            j4 j4VarO = l21.O(context, c4Var);
            int i2 = 1;
            oz[] ozVarArr = (oz[]) j4VarO.F;
            int i3 = j4VarO.E;
            if (i3 != 0) {
                i2 = i3 != 1 ? -3 : -2;
            } else if (ozVarArr != null && ozVarArr.length != 0) {
                int length = ozVarArr.length;
                i2 = 0;
                int i4 = 0;
                while (true) {
                    if (i4 >= length) {
                        break;
                    }
                    int i5 = ozVarArr[i4].e;
                    if (i5 == 0) {
                        i4++;
                    } else if (i5 >= 0) {
                        i2 = i5;
                    }
                }
            }
            if (i2 != 0) {
                return new hz(i2);
            }
            Typeface typefaceP = n21.a.p(context, ozVarArr, i);
            if (typefaceP == null) {
                return new hz(-3);
            }
            eb0Var.b(str, typefaceP);
            return new hz(typefaceP);
        } catch (PackageManager.NameNotFoundException unused) {
            return new hz(-1);
        }
    }
}
