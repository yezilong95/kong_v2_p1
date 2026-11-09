package defpackage;

import com.google.android.material.datepicker.l;
import com.google.android.material.navigation.NavigationBarItemView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class wb implements Runnable {
    public final /* synthetic */ int E;
    public final int F;
    public final Object G;

    public /* synthetic */ wb(Object obj, int i, int i2) {
        this.E = i2;
        this.G = obj;
        this.F = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.F;
        Object obj = this.G;
        switch (this.E) {
            case 0:
                o80 o80Var = (o80) ((cw0) obj).F;
                if (o80Var != null) {
                    o80Var.W(i);
                }
                break;
            case 1:
                ArrayList arrayList = (ArrayList) obj;
                int size = arrayList.size();
                int i2 = 0;
                if (i == 1) {
                    while (i2 < size) {
                        ((xs) arrayList.get(i2)).b();
                        i2++;
                    }
                } else {
                    while (i2 < size) {
                        ((xs) arrayList.get(i2)).a();
                        i2++;
                    }
                }
                break;
            case 2:
                ((l) obj).E0.b0(i);
                break;
            case 3:
                int[] iArr = NavigationBarItemView.n0;
                ((NavigationBarItemView) obj).i(i);
                break;
            default:
                ((f61) obj).b0(i);
                break;
        }
    }

    public wb(int i, f61 f61Var) {
        this.E = 4;
        this.F = i;
        this.G = f61Var;
    }

    public wb(List list, int i, Throwable th) {
        this.E = 1;
        la0.i(list, "initCallbacks cannot be null");
        this.G = new ArrayList(list);
        this.F = i;
    }
}
