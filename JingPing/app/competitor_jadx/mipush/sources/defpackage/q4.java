package defpackage;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Comparator;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class q4 implements Comparator {
    public final /* synthetic */ int a;

    public /* synthetic */ q4(int i) {
        this.a = i;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        switch (this.a) {
            case 0:
                return ((int[]) obj)[0] - ((int[]) obj2)[0];
            case 1:
                WeakHashMap weakHashMap = f51.a;
                float fM = t41.m((View) obj);
                float fM2 = t41.m((View) obj2);
                if (fM > fM2) {
                    return -1;
                }
                return fM < fM2 ? 1 : 0;
            case 2:
                x10 x10Var = (x10) obj;
                x10 x10Var2 = (x10) obj2;
                RecyclerView recyclerView = x10Var.d;
                if ((recyclerView == null) == (x10Var2.d == null)) {
                    boolean z = x10Var.a;
                    if (z == x10Var2.a) {
                        int i = x10Var2.b - x10Var.b;
                        if (i != 0) {
                            return i;
                        }
                        int i2 = x10Var.c - x10Var2.c;
                        if (i2 != 0) {
                            return i2;
                        }
                        return 0;
                    }
                    if (z) {
                        return -1;
                    }
                } else if (recyclerView != null) {
                    return -1;
                }
                return 1;
            case 3:
                if (obj != null) {
                    throw new ClassCastException();
                }
                obj2.getClass();
                throw new ClassCastException();
            case 4:
                return ((Comparable) obj).compareTo((Comparable) obj2);
            case 5:
                return ((Comparable) obj).compareTo((Comparable) obj2);
            case 6:
                return ((hw0) obj).b - ((hw0) obj2).b;
            case 7:
                return ((View) obj).getTop() - ((View) obj2).getTop();
            default:
                return ((v51) obj).a - ((v51) obj2).a;
        }
    }
}
