package defpackage;

import android.util.Log;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class vr implements sj {
    public static final vr c = new vr();
    public static final String[] d = {"standard", "accelerate", "decelerate", "linear"};
    public final /* synthetic */ int a;
    public String b;

    public /* synthetic */ vr(String str, int i) {
        this.a = i;
        this.b = str;
    }

    public static vr d(String str) {
        if (str == null) {
            return null;
        }
        if (str.startsWith("cubic")) {
            return new ur(str);
        }
        switch (str) {
            case "accelerate":
                return new ur("cubic(0.4, 0.05, 0.8, 0.7)");
            case "decelerate":
                return new ur("cubic(0.0, 0.0, 0.2, 0.95)");
            case "linear":
                return new ur("cubic(1, 1, 0, 0)");
            case "standard":
                return new ur("cubic(0.4, 0.0, 0.2, 1)");
            default:
                Log.e("ConstraintSet", "transitionEasing syntax error syntax:transitionEasing=\"cubic(1.0,0.5,0.0,0.6)\" or " + Arrays.toString(d));
                return c;
        }
    }

    @Override // defpackage.sj
    public String a() {
        switch (this.a) {
        }
        return this.b;
    }

    public double c(double d2) {
        return 1.0d;
    }

    public String toString() {
        switch (this.a) {
            case 0:
                return this.b;
            default:
                return super.toString();
        }
    }

    public vr() {
        this.a = 0;
        this.b = "identity";
    }

    public double b(double d2) {
        return d2;
    }
}
