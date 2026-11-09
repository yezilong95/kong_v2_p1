package defpackage;

import com.google.android.material.datepicker.l;
import com.google.android.material.navigation.NavigationBarItemView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ha implements Runnable {
    public final /* synthetic */ int b;
    public final int c;
    public final Object d;

    public /* synthetic */ ha(Object obj, int i, int i2) {
        this.b = i2;
        this.d = obj;
        this.c = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.c;
        Object obj = this.d;
        switch (this.b) {
            case 0:
                rp0 rp0Var = (rp0) ((d90) obj).b;
                if (rp0Var != null) {
                    rp0Var.L(i);
                }
                break;
            case 1:
                ArrayList arrayList = (ArrayList) obj;
                int size = arrayList.size();
                int i2 = 0;
                if (i == 1) {
                    while (i2 < size) {
                        ((fn) arrayList.get(i2)).b();
                        i2++;
                    }
                } else {
                    while (i2 < size) {
                        ((fn) arrayList.get(i2)).a();
                        i2++;
                    }
                }
                break;
            case 2:
                ((l) obj).b0.b0(i);
                break;
            case 3:
                int[] iArr = NavigationBarItemView.H;
                ((NavigationBarItemView) obj).i(i);
                break;
            default:
                ((zs0) obj).b0(i);
                break;
        }
    }

    public ha(int i, zs0 zs0Var) {
        this.b = 4;
        this.c = i;
        this.d = zs0Var;
    }

    public ha(List list, int i, Throwable th) {
        this.b = 1;
        k9.h(list, "initCallbacks cannot be null");
        this.d = new ArrayList(list);
        this.c = i;
    }
}
