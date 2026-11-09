package defpackage;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import androidx.recyclerview.widget.n;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class mj extends ld0 {
    public static TimeInterpolator s;
    public boolean g;
    public ArrayList h;
    public ArrayList i;
    public ArrayList j;
    public ArrayList k;
    public ArrayList l;
    public ArrayList m;
    public ArrayList n;
    public ArrayList o;
    public ArrayList p;
    public ArrayList q;
    public ArrayList r;

    public static void h(ArrayList arrayList) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            ((n) arrayList.get(size)).a.animate().cancel();
        }
    }

    @Override // defpackage.ld0
    public final boolean a(n nVar, n nVar2, n90 n90Var, n90 n90Var2) {
        int i;
        int i2;
        int i3 = n90Var.a;
        int i4 = n90Var.b;
        if (nVar2.o()) {
            int i5 = n90Var.a;
            i2 = n90Var.b;
            i = i5;
        } else {
            i = n90Var2.a;
            i2 = n90Var2.b;
        }
        if (nVar == nVar2) {
            return g(nVar, i3, i4, i, i2);
        }
        View view = nVar.a;
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        float alpha = view.getAlpha();
        l(nVar);
        view.setTranslationX(translationX);
        view.setTranslationY(translationY);
        view.setAlpha(alpha);
        l(nVar2);
        float f = -((int) ((i - i3) - translationX));
        View view2 = nVar2.a;
        view2.setTranslationX(f);
        view2.setTranslationY(-((int) ((i2 - i4) - translationY)));
        view2.setAlpha(0.0f);
        ArrayList arrayList = this.k;
        kj kjVar = new kj();
        kjVar.a = nVar;
        kjVar.b = nVar2;
        kjVar.c = i3;
        kjVar.d = i4;
        kjVar.e = i;
        kjVar.f = i2;
        arrayList.add(kjVar);
        return true;
    }

    @Override // defpackage.ld0
    public final void d(n nVar) {
        View view = nVar.a;
        view.animate().cancel();
        ArrayList arrayList = this.j;
        int size = arrayList.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            if (((lj) arrayList.get(size)).a == nVar) {
                view.setTranslationY(0.0f);
                view.setTranslationX(0.0f);
                c(nVar);
                arrayList.remove(size);
            }
        }
        j(this.k, nVar);
        if (this.h.remove(nVar)) {
            view.setAlpha(1.0f);
            c(nVar);
        }
        if (this.i.remove(nVar)) {
            view.setAlpha(1.0f);
            c(nVar);
        }
        ArrayList arrayList2 = this.n;
        for (int size2 = arrayList2.size() - 1; size2 >= 0; size2--) {
            ArrayList arrayList3 = (ArrayList) arrayList2.get(size2);
            j(arrayList3, nVar);
            if (arrayList3.isEmpty()) {
                arrayList2.remove(size2);
            }
        }
        ArrayList arrayList4 = this.m;
        for (int size3 = arrayList4.size() - 1; size3 >= 0; size3--) {
            ArrayList arrayList5 = (ArrayList) arrayList4.get(size3);
            int size4 = arrayList5.size() - 1;
            while (true) {
                if (size4 < 0) {
                    break;
                }
                if (((lj) arrayList5.get(size4)).a == nVar) {
                    view.setTranslationY(0.0f);
                    view.setTranslationX(0.0f);
                    c(nVar);
                    arrayList5.remove(size4);
                    if (arrayList5.isEmpty()) {
                        arrayList4.remove(size3);
                    }
                } else {
                    size4--;
                }
            }
        }
        ArrayList arrayList6 = this.l;
        for (int size5 = arrayList6.size() - 1; size5 >= 0; size5--) {
            ArrayList arrayList7 = (ArrayList) arrayList6.get(size5);
            if (arrayList7.remove(nVar)) {
                view.setAlpha(1.0f);
                c(nVar);
                if (arrayList7.isEmpty()) {
                    arrayList6.remove(size5);
                }
            }
        }
        this.q.remove(nVar);
        this.o.remove(nVar);
        this.r.remove(nVar);
        this.p.remove(nVar);
        i();
    }

    @Override // defpackage.ld0
    public final void e() {
        ArrayList arrayList = this.j;
        int size = arrayList.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            lj ljVar = (lj) arrayList.get(size);
            View view = ljVar.a.a;
            view.setTranslationY(0.0f);
            view.setTranslationX(0.0f);
            c(ljVar.a);
            arrayList.remove(size);
        }
        ArrayList arrayList2 = this.h;
        for (int size2 = arrayList2.size() - 1; size2 >= 0; size2--) {
            c((n) arrayList2.get(size2));
            arrayList2.remove(size2);
        }
        ArrayList arrayList3 = this.i;
        int size3 = arrayList3.size();
        while (true) {
            size3--;
            if (size3 < 0) {
                break;
            }
            n nVar = (n) arrayList3.get(size3);
            nVar.a.setAlpha(1.0f);
            c(nVar);
            arrayList3.remove(size3);
        }
        ArrayList arrayList4 = this.k;
        for (int size4 = arrayList4.size() - 1; size4 >= 0; size4--) {
            kj kjVar = (kj) arrayList4.get(size4);
            n nVar2 = kjVar.a;
            if (nVar2 != null) {
                k(kjVar, nVar2);
            }
            n nVar3 = kjVar.b;
            if (nVar3 != null) {
                k(kjVar, nVar3);
            }
        }
        arrayList4.clear();
        if (f()) {
            ArrayList arrayList5 = this.m;
            for (int size5 = arrayList5.size() - 1; size5 >= 0; size5--) {
                ArrayList arrayList6 = (ArrayList) arrayList5.get(size5);
                for (int size6 = arrayList6.size() - 1; size6 >= 0; size6--) {
                    lj ljVar2 = (lj) arrayList6.get(size6);
                    View view2 = ljVar2.a.a;
                    view2.setTranslationY(0.0f);
                    view2.setTranslationX(0.0f);
                    c(ljVar2.a);
                    arrayList6.remove(size6);
                    if (arrayList6.isEmpty()) {
                        arrayList5.remove(arrayList6);
                    }
                }
            }
            ArrayList arrayList7 = this.l;
            for (int size7 = arrayList7.size() - 1; size7 >= 0; size7--) {
                ArrayList arrayList8 = (ArrayList) arrayList7.get(size7);
                for (int size8 = arrayList8.size() - 1; size8 >= 0; size8--) {
                    n nVar4 = (n) arrayList8.get(size8);
                    nVar4.a.setAlpha(1.0f);
                    c(nVar4);
                    arrayList8.remove(size8);
                    if (arrayList8.isEmpty()) {
                        arrayList7.remove(arrayList8);
                    }
                }
            }
            ArrayList arrayList9 = this.n;
            for (int size9 = arrayList9.size() - 1; size9 >= 0; size9--) {
                ArrayList arrayList10 = (ArrayList) arrayList9.get(size9);
                for (int size10 = arrayList10.size() - 1; size10 >= 0; size10--) {
                    kj kjVar2 = (kj) arrayList10.get(size10);
                    n nVar5 = kjVar2.a;
                    if (nVar5 != null) {
                        k(kjVar2, nVar5);
                    }
                    n nVar6 = kjVar2.b;
                    if (nVar6 != null) {
                        k(kjVar2, nVar6);
                    }
                    if (arrayList10.isEmpty()) {
                        arrayList9.remove(arrayList10);
                    }
                }
            }
            h(this.q);
            h(this.p);
            h(this.o);
            h(this.r);
            ArrayList arrayList11 = this.b;
            if (arrayList11.size() > 0) {
                arrayList11.get(0).getClass();
                throw new ClassCastException();
            }
            arrayList11.clear();
        }
    }

    @Override // defpackage.ld0
    public final boolean f() {
        return (this.i.isEmpty() && this.k.isEmpty() && this.j.isEmpty() && this.h.isEmpty() && this.p.isEmpty() && this.q.isEmpty() && this.o.isEmpty() && this.r.isEmpty() && this.m.isEmpty() && this.l.isEmpty() && this.n.isEmpty()) ? false : true;
    }

    public final boolean g(n nVar, int i, int i2, int i3, int i4) {
        View view = nVar.a;
        int translationX = i + ((int) view.getTranslationX());
        int translationY = i2 + ((int) nVar.a.getTranslationY());
        l(nVar);
        int i5 = i3 - translationX;
        int i6 = i4 - translationY;
        if (i5 == 0 && i6 == 0) {
            c(nVar);
            return false;
        }
        if (i5 != 0) {
            view.setTranslationX(-i5);
        }
        if (i6 != 0) {
            view.setTranslationY(-i6);
        }
        ArrayList arrayList = this.j;
        lj ljVar = new lj();
        ljVar.a = nVar;
        ljVar.b = translationX;
        ljVar.c = translationY;
        ljVar.d = i3;
        ljVar.e = i4;
        arrayList.add(ljVar);
        return true;
    }

    public final void i() {
        if (f()) {
            return;
        }
        ArrayList arrayList = this.b;
        if (arrayList.size() <= 0) {
            arrayList.clear();
        } else {
            arrayList.get(0).getClass();
            throw new ClassCastException();
        }
    }

    public final void j(ArrayList arrayList, n nVar) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            kj kjVar = (kj) arrayList.get(size);
            if (k(kjVar, nVar) && kjVar.a == null && kjVar.b == null) {
                arrayList.remove(kjVar);
            }
        }
    }

    public final boolean k(kj kjVar, n nVar) {
        if (kjVar.b == nVar) {
            kjVar.b = null;
        } else {
            if (kjVar.a != nVar) {
                return false;
            }
            kjVar.a = null;
        }
        nVar.a.setAlpha(1.0f);
        View view = nVar.a;
        view.setTranslationX(0.0f);
        view.setTranslationY(0.0f);
        c(nVar);
        return true;
    }

    public final void l(n nVar) {
        if (s == null) {
            s = new ValueAnimator().getInterpolator();
        }
        nVar.a.animate().setInterpolator(s);
        d(nVar);
    }
}
