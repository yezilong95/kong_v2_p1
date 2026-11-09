package defpackage;

import android.util.Log;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class nm {
    public static final nm c = new nm();
    public static final String[] d = {"standard", "accelerate", "decelerate", "linear"};
    public final /* synthetic */ int a;
    public String b;

    public nm(String str) {
        this.a = 1;
        this.b = str;
    }

    public static nm c(String str) {
        if (str == null) {
            return null;
        }
        if (str.startsWith("cubic")) {
            return new mm(str);
        }
        switch (str) {
            case "accelerate":
                return new mm("cubic(0.4, 0.05, 0.8, 0.7)");
            case "decelerate":
                return new mm("cubic(0.0, 0.0, 0.2, 0.95)");
            case "linear":
                return new mm("cubic(1, 1, 0, 0)");
            case "standard":
                return new mm("cubic(0.4, 0.0, 0.2, 1)");
            default:
                Log.e("ConstraintSet", "transitionEasing syntax error syntax:transitionEasing=\"cubic(1.0,0.5,0.0,0.6)\" or " + Arrays.toString(d));
                return c;
        }
    }

    public double b(double d2) {
        return 1.0d;
    }

    public final String toString() {
        switch (this.a) {
            case 0:
                return this.b;
            default:
                return "<" + this.b + '>';
        }
    }

    public nm() {
        this.a = 0;
        this.b = "identity";
    }

    public double a(double d2) {
        return d2;
    }
}
