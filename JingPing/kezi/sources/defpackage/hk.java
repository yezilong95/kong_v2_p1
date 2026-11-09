package defpackage;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.c;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class hk {
    public static final void a(View view, int i) {
        int iO = o(i);
        if (iO == 0) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if (viewGroup != null) {
                if (c.C(2)) {
                    Log.v("FragmentManager", "SpecialEffectsController: Removing view " + view + " from container " + viewGroup);
                }
                viewGroup.removeView(view);
                return;
            }
            return;
        }
        if (iO == 1) {
            if (c.C(2)) {
                Log.v("FragmentManager", "SpecialEffectsController: Setting view " + view + " to VISIBLE");
            }
            view.setVisibility(0);
            return;
        }
        if (iO == 2) {
            if (c.C(2)) {
                Log.v("FragmentManager", "SpecialEffectsController: Setting view " + view + " to GONE");
            }
            view.setVisibility(8);
            return;
        }
        if (iO != 3) {
            return;
        }
        if (c.C(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Setting view " + view + " to INVISIBLE");
        }
        view.setVisibility(4);
    }

    public static int b(int i) {
        if (i == 0) {
            return 2;
        }
        if (i == 4) {
            return 4;
        }
        if (i == 8) {
            return 3;
        }
        throw new IllegalArgumentException(g(i, "Unknown visibility "));
    }

    public static int c(View view) {
        if (view.getAlpha() == 0.0f && view.getVisibility() == 0) {
            return 4;
        }
        return b(view.getVisibility());
    }

    public static float d(float f, float f2, float f3, float f4) {
        return ((f - f2) * f3) + f4;
    }

    public static ClassCastException e(Iterator it) {
        it.next().getClass();
        return new ClassCastException();
    }

    public static String f(int i, int i2, String str, String str2) {
        return str + i + str2 + i2;
    }

    public static String g(int i, String str) {
        return str + i;
    }

    public static String h(int i, String str, String str2) {
        return str + i + str2;
    }

    public static String i(String str, String str2) {
        return str + str2;
    }

    public static String j(String str, String str2, String str3) {
        return str + str2 + str3;
    }

    public static String k(StringBuilder sb, String str, String str2) {
        sb.append(str);
        sb.append(str2);
        return sb.toString();
    }

    public static StringBuilder l(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        return sb;
    }

    public static /* synthetic */ void m(Object obj) {
        if (obj != null) {
            throw new ClassCastException();
        }
    }

    public static String n(String str, String str2) {
        return str + str2;
    }

    public static /* synthetic */ int o(int i) {
        if (i != 0) {
            return i - 1;
        }
        throw null;
    }

    public static /* synthetic */ String p(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "null" : "REMOVING" : "ADDING" : "NONE";
    }

    public static /* synthetic */ String q(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "null" : "INVISIBLE" : "GONE" : "VISIBLE" : "REMOVED";
    }
}
