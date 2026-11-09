package defpackage;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Comparator;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class l3 implements Comparator {
    public final /* synthetic */ int a;

    public /* synthetic */ l3(int i) {
        this.a = i;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        switch (this.a) {
            case 0:
                return ((int[]) obj)[0] - ((int[]) obj2)[0];
            case 1:
                String str = (String) obj;
                String str2 = (String) obj2;
                oy.j(str, "a");
                oy.j(str2, "b");
                int iMin = Math.min(str.length(), str2.length());
                int i = 4;
                while (true) {
                    if (i >= iMin) {
                        int length = str.length();
                        int length2 = str2.length();
                        if (length == length2) {
                            return 0;
                        }
                        if (length < length2) {
                            return -1;
                        }
                    } else {
                        char cCharAt = str.charAt(i);
                        char cCharAt2 = str2.charAt(i);
                        if (cCharAt == cCharAt2) {
                            i++;
                        } else if (oy.n(cCharAt, cCharAt2) < 0) {
                            return -1;
                        }
                    }
                }
                return 1;
            case 2:
                WeakHashMap weakHashMap = xr0.a;
                float fM = lr0.m((View) obj);
                float fM2 = lr0.m((View) obj2);
                if (fM > fM2) {
                    return -1;
                }
                return fM < fM2 ? 1 : 0;
            case 3:
                av avVar = (av) obj;
                av avVar2 = (av) obj2;
                RecyclerView recyclerView = avVar.d;
                if ((recyclerView == null) == (avVar2.d == null)) {
                    boolean z = avVar.a;
                    if (z == avVar2.a) {
                        int i2 = avVar2.b - avVar.b;
                        if (i2 != 0) {
                            return i2;
                        }
                        int i3 = avVar.c - avVar2.c;
                        if (i3 != 0) {
                            return i3;
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
            case 4:
                if (obj != null) {
                    throw new ClassCastException();
                }
                obj2.getClass();
                throw new ClassCastException();
            case 5:
                return ((Comparable) obj).compareTo((Comparable) obj2);
            case 6:
                return ((Comparable) obj).compareTo((Comparable) obj2);
            case 7:
                return ((sj0) obj).b - ((sj0) obj2).b;
            case 8:
                return ((View) obj).getTop() - ((View) obj2).getTop();
            default:
                return ((os0) obj).a - ((os0) obj2).a;
        }
    }
}
