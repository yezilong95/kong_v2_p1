package defpackage;

import android.graphics.Rect;
import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
public final class ns implements Comparator {
    public final Rect a = new Rect();
    public final Rect b = new Rect();
    public final boolean c;
    public final qm d;

    public ns(qm qmVar, boolean z) {
        this.c = z;
        this.d = qmVar;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        this.d.getClass();
        Rect rect = this.a;
        ((o0) obj).f(rect);
        Rect rect2 = this.b;
        ((o0) obj2).f(rect2);
        int i = rect.top;
        int i2 = rect2.top;
        if (i < i2) {
            return -1;
        }
        if (i > i2) {
            return 1;
        }
        int i3 = rect.left;
        int i4 = rect2.left;
        boolean z = this.c;
        if (i3 < i4) {
            return z ? 1 : -1;
        }
        if (i3 > i4) {
            return z ? -1 : 1;
        }
        int i5 = rect.bottom;
        int i6 = rect2.bottom;
        if (i5 < i6) {
            return -1;
        }
        if (i5 > i6) {
            return 1;
        }
        int i7 = rect.right;
        int i8 = rect2.right;
        if (i7 < i8) {
            return z ? 1 : -1;
        }
        if (i7 > i8) {
            return z ? -1 : 1;
        }
        return 0;
    }
}
