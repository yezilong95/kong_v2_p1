package defpackage;

import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
public final class j80 implements Comparator {
    public static final j80 b = new j80(0);
    public static final j80 c = new j80(1);
    public final /* synthetic */ int a;

    public /* synthetic */ j80(int i) {
        this.a = i;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        switch (this.a) {
            case 0:
                Comparable comparable = (Comparable) obj;
                Comparable comparable2 = (Comparable) obj2;
                oy.j(comparable, "a");
                oy.j(comparable2, "b");
                return comparable.compareTo(comparable2);
            default:
                Comparable comparable3 = (Comparable) obj;
                Comparable comparable4 = (Comparable) obj2;
                oy.j(comparable3, "a");
                oy.j(comparable4, "b");
                return comparable4.compareTo(comparable3);
        }
    }

    @Override // java.util.Comparator
    public final Comparator reversed() {
        switch (this.a) {
            case 0:
                return c;
            default:
                return b;
        }
    }
}
